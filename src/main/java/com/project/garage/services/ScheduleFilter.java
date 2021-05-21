package com.project.garage.services;

import com.project.garage.dao.DriverScheduleReaderDAO;
import com.project.garage.models.objects.CarResult;
import com.project.garage.models.objects.TimeInterval;
import com.project.garage.models.serviceObjects.SubOrder;
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

    public List<CarResult> filter(List<CarResult> l, SubOrder sOrder) throws ParseException {

        List<CarResult> filteredCars = new ArrayList<>(l);

        log.debug("cars before schedule filter " + filteredCars.size());

        TimeInterval timeIntervalForThisOrder = new TimeInterval();
        Calendar start = new GregorianCalendar();
        start.add(Calendar.HOUR, sOrder.getHoursBeforeOrder());


        timeIntervalForThisOrder.setStartDate(start);


        for (CarResult c : l) {
            int timeForOrder = timeForOrderCalc.timeExpectedInt(sOrder.getDistanceKm(), sOrder.getNumOfKg(), c.getMaxLoadKg());

            Calendar finish = new GregorianCalendar();
            finish.setTime(start.getTime());
            finish.add(Calendar.HOUR, timeForOrder);
            timeIntervalForThisOrder.setEndDate(finish);


            List<TimeInterval> timeListForDriverC = driverScheduleReaderDAO.getScheduleListTimeInt(c.getDriver_id());


            for (TimeInterval t : timeListForDriverC) {
                if (t.isCrossedChecker(timeIntervalForThisOrder)) {
                    filteredCars.remove(c);
                    break;
                }

            }


        }

        log.debug("cars after schedule filter  " + filteredCars.size());


        return filteredCars;

    }


}
