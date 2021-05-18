package com.project.garage.dao;
import com.project.garage.models.objects.CarResult;
import com.project.garage.models.serviceObjects.SubOrder;
import com.project.garage.models.serviceObjects.SubOrderWithSpecialConditions;
import com.project.garage.services.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class GarageReadDAO {

    Connection connection = new ConnectionMaker().makeConnection();
    CarListFiller carListFiller = new CarListFiller();
    QueryToBaseForCarAndDriver queryToBaseForCarAndDriver = new QueryToBaseForCarAndDriver();



    public List<CarResult> getCarListSuitableCars(SubOrder subOrder, SubOrderWithSpecialConditions subOrderWithSpecialConditions){
        List<CarResult> carList = new ArrayList<>();

        ResultSet resultSet ;
        try {

            resultSet = queryToBaseForCarAndDriver.AskerToBaseForCarAndDriver(connection, subOrder, subOrderWithSpecialConditions);
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