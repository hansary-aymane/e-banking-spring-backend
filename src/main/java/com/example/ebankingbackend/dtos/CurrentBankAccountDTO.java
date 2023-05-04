package com.example.ebankingbackend.dtos;

import com.example.ebankingbackend.enums.AcountStatus;
import lombok.Data;

import java.util.Date;

@Data
public class CurrentBankAccountDTO extends BankAccountDTO {
    private String id;
    private double balance;
    private Date createdAt;
    private AcountStatus acountStatus;
    private CustomerDTO customerDTO;
    private double overDraft;
}