package com.example.dataanalyticrestfulapi.controller;

import com.example.dataanalyticrestfulapi.model.Transaction;
import com.example.dataanalyticrestfulapi.model.request.TransactionRequest;
import com.example.dataanalyticrestfulapi.model.response.TransactionResponse;
import com.example.dataanalyticrestfulapi.service.TransactionsService;
import com.example.dataanalyticrestfulapi.utils.Response;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    TransactionsService transactionsService;
    @Autowired
    TransactionController(TransactionsService transactionsService){
        this.transactionsService = transactionsService;
    }

    @GetMapping("")
    Response<PageInfo<Transaction>> getAllTransaction(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int limit){
        try {
            PageInfo<Transaction> transactionPageInfo=transactionsService.getAllTransaction(page,limit);
            return Response.<PageInfo<Transaction>>ok().setPayload(transactionPageInfo).setMessage("Here is your data.");
        }catch (Exception ex){
            System.out.println("Error :"+ex);
            return Response.<PageInfo<Transaction>>exception().setMessage("Error Exception "+ex).setSuccess(false);
        }

    }
    @PostMapping("/")
    Response<Transaction> createTransaction(@RequestBody TransactionRequest transaction){
        try {
            System.out.println(transaction);
            int results= transactionsService.createTransaction(transaction);
            if(results>0){
                Transaction newTransaction=new Transaction().setSenderAccountId(transaction.getSenderAccountId())
                        .setReceiveAccountId(transaction.getReceiveAccountId())
                        .setAmount(transaction.getAmount())
                        .setRemark(transaction.getRemark())
                        .setTransferAt(transaction.getTransferAt());
                return Response.<Transaction>createSuccess().setPayload(newTransaction).setMessage("You're create transaction successfully");
            }else {
                return Response.<Transaction>badRequest().setMessage("Your insert is error").setSuccess(false);
            }

        }catch (Exception ex){
            System.out.println("error : "+ex);
            return Response.<Transaction>exception().setMessage("Error Exception").setSuccess(false);
        }

    }
    @PutMapping("/{id}")
    Response<Transaction> updateTransaction(@RequestBody TransactionRequest transactionRequest,@PathVariable("id") int id){
        try {
            Date date=new Date();
            transactionRequest.setTransferAt(date);
            System.out.println("This is value: "+transactionRequest);
            int results= transactionsService.updateTransaction(transactionRequest,id);
            System.out.println("this is result :"+results);
            if(results>0){
                Transaction transaction= new Transaction().setSenderAccountId(transactionRequest.getSenderAccountId())
                        .setReceiveAccountId(transactionRequest.getReceiveAccountId())
                        .setAmount(transactionRequest.getAmount())
                        .setRemark(transactionRequest.getRemark())
                        .setTransferAt(date);
                return Response.<Transaction>updateSuccess().setPayload(transaction).setMessage("You're Update Successfully");
            }else {
                return Response.<Transaction>badRequest().setMessage("Your are failed update data.").setSuccess(false);
            }
        }catch (Exception ex){
            System.out.println("error : "+ex);
            return Response.<Transaction>exception().setMessage("Error Exception").setSuccess(false);
        }
    }
    @DeleteMapping("/{id}")
    Response<Transaction> deleteTransaction(@PathVariable("id") int id){
        try {
            int results= transactionsService.deleteTransaction(id);
            if(results>0){
                return Response.<Transaction>deleteSuccess().setSuccess(true).setMessage("You're delete successfully");
            }else {
                return Response.<Transaction>badRequest().setMessage("Your are failed delete data.").setSuccess(false);
            }
        }catch (Exception ex){
            System.out.println("error : "+ex);
            return Response.<Transaction>exception().setMessage("Error Exception").setSuccess(false);
        }
    }

    @GetMapping("/all")
    Response<PageInfo<TransactionResponse>> getAllTransactionAccount(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int limit) {
        try {
            PageInfo<TransactionResponse> transactionPageInfo = transactionsService.getAllTransactionaccount(page, limit);
            return Response.<PageInfo<TransactionResponse>>ok().setPayload(transactionPageInfo).setMessage("Here is your Data.");
        } catch (Exception ex) {
            System.out.println("Error :" + ex);
            return Response.<PageInfo<TransactionResponse>>exception().setMessage("Error Exceptions " + ex).setSuccess(false);
        }

    }
}
