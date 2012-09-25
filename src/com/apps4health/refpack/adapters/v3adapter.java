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

public class v3adapter extends SimpleAdapter {

	private LayoutInflater mLayoutInflater;
	private List<HashMap<String, String>> items;

	private int mLayout;

	public v3adapter(Context context, List<HashMap<String, String>> items,
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

			viewHolder.v3Version = (TextView) convertView
					.findViewById(R.id.tvV3Version);
			viewHolder.v3Name = (TextView) convertView
					.findViewById(R.id.tvV3Name);
			viewHolder.v3Count = (TextView) convertView
					.findViewById(R.id.tvV3Count);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.v3Version.setText(items.get(position).get("V3_VERSION"));
		viewHolder.v3Name.setText(items.get(position).get("V3_NAME"));

		int CountValue = Integer.parseInt(items.get(position).get("V3_COUNT"));

		Locale usaLocale = new Locale("en", "US");
		NumberFormat formatusa = DecimalFormat.getInstance(usaLocale);

		viewHolder.v3Count.setText(formatusa.format(CountValue));

		return convertView;
	}

	static class ViewHolder {
		public TextView v3Version;
		public TextView v3Name;
		public TextView v3Count;
	}
}
