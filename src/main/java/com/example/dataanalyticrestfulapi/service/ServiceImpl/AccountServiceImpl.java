package com.example.dataanalyticrestfulapi.service.ServiceImpl;

import com.example.dataanalyticrestfulapi.model.Account;
import com.example.dataanalyticrestfulapi.repository.AccountRepo;
import com.example.dataanalyticrestfulapi.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccountServiceImpl implements AccountService {
    final private AccountRepo accountRepo;
    AccountServiceImpl(AccountRepo accountRepo){
        this.accountRepo = accountRepo;
    }
    @Override
    public List<Account> getAllAccount() {
        return accountRepo.getAllAccount();
    }

    @Override
    public int createAccount(Account account) {
        return 0;
    }

    @Override
    public int updateAccount(Account account, int id) {
        return 0;
    }

    @Override
    public Account findAccountByID(int id) {
        return accountRepo.findAccountByID(id);
    }
}
