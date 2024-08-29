package com.ust.budget.repository;


import com.ust.budget.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget,Long> {
    Optional<Budget> findByCategory(String category);
}











//-----------------------------------------------------

//
//import java.util.List;
//
//@Repository
//public interface BudgetRepository extends JpaRepository<Budget, Long> {
//    List<Budget> findByCategoryAndUserId(String category, String userId);
//    List<Budget> findByUserId(String userId);
//}
