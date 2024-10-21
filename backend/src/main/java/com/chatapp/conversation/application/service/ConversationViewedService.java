package com.chatapp.conversation.application.service;

import com.chatapp.conversation.domain.vo.ConversationViewedForNotification;
import com.chatapp.messaging.domain.message.aggregate.Message;
import com.chatapp.messaging.domain.message.repository.MessageRepository;
import com.chatapp.messaging.domain.message.service.MessageChangeNotifier;
import com.chatapp.messaging.domain.message.vo.ConversationPublicId;
import com.chatapp.messaging.domain.message.vo.MessageSendState;
import com.chatapp.shared.service.State;
import com.chatapp.user.application.service.UserReaderService;
import com.chatapp.user.domain.aggregate.User;
import com.chatapp.user.domain.vo.UserPublicId;

import java.util.List;

public class ConversationViewedService {

    private final MessageRepository messageRepository;
    private final MessageChangeNotifier messageChangeNotifier;
    private final UserReaderService userReader;

    public ConversationViewedService(MessageRepository messageRepository, MessageChangeNotifier messageChangeNotifier, UserReaderService userReader) {
        this.messageRepository = messageRepository;
        this.messageChangeNotifier = messageChangeNotifier;
        this.userReader = userReader;
    }

    public State<Integer, String> markAsRead(ConversationPublicId conversationPublicId, UserPublicId connectedUserPublicId) {
        List<Message> messageToUpdateSendState = messageRepository.findMessageToUpdateSendState(conversationPublicId, connectedUserPublicId);
        int nbUpdatedMessages = messageRepository.updateMessageSendState(conversationPublicId, connectedUserPublicId, MessageSendState.READ);
        List<UserPublicId> usersToNotify = userReader.findUsersToNotify(conversationPublicId, connectedUserPublicId)
                .stream().map(User::getUserPublicId).toList();
        ConversationViewedForNotification conversationViewedForNotification = new ConversationViewedForNotification(conversationPublicId.value(),
                messageToUpdateSendState.stream().map(message -> message.getPublicId().value()).toList());
        messageChangeNotifier.view(conversationViewedForNotification, usersToNotify);

        if (nbUpdatedMessages > 0) {
            return State.<Integer, String>builder().forSuccess(nbUpdatedMessages);
        } else {
            return State.<Integer, String>builder().forSuccess();
        }
    }
}
