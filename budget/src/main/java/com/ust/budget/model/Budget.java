package com.ust.budget.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
@Table(name= "budgetservice")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;  // Foreign key to associate with a user
    private String category;
    private double amount;
    private LocalDate startDate;
    private LocalDate endDate;

    // Constructors, Getters, and Setters

}
