package com.project.garage.services.calcAndConv;

import com.project.garage.models.objects.orders.Order;

import java.util.Calendar;

public class REFACTORWatchCalc {




    public int returnWatch(Order order) throws Exception {


       if(order.getOrderStartTime().get(Calendar.HOUR_OF_DAY)>12) return 2;
        if(order.getOrderStartTime().get(Calendar.HOUR_OF_DAY)<=12) return 1;
        else throw new Exception("invalid watch parameters");




}}
