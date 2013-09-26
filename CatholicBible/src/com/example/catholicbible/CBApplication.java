package com.example.catholicbible;

import javax.sql.DataSource;

import android.app.Application;

public class CBApplication extends Application {
	String foo = "Arthur";
	private DBHelper helper = null;
	//private DBHelper helper = new DBHelper(this);
	private BibleDataSource dataSource;
	
	public DBHelper getDBHelper(){
		if (helper == null) {
			helper = new DBHelper(this);
			//helper.open();
			helper.getWritableDatabase();
		}
		return helper;
		//return null;
	}
	
	public BibleDataSource getDataSource() {
		if (dataSource == null) {
			dataSource = new BibleDataSource(this);
			dataSource.open();
		}
		return dataSource;
	}
}
