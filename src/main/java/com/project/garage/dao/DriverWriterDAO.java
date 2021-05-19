package com.project.garage.dao;

import com.project.garage.models.objects.CarResult;
import com.project.garage.models.serviceObjects.SubOrder;
import com.project.garage.models.serviceObjects.SubOrderWithSpecialConditions;
import com.project.garage.services.ConnectionMaker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DriverWriterDAO {


    Connection connection = new ConnectionMaker().makeConnection();



    public void putBusyTimeInBase(int hoursBeforeOrder, int orderProcessTime){

       // Date timeAndDateStart = new Date();
        //Date timeAndDateFinish = timeAndDateStart.setTime();
        Calendar timeAndDateStart = Calendar.getInstance();
        Calendar timeAndDateFinish =Calendar.getInstance();


        timeAndDateStart.add(Calendar.HOUR, hoursBeforeOrder);
                timeAndDateFinish.add(Calendar.HOUR, orderProcessTime+hoursBeforeOrder);


                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");

        System.out.println(simpleDateFormat.format(timeAndDateStart.getTime()));
        System.out.println(simpleDateFormat.format(timeAndDateFinish.getTime()));



        try {

                PreparedStatement preparedStatement = connection.prepareStatement
                        (" INSERT INTO drivers (busy_time) values ?" );

                preparedStatement.setString(1,
                        "[\""+simpleDateFormat.format(timeAndDateStart.getTime())+
                                "\""+
                                ","+
                                "\""+
                                simpleDateFormat.format(timeAndDateFinish.getTime())
                        );



            } catch (SQLException e) {
                e.printStackTrace();
            }



        }






    }




