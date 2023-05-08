package com.example.dataanalyticrestfulapi.model.response;

import com.example.dataanalyticrestfulapi.model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {

    private  int id;
    private Account senderAccountId;
    private Account receiveAccountId;
    private float amount;
    private String remark;
    private Date transferAt;
}
