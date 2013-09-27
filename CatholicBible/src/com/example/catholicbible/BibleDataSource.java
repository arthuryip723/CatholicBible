package com.example.catholicbible;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class BibleDataSource {
	private SQLiteDatabase database;
	private DBHelper dbHelper;
	
	public BibleDataSource(Context context) {
		dbHelper = new DBHelper(context);
	}
	
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		dbHelper.close();
	}
	
	public List<String> getAllBooks() {
		List<String> books = new ArrayList<String>();
		Cursor cursor = database.query("book", null,
				null, null, null, null, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()) {
			String book = cursor.getString(2);
			books.add(book);
			cursor.moveToNext();
		}
		//return null;
		cursor.close();
		return books;
	}
}
