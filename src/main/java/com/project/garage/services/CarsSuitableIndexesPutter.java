package com.project.garage.services;

import com.project.garage.models.objects.CarResult;
import com.project.garage.models.objects.EconomicBlock;
import com.project.garage.services.calcAndConv.CarsSuitableIndexesCalculator;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class CarsSuitableIndexesPutter {

    CarsSuitableIndexesCalculator carsSuitableIndexesCalculator = new CarsSuitableIndexesCalculator();





    public NavigableMap<EconomicBlock, CarResult> putIndexes
            (NavigableMap<EconomicBlock, CarResult> carMap,
             double indexOfClientParkMoneyRelationship) {


        double mimSalary = 0;
        double maxSalary = 0;

        double minPrice = 0;
        double maxPrice = 0;

        for (Map.Entry<EconomicBlock, CarResult> entry : carMap.entrySet()) {

            if (entry.getKey().getDriverEarnedThisWeek() < mimSalary)
                mimSalary = entry.getKey().getDriverEarnedThisWeek();

            if (entry.getKey().getDriverEarnedThisWeek() > maxSalary)
                maxSalary = entry.getKey().getDriverEarnedThisWeek();

            if (entry.getKey().getPriceForOrder() < minPrice)
                minPrice = entry.getKey().getPriceForOrder();

            if (entry.getKey().getPriceForOrder() > maxPrice)
                maxPrice = entry.getKey().getPriceForOrder();

        }


        double diffInSalaries = maxSalary - mimSalary;
        double diffInPrices = maxPrice - minPrice;
//        double diffInPrices = carMap.lastKey().getPriceForOrder() -
//                carMap.firstKey().getPriceForOrder();

        double diffCoeffForCorrection = diffInPrices/diffInSalaries;
        /*коэфициент нивелирует разность масштабов значений цен рейса и
        зарплат и приводит их к сравнимым равнозначным значениям*/


        log.debug("diff in sal " + diffInSalaries);
        log.debug("diff in prices " + diffInPrices);
        log.debug("diff in values " + diffCoeffForCorrection);


        TreeMap <EconomicBlock, CarResult> carsAndIndexes = new TreeMap<>();


        carMap.forEach((e, c) -> {
            e.setSuitableIndex(carsSuitableIndexesCalculator.executeIndexCalculation(indexOfClientParkMoneyRelationship, diffCoeffForCorrection, e.getPriceForOrder(), c.getEarnedThisWeek()));
            carsAndIndexes.put(e,c);
        });




        return carsAndIndexes;

    }




//
}