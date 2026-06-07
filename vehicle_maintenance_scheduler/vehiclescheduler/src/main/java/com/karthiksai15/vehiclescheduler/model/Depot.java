package com.karthiksai15.vehiclescheduler.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Depot{

    @JsonProperty("ID")
    private Integer id;

    @JsonProperty("MechanicHours")
    private Integer mechanicHours;

    public Integer getID(){
        return id;
    }

    public void setID(Integer id){
        this.id=id;
    }

    public Integer getMechanicHours(){
        return mechanicHours;
    }

    public void setMechanicHours(Integer mechanicHours){
        this.mechanicHours=mechanicHours;
    }
}