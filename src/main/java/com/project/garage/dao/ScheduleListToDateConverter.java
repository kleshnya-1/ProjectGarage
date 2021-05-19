package com.project.garage.dao;

import com.project.garage.services.calcAndConv.TimeInterval;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class ScheduleListToDateConverter {




    public List<TimeInterval> convertMethod(List<String> list) throws ParseException {

        List<TimeInterval> listTimeInterval = new ArrayList<>();


        Pattern pattern = Pattern.compile("\"");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");




        for (String s: list){
            String[] strings = pattern.split(s);

            Calendar startDate = new GregorianCalendar();
            Calendar endDate = new GregorianCalendar();




            startDate.setTime( format.parse(strings[1]));
            endDate.setTime(format.parse(strings[3]));





            listTimeInterval.add(new TimeInterval(startDate,endDate)


            );

            System.out.println(listTimeInterval.toString());

        }

        return listTimeInterval;



    }

}
