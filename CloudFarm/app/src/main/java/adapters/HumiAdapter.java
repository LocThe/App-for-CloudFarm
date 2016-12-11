package adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.thanhnguyentung95.cloudfarm.HumiPara;
import com.example.thanhnguyentung95.cloudfarm.R;

import java.util.ArrayList;

/**
 * Created by LOC on 12/11/2016.
 */

public class HumiAdapter extends ArrayAdapter<HumiPara> {

    public HumiAdapter(Context context, int resource, ArrayList<HumiPara>stations){
        super(context, resource, stations);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup patent){
        View view = convertView;
        if(view==null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view =inflater.inflate(R.layout.activity_temp_parameter,null);
        }
        HumiPara p = getItem(position);
        if(p!=null){
            TextView txt = (TextView) view.findViewById(R.id.textTimeTemp);
            txt.setText(p.Time);
            txt.setTextColor(Color.WHITE);
            TextView txt2 = (TextView) view.findViewById(R.id.textParaTemp);
            txt2.setText(String.valueOf(p.Humi)+"%");
            txt2.setTextColor(Color.WHITE);
        }
        return view;
    }

}
