package com.assessment.service;


import com.assessment.model.Account;

import com.assessment.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyAccountService {

    private AccountRepository accountRepository;

    @Autowired
    public MyAccountService(AccountRepository accountRepository)
    {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Account saveAccount(Account account) {
         return  accountRepository.save(account);
    }

    @Transactional
    public Account updateAccount(long id,Account newAccount) {
            Account selectedAccount = accountRepository.findById(id).orElse(null);
            selectedAccount.setAccountNumber(newAccount.getAccountNumber());
            selectedAccount.setOwnerName(newAccount.getOwnerName());
            selectedAccount.setBalance(newAccount.getBalance());
            return  accountRepository.save(selectedAccount);

    }

    @Transactional
    public Account getAccountByAccountNumber(String accountNumber)
    {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    @Transactional
    public Account getAccountById(long id)
    {
        return accountRepository.findById(id).orElse(null);
    }

    public void deleteAccount(long id) {
          accountRepository.deleteById(id);
    }



}
