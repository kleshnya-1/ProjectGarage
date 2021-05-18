package com.project.garage.models.objects;

import com.project.garage.models.objects.Car;
import lombok.Data;

import java.util.Iterator;


public class CarResult extends Car {
    private String driverName;
    private double earnedThisWeek;
    private int driver_id;



    public void setDriver_id(int id){
       // super.setDriver_id(id);
        this.driver_id = id;
    }


    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public void setEarnedThisWeek(double earnedThisWeek) {
        this.earnedThisWeek = earnedThisWeek;
    }

    public double getEarnedThisWeek() {
        return earnedThisWeek;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder
                (super.toString()+'\n');

        return sb.toString();
    }


}
