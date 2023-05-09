package com.example.dataanalyticrestfulapi.service;

import com.example.dataanalyticrestfulapi.model.Transaction;
import com.example.dataanalyticrestfulapi.model.request.TransactionRequest;
import com.example.dataanalyticrestfulapi.model.response.TransactionResponse;
import com.github.pagehelper.PageInfo;

public interface TransactionsService {
    PageInfo<Transaction> getAllTransaction(int page, int limit);
    int createTransaction(TransactionRequest transaction);
    int updateTransaction(TransactionRequest transactionRequest, int id);
    int deleteTransaction(int id);
    PageInfo<TransactionResponse> getAllTransactionaccount(int page, int limit);
}
