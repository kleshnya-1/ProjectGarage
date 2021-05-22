package com.project.garage.dao;

import com.project.garage.models.objects.orders.TimeTable;
import com.project.garage.services.ConnectionMaker;
import com.project.garage.models.suppObjects.TimeInterval;
import com.project.garage.dao.queries.QueriesToBaseForDriverSchedule;
import com.project.garage.services.collectionFillersAndPutters.ScheduleListFiller;
import com.project.garage.services.calcAndConv.ScheduleListToDateConverter;
import com.project.garage.services.collectionFillersAndPutters.TimeTableFiller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.*;

public class DriverScheduleReaderDAO {




    Connection connection = new ConnectionMaker().makeConnection();
    QueriesToBaseForDriverSchedule queryToBaseForDriverSchedule = new QueriesToBaseForDriverSchedule();
    ScheduleListFiller scheduleListFiller = new ScheduleListFiller();
    ScheduleListToDateConverter scheduleListToDateConverter = new ScheduleListToDateConverter();
    TimeTableFiller timeTableFiller = new TimeTableFiller();

    public List<String> getScheduleListString(int driverId){

        List<String> schedule = new ArrayList<>();
        ResultSet resultSet ;

        try {
            resultSet = queryToBaseForDriverSchedule.askForDateIntervals(connection, driverId);
            schedule = scheduleListFiller.ScheduleListStringFiller(resultSet);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return schedule;
    }

    public List<TimeInterval> getScheduleListTimeInt(int driverId){

        List<TimeInterval> l = new ArrayList<>();
        try {
               l = scheduleListToDateConverter.convertMethodRef(getScheduleListString(driverId));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return l;
    }

    public List<TimeTable> getTimeTable( ){

        List<TimeTable> timeTable = new ArrayList<>();
        ResultSet resultSet ;

        try {
            resultSet = queryToBaseForDriverSchedule.
                    askerForFullSchedule(connection);
            timeTable = timeTableFiller.createTimeTableList(resultSet);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return timeTable;
    }


}
