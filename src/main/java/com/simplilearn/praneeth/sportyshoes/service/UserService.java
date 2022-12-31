package com.simplilearn.praneeth.sportyshoes.service;

import com.simplilearn.praneeth.sportyshoes.model.User;
import com.simplilearn.praneeth.sportyshoes.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Transactional
    public User save(User user) {
        return repository.save(user);
    }
}
