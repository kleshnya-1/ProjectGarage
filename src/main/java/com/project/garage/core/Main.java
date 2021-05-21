package com.project.garage.core;

import com.project.garage.dao.*;
import com.project.garage.models.objects.CarResult;
import com.project.garage.models.enums.CarType;
import com.project.garage.models.enums.FuelType;
import com.project.garage.models.objects.CarDriver;
import com.project.garage.models.objects.TimeInterval;
import com.project.garage.models.serviceObjects.SubOrder;
import com.project.garage.models.serviceObjects.SubOrderWithSpecialConditions;
import com.project.garage.services.ScheduleListToDateConverter;
import com.project.garage.services.calcAndConv.PriceCalculator;
import lombok.extern.slf4j.Slf4j;


import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) throws Exception {


        SubOrder o = new SubOrder(10.4, CarType.T,  1000,20);
        SubOrder o2 = new SubOrder(10.4, CarType.T,  1000,0);
        SubOrder oPass = new SubOrder(10.4, CarType.P,  20,0);
        SubOrderWithSpecialConditions o1 = new SubOrderWithSpecialConditions("IVECO", FuelType.diesel, false);
        GasStationDAO gasStation = new GasStationDAO();

        log.info(gasStation.getFuelMap().toString());


        GarageReadDAO g = new GarageReadDAO();
       // List<CarResult> c =        g.getCarListSuitableCars(o, null);
        //System.out.println(c);

        PriceCalculator p = new PriceCalculator();
        //double price = p.calculatePrice(100, 2000, c.get(0));
       // System.out.println(price);

        Dispatcher dispatcher1 = new Dispatcher(1);
        Dispatcher dispatcher0 = new Dispatcher(0);

       CarResult c0=  dispatcher0.publishOrderAndReturnChoosedCar(o,null);
        CarResult c1=   dispatcher1.publishOrderAndReturnChoosedCar(o,null);


       System.out.println(c0);
        //System.out.println("min price 'n"+c1);


      CarDriver driver0 = new CarDriver();
     // CarDriver driver1 = new CarDriver();

      driver0.takeAndDriveTheCar(c0);
      driver0.returnCar(true);

      dispatcher0.assignToRace(c1,o);

//      Date date = new Date();
//
//        System.out.println(date.toString());
//        Calendar calendar = Calendar.getInstance();
//        System.out.println(calendar.getTime());
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:SSS'Z'");
//        System.out.println();
//        System.out.println(simpleDateFormat.format(calendar.getTime()));
//
//
//        DriverWriterDAO d = new DriverWriterDAO();
//        d.putBusyTimeInBase(5,6);



//        DriverScheduleReaderDAO driverScheduleReaderDAO = new DriverScheduleReaderDAO();
//        List oo = driverScheduleReaderDAO.getScheduleList(1);
//        ScheduleListToDateConverter scheduleListToDateConverter = new ScheduleListToDateConverter();
//        List mmm = scheduleListToDateConverter.convertMethod(oo);
//
//        System.out.println(mmm.toString());













    }

}
