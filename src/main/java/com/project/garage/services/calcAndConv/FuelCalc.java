package com.project.garage.services.calcAndConv;

import com.project.garage.models.objects.cars.CarResult;


public class FuelCalc extends Calculator {



    public double fuelConsExpected(double distance, double consumption,
                                   double loadedConsumption, int load, int maxLoad){


         double loadedDistance = distance;
         double expectedLoadedConsumption = ( ((double)load/(double)maxLoad)*(loadedConsumption-consumption)+consumption);

        return ((getUnloadedDistanceForCalc()*consumption) + (loadedDistance*expectedLoadedConsumption))/100;
    }

    public double fuelConsExpectedFromCar(double distanceKm, int numOfKg, CarResult carResult) {
    return fuelConsExpected(distanceKm, carResult.getConsumption(), carResult.getConsumption_loaded(), numOfKg, carResult.getMaxLoadKg());
    }





}
