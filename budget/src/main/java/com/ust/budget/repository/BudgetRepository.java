package com.ust.budget.repository;


import com.ust.budget.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget,Long> {
    Optional<Budget> findByCategory(String category);

    @Query("SELECT b FROM Budget b WHERE b.email = :email")
    List<Budget> findAllByEmail(String email);
}






