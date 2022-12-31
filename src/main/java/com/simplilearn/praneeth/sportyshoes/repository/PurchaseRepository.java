package com.simplilearn.praneeth.sportyshoes.repository;

import com.simplilearn.praneeth.sportyshoes.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    List<Purchase> findByPurchaseDate(LocalDate date);

    List<Purchase> findByCategory(String category);
}
