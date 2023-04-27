package com.example.dataanalyticrestfulapi.controller;

import com.example.dataanalyticrestfulapi.mapper.AutoAccountMapper;
import com.example.dataanalyticrestfulapi.model.Account;
import com.example.dataanalyticrestfulapi.model.User;
import com.example.dataanalyticrestfulapi.model.response.AccountResponse;
import com.example.dataanalyticrestfulapi.service.AccountService;
import com.example.dataanalyticrestfulapi.service.UserService;
import com.example.dataanalyticrestfulapi.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
   final private AccountService accountService;
   final private AutoAccountMapper autoAccountMapper;
    AccountController(AccountService accountService,AutoAccountMapper autoAccountMapper){
        this.accountService = accountService;
        this.autoAccountMapper = autoAccountMapper;
    }

    @GetMapping("/allAccounts")
    public Response<List<AccountResponse>> getAllAccount(){
        try {
            List<Account> allAccount = accountService.getAllAccount();
            List<AccountResponse> accountResponses = autoAccountMapper.mapToAccountResponse(allAccount);
            return Response.<List<AccountResponse>>ok().setPayload(accountResponses).setMessage("Successfully retrieved all account information") ;

        }catch (Exception exception){
            System.out.println("Something wrong : " + exception.getMessage());
            return Response.<List<AccountResponse>>exception().setMessage("Exception occurs! failed to retrieved account information");
        }
    }
}
