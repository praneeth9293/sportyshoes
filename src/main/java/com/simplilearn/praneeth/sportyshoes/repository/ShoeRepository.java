package com.simplilearn.praneeth.sportyshoes.repository;

import com.simplilearn.praneeth.sportyshoes.model.Shoe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoeRepository extends JpaRepository<Shoe, Long> {

    Optional<Shoe> findById(Long id);

    List<Shoe> findByCategory(String category);
}
