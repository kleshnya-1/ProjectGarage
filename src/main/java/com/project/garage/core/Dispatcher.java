package com.project.garage.core;

import com.project.garage.models.objects.CarResult;
import com.project.garage.dao.GarageReadDAO;
import com.project.garage.models.objects.EconomicPOKAZATELI;
import com.project.garage.models.serviceObjects.SubOrder;
import com.project.garage.models.serviceObjects.SubOrderWithSpecialConditions;
import com.project.garage.services.CarsOrderPriceAndSalaryPutter;
import com.project.garage.services.CarsSuitableIndexesPutter;
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


    public Dispatcher(double percentOfMargin, double indexOfClientParkMoneyRelationship) {
        this.indexOfClientParkMoneyRelationship = indexOfClientParkMoneyRelationship;
    }

    public Dispatcher(double indexOfClientParkMoneyRelationship) {

        this.indexOfClientParkMoneyRelationship = indexOfClientParkMoneyRelationship;
        log.info("Диспетчер создан с индексом " + indexOfClientParkMoneyRelationship);
    }


    public CarResult publishOrderAndReturnChoosedCar(SubOrder subOrder, SubOrderWithSpecialConditions subOwSc) {

       // List<CarResult> suitableCars =garageDAO.getCarListSuitableCars(subOrder, subOwSc);

        log.info("\n \nЗапущен метод присвоения машинам эконом. показателей ");
        NavigableMap<EconomicPOKAZATELI, CarResult> carsAndPrices =
                //carsOrderPriceAndSalaryPutter.makeFromList(suitableCars,subOrder, subOwSc);
                carsOrderPriceAndSalaryPutter.make(subOrder, subOwSc);


        //System.out.println(carsAndPrices);
        log.info("Машин с присвоенными ценами: " + carsAndPrices.size());

        log.info("\n \n Запущен метод присвоения машинам индекса подходимости под заказ ");

        NavigableMap<EconomicPOKAZATELI, CarResult> carsAndPricesAndIndexes=
                carsSuitableIndexesPutter.putIndexes(carsAndPrices,indexOfClientParkMoneyRelationship);

        log.info("Машин с присвоенными индексами: " + carsAndPricesAndIndexes.size());
        log.info( "\n"+carsAndPricesAndIndexes.toString());

        TreeMap<EconomicPOKAZATELI, CarResult> s = new TreeMap<>();
        s.putAll(carsAndPricesAndIndexes);



        return carsAndPricesAndIndexes.firstEntry().getValue();







    }









}