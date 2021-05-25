package com.project.garage.services.collectionFillersAndPutters;

import lombok.extern.log4j.Log4j2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Log4j2
public class ScheduleListFiller {

    public List ScheduleListStringFiller(ResultSet resultSet) throws SQLException {
        List<String> schedule = new ArrayList<>();


        while (resultSet.next()) {

            if (resultSet.getString("note") == null || !resultSet.getString("note").equals("Выполнен"))
            {
                schedule.add(resultSet.getString("busy_time"));

            }

        }
        return schedule;
    }
}
