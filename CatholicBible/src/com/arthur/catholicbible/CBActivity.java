package com.arthur.catholicbible;

import android.app.Activity;

public class CBActivity extends Activity {
//	public DBHelper getDBHelper(){
//		return ((CBApplication)getApplication()).getDBHelper();
//		//return null;
//	}
	
	public CBDataSource getDataSource() {
		return ((CBApplication)getApplication()).getDataSource();
	}
}
