package com.project.garage.services;

import com.project.garage.models.objects.CarResult;
import com.project.garage.models.objects.EconomicPOKAZATELI;
import com.project.garage.services.calcAndConv.CarsSuitableIndexesCalculator;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class CarsSuitableIndexesPutter {

    CarsSuitableIndexesCalculator carsSuitableIndexesCalculator = new CarsSuitableIndexesCalculator();





    public NavigableMap<EconomicPOKAZATELI, CarResult> putIndexes
            (NavigableMap<EconomicPOKAZATELI, CarResult> carMap,
             double indexOfClientParkMoneyRelationship) {


        double mimSalary = 0;
        double maxSalary = 0;

        double minPrice = 0;
        double maxPrice = 0;

        for (Map.Entry<EconomicPOKAZATELI, CarResult> entry : carMap.entrySet()) {

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



        log.info("diff in sal " + diffInSalaries);
        log.info("diff in prices " + diffInPrices);
        log.info("diff in values " + diffCoeffForCorrection);


        TreeMap <EconomicPOKAZATELI, CarResult> carsAndIndexes = new TreeMap<>();


        carMap.forEach((e, c) -> {
            e.setSuitableIndex(carsSuitableIndexesCalculator.executeIndexCalculation(indexOfClientParkMoneyRelationship, diffCoeffForCorrection, e.getPriceForOrder(), c.getEarnedThisWeek()));



            carsAndIndexes.put(e,c);
        });




        return carsAndIndexes;

    }




//
}