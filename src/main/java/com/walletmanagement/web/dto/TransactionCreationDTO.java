package com.walletmanagement.web.dto;

import java.util.Date;

public class TransactionCreationDTO {
    private Float amount;
    private String type;
    private Long walletId;
    private Date date;


    public TransactionCreationDTO() {
        
    }

    


    public TransactionCreationDTO(Float amount, String type, Long walletId, Date date) {
        this.amount = amount;
        this.type = type;
        this.walletId = walletId;
        this.date = date;
    }




    public Float getAmount() {
        return amount;
    }


    public void setAmount(Float amount) {
        this.amount = amount;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public Long getWalletId() {
        return walletId;
    }


    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }


    public Date getDate() {
        return date;
    }


    public void setDate(Date date) {
        this.date = date;
    }  
}
