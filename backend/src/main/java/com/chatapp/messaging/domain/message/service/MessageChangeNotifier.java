package com.chatapp.messaging.domain.message.service;

import com.chatapp.infrastructure.secundary.message.ConversationViewedForNotification;
import com.chatapp.messaging.domain.message.aggregate.Message;
import com.chatapp.messaging.domain.message.vo.ConversationPublicId;
import com.chatapp.shared.service.State;
import com.chatapp.user.domain.vo.UserPublicId;

import java.util.List;

public interface MessageChangeNotifier {

    State<Void, String> send(Message message, List<UserPublicId> userToNotify);

    State<Void, String> delete(ConversationPublicId conversationPublicId, List<UserPublicId> userToNotify);

    State<Void, String> view(ConversationViewedForNotification conversationViewedForNotification, List<UserPublicId> usersToNotify);
}
