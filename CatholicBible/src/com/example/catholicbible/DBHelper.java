package com.example.catholicbible;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	static final String dbName = "CatholicBible.db";
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
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("PRAGMA foreign_keys = ON");
		db.execSQL("create table book(id integer primary key, name text);");
		db.execSQL("insert into book(name) values('book 1');");
		db.execSQL("insert into book(name) values('book 2');");
		db.execSQL("create table chapter(id integer primary key, name text, book_id integer, "
				+ "foreign key(book_id) references book(id));");
		db.execSQL("insert into chapter(name, book_id) values('c 1 b 1', 1);");
		db.execSQL("insert into chapter(name, book_id) values('c 2 b 1', 1);");
		db.execSQL("insert into chapter(name, book_id) values('c 1 b 2', 2);");
		db.execSQL("insert into chapter(name, book_id) values('c 2 b 2', 2);");
		db.execSQL("create table verse(id integer primary key, content text, chapter_id integer, "
				+ "foreign key(chapter_id) references chapter(id));");
		db.execSQL("insert into verse(content, chapter_id) values('v 1 c1 b1', 1);");
		db.execSQL("insert into verse(content, chapter_id) values('v 2 c1 b1', 1);");
		db.execSQL("insert into verse(content, chapter_id) values('v 1 c2 b1', 2);");
		db.execSQL("insert into verse(content, chapter_id) values('v 2 c2 b1', 2);");
		db.execSQL("insert into verse(content, chapter_id) values('v 1 c1 b2', 3);");
		db.execSQL("insert into verse(content, chapter_id) values('v 2 c1 b2', 3);");
		db.execSQL("insert into verse(content, chapter_id) values('v 1 c2 b2', 4);");
		db.execSQL("insert into verse(content, chapter_id) values('v 2 c2 b2', 4);");
		db.execSQL("insert into verse(content, chapter_id) values('v 1 c2 b2', 4);");
		db.execSQL("insert into verse(content, chapter_id) values('v 2 c2 b2', 4);");
		db.execSQL("insert into verse(content, chapter_id) values('v 3 c2 b2', 4);");
		db.execSQL("insert into verse(content, chapter_id) values('v 4 c2 b2', 4);");
		db.execSQL("insert into verse(content, chapter_id) values('v 5 c2 b2', 4);");
		db.execSQL("insert into verse(content, chapter_id) values('v 6 c2 b2', 4);");
		db.execSQL("insert into verse(content, chapter_id) values('v 7 c2 b2', 4);");
		db.execSQL("insert into verse(content, chapter_id) values('v 9 c2 b2', 4);");
		db.execSQL("insert into verse(content, chapter_id) values('v 10 c2 b2', 4);");
		db.execSQL("insert into verse(content, chapter_id) values('v 11 c2 b2', 4);");
		db.execSQL("insert into verse(content, chapter_id) values('v 12 c2 b2', 4);");

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
	}

	public void getChaptersByBook() {

	}

	public void getVersesByBookAndChapter() {

	}

}
