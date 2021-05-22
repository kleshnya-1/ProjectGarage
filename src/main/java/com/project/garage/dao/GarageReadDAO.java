package com.project.garage.dao;
import com.project.garage.models.objects.cars.CarResult;
import com.project.garage.models.objects.orders.Order;
import com.project.garage.models.objects.orders.OrderWithSpecialConditions;
import com.project.garage.services.*;
import com.project.garage.services.collectionFillersAndPutters.CarListFiller;
import com.project.garage.dao.queries.QueryToBaseForCarAndDriver;
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

    public List<CarResult> getCarListSuitableCars(Order order, OrderWithSpecialConditions orderWithSpecialConditions){
        List<CarResult> carList = new ArrayList<>();
        List<CarResult> carListFilteredSchedule = new ArrayList<>();

        ResultSet resultSet ;
        try {
            resultSet = queryToBaseForCarAndDriver.AskerToBaseForCarAndDriver(connection, order, orderWithSpecialConditions);
             carList = carListFiller.CarListFiller(resultSet);
            log.info("Машин взято из гаража на основании заказа: "+carList.size());


            if (orderWithSpecialConditions != null) {
                carList = subOrderWithSpecialConditionsFilter.filter(carList, orderWithSpecialConditions);
                log.info("Машин подходит по дополнительным условиям: "+carList.size());

            }

            carListFilteredSchedule = scheduleFilter.filter(carList, order);
            log.info("Машин свободно на время заказа: "+carListFilteredSchedule.size());
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        return carListFilteredSchedule;
    }




}