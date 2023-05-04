package com.example.ebankingbackend.dtos;

import com.example.ebankingbackend.entities.Customer;
import com.example.ebankingbackend.enums.AcountStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
public class SavingBankAccountDTO extends BankAccountDTO {
    private String id;
    private double balance;
    private Date createdAt;
    private AcountStatus acountStatus;
    private CustomerDTO customerDTO;
    private double interestRate;
}