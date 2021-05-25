package com.project.garage.services.calcAndConv;


import com.project.garage.models.objects.orders.Order;
import com.project.garage.models.objects.orders.OrderWithSpecialConditions;
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

    private Order order;
    private OrderWithSpecialConditions subOwSc;

    public Calculator() {

    }

    public Calculator(Order order, OrderWithSpecialConditions subOwSc) {
       this.order = order;
       this.subOwSc = subOwSc;

    }
    double unloadedDistance = getDistanceFromGarageKm()*2;
    double unloadedDistanceForCalc = getDistanceFromGarageKm()*2+getReserveDistanceInKm();

}
