package com.project.garage.services.calcAndConv;

import com.project.garage.models.objects.CarResult;

public class FuelCalc extends Calculator {







    public double fuelConsExpected(double distance, double consumption,
                                   double loadedConsumption, int load, int maxLoad){

         double unloadedDistance = getDistanceFromGarageKm()*2+getReserveDistanceInKm();
         double loadedDistance = distance;
         double expectedLoadedConsumption = ( ((double)load/(double)maxLoad)*(loadedConsumption-consumption)+consumption);

        return ((unloadedDistance*consumption) + (loadedDistance*expectedLoadedConsumption))/100;
    }

    public double fuelConsExpectedFromCar(double distanceKm, int numOfKg, CarResult carResult) {
    return fuelConsExpected(distanceKm, carResult.getConsumption(), carResult.getConsumption_loaded(), numOfKg, carResult.getMaxLoadKg());
    }



}
