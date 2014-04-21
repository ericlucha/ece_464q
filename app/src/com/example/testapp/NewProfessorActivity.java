package com.example.testapp;

import java.util.Calendar;

import com.example.testapp.FeedReaderContract.ProfessorEntry;
import com.parse.ParseObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class NewProfessorActivity extends Activity implements View.OnClickListener {

	EditText name;
	Button submit;
	RadioGroup availGroup;
	RadioButton availButton;
	boolean available;
	boolean unavailable;
	boolean away;
	EditText location;
	EditText notes;
	TimePicker clock;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_professor);
		// Show the Up button in the action bar.
		setupActionBar();
		
		name = (EditText) findViewById(R.id.name);
		availGroup = (RadioGroup) findViewById(R.id.avail_radio);
		location = (EditText) findViewById(R.id.location);
		notes = (EditText) findViewById(R.id.notes);
		clock = (TimePicker) findViewById(R.id.end_time);
		submit = (Button) findViewById(R.id.submit);
		
		submit.setOnClickListener (this);
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_professor, menu);
		return true;
	}
	*/

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onClick(View v) {
		// TODO figure out the availability option
		try {
			String firstlast = name.getText().toString();
			int selectedAvail = availGroup.getCheckedRadioButtonId();
			availButton = (RadioButton) findViewById(selectedAvail);
			String availability = (String) availButton.getText();
			String locationStr = location.getText().toString();
			String notesStr = notes.getText().toString();
			int hour = clock.getCurrentHour();
			int minute = clock.getCurrentMinute();
			// Toast.makeText(getApplicationContext(),
			//		availability +  " " + locationStr + " " + notesStr + " " + hour + " " + minute,
			//		Toast.LENGTH_LONG).show();
			Calendar c = Calendar.getInstance();
			int cHr = c.get(Calendar.HOUR_OF_DAY);
			int cMin = c.get(Calendar.MINUTE);
			if (firstlast.equals("")) {
				Toast.makeText(getApplicationContext(), "Please enter your name.", Toast.LENGTH_SHORT).show();
				return;
			}
			else if (locationStr.equals("")) {
				Toast.makeText(getApplicationContext(), "Please enter your location.", Toast.LENGTH_SHORT).show();
				return;
			}
			else if (((cHr*60 + cMin)>(hour*60 + cMin)) && ((cHr*60 + cMin + 240)<((hour+24)*60 + cMin))) {
				Toast.makeText(getApplicationContext(), "Check in cannot last longer than four hours.", Toast.LENGTH_SHORT).show();
				return;
			}
			else if ((cHr*60 + cMin + 240)<(hour*60 + cMin)) {
				Toast.makeText(getApplicationContext(), "Check in cannot last longer than four hours.", Toast.LENGTH_SHORT).show();
				return;
			}
			else if ((cHr*60 + cMin + 15)>(hour*60 + cMin) && ((cHr*60 + cMin + 240)<((hour+24)*60 + cMin))) {
				Toast.makeText(getApplicationContext(), "Check in must last at least 15 minutes.", Toast.LENGTH_SHORT).show();
				return;
			}
			else {
				ParseObject values = new ParseObject("ProfessorCheckIn");
				values.put(ProfessorEntry.COLUMN_NAME_NAME, firstlast);
				values.put(ProfessorEntry.COLUMN_NAME_AVAILABILITY, availability);
				values.put(ProfessorEntry.COLUMN_NAME_LOCATION, locationStr);
				values.put(ProfessorEntry.COLUMN_NAME_NOTES, notesStr);
				values.put(ProfessorEntry.COLUMN_NAME_END_HRS, hour);
				values.put(ProfessorEntry.COLUMN_NAME_END_MIN, minute);
				values.put(ProfessorEntry.COLUMN_NAME_START_HRS, cHr);
				values.put(ProfessorEntry.COLUMN_NAME_START_MIN, cMin);
				values.saveInBackground();
				Intent openProfessorActivity = new Intent(getApplicationContext(), ProfessorActivity.class);
				startActivity(openProfessorActivity);
			}
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_LONG).show();
		}
	}

}
