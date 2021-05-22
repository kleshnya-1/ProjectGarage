package com.project.garage.services;

import com.project.garage.dao.DriverScheduleReaderDAO;
import com.project.garage.models.objects.cars.CarResult;
import com.project.garage.models.suppObjects.TimeInterval;
import com.project.garage.models.objects.orders.Order;
import com.project.garage.services.calcAndConv.ScheduleListToDateConverter;
import com.project.garage.services.calcAndConv.TimeForOrderCalc;
import lombok.extern.log4j.Log4j2;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Log4j2
public class ScheduleFilter {

    DriverScheduleReaderDAO driverScheduleReaderDAO = new DriverScheduleReaderDAO();

    ScheduleListToDateConverter scheduleListToDateConverter = new ScheduleListToDateConverter();
    TimeForOrderCalc timeForOrderCalc = new TimeForOrderCalc();

    public List<CarResult> filter(List<CarResult> l, Order sOrder) throws ParseException {

        List<CarResult> filteredCars = new ArrayList<>(l);

        log.debug("cars before schedule filter " + filteredCars.size());

        TimeInterval timeIntervalForThisOrder = new TimeInterval();
        Calendar start = sOrder.getOrderStartTime();
        //start.add(Calendar.MINUTE,  sOrder.getMinutesBeforeOrder());



        timeIntervalForThisOrder.setStartDate(start);



        for (CarResult c : l) {
            int minutesForOrder = timeForOrderCalc.minutesForOrderExpected(sOrder.getDistanceKm(), sOrder.getNumOfKg(), c.getMaxLoadKg());
            start.add(Calendar.MINUTE,  -timeForOrderCalc.getMinutesOneWayTime());
            Calendar finish = new GregorianCalendar();
            finish.setTime(start.getTime());
            finish.add(Calendar.MINUTE, minutesForOrder);
            timeIntervalForThisOrder.setEndDate(finish);


            List<TimeInterval> timeListForDriverC = driverScheduleReaderDAO.getScheduleListTimeInt(c.getDriver_id());


            for (TimeInterval t : timeListForDriverC) {
                if (t.isCrossedChecker(timeIntervalForThisOrder)) {
                    log.debug(timeIntervalForThisOrder+" и "+t+ " пересекаются, машина удалена");
                    filteredCars.remove(c);

                }

            }


        }

        log.debug("cars after schedule filter  " + filteredCars.size());


        return filteredCars;

    }


}
