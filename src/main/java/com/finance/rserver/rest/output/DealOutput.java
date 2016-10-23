package com.finance.rserver.rest.output;

import java.util.List;

public class DealOutput {
    
    private Long id;
    
    private City city;
    
    private List<Deal> deals;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Deal> getDeals() {
        return deals;
    }

    public void setDeals(List<Deal> deals) {
        this.deals = deals;
    }
    
    
    
    
    
    

}
