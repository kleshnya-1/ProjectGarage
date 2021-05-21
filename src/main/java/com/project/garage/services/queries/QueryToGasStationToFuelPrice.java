package com.project.garage.services.queries;

import com.project.garage.models.serviceObjects.SubOrder;
import com.project.garage.models.serviceObjects.SubOrderWithSpecialConditions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



import com.project.garage.models.serviceObjects.SubOrder;
import com.project.garage.models.serviceObjects.SubOrderWithSpecialConditions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

public class QueryToGasStationToFuelPrice {

    public ResultSet askerToGasStationToFuelPrice
            (Connection connection) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement
                (" select * " +
                        "from fuel_price" );

        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }



}























