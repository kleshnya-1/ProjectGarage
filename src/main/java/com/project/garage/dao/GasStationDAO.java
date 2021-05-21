package com.project.garage.dao;

import com.project.garage.models.enums.FuelType;
import com.project.garage.services.queries.QueryToGasStationToFuelPrice;
import com.project.garage.services.ConnectionMaker;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
@Slf4j
public class GasStationDAO {
    Connection connection = new ConnectionMaker().makeConnection();
    QueryToGasStationToFuelPrice queryToGasStationToFuelPrice = new QueryToGasStationToFuelPrice();




    public Map getFuelMap(){
        Map<String, Double> fuelMap = new HashMap<String, Double>();

       try {
           ResultSet resultSet = queryToGasStationToFuelPrice.askerToGasStationToFuelPrice(connection);

           while ( resultSet.next()){

               fuelMap.put(resultSet.getString("fuel_type"),resultSet.getDouble("price_in_byn"));
           }


       } catch (Exception e) {
           e.printStackTrace();
       }

       log.debug(fuelMap.toString());
        return fuelMap;
    }

    public double getFuelPrice (FuelType fuelType){




        return (double)getFuelMap().get(fuelType.toString());

    }



}
