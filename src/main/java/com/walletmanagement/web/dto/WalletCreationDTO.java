package com.walletmanagement.web.dto;

public class WalletCreationDTO {
    private String walletName;
    private String currency;
    private Float amount;
    private String notes;
    private Long userId;


    public WalletCreationDTO() {
        
    }

    


    public WalletCreationDTO(String walletName, String currency, Float amount, String notes, Long userId) {
        this.walletName = walletName;
        this.currency = currency;
        this.amount = amount;
        this.notes = notes;
        this.userId = userId;
    }




    public String getWalletName() {
        return walletName;
    }


    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }


    public String getCurrency() {
        return currency;
    }


    public void setCurrency(String currency) {
        this.currency = currency;
    }


    public Float getAmount() {
        return amount;
    }


    public void setAmount(Float amount) {
        this.amount = amount;
    }


    public String getNotes() {
        return notes;
    }


    public void setNotes(String notes) {
        this.notes = notes;
    }




    public Long getUserId() {
        return userId;
    }




    public void setUserId(Long userId) {
        this.userId = userId;
    }

    

    
    
}
