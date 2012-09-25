package com.apps4health.refpack.parsers;

import java.util.ArrayList;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Log;

import com.apps4health.refpack.R;

public class OidParser {

	private ArrayList<HashMap<String, String>> oidItems = new ArrayList<HashMap<String, String>>();

	public ArrayList<HashMap<String, String>> oidadapter(Context context) {
		try {
			XmlResourceParser xrp = context.getResources().getXml(R.xml.oids);
			xrp.next();

			int eventType = xrp.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_DOCUMENT) {

				} else if (eventType == XmlPullParser.START_TAG) {
					if (xrp.getAttributeCount() > 0) {

						HashMap<String, String> map = new HashMap<String, String>();
						map.put("OID_ID", xrp.getAttributeValue(null, "oid"));
						map.put("OID_NAME", xrp.getAttributeValue(null, "name"));
						map.put("OID_DESC", xrp.getAttributeValue(null, "desc"));
						oidItems.add(map);
					}
				}
				eventType = xrp.next();
			}

		} catch (Exception e) {
			Log.e("Error: ", e.getMessage());
		}
		
		return oidItems;

	}

}
