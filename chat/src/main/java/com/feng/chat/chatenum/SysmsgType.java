package com.feng.chat.chatenum;

import lombok.Getter;

/**
 * @author Williams_Tian
 * @CreateDate 2024/11/12
 */
@Getter
public enum SysmsgType {
    FriendInvite(0),
    AddGroup(1),
    InviteInGroup(2);

    SysmsgType(Integer sysmsgType) {
        this.sysmsgType = sysmsgType;
    }
    private final Integer sysmsgType;
}
