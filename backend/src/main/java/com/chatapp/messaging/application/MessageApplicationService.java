package com.chatapp.messaging.application;

import com.chatapp.conversation.application.service.ConversationReaderService;
import com.chatapp.conversation.domain.repository.ConversationRepository;
import com.chatapp.message.domain.aggregate.Message;
import com.chatapp.messaging.domain.message.aggregate.MessageSendNew;
import com.chatapp.messaging.domain.message.repository.MessageRepository;
import com.chatapp.messaging.domain.message.service.MessageChangeNotifier;
import com.chatapp.messaging.domain.message.service.MessageCreator;
import com.chatapp.shared.authentication.application.AuthenticatedUser;
import com.chatapp.shared.service.State;
import com.chatapp.user.application.service.UserReaderService;
import com.chatapp.user.domain.aggregate.User;
import com.chatapp.user.domain.repository.UserRepository;
import com.chatapp.user.domain.vo.UserEmail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MessageApplicationService {

    private final MessageCreator messageCreator;
    private final UserReaderService userReader;

    public MessageApplicationService(MessageRepository messageRepository, UserRepository userRepository,
                                     ConversationRepository conversationRepository, MessageChangeNotifier messageChangeNotifier) {
        ConversationReaderService conversationReader = new ConversationReaderService(conversationRepository);
        this.messageCreator = new MessageCreator(messageRepository, messageChangeNotifier, conversationReader);
        this.userReader = new UserReaderService(userRepository);
    }

    @Transactional
    public State<Message, String> send(MessageSendNew messageSendNew) {
        State<Message, String> creationState;
        Optional<User> connectedUser = this.userReader.getByEmail(new UserEmail(AuthenticatedUser.username().username()));
        if(connectedUser.isPresent()) {
            creationState = this.messageCreator.create(messageSendNew, connectedUser.get());
        } else {
            creationState = State.<Message, String>builder()
                    .forError(String.format("Error retrieving user information inside the DB : %s", AuthenticatedUser.username().username()));
        }
        return creationState;
    }
}
