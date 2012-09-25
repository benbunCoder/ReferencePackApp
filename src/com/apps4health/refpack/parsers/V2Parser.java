package com.apps4health.refpack.parsers;

import java.util.ArrayList;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Log;

import com.apps4health.refpack.R;

public class V2Parser {

	private ArrayList<HashMap<String, String>> v2Items = new ArrayList<HashMap<String, String>>();

	public ArrayList<HashMap<String, String>> v2adapter(Context context) {
		try {
			XmlResourceParser xrp = context.getResources().getXml(R.xml.v2xml);
			xrp.next();

			int eventType = xrp.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_DOCUMENT) {

				} else if (eventType == XmlPullParser.START_TAG) {
					if (xrp.getAttributeCount() > 0) {

						HashMap<String, String> map = new HashMap<String, String>();
						map.put("V2_NAME", xrp.getAttributeValue(null, "name"));
						map.put("V2_FULLNAME",xrp.getAttributeValue(null, "fullName"));
						map.put("V2_COUNT",xrp.getAttributeValue(null, "count"));
						map.put("V2_VERSION",xrp.getAttributeValue(null, "version"));

						/*
						 * Get the table number from the full name.
						 */
						String[] temp = xrp.getAttributeValue(null, "fullName").split("-");
						map.put("V2_TABLE",temp[0]);

						v2Items.add(map);
					}
				}
				eventType = xrp.next();
			}

		} catch (Exception e) {
			Log.e("Error: ", e.getMessage());
		}
		return v2Items;
	}
}