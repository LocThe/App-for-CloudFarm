package com.example.thanhnguyentung95.cloudfarm;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import objectlist.Station;

public class StationDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_detail);
        createAddFragment();
//        setTitle(R.string.viewFragmentTitle);
    }

    private void createAddFragment(){

        //grab intent and fragment to launch from our main activity list fragment
        Intent intent = getIntent();
        MainActivity.FragmentToLaunch fragmentToLaunch =
                (MainActivity.FragmentToLaunch) intent.getExtras()
                        .getSerializable(MainActivity.STATION_FRAGMENT_TO_LOAD_EXTRA);

        // grabbing our fragment manager and fragment transaction so that we can add our edit or
        // view fragment dynamically
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // choose the correct fragment to load
        switch(fragmentToLaunch){
            case EDIT:
                // create and add station edit fragment to note detail activity if we that's what we
                // want to launch
                StationEditFragment stationEditFragment = new StationEditFragment();
                setTitle(R.string.editFragmentTitle);
                fragmentTransaction.add(R.id.station_container, stationEditFragment, "STATION_EDIT_FRAGMENT");
                break;
            case VIEW:
                StationViewFragment stationViewFragment = new StationViewFragment();
                setTitle(R.string.viewFragmentTitle);
                fragmentTransaction.add(R.id.station_container, stationViewFragment, "STATION_VIEW_FRAGMENT");
                break;
        }

        StationViewFragment stationViewFragment = new StationViewFragment(); // Unique String identify
        fragmentTransaction.add(R.id.station_container, stationViewFragment, "STATION_VIEW_FRAGMENT");

        // commit our changes to that everything works
        fragmentTransaction.commit();
    }

}
