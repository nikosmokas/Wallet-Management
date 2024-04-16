package com.walletmanagement.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "transaction", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private Float amount;


    @Column(name = "type")
    private String type;

    @Column(name = "wallet_id")
    private Long walletId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "transaction_date")
    private Date transactionDate;
    

    public Transaction() {
        
    }

    public Transaction(Float amount, String type, Long walletId, Date transactionDate) {
        this.amount = amount;
        this.type = type;
        this.walletId = walletId;
        this.transactionDate = transactionDate;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
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




    public Date getTransactionDate() {
        return transactionDate;
    }




    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    

    

    
}
