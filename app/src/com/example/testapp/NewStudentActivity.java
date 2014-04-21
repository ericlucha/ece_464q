package com.example.testapp;

import java.util.Calendar;

import com.example.testapp.FeedReaderContract.StudyEntry;
import com.parse.ParseObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class NewStudentActivity extends Activity implements View.OnClickListener {

	Button submit;
	Spinner fieldSpinner;
	EditText courseCode;
	EditText location;
	EditText notes;
	TimePicker clock;
	String courseNo;
	int chosenField;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_student);
		// Show the Up button in the action bar.
		setupActionBar();
		
		fieldSpinner = (Spinner) findViewById(R.id.field_spinner);
		courseCode = (EditText) findViewById(R.id.course_code);
		location = (EditText) findViewById(R.id.location);
		notes = (EditText) findViewById(R.id.notes);
		clock = (TimePicker) findViewById(R.id.end_time);
		submit = (Button) findViewById(R.id.submit);
		
		submit.setOnClickListener (this);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> fieldAdapter = ArrayAdapter.createFromResource(this,
		        R.array.field_codes_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		fieldAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		fieldSpinner.setAdapter(fieldAdapter);
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
		getMenuInflater().inflate(R.menu.new_student, menu);	
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
		// TODO connect these inputs to the backend
		try {
			String field = fieldSpinner.getSelectedItem().toString();
			String coursecode = courseCode.getText().toString();
			String locationStr = location.getText().toString();
			String notesStr = notes.getText().toString();
			int hour = clock.getCurrentHour();
			int minute = clock.getCurrentMinute();
			//Toast.makeText(getApplicationContext(),
			//		field + " " + coursecode + " " + locationStr + " " + notesStr + " " + hour + " " + minute,
			//		Toast.LENGTH_LONG).show();
			Calendar c = Calendar.getInstance();
			int cMin = c.get(Calendar.MINUTE);
			int cHr = c.get(Calendar.HOUR_OF_DAY);
			if (coursecode.equals("")) {
				Toast.makeText(getApplicationContext(), "Please enter a valid course number.", Toast.LENGTH_SHORT).show();
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
				Toast.makeText(getApplicationContext(), "Study group post must expire four hours.", Toast.LENGTH_SHORT).show();
				return;
			}
			else if ((cHr*60 + cMin + 15)>(hour*60 + cMin) && ((cHr*60 + cMin + 240)<((hour+24)*60 + cMin))) {
				Toast.makeText(getApplicationContext(), "Study group must last at least 15 minutes.", Toast.LENGTH_SHORT).show();
				return;
			}
			else {
				ParseObject values = new ParseObject("StudyGroup");
				values.put(StudyEntry.COLUMN_NAME_FIELD, field);
				values.put(StudyEntry.COLUMN_NAME_COURSE, coursecode);
				values.put(StudyEntry.COLUMN_NAME_LOCATION, locationStr);
				values.put(StudyEntry.COLUMN_NAME_NOTES, notesStr);
				values.put(StudyEntry.COLUMN_NAME_END_HRS, hour);
				values.put(StudyEntry.COLUMN_NAME_END_MIN, minute);
				values.put(StudyEntry.COLUMN_NAME_START_MIN, cMin);
				values.put(StudyEntry.COLUMN_NAME_START_HRS, cHr);
				values.saveInBackground();
				Intent openStudyActivity = new Intent(getApplicationContext(), StudyActivity.class);
				startActivity(openStudyActivity);
			}
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_LONG).show();
		}
	}

}