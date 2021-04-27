package com.fetchreward.point.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long TransactionId;

    private String payer;
    private Integer points;

    public Integer getPoints() {
        return points;
    }
    public Transaction() {

    }
    public Transaction(String payer, Integer points) {
        this.payer = payer;
        this.points = points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    private LocalDateTime timestamp;

    public Long getTransactionId() {
        return TransactionId;
    }

    public void setTransactionId(Long transactionId) {
        TransactionId = transactionId;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
