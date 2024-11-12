package com.feng.chat.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feng.chat.chatenum.SysmsgType;
import com.feng.chat.common.R;
import com.feng.chat.entity.ChatUser;
import com.feng.chat.entity.SysMsg;
import com.feng.chat.entity.dto.FriendDto;
import com.feng.chat.entity.dto.LoginUser;
import com.feng.chat.entity.dto.UpdateFormDto;
import com.feng.chat.exception.MyException;
import com.feng.chat.mapper.ChatMsgMapper;
import com.feng.chat.mapper.ChatUserMapper;
import com.feng.chat.service.ChatUserService;
import com.feng.chat.service.SysmsgService;
import com.feng.chat.utils.TokenSecretUtil;
import com.feng.chat.utils.UserContextUtil;
import com.feng.chat.websocket.WebSocketChatServerHandler;
import org.apache.catalina.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * (ChatUser)表服务实现类
 *
 * @author makejava
 * @since 2024-07-31 20:35:31
 */
@Service("chatUserService")
public class ChatUserServiceImpl extends ServiceImpl<ChatUserMapper, ChatUser> implements ChatUserService {
    @Resource
    private SysmsgService sysmsgService;
    @Resource
    private ChatUserMapper chatUserMapper;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private WebSocketChatServerHandler webSocketChatServerHandler;

    @Override
    public R loginByUsernamePassword(LoginUser loginUser) {
        ChatUser user = chatUserMapper.selectOne(new QueryWrapper<ChatUser>().eq("username", loginUser.getUsername()));
        if ( user == null ) throw new MyException("该账号不存在");
        if ( !user.getPassword().equals(loginUser.getPassword()) ) throw new MyException("账号密码不匹配");
        // sa-token -- 有效期在配置文件里面看得到
        StpUtil.login(user.getUid());
        String tokenValue = StpUtil.getTokenValue();
        redisTemplate.opsForValue().set(tokenValue, "userToken:" + user.getUid(), 60, TimeUnit.MINUTES);

        user.setPassword("");

        return R.success()
                .setData("userInfo", user)
                .setData("msg", "登录成功！")
                .setData("chatToken", tokenValue);
    }

    @Override
    public List<FriendDto> getMyFriends(Long uid) {
        return chatUserMapper.selectFriends(uid);
    }

    @Override
    public R logout() {
        // 登出
        StpUtil.logout(); // 会话登出

        // websocket中移除
        webSocketChatServerHandler.userLogout(UserContextUtil.getUid());

        // redis删除
        String token = StpUtil.getTokenValue();
        redisTemplate.opsForValue().getAndDelete(token);

        return R.success().setData("msg", "登出成功!");
    }

    @Override
    public R getMyBaseInfo() {
        Long uid = UserContextUtil.getUid();
        ChatUser chatUser = chatUserMapper.selectById(uid);
        chatUser.setPassword("");
        return R.success().setData("myBaseInfo", chatUser);
    }

    @Override
    public R updateNick(UpdateFormDto updateFormDto) {
        String nick = updateFormDto.getNick(); // 新的昵称
        if ( nick == null || nick.isEmpty() ) return R.fail().setData("msg", "昵称长度不能为0!");
        Long uid = UserContextUtil.getUid();

        ChatUser user = chatUserMapper.selectOne(new LambdaQueryWrapper<ChatUser>().eq(ChatUser::getNick, nick));
        if ( user != null ) { // 不能更新
            if ( user.getUid().equals(uid) ) return R.fail().setData("msg", "修改前后昵称相同!");
            else return R.fail().setData("msg", "改昵称已被使用!");
        }
        chatUserMapper.updateNick(nick, uid); // 可以更新
        ChatUser chatUser = chatUserMapper.selectById(uid);
        chatUser.setPassword("");

        return R.success().setData("myBaseInfo", chatUser);
    }

    @Override
    public R updatePwd(UpdateFormDto updateFormDto) {
        String oldPassword = updateFormDto.getOldPassword();
        String password = updateFormDto.getPassword();
        if (oldPassword == null || oldPassword.equals(password))
            return R.fail().setData("msg", "新旧密码一致！");
        Long uid = UserContextUtil.getUid();
        ChatUser chatUser = chatUserMapper.selectById(uid);
        if ( !chatUser.getPassword().equals(oldPassword) )
            return R.fail().setData("msg", "您输入的旧密码错误!");
        chatUserMapper.updatePwd(uid, password);
        return R.success().setData("msg", "密码修改成功!");
    }

    @Override
    public R findFriendsByChatNo(String chatNo) {
        ChatUser me = chatUserMapper.selectById(UserContextUtil.getUid());
        // chatNo就是chat号，也就是username
        ChatUser user = chatUserMapper.selectOne(new LambdaQueryWrapper<ChatUser>().eq(ChatUser::getUsername, chatNo));
        if ( user == null ) return R.fail().setData("msg", "无法根据该chat号找到您要的好友!");
        if ( me.getUsername().equals(user.getUsername())) return R.fail().setData("msg", "你搜你自己干嘛!??");
        user.setPassword("");
        return R.success().setData("friend", user).setData("msg", "查找成功!");
    }

    //{ "uid": uid, "username": username } uid和chat号
    @Override // 发送好友申请
    public R sendFriendInvite(Map<String, Object> invite) {
        Long uid = Long.valueOf((String) invite.get("uid")); // 对方的uid
        String username = (String) invite.get("username"); // chat号
        // 1.插入数据库中
        SysMsg sysMsg = SysMsg.builder()
                .msgType(SysmsgType.FriendInvite.getSysmsgType()).sendUser(UserContextUtil.getUid())
                .toUser(uid).isRead(false).build();
        sysmsgService.save(sysMsg); // 先插入数据库

        // 2.给对方发送过去，如果在线的话
        webSocketChatServerHandler.sendSysMsgToUser(sysMsg);

        return null;
    }
}

