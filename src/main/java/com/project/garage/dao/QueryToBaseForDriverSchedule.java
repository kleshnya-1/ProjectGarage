package com.project.garage.dao;

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
}
