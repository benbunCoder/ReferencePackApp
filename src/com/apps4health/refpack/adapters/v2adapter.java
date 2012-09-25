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

public class v2adapter extends SimpleAdapter {

	private LayoutInflater mLayoutInflater;
	private List<HashMap<String, String>> items;

	private int mLayout;

	public v2adapter(Context context, List<HashMap<String, String>> items,
			int layout, String[] from, int[] to) {
		super(context, items, layout, from, to);

		mLayoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mLayout = layout;

		this.items = items;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder viewHolder;

		if (convertView == null) {
			convertView = mLayoutInflater.inflate(mLayout, null);

			viewHolder = new ViewHolder();

			viewHolder.v2TableNumber = (TextView) convertView.findViewById(R.id.tvV2Table);
			viewHolder.v2Name = (TextView) convertView.findViewById(R.id.tvV2Name);
			viewHolder.v2Count = (TextView) convertView.findViewById(R.id.tvV2Count);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.v2TableNumber.setText(items.get(position).get("V2_TABLE"));
		viewHolder.v2Name.setText(items.get(position).get("V2_NAME"));

		int CountValue = Integer.parseInt(items.get(position).get("V2_COUNT"));

		Locale usaLocale = new Locale("en", "US");
		NumberFormat formatusa = DecimalFormat.getInstance(usaLocale);

		viewHolder.v2Count.setText(formatusa.format(CountValue));

		return convertView;
	}

	static class ViewHolder {
		public TextView v2TableNumber;
		public TextView v2Name;
		public TextView v2Count;
	}
}
