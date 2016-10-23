package com.finance.rserver.rest.output;

import java.math.BigDecimal;
import java.util.List;

public class Deal {
    
    private String title;
    
    private BigDecimal mPayment;
    
    private BigDecimal dPayment;
    
    private BigDecimal total;
    
    private BigDecimal rate;
    
    private List<Integer> monthlyDebts;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getmPayment() {
        return mPayment;
    }

    public void setmPayment(BigDecimal mPayment) {
        this.mPayment = mPayment;
    }

    public BigDecimal getdPayment() {
        return dPayment;
    }

    public void setdPayment(BigDecimal dPayment) {
        this.dPayment = dPayment;
    }
    
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    
    public BigDecimal getTotal() {
        return total;
    }


    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public List<Integer> getMonthlyDebts() {
        return monthlyDebts;
    }

    public void setMonthlyDebts(List<Integer> monthlyDebts) {
        this.monthlyDebts = monthlyDebts;
    }
    
    
    
    
}
