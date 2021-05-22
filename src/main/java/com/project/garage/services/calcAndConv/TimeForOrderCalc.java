package com.project.garage.services.calcAndConv;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class TimeForOrderCalc extends Calculator {


    public int minutesForOrderExpected(double distance, int load, int maxLoad) {

        double minutesForOrder = distance*60 / getSpeedMiddleKmH();
        double extraTimeMinutes = (super.getReserveTimeHours() + getTimeForLandingFullHours() * 2)*60;



        return (int) (minutesForOrder + extraTimeMinutes + getMinutesOneWayTime()*2);
    }

    public int timeExpectedForOrderInt(double distance, int load, int maxLoad) {

        double timeDouble = minutesForOrderExpected(distance, load, maxLoad);

        int time = (int) Math.ceil(timeDouble);


        log.debug("на перевозку " + load + "кг. на " + distance +
                "км потребуется " + timeDouble + " (" + time + ") ч.");
        return time;
    }

    public int getMinutesOneWayTime() {

         double oneWayTime = getDistanceFromGarageKm()/getSpeedMiddleKmH();
         return (int)(oneWayTime*60);

    }


}
