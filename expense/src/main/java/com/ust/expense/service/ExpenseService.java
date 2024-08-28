package com.ust.expense.service;

import com.ust.expense.model.Budget;
import com.ust.expense.model.Expense;
import com.ust.expense.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String BUDGET_SERVICE_URL = "http://localhost:9000/budgets/category/";

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

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public double getTotalExpenses() {
        return expenseRepository.findAll().stream().mapToDouble(Expense::getAmount).sum();
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

    public Map<String, String> getRemainingBudgetAndPercentage() {
        List<String> categories = List.of("Groceries", "Transportation", "Entertainment"); // Example categories
        Map<String, String> result = new HashMap<>();
        double totalRemainingBudget = 0;

        for (String category : categories) {
            Budget budgets = restTemplate.getForObject(BUDGET_SERVICE_URL + category, Budget.class);
            if (budgets != null) {
                double totalBudget = 0;
                for (Budget budget : budgets) {
                    totalBudget += budget.getAmount();
                }
                double totalSpent = getTotalExpensesByCategory(category);
                double remainingBudget = totalBudget - totalSpent;
                double percentageSpent = (totalSpent / totalBudget) * 100;

                result.put(category + "_remaining_budget", remainingBudget + "");
                result.put(category + "_percentage_spent", percentageSpent + "");
                totalRemainingBudget += remainingBudget;
            }
        }

        result.put("total_remaining_budget", totalRemainingBudget+ "");
        return result;
    }

    private double getTotalExpensesByCategory(String category) {
        return expenseRepository.findByCategory(category).stream().mapToDouble(Expense::getAmount).sum();
    }
}

