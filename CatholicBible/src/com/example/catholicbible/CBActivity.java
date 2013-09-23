package com.example.catholicbible;

import android.app.Activity;

public class CBActivity extends Activity {
	public DBHelper getDatabaseHelper(){
		return ((CBApplication)getApplication()).getDBHelper();
		//return null;
	}
}
