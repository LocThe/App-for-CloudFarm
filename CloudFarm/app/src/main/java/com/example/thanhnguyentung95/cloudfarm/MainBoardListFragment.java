package com.example.thanhnguyentung95.cloudfarm;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import adapters.StationAdapter;
import objectlist.Station;

import static com.example.thanhnguyentung95.cloudfarm.MainActivity.MAINBOARD_KEY_GET_STATION;
import static com.example.thanhnguyentung95.cloudfarm.MainBoard.MAINBOARD_KEY_GET_STATIONS;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainBoardListFragment extends ListFragment {

    private final String KEY_GET_DATA = "cloudfarm key_get_data";
    private ArrayList<Station> stations, savedStations;
    private StationAdapter stationAdapter;
    private Intent intent;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        intent = getActivity().getIntent();
        stations = (ArrayList<Station>) (ArrayList<Station>)intent.getSerializableExtra(MainActivity.MAIN_KEY_GET_STATIONS);

        if(stations != null){
            stationAdapter = new StationAdapter(getActivity(),stations);
            setListAdapter(stationAdapter);
            getListView().setDivider(ContextCompat.getDrawable(getActivity(),android.R.color.black));
            getListView().setDividerHeight(1);

        } else {
            stations = (ArrayList<Station>) savedInstanceState.get(KEY_GET_DATA);
        }
        registerForContextMenu(getListView());
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_GET_DATA, stations);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

//        launchStationDetailActivity(MainActivity.FragmentToLaunch.VIEW, position);
        // ### Trinh The Loc
        Intent intent = new Intent(getActivity(), StationInfo.class/* ten cua class chuyen den */);
        Station station = (Station) getListAdapter().getItem(position);
        intent.putExtra(MAINBOARD_KEY_GET_STATIONS, station);
        startActivity(intent);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.long_press_layout, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // give me the position of whatever note I long pressed on
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int rowPosition = info.position;

        switch (item.getItemId()){
            // if we press edit
            case R.id.view:
                launchStationDetailActivity(MainActivity.FragmentToLaunch.VIEW,rowPosition);
                return true;
            case R.id.edit:
                launchStationDetailActivity(MainActivity.FragmentToLaunch.EDIT,rowPosition);
                return true;
        }
        return super.onContextItemSelected(item);
    }

    public void launchStationDetailActivity(MainActivity.FragmentToLaunch ftl,int position){

        // grab the station information associated with whatever note item  we clicked on
        Station station = (Station) getListAdapter().getItem(position);

        // create a new intent that launches our stationDetailAcitivity
        Intent intent = new Intent(getActivity(), StationDetailActivity.class);
        // pass along the information of the note we clicked on to our stationDetailActivity
        intent.putExtra(MAINBOARD_KEY_GET_STATION, station);
        intent.putExtra(MainBoard.MAINBOARD_KEY_GET_STATIONS, stations);
        switch (ftl){
            case VIEW:
                intent.putExtra(MainActivity.STATION_FRAGMENT_TO_LOAD_EXTRA,MainActivity.FragmentToLaunch.VIEW);
                break;
            case EDIT:
                intent.putExtra(MainActivity.STATION_FRAGMENT_TO_LOAD_EXTRA,MainActivity.FragmentToLaunch.EDIT);
                break;
        }
        startActivity(intent);
    }

}
