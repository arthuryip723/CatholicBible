package com.example.catholicbible;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CBDataSource {
	private SQLiteDatabase database;
	private DBHelper dbHelper;

	public CBDataSource(Context context) {
		dbHelper = new DBHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public List<Book> getAllBooks() {
		List<Book> books = new ArrayList<Book>();
		Cursor cursor = database.query("book", null, null, null, null, null,
				null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Book book = new Book(cursor.getInt(0), cursor.getString(1));
			// cursor.getString(2);
			books.add(book);
			cursor.moveToNext();
		}
		// return null;
		cursor.close();
		return books;
	}

	public List<Book> getOldTestament() {
		return null;
	}

	public List<Book> getNewTestament() {
		return null;
	}

	public List<Chapter> getChapters(int id) {
		return null;
	}

	public List<Chapter> getChapters(String bookName) {
		return null;
	}
}
