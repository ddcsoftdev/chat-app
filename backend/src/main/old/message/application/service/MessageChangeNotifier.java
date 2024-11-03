package old.message.application.service;

import old.conversation.domain.vo.ConversationViewedForNotification;
import old.message.domain.aggregate.Message;
import old.conversation.domain.vo.ConversationPublicId;
import old.shared.service.State;
import old.user.domain.vo.UserPublicId;

import java.util.List;

public interface MessageChangeNotifier {

    State<Void, String> send(Message message, List<UserPublicId> userToNotify);

    State<Void, String> delete(ConversationPublicId conversationPublicId, List<UserPublicId> userToNotify);

    State<Void, String> view(ConversationViewedForNotification conversationViewedForNotification, List<UserPublicId> usersToNotify);
}
