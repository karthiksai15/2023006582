package com.karthiksai15.vehiclescheduler.model;

import java.util.List;

public class VehicleResponse{

    private List<Vehicle> vehicles;

    public List<Vehicle> getVehicles() {
        return vehicles;
    }
    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}