package com.apps4health.refpack.adapters;

import java.util.HashMap;
import java.util.List;

import com.apps4health.refpack.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class oidadapter extends SimpleAdapter {

    private LayoutInflater mLayoutInflater;
    private List<HashMap<String, String>> items;

    private int mLayout;

    public oidadapter(Context context, List<HashMap<String, String>> items, int layout, String[] from, int[] to) {
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
            
            viewHolder.oidId   = (TextView) convertView.findViewById(R.id.tvOid);
            viewHolder.oidName = (TextView) convertView.findViewById(R.id.tvOidName);
            viewHolder.oidDesc = (TextView) convertView.findViewById(R.id.tvOidDesc);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.oidId.setText(items.get(position).get("OID_ID"));
        viewHolder.oidName.setText(items.get(position).get("OID_NAME"));
        viewHolder.oidDesc.setText(items.get(position).get("OID_DESC"));
        
        return convertView;
    }
    
    static class ViewHolder {
        public TextView oidId;
        public TextView oidName;
        public TextView oidDesc;
    }
}
