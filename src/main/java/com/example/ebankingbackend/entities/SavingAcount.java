package com.example.ebankingbackend.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DiscriminatorValue("SA")
@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class SavingAcount extends BankAccount{
    private double interestRate;
}