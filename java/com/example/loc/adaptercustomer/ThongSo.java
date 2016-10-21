package com.example.loc.adaptercustomer;

/**
 * Created by LOC on 10/11/2016.
 */

public class ThongSo {
    public String Time;
    public String Temp;
    public String Light;
    public String Humi;
    public String CO2;

    public ThongSo(String time, String temp, String light, String humi, String CO2) {
        Time = time;
        Temp = temp;
        Light = light;
        Humi = humi;
        this.CO2 = CO2;
    }
}
