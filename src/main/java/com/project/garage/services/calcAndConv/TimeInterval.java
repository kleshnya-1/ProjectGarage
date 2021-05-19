package com.project.garage.services.calcAndConv;

import lombok.Data;

import java.util.Calendar;
import java.util.GregorianCalendar;
@Data
public class TimeInterval {
    Calendar startDate = new GregorianCalendar();
    Calendar endDate = new GregorianCalendar();

    public TimeInterval(Calendar startDate, Calendar endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
