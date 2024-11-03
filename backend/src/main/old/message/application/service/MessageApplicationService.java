package old.message.application.service;

import old.conversation.application.service.ConversationReaderService;
import old.conversation.domain.repository.ConversationRepository;
import old.message.domain.aggregate.Message;
import old.message.domain.vo.MessageNew;
import old.message.domain.repository.MessageRepository;
import old.shared.authentication.application.AuthenticatedUser;
import old.shared.service.State;
import old.user.application.service.UserReaderService;
import old.user.domain.aggregate.User;
import old.user.domain.repository.UserRepository;
import old.user.domain.vo.UserEmail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MessageApplicationService {

    private final MessageCreatorService messageCreator;
    private final UserReaderService userReader;

    public MessageApplicationService(MessageRepository messageRepository, UserRepository userRepository,
                                     ConversationRepository conversationRepository, MessageChangeNotifier messageChangeNotifier) {
        ConversationReaderService conversationReader = new ConversationReaderService(conversationRepository);
        this.messageCreator = new MessageCreatorService(messageRepository, messageChangeNotifier, conversationReader);
        this.userReader = new UserReaderService(userRepository);
    }

    @Transactional
    public State<Message, String> send(MessageNew messageSendNew) {
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
