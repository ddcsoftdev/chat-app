package old.conversation.application.service;

import old.conversation.domain.vo.ConversationViewedForNotification;
import old.message.domain.aggregate.Message;
import old.message.domain.repository.MessageRepository;
import old.message.application.service.MessageChangeNotifier;
import old.conversation.domain.vo.ConversationPublicId;
import old.message.domain.vo.MessageSendState;
import old.shared.service.State;
import old.user.application.service.UserReaderService;
import old.user.domain.aggregate.User;
import old.user.domain.vo.UserPublicId;

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
