package adapters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import objectlist.Node;
import objectlist.Station;

/**
 * Created by thanhnguyentung95 on 03/12/2016.
 */

public class Adapters {
    public static ArrayList stringToStationArray(String data) {
        ArrayList<Station> station_arr = new ArrayList<>();
        try {
            JSONArray json_arr = new JSONArray(data);
            for(int i = 0; i < json_arr.length(); i++){
                JSONObject json = json_arr.getJSONObject(i);
                Station station = new Station();
                station.setNode(json.getInt("node"));
                station.setName(json.getString("name"));
                station.setDesc(json.getString("desc"));
                station.setType(Station.stringToType(json.getString("type")));
                station_arr.add(station);
            }
        } catch (JSONException e) {
            // Handle bad JSON format
        }
        return station_arr;
    }

    public static ArrayList stringToNodeArray(String data){
        ArrayList<Node> node_arr = new ArrayList<>();
        try{
            JSONArray json_arr = new JSONArray(data);
            for(int i = 0; i < json_arr.length(); i++){
                JSONObject json = json_arr.getJSONObject(i);
                Node node = new Node();
                node.setNode(json.getInt("node"));
                node_arr.add(node);
            }
        } catch(JSONException e){
            // Handle bad JSON format
        }
        return node_arr;
    }

    public static String arrayListToJSON(ArrayList<Station> stations) {
        JSONArray json_array = new JSONArray();
        try {

        for(int i = 0; i < stations.size(); i++){
            Station station = stations.get(i);
            JSONObject json_object = new JSONObject();
            json_object.put("node",String.valueOf(station.getNode()));
            json_object.put("type",Station.typeToString(station.getType()));
            json_object.put("name",station.getName());
            json_object.put("desc",station.getDesc());

            json_array.put(json_object);
            }
        } catch(JSONException e){
            System.out.println("JSON Exception");
        }
        return json_array.toString();
    }
}
