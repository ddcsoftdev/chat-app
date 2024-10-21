package com.chatapp.message.domain.repository;

import com.chatapp.conversation.domain.aggregate.Conversation;
import com.chatapp.message.domain.aggregate.Message;
import com.chatapp.conversation.domain.vo.ConversationPublicId;
import com.chatapp.message.domain.vo.MessageSendState;
import com.chatapp.user.domain.aggregate.User;
import com.chatapp.user.domain.vo.UserPublicId;

import java.util.List;

public interface MessageRepository {

    Message save(Message message, User sender, Conversation conversation);

    int updateMessageSendState(ConversationPublicId conversationPublicId, UserPublicId userPublicId, MessageSendState state);

    List<Message> findMessageToUpdateSendState(ConversationPublicId conversationPublicId, UserPublicId userPublicId);

}

