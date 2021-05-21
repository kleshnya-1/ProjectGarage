package com.project.garage.core;

import com.project.garage.dao.DriverScheduleWriterDAO;
import com.project.garage.models.objects.AssignedCarCreator;
import com.project.garage.dao.ExceptionNoCarsFound;
import com.project.garage.models.objects.AssignedCar;
import com.project.garage.models.objects.CarResult;
import com.project.garage.dao.GarageReadDAO;
import com.project.garage.models.objects.EconomicBlock;
import com.project.garage.models.serviceObjects.SubOrder;
import com.project.garage.models.serviceObjects.SubOrderWithSpecialConditions;
import com.project.garage.services.CarsOrderPriceAndSalaryPutter;
import com.project.garage.services.CarsSuitableIndexesPutter;
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

    DriverScheduleWriterDAO driverScheduleWriterDAO = new DriverScheduleWriterDAO();

    public Dispatcher(double percentOfMargin, double indexOfClientParkMoneyRelationship) {
        this.indexOfClientParkMoneyRelationship = indexOfClientParkMoneyRelationship;
    }

    public Dispatcher(double indexOfClientParkMoneyRelationship) {

        this.indexOfClientParkMoneyRelationship = indexOfClientParkMoneyRelationship;
        log.info("Диспетчер создан с индексом " + indexOfClientParkMoneyRelationship);
    }


    public CarResult publishOrderAndReturnChoosedCar(SubOrder subOrder, SubOrderWithSpecialConditions subOwSc) {

        try {
            //запрос на лист машин и присвоение машинам эконом. показателей.
            // eсли пуст - выкинет ошибку
            NavigableMap<EconomicBlock, CarResult> carsAndPrices =
                    carsOrderPriceAndSalaryPutter.make(subOrder, subOwSc);


            log.info("Диспетчер(" + indexOfClientParkMoneyRelationship + "). Машин с присвоенными ценами: " + carsAndPrices.size());

            //присвоение машинам в листе индекса подходимости под заказ
            NavigableMap<EconomicBlock, CarResult> carsAndPricesAndIndexes =
                    carsSuitableIndexesPutter.putIndexes(carsAndPrices, indexOfClientParkMoneyRelationship);

            log.info("Диспетчер(" + indexOfClientParkMoneyRelationship + "). Машин с присвоенными индексами: " + carsAndPricesAndIndexes.size());
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

    public AssignedCar assignToRace(CarResult choosedCar, SubOrder subOrderForAssigning ) {

        AssignedCarCreator assignedCarCreator= new AssignedCarCreator();

        AssignedCar assignedCar = assignedCarCreator.create(choosedCar, subOrderForAssigning,  percentOfMargin);
        //все посчитать

        try {
            driverScheduleWriterDAO.addInSchedule(assignedCar);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // добавить водителю в рассписание

        return assignedCar;


    }




}