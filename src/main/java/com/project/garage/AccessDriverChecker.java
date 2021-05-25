package com.project.garage;

import com.project.garage.dao.DriverScheduleReaderDAO;
import com.project.garage.dao.DriverScheduleWriterDAO;
import com.project.garage.models.objects.cars.AssignedCar;
import com.project.garage.models.objects.orders.TimeTable;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class AccessDriverChecker {

    DriverScheduleReaderDAO driverScheduleReaderDAO = new DriverScheduleReaderDAO();


    public boolean check(int driverId, AssignedCar car) {

        //проверяет, если ли в рассписании за этим водителем такая закрепленная машина

        boolean decision = false;
        List<TimeTable> l = driverScheduleReaderDAO.getTimeTable();

        for (TimeTable t : l) {

            if (t.getCarId().equals(car.getCarResultInAssignedCar().getId()) &&
                    car.getCarResultInAssignedCar().getDriver_id() == driverId) {
                log.info("Подтверждаю доступ к " + t.getCarId());
                decision = true;
                break;

            }


        }

        return decision;
    }
}
