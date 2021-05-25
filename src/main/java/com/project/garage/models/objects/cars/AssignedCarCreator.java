package com.project.garage.models.objects.cars;

import com.project.garage.models.suppObjects.TimeInterval;
import com.project.garage.models.objects.orders.Order;
import com.project.garage.services.calcAndConv.Calculator;
import com.project.garage.services.calcAndConv.PriceCalculator;
import com.project.garage.services.calcAndConv.TimeForOrderCalc;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AssignedCarCreator {
    PriceCalculator priceCalculator = new PriceCalculator();
    TimeForOrderCalc timeForOrderCalc = new TimeForOrderCalc();
    Calculator calculator = new Calculator();

    public AssignedCar create(CarResult choosedCar, Order orderForAssigning, double percentOfMargin) {


        AssignedCar a = new AssignedCar(choosedCar);

//заполняем данные, необходимые далее в экземпляре
        a.setOrder(orderForAssigning.getOrderExplaining());
        a.setOrderDistance(orderForAssigning.getDistanceKm()+calculator.getUnloadedDistanceForCalc());
        a.setFuelExpectedConsumption(priceCalculator.getFuelConsumption(orderForAssigning.getDistanceKm(), orderForAssigning.getNumOfKg(), choosedCar));
        a.setPriceForOrder((1 + percentOfMargin / 100) * priceCalculator.calculatePrice(orderForAssigning.getDistanceKm(), orderForAssigning.getNumOfKg(), choosedCar));

        //расчет временного интервала
        TimeInterval timeIntervalForThisOrder = new TimeInterval();

        Calendar orderStartTime = orderForAssigning.getOrderStartTime();
        //время начала заказа минус время на дорогу
        orderStartTime.add(Calendar.MINUTE, -timeForOrderCalc.getMinutesOneWayTime());
        timeIntervalForThisOrder.setStartDate(orderStartTime);


        Calendar finish = new GregorianCalendar();
        int timeForOrder = timeForOrderCalc.minutesForOrderExpected(orderForAssigning.getDistanceKm(), orderForAssigning.getNumOfKg(), choosedCar.getMaxLoadKg());

        //конец заказа это начало + ожидаемое время на выполнение заказа
        finish.setTime(orderStartTime.getTime());
        finish.add(Calendar.MINUTE, timeForOrder);

        timeIntervalForThisOrder.setEndDate(finish);


        a.setTimeInterval(timeIntervalForThisOrder);

        if (orderForAssigning.getNumOfPass() != 0)
            a.setNumOfElements(orderForAssigning.getNumOfPass());
        else
            a.setNumOfElements(orderForAssigning.getNumOfKg());
        return a;

    }
}
