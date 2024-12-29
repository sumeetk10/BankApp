package com.example.bankApp.mapper;

import com.example.bankApp.dto.AccountDto;
import com.example.bankApp.entity.Account;

public class AccountMapper {

    public static Account mapToAccount(AccountDto accountDto){
        
        // to convert accountDto to account JPA Entity
        Account account = new Account(
            accountDto.getId(),
            accountDto.getAccountHolderName(),
            accountDto.getBalance()
        );

        return account;
    }


    public static AccountDto mapToAccountDto(Account account){

        // to convert account to accountDto JPA Entity
        AccountDto accountDto = new AccountDto(
            account.getId(),
            account.getAccountHolderName(),
            account.getBalance()
        );

        return accountDto;
    }
}
