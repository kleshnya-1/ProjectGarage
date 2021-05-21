package com.project.garage.models.objects;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

@Data
@Log4j2
public class TimeInterval {
    Calendar startDate = new GregorianCalendar();
    Calendar endDate = new GregorianCalendar();

    public TimeInterval(Calendar startDate, Calendar endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public TimeInterval() {
        this.startDate = startDate;
        this.endDate = endDate;
    }



    public boolean isCrossedChecker(TimeInterval t){
        //проверяет пересечение временных интервалов

        boolean result =(t.getEndDate().after(this.getStartDate())&&this.getEndDate().after(t.getStartDate())) ;

log.debug("is "+t.toString() + " and " + toString()+ " crossed? I think it's "+ result);
        return result;

    }

    public String toString(){
               DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        return  (format.format(this.startDate.getTime())+" - "+
                format.format(this.endDate.getTime()));

    }

    public String toStringForSQL(){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        return  ("["+format.format(this.startDate.getTime())+","+
                format.format(this.endDate.getTime())+"]");

    }

   // public TimeInterval generateTimeTinterval()


}
