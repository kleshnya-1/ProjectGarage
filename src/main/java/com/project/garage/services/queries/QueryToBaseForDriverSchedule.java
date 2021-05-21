package com.project.garage.services.queries;

import com.project.garage.models.objects.TimeInterval;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class QueryToBaseForDriverSchedule {


    public ResultSet askForDateIntervals(Connection connection, int driverId) throws Exception {

        PreparedStatement preparedStatement = connection.prepareStatement

                (" select *    from driver_schedule where driver_id = ?" );

        preparedStatement.setInt(1, driverId );

        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet;

    }

    public void makeNoteInSchedule(Connection connection, int driverId, String order, int num, String timeInterval) throws Exception {

        PreparedStatement preparedStatement = connection.prepareStatement

                ("INSERT INTO driver_schedule (driver_id, order, num_of_elements, busy_time) VALUES (?,?,?,?)");

        preparedStatement.setInt(1, driverId );
        preparedStatement.setString(2, order );
        preparedStatement.setInt(3, num );
        preparedStatement.setString(4, timeInterval );

        //ResultSet resultSet = preparedStatement.executeQuery();

        //return resultSet;

    }


}
