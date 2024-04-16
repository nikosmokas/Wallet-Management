package com.walletmanagement.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


@Entity
@Table(name = "wallet", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(name = "wallet_name")
    private String walletName;

    @Column(name = "currency")
    private String currency;

    @Column(name = "amount")
    private Float amount;


    @Column(name = "notes")
    private String notes;


    @Column(name = "user_id", nullable = false)
    private Long userId;


    public Wallet() {
        
    }

    

    public Wallet(String walletName, String currency, Float amount, String notes, Long userId) {
        this.walletName = walletName;
        this.currency = currency;
        this.amount = amount;
        this.notes = notes;
        this.userId = userId;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
