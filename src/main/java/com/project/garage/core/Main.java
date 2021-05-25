package com.project.garage.core;

import com.project.garage.models.enums.FuelType;
import com.project.garage.models.objects.cars.AssignedCar;
import com.project.garage.models.enums.CarType;
import com.project.garage.models.objects.CarDriver;
import com.project.garage.models.objects.orders.Order;
import com.project.garage.models.objects.orders.OrderWithSpecialConditions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) throws Exception {


        Order o = new Order("из D в пункт E",16.4, CarType.T,  1000,18.0);
        Order oMore = new Order("из А в пункт Б",6.4, CarType.T,  900,16.0);
        Order oMore2 = new Order("Аэропорт Домодедово",18.3, CarType.P,  6,18.0);
        OrderWithSpecialConditions o1 = new OrderWithSpecialConditions("IVECO", FuelType.diesel, false);


        Dispatcher dispatcher0 = new Dispatcher(0);

        // CarResult c0=  dispatcher0.publishOrderAndReturnChoosedCar(o,null);


         /*
        //dispatcher0.publishOrderAndReturnAssignedCar(c0,o);

*/


        AssignedCar assignedCar =dispatcher0.publishOrderAndReturnAssignedCar(o, null);

       // dispatcher0.printSchedule();
        System.out.println();

        //CarDriver driver0 = new CarDriver(0);
        //driver0.setCar(assignedCar);

        CarDriver driver0 = new CarDriver(assignedCar.getCarResultInAssignedCar().getDriver_id());
        //driver0.takeTheCar(assignedCar);

        //driver0.driveCar();

       // driver0.returnCar(true);



        //  SubOrder o2 = new SubOrder(10.4, CarType.T,  1000,0);
        //SubOrder oPass = new SubOrder(10.4, CarType.P,  20,0);



        // List<CarResult> c =        g.getCarListSuitableCars(o, null);
        //System.out.println(c);

        //double price = p.calculatePrice(100, 2000, c.get(0));
        // System.out.println(price);

        // Dispatcher dispatcher1 = new Dispatcher(1);

        //CarResult c1=   dispatcher1.publishOrderAndReturnChoosedCar(o,null);



        //System.out.println("min price 'n"+c1);

        // CarDriver driver1 = new CarDriver();

        //  driver0.takeAndDriveTheCar(c0);
        // driver0.returnCar(true);


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
