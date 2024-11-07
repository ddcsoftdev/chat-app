package old.conversation.application.service;

import old.conversation.domain.aggregate.Conversation;
import old.conversation.domain.repository.ConversationRepository;
import old.message.application.service.MessageChangeNotifier;
import old.conversation.domain.vo.CreateConversation;
import old.message.domain.repository.MessageRepository;
import old.conversation.domain.vo.ConversationPublicId;
import old.shared.service.State;
import old.user.application.service.UserApplicationService;
import old.user.application.service.UserReaderService;
import old.user.domain.aggregate.User;
import old.user.domain.repository.UserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ConversationManagementService {

    private final ConversationCreatorService conversationCreator;
    private final ConversationReaderService conversationReader;
    private final ConversationDeleterService conversationDeleter;
    private final UserApplicationService usersApplicationService;
    private final ConversationViewedService conversationViewed;

    public ConversationManagementService(ConversationRepository conversationRepository,
                                         UserRepository userRepository,
                                         MessageChangeNotifier messageChangeNotifier,
                                         MessageRepository messageRepository,
                                         UserApplicationService usersApplicationService) {
        UserReaderService userReader = new UserReaderService(userRepository);
        this.conversationCreator = new ConversationCreatorService(conversationRepository, userReader);
        this.conversationReader = new ConversationReaderService(conversationRepository);
        this.conversationDeleter = new ConversationDeleterService(conversationRepository, messageChangeNotifier);
        this.usersApplicationService = usersApplicationService;
        this.conversationViewed = new ConversationViewedService(messageRepository, messageChangeNotifier, userReader);
    }

    @Transactional
    public State<Conversation, String> create(CreateConversation conversation) {
        User authenticatedUser = usersApplicationService.getAuthenticatedUser();
        return conversationCreator.create(conversation, authenticatedUser);
    }

    @Transactional(readOnly = true)
    public List<Conversation> getAllByConnectedUser(Pageable pageable) {
        User authenticatedUser = usersApplicationService.getAuthenticatedUser();
        return this.conversationReader.getAllByUserPublicID(authenticatedUser.getUserPublicId(), pageable)
                .stream().toList();
    }

    @Transactional
    public State<ConversationPublicId, String> delete(ConversationPublicId conversationPublicId) {
        User authenticatedUser = usersApplicationService.getAuthenticatedUser();
        return this.conversationDeleter.deleteById(conversationPublicId, authenticatedUser);
    }

    @Transactional(readOnly = true)
    public Optional<Conversation> getOneByConversationId(ConversationPublicId conversationPublicId) {
        User authenticatedUser = usersApplicationService.getAuthenticatedUser();
        return this.conversationReader.getOneByPublicIdAndUserId(conversationPublicId, authenticatedUser.getUserPublicId());
    }

    @Transactional
    public State<Integer, String> markConversationAsRead(ConversationPublicId conversationPublicId) {
        User authenticatedUser = usersApplicationService.getAuthenticatedUser();
        return conversationViewed.markAsRead(conversationPublicId, authenticatedUser.getUserPublicId());
    }
}