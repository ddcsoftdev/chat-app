package com.chatapp.notification.domain.vo;

public enum NotificationEventType {
    NEW_MESSAGE("message"), DELETE_CONVERSATION("delete-conversation"),
    VIEWS_MESSAGES("view-messages");

    public final String value;

    NotificationEventType(String value) {
        this.value = value;
    }
}
