package com.example.thanhnguyentung95.cloudfarm;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import adapters.CO2Adapter;
import adapters.HumiAdapter;
import adapters.LightAdapter;
import adapters.TempAdapter;

public class StationInfo extends AppCompatActivity {

    TabHost tabHost;
    static Context mContext;
    static final String FILE_NAME = "old_data.txt";
    ListView lvThongSoAll, lvThongSoTemp, lvThongSoLight, lvThongSoHumi, lvThongSoCO2;
    RelativeLayout mhp;
    TextView tvAll, tvChartTemp, tvListTemp, tvChartLight, tvListLight, tvChartHumi, tvListHumi, tvChartCO2, tvListCO2;

    BarChart barChartTemp, barChartLight, barChartHumi, barChartCO2;
    ArrayList<BarEntry> barEntriesTemp = new ArrayList<>();
    ArrayList<BarEntry> barEntriesLight = new ArrayList<>();
    ArrayList<BarEntry> barEntriesHumi = new ArrayList<>();
    ArrayList<BarEntry> barEntriesCO2 = new ArrayList<>();
    ArrayList<String> theDates = new ArrayList<>();
    BarDataSet barDataSetTemp = new BarDataSet(barEntriesTemp,"Temperature(oC)");
    BarDataSet barDataSetLight = new BarDataSet(barEntriesLight,"Light(lx)");
    BarDataSet barDataSetHumi = new BarDataSet(barEntriesHumi,"Humidity(%)");
    BarDataSet barDataSetCO2 = new BarDataSet(barEntriesCO2,"CO2(ug/m3)");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_info);

        TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();
        //Set text trong cac Tab
        tvAll =(TextView) findViewById(R.id.textViewAll);
        tvAll.setTextColor(Color.WHITE);
        tvChartTemp =(TextView) findViewById(R.id.textViewChartTemp);
        tvChartTemp.setTextColor(Color.WHITE);
        tvListTemp =(TextView) findViewById(R.id.textViewListTemp);
        tvListTemp.setTextColor(Color.WHITE);
        tvChartLight =(TextView) findViewById(R.id.textViewChartLight);
        tvChartLight.setTextColor(Color.WHITE);
        tvListLight =(TextView) findViewById(R.id.textViewListLight);
        tvListLight.setTextColor(Color.WHITE);
        tvChartHumi =(TextView) findViewById(R.id.textViewChartHumi);
        tvChartHumi.setTextColor(Color.WHITE);
        tvListHumi =(TextView) findViewById(R.id.textViewListHumi);
        tvListHumi.setTextColor(Color.WHITE);
        tvChartCO2 =(TextView) findViewById(R.id.textViewChartCO2);
        tvChartCO2.setTextColor(Color.WHITE);
        tvListCO2 =(TextView) findViewById(R.id.textViewListCO2);
        tvListCO2.setTextColor(Color.WHITE);



        //Set cac tab trong TabHost
        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Tab One");
        spec.setContent(R.id.tab1);
        spec.setIndicator("All");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Tab Two");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Temp");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("Tab Three");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Light");
        host.addTab(spec);

        //Tab 4
        spec = host.newTabSpec("Tab Four");
        spec.setContent(R.id.tab4);
        spec.setIndicator("Humi");
        host.addTab(spec);

        //Tab 5
        spec = host.newTabSpec("Tab Five");
        spec.setContent(R.id.tab5);
        spec.setIndicator("CO2");
        host.addTab(spec);

        //--------------------------
        mContext = getApplicationContext();
        barChartTemp = (BarChart) findViewById(R.id.bargraphTemp);
        barChartLight = (BarChart) findViewById(R.id.bargraphLight);
        barChartHumi = (BarChart) findViewById(R.id.bargraphHumi);
        barChartCO2 = (BarChart) findViewById(R.id.bargraphCO2);

        lvThongSoAll = (ListView) findViewById(R.id.listview_ThongSoAll);
        lvThongSoTemp = (ListView) findViewById(R.id.listview_ThongSoTemp);
        lvThongSoLight = (ListView) findViewById(R.id.listview_ThongSoLight);
        lvThongSoHumi = (ListView) findViewById(R.id.listview_ThongSoHumi);
        lvThongSoCO2 = (ListView) findViewById(R.id.listview_ThongSoCO2);


        ///Chen hinh anh vao man hinh
