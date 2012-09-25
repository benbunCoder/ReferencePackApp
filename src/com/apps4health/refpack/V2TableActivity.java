package com.apps4health.refpack;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.apps4health.refpack.adapters.v2vocabadapter;
import com.apps4health.refpack.dialogs.aboutDialog;
import com.apps4health.refpack.parsers.V2VocabParser;

public class V2TableActivity extends SherlockFragmentActivity implements
		OnNavigationListener {

	//private vocabAdapter refAdapter;
	private ArrayList<HashMap<String, String>> lvItems = new ArrayList<HashMap<String, String>>();
	
	private v2vocabadapter adapter;
	
	private ListView list;
	private ActionBar actionBar;
	
	private String targetTable = "";
	private String targetName = "";
	private String targetVersion = "";
	
	private String ActionBarTitle1 = "";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vocab_detail_main);

		actionBar = getSupportActionBar();
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		// Get the details of the V2 Table to be viewed
		
		getAnyIntents();
		setActionBarTitle();

		list = (ListView) findViewById(R.id.lvList);
		//	list.setFastScrollEnabled(true);
		
		V2VocabParser XMLParser = new V2VocabParser(targetName,targetVersion);
		lvItems = XMLParser.dataadapter(this);
		
		TextView tvOid = (TextView) findViewById(R.id.vocabDetailOid);
		TextView tvVersion = (TextView) findViewById(R.id.vocabDetailVersion);
		TextView tvStatus = (TextView) findViewById(R.id.vocabDetailStatus);
		
		tvOid.setText( XMLParser.getValueId() );
		tvVersion.setText( XMLParser.getValueVersion() );
		tvStatus.setText( XMLParser.getValueStatus() );
		
		int layout = R.layout.v2itemlayout;
		adapter = new v2vocabadapter(this, lvItems, layout,	new String[] {}, new int[] {});
		list.setAdapter(adapter);
	}

	private void setActionBarTitle() {
		ActionBarTitle1 += getApplicationContext().getResources().getString(R.string.v2vocabs);
		ActionBarTitle1 += " : " + targetTable;
		ActionBarTitle1 += " - " + targetName;
		//ActionBarTitle1 += " ( " + targetVersion + " )";
		actionBar.setTitle(ActionBarTitle1);
	}
	
	private void getAnyIntents() {
		
		Intent intent = getIntent();
		Bundle bundleData = intent.getExtras();
		
		if (bundleData != null) {			
			targetTable = bundleData.getString("TABLE");
			targetName = bundleData.getString("NAME");
			targetVersion = bundleData.getString("VERSION");
			actionBar.setTitle(targetTable);
		}	
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getSupportMenuInflater().inflate(R.menu.activity_v2, menu);

		return true;
	}

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
	
			case android.R.id.home: {
				
				finish(); 
				//pager.setCurrentItem(0, false);
	
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

}
