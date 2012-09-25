package com.apps4health.refpack.parsers;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Context;
import android.util.Log;

public class SNOMEDVocabParser {

	private ArrayList<HashMap<String, String>> dataItems = new ArrayList<HashMap<String, String>>();
	private String name;
	private String id;
	private String setid;
	private String version;
	private String status;
	private String type;
	private String originalSubsetId;
	private String assetName;
	
	private String concept;
	private String pt;
	private String fsn;
	private String sym;
	
	private Boolean firstConcept;

	public SNOMEDVocabParser(String name) {
		assetName = "SCT/V" + name + ".XML";
	}

	//public String getValueOriginalSubsetId() { return originalSubsetId;	}
	public String getValueStatus() { return status;	}
	public String getValueType() { return type;	}
	public String getValueVersion() { return version;	}
	public String getValueOriginalSubsetId() { return id;	}
	public String getValueCurrentSubsetId() { return setid;	}
	
	public ArrayList<HashMap<String, String>> dataadapter(Context context) {
		
		try {
			    firstConcept = true;
			    pt = fsn = sym = "";
			
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

						if (tagName.equals("vocabulary")) {
							status =  xrp.getAttributeValue(null, "status");
							type =  xrp.getAttributeValue(null, "type");
							version =  xrp.getAttributeValue(null, "version");
							id =  xrp.getAttributeValue(null, "id");
							setid =  xrp.getAttributeValue(null, "setId");
						}

						if (tagName.equals("concept")) {
							if (firstConcept) {
								
								HashMap<String, String> map = new HashMap<String, String>();							
								map.put("PT",pt);
								map.put("FSN",fsn);
								map.put("SYM",sym);
								dataItems.add(map);
								
								// clear for the next concept code
								pt = fsn = sym = "";
								
							} else {
								firstConcept = true;
							}
							
							concept = xrp.getAttributeValue(null, "code");
						}
						
						if (tagName.equals("displayName")) {	
							displayName = xrp.nextText();
							
							
							
							//Log.i("dn", code + " -- " + displayName);
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