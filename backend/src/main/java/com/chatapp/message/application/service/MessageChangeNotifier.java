package com.chatapp.message.application.service;

import com.chatapp.conversation.domain.vo.ConversationViewedForNotification;
import com.chatapp.message.domain.aggregate.Message;
import com.chatapp.conversation.domain.vo.ConversationPublicId;
import com.chatapp.shared.service.State;
import com.chatapp.user.domain.vo.UserPublicId;

import java.util.List;

public interface MessageChangeNotifier {

    State<Void, String> send(Message message, List<UserPublicId> userToNotify);

    State<Void, String> delete(ConversationPublicId conversationPublicId, List<UserPublicId> userToNotify);

    State<Void, String> view(ConversationViewedForNotification conversationViewedForNotification, List<UserPublicId> usersToNotify);
}
