package com.ust.budget.service;


import com.ust.budget.entity.Budget;
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



//-----------------------------------------------------------------------------
//package com.example.budgetservice.service;
//
//import com.example.budgetservice.model.Budget;
//import com.example.budgetservice.repository.BudgetRepository;
//import com.example.budgetservice.util.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class BudgetService {
//
//    @Autowired
//    private BudgetRepository budgetRepository;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    public Budget createBudget(Budget budget) {
//        String userId = jwtUtil.extractUserId();
//        budget.setUserId(userId);
//        return budgetRepository.save(budget);
//    }
//
//    public Budget updateBudget(Long id, Budget updatedBudget) {
//        String userId = jwtUtil.extractUserId();
//        if (budgetRepository.existsById(id)) {
//            updatedBudget.setId(id);
//            updatedBudget.setUserId(userId);
//            return budgetRepository.save(updatedBudget);
//        }
//        return null; // Handle this case as appropriate
//    }
//
//    public List<Budget> getBudgetsByUserId() {
//        String userId = jwtUtil.extractUserId();
//        return budgetRepository.findByUserId(userId);
//    }
//
//    public List<Budget> getBudgetsByCategoryAndUserId(String category) {
//        String userId = jwtUtil.extractUserId();
//        return budgetRepository.findByCategoryAndUserId(category, userId);
//    }
//
//    public void deleteBudget(Long id) {
//        budgetRepository.deleteById(id);
//    }
//
//    // Admin-specific methods
//    public List<Budget> getAllBudgets() {
//        return budgetRepository.findAll();
//    }
//}


