package com.ust.expense.client;

import com.ust.expense.controller.Budget;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "budget-service", url = "http://localhost:9999")  // Replace with your actual service name and URL
public interface BudgetClient {

    @GetMapping("/budgets/email/{email}")
    List<Budget> getBudgetByEmail(@PathVariable("email") String email);


}
