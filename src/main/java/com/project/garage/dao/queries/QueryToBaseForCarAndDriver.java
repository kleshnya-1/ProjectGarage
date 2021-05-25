package com.project.garage.dao.queries;

import com.project.garage.models.objects.orders.Order;
import com.project.garage.models.objects.orders.OrderWithSpecialConditions;
import com.project.garage.services.calcAndConv.REFACTORWatchCalc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class QueryToBaseForCarAndDriver {
    REFACTORWatchCalc REFACTORWatchCalc = new REFACTORWatchCalc();






    public ResultSet AskerToBaseForCarAndDriver(Connection connection, Order order, OrderWithSpecialConditions orderWithSpecialConditions) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement
                (" select * " +
                        "from cars left join drivers on cars.driver_id = drivers.id where " +
                        " seats >=? and carrying >=? and is_ok = true and watch=?  " );

        preparedStatement.setInt(1, order.getNumOfPass());
        preparedStatement.setInt(2, order.getNumOfKg());
        preparedStatement.setInt(3, REFACTORWatchCalc.returnWatch(order));
        ResultSet resultSet = preparedStatement.executeQuery();



        return resultSet;
    }

    public void addInfoForCars(Connection connection, String id, Boolean techState, int odometer, double fuel ) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement
                (" update  cars set is_ok=?, odometer_km=odometer_km+?, " +
                        "fuel_in_tank=fuel_in_tank-? " +
                        "where  id  =? " );

        preparedStatement.setBoolean(1, techState);
        preparedStatement.setInt(2, odometer);
        preparedStatement.setDouble(3, fuel);
        preparedStatement.setString(4, id);
        preparedStatement.executeUpdate();

    }







}
