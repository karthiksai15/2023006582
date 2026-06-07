package com.karthiksai15.vehiclescheduler.model;

import java.util.List;

public class ScheduleResponse{

    private Integer depotId;
    private Integer mechanicHours;
    private Integer totalImpact;
    private List<Vehicle> selectedVehicles;

    public Integer getDepotId(){
        return depotId;
    }

    public void setDepotId(Integer depotId){
        this.depotId=depotId;
    }

    public Integer getMechanicHours(){
        return mechanicHours;
    }

    public void setMechanicHours(Integer mechanicHours){
        this.mechanicHours=mechanicHours;
    }

    public Integer getTotalImpact(){
        return totalImpact;
    }

    public void setTotalImpact(Integer totalImpact){
        this.totalImpact=totalImpact;
    }

    public List<Vehicle> getSelectedVehicles(){
        return selectedVehicles;
    }

    public void setSelectedVehicles(List<Vehicle> selectedVehicles){
        this.selectedVehicles=selectedVehicles;
    }
}