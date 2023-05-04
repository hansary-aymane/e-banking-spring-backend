package com.example.ebankingbackend.mappers;

import com.example.ebankingbackend.dtos.AccountOperationDTO;
import com.example.ebankingbackend.dtos.CurrentBankAccountDTO;
import com.example.ebankingbackend.dtos.CustomerDTO;
import com.example.ebankingbackend.dtos.SavingBankAccountDTO;
import com.example.ebankingbackend.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankAccountMapperImpl {
    public CustomerDTO fromCustomer(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }

    public Customer fromCustomerDTO(CustomerDTO customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    public SavingBankAccountDTO fromSavingBankAccount(SavingAcount savingAcount){
        SavingBankAccountDTO savingBankAccountDTO = new SavingBankAccountDTO();
        BeanUtils.copyProperties(savingAcount, savingBankAccountDTO);
        savingBankAccountDTO.setCustomerDTO(fromCustomer(savingAcount.getCustomer()));
        savingBankAccountDTO.setType(savingAcount.getClass().getSimpleName());
        return savingBankAccountDTO;
    }

    public SavingAcount fromSavingBankAccountDTO(SavingBankAccountDTO savingBankAccountDTO){
        SavingAcount savingAcount = new SavingAcount();
        BeanUtils.copyProperties(savingBankAccountDTO, savingAcount);
        savingAcount.setCustomer(fromCustomerDTO(savingBankAccountDTO.getCustomerDTO()));
        return savingAcount;
    }

    public CurrentBankAccountDTO fromCurrentBankAccount(CurrentAcount currentAcount){
        CurrentBankAccountDTO currentBankAccountDTO = new CurrentBankAccountDTO();
        BeanUtils.copyProperties(currentAcount, currentBankAccountDTO);
        currentBankAccountDTO.setCustomerDTO(fromCustomer(currentAcount.getCustomer()));
        currentBankAccountDTO.setType(currentAcount.getClass().getSimpleName());
        return currentBankAccountDTO;
    }

    public CurrentAcount fromCurrentBankAcountDTO(CurrentBankAccountDTO currentBankAccountDTO){
        CurrentAcount currentAcount = new CurrentAcount();
        BeanUtils.copyProperties(currentBankAccountDTO, currentAcount);
        currentAcount.setCustomer(fromCustomerDTO(currentBankAccountDTO.getCustomerDTO()));
        return currentAcount;
    }

    public AccountOperationDTO fromAccountOperation(AccountOperation accountOperation){
        AccountOperationDTO accountOperationDTO = new AccountOperationDTO();
        BeanUtils.copyProperties(accountOperation, accountOperationDTO);
        return accountOperationDTO;
    }
}