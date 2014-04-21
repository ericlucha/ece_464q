package com.example.testapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.app.NavUtils;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;

public class BusActivity extends Activity {
	private Spinner busSpinner;
	private Spinner stopSpinner;
	//private Spinner timeSpinner;
	private ListView timeListing;
	private ArrayAdapter<CharSequence> bus_adapter;
	private ArrayAdapter<CharSequence> stop_adapter;
	private ArrayAdapter<CharSequence> time_adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bus);
		// Show the Up button in the action bar.
		setupActionBar();
		setupSpinners();
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}
	
	private void setupSpinners() {
		
		busSpinner = (Spinner)findViewById(R.id.busSpinner);
		stopSpinner = (Spinner)findViewById(R.id.stopSpinner);
		//timeSpinner = (Spinner)findViewById(R.id.timeSpinner);
		timeListing = (ListView) findViewById(R.id.list_bustimes);
			
		bus_adapter = ArrayAdapter.createFromResource(this,
			R.array.bus_array, android.R.layout.simple_spinner_item);
		stop_adapter = ArrayAdapter.createFromResource(this,
				R.array.blank_menu_array, android.R.layout.simple_spinner_item);
		//time_adapter = ArrayAdapter.createFromResource(this,
		//		R.array.blank_menu_array, android.R.layout.simple_spinner_item);
		/*
		bus_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		stop_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		time_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		*/
		busSpinner.setAdapter(bus_adapter);
		stopSpinner.setAdapter(stop_adapter);
		//timeSpinner.setAdapter(time_adapter);
	
		busSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			Resources res = getResources();
			TypedArray busStopArray = res.obtainTypedArray(R.array.stoparray_array);
			
		    @Override
		    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		    	stop_adapter = ArrayAdapter.createFromResource(parent.getContext(),
	    				busStopArray.getResourceId(position, 0), android.R.layout.simple_spinner_item);
		    	stop_adapter.notifyDataSetChanged();
		    	stopSpinner.setAdapter(stop_adapter);
		    }

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
		
		stopSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			Resources res = getResources();
			TypedArray busStopTimeArray = res.obtainTypedArray(R.array.timesarrayarray_array);
			
		    @Override
		    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		    	TypedArray secondaryTimeArray = res.obtainTypedArray(busStopTimeArray.getResourceId(busSpinner.getSelectedItemPosition(),0));
		    	
		    	time_adapter = ArrayAdapter.createFromResource(parent.getContext(),
	    				secondaryTimeArray.getResourceId(position, 0), android.R.layout.simple_spinner_item);
		    	time_adapter.notifyDataSetChanged();
		    	timeListing.setAdapter(time_adapter);		    	
		    }

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bus, menu);
		return true;
	}

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

}
