package com.chatapp.conversation.application.service;

import com.chatapp.conversation.domain.aggregate.Conversation;
import com.chatapp.conversation.domain.repository.ConversationRepository;
import com.chatapp.conversation.domain.vo.CreateConversation;
import com.chatapp.shared.service.State;
import com.chatapp.user.application.service.UserReaderService;
import com.chatapp.user.domain.aggregate.User;
import com.chatapp.user.domain.vo.UserPublicId;

import java.util.List;
import java.util.Optional;

public class ConversationCreatorService {

    private final ConversationRepository conversationRepository;
    private final UserReaderService userReader;

    public ConversationCreatorService(ConversationRepository conversationRepository, UserReaderService userReader) {
        this.conversationRepository = conversationRepository;
        this.userReader = userReader;
    }


    public State<Conversation, String> create(CreateConversation newConversation, User authenticatedUser) {
        newConversation.getMembers().add(authenticatedUser.getUserPublicId());
        List<User> members = userReader.getUsersByPublicId(newConversation.getMembers());
        List<UserPublicId> membersUuids = members.stream().map(User::getUserPublicId).toList();
        Optional<Conversation> conversationAlreadyPresent = conversationRepository.getConversationByUserPublicIds(membersUuids);
        State<Conversation, String> stateResult;
        if (conversationAlreadyPresent.isEmpty()) {
            Conversation newConversationSaved = conversationRepository.save(newConversation, members);
            stateResult = State.<Conversation, String>builder().forSuccess(newConversationSaved);
        } else {
            stateResult = State.<Conversation, String>builder().forError("This conversation already exists");
        }
        return stateResult;
    }
}
