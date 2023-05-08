package com.example.dataanalyticrestfulapi.service.ServiceImpl;

import com.example.dataanalyticrestfulapi.model.request.TransactionRequest;
import com.example.dataanalyticrestfulapi.model.response.TransactionResponse;
import com.example.dataanalyticrestfulapi.repository.TransactionAccountRepo;
import com.example.dataanalyticrestfulapi.repository.TransactionRepo;
import com.example.dataanalyticrestfulapi.service.TransactionsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionsService {
    @Autowired
    TransactionRepo transactionRepo;
    @Autowired
    TransactionAccountRepo transactionAccountRepo;

    @Override
    public PageInfo<Transaction> getAllTransaction(int page, int limit) {
        PageHelper.startPage(page,limit);
        return new PageInfo<>(transactionRepo.getAllTransaction());
    }

    @Override
    public int createTransaction(TransactionRequest transaction) {
        return transactionRepo.createTransaction(transaction);
    }

    @Override
    public int updateTransaction(TransactionRequest transactionRequest, int id) {
        return transactionRepo.updateTransaction(transactionRequest,id);
    }

    @Override
    public int deleteTransaction(int id) {
       return transactionRepo.deleteTransaction(id);
    }

    @Override
    public PageInfo<TransactionResponse> getAllTransactionaccount(int page, int limit) {
        PageHelper.startPage(page,limit);
        return new PageInfo<>(transactionRepo.getAllTransactionAccount());
    }
}
