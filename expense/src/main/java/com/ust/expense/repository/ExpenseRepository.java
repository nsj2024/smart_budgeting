package com.ust.expense.repository;

import com.ust.expense.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByCategory(String category);

    @Query("SELECT b FROM Expense b WHERE b.email = :email")
    List<Expense> findAllByEmail(String email);
}
