package com.example.ebankingbackend.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DiscriminatorValue("CA")
@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class CurrentAcount extends BankAccount{
    private double overDraft;
}
