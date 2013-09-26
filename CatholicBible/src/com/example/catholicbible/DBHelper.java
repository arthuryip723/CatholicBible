package com.example.catholicbible;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	static final String dbName = "CatholicBibleDB";
	static final String booksTable = "Books";
	static final String chaptersTable = "Chapters";
	static final String versesTable = "Verses";
	private static final int DATABASE_VERSION = 1; 

	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	public DBHelper(Context context) {
		super(context, dbName, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		int i = 1;
		String s = "my string";
		String s1 = s + "Arthur";
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}
	
	public void getChaptersByBook()
	{
		
	}
	
	public void getVersesByBookAndChapter()
	{
		
	}

}
