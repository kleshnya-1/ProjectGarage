package com.project.garage.dao;

import com.project.garage.models.objects.cars.AssignedCar;
import com.project.garage.services.ConnectionMaker;
import com.project.garage.dao.queries.QueriesToBaseForDriverSchedule;

import java.sql.Connection;

public class DriverScheduleWriterDAO {

    Connection connection = new ConnectionMaker().makeConnection();

    QueriesToBaseForDriverSchedule queryToBaseForDriverSchedule = new QueriesToBaseForDriverSchedule();


    public void addInSchedule(AssignedCar assignedCar) throws Exception {


        queryToBaseForDriverSchedule.makeNoteInSchedule(connection,
                assignedCar.getCarResultInAssignedCar().getDriver_id(),
                assignedCar.getCarResultInAssignedCar().getId(),
                assignedCar.getOrder(),
                assignedCar.getNumOfElements(),
                assignedCar.getTimeInterval().toStringForSQL(),
                assignedCar.getPriceForOrder()
        );

    }


}

