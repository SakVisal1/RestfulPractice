package com.example.dataanalyticrestfulapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Transaction {
    private  int id;
    private int senderAccountId;
    private int receiveAccountId;
    private float amount;
    private String remark;
    private Date transferAt;
    private User user;
}
