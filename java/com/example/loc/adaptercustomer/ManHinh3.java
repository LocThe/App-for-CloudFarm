package com.example.loc.adaptercustomer;
//Doc noi dung luc co ket noi intenet---------------------------------------------------------------

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


public class ManHinh3  extends AppCompatActivity {
    ListView lvThongSo;
    static String k;
    RelativeLayout mhp;
    TextView tv_Back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh3);

        //Hien thi ListThongso: Thoigian, Nhietdo

        lvThongSo = (ListView) findViewById(R.id.listview_ThongSo);
        mhp =  (RelativeLayout) findViewById(R.id.activity_manhinh3) ;
        mhp.setBackgroundResource(R.drawable.moon2);

        //Xoa thanh actionbar(thanh ghi tên project)
        android.support.v7.app.ActionBar AB=getSupportActionBar();
        AB.hide();


        //Xu ly su kien khi bam TextView Back
        tv_Back = (TextView) findViewById(R.id.textView_Back);
        tv_Back.setText("BACK");
        tv_Back.setTextSize(30);
        tv_Back.setTextColor(Color.WHITE);
        tv_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mh1 = new Intent(ManHinh3.this, MainActivity.class);
                mh1.putExtra("Temp", k);
                startActivity(mh1);

            }
        });

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJSON().execute("http://lekienhust94.mybluemix.net/jsonOutput.php");
            }
        });
    }


    //Tao class Read JSON
    class ReadJSON extends AsyncTask<String, Integer, String > {

        @Override
        protected String doInBackground(String... params) {
            String chuoi = docNoiDung_Tu_URL(params[0]);
            return chuoi;
        }

        @Override
        protected void onPostExecute(String s) {
            JSONArray mangJSON;
           ArrayList<ThongSo> mangThongSo = new ArrayList<ThongSo>();

            try {
                 mangJSON = new JSONArray(s);
                for (int i = mangJSON.length() - 1; i >= 0; i--) {
                      mangJSON.getJSONObject(i);
                    JSONObject time = mangJSON.getJSONObject(i);
                    mangThongSo.add(new ThongSo(
                            time.getString("time"),
                            time.getString("tem"),
                            time.getString("humi"),
                            time.getString("light"),
                            time.getString("c02")));
                    if (i == (mangJSON.length() - 1))
                        k = time.getString("tem");
                }
                ListAdapter adapter = new ListAdapter(
                        ManHinh3.this,
                        R.layout.activity_list_parameter,
                        mangThongSo
                );
                lvThongSo.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    //Doc noi dung  tu Internet
    private static String docNoiDung_Tu_URL(String theUrl)
    {
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
        }
        return content.toString();
    }
}





//------------------------------------------------------------------------------------------------*/
//                //Tao file cache
//                try {
//                    File pathCacheDir = getCacheDir();
//                    String strCacheFileName = "myCacheFile.cache";
//                    File newCacheFile = new File(pathCacheDir, strCacheFileName);
//                    newCacheFile.createNewFile();
//                    FileOutputStream foCache = new FileOutputStream(newCacheFile.getAbsolutePath());
//                    foCache.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//    //Doc file cache
//    File pathCacheDir = getCacheDir();
//    String strCacheFileName = "myCacheFile.cache";
//    File newCacheFile = new File(pathCacheDir, strCacheFileName);
//    ListAdapter adapter = new ListAdapter(
//            ManHinh3.this,
//            R.layout.activity_list_parameter,
//            mangThongSo
//    );
//lvThongSo.setAdapter(adapter);

/*
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

//------------------------------------------------------------------------------------------------

public class ManHinh3  extends AppCompatActivity {
    ListView lvThongSo;
    static String k;
    RelativeLayout mhp;
    TextView tv_Back;
    JSONArray mangJSON;
    ArrayList<ThongSo> mangThongSo = new ArrayList<ThongSo>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh3);

        //Hien thi ListThongso: Thoigian, Nhietdo
        lvThongSo = (ListView) findViewById(R.id.listview_ThongSo);
        mhp = (RelativeLayout) findViewById(R.id.activity_manhinh3);
        mhp.setBackgroundResource(R.drawable.moon2);


        //Xu ly su kien khi bam TextView Back
        tv_Back = (TextView) findViewById(R.id.textView_Back);
        tv_Back.setText("BACK");
        tv_Back.setTextSize(30);
        tv_Back.setTextColor(Color.WHITE);
        tv_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mh1 = new Intent(ManHinh3.this, MainActivity.class);
                mh1.putExtra("Temp", k);
                startActivity(mh1);
            }
        });
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJSON().execute("http://lekienhust94.mybluemix.net/jsonOutput.php");
                ReadJSONdadow hienThi = new ReadJSONdadow();
                hienThi.docMang();
            }
        });
    }

    //Tao class ReadJSON de doc du lieu tu internet
    class ReadJSON extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {
            String chuoi = docNoiDung_Tu_URL(params[0]);//params la dia chi http://lekienhust94.mybluemix.net/jsonOutput.php
            return chuoi;
        }
        @Override
        protected void onPostExecute(String chuoi) {
            try {
                File pathCacheDir = getCacheDir();
                String strCacheFileName = "myCacheFile.cache";
                String strFileContents = chuoi;
                File newCacheFile = new File(pathCacheDir, strCacheFileName);
                newCacheFile.createNewFile();
                FileOutputStream foCache = new FileOutputStream(newCacheFile.getAbsolutePath());
                foCache.write(strFileContents.getBytes());
                foCache.close();
                //Kiem tra foCache duoc ket qua la dia chi....=>chua hieu?????
                //Toast.makeText(ManHinh3.this, foCache.toString(), Toast.LENGTH_LONG).show();
//Hàm getAbsolutePath() lấy đường dẫn đến file đã tạo



//Doc mang string de luu vao bo dem va doc no ra khi thuc hien hoat dong doc du lieu
//                CharArrayWriter WJS = new CharArrayWriter();
//                String string = chuoi;
//                for (int k = 0; k < string.length(); k++) {
//                    WJS.write(string.charAt(k));
//                }
//
//                CharArrayReader RJS;
//                RJS = new CharArrayReader(WJS.toCharArray());
//                int a= 0;
//                StringBuffer sbl = new StringBuffer();
//                while((a=RJS.read())!=1)
//                    sbl.append((char)a);
//                string=sbl.toString();
//                Toast.makeText(ManHinh3.this, string, Toast.LENGTH_LONG).show();
//--------------------------------------------------------------------------------------------------

                //Toast.makeText(ManHinh3.this, chuoi, Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Doc noi dung  tu Internet
    private static String docNoiDung_Tu_URL(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    //Doc chuoi JSON da dowload
    class ReadJSONdadow {
        protected void docMang() {
//            File pathCacheDir = getCacheDir();
//            String strCacheFileName = "myCacheFile.cache";
//            File newCacheFile = new File(pathCacheDir, strCacheFileName);
            //Toast.makeText(ManHinh3.this, newCacheFile.toString(), Toast.LENGTH_LONG).show();

//            JSONArray mangJSON2 = null;
//            JSONObject time = null;
//            try {
//                mangJSON2 = new JSONArray(newCacheFile);//Them chuoi ma doc duoc tu cache
//                for(int j = mangJSON2.length()-1;j >= 0;j++){
//                    time = mangJSON2.getJSONObject(j);
//                    mangThongSo.add(new ThongSo(
//                            time.getString("time"),
//                            time.getString("tem"),
//                            time.getString("humi"),
//                            time.getString("light"),
//                            time.getString("c02")));
//                    if (j == (mangJSON2.length() - 1))
//                        k = time.getString("tem");
//                }
//                ListAdapter adapter = new ListAdapter(
//                        ManHinh3.this,
//                        R.layout.activity_list_parameter,
//                        mangThongSo
//                );
//                lvThongSo.setAdapter(adapter);
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            Toast.makeText(ManHinh3.this, "huhu", Toast.LENGTH_LONG).show();

        }
    }

}


/*    //Lay toan bo file cache------------------------------------------------------------------------
    public void loadAllCache() {
        File pathCacheDir = getCacheDir();
        File[] listCache = pathCacheDir.listFiles();
        for (File f : listCache) {
            //process f here
            f.delete();
        }
    }
    //Doc file cache co ten File myCacheFile
        public void readCache() {

            File pathCacheDir = getCacheDir();
            String strCacheFileName = "myCacheFile.cache";
            File newCacheFile = new File(pathCacheDir, strCacheFileName);

            Scanner sc = new Scanner(newCacheFile);
            String data="";
            while(sc.hasNext())
            {
                data+=sc.nextLine()+"\n";
            }
            editdata.setText(data);
            sc.close();
    }


    //Tao file cache
        public void createCache(){
            try {
                File pathCacheDir = getCacheDir();
                String strCacheFileName = "myCacheFile.cache";
                String strFileContents = editdata.getText()+"";
                File newCacheFile = new File(pathCacheDir, strCacheFileName);
                newCacheFile.createNewFile();
                FileOutputStream foCache = new FileOutputStream(newCacheFile.getAbsolutePath());
                foCache.write(strFileContents.getBytes());
                foCache.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


}*/
