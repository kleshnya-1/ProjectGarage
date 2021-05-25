package com.project.garage.dao.queries;

import com.project.garage.models.objects.orders.Order;
import com.project.garage.models.objects.orders.OrderWithSpecialConditions;
import lombok.extern.log4j.Log4j2;


import java.sql.*;

@Log4j2
public class QueriesToBaseForDriverSchedule {


    public ResultSet askForDateIntervals(Connection connection, int driverId) throws Exception {

        PreparedStatement preparedStatement = connection.prepareStatement

                (" select *    from driver_schedule where driver_id = ?");

        preparedStatement.setInt(1, driverId);

        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet;

    }

    public void makeNewOrderInSchedule(Connection connection, int driverId, String carId, String order, int num, String timeInterval, double orderPrice, double distance) throws Exception {

        Statement statement = connection.createStatement();
        String up =
                "INSERT INTO " +
                        "driver_schedule (driver_id, car_id, order_info, num_of_elements, busy_time, order_price, distance_km) " +
                        "VALUES (" + driverId + ", '" + carId + "', '" + order + "'," + num + "," + timeInterval + "," + orderPrice +", "+ distance +")";

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

    public void makeOrderDoneInSchedule(Connection connection, int driverId , String timeInterval) throws Exception {

        Statement statement = connection.createStatement();
        String up =
                "update  driver_schedule set note = 'выполнен'" +
                        "where  driver_id = "+driverId+" and busy_time = "+timeInterval;

        log.debug(up);
        statement.executeUpdate(up);




        //log.info("Запись водителю " + driverId + " на " + timeInterval + " сделана в базу на машину " + carId);


    }


    public void addMoney(Connection connection, int driverId, double earnedMoney) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "update  drivers set earned_this_week  =earned_this_week+? " +
                "where id=?");

        preparedStatement.setDouble(1, earnedMoney);
        preparedStatement.setInt(2, driverId);
        log.info("Водитель "+ driverId + " заработал " + earnedMoney);
        preparedStatement.executeUpdate();



    }
}
