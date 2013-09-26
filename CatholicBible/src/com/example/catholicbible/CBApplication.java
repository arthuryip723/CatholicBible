package com.example.catholicbible;

import android.app.Application;

public class CBApplication extends Application {
	String foo = "Arthur";
	private DBHelper helper = null;
	//private DBHelper helper = new DBHelper(this);
	
	public DBHelper getDBHelper(){
		if (helper == null) {
			helper = new DBHelper(this);
			//helper.open();
			helper.getWritableDatabase();
		}
		return helper;
		//return null;
	}
}
