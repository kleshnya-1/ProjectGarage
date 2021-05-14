package com.project.garage.services;

import com.project.garage.models.serviceObjects.SubOrder;

import java.util.Date;

public class WatchCalc {




    Date date1 = new Date();


    public int returnWatch(SubOrder subOrder) throws Exception {

        if (((int)(date1.getHours() + subOrder.getHoursBeforeOrder()) % 24) > 12) {
            return 2;
        } if ((((int)date1.getHours() + subOrder.getHoursBeforeOrder()) % 24) <= 12) {
            return 1;
        } else throw new Exception("invalid watch parameters")
        ;

}}
