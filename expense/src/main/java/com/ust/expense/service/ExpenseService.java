package com.ust.expense.service;

import com.ust.expense.client.BudgetClient;
import com.ust.expense.controller.Budget;
import com.ust.expense.model.Expense;
import com.ust.expense.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private BudgetClient budgetClient;



    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public Expense updateExpense(Long id, Expense updatedExpense) {
        if (expenseRepository.existsById(id)) {
            updatedExpense.setExp_id(id);
            return expenseRepository.save(updatedExpense);
        }
        return null; // Handle this case as appropriate
    }


    public List<Expense> getBudgetByEmail(String email)
    {
        return expenseRepository.findAllByEmail(email);
    }



    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public double getTotalExpenses(String email) {
        return expenseRepository.findAllByEmail(email).stream().mapToDouble(Expense::getAmount).sum();
    }

    public List<Expense> getExpensesByCategory(String category) {
        return expenseRepository.findByCategory(category);
    }

    public Optional<Expense> getExpenseById(Long id) {
        return expenseRepository.findById(id);
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    public Map<String, String> getRemainingBudgetAndPercentage(String email) {
        List<Budget> budgets = budgetClient.getBudgetByEmail(email);
        String category;
        Map<String, String> result = new HashMap<>();
        double totalRemainingBudget = 0;

        for (Budget budget : budgets) {
            category = budget.getCategory();
            double totalBudget = budget.getAmount();
            double totalSpent = getTotalExpensesByCategory(email,category);
            double remainingBudget = totalBudget - totalSpent;
            double percentageSpent = (totalSpent / totalBudget) * 100;

//            if (budgets != null) {

//                if(result.containsKey(category + "_remaining_budget")){
//                    result.put(category + "_remaining_budget",result.get(category + "_remaining_budget")+budget.getAmount());
//                }

                result.put(category + "_remaining_budget", String.valueOf(remainingBudget));
                result.put(category + "_percentage_spent", String.valueOf(percentageSpent));
                totalRemainingBudget += remainingBudget;
//            }
        }

        result.put("total_remaining_budget", String.valueOf(totalRemainingBudget));
        return result;
    }


    private double getTotalExpensesByCategory(String email, String category) {
        return expenseRepository.findAllByEmail(email).stream()
                .filter(expense -> expense.getCategory().equals(category))
                .mapToDouble(Expense::getAmount)
                .sum();
    }
}

