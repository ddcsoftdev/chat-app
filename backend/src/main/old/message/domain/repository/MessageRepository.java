package old.message.domain.repository;

import old.conversation.domain.aggregate.Conversation;
import old.message.domain.aggregate.Message;
import old.conversation.domain.vo.ConversationPublicId;
import old.message.domain.vo.MessageSendState;
import old.user.domain.aggregate.User;
import old.user.domain.vo.UserPublicId;

import java.util.List;

public interface MessageRepository {

    Message save(Message message, User sender, Conversation conversation);

    int updateMessageSendState(ConversationPublicId conversationPublicId, UserPublicId userPublicId, MessageSendState state);

    List<Message> findMessageToUpdateSendState(ConversationPublicId conversationPublicId, UserPublicId userPublicId);

}

