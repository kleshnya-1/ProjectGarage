package com.project.garage.services.calcAndConv;

import com.project.garage.models.objects.cars.CarResult;
import com.project.garage.dao.GasStationDAO;

public class PriceCalculator {
   private FuelCalc fuelCalc = new FuelCalc();
    private GasStationDAO gasStationDAO = new GasStationDAO();


    public double calculatePrice(double distance, int load, CarResult carResult){

        //carResult

          double fuelCunsumption = fuelCalc.fuelConsExpected(distance, carResult.getConsumption(),
                carResult.getConsumption_loaded(), load, carResult.getMaxLoadKg());

          double fuelPrice = gasStationDAO.getFuelPrice(carResult.getFuelType());

          return fuelPrice*fuelCunsumption;

    }

}
