package adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.thanhnguyentung95.cloudfarm.CO2Para;
import com.example.thanhnguyentung95.cloudfarm.R;

import java.util.ArrayList;

/**
 * Created by LOC on 12/11/2016.
 */

public class CO2Adapter extends ArrayAdapter<CO2Para> {
    public CO2Adapter(Context context, int resource, ArrayList<CO2Para>stations){
        super(context,resource,stations);
    }
    @Override
    public View getView(int positon, View convertView, ViewGroup patent){
        View view = convertView;
        if(view==null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.activity_temp_parameter,null);
        }
        CO2Para p = getItem(positon);
        if(p!=null){
            TextView txt = (TextView) view.findViewById(R.id.textTimeTemp);
            txt.setText(p.Time);
            txt.setTextColor(Color.WHITE);
            TextView txt2 = (TextView) view.findViewById(R.id.textParaTemp);
            txt2.setText(String.valueOf(p.CO2)+"ug/m3");
            txt2.setTextColor(Color.WHITE);
        }
        return view;
        }
    }

