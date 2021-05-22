package com.project.garage.models.objects.orders;

import com.project.garage.services.calcAndConv.ScheduleListToDateConverter;
import lombok.Data;

import java.text.ParseException;

@Data
public class TimeTable {
    private String driverName;
    private String carId;
    private String carBrand;
    private String order;
    private String busyTime;

    private String numOfEl ;
    private String distance ;
    private String price;
    private String time;
    private String note = "omg";


    ScheduleListToDateConverter scheduleListToDateConverter = new ScheduleListToDateConverter();
    BusyTimeForTableSplitter busyTimeForTableSplitter = new BusyTimeForTableSplitter();
    public void setBusyTime(String busyTime) {
        try {
            this.busyTime = scheduleListToDateConverter.convertMethodForOneString(busyTime).toStringForTimeTable();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }



}
