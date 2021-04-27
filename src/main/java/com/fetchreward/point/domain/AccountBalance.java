package com.fetchreward.point.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class AccountBalance {



    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    private String payer;
    private Integer balance;
    public AccountBalance() {

    }
    public AccountBalance(String payer, Integer balance) {
        this.payer = payer;
        this.balance = balance;
    }


    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
