package com.project.garage.services.calcAndConv;

import com.project.garage.models.objects.CarResult;

public class FuelCalc {

    private int reserveDistanceInKm = 5;
    private int distanceFromGarageKm = 2;
    double unloadedDistance =0;
    double loadedDistance =0;
    double expectedLoadedConsumption=0;





    public double fuelConsExpected(double distance, double consumption,
                                   double loadedConsumption, int load, int maxLoad){

         unloadedDistance = distanceFromGarageKm*2+reserveDistanceInKm;
         loadedDistance = distance;
         expectedLoadedConsumption = ( ((double)load/(double)maxLoad)*(loadedConsumption-consumption)+consumption);

        return ((unloadedDistance*consumption) + (loadedDistance*expectedLoadedConsumption))/100;
    }

    public double fuelConsExpectedFromCar(double distanceKm, int numOfKg, CarResult carResult) {
    return fuelConsExpected(distanceKm, carResult.getConsumption(), carResult.getConsumption_loaded(), numOfKg, carResult.getMaxLoadKg());
    }


//    public double fuelNedee77d(double distance,double consumption, double loadedConsumption,
//                             int load, int maxLoad){
//
//        unloadedDistance = distanceFromGarageKm*2+reserveDistanceInKm;
//        loadedDistance = distance;
//        expectedLoadedConsumption = (load/maxLoad)*(loadedConsumption-consumption)+consumption;
//
//
//        return ()
//
//    }

}
