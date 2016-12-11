package com.example.thanhnguyentung95.cloudfarm;

/**
 * Created by LOC on 12/10/2016.
 */

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.thanhnguyentung95.cloudfarm.Parameter;
import com.example.thanhnguyentung95.cloudfarm.R;

import java.util.List;

/**
 * Created by LOC on 12/10/2016.
 */

public class ListAdapter extends ArrayAdapter<Parameter> {

    public ListAdapter(Context context, int resource, List<Parameter> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view =  inflater.inflate(R.layout.activity_list_parameter, null);
        }
        Parameter p = getItem(position);
        if (p != null) {
            // Anh xa + Gan gia tri
            TextView txt = (TextView) view.findViewById(R.id.textViewTime);
            txt.setText(p.Time);
            txt.setTextColor(Color.WHITE);
            TextView txt2 = (TextView) view.findViewById(R.id.textViewTemp);
            txt2.setText(String.valueOf(p.Temp)+"oC");
            txt2.setTextColor(Color.WHITE);
            TextView txt3 = (TextView) view.findViewById(R.id.textViewLight);
            txt3.setText(String.valueOf(p.Light)+"lx");
            txt3.setTextColor(Color.WHITE);
            TextView txt4 = (TextView) view.findViewById(R.id.textViewHumi);
            txt4.setText(String.valueOf(p.Humi)+"%");
            txt4.setTextColor(Color.WHITE);
            TextView txt5 = (TextView) view.findViewById(R.id.textViewCO2);
            txt5.setText(String.valueOf(p.CO2)+"ug/m3");
            txt5.setTextColor(Color.WHITE);

            TextView txt6 = (TextView) view.findViewById(R.id.textTemp);
            txt6.setTextColor(Color.WHITE);
            TextView txt7 = (TextView) view.findViewById(R.id.textLight);
            txt7.setTextColor(Color.WHITE);
            TextView txt8 = (TextView) view.findViewById(R.id.textHumi);
            txt8.setTextColor(Color.WHITE);
            TextView txt9 = (TextView) view.findViewById(R.id.textCO2);
            txt9.setTextColor(Color.WHITE);

        }
        return view;
    }

}