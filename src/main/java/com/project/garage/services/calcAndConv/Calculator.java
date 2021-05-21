package com.project.garage.services.calcAndConv;


import com.project.garage.models.serviceObjects.SubOrder;
import com.project.garage.models.serviceObjects.SubOrderWithSpecialConditions;
import lombok.Data;

@Data
public class Calculator {

    private int reserveDistanceInKm = 5;
    private int distanceFromGarageKm = 2;
    //private double unloadedDistance ;
    //private double loadedDistance ;
    //private double expectedLoadedConsumption;

    private double reserveTimeHours = 0.5;
    private double timeForLandingFullHours = 0.25;
    private double speedMiddleKmH = 50;

    private SubOrder subOrder ;
    private  SubOrderWithSpecialConditions subOwSc;


    public Calculator() {

    }
    public Calculator(SubOrder subOrder, SubOrderWithSpecialConditions subOwSc) {
       this.subOrder = subOrder;
       this.subOwSc = subOwSc;

    }
}
