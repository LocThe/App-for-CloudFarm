package com.example.thanhnguyentung95.cloudfarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import objectlist.Station;

public class MainBoard extends AppCompatActivity {

    public static final String MAINBOARD_KEY_GET_STATION = "cloudfarm.mainboard key get station";
    public static final String MAINBOARD_KEY_GET_STATIONS = "cloudfarm.mainboard key get stations";

    Intent homeIntent;
    ArrayList<Station> station_arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainboard);

        setTitle(R.string.mainBoardTitle);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

}
