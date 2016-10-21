package com.example.loc.adaptercustomer;

/*//Doc noi dung luc co ket noi internet------------------------------------------------------------
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv_Address, tv_Time, tv_Temperature, tv_CurentTemp, tv_HisTemp;
    RelativeLayout mh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_Address = (TextView) findViewById(R.id.textView_Address);
        tv_Time = (TextView) findViewById(R.id.textView_Time);
        tv_Temperature = (TextView) findViewById(R.id.textView_Temperature);
        tv_CurentTemp= (TextView) findViewById(R.id.textView_CurrentTemp);
        tv_HisTemp = (TextView) findViewById(R.id.textView_HisTemp);
        mh = (RelativeLayout) findViewById(R.id.activity_main_id);
        mh.setBackgroundResource(R.drawable.manhinhchinh);

        //Set text dia diem
        tv_Address.setText("Hanoi");
        tv_Address.setTextColor(Color.WHITE);
        tv_Address.setTextSize(22);
        //Set text time
        tv_Time.setText("Mon, 10/10/2016");
        tv_Time.setTextColor(Color.WHITE);
        tv_Time.setTextSize(22);
        //Set text nhiet do
        tv_Temperature.setText("Temperature");
        tv_Temperature.setTextSize(30);
        tv_Temperature.setTextColor(Color.WHITE);
        //Set text Temp
        tv_HisTemp.setText("TEMP");
        tv_HisTemp.setTextColor(Color.WHITE);
        tv_HisTemp.setTextSize(30);
        tv_HisTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mh2 = new Intent(MainActivity.this, ManHinh3.class);
                startActivity(mh2);

            }
        });


        Bundle bd = getIntent().getExtras();
        if(bd!=null){
            String ten = bd.getString("Temp");
            tv_CurentTemp.setText(ten);
            tv_CurentTemp.setTextSize(100);
            tv_CurentTemp.setTextColor(Color.WHITE);
        }
    }

}
//-----------------------------------------------------------------------------------------------*/



//Doc noi dung luc khong co internet--------------------------------------------------------------

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv_Address, tv_Time, tv_Temperature, tv_CurentTemp, tv_HisTemp;
    RelativeLayout mh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_Address = (TextView) findViewById(R.id.textView_Address);
        tv_Time = (TextView) findViewById(R.id.textView_Time);
        tv_Temperature = (TextView) findViewById(R.id.textView_Temperature);
        tv_CurentTemp= (TextView) findViewById(R.id.textView_CurrentTemp);
        tv_HisTemp = (TextView) findViewById(R.id.textView_HisTemp);
        mh = (RelativeLayout) findViewById(R.id.activity_main_id);
        mh.setBackgroundResource(R.drawable.manhinhchinh);

        //Xoa thanh actionbar(thanh ghi tên project)
        android.support.v7.app.ActionBar AB=getSupportActionBar();
        AB.hide();

        //Set text dia diem
        tv_Address.setText("Hanoi");
        tv_Address.setTextColor(Color.WHITE);
        tv_Address.setTextSize(22);
        //Set text time
        tv_Time.setText("Tue, 18/10/2016");
        tv_Time.setTextColor(Color.WHITE);
        tv_Time.setTextSize(22);
        //Set text nhiet do
        tv_Temperature.setText("Temperature");
        tv_Temperature.setTextSize(30);
        tv_Temperature.setTextColor(Color.WHITE);
        //Set text Temp
        tv_HisTemp.setText("TEMP");
        tv_HisTemp.setTextColor(Color.WHITE);
        tv_HisTemp.setTextSize(30);
        tv_HisTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mh2 = new Intent(MainActivity.this, ManHinh3.class);
                startActivity(mh2);
            }
        });


        Bundle bd = getIntent().getExtras();
        if(bd!=null){
            String ten = bd.getString("Temp");
            tv_CurentTemp.setText(ten);
            tv_CurentTemp.setTextSize(100);
            tv_CurentTemp.setTextColor(Color.WHITE);
        }
    }

}
//-----------------------------------------------------------------------------------------------