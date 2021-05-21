package com.project.garage.services.queries;

import lombok.extern.log4j.Log4j2;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Log4j2
public class QueryToBaseForDriverSchedule  {



    public ResultSet askForDateIntervals(Connection connection, int driverId) throws Exception {

        PreparedStatement preparedStatement = connection.prepareStatement

                (" select *    from driver_schedule where driver_id = ?" );

        preparedStatement.setInt(1, driverId );

        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet;

    }

    public void makeNoteInSchedule(Connection connection, int driverId, String order, int num, String timeInterval,double orderPrice) throws Exception {


        Statement statement = connection.createStatement();
        String up = "INSERT INTO driver_schedule (driver_id, order_info, num_of_elements, busy_time, order_price) " +
                "VALUES ("+driverId+","+order+","+num+","+timeInterval+","+orderPrice+")";

        statement.executeUpdate(up);
        log.info("Запись водителю "+driverId+" на "+timeInterval+" сделана в базу");

        /*PreparedStatement preparedStatement = connection.prepareStatement

                ("INSERT INTO driver_schedule (driver_id, order_info, num_of_elements, busy_time, order_price) VALUES (?,?,?,?,?)");

        preparedStatement.setInt(1, driverId );
        preparedStatement.setString(2, order );
        preparedStatement.setInt(3, num );
        preparedStatement.setObject(4, timeInterval  );
        preparedStatement.setDouble(5, orderPrice );
        preparedStatement.executeUpdate();
        log.info("Запись водителю "+driverId+" на "+timeInterval+" сделана в базу");
        log.info(preparedStatement);*/

        //ResultSet resultSet = preparedStatement.executeQuery();

        //return resultSet;

    }
   /* public void makeNoteInSchedule(Connection connection, int driverId, String order, int num, String timeInterval,double orderPrice) throws Exception {

        PreparedStatement preparedStatement = connection.prepareStatement

                ("INSERT INTO driver_schedule (driver_id, order_info, num_of_elements,  order_price) VALUES (?,?,?,?,?)");

        preparedStatement.setInt(1, driverId );
        preparedStatement.setString(2, order );
        preparedStatement.setInt(3, num );

        preparedStatement.setDouble(4, orderPrice );
        log.info("Запись водителю "+driverId+" на "+timeInterval+" сделана в базу");

        //ResultSet resultSet = preparedStatement.executeQuery();

        //return resultSet;

    }*/


}
