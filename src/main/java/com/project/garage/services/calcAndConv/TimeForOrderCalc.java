package com.project.garage.services.calcAndConv;

import com.project.garage.models.objects.CarResult;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class TimeForOrderCalc extends Calculator {


    public double timeExpected(double distance, int load, int maxLoad) {

        double timeForOrder = distance / getSpeedMiddleKmH();
        double extraTime = super.getReserveTimeHours() + getTimeForLandingFullHours() * 2;


        return timeForOrder + extraTime;
    }

    public int timeExpectedInt(double distance, int load, int maxLoad) {

        double timeDouble = timeExpected(distance, load, maxLoad);

        int time = (int) Math.ceil(timeDouble);


        log.debug("на перевозку " + load + "кг. на " + distance +
                "км потребуется " + timeDouble + " (" + time + ") ч.");
        return time;
    }


}
