package com.project.garage.models.objects;

import com.project.garage.models.objects.AssignedCar;
import com.project.garage.models.objects.CarResult;
import com.project.garage.models.objects.TimeInterval;
import com.project.garage.models.serviceObjects.SubOrder;
import com.project.garage.services.calcAndConv.PriceCalculator;
import com.project.garage.services.calcAndConv.TimeForOrderCalc;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AssignedCarCreator {
    PriceCalculator priceCalculator = new PriceCalculator();
    TimeForOrderCalc timeForOrderCalc = new TimeForOrderCalc();

    public AssignedCar create(CarResult choosedCar, SubOrder subOrderForAssigning,double percentOfMargin) {



       AssignedCar a   = new AssignedCar(choosedCar);



        a.setOrder(subOrderForAssigning.getOrderExplaining());
        a.setPriceForOrder((1+percentOfMargin/100)*priceCalculator.calculatePrice(subOrderForAssigning.getDistanceKm(), subOrderForAssigning.getNumOfKg(), choosedCar));

        //расчет временного интервала
        TimeInterval timeIntervalForThisOrder = new TimeInterval();
            Calendar start = new GregorianCalendar();
            start.add(Calendar.HOUR, subOrderForAssigning.getHoursBeforeOrder());
            timeIntervalForThisOrder.setStartDate(start);
            int timeForOrder = timeForOrderCalc.timeExpectedInt(subOrderForAssigning.getDistanceKm(), subOrderForAssigning.getNumOfKg(), choosedCar.getMaxLoadKg());
            Calendar finish = new GregorianCalendar();
            finish.setTime(start.getTime());
            finish.add(Calendar.HOUR, timeForOrder);
            timeIntervalForThisOrder.setEndDate(finish);



        a.setTimeInterval(timeIntervalForThisOrder);


        if (subOrderForAssigning.getNumOfPass()!=0)
            a.setNumOfElements(  subOrderForAssigning.getNumOfPass());
        else
            a.setNumOfElements(  subOrderForAssigning.getNumOfKg());





        return a;


    }
}
