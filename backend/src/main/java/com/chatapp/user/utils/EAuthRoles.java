package com.chatapp.user.utils;

public enum EAuthRoles{
    USER,
    ADMIN;
    public String getRoleName() {
        return this.name();
    }
}
