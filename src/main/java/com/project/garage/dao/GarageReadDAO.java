package com.project.garage.dao;
import com.project.garage.models.objects.CarResult;
import com.project.garage.models.serviceObjects.SubOrder;
import com.project.garage.models.serviceObjects.SubOrderWithSpecialConditions;
import com.project.garage.services.*;
import com.project.garage.services.collectionFillers.CarListFiller;
import com.project.garage.services.queries.QueryToBaseForCarAndDriver;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class GarageReadDAO {

    Connection connection = new ConnectionMaker().makeConnection();
    CarListFiller carListFiller = new CarListFiller();
    QueryToBaseForCarAndDriver queryToBaseForCarAndDriver = new QueryToBaseForCarAndDriver();

    SubOrderWithSpecialConditionsFilter subOrderWithSpecialConditionsFilter = new SubOrderWithSpecialConditionsFilter();
ScheduleFilter scheduleFilter = new ScheduleFilter();

    public List<CarResult> getCarListSuitableCars(SubOrder subOrder, SubOrderWithSpecialConditions subOrderWithSpecialConditions){
        List<CarResult> carList = new ArrayList<>();
        List<CarResult> carListFilteredSchedule = new ArrayList<>();

        ResultSet resultSet ;
        try {
            resultSet = queryToBaseForCarAndDriver.AskerToBaseForCarAndDriver(connection, subOrder, subOrderWithSpecialConditions);
             carList = carListFiller.CarListFiller(resultSet);
            log.info("Машин взято из гаража на основании заказа: "+carList.size());


            if (subOrderWithSpecialConditions != null) {
                carList = subOrderWithSpecialConditionsFilter.filter(carList, subOrderWithSpecialConditions);
                log.info("Машин подходит по дополнительным условиям: "+carList.size());

            }

            carListFilteredSchedule = scheduleFilter.filter(carList, subOrder);
            log.info("Машин свободно на время заказа: "+carListFilteredSchedule.size());
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        return carListFilteredSchedule;
    }




}