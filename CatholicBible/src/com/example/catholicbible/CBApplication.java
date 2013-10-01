package com.example.catholicbible;

import android.app.Application;

public class CBApplication extends Application {
	//String foo = "Arthur";
//	private DBHelper helper = null;
	//private DBHelper helper = new DBHelper(this);
	private CBDataSource dataSource;
	
//	public DBHelper getDBHelper(){
//		if (helper == null) {
//			helper = new DBHelper(this);
//			//helper.open();
//			helper.getWritableDatabase();
//		}
//		return helper;
//		//return null;
//	}
	
	public CBDataSource getDataSource() {
		if (dataSource == null) {
			dataSource = new CBDataSource(this);
			dataSource.open();
		}
		return dataSource;
	}
}
