package com.simplilearn.praneeth.sportyshoes.service;

import com.simplilearn.praneeth.sportyshoes.model.Shoe;
import com.simplilearn.praneeth.sportyshoes.repository.ShoeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ShoeService {

    private final ShoeRepository repository;

    public ShoeService(ShoeRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Shoe> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Shoe> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Shoe> findByCategory(String category) {
        return repository.findByCategory(category);
    }

    @Transactional
    public List<Shoe> saveAll(List<Shoe> shoes) {
        return repository.saveAll(shoes);
    }
}
