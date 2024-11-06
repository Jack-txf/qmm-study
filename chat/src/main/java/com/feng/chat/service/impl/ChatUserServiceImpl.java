package com.feng.chat.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feng.chat.common.R;
import com.feng.chat.entity.ChatUser;
import com.feng.chat.entity.dto.FriendDto;
import com.feng.chat.entity.dto.LoginUser;
import com.feng.chat.exception.MyException;
import com.feng.chat.mapper.ChatMsgMapper;
import com.feng.chat.mapper.ChatUserMapper;
import com.feng.chat.service.ChatUserService;
import com.feng.chat.utils.TokenSecretUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * (ChatUser)表服务实现类
 *
 * @author makejava
 * @since 2024-07-31 20:35:31
 */
@Service("chatUserService")
@SuppressWarnings("unchecked")
public class ChatUserServiceImpl extends ServiceImpl<ChatUserMapper, ChatUser> implements ChatUserService {
    @Resource
    private ChatUserMapper chatUserMapper;
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public R loginByUsernamePassword(LoginUser loginUser) {
        ChatUser user = chatUserMapper.selectOne(new QueryWrapper<ChatUser>().eq("username", loginUser.getUsername()));
        if ( user == null ) throw new MyException("该账号不存在");
        if ( !user.getPassword().equals(loginUser.getPassword()) ) throw new MyException("账号密码不匹配");
        // sa-token -- 有效期在配置文件里面看得到
        StpUtil.login(user.getUid());
        String tokenValue = StpUtil.getTokenValue();
        redisTemplate.opsForValue().set(tokenValue, "userToken:" + user.getUid(), 60, TimeUnit.MINUTES);

        return R.success()
                .setData("userInfo", user)
                .setData("msg", "登录成功！")
                .setData("chatToken", tokenValue);
    }

    @Override
    public List<FriendDto> getMyFriends(Long uid) {
        return chatUserMapper.selectFriends(uid);
    }
}

