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

		/*
		 * List<Book> books = new ArrayList<Book>(); Book b1 = new Book(1,
		 * "Book 1"); Book b2 = new Book(2, "Book 2"); books.add(b1);
		 * books.add(b2); return books;
		 */
	}

	public List<Book> getOldTestament() {
		return null;
	}

	public List<Book> getNewTestament() {
		return null;
	}

	public List<Chapter> getChapters(int bookId) {
		// Mock some data here
		/*
		 * Chapter chapter1 = new Chapter(1, bookId, 1, "Chapter 1"); Chapter
		 * chapter2 = new Chapter(2, bookId, 2, "Chapter 2"); List<Chapter>
		 * chapters = new ArrayList<Chapter>(); chapters.add(chapter1);
		 * chapters.add(chapter2); return chapters;
		 */
		List<Chapter> chapters = new ArrayList<Chapter>();
		/*
		 * Cursor cursor = database.query("chapter", null, "book_id=" + bookId,
		 * null, null, null, null);
		 */
		String[] args = { "" + bookId };
		Cursor cursor = database.query("chapter", null, "book_id=?", args,
				null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Chapter chapter = new Chapter(cursor.getInt(0), cursor.getInt(2),
					0, cursor.getString(1));
			chapters.add(chapter);
			cursor.moveToNext();
		}
		cursor.close();
		return chapters;
	}

	public List<Chapter> getChapters(String bookName) {
		return null;
	}

	public List<Verse> getVerses(int chapterId) {
		// Mock some data here
		/*
		 * Verse v1 = new Verse(1, chapterId, 1, "Verse 1"); Verse v2 = new
		 * Verse(2, chapterId, 2, "Verse 2"); List<Verse> verses = new
		 * ArrayList<Verse>(); verses.add(v1); verses.add(v2); return verses;
		 */
		List<Verse> verses = new ArrayList<Verse>();
		String[] args = { "" + chapterId };
		Cursor cursor = database.query("verse", null, "chapter_id=?", args,
				null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Verse verse = new Verse(cursor.getInt(0), cursor.getInt(2), 0, cursor.getString(1));
			verses.add(verse);
			cursor.moveToNext();
		}
		cursor.close();
		return verses;
	}

	public List<Verse> getVerses(String bookName, int chapterNum) {
		return null;
	}

	public List<Verse> getVerses(int bookId, int chapterNum) {
		return null;
	}
}
