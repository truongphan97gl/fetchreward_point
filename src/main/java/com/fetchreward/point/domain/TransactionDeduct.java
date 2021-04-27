package com.fetchreward.point.domain;

public class TransactionDeduct {
    private String payer;
    private Integer points;

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public Integer getPoints() {
        return points;
    }
    public TransactionDeduct(){}
    public TransactionDeduct(String payer, Integer points) {
        this.payer = payer;
        this.points = points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
