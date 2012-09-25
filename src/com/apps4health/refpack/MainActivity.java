package com.apps4health.refpack;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import com.apps4health.refpack.adapters.oidadapter;
import com.apps4health.refpack.adapters.subsetadapter;
import com.apps4health.refpack.adapters.v2adapter;
import com.apps4health.refpack.adapters.v3adapter;
import com.apps4health.refpack.dialogs.aboutDialog;
import com.apps4health.refpack.dialogs.oidDialog;
import com.apps4health.refpack.parsers.OidParser;
import com.apps4health.refpack.parsers.SubsetParser;
import com.apps4health.refpack.parsers.V2Parser;
import com.apps4health.refpack.parsers.V3Parser;

public class MainActivity extends SherlockFragmentActivity implements
		OnNavigationListener {

	private oidadapter refOIDAdapter;
	private subsetadapter refSubsetAdapter;
	private v2adapter refV2Adapter;
	private v3adapter refV3Adapter;

	private ArrayList<HashMap<String, String>> lvItems = new ArrayList<HashMap<String, String>>();
	private ListView list;
	private ActionBar actionBar;
	private DisplayMode activityMode = DisplayMode.NONE;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		actionBar = getSupportActionBar();
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(R.string.title_activity_main);

		list = (ListView) findViewById(R.id.lvList);

		list.setFastScrollEnabled(true);

		// Add click listener
		list.setOnItemClickListener(new OnItemClickListener() {

			@SuppressWarnings("unchecked")
			public void onItemClick(AdapterView<?> parent, View v,int position, long id) {

				HashMap<String, String> dataItem;

				switch (activityMode) {

				case OIDS: {
					dataItem = (HashMap<String, String>) refOIDAdapter.getItem(position);

					FragmentManager fm = getSupportFragmentManager();
					oidDialog oidDlg = oidDialog.newInstance();
					oidDlg.dataItem = dataItem;
					oidDlg.show(fm, "");
				}
				break;

				case SUBSETS: {
					dataItem = (HashMap<String, String>) refSubsetAdapter.getItem(position);
										
					Intent activityIntent = new Intent("com.apps4health.refpack.SNOMEDVIEWER");

					Bundle activityData = new Bundle();
					activityData.putString("ID", dataItem.get("SUBSET_ID").toString());
					activityData.putString("NAME", dataItem.get("SUBSET_NAME").toString());

					activityIntent.putExtras(activityData);

					startActivity(activityIntent);
				}
				break;

				case V2VOCABS: {
					dataItem = (HashMap<String, String>) refV2Adapter.getItem(position);

					Intent activityIntent = new Intent("com.apps4health.refpack.V2VIEWER");

					Bundle activityData = new Bundle();
					activityData.putString("TABLE", dataItem.get("V2_TABLE").toString());
					activityData.putString("NAME", dataItem.get("V2_NAME").toString());
					activityData.putString("VERSION", dataItem.get("V2_VERSION").toString());

					activityIntent.putExtras(activityData);

					startActivity(activityIntent);

				}
					break;
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getSupportMenuInflater().inflate(R.menu.activity_main, menu);

		return true;
	};

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.menu_help: {

			FragmentManager fm = getSupportFragmentManager();
			aboutDialog deleteDlg = aboutDialog.newInstance();
			deleteDlg.show(fm, "fragment_delete");

			return true;
		}

		case R.id.menu_viewOids: {

			activityMode = DisplayMode.OIDS;

			// Parse the OID XML file to get populate the ArrayAdapter, used to
			// control the ListView
			OidParser OidXMLParser = new OidParser();
			lvItems = OidXMLParser.oidadapter(this);

			// Set the layout to the layout for each OID item.
			int layout = R.layout.oiditemlayout;
			// Initialise the adapter - note most of the parameters are dummy
			// values.
			refOIDAdapter = new oidadapter(this, lvItems, layout,new String[] {}, new int[] {});

			// Bind the ListView to the adapter
			list.setAdapter(refOIDAdapter);

			// Signal an update
			refOIDAdapter.notifyDataSetChanged();

			String ActionBarTitleLHS = getApplicationContext().getResources().getString(R.string.title_activity_main);
			String ActionBarTitleRHS = getApplicationContext().getResources().getString(R.string.oids);

			actionBar.setTitle(ActionBarTitleLHS + " : " + ActionBarTitleRHS);

			return true;
		}

		case R.id.menu_viewSubsets: {

			activityMode = DisplayMode.SUBSETS;

			// Parse the OID XML file to get populate the ArrayAdapter, used to
			// control the ListView
			SubsetParser SubsetXMLParser = new SubsetParser();
			lvItems = SubsetXMLParser.subsetadapter(this);

			// Set the layout to the layout for each OID item.
			int layout = R.layout.subsetitemlayout;

			// Initialise the adapter - note most of the parameters are dummy
			// values.
			refSubsetAdapter = new subsetadapter(this, lvItems, layout, new String[] {}, new int[] {});

			// Bind the ListView to the adapter
			list.setAdapter(refSubsetAdapter);

			// Signal an update
			refSubsetAdapter.notifyDataSetChanged();

			String ActionBarTitleLHS = getApplicationContext().getResources().getString(R.string.title_activity_main);
			String ActionBarTitleRHS = getApplicationContext().getResources().getString(R.string.subsets);

			actionBar.setTitle(ActionBarTitleLHS + " : " + ActionBarTitleRHS);

			return true;
		}

		case R.id.menu_viewVocabs: {

			activityMode = DisplayMode.V3VOCABS;

			// Parse the XML file to get populate the ArrayAdapter, used to
			// control the ListView
			V3Parser V3XMLParser = new V3Parser();
			lvItems = V3XMLParser.v3adapter(this);

			// Set the layout to the layout for each OID item.
			int layout = R.layout.v3itemlayout;

			// Initialise the adapter - note most of the parameters are dummy
			// values.
			refV3Adapter = new v3adapter(this, lvItems, layout, new String[] {}, new int[] {});

			// Bind the ListView to the adapter
			list.setAdapter(refV3Adapter);

			// Signal an update
			refV3Adapter.notifyDataSetChanged();

			String ActionBarTitleLHS = getApplicationContext().getResources().getString(R.string.title_activity_main);
			String ActionBarTitleRHS = getApplicationContext().getResources().getString(R.string.v3vocabs);

			actionBar.setTitle(ActionBarTitleLHS + " : " + ActionBarTitleRHS);
			return true;
		}

		case R.id.menu_viewTables: {

			activityMode = DisplayMode.V2VOCABS;

			// Parse the XML file to get populate the ArrayAdapter, used to
			// control the ListView
			V2Parser V2XMLParser = new V2Parser();
			lvItems = V2XMLParser.v2adapter(this);

			// Set the layout to the layout for each OID item.
			int layout = R.layout.v2itemlayout;

			// Initialise the adapter - note most of the parameters are dummy
			// values.
			refV2Adapter = new v2adapter(this, lvItems, layout, new String[] {}, new int[] {});

			// Bind the ListView to the adapter
			list.setAdapter(refV2Adapter);

			// Signal an update
			refV2Adapter.notifyDataSetChanged();

			String ActionBarTitleLHS = getApplicationContext().getResources().getString(R.string.title_activity_main);
			String ActionBarTitleRHS = getApplicationContext().getResources().getString(R.string.v2vocabs);

			actionBar.setTitle(ActionBarTitleLHS + " : " + ActionBarTitleRHS);
			return true;
		}
		case R.id.menu_settings: {
			// Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
			// showTempDialog();
			return true;
		}

		default: {
			return super.onOptionsItemSelected(item);
		}
		}
	}

	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// TODO Auto-generated method stub
		String s = "Filter " + itemPosition + " selected.";
		Toast.makeText(this, s, Toast.LENGTH_SHORT).show();

		return false;
	}

	private enum DisplayMode {
		NONE, OIDS, SUBSETS, V2VOCABS, V3VOCABS
	}
}
