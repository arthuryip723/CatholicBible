package com.example.catholicbible;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CBDataSource {
	private SQLiteDatabase database;
	// private DBHelper dbHelper;
	private AssetDBHelper dbHelper;

	public CBDataSource(Context context) {
		// dbHelper = new DBHelper(context);
		dbHelper = new AssetDBHelper(context);
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
			Book book = new Book(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
			// cursor.getString(2);
			books.add(book);
			cursor.moveToNext();
		}
		// return null;
		cursor.close();
		return books;

		// List<Book> books = new ArrayList<Book>(); Book b1 = new Book(1,
		// "Book 1"); Book b2 = new Book(2, "Book 2"); books.add(b1);
		// books.add(b2); return books;
	}

	public List<Book> getOldTestament() {
		List<Book> books = new ArrayList<Book>();
		/*
		 * Cursor cursor = database.query("book", null, null, null, null, null,
		 * null);
		 */
		String[] args = { "1" };
		Cursor cursor = database.query("book", null, "testament_id=?", args,
				null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Book book = new Book(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
			books.add(book);
			cursor.moveToNext();
		}
		cursor.close();
		return books;
	}

	public List<Book> getNewTestament() {
		List<Book> books = new ArrayList<Book>();
		/*
		 * Cursor cursor = database.query("book", null, null, null, null, null,
		 * null);
		 */
		String[] args = { "2" };
		Cursor cursor = database.query("book", null, "testament_id=?", args,
				null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Book book = new Book(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
			books.add(book);
			cursor.moveToNext();
		}
		cursor.close();
		return books;
	}

	public List<Chapter> getChapters(int bookId) {
		// Mock some data here

		// Chapter chapter1 = new Chapter(1, bookId, 1, "Chapter 1"); Chapter
		// chapter2 = new Chapter(2, bookId, 2, "Chapter 2"); List<Chapter>
		// chapters = new ArrayList<Chapter>(); chapters.add(chapter1);
		// chapters.add(chapter2); return chapters;

		List<Chapter> chapters = new ArrayList<Chapter>();

		// Cursor cursor = database.query("chapter", null, "book_id=" + bookId,
		// null, null, null, null);

		String[] args = { "" + bookId };
		Cursor cursor = database.query("chapter", null, "book_id=?", args,
				null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			/*
			 * Chapter chapter = new Chapter(cursor.getInt(0), cursor.getInt(2),
			 * 0, cursor.getString(1));
			 */
			Chapter chapter = new Chapter(cursor.getInt(0),
					cursor.getString(1), cursor.getInt(2), cursor.getInt(3),
					cursor.getCount());
			chapters.add(chapter);
			cursor.moveToNext();
		}
		cursor.close();
		return chapters;
	}

	public List<Chapter> getChapters(String bookName) {
		return null;
	}

	public Chapter getChapterById(int id) {
		return null;
	}

	public Chapter getChapterByBookIdAndChapterIndex(int bookId,
			int chapterIndex) {
		String[] args = { "" + bookId, "" + chapterIndex };
		Cursor cursor = database.query("chapter", null,
				"book_id=? AND number=?", args, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Chapter chapter = new Chapter(cursor.getInt(0),
					cursor.getString(1), cursor.getInt(2), cursor.getInt(3),
					cursor.getCount());
			return chapter;
		}
		return null;
	}

/*	public List<Verse> getVerses(int chapterId) {
		// Mock some data here

		// Verse v1 = new Verse(1, chapterId, 1, "Verse 1"); Verse v2 = new
		// Verse(2, chapterId, 2, "Verse 2"); List<Verse> verses = new
		// ArrayList<Verse>(); verses.add(v1); verses.add(v2); return verses;

		List<Verse> verses = new ArrayList<Verse>();
		String[] args = { "" + chapterId };
		Cursor cursor = database.query("verse", null, "chapter_id=?", args,
				null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Verse verse = new Verse(cursor.getInt(0), cursor.getString(1),
					cursor.getInt(3), chapterId, 0);
			verses.add(verse);
			cursor.moveToNext();
		}
		cursor.close();
		return verses;
	}*/

	public List<Verse> getVerses(Chapter chapter) {
		// Mock some data here

		// Verse v1 = new Verse(1, chapterId, 1, "Verse 1"); Verse v2 = new
		// Verse(2, chapterId, 2, "Verse 2"); List<Verse> verses = new
		// ArrayList<Verse>(); verses.add(v1); verses.add(v2); return verses;

		List<Verse> verses = new ArrayList<Verse>();
		String[] args = { "" + chapter.getId() };
		Cursor cursor = database.query("verse", null, "chapter_id=?", args,
				null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Verse verse = new Verse(cursor.getInt(0), cursor.getString(1),
					cursor.getInt(3), chapter.getId(), chapter.getIndex());
			verses.add(verse);
			cursor.moveToNext();
		}
		cursor.close();
		return verses;
	}

	public List<Verse> getVerses(String bookName, int chapterIndex) {
		return null;
	}

	public List<Verse> getVerses(int bookId, int chapterIndex) {
		return null;
	}

	public Chapter getNextChapter(Chapter chapter, Book book) {
//		if (chapter.getIndex() < chapter.getMaxChapterIndexInBook()) {
		if (chapter.getIndex() < book.getNumOfChapters()) {
			int nextIndex = chapter.getIndex() + 1;
			Chapter result = getChapterByBookIdAndChapterIndex(
					chapter.getBookId(), nextIndex);
			return result;
		} else
			return null;
	}

	public Chapter getPreviousChapter(Chapter chapter) {
		if (chapter.getIndex() > 0) {
			int nextIndex = chapter.getIndex() - 1;
			Chapter result = getChapterByBookIdAndChapterIndex(
					chapter.getBookId(), nextIndex);
			return result;
		} else
			return null;
	}
}
