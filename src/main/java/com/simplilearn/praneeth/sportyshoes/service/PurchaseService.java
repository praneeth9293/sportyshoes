package com.simplilearn.praneeth.sportyshoes.service;

import com.simplilearn.praneeth.sportyshoes.model.Purchase;
import com.simplilearn.praneeth.sportyshoes.model.Shoe;
import com.simplilearn.praneeth.sportyshoes.repository.PurchaseRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PurchaseService {

    private final PurchaseRepository repository;

    public PurchaseService(PurchaseRepository repository) {
        this.repository = repository;
    }

    public List<Purchase> findAll() {
        return repository.findAll();
    }

    public List<Purchase> findByPurchaseDate(LocalDate date) {
        return repository.findByPurchaseDate(date);
    }

    public List<Purchase> findByCategory(String category) {
        return repository.findByCategory(category);
    }

    public Purchase purchase(Shoe shoe) {
        Purchase purchase = new Purchase();
        purchase.setShoeId(shoe.getId());
        purchase.setPurchaseDate(LocalDate.now());
        purchase.setCategory(shoe.getCategory());
        purchase.setUsername(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        return repository.save(purchase);
    }
}
