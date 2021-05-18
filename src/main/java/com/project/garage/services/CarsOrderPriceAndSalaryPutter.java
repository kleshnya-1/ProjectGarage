package com.project.garage.services;

import com.project.garage.core.PriceCalculator;
import com.project.garage.models.objects.CarResult;
import com.project.garage.dao.GarageReadDAO;
import com.project.garage.models.objects.EconomicPOKAZATELI;
import com.project.garage.models.serviceObjects.SubOrder;
import com.project.garage.models.serviceObjects.SubOrderWithSpecialConditions;
import com.project.garage.services.calcAndConv.FuelCalc;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
@Slf4j
public class CarsOrderPriceAndSalaryPutter {

    GarageReadDAO garageReadDAO = new GarageReadDAO();
    PriceCalculator priceCalculator = new PriceCalculator();
    FuelCalc fuelCalc = new FuelCalc();


    public NavigableMap<EconomicPOKAZATELI, CarResult> make(SubOrder subOrder, SubOrderWithSpecialConditions subOwSc){
        NavigableMap<EconomicPOKAZATELI, CarResult> carsAndPrices = new TreeMap();
        List<CarResult> carList = garageReadDAO.getCarListSuitableCars(subOrder,subOwSc);

        carList.forEach(carResult -> {

            double fuelNeeded = fuelCalc.fuelConsExpectedFromCar(subOrder.getDistanceKm(),
                    subOrder.getNumOfKg(),carResult);
            double price = priceCalculator.calculatePrice(subOrder.getDistanceKm(),
                    subOrder.getNumOfKg(),carResult);

            log.info("на "+subOrder.getDistanceKm()+" км"+ " потребуется "+fuelNeeded+" л топлива");

//проверка на наличие топлива для поездки
            if (fuelNeeded<carResult.getFuel_in_tank()) {
                carsAndPrices.put(new EconomicPOKAZATELI(0, price, carResult.getEarnedThisWeek()),    carResult);
            }

        } );
        return carsAndPrices;
    }


    public NavigableMap<EconomicPOKAZATELI, CarResult> makeFromList(List<CarResult> carList, SubOrder subOrder, SubOrderWithSpecialConditions subOwSc){

        NavigableMap<EconomicPOKAZATELI, CarResult> carsAndPrices = new TreeMap();
        carList.forEach(carResult -> {
            double price = priceCalculator.calculatePrice(subOrder.getDistanceKm(),
                    subOrder.getNumOfKg(),carResult);


            try {
                carsAndPrices.put(new EconomicPOKAZATELI(0, price, carResult.getEarnedThisWeek()), (CarResult) carResult.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }


        } );
        return carsAndPrices;
    }

}
