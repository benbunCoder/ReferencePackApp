package com.apps4health.refpack.parsers;

import java.util.ArrayList;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;

import com.apps4health.refpack.R;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Log;

public class V3Parser {

	private ArrayList<HashMap<String, String>> v3Items = new ArrayList<HashMap<String, String>>();

	public ArrayList<HashMap<String, String>> v3adapter(Context context) {
		try {
			XmlResourceParser xrp = context.getResources().getXml(R.xml.v3xml);
			xrp.next();

			int eventType = xrp.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_DOCUMENT) {

				} else if (eventType == XmlPullParser.START_TAG) {
					if (xrp.getAttributeCount() > 0) {

						HashMap<String, String> map = new HashMap<String, String>();
						map.put("V3_NAME", xrp.getAttributeValue(null, "name"));
						map.put("V3_FULLNAME",xrp.getAttributeValue(null, "fullName"));
						map.put("V3_COUNT",xrp.getAttributeValue(null, "count"));
						map.put("V3_VERSION",xrp.getAttributeValue(null, "version"));
						v3Items.add(map);
					}
				}
				eventType = xrp.next();
			}

		} catch (Exception e) {
			Log.e("Error: ", e.getMessage());
		}

		return v3Items;

	}

}
