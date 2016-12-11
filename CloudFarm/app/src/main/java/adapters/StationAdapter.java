package adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thanhnguyentung95.cloudfarm.R;

import java.util.ArrayList;

import objectlist.Station;

/**
 * Created by thanhnguyentung95 on 05/12/2016.
 */

public class StationAdapter extends ArrayAdapter<Station> {

    public static class ViewHolder{
        TextView stationTitle;
        TextView stationDesc;
        ImageView stationIcon;
    }

    public StationAdapter(Context context, ArrayList<Station> stations){
            super(context, 0, stations);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        Station station = getItem(position);

        ViewHolder viewHolder;

        // Checking if an existing view is being reused
        // , otherwise inflate a new view from custom row layout
        if(convertView == null){
            // If we don't have a view that is being reused create one, and make sure you creat a
            // view holder along with it to save our data to.
            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row, parent, false);

            viewHolder.stationTitle = (TextView)convertView.findViewById(R.id.stationTitle);
            viewHolder.stationDesc = (TextView)convertView.findViewById(R.id.stationDesc);
            viewHolder.stationIcon = (ImageView)convertView.findViewById(R.id.stationIcon);

            // use set tag to remember our view holder which is holding our references to our widgets
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Grab references of views so we can populate them with specific note row data
        viewHolder.stationTitle.setText(station.getName());
        viewHolder.stationDesc.setText(station.getDesc());
        viewHolder.stationIcon.setImageResource(station.getAssociatedDrawable());

        return convertView;
    }
}
