
package com.jumpdigital.nico.classcode.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AttendanceResult {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("attendanceData")
    @Expose
    private AttendanceData attendanceData;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AttendanceData getAttendanceData() {
        return attendanceData;
    }

    public void setAttendanceData(AttendanceData attendanceData) {
        this.attendanceData = attendanceData;
    }

}
