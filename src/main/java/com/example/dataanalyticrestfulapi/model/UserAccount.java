package com.example.dataanalyticrestfulapi.model;

import com.example.dataanalyticrestfulapi.model.Account;
import com.example.dataanalyticrestfulapi.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserAccount {
    private int id ;
    private String username;
    private String gender;
    private String address;
    private List<Account> accounts;
}
