package com.project.garage.dao;

import com.project.garage.models.objects.Car;
import lombok.Data;


public class CarResult extends Car {
    private String driverName;
    private int earnedThisWeek;
    private int driver_id;

    public void setDriver_id(int id){
       // super.setDriver_id(id);
        this.driver_id = id;
    }


    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public void setEarnedThisWeek(int earnedThisWeek) {
        this.earnedThisWeek = earnedThisWeek;
    }
}
