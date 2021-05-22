package com.project.garage.services.collectionFillersAndPutters;

import com.project.garage.models.objects.orders.TimeTable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TimeTableFiller {



    public List<TimeTable> createTimeTableList(ResultSet r) throws SQLException {


        List<TimeTable> timeTableList= new ArrayList<>();
        while (r.next()){

            TimeTable t = new TimeTable();

            t.setDriverName(r.getString("name"));
            t.setCarId(r.getString("car_id"));
            t.setCarBrand(r.getString("brand"));
            t.setOrder(r.getString("order_info"));
            t.setPrice(r.getString("order_price"));
            t.setNumOfEl(r.getString("num_of_elements"));
           // t.setDistance(r.getDouble(""));

            t.setBusyTime(r.getString("busy_time"));

            timeTableList.add(t);

        }
        return timeTableList;






    }


}
