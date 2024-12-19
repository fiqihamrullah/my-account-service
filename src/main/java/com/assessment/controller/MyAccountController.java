package com.assessment.controller;


import com.assessment.dto.AnimeQouteResponse;
import com.assessment.model.Account;
import com.assessment.service.MyAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
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
        log.info("Request : {}", account);
        Account _account = myAccountService.saveAccount(account);
        log.info("Response : {}", new ResponseEntity<>(_account, HttpStatus.CREATED));
        return new ResponseEntity<>(_account, HttpStatus.CREATED);
    }

    @PutMapping("/account/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable("id") long id, @RequestBody Account newAccount) {
        log.info("Request : {} -- {}",id, newAccount);
        Account _account = myAccountService.updateAccount(id,newAccount);
        log.info("Response : {}", new ResponseEntity<>(_account, HttpStatus.OK));
        return new ResponseEntity<>(_account, HttpStatus.OK);
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable("id") long id) {
        log.info("Request :Id {}", id);
        Account _account = myAccountService.getAccountById(id);
        log.info("Response : {}", new ResponseEntity<>(_account, HttpStatus.OK));
        return new ResponseEntity<>(_account, HttpStatus.OK);
    }


    @DeleteMapping("/account/{id}")
    public ResponseEntity<HttpStatus> deleteAccount(@PathVariable("id") long id) {
        log.info("Request : Id {}", id);
        myAccountService.deleteAccount(id);
        log.info("Response : None");
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @GetMapping("/account")
    public ResponseEntity<Map<String,Object>> getAllAccount(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        log.info("Request : Page {} Size {} ", page,size);
        log.info("Response : {}", new ResponseEntity<>(myAccountService.getAllAccounts(page,size), HttpStatus.OK));
        return new ResponseEntity<>(myAccountService.getAllAccounts(page,size), HttpStatus.OK);
    }

    @GetMapping("/animeqoutes")
    public AnimeQouteResponse getAnimeQoutes( ) {
        log.info("Request : None");
        AnimeQouteResponse animeQouteResponse =  myAccountService.getQoutesfromAnimeChan();
        log.info("Response :  {}", animeQouteResponse);
        return  animeQouteResponse;
    }


}
