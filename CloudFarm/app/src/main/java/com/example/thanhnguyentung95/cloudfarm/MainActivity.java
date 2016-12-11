package com.example.thanhnguyentung95.cloudfarm;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import adapters.Adapters;
import iohandler.MyFileHandler;
import objectlist.Node;
import objectlist.Station;

public class MainActivity extends AppCompatActivity {

    public static final String FILE_NAME = "old_data.txt";
    public static final String MAIN_KEY_GET_STATIONS = "cloudfarm.mainactivity stations";
    public static final String MAINBOARD_KEY_GET_STATION = "cloudfarm.mainboard station";
    public static final String STATION_FRAGMENT_TO_LOAD_EXTRA = "cloudfarm.mainboard fragment_to_load";
    public enum FragmentToLaunch { VIEW, EDIT}

    private ProgressBar main_act_pb;
    private TextView main_act_load_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ProgressBar initialization
        main_act_pb = (ProgressBar)findViewById(R.id.main_act_prog);
        main_act_pb.setVisibility(View.GONE);
        main_act_pb.getIndeterminateDrawable().setColorFilter(0xFFFF0000, android.graphics.PorterDuff.Mode.MULTIPLY);

        // TextView initialization
        main_act_load_tv = (TextView)findViewById(R.id.main_act_load);
        main_act_load_tv.setVisibility(View.GONE);
        main_act_load_tv.setTextColor(Color.GREEN);
        main_act_load_tv.setText("Loading");
//        setTitle(R.string.mainActivity);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // execute after 1s
                main_act_load_tv.setVisibility(View.VISIBLE);
                main_act_pb.setVisibility(View.VISIBLE);

                // Start to make color
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // execute after 1s
                        Intent intent = new Intent(getApplicationContext(),MainBoard.class);
                        startActivity(intent);
                        ArrayList<Node> node_arr = loadNode();
                        ArrayList<Station> station_arr = loadStation();
                        ArrayList<Station> station_arr_on = new ArrayList<>();

                        // Handle station_arr & station_arr
                        int node = 0;
                        for(int i = 0; i < node_arr.size(); i++){
                            node = node_arr.get(i).getNode();
                            for(int j = 0; j < node_arr.size(); j++){
                                if(node == station_arr.get(j).getNode()){
                                    station_arr_on.add(station_arr.get(j));
                                    break;
                                }
                                if(j == node_arr.size() - 1){ // exist new node
                                    Station s = new Station();
                                    s.setNode(node);
                                    station_arr_on.add(s);
                                }
                            }
                        }
                        // Display online stations
                        Intent main_act_intent = new Intent(getApplicationContext(),MainBoard.class);
                        main_act_intent.putExtra(MAIN_KEY_GET_STATIONS,station_arr_on);

                        startActivity(main_act_intent);
                    }
                }, 500);
                // End make color
            }
        }, 500);
    }

    // Assume that List of stations is a JSON, ex : [{"node":"1"},{"node":"2"}]
    private ArrayList loadNode(){
        String data;
        ArrayList arr = null;
        try {
            data = "[{\"node\":\"1\"}," +
                    "{\"node\":\"2\"}," +
                    "{\"node\":\"3\"}," +
                    "{\"node\":\"4\"}," +
                    "{\"node\":\"5\"}," +
                    "{\"node\":\"6\"}," +
                    "{\"node\":\"7\"}," +
                    "{\"node\":\"8\"}," +
                    "{\"node\":\"9\"}" +
//                    "{\"node\":\"100\"}" +
                    "]";
            arr = Adapters.stringToNodeArray(data);
        } catch(Exception e){
            e.printStackTrace();
        }
        return arr;
    }

    // Assume that List of stations is a JSON, ex : [{"node":"1","pic":"","name":"",desc:""}]

    private ArrayList loadStation(){
        String data;
        ArrayList arr = null;
        try{
            // #1
//            data = "[{\"node\":\"1\",\"type\":\"tree\",\"name\":\"No name 1\",desc:\"No 1\"}," +
//                    "{\"node\":\"2\",\"type\":\"fruit\",\"name\":\"No name 2 \",desc:\"No 2\"}," +
//                    "{\"node\":\"3\",\"type\":\"tree\",\"name\":\"No name 3\",desc:\"No 3\"}," +
//                    "{\"node\":\"4\",\"type\":\"plant\",\"name\":\"No name 4\",desc:\"No 4\"}," +
//                    "{\"node\":\"5\",\"type\":\"fruit\",\"name\":\"No name 5\",desc:\"No 5\"}," +
//                    "{\"node\":\"6\",\"type\":\"flower\",\"name\":\"No name 6\",desc:\"No 6\"}," +
//                    "{\"node\":\"7\",\"type\":\"tree\",\"name\":\"No name 7\",desc:\"No 7\"}," +
//                    "{\"node\":\"8\",\"type\":\"fruit\",\"name\":\"No name 8\",desc:\"No 8\"}," +
//                    "{\"node\":\"9\",\"type\":\"flower\",\"name\":\"No name 9\",desc:\"No 9\"}," +
//                    "{\"node\":\"10\",\"type\":\"tree\",\"name\":\"No name 10\",desc:\"No 10\"}," +
//                    "]";
//            MyFileHandler.saveFile(getApplicationContext(), FILE_NAME, data);
            // End #1
            // # 2
            data = MyFileHandler.readFile(getApplicationContext(), FILE_NAME);
            // End #2
            arr = Adapters.stringToStationArray(data);
        } catch(Exception e){
            e.printStackTrace();
        }
        return arr;
    }

}