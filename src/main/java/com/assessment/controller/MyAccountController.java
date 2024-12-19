package com.assessment.controller;


import com.assessment.dto.AnimeQouteResponse;
import com.assessment.model.Account;
import com.assessment.service.MyAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class MyAccountController {

    private MyAccountService myAccountService;

    @Autowired
    public MyAccountController(MyAccountService myAccountService)
    {
        this.myAccountService = myAccountService;
    }
    @PostMapping("/account")
    public ResponseEntity<Account> insertAccount(@RequestBody Account account) {
        Account _account = myAccountService.saveAccount(account);
        return new ResponseEntity<>(_account, HttpStatus.CREATED);
    }

    @PutMapping("/account/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable("id") long id, @RequestBody Account newAccount) {
        Account _account = myAccountService.updateAccount(id,newAccount);
        return new ResponseEntity<>(_account, HttpStatus.OK);
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable("id") long id) {
        Account _account = myAccountService.getAccountById(id);
        return new ResponseEntity<>(_account, HttpStatus.OK);
    }


    @DeleteMapping("/account/{id}")
    public ResponseEntity<HttpStatus> deleteAccount(@PathVariable("id") long id) {
        myAccountService.deleteAccount(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @GetMapping("/account")
    public ResponseEntity<Map<String,Object>> getAllAccount(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {

        return new ResponseEntity<>(myAccountService.getAllAccounts(page,size), HttpStatus.OK);
    }

    @GetMapping("/animeqoutes")
    public AnimeQouteResponse getAnimeQoutes( ) {
        return  myAccountService.getQoutesfromAnimeChan();
    }


}
