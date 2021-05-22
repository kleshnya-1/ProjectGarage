package com.project.garage.services;

import com.project.garage.models.objects.cars.CarResult;
import com.project.garage.models.objects.orders.OrderWithSpecialConditions;

import java.util.ArrayList;
import java.util.List;

public class SubOrderWithSpecialConditionsFilter {


    public List<CarResult> filter(List<CarResult> l, OrderWithSpecialConditions sOrder){
        List<CarResult> filteredCars = new ArrayList<>(l);




        for (CarResult c : l){


            if (c.isIs_male()!=sOrder.isIs_male())
                filteredCars.remove(c);

            if (sOrder.getFuelType()!=null&&!c.getFuelType().equals(sOrder.getFuelType()))
                filteredCars.remove(c);

            if ((sOrder.getBrand()!=null)&&(!c.getBrand().equals(sOrder.getBrand())) )
                filteredCars.remove(c);

        }
        System.out.println(filteredCars);



        return filteredCars;

    }
}
