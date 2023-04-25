package com.cursework.WebArtSell.Models;

public class TransactionChartData {
    private String date;
    private Double sum;

    public TransactionChartData(String date, Double sum) {
        this.date = date;
        this.sum = sum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }
}
