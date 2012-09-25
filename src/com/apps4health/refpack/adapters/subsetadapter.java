package com.apps4health.refpack.adapters;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import com.apps4health.refpack.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class subsetadapter extends SimpleAdapter {

    private LayoutInflater mLayoutInflater;
    private List<HashMap<String, String>> items;

    private int mLayout;

    public subsetadapter(Context context, List<HashMap<String, String>> items, int layout, String[] from, int[] to) {
    	super(context, items, layout, from, to);
        
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mLayout = layout;
        
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(mLayout, null);

            viewHolder = new ViewHolder();
            
            viewHolder.subsetId   = (TextView) convertView.findViewById(R.id.tvSubsetId);
            viewHolder.subsetName = (TextView) convertView.findViewById(R.id.tvSubsetName);
            viewHolder.subsetCount = (TextView) convertView.findViewById(R.id.tvSubsetCount);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.subsetId.setText(items.get(position).get("SUBSET_ID"));
        viewHolder.subsetName.setText(items.get(position).get("SUBSET_NAME"));
        
        int CountValue =Integer.parseInt(items.get(position).get("SUBSET_COUNT"));
        
        Locale usaLocale = new Locale("en", "US");
        NumberFormat formatusa = DecimalFormat.getInstance(usaLocale);
        
        viewHolder.subsetCount.setText(formatusa.format(CountValue));
        
        return convertView;
    }
    
    static class ViewHolder {
        public TextView subsetId;
        public TextView subsetName;
        public TextView subsetCount;
    }
}
