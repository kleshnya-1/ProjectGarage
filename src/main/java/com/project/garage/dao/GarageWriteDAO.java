package com.project.garage.dao;

import com.project.garage.dao.queries.QueryToBaseForCarAndDriver;
import com.project.garage.services.ConnectionMaker;

import java.sql.Connection;

public class GarageWriteDAO {

    QueryToBaseForCarAndDriver queryToBaseForCarAndDriver = new QueryToBaseForCarAndDriver();

    Connection connection = new ConnectionMaker().makeConnection();
    public void addInfoForCars
            (String id, boolean technicalSatusOk,int distanceHasPassed,double fuelHasConsumed){

        try {
            queryToBaseForCarAndDriver.addInfoForCars(connection,id, technicalSatusOk, distanceHasPassed, fuelHasConsumed );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
