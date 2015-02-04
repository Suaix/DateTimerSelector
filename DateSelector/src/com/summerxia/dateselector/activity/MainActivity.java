package com.summerxia.dateselector.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.summerxia.dateselector.R;
import com.summerxia.dateselector.widget.DateTimeSelectorDialogBuilder;
import com.summerxia.dateselector.widget.DateTimeSelectorDialogBuilder.OnSaveListener;

public class MainActivity extends Activity implements OnClickListener, OnSaveListener {
	private Button button;
	private DateTimeSelectorDialogBuilder dialogBuilder;
	private TextView daTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button) findViewById(R.id.bt);
		button.setOnClickListener(this);
		daTextView = (TextView) findViewById(R.id.tv_date);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if (dialogBuilder == null) {
			dialogBuilder = DateTimeSelectorDialogBuilder.getInstance(this);
			dialogBuilder.setOnSaveListener(this);
		}
		dialogBuilder.show();
	}

	@Override
	public void onSaveSelectedDate(String selectedDate) {
		daTextView.setText(selectedDate);
	}

}
