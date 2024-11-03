package old.user.application.service;

import old.conversation.domain.vo.ConversationPublicId;
import old.user.domain.aggregate.User;
import old.user.domain.repository.UserRepository;
import old.user.domain.vo.UserEmail;
import old.user.domain.vo.UserPublicId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class UserReaderService {

    private final UserRepository userRepository;

    public UserReaderService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getByEmail(UserEmail email) {
        return userRepository.getOneByEmail(email);
    }

    public List<User> getUsersByPublicId(Set<UserPublicId> publicIds) {
        return userRepository.getByPublicIds(publicIds);
    }

    public Page<User> search(Pageable pageable, String query) {
        return userRepository.search(pageable, query);
    }

    public Optional<User> getByPublicId(UserPublicId publicId) {
        return userRepository.getOneByPublicId(publicId);
    }

    public List<User> findUsersToNotify(ConversationPublicId conversationPublicId, UserPublicId readerPublicId) {
        return userRepository.getRecipientByConversationIdExcludingReader(conversationPublicId, readerPublicId);
    }
}
