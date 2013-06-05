package com.example.catholicbible;

import android.app.Application;

public class CBApplication extends Application {
	String foo = "Arthur";
	private DBHelper helper = null;
	
	public DBHelper getDBHelper(){
		return helper;
		//return null;
	}
}
