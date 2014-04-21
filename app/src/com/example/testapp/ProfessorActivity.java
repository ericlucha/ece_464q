package com.example.testapp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uk.co.senab.actionbarpulltorefresh.extras.actionbarcompat.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;

import com.example.testapp.FeedReaderContract.ProfessorEntry;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.os.AsyncTask;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ProfessorActivity extends ActionBarActivity implements OnRefreshListener{

	ListView listView;
	List<ParseObject> professorPosts;
	
	private PullToRefreshLayout mPullToRefreshLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_professor);
		getSupportActionBar().setDisplayHomeAsUpEnabled(false);
		
		// Show the Up button in the action bar.
		setupActionBar();
		
		//Pull-to-refresh setup
		mPullToRefreshLayout = (PullToRefreshLayout) findViewById(R.id.professor);

	    // Now setup the PullToRefreshLayout
	    ActionBarPullToRefresh.from(this)
	            // Mark All Children as pullable
	            .allChildrenArePullable()
	            // Set a OnRefreshListener
	            .listener(this)
	            // Finally commit the setup to our PullToRefreshLayout
	            .setup(mPullToRefreshLayout);
			    
		// Parse set-up
		queryParse();
		
		listView = (ListView) findViewById(R.id.list_professor);
		
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(false);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.professor, menu);
		return true;
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

	
	public void queryParse() {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("ProfessorCheckIn");
		professorPosts = new ArrayList<ParseObject>();
		query.addDescendingOrder("createdAt");
		query.findInBackground(new FindCallback<ParseObject>() {
		    public void done(List<ParseObject> pp, ParseException e) {
		        if (e == null) {
		            // Toast.makeText(getApplicationContext(), "Retrieved " + pp.size() + " entries", Toast.LENGTH_LONG).show();
		        } else {
		        	Toast.makeText(getApplicationContext(), "Error: No Internet Connection", Toast.LENGTH_LONG).show();
		        	Intent openMainActivity = new Intent(getApplicationContext(), MainActivity.class);
		        	startActivity(openMainActivity);
		        }
		        
		        ArrayList<String> posts = new ArrayList<String>();
		        
		        Date now = new Date();
				for (ParseObject p : pp)
				{
					String name = p.getString(ProfessorEntry.COLUMN_NAME_NAME);
					String availability = p.getString(ProfessorEntry.COLUMN_NAME_AVAILABILITY);
					String location = p.getString(ProfessorEntry.COLUMN_NAME_LOCATION);
					String notes = p.getString(ProfessorEntry.COLUMN_NAME_NOTES);
					int eH = p.getInt(ProfessorEntry.COLUMN_NAME_END_HRS);
					int eM = p.getInt(ProfessorEntry.COLUMN_NAME_END_MIN);
					int sH = p.getInt(ProfessorEntry.COLUMN_NAME_START_HRS);
					int sM = p.getInt(ProfessorEntry.COLUMN_NAME_START_MIN);
					
					Date createdAt = p.getCreatedAt();
					Date expire = new Date(createdAt.getTime() + 14400000);  //created time + 4 hours
									
					int calcE;
					String hoursE;
			    	String minutesE;
			    	String dayniteE;
			    	if (eH < 10) {
			    		hoursE = "0" + eH;
			    		if (eH == 0) hoursE = "12";
			    		dayniteE = "AM";
			    	}
			    	else if (eH > 12)
			    	{
			    		calcE = eH - 12;
			    		if (calcE < 10) hoursE = "0" + calcE;
			    		else hoursE = "" + calcE;
			    		dayniteE = "PM";
			    	}
			    	else
			    	{
			    		hoursE = "" + eH;
			    		dayniteE = "AM";
			    	}
			    	if (eM < 10) minutesE = "0" + eM;
			    	else minutesE = "" + eM;
			    	String endTime = hoursE + ":" + minutesE + " " + dayniteE;
			    	
			    	int calcS;
					String hoursS;
			    	String minutesS;
			    	String dayniteS;
			    	if (sH < 10) {
			    		hoursS = "0" + sH;
			    		if (sH == 0) hoursS = "12";
			    		dayniteS = "AM";
			    	}
			    	else if (sH > 12)
			    	{
			    		calcS = sH - 12;
			    		if (calcS < 10) hoursS = "0" + calcS;
			    		else hoursS = "" + calcS;
			    		dayniteS = "PM";
			    	}
			    	else
			    	{
			    		hoursS = "" + sH;
			    		dayniteS = "AM";
			    	}
			    	if (sM < 10) minutesS = "0" + sM;
			    	else minutesS = "" + sM;
			    	String startTime = hoursS + ":" + minutesS + " " + dayniteS;
			    	
					if (now.before(expire)) {
						posts.add("Dr. " + name + "\n" + availability + "\nLocation: " +  location + "\nFrom " + startTime + " until " + endTime + "\n" + notes);
					}
			    }
				
			    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_2, android.R.id.text1, posts);
			    
			    // Assign adapter to ListView
		        listView.setAdapter(adapter); 
		    }
		});
	}
	
	@Override
	public void onRefreshStarted(View view) {
		
		new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                queryParse();
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);

                // Notify PullToRefreshLayout that the refresh has finished
                mPullToRefreshLayout.setRefreshComplete();

                
            }
        }.execute();
	}
}
