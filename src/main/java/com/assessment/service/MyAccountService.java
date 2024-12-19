package com.assessment.service;


import com.assessment.dto.AnimeQouteResponse;
import com.assessment.model.Account;

import com.assessment.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MyAccountService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${url.animeqoute}")
    private String urlAnimeQoute;
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
            if (!ObjectUtils.isEmpty(selectedAccount)) {
                selectedAccount.setAccountNumber(newAccount.getAccountNumber());
                selectedAccount.setOwnerName(newAccount.getOwnerName());
                selectedAccount.setBalance(newAccount.getBalance());
                accountRepository.save(selectedAccount);
            }
            return  selectedAccount;


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

    public Map<String, Object> getAllAccounts(int page, int size) {
        List<Account> accountList = new ArrayList<Account>();
        Pageable paging = PageRequest.of(page, size);

        Page<Account> accountListPerPage = accountRepository.findAll(paging);
        accountList = accountListPerPage.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("accountList", accountList);
        response.put("currentPage", accountListPerPage.getNumber());
        response.put("totalItems", accountListPerPage.getTotalElements());
        response.put("totalPages", accountListPerPage.getTotalPages());

        return response;

    }

    public AnimeQouteResponse getQoutesfromAnimeChan() {
        AnimeQouteResponse animRes =  restTemplate.getForEntity(urlAnimeQoute,AnimeQouteResponse.class).getBody();
        return animRes;
    }





}
