package com.example.dataanalyticrestfulapi.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
    private int senderAccountId;
    private int receiveAccountId;
    private float amount;
    private String remark;
    private Date transferAt;
}
