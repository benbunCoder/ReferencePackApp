package com.apps4health.refpack.dialogs;

import java.util.HashMap;

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

public class oidDialog extends DialogFragment {

	public HashMap<String, String> dataItem = new HashMap<String, String>();
	
	public static oidDialog newInstance() {
		return new oidDialog();
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		View view = getActivity().getLayoutInflater().inflate(R.layout.oiditemdlg, null);
		TextView tvName = (TextView) view.findViewById(R.id.TextViewOidDlg01);
		TextView tvDescription = (TextView) view.findViewById(R.id.TextViewOidDlg02);
		String OidId =  dataItem.get("OID_ID");
		
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		builder.setTitle(OidId);
		builder.setPositiveButton("OK", null);
		
		
		tvName.setText( dataItem.get("OID_NAME") );
		tvDescription.setText( dataItem.get("OID_DESC") );
		

		
		builder.setView(view);
	
		return builder.create();
	}
}
