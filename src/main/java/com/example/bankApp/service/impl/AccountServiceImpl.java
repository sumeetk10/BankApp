package com.example.bankApp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.bankApp.dto.AccountDto;
import com.example.bankApp.entity.Account;
import com.example.bankApp.exception.AccountException;
import com.example.bankApp.mapper.AccountMapper;
import com.example.bankApp.repository.AccountRepository;
import com.example.bankApp.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{

    private AccountRepository accountRepository;

    // since only single constructor, spring will automatically inject constructor
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }


    @Override
    public AccountDto getAccountById(Long id) {
        
        Account account = accountRepository
                                .findById(id)
                                .orElseThrow(() -> new AccountException("Account does not exist"));
        
        
        return AccountMapper.mapToAccountDto(account);

    }


    @Override
    public AccountDto desposit(Long id, double amount) {
        Account account = accountRepository
                                .findById(id)
                                .orElseThrow(() -> new AccountException("Account does not exist"));

        double total = account.getBalance() + amount;
        account.setBalance(total);

        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);

    }


    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository
                                .findById(id)
                                .orElseThrow(() -> new AccountException("Account does not exist"));

        if(account.getBalance() < amount){
            throw new RuntimeException("Insufficient Balance");
        }
        
        double total = account.getBalance() - amount;
        account.setBalance(total);

        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);

    }


    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());

    }


    @Override
    public void deleteAccount(Long id) {

        accountRepository
                        .findById(id)
                        .orElseThrow(() -> new AccountException("Account does not exist"));

        accountRepository.deleteById(id);
    }

}
