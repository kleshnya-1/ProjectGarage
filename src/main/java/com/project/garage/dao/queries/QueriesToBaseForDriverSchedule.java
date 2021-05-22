package com.project.garage.dao.queries;

import com.project.garage.models.objects.orders.Order;
import com.project.garage.models.objects.orders.OrderWithSpecialConditions;
import lombok.extern.log4j.Log4j2;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Log4j2
public class QueriesToBaseForDriverSchedule {


    public ResultSet askForDateIntervals(Connection connection, int driverId) throws Exception {

        PreparedStatement preparedStatement = connection.prepareStatement

                (" select *    from driver_schedule where driver_id = ?");

        preparedStatement.setInt(1, driverId);

        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet;

    }

    public void makeNoteInSchedule(Connection connection, int driverId, String carId, String order, int num, String timeInterval, double orderPrice) throws Exception {

        Statement statement = connection.createStatement();
        String up =
                "INSERT INTO " +
                        "driver_schedule (driver_id, car_id, order_info, num_of_elements, busy_time, order_price) " +
                        "VALUES (" + driverId + ", '" + carId + "', '" + order + "'," + num + "," + timeInterval + "," + orderPrice + ")";

        log.debug(up);
        statement.executeUpdate(up);

        log.info("Запись водителю " + driverId + " на " + timeInterval + " сделана в базу на машину " + carId);


    }


    public ResultSet askerForFullSchedule(Connection connection) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement
                (" select * from driver_schedule  " +
                        "left join drivers on driver_schedule.driver_id = " +
                        "drivers.id  " +
                        "left join cars on driver_schedule.car_id = " +
                        "cars.id ORDER BY driver_schedule.driver_id  ASC");

                                ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet;
    }


}
