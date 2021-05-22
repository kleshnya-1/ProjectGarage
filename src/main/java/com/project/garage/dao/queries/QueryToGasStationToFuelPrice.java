package com.project.garage.dao.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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























