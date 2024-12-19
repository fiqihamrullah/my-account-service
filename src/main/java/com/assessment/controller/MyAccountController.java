package com.assessment.controller;


import com.assessment.dto.AnimeQouteResponse;
import com.assessment.model.Account;
import com.assessment.service.MyAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
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
        try {
            log.info("Request : {}", account);
            Account _account = myAccountService.saveAccount(account);
            log.info("Response : {}", new ResponseEntity<>(_account, HttpStatus.CREATED));
            return new ResponseEntity<>(_account, HttpStatus.CREATED);
        } catch (Exception e) {
           return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/account/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable("id") long id, @RequestBody Account newAccount) {
        log.info("Request : {} -- {}",id, newAccount);
        Account _account = myAccountService.updateAccount(id,newAccount);
        if (!ObjectUtils.isEmpty(_account)) {
            log.info("Response : {}", new ResponseEntity<>(_account, HttpStatus.OK));
            return new ResponseEntity<>(_account, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable("id") long id) {
        log.info("Request :Id {}", id);
        Account _account = myAccountService.getAccountById(id);
        if (!ObjectUtils.isEmpty(_account)) {
            log.info("Response : {}", new ResponseEntity<>(_account, HttpStatus.OK));
            return new ResponseEntity<>(_account, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/account/{id}")
    public ResponseEntity<HttpStatus> deleteAccount(@PathVariable("id") long id) {
        try {
            log.info("Request : Id {}", id);
            myAccountService.deleteAccount(id);
            log.info("Response : None");
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/account")
    public ResponseEntity<Map<String,Object>> getAllAccount(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        try {
            log.info("Request : Page {} Size {} ", page,size);
            log.info("Response : {}", new ResponseEntity<>(myAccountService.getAllAccounts(page,size), HttpStatus.OK));
            return new ResponseEntity<>(myAccountService.getAllAccounts(page,size), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/animeqoutes")
    public AnimeQouteResponse getAnimeQoutes( ) {
        try {
            log.info("Request : None");
            AnimeQouteResponse animeQouteResponse =  myAccountService.getQoutesfromAnimeChan();
            log.info("Response :  {}", animeQouteResponse);
             return  animeQouteResponse;
        } catch (Exception e) {
             return AnimeQouteResponse.builder().build();
        }
    }


}
