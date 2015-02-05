package com.summerxia.dateselector.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.summerxia.dateselector.R;
import com.summerxia.dateselector.widget.DateTimeSelectorDialogBuilder;
import com.summerxia.dateselector.widget.DateTimeSelectorDialogBuilder.OnSaveListener;
import com.summerxia.dateselector.widget.LocationSelectorDialogBuilder;
import com.summerxia.dateselector.widget.LocationSelectorDialogBuilder.OnSaveLocationLister;

public class MainActivity extends Activity implements OnClickListener, OnSaveListener, OnSaveLocationLister {
	private Button button;
	private DateTimeSelectorDialogBuilder dialogBuilder;
	private TextView daTextView;
	private Button button2;
	private TextView locationText;
	private LocationSelectorDialogBuilder locationBuilder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button) findViewById(R.id.bt);
		button.setOnClickListener(this);
		daTextView = (TextView) findViewById(R.id.tv_date);
		button2 = (Button) findViewById(R.id.bt_2);
		button2.setOnClickListener(this);
		locationText = (TextView) findViewById(R.id.tv_location);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt:
			if (dialogBuilder == null) {
				dialogBuilder = DateTimeSelectorDialogBuilder.getInstance(this);
				dialogBuilder.setOnSaveListener(this);
			}
			dialogBuilder.show();
			break;
		case R.id.bt_2:
			if (locationBuilder == null) {
				locationBuilder = LocationSelectorDialogBuilder.getInstance(this);
				locationBuilder.setOnSaveLocationLister(this);
			}
			locationBuilder.show();
			break;
		}
		
	}

	@Override
	public void onSaveSelectedDate(String selectedDate) {
		daTextView.setText(selectedDate);
	}

	@Override
	public void onSaveLocation(String location, String provinceId, String cityId) {
		locationText.setText(location);
	}

}
