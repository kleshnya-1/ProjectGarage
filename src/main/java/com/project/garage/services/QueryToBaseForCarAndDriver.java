package com.project.garage.services;

import com.project.garage.models.serviceObjects.SubOrder;
import com.project.garage.models.serviceObjects.SubOrderWithSpecialConditions;
import com.project.garage.services.calcAndConv.WatchCalc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class QueryToBaseForCarAndDriver {
    WatchCalc watchCalc = new WatchCalc();






    public ResultSet AskerToBaseForCarAndDriver(Connection connection, SubOrder subOrder, SubOrderWithSpecialConditions subOrderWithSpecialConditions) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement
                (" select * " +
                        "from cars left join drivers on cars.driver_id = drivers.id where " +
                        " seats >=? and carrying >=? and is_ok = true and watch=?  " );
        //SELECT * FROM course WHERE dept_name='Comp. Sci.' AND credits>3;
        //preparedStatement.setInt(1, 12);
        preparedStatement.setInt(1, subOrder.getNumOfPass());
        preparedStatement.setInt(2, subOrder.getNumOfKg());
        preparedStatement.setInt(3, watchCalc.returnWatch(subOrder));
        ResultSet resultSet = preparedStatement.executeQuery();



        return resultSet;
    }







}
