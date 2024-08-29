package com.ust.expense.client;

import com.ust.expense.controller.Budget;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "budget-service", url = "http://localhost:8888")  // Replace with your actual service name and URL
public interface BudgetClient {

    @GetMapping("/budgets/category/{category}")
    Budget getBudgetByCategory(@PathVariable("category") String category);


}
