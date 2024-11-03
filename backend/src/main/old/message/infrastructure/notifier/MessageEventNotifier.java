package old.message.infrastructure.notifier;


import old.conversation.domain.vo.ConversationIdWithUsers;
import old.conversation.domain.vo.ConversationViewedForNotification;
import old.message.domain.vo.MessageIdWithUsers;
import old.message.domain.vo.MessageNotificationData;
import old.message.infrastructure.dto.MessageDto;
import old.message.domain.aggregate.Message;
import old.message.application.service.MessageChangeNotifier;
import old.conversation.domain.vo.ConversationPublicId;
import old.notification.application.service.NotificationService;
import old.notification.domain.vo.NotificationEventType;
import old.shared.service.State;
import old.user.domain.vo.UserPublicId;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageEventNotifier implements MessageChangeNotifier {

    private final NotificationService notificationService;
    private ApplicationEventPublisher applicationEventPublisher;

    public MessageEventNotifier(ApplicationEventPublisher applicationEventPublisher, NotificationService notificationService) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.notificationService = notificationService;
    }

    @Override
    public State<Void, String> send(Message message, List<UserPublicId> userToNotify) {
        MessageNotificationData messageWithUsers = new MessageNotificationData(message, userToNotify);
        applicationEventPublisher.publishEvent(messageWithUsers);
        return State.<Void, String>builder().forSuccess();
    }

    @Override
    public State<Void, String> delete(ConversationPublicId conversationPublicId,
                                      List<UserPublicId> userToNotify) {
        ConversationIdWithUsers conversationIdWithUsers = new ConversationIdWithUsers(conversationPublicId, userToNotify);
        applicationEventPublisher.publishEvent(conversationIdWithUsers);
        return State.<Void, String>builder().forSuccess();
    }

    @Override
    public State<Void, String> view(ConversationViewedForNotification conversationViewedForNotification, List<UserPublicId> usersToNotify) {
        MessageIdWithUsers messageIdWithUsers = new MessageIdWithUsers(conversationViewedForNotification, usersToNotify);
        applicationEventPublisher.publishEvent(messageIdWithUsers);
        return State.<Void, String>builder().forSuccess();
    }

    @EventListener
    public void handleDeleteConversation(ConversationIdWithUsers conversationIdWithUsers) {
        notificationService.sendMessage(conversationIdWithUsers.conversationPublicId().value(),
                conversationIdWithUsers.users(), NotificationEventType.DELETE_CONVERSATION);
    }

    @EventListener
    public void handleNewMessage(MessageNotificationData messageWithUsers) {
        notificationService.sendMessage(MessageDto.from(messageWithUsers.message()),
                messageWithUsers.userToNotify(), NotificationEventType.NEW_MESSAGE);
    }

    @EventListener
    public void handleView(MessageIdWithUsers messageIdWithUsers) {
        notificationService.sendMessage(messageIdWithUsers.conversationViewedForNotification(),
                messageIdWithUsers.usersToNotify(), NotificationEventType.VIEWS_MESSAGES);
    }
}
