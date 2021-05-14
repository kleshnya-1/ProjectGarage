package com.project.garage.dao;


import com.project.garage.models.serviceObjects.SubOrder;
import com.project.garage.models.serviceObjects.SubOrderWithSpecialConditions;
import com.project.garage.services.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class GarageDAO {

   /* private static String URL = "jdbc:postgresql://localhost:5432/project_garage_database";
    private static String USERNAME = "postgres";
    private static String PASSWORD = "1";*/
    Connection connection = new ConnectionMaker().makeConnection();
    WatchCalc watchCalc = new WatchCalc();
    FuelTupeConverter fuelTupeConverter = new FuelTupeConverter();
    CarListFiller carListFiller = new CarListFiller();
    AskerToBaseForCarAndDriver askerToBaseForCarAndDriver = new AskerToBaseForCarAndDriver();



    public List<CarResult> getCarListSuitableCars(SubOrder subOrder, SubOrderWithSpecialConditions subOrderWithSpecialConditions){
        List<CarResult> carList = new ArrayList<>();
        ResultSet resultSet ;
        try {

            resultSet = askerToBaseForCarAndDriver.AskerToBaseForCarAndDriver(connection, subOrder, subOrderWithSpecialConditions);
             carList = carListFiller.CarListFiller(resultSet);
                             if (subOrderWithSpecialConditions != null) {
                                 // pridumat'



            }


        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return carList;
    }


}