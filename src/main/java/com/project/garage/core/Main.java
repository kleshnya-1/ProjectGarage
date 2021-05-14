package com.project.garage.core;

import com.project.garage.dao.CarResult;
import com.project.garage.dao.GarageDAO;
import com.project.garage.models.enums.CarType;
import com.project.garage.models.enums.FuelType;
import com.project.garage.models.objects.Car;
import com.project.garage.models.objects.ExpectedCar;
import com.project.garage.models.serviceObjects.SubOrder;
import com.project.garage.models.serviceObjects.SubOrderWithSpecialConditions;
import com.project.garage.services.Operator;


import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        SubOrder o = new SubOrder(10.4, CarType.T,  1000,2);
        SubOrderWithSpecialConditions o1 = new SubOrderWithSpecialConditions("Touota", FuelType.electric, false);

      //  DELETED____Order order = new DELETED____Order(o,o1);
       // DELETED____Order order1 = new DELETED____Order(o);

        Operator operator = new Operator();
       // operator.getSubOrder(o);
        //operator.getSubOrderWithSpecialConditions(o1);
      //  ExpectedCar expectedCar = operator.getExpectedCar();


        GarageDAO g = new GarageDAO();

        List<CarResult> c =        g.getCarListSuitableCars(o, null);
        //System.out.println(c.get(1));
        System.out.println(c);
        //operator

      //  GarageDAO












    }

}
