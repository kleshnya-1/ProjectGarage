package com.project.garage.dao;

import com.project.garage.models.objects.cars.AssignedCar;
import com.project.garage.services.ConnectionMaker;
import com.project.garage.dao.queries.QueriesToBaseForDriverSchedule;

import java.sql.Connection;

public class DriverScheduleWriterDAO {

    Connection connection = new ConnectionMaker().makeConnection();

    QueriesToBaseForDriverSchedule queryToBaseForDriverSchedule = new QueriesToBaseForDriverSchedule();


    public void addInSchedule(AssignedCar assignedCar) throws Exception {


        queryToBaseForDriverSchedule.makeNewOrderInSchedule(connection,
                assignedCar.getCarResultInAssignedCar().getDriver_id(),
                assignedCar.getCarResultInAssignedCar().getId(),
                assignedCar.getOrder(),
                assignedCar.getNumOfElements(),
                assignedCar.getTimeInterval().toStringForSQL(),
                assignedCar.getPriceForOrder(),
                assignedCar.getOrderDistance()
        );

    }


    public void makeOrderDone(int driverId, AssignedCar car) throws Exception {

        queryToBaseForDriverSchedule.makeOrderDoneInSchedule(connection,driverId,car.getTimeInterval().toStringForSQL());

    }

    public void addMoney(int driverId, double earnedMoney) throws Exception {

        queryToBaseForDriverSchedule.addMoney(connection,driverId, earnedMoney);

    }
}

