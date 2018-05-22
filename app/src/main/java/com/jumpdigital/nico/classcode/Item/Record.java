package com.jumpdigital.nico.classcode.Item;

/**
 * Created by Nico on 21/05/2018.
 */

public class Record {

    private String dateFormat;
    private String dayName;
    private int attendance;

    public Record() {

    }

    public Record(String dateFormat, String dayName, int attendance) {
        this.dateFormat = dateFormat;
        this.dayName = dayName;
        this.attendance = attendance;
    }

    //getter
    public String getDateFormat() {
        return dateFormat;
    }

    public String getDayName() {
        return dayName;
    }

    public int getAttendance() {
        return attendance;
    }

    //setter
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }
}
