package adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.thanhnguyentung95.cloudfarm.LightPara;
import com.example.thanhnguyentung95.cloudfarm.R;

import java.util.ArrayList;

/**
 * Created by LOC on 12/11/2016.
 */

public class LightAdapter extends ArrayAdapter<LightPara> {
    public LightAdapter(Context context, int resource, ArrayList<LightPara> stations) {
        super(context, resource, stations);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup patent){

        View view = convertView;
        if(view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.activity_temp_parameter, null);
        }
        LightPara p = getItem(position);
        if(p != null){
            TextView txt = (TextView) view.findViewById(R.id.textTimeTemp);
            txt.setText(p.Time);
            txt.setTextColor(Color.WHITE);
            TextView txt2 = (TextView) view.findViewById(R.id.textParaTemp);
            txt2.setText(String.valueOf(p.Light)+"lx");
            txt2.setTextColor(Color.WHITE);
        }
        return view;
    }
}
