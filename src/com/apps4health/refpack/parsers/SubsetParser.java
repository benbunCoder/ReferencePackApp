package com.apps4health.refpack.parsers;

import java.util.ArrayList;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Log;

import com.apps4health.refpack.R;

public class SubsetParser {

	private ArrayList<HashMap<String, String>> subsetItems = new ArrayList<HashMap<String, String>>();

	public ArrayList<HashMap<String, String>> subsetadapter(Context context) {
		try {
			XmlResourceParser xrp = context.getResources().getXml(R.xml.subset);
			xrp.next();

			int eventType = xrp.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_DOCUMENT) {

				} else if (eventType == XmlPullParser.START_TAG) {
					if (xrp.getAttributeCount() > 0) {

						HashMap<String, String> map = new HashMap<String, String>();
						map.put("SUBSET_ID", xrp.getAttributeValue(null, "id"));
						map.put("SUBSET_NAME", xrp.getAttributeValue(null, "name"));
						map.put("SUBSET_COUNT", xrp.getAttributeValue(null, "memberCount"));
						subsetItems.add(map);
					}
				}
				eventType = xrp.next();
			}

		} catch (Exception e) {
			Log.e("Error: ", e.getMessage());
		}
		
		return subsetItems;

	}

}
