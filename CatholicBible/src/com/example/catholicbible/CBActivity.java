package com.example.catholicbible;

import android.app.Activity;

public class CBActivity extends Activity {
	public DBHelper getDBHelper(){
		return ((CBApplication)getApplication()).getDBHelper();
		//return null;
	}
	
	public BibleDataSource getDataSource() {
		return ((CBApplication)getApplication()).getDataSource();
	}
}
