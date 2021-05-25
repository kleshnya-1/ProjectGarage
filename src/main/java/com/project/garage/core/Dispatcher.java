package com.project.garage.core;

import com.project.garage.dao.DriverScheduleReaderDAO;
import com.project.garage.dao.DriverScheduleWriterDAO;
import com.project.garage.models.objects.cars.AssignedCarCreator;
import com.project.garage.ExceptionNoCarsFound;
import com.project.garage.models.objects.cars.AssignedCar;
import com.project.garage.models.objects.cars.CarResult;
import com.project.garage.dao.GarageReadDAO;
import com.project.garage.models.objects.orders.TimeTable;
import com.project.garage.models.suppObjects.EconomicBlock;
import com.project.garage.models.objects.orders.Order;
import com.project.garage.models.objects.orders.OrderWithSpecialConditions;
import com.project.garage.services.collectionFillersAndPutters.CarsOrderPriceAndSalaryPutter;
import com.project.garage.services.collectionFillersAndPutters.CarsSuitableIndexesPutter;
import com.project.garage.services.calcAndConv.PriceCalculator;
import lombok.extern.slf4j.Slf4j;

import java.util.*;


@Slf4j
public class Dispatcher {
    private double indexOfClientParkMoneyRelationship = 0;
    // 1 - клиент получает минимальную цену
    // 0 - парк отдает заказ самому "бедному" водителю за неделю
    private double percentOfMargin = 30;


    GarageReadDAO garageReadDAO = new GarageReadDAO();
    PriceCalculator priceCalculator = new PriceCalculator();
    CarsOrderPriceAndSalaryPutter carsOrderPriceAndSalaryPutter = new CarsOrderPriceAndSalaryPutter();
    CarsSuitableIndexesPutter carsSuitableIndexesPutter = new CarsSuitableIndexesPutter();
    DriverScheduleReaderDAO driverScheduleReaderDAO = new DriverScheduleReaderDAO();

    DriverScheduleWriterDAO driverScheduleWriterDAO = new DriverScheduleWriterDAO();

    public Dispatcher(double percentOfMargin, double indexOfClientParkMoneyRelationship) {
        this.indexOfClientParkMoneyRelationship = indexOfClientParkMoneyRelationship;
    }

    public Dispatcher(double indexOfClientParkMoneyRelationship) {

        this.indexOfClientParkMoneyRelationship = indexOfClientParkMoneyRelationship;
        log.info("Диспетчер создан с индексом " + indexOfClientParkMoneyRelationship);
    }


    // по заказу выбирает машину
    public CarResult publishOrderAndReturnChoosedCar(Order order, OrderWithSpecialConditions oWsC) {

        try {
            //запрос на лист машин и присвоение машинам эконом. показателей.
            // eсли пуст - выкинет ошибку
            NavigableMap<EconomicBlock, CarResult> carsAndPrices =
                    carsOrderPriceAndSalaryPutter.make(order, oWsC);


            log.info("Диспетчер(" + indexOfClientParkMoneyRelationship + "). Машин с присвоенными ценами: " + carsAndPrices.size());

            //присвоение машинам в листе индекса подходимости под заказ
            NavigableMap<EconomicBlock, CarResult> carsAndPricesAndIndexes =
                    carsSuitableIndexesPutter.putIndexes(carsAndPrices, indexOfClientParkMoneyRelationship);

            log.info("Диспетчер(" + indexOfClientParkMoneyRelationship + "). Машин с присвоенными рейтингами: " + carsAndPricesAndIndexes.size());
            log.debug("\n" + carsAndPricesAndIndexes.toString());

            //TreeMap<EconomicBlock, CarResult> s = new TreeMap<>();
            //s.putAll(carsAndPricesAndIndexes);

            return carsAndPricesAndIndexes.firstEntry().getValue();

        } catch (ExceptionNoCarsFound e) {
            log.info("Машин по запросу не найдено!");
            e.printStackTrace();
            return null;
        }


    }

    // переданную машину преобразует и ДЕЛАЕТ запись в рассписании.
    public AssignedCar assignToRaceAndReturnAssignedCar(CarResult choosedCar, Order orderForAssigning) {

        if (choosedCar == null) throw new NullPointerException("Машина не передана для обработки");

        AssignedCarCreator assignedCarCreator= new AssignedCarCreator();

        AssignedCar assignedCar = assignedCarCreator.create(choosedCar, orderForAssigning,  percentOfMargin);
        //все посчитать

        try {
            driverScheduleWriterDAO.addInSchedule(assignedCar);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // добавить водителю в рассписание

        return assignedCar;


    }


    //по заказу выбирает машину, машину преобразует и ДЕЛАЕТ запись в рассписании.
    public AssignedCar publishOrderAndReturnAssignedCar(Order order, OrderWithSpecialConditions oWsC){

        try {

            return assignToRaceAndReturnAssignedCar(publishOrderAndReturnChoosedCar(order, oWsC), order);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void printSchedule(){


        //String format = "%-15s%-15s%-15s%-15s%-10d%-50d-50d%n";
        String
                format =
                "%-20s%-10s%-15s%" +
                "-20s%-25s%-10s%" +
                "-15s%-15s%-15s%n";

        System.out.printf(format,
                "Водитель", "Номер А/М","Машина", "Дата и время",
                 "Задание", "Кол-во",
                "Расстояние", "Цена", "Примечание");
        System.out.println();



       List<TimeTable> l = driverScheduleReaderDAO.getTimeTable();
        TimeTable tPrev =l.get(0);
        for (TimeTable t: l){

            if (!t.getDriverName().equals(tPrev.getDriverName())) {

                System.out.println("***");
                tPrev = t;

            } else if (t!=l.get(0))t.setDriverName(" ");




            System.out.printf(format,
                    t.getDriverName(), t.getCarId(), t.getCarBrand(),
                    t.getBusyTime(),                     t.getOrder(), t.getNumOfEl(),
                    t.getDistance(), t.getPrice(), t.getNote()
            );

        }

        //int




        //вывести на экран
        //отельным классом посчитать шанс поломки и предупредить
    }




}