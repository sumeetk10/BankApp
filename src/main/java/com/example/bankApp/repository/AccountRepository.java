package com.example.bankApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bankApp.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
