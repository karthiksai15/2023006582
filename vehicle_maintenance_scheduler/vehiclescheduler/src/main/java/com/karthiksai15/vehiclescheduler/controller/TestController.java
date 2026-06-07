package com.karthiksai15.vehiclescheduler.controller;

import com.karthiksai15.vehiclescheduler.model.DepotResponse;
import com.karthiksai15.vehiclescheduler.model.ScheduleResponse;
import com.karthiksai15.vehiclescheduler.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController{

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/depots")
    public DepotResponse getDepots(){
        return vehicleService.getDepots();
    }

    @GetMapping("/vehicles")
    public Object getVehicles(){
        return vehicleService.getVehicles();
    }

    @GetMapping("/schedule/{depotId}")
    public ScheduleResponse schedule(@PathVariable Integer depotId){
        return vehicleService.scheduleVehicles(depotId);
    }
}