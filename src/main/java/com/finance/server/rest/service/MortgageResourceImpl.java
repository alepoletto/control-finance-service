package com.finance.server.rest.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finance.rserver.rest.output.City;
import com.finance.rserver.rest.output.Deal;
import com.finance.rserver.rest.output.DealOutput;
import com.finance.server.calc.MortgageCalculator;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@RestController
@CrossOrigin
public class MortgageResourceImpl  {

    private final AtomicLong counter = new AtomicLong();
    
    @Autowired
    private MortgageCalculator calculator;



    @RequestMapping("/deal")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public DealOutput fetchDeal(@RequestParam(value="city", defaultValue="vancouver") String city, @RequestParam(value="propertyValue", defaultValue="100000") String propertyValue,
            @RequestParam(value="downPayment", defaultValue="20") String downPayment, @RequestParam(value="score", defaultValue="score") String score) {

        City cityObject = findCityDetails(city);
        List<Deal> deals = getDeals(propertyValue, downPayment);

        DealOutput output = createResponseObject(cityObject, deals);
        return output;
    }



    private List<Deal> getDeals(String propertyValue, String downPayment) {
        List<Deal> deals = new ArrayList<>();
        deals.add(getDeal("5/1 ARM",5,propertyValue, downPayment));
        deals.add(getDeal("15 YEARS FIXED",15,propertyValue, downPayment));
        deals.add(getDeal("30 YEARS FIXED",30,propertyValue, downPayment));
        return deals;
    }



    private Deal getDeal(String title, int year, String propertyValue, String downPayment) {
        Integer dowPaymentInteger = Integer.valueOf(downPayment);
        BigDecimal propertyValueBigDecimal = new BigDecimal(propertyValue);
        BigDecimal total = calculator.getTotalPayment(year, propertyValueBigDecimal, dowPaymentInteger);
        BigDecimal dtotal = calculator.getTotalDebt(propertyValueBigDecimal, dowPaymentInteger);
        BigDecimal monthlyPayment = calculator.getMonthlyPayment(year, total);
        List<Integer> monthlyDebts = calculator.getDebtByMounth(monthlyPayment, total);
        Deal deal = new Deal();
        deal.setMonthlyDebts(monthlyDebts);
        deal.setTitle(title);
        deal.setmPayment(monthlyPayment);
        deal.setRate(new BigDecimal(4.5));
        deal.setTotal(total);
        deal.setdPayment(dtotal);
        return deal;
    }



    private DealOutput createResponseObject(City cityObject, List<Deal> deals) {
        DealOutput output = new DealOutput();
        output.setId(counter.incrementAndGet());
        output.setCity(cityObject);
        output.setDeals(deals);
        return output;
    }



    private City findCityDetails(String city) {
        try {
            HttpResponse<City> response = Unirest.get("http://api.openweathermap.org/data/2.5/weather?&appid=db9b7930c68c3067172e42d0a07e6d9a")
                    .queryString("q", city).asObject(City.class);
            City cityObject = response.getBody();
            return cityObject;

        } catch (UnirestException e) {
            throw new RuntimeException("Error on fetching the city ",e);
        }

    }
    
    public void setCalculator(MortgageCalculator calculator) {
        this.calculator = calculator;
    }

}
