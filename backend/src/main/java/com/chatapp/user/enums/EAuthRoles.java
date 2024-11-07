package com.chatapp.user.enums;

public enum EAuthRoles{
    USER,
    ADMIN;
    public String getRoleName() {
        return this.name();
    }
}
