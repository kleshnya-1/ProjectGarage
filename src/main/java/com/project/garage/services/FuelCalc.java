package com.project.garage.services;

public class FuelCalc {

    private int reserveDistanceInKm = 5;
    private int distanceFromGarageKm = 2;





    public double distanceExpected(double distance,double consumption, double loadedConsumption, int load, int maxLoad){

        double unloadedDistance = distanceFromGarageKm*2+reserveDistanceInKm;
        double loadedDistance = distance;
        double expectedLoadedConsumption = (load/maxLoad)*(loadedConsumption-consumption)+consumption;



        return (unloadedDistance/consumption) + (loadedDistance/expectedLoadedConsumption);
    }
}
