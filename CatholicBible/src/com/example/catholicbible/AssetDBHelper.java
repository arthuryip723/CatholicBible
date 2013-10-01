package com.example.catholicbible;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class AssetDBHelper extends SQLiteAssetHelper {
	private static final String DATABASE_NAME = "CatholicBible";
	private static final int DATABASE_VERSION = 1;

	public AssetDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

}
