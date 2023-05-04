package com.example.ebankingbackend.services;

import com.example.ebankingbackend.entities.*;
import com.example.ebankingbackend.enums.AcountStatus;
import com.example.ebankingbackend.enums.OperationType;
import com.example.ebankingbackend.repositories.AccountOperationRepository;
import com.example.ebankingbackend.repositories.BankAccountRepository;
import com.example.ebankingbackend.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@Transactional
public class BankService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountOperationRepository accountOperationRepository;
    @Autowired
    private BankAccountRepository bankAccountRepository;

    public void save(){
        Stream.of("Hassan", "Yassine", "Aicha")
                .forEach(name->{
                    Customer customer = new Customer();
                    customer.setName(name);
                    customer.setEmail(name+"@gmail.com");
                    customerRepository.save(customer);
                });
        customerRepository.findAll().forEach(cust -> {
            CurrentAcount currentAcount = new CurrentAcount();
            currentAcount.setId(UUID.randomUUID().toString());
            currentAcount.setBalance(Math.random()*90000);
            currentAcount.setCreatedAt(new Date());
            currentAcount.setAcountStatus(AcountStatus.CREATED);
            currentAcount.setCustomer(cust);
            currentAcount.setOverDraft(9000);
            bankAccountRepository.save(currentAcount);

            SavingAcount savingAcount = new SavingAcount();
            savingAcount.setId(UUID.randomUUID().toString());
            savingAcount.setBalance(Math.random()*90000);
            savingAcount.setCreatedAt(new Date());
            savingAcount.setAcountStatus(AcountStatus.CREATED);
            savingAcount.setCustomer(cust);
            savingAcount.setInterestRate(5.5);
            bankAccountRepository.save(savingAcount);
        });

        bankAccountRepository.findAll().forEach(acc->{
            for (int i=0; i<10; i++){
                AccountOperation accountOperation = new AccountOperation();
                accountOperation.setBankAccount(acc);
                accountOperation.setOperationDate(new Date());
                accountOperation.setAmount(Math.random()*9000);
                accountOperation.setOperationType(Math.random()>0.5? OperationType.DEBIT:OperationType.CREDIT);
                accountOperationRepository.save(accountOperation);
            }
        });
    }
    public void consulter(){
        BankAccount bankAccount = bankAccountRepository.findAll().get(0);
        if (bankAccount!=null){
            System.out.printf("*********************************\n");
            System.out.printf(bankAccount.getId()+"\n");
            System.out.printf(String.valueOf(bankAccount.getBalance())+"\n");
            System.out.printf(String.valueOf(bankAccount.getAcountStatus())+"\n");
            System.out.printf(String.valueOf(bankAccount.getCreatedAt())+"\n");
            System.out.printf(bankAccount.getCustomer().getName()+"\n");
            System.out.printf(bankAccount.getClass().getSimpleName()+"\n");
            if(bankAccount instanceof CurrentAcount){
                System.out.printf("Over Draft: "+((CurrentAcount)bankAccount).getOverDraft()+"\n");
            }else {
                System.out.printf("Interest Rate: "+((SavingAcount)bankAccount).getInterestRate()+"\n");
            }
            System.out.printf("============= Operations ===============\n");
            bankAccount.getAccountOperations().forEach(op->{
                System.out.printf("Type: "+String.valueOf(op.getOperationType())
                        +", Amount: "+String.valueOf(op.getAmount())
                        +", Date: "+String.valueOf(op.getOperationDate())+"\n");
            });
        }
    }
}