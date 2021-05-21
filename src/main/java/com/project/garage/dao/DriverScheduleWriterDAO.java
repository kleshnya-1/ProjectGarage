package com.project.garage.dao;

import com.project.garage.models.objects.AssignedCar;
import com.project.garage.services.ConnectionMaker;
import com.project.garage.services.queries.QueryToBaseForDriverSchedule;

import java.sql.Connection;

public class DriverScheduleWriterDAO {

    Connection connection = new ConnectionMaker().makeConnection();

    QueryToBaseForDriverSchedule queryToBaseForDriverSchedule = new QueryToBaseForDriverSchedule();


    public void addInSchedule(AssignedCar assignedCar) throws Exception {


        queryToBaseForDriverSchedule.makeNoteInSchedule(connection,
                assignedCar.getCarResultInAssignedCar().getDriver_id(),
                assignedCar.getOrder(),
                assignedCar.getNumOfElements(),
                assignedCar.getTimeInterval().toStringForSQL()
        );

    }


}

