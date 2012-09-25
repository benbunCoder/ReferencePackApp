package com.apps4health.refpack.parsers;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Context;
import android.util.Log;

public class V2VocabParser {

	private ArrayList<HashMap<String, String>> dataItems = new ArrayList<HashMap<String, String>>();
	private String name;
	private String version;
	private String status;
	private String id;
	private String assetName;

	public V2VocabParser(String name, String version) {
		assetName = "v2/" + name + "-v" + version + ".xml";
	}

	public String getValueId() { return id;	}
	public String getValueStatus() { return status;	}
	public String getValueVersion() { return version;	}
	
	public ArrayList<HashMap<String, String>> dataadapter(Context context) {
		
		try {
				InputStream istr = context.getAssets().open(assetName);
				XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
	
				factory.setNamespaceAware(true);
				XmlPullParser xrp = factory.newPullParser();
				xrp.setInput(istr, "UTF-8");
				xrp.next();
				int eventType = xrp.getEventType();
	
				String code = "";
				String displayName = "";

				while (eventType != XmlPullParser.END_DOCUMENT) {
					if (eventType == XmlPullParser.START_TAG) {
	
						String tagName = xrp.getName().toString();

						if (tagName.equals("concept")) {
							code =  xrp.getAttributeValue(null, "code");
						}

						if (tagName.equals("displayName")) {	
							displayName = xrp.nextText();
							
							HashMap<String, String> map = new HashMap<String, String>();							
							map.put("NAME",displayName);
							map.put("CODE",code);
							dataItems.add(map);
							
							//Log.i("dn", code + " -- " + displayName);
						}
						
						if (tagName.equals("vocabulary")) {
							name = xrp.getAttributeValue(null, "name");
							version = xrp.getAttributeValue(null, "version");
							status = xrp.getAttributeValue(null, "status");
							id = xrp.getAttributeValue(null, "id");
							//Log.i("debug", name + ":" + version + ":" + status 	+ ":" + id);
						}
						
					}
					
					eventType = xrp.next();
				}
			} 
		catch (Exception e) {
			Log.e("Error: ", name);
		}
		return dataItems;
	}

}