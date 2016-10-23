package com.finance.server.calc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MortgageCalculator {
    
    private BigDecimal interestRate = new BigDecimal("0.45");

    public BigDecimal getTotalPayment(int years, BigDecimal propertyValue, int downPaymentPorcentage) {
         BigDecimal monthlyRate = interestRate.divide(new BigDecimal(12));
         BigDecimal debt = getTotalDebt(propertyValue, downPaymentPorcentage);
         int termInMonths = years * 12;
         Double total  = (debt.doubleValue()  * monthlyRate.doubleValue())* termInMonths;
         return new BigDecimal(total).setScale(2,BigDecimal.ROUND_UP).abs();    
    }
    
    public BigDecimal getTotalDebt(BigDecimal propertyValue, int downPaymentPorcentage) {
        double percentage = downPaymentPorcentage /100.0;
        return propertyValue.subtract(propertyValue.multiply(new BigDecimal(percentage)));
    }
    
    public BigDecimal getMonthlyPayment(int years, BigDecimal totalValue) {
        BigDecimal monthlyRate = interestRate.divide(new BigDecimal(12));
        int termInMonths = years * 12;
        Double monthlyPayment  = (totalValue.doubleValue() * monthlyRate.doubleValue()) / (1-Math.pow(monthlyRate.doubleValue()+1, -termInMonths ));
        return new BigDecimal(monthlyPayment).setScale(2,BigDecimal.ROUND_UP).abs();    
   }
    
    
    
    
    public List<Integer> getDebtByMounth(BigDecimal monthlyPayment, BigDecimal totalValue) {
       BigDecimal debt = totalValue;
       List<Integer> debtsByMounth = new ArrayList<>();
       while(debt.intValue() > 0){
           debtsByMounth.add(debt.intValue());
           debt = debt.subtract(monthlyPayment);
       }
       return debtsByMounth;
   }
    


}
