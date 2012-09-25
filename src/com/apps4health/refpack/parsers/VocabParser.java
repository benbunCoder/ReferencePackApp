package com.apps4health.refpack.parsers;

import java.util.ArrayList;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Log;

import com.apps4health.refpack.R;

public class VocabParser {

	private ArrayList<HashMap<String, String>> dataItems = new ArrayList<HashMap<String, String>>();
	
	private String name;
	private String version;
	private String status;
	private String id;
	
	private String assetName;
	
	public VocabParser(String name, String version) { 
		
		assetName = name + "-v" + version + ".xml";
		Log.e("WWW", assetName);
		
	}
	
	public ArrayList<HashMap<String, String>> dataadapter(Context context) {
		try {
			XmlResourceParser xrp = context.getResources().getXml(R.xml.oids);
			xrp.next();

			int eventType = xrp.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_DOCUMENT) {

				} else if (eventType == XmlPullParser.START_TAG) {
					if (xrp.getAttributeCount() > 1) {

						HashMap<String, String> map = new HashMap<String, String>();
						map.put("OID_ID", xrp.getAttributeValue(null, "oid"));
						map.put("OID_NAME", xrp.getAttributeValue(null, "name"));
						map.put("OID_DESC", xrp.getAttributeValue(null, "desc"));
						//oidItems.add(map);
					} else {
						
						name = xrp.getAttributeValue(null, "name");
						version = xrp.getAttributeValue(null, "version");
						status = xrp.getAttributeValue(null, "status");
						id = xrp.getAttributeValue(null, "id");
						Log.e("debug", name  + ":"+version+":"+status+":"+id);
					}
				}
				eventType = xrp.next();
			}

		} catch (Exception e) {
			Log.e("Error: ", e.getMessage());
		}
		
		return null;

	}

}
