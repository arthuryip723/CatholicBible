package com.example.catholicbible;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class AssetDBHelper extends SQLiteAssetHelper {
	private static final String DATABASE_NAME = "CatholicBible";
	private static final int DATABASE_VERSION = 8;

	public AssetDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

		/**
		 * Here I want to force the db upgraded by copying new db file. I should
		 * set to the same the DAVABASE_VERSION and the parameter of
		 * setForceUpgradedVersion(), and make them increment to upgrade db.
		 */
		setForcedUpgradeVersion(8);
		// TODO Auto-generated constructor stub
	}

}
