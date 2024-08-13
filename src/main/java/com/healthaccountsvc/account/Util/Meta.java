package com.healthaccountsvc.account.Util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Meta {
    private String transactionID;
    private String status;
    private int statusCode;
    private String timestamp;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String message;

    public Meta(String transactionID, String status, int statusCode){
        this.transactionID = transactionID;
        this.status = status;
        this.statusCode = statusCode;
        this.timestamp = LocalDate.now().toString();
    }

    public Meta(String transactionID, String status, int statusCOde, String message){
        this.transactionID = transactionID;
        this.status = status;
        this.statusCode = statusCOde;
        this.message = message;
        this.timestamp = LocalDate.now().toString();
    }
}