//        mhp =  (RelativeLayout) findViewById(R.id.activity_main);
//        mhp.setBackgroundResource(R.drawable.water4);



        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJSON().execute("http://lekienhust94.16mb.com/json.php");
            }
        });
    }

    //Tao class Read JSON
    class ReadJSON extends AsyncTask<String, Integer, String > {

        @Override
        protected String doInBackground(String... params) {
            String chuoi = docNoiDung_Tu_URL(params[0]);
            //Toast.makeText(ManHinh2.this, chuoi, Toast.LENGTH_LONG).show();
            try{
                saveData(chuoi);
            } catch(IOException e){
                // do something to alert that an error File occur
            }
            return chuoi;
        }

        @Override
        protected void onPostExecute(String s) {
            JSONArray mangJSON;
            ArrayList<Parameter> mangThongSoAll = new ArrayList<Parameter>();
            ArrayList<TempPara> mangThongSoTemp = new ArrayList<TempPara>();
            ArrayList<LightPara> mangThongSoLight = new ArrayList<LightPara>();
            ArrayList<HumiPara> mangThongSoHumi = new ArrayList<HumiPara>();
            ArrayList<CO2Para> mangThongSoCO2 = new ArrayList<CO2Para>();

            try {
                mangJSON = new JSONArray(s);
                for (int i = mangJSON.length()-1; i >= 0; i--){
                    mangJSON.getJSONObject(i);
                    JSONObject json = mangJSON.getJSONObject(i);
                    //Add tat ca thong so cho tab All trong Tabhost
                    mangThongSoAll.add(new Parameter(
                            json.getString("time"),
                            json.getString("tem"),
                            json.getString("humi"),
                            json.getString("light"),
                            json.getString("co2")));
                    //Add Tem cho tab Tem trong Tabhost
                    mangThongSoTemp.add(new TempPara(
                            json.getString("time"),
                            json.getString("tem")));
                    //Add Light cho tab Light trong Tabhost
                    mangThongSoLight.add(new LightPara(
                            json.getString("time"),
                            json.getString("light")));
                    //Add Humi cho tab Humi trong Tabhost
                    mangThongSoHumi.add(new HumiPara(
                            json.getString("time"),
                            json.getString("humi")));
                    //Add CO2 cho tab CO2 trong Tabhost
                    mangThongSoCO2.add(new CO2Para(
                            json.getString("time"),
                            json.getString("co2")));

                    //Add Temp vao chart co 7 gia tri
                    if(i==(mangJSON.length()- 1)){
                        barEntriesTemp.add(new BarEntry(Float.valueOf(json.getString("tem")),0));}
                    if(i==(mangJSON.length()- 2)){
                        barEntriesTemp.add(new BarEntry(Float.valueOf(json.getString("tem")),1));}
                    if(i==(mangJSON.length()- 3)){
                        barEntriesTemp.add(new BarEntry(Float.valueOf(json.getString("tem")),2));}
                    if(i==(mangJSON.length()- 4)){
                        barEntriesTemp.add(new BarEntry(Float.valueOf(json.getString("tem")),3));}
                    if(i==(mangJSON.length()- 5)){
                        barEntriesTemp.add(new BarEntry(Float.valueOf(json.getString("tem")),4));}
                    if(i==(mangJSON.length()- 6)){
                        barEntriesTemp.add(new BarEntry(Float.valueOf(json.getString("tem")),5));}
                    if(i==(mangJSON.length()- 7)){
                        barEntriesTemp.add(new BarEntry(Float.valueOf(json.getString("tem")),6));}

                    //Add Light vao chart co 7 gia tri
                    if(i==(mangJSON.length()- 1)){
                        barEntriesLight.add(new BarEntry(Float.valueOf(json.getString("light")),0));}
                    if(i==(mangJSON.length()- 2)){
                        barEntriesLight.add(new BarEntry(Float.valueOf(json.getString("light")),1));}
                    if(i==(mangJSON.length()- 3)){
                        barEntriesLight.add(new BarEntry(Float.valueOf(json.getString("light")),2));}
                    if(i==(mangJSON.length()- 4)){
                        barEntriesLight.add(new BarEntry(Float.valueOf(json.getString("light")),3));}
                    if(i==(mangJSON.length()- 5)){
                        barEntriesLight.add(new BarEntry(Float.valueOf(json.getString("light")),4));}
                    if(i==(mangJSON.length()- 6)){
                        barEntriesLight.add(new BarEntry(Float.valueOf(json.getString("light")),5));}
                    if(i==(mangJSON.length()- 7)){
                        barEntriesLight.add(new BarEntry(Float.valueOf(json.getString("light")),6));}

                    //Add humi vao chart co 7 gia tri
                    if(i==(mangJSON.length()- 1)){
                        barEntriesHumi.add(new BarEntry(Float.valueOf(json.getString("humi")),0));}
                    if(i==(mangJSON.length()- 2)){
                        barEntriesHumi.add(new BarEntry(Float.valueOf(json.getString("humi")),1));}
                    if(i==(mangJSON.length()- 3)){
                        barEntriesHumi.add(new BarEntry(Float.valueOf(json.getString("humi")),2));}
                    if(i==(mangJSON.length()- 4)){
                        barEntriesHumi.add(new BarEntry(Float.valueOf(json.getString("humi")),3));}
                    if(i==(mangJSON.length()- 5)){
                        barEntriesHumi.add(new BarEntry(Float.valueOf(json.getString("humi")),4));}
                    if(i==(mangJSON.length()- 6)){
                        barEntriesHumi.add(new BarEntry(Float.valueOf(json.getString("humi")),5));}
                    if(i==(mangJSON.length()- 7)){
                        barEntriesHumi.add(new BarEntry(Float.valueOf(json.getString("humi")),6));}

                    //Add CO2 vao chart co 7 gia tri
                    if(i==(mangJSON.length()- 1)){
                        barEntriesCO2.add(new BarEntry(Float.valueOf(json.getString("co2")),0));}
                    if(i==(mangJSON.length()- 2)){
                        barEntriesCO2.add(new BarEntry(Float.valueOf(json.getString("co2")),1));}
                    if(i==(mangJSON.length()- 3)){
                        barEntriesCO2.add(new BarEntry(Float.valueOf(json.getString("co2")),2));}
                    if(i==(mangJSON.length()- 4)){
                        barEntriesCO2.add(new BarEntry(Float.valueOf(json.getString("co2")),3));}
                    if(i==(mangJSON.length()- 5)){
                        barEntriesCO2.add(new BarEntry(Float.valueOf(json.getString("co2")),4));}
                    if(i==(mangJSON.length()- 6)){
                        barEntriesCO2.add(new BarEntry(Float.valueOf(json.getString("co2")),5));}
                    if(i==(mangJSON.length()- 7)){
                        barEntriesCO2.add(new BarEntry(Float.valueOf(json.getString("co2")),6));}

                }

                //Do data vao chart
                theDates.add("Mon");
                theDates.add("Tue");
                theDates.add("Web");
                theDates.add("Thur");
                theDates.add("Fri");
                theDates.add("Sar");
                theDates.add("Sun");

                //Do du lieu va mau cho barchartTemp
                BarData theDataTemp = new BarData(theDates,barDataSetTemp);
                barChartTemp.setData(theDataTemp);
                barChartTemp.setTouchEnabled(true);
                barChartTemp.setDragEnabled(true);
                barChartTemp.setScaleEnabled(true);
                barChartTemp.setBackgroundColor(Color.YELLOW);
                //Do du lieu va mau cho barchartLight
                BarData theDataLight = new BarData(theDates,barDataSetLight);
                barChartLight.setData(theDataLight);
                barChartLight.setTouchEnabled(true);
                barChartLight.setDragEnabled(true);
                barChartLight.setScaleEnabled(true);
                barChartLight.setBackgroundColor(Color.YELLOW);
                //Do du lieu va mau cho barchartHumi
                BarData theDataHumi = new BarData(theDates,barDataSetHumi);
                barChartHumi.setData(theDataHumi);
                barChartHumi.setTouchEnabled(true);
                barChartHumi.setDragEnabled(true);
                barChartHumi.setScaleEnabled(true);
                barChartHumi.setBackgroundColor(Color.YELLOW);
                //Do du lieu va mau cho barchartCO2
                BarData theDataCO2 = new BarData(theDates,barDataSetCO2);
                barChartCO2.setData(theDataCO2);
                barChartCO2.setTouchEnabled(true);
                barChartCO2.setDragEnabled(true);
                barChartCO2.setScaleEnabled(true);
                barChartCO2.setBackgroundColor(Color.YELLOW);

                //Xuat mangThong1 so ra tab All trong Tabhost
                ListAdapter adapter = new ListAdapter(
                        StationInfo.this,
                        R.layout.activity_list_parameter,
                        mangThongSoAll
                );
                lvThongSoAll.setAdapter(adapter);
                //Xuat mangThong1 so ra tab Temp trong Tabhost
                TempAdapter adapter2 = new TempAdapter(
                        StationInfo.this,
                        R.layout.activity_temp_parameter,
                        mangThongSoTemp
                );
                lvThongSoTemp.setAdapter(adapter2);
                //Xuat mangThong1 so ra tab Light trong Tabhost
                LightAdapter adapter3 = new LightAdapter(
                        StationInfo.this,
                        R.layout.activity_temp_parameter,
                        mangThongSoLight
                );
                lvThongSoLight.setAdapter(adapter3);
                //Xuat mangThong1 so ra tab Humi trong Tabhost
                HumiAdapter adapter4 = new HumiAdapter(
                        StationInfo.this,
                        R.layout.activity_temp_parameter,
                        mangThongSoHumi
                );
                lvThongSoHumi.setAdapter(adapter4);
                //Xuat mangThong1 so ra tab CO2 trong Tabhost
                CO2Adapter adapter5 = new CO2Adapter(
                        StationInfo.this,
                        R.layout.activity_temp_parameter,
                        mangThongSoCO2
                );
                lvThongSoCO2.setAdapter(adapter5);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    //Doc noi dung  tu Internet
    private static String docNoiDung_Tu_URL(String theUrl)
    {
        boolean check = false;
        StringBuilder content = new StringBuilder();

        try
        {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }

        catch(Exception e)
        {
            e.printStackTrace();
            try{
                return loadData();
            } catch(IOException ee){
                // do something to alert that an error File occur
            }
        }
        return content.toString();
    }

    private void saveData(String data) throws IOException {
        Writer writer = null;
        try {
            OutputStream out = mContext.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
//            writer.write(data.toString());
            writer.write(data);

        } finally {
            if (writer != null)
                writer.close();
        }
    }

    private static String loadData()throws IOException{
        StringBuilder jsonString = null;
        BufferedReader reader = null;
        try {
            // Open and read the file into a StringBuilder
            InputStream in = mContext.openFileInput(FILE_NAME);
            reader = new BufferedReader(new InputStreamReader(in));
            jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
        } finally {
            if (reader != null)
                reader.close();
        }
        return jsonString.toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
