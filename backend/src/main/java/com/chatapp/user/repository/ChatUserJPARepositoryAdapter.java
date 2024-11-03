package com.chatapp.user.repository;

import com.chatapp.user.entity.ChatUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("jpa")
public class ChatUserJPARepositoryAdapter implements ChatUserDAO{
    private final ChatUserRepository chatUserRepository;

    public ChatUserJPARepositoryAdapter(ChatUserRepository chatUserRepository) {
        this.chatUserRepository = chatUserRepository;
    }

    @Override
    public List<ChatUser> selectAllUsers() {
        return chatUserRepository.findAll();
    }

    @Override
    public Optional<ChatUser> selectUserById(Long id) {
        return chatUserRepository.findById(id);
    }


    @Override
    public Optional<ChatUser> selectUserByEmail(String email) {
        return chatUserRepository.findUserByEmail(email);
    }

    @Override
    public boolean existsUserWithId(Long id) {
        return chatUserRepository.existsUserById(id);
    }

    @Override
    public boolean existsUserWithEmail(String email) {
        return chatUserRepository.existsUserByEmail(email);
    }

    @Override
    public void insertUser(ChatUser user) {
        chatUserRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        chatUserRepository.deleteById(id);
    }

    @Override
    public void updateUser(ChatUser update) {
        chatUserRepository.save(update);
    }
}
