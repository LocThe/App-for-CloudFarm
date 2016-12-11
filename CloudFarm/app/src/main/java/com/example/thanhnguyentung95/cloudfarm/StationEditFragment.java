package com.example.thanhnguyentung95.cloudfarm;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;

import adapters.Adapters;
import iohandler.MyFileHandler;
import objectlist.Station;


/**
 * A simple {@link Fragment} subclass.
 */
public class StationEditFragment extends Fragment {

    private static final String MODIFIED_ICON = "Modified Icon";

    private ImageButton stationIconButton;
    private EditText title, desc;
    private Station.Type savedIconStation;
    private AlertDialog iconDialogObject, confirmDialogObject;
    private Station station;

    public StationEditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentLayout = inflater.inflate(R.layout.fragment_station_edit, container, false);

        if(savedInstanceState != null){
            savedIconStation = (Station.Type) savedInstanceState.get(MODIFIED_ICON);
        }

        // grab widget references  from layout
        title = (EditText) fragmentLayout.findViewById(R.id.editStationTitle);
        desc = (EditText) fragmentLayout.findViewById(R.id.editStationDesc);
        stationIconButton = (ImageButton) fragmentLayout.findViewById(R.id.editStationButton);
        Button saveButton = (Button) fragmentLayout.findViewById(R.id.saveStation);

        // populate widgets with note data
        Intent intent = getActivity().getIntent();
        station = (Station)intent.getExtras().getSerializable(MainActivity.MAINBOARD_KEY_GET_STATION);
        title.setText(station.getName());
        desc.setText(station.getDesc());

        // if we grabbed a category from our bunble than we know we changed orientation and saved
        // information so we set our image button background to that category
        if(savedIconStation != null){
            stationIconButton.setImageResource(Station.typeToDrawable(savedIconStation));
        } else {
            // otherwise we came from our list fragment so just do everything normally
            stationIconButton.setImageResource(station.getAssociatedDrawable());
            savedIconStation = station.getType();
        }
        buildIconsLayout();
        buildConfirmDialog();

        stationIconButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                iconDialogObject.show();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                confirmDialogObject.show();
            }
        });

        return fragmentLayout;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(MODIFIED_ICON, savedIconStation);
    }

    private void buildIconsLayout(){
        final String [] icons = new String[] {"Flower","Fruit","Tree"};
        final AlertDialog.Builder iconsBuilder = new AlertDialog.Builder(getActivity());
        iconsBuilder.setTitle("Choose Station Icon");

        iconsBuilder.setSingleChoiceItems(icons, 0, new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int item) {
                // dismiss our dialog window
                iconDialogObject.cancel();
                switch(item){
                    case 0:
                        savedIconStation = Station.Type.FLOWER;
                        stationIconButton.setImageResource(R.drawable.flower);
                        break;
                    case 1:
                        savedIconStation = Station.Type.FRUIT;
                        stationIconButton.setImageResource(R.drawable.fruit);
                        break;
                    case 2:
                        savedIconStation = Station.Type.TREE;
                        stationIconButton.setImageResource(R.drawable.tree);
                        break;
                }
            }
        });

        iconDialogObject = iconsBuilder.create();

    }

    private void buildConfirmDialog(){
        AlertDialog.Builder confirmBuilder = new AlertDialog.Builder(getActivity());
        confirmBuilder.setTitle("Are you sure?");
        confirmBuilder.setMessage("Are you are you want to save the Station?");

        confirmBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // #2
                ArrayList<Station> stations = (ArrayList<Station>)getActivity().getIntent().getSerializableExtra(MainBoard.MAINBOARD_KEY_GET_STATIONS);
                for(int j = 0; j < stations.size(); j++){
                    Station s = stations.get(j);
                    if(s.getNode() == station.getNode()){
                        s.setType(savedIconStation);
                        s.setName(title.getText().toString());
                        s.setDesc(desc.getText().toString());
                    }
                }
                String new_data = null;
                new_data = Adapters.arrayListToJSON(stations);
                MyFileHandler.saveFile(getContext(),MainActivity.FILE_NAME, new_data);
                // End #2
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        confirmBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Do nothing here
            }
        });

        confirmDialogObject = confirmBuilder.create();
    }

}
