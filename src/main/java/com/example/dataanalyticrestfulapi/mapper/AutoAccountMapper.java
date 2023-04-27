package com.example.dataanalyticrestfulapi.mapper;

import com.example.dataanalyticrestfulapi.model.Account;
import com.example.dataanalyticrestfulapi.model.response.AccountResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AutoAccountMapper {

    //account => accountResponse
    List<AccountResponse> mapToAccountResponse(List<Account> accounts);
    // account-response -> account
    List<Account> mapToAccount(List<AccountResponse> responses);
}
