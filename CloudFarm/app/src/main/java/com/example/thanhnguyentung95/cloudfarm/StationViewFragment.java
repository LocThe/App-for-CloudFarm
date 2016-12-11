package com.example.thanhnguyentung95.cloudfarm;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import objectlist.Station;


/**
 * A simple {@link Fragment} subclass.
 */
public class StationViewFragment extends Fragment {


    public StationViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentLayout = inflater.inflate(R.layout.fragment_station_view, container, false);

        TextView title = (TextView) fragmentLayout.findViewById(R.id.viewStationTitle);
        TextView decs = (TextView) fragmentLayout.findViewById(R.id.viewStationDesc);
        ImageView icon = (ImageView) fragmentLayout.findViewById(R.id.viewStationIcon);

        Intent intent = getActivity().getIntent();
        Station station = (Station)intent.getExtras().
                getSerializable(MainActivity.MAINBOARD_KEY_GET_STATION);
        title.setText(station.getName());
        decs.setText(station.getDesc());
        icon.setImageResource(station.getAssociatedDrawable());
        // Inflate the layout for this fragment
        return fragmentLayout;
    }
}
