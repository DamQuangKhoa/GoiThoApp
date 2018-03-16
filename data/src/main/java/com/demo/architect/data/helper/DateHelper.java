package com.demo.architect.data.helper;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Skull on 07/01/2018.
 */

public class DateHelper {
    public static Date getZeroTimeDate(Date fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
