package com.apps4health.refpack.dialogs;

import com.apps4health.refpack.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TextView;

public class aboutDialog extends DialogFragment {


	public static aboutDialog newInstance() {
		return new aboutDialog();
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	
		String versionNoText = "";
	
		PackageManager pm = getActivity().getPackageManager();


		try {
			PackageInfo pmi = pm.getPackageInfo(getActivity().getPackageName(),0);
			versionNoText = pmi.versionName.toString();
			
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		View view = getActivity().getLayoutInflater().inflate(R.layout.about, null);
		builder.setTitle(R.string.app_name);
		builder.setPositiveButton("OK", null);
		
		
		TextView tvRelease = (TextView) view.findViewById(R.id.tvRelease);
		tvRelease.setText( getString(R.string.version,versionNoText));
		

		
		builder.setView(view);
	
		return builder.create();
	}
}
