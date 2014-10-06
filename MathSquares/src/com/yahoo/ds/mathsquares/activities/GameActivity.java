package com.yahoo.ds.mathsquares.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.LogInCallback;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.yahoo.ds.mathsquares.R;

public class GameActivity extends Activity {
	
    private static final String TAG = GameActivity.class.getName();
    private static String sUserId;
    private static final Integer INSTRUCTIONS_INTENT = 5;
    private static final Integer LEADERBOARD_INTENT = 6;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		if (ParseUser.getCurrentUser() != null) { // start with existing user
            startWithCurrentUser();
        } else { // If not logged in, login as a new anonymous user
            login();
        }
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
	}
	
	
	public void onSettingsLeaderboard(MenuItem mi)
	{
    	Intent i = new Intent(this, LeaderboardActivity.class);
    	//Pass arguments
    	//Execute Intent startActivityForResults
    	startActivityForResult(i, LEADERBOARD_INTENT);
	}
	
	public void onSettingsHelp(MenuItem mi)
	{
    	Intent i = new Intent(this, InstructionsActivity.class);
    	//Pass arguments
    	//Execute Intent startActivityForResults
    	startActivityForResult(i, INSTRUCTIONS_INTENT); 
	}
	
	private void startWithCurrentUser() {
        sUserId = ParseUser.getCurrentUser().getObjectId();		
    }
    
    private void login() {
        ParseAnonymousUtils.logIn(new LogInCallback() {
	    @Override
	    public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    Log.d(TAG, "Anonymous login failed.");
                } else {
                    startWithCurrentUser();
                }
            }
       });		
    }
}
