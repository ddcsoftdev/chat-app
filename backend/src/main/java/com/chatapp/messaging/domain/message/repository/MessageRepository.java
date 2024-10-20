package com.chatapp.messaging.domain.message.repository;

import com.chatapp.conversation.domain.aggregate.Conversation;
import com.chatapp.messaging.domain.message.aggregate.Message;
import com.chatapp.messaging.domain.message.vo.ConversationPublicId;
import com.chatapp.messaging.domain.message.vo.MessageSendState;
import com.chatapp.user.domain.aggregate.User;
import com.chatapp.user.domain.vo.UserPublicId;

import java.util.List;

public interface MessageRepository {

    Message save(Message message, User sender, Conversation conversation);

    int updateMessageSendState(ConversationPublicId conversationPublicId, UserPublicId userPublicId, MessageSendState state);

    List<Message> findMessageToUpdateSendState(ConversationPublicId conversationPublicId, UserPublicId userPublicId);

}

