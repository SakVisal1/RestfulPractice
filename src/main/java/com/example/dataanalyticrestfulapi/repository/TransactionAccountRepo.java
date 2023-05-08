package com.example.dataanalyticrestfulapi.repository;

import com.example.dataanalyticrestfulapi.model.Account;
import com.example.dataanalyticrestfulapi.model.AccountType;
import com.example.dataanalyticrestfulapi.model.User;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TransactionAccountRepo {
    @Results({
            @Result(column = "id",property = "id"),
            @Result(property = "accountNumber",column = "account_no"),
            @Result(property = "accountName",column = "account_name"),
            @Result(property = "transferLimit",column = "transfer_limit"),
            @Result(property = "phoneNumber",column = "phonenumber"),
            @Result(property = "user",column = "id",one = @One(select = "getDataUser")),
            @Result(property = "accountType",column = "account_type",one = @One(select = "getAccountType"))
    })
    @Select("SELECT * FROM account_tb WHERE id=#{id}")
    List<Account> getTransactionAccount();

    @Select("SELECT * FROM user_account_tb a inner join users_tb ON a.user_id=users_tb.id WHERE account_id=#{id}")
    User getDataUser();
    @Result(column = "name",property = "accountName")
    @Select("SELECT * FROM accounttype_tb WHERE id=#{accountType}")
    AccountType getAccountType();
}
