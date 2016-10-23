package com.finance.server.calc.test;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.finance.server.calc.MortgageCalculator;

public class MortgageCalculatorTest {
    
    private MortgageCalculator calc;
    
    @Before
    public void setup(){
        calc = new MortgageCalculator();
    }
    
    @Test
    public void testGetTotalPayment(){
        assertEquals(new BigDecimal("180000.00"), calc.getTotalPayment(5, new BigDecimal(100000),20));
    }
    
    @Test
    public void testGetMonthlyPayment(){
        assertEquals(new BigDecimal("7582.81"), calc.getMonthlyPayment(5, new BigDecimal(180000)));
    }

}
