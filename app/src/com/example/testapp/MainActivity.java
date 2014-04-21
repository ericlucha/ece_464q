package com.example.testapp;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.parse.Parse;

public class MainActivity extends FragmentActivity implements ActionBar.OnNavigationListener, View.OnClickListener {

	RadioGroup userGroup;
	Button userButton;
	Button submit;
	String userType;
	
	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * current dropdown position.
	 */
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";		

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Parse.initialize(this, "vmDrfkYEg6C2GyzPXh8uMFFSMuOdl2Lg5fbn4zVj", "diAwG3vYdBkkULpmo16qgOOoDGgQk0fxXFeSFS0S");
		setContentView(R.layout.activity_main);
		
		userGroup = (RadioGroup) findViewById(R.id.user_radio);
		submit = (Button) findViewById(R.id.submit);
		
		submit.setOnClickListener (this);
		
	}
	
	@Override
	public void onClick(View v) {
		Intent openNewStudentActivity = new Intent(getApplicationContext(), NewStudentActivity.class);
		Intent openNewProfessorActivity = new Intent(getApplicationContext(), NewProfessorActivity.class);
		Intent openBusActivity = new Intent(getApplicationContext(), BusActivity.class);
		// TODO figure out the availability option
		int selectedUser = userGroup.getCheckedRadioButtonId();
		userButton = (RadioButton) findViewById(selectedUser);
		userType = (String) userButton.getText();
		if (userType.equals(getResources().getString(R.string.user_student)))	startActivity(openNewStudentActivity);
		else if (userType.equals(getResources().getString(R.string.user_professor))) startActivity(openNewProfessorActivity);
		else if (userType.equals(getResources().getString(R.string.user_bus_view))) startActivity(openBusActivity);
		else System.exit(0);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_activity_actions, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
		Intent openBusActivity = new Intent(getApplicationContext(), BusActivity.class);
		Intent openNewProfessorActivity = new Intent(getApplicationContext(), NewProfessorActivity.class);
		Intent openNewStudentActivity = new Intent(getApplicationContext(), NewStudentActivity.class);
		Intent openProfessorActivity = new Intent(getApplicationContext(), ProfessorActivity.class);
		Intent openStudyActivity = new Intent(getApplicationContext(), StudyActivity.class);
		
	    switch (item.getItemId()) {
	        case R.id.action_study:
	            startActivity(openStudyActivity);
	            return true;
	        case R.id.action_professor:
	            startActivity(openProfessorActivity);
	            return true;
	        case R.id.action_bus:
	            startActivity(openBusActivity);
	            return true;
	        case R.id.action_new_study:
	            startActivity(openNewStudentActivity);
	            return true;
	        case R.id.action_new_professor:
	            startActivity(openNewProfessorActivity);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	/**
	 * Backward-compatible version of {@link ActionBar#getThemedContext()} that
	 * simply returns the {@link android.app.Activity} if
	 * <code>getThemedContext</code> is unavailable.
	 */
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	private Context getActionBarThemedContextCompat() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			return getActionBar().getThemedContext();
		} else {
			return this;
		}
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		// Restore the previously serialized current dropdown position.
		if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
			getActionBar().setSelectedNavigationItem(
					savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// Serialize the current dropdown position.
		outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActionBar()
				.getSelectedNavigationIndex());
	}

	@Override
	public boolean onNavigationItemSelected(int position, long id) {
		// When the given dropdown item is selected, show its contents in the
		// container view.
		Fragment fragment = new DummySectionFragment();
		Bundle args = new Bundle();
		args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
		fragment.setArguments(args);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.container, fragment).commit();
		return true;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		  if (requestCode == 1) {

		     if(resultCode == RESULT_OK){      
		         userType = data.getStringExtra("result");          
		     }
		     if (resultCode == RESULT_CANCELED) {    
		         //Write your code if there's no result
		     }
		  }
		}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
			switch(getArguments().getInt(ARG_SECTION_NUMBER)){
				case 1:
					View rootView = inflater.inflate(R.layout.fragment_main_dummy,
							container, false);
					return rootView;
				case 2:
					View rootView2 = inflater.inflate(R.layout.activity_study,
							container, false);
					return rootView2;
				case 3:
					View rootView3 = inflater.inflate(R.layout.activity_professor,
							container, false);
					return rootView3;
				case 4:
					View rootView4 = inflater.inflate(R.layout.activity_bus,
							container, false);
					return rootView4;
				case 5:
					View rootView5 = inflater.inflate(R.layout.activity_new_student,
							container, false);
					return rootView5;
				case 6:
					View rootView6 = inflater.inflate(R.layout.activity_new_professor,
							container, false);
					return rootView6;
				default:
					View rootViewdef = inflater.inflate(R.layout.fragment_main_dummy,
							container, false);
					return rootViewdef;
			}
		}
	}
}
