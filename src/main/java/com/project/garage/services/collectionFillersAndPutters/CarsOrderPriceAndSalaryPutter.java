package com.project.garage.services.collectionFillersAndPutters;

import com.project.garage.ExceptionNoCarsFound;
import com.project.garage.services.calcAndConv.PriceCalculator;
import com.project.garage.models.objects.cars.CarResult;
import com.project.garage.dao.GarageReadDAO;
import com.project.garage.models.suppObjects.EconomicBlock;
import com.project.garage.models.objects.orders.Order;
import com.project.garage.models.objects.orders.OrderWithSpecialConditions;
import com.project.garage.services.calcAndConv.FuelCalc;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class CarsOrderPriceAndSalaryPutter {

    GarageReadDAO garageReadDAO = new GarageReadDAO();
    PriceCalculator priceCalculator = new PriceCalculator();
    FuelCalc fuelCalc = new FuelCalc();


    public NavigableMap<EconomicBlock, CarResult> make(Order order, OrderWithSpecialConditions subOwSc) throws ExceptionNoCarsFound {
        NavigableMap<EconomicBlock, CarResult> carsAndPrices = new TreeMap();
        List<CarResult> carList = garageReadDAO.getCarListSuitableCars(order, subOwSc);

        //log.info("Машин взято из базы: "+carList.size());

        log.info("Машин поступило на расчет топлива: "+carList.size());

        carList.forEach(carResult -> {

            double fuelNeeded = fuelCalc.fuelConsExpectedFromCar(order.getDistanceKm(),
                    order.getNumOfKg(), carResult);
            double price = priceCalculator.calculatePrice(order.getDistanceKm(),
                    order.getNumOfKg(), carResult);

            log.debug("на " + order.getDistanceKm() + " км" + " потребуется " + fuelNeeded + " л топлива");

//проверка на наличие топлива для поездки
            if (fuelNeeded < carResult.getFuel_in_tank()) {
                carsAndPrices.put(new EconomicBlock(0, price, carResult.getEarnedThisWeek()), carResult);
            }


        });
        log.info("Машин с достаточным числом топлива: "+carsAndPrices.size());

        if (carsAndPrices.size() == 0)
            throw new ExceptionNoCarsFound();
        else return carsAndPrices;
    }


    public NavigableMap<EconomicBlock, CarResult> makeFromList(List<CarResult> carList, Order order, OrderWithSpecialConditions subOwSc) {

        NavigableMap<EconomicBlock, CarResult> carsAndPrices = new TreeMap();
        carList.forEach(carResult -> {
            double price = priceCalculator.calculatePrice(order.getDistanceKm(),
                    order.getNumOfKg(), carResult);


            try {
                carsAndPrices.put(new EconomicBlock(0, price, carResult.getEarnedThisWeek()), (CarResult) carResult.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }


        });
        return carsAndPrices;
    }

}
