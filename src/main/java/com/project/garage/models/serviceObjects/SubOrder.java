package com.project.garage.models.serviceObjects;

import com.project.garage.models.enums.CarType;
import lombok.Data;

import java.util.Calendar;

@Data
public class SubOrder {
    private String orderExplaining;
    private double distanceKm;
    private CarType carType;
    private int numOfPass=0;
    private int numOfKg=0;
    private int hoursBeforeOrder;
    Calendar orderTime;


    public SubOrder(double distanceKm, CarType carType,
                    int numOfOrderElements, int hoursBeforeOrder) {

        if (numOfOrderElements < 0 || hoursBeforeOrder < 0|| distanceKm < 1) {
            throw new IllegalArgumentException("not valid!");
        }
        {
            this.distanceKm = distanceKm;
            this.numOfPass = numOfPass;

            this.hoursBeforeOrder = hoursBeforeOrder;
        }
        this.carType = carType;

        if (carType == carType.T){
            this.numOfKg = numOfOrderElements;
        }
        if (carType == carType.P){
            this.numOfPass = numOfOrderElements;
            this.numOfKg = numOfOrderElements*75;
        }


    }

    public SubOrder(String orderExplaining, double distanceKm, CarType carType,
                    int numOfOrderElements, int hoursBeforeOrder) {


        this.orderExplaining = orderExplaining;

        if (numOfOrderElements < 0 || hoursBeforeOrder < 0|| distanceKm < 1) {
            throw new IllegalArgumentException("not valid!");
        }
        {
            this.distanceKm = distanceKm;
            this.numOfPass = numOfPass;

            this.hoursBeforeOrder = hoursBeforeOrder;
        }
        this.carType = carType;

        if (carType == carType.T){
            this.numOfKg = numOfOrderElements;
        }
        if (carType == carType.P){
            this.numOfPass = numOfOrderElements;
            this.numOfKg = numOfOrderElements*75;
        }

    }

    public SubOrder(String orderExplaining, double distanceKm, CarType carType,
                    int numOfOrderElements, Calendar orderStartTime) {


        this.orderExplaining = orderExplaining;
        this.orderTime=orderStartTime;


        {
            this.distanceKm = distanceKm;
            this.numOfPass = numOfPass;

            this.hoursBeforeOrder = hoursBeforeOrder;
        }
        this.carType = carType;

        if (carType == carType.T){
            this.numOfKg = numOfOrderElements;
        }
        if (carType == carType.P){
            this.numOfPass = numOfOrderElements;
            this.numOfKg = numOfOrderElements*75;
        }

    }


}
