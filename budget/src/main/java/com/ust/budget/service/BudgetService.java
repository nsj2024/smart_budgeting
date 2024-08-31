package com.ust.budget.service;


import com.ust.budget.model.Budget;
import com.ust.budget.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {

        @Autowired
        private BudgetRepository budgetRepository;

        public  List<Budget> getBudgetByEmail(String email)
        {
            return budgetRepository.findAllByEmail(email);
        }
        // Create new Budget
        public Budget createBudget(Budget budget) {
            return budgetRepository.save(budget);
        }

        //fetch ALL budgets
        public List<Budget> getAllBudgets() {
            return budgetRepository.findAll();
        }


        // Get Budget By ID
        public Optional<Budget> getBudgetById(Long id) {
            return budgetRepository.findById(id);
        }


        // Get Budget By Category
        public Optional<Budget> getBudgetByCategory(String category) {
            LocalDate today = LocalDate.now();
            return budgetRepository.findByCategory(category);
        }


        //Update existing Budget
        public Optional<Budget> updateBudget(Long id, Budget updatedBudget) {
            Optional<Budget> existingBudget = budgetRepository.findById(id);

            if (existingBudget.isPresent()) {
                Budget budget = existingBudget.get();
                budget.setCategory(updatedBudget.getCategory());
                budget.setAmount(updatedBudget.getAmount());
                budget.setStartDate(updatedBudget.getStartDate());
                budget.setEndDate(updatedBudget.getEndDate());
                return Optional.of(budgetRepository.save(budget));
            } else {
                return Optional.empty();
            }
        }


        // Delete Budget
        public boolean deleteBudget(Long id) {
            Optional<Budget> existingBudget = budgetRepository.findById(id);

            if (existingBudget.isPresent()) {
                budgetRepository.delete(existingBudget.get());
                return true;
            } else {
                return false;
            }
        }
}


