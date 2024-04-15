package com.walletmanagement.web.dto;

public class WalletCreationDTO {
    private String walletName;
    private String currency;
    private Long amount;
    private String notes;
    private Long userId;


    public WalletCreationDTO() {
        
    }

    


    public WalletCreationDTO(String walletName, String currency, Long amount, String notes, Long userId) {
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


    public Long getAmount() {
        return amount;
    }


    public void setAmount(Long amount) {
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
