package com.project.garage.services;

import com.project.garage.models.objects.CarResult;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleListFiller {

    public List ScheduleListStringFiller(ResultSet resultSet) throws SQLException {
        List<String> schedule = new ArrayList<>();


        while (resultSet.next()) {

            schedule.add(resultSet.getString("busy_time"));

        }
        return schedule;
    }
}
