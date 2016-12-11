package com.example.thanhnguyentung95.cloudfarm;

/**
 * Created by LOC on 12/10/2016.
 */

public class Parameter {
    public String Time;
    public String Temp;
    public String Light;
    public String Humi;
    public String CO2;
    //phai theo thu tu trong activity_list_parameter
    public Parameter(String time, String temp, String humi, String light, String CO2) {
        this.Time = time;
        this.Temp = temp;
        this.Humi = humi;
        this.Light = light;
        this.CO2 = CO2;
    }
}