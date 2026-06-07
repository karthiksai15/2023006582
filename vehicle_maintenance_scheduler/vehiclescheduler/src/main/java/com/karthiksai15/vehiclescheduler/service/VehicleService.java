package com.karthiksai15.vehiclescheduler.service;

import com.karthiksai15.vehiclescheduler.model.Depot;
import com.karthiksai15.vehiclescheduler.model.DepotResponse;
import com.karthiksai15.vehiclescheduler.model.ScheduleResponse;
import com.karthiksai15.vehiclescheduler.model.Vehicle;
import com.karthiksai15.vehiclescheduler.model.VehicleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService{

    @Autowired
    private RestTemplate restTemplate;

    private static final String TOKEN="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJNYXBDbGFpbXMiOnsiYXVkIjoiaHR0cDovLzIwLjI0NC41Ni4xNDQvZXZhbHVhdGlvbi1zZXJ2aWNlIiwiZW1haWwiOiJrbW9wdXJAZ2l0YW0uaW4iLCJleHAiOjE3ODA4MTU0NDAsImlhdCI6MTc4MDgxNDU0MCwiaXNzIjoiQWZmb3JkIE1lZGljYWwgVGVjaG5vbG9naWVzIFByaXZhdGUgTGltaXRlZCIsImp0aSI6Ijc2NmY0Yjg1LTJlZmYtNDllMi1hOWUwLTY0ZjAzMTNjNWNkZCIsImxvY2FsZSI6ImVuLUlOIiwibmFtZSI6Im1vcHVyIGthcnRoaWsgc2FpIiwic3ViIjoiYWQ0ZGUxMzMtMmYwZi00Nzk5LWE4ZWMtYmRhNjRiZTdlMDY2In0sImVtYWlsIjoia21vcHVyQGdpdGFtLmluIiwibmFtZSI6Im1vcHVyIGthcnRoaWsgc2FpIiwicm9sbE5vIjoiMjAyMzAwNjU4MiIsImFjY2Vzc0NvZGUiOiJ3Z0t0Z1oiLCJjbGllbnRJRCI6ImFkNGRlMTMzLTJmMGYtNDc5OS1hOGVjLWJkYTY0YmU3ZTA2NiIsImNsaWVudFNlY3JldCI6InhaUVJ5elBtRnF4V2RHZngifQ.i9F3Eehhz4GfuK9KEQfIQSRhuIEGaXIQZA4wp3iwtOM";

    private static final String DEPOT_URL=
            "http://4.224.186.213/evaluation-service/depots";

    private static final String VEHICLE_URL=
            "http://4.224.186.213/evaluation-service/vehicles";

    public DepotResponse getDepots(){

        HttpHeaders headers=new HttpHeaders();
        headers.setBearerAuth(TOKEN);

        HttpEntity<String> request=
                new HttpEntity<>(headers);

        ResponseEntity<DepotResponse> response=
                restTemplate.exchange(
                        DEPOT_URL,
                        HttpMethod.GET,
                        request,
                        DepotResponse.class
                );

        return response.getBody();
    }

    public VehicleResponse getVehicles(){

        HttpHeaders headers=new HttpHeaders();
        headers.setBearerAuth(TOKEN);

        HttpEntity<String> request=
                new HttpEntity<>(headers);

        ResponseEntity<VehicleResponse> response=
                restTemplate.exchange(
                        VEHICLE_URL,
                        HttpMethod.GET,
                        request,
                        VehicleResponse.class
                );

        return response.getBody();
    }

    public ScheduleResponse scheduleVehicles(Integer depotId){

        DepotResponse depotResponse=getDepots();
        VehicleResponse vehicleResponse=getVehicles();

        int capacity=0;

        for(Depot depot:depotResponse.getDepots()){

            if(depot.getID().equals(depotId)){
                capacity=depot.getMechanicHours();
                break;
            }
        }

        List<Vehicle> vehicles=vehicleResponse.getVehicles();

        int n=vehicles.size();

        int[][] dp=new int[n+1][capacity+1];

        for(int i=1;i<=n;i++){

            Vehicle vehicle=vehicles.get(i-1);

            int duration=vehicle.getDuration();
            int impact=vehicle.getImpact();

            for(int j=0;j<=capacity;j++){

                if(duration<=j){

                    int include=impact+dp[i-1][j-duration];
                    int exclude=dp[i-1][j];

                    dp[i][j]=Math.max(include,exclude);

                }else{
                    dp[i][j]=dp[i-1][j];
                }
            }
        }

        List<Vehicle> selectedVehicles=new ArrayList<>();

        int j=capacity;

        for(int i=n;i>0;i--){

            if(dp[i][j]!=dp[i-1][j]){

                Vehicle vehicle=vehicles.get(i-1);

                selectedVehicles.add(vehicle);

                j=j-vehicle.getDuration();
            }
        }

        ScheduleResponse response=new ScheduleResponse();

        response.setDepotId(depotId);
        response.setMechanicHours(capacity);
        response.setTotalImpact(dp[n][capacity]);
        response.setSelectedVehicles(selectedVehicles);

        return response;
    }
}