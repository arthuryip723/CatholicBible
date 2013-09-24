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

	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub

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
