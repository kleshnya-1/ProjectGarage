package com.project.garage.core;

import com.project.garage.models.objects.CarResult;
import com.project.garage.dao.GarageReadDAO;
import com.project.garage.dao.GasStationDAO;
import com.project.garage.models.enums.CarType;
import com.project.garage.models.enums.FuelType;
import com.project.garage.models.objects.CarDriver;
import com.project.garage.models.serviceObjects.SubOrder;
import com.project.garage.models.serviceObjects.SubOrderWithSpecialConditions;


import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {


        SubOrder o = new SubOrder(10.4, CarType.T,  1000,0);
        SubOrder o2 = new SubOrder(10.4, CarType.T,  1000,0);
        SubOrder oPass = new SubOrder(10.4, CarType.P,  20,0);
        SubOrderWithSpecialConditions o1 = new SubOrderWithSpecialConditions("Touota", FuelType.electric, false);

        GasStationDAO gasStation = new GasStationDAO();
        Map mmm= gasStation.getFuelMap();
        System.out.println(mmm);

        GarageReadDAO g = new GarageReadDAO();
        List<CarResult> c =        g.getCarListSuitableCars(o, null);
        //System.out.println(c);

        PriceCalculator p = new PriceCalculator();
        //double price = p.calculatePrice(100, 2000, c.get(0));
       // System.out.println(price);

        Dispatcher dispatcher1 = new Dispatcher(1);
        Dispatcher dispatcher0 = new Dispatcher(0);

       CarResult c0=  dispatcher0.publishOrderAndReturnChoosedCar(o,null);
        CarResult c1=   dispatcher1.publishOrderAndReturnChoosedCar(o,null);


       System.out.println(c0);
        System.out.println("min price 'n"+c1);


      CarDriver driver0 = new CarDriver();
      CarDriver driver1 = new CarDriver();

      driver0.takeAndDriveTheCar(c0);
      driver0.returnCar(true);

















    }

}
