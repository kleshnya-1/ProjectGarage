package com.project.garage.dao;

import com.project.garage.services.ConnectionMaker;
import com.project.garage.models.objects.TimeInterval;
import com.project.garage.services.queries.QueryToBaseForDriverSchedule;
import com.project.garage.services.ScheduleListFiller;
import com.project.garage.services.ScheduleListToDateConverter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.*;

public class DriverScheduleReaderDAO {




    Connection connection = new ConnectionMaker().makeConnection();
    QueryToBaseForDriverSchedule queryToBaseForDriverSchedule = new QueryToBaseForDriverSchedule();
    ScheduleListFiller scheduleListFiller = new ScheduleListFiller();
    ScheduleListToDateConverter scheduleListToDateConverter = new ScheduleListToDateConverter();

    public List<String> getScheduleListString(int driverId){

        List<String> schedule = new ArrayList<>();

        List<TimeInterval> listTimeInterval = new ArrayList<>();

        ResultSet resultSet ;

        try {

            resultSet = queryToBaseForDriverSchedule.askForDateIntervals(connection, driverId);
            schedule = scheduleListFiller.ScheduleListStringFiller(resultSet);
            listTimeInterval = scheduleListToDateConverter.convertMethod(schedule);




        } catch (Exception throwables) {
            throwables.printStackTrace();
        }


        return schedule;
    }

    public List<TimeInterval> getScheduleListTimeInt(int driverId){

        List<TimeInterval> l = new ArrayList<>();

        try {
               l = scheduleListToDateConverter.convertMethod(getScheduleListString(driverId));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return l;


    }









//    QueryToBaseForCarAndDriver queryToBaseForCarAndDriver = new QueryToBaseForCarAndDriver();
//
//    SubOrderWithSpecialConditionsFilter subOrderWithSpecialConditionsFilter = new SubOrderWithSpecialConditionsFilter();


//    public List<CarResult> getCarListSuitableCars(SubOrder subOrder, SubOrderWithSpecialConditions subOrderWithSpecialConditions){
//        List<CarResult> carList = new ArrayList<>();
//
//        ResultSet resultSet ;
//        try {
//
//            resultSet = queryToBaseForCarAndDriver.AskerToBaseForCarAndDriver(connection, subOrder, subOrderWithSpecialConditions);
//            carList = carListFiller.CarListFiller(resultSet);
//            if (subOrderWithSpecialConditions != null) {
//                carList = subOrderWithSpecialConditionsFilter.filter(carList, subOrderWithSpecialConditions);
//
//
//            }
//
//        } catch (Exception throwables) {
//            throwables.printStackTrace();
//        }
//
//
//        return carList;



}
