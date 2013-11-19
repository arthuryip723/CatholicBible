package com.example.catholicbible;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ChapterActivity extends CBActivity {
	ListView versesView;
	TextView titleView;
	Chapter chapter;
	Book book;
//	ArrayAdapter<Verse> verseAdapter;
	VerseAdapter<Verse> verseAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chapter);
		versesView = (ListView) findViewById(R.id.versesView);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
//			Chapter chapter = (Chapter) extras.getSerializable("CHAPTER");
			chapter = (Chapter) extras.getSerializable("CHAPTER");
			book = (Book) extras.getSerializable("BOOK");
//			TextView view = (TextView) findViewById(R.id.titleView);
//			view.setText(chapter.toString());
			titleView = (TextView) findViewById(R.id.titleView);
//			titleView.setText(chapter.toString());
			titleView.setText(book.toString() + " " + chapter.toString());
//			List<Verse> verses = getDataSource().getVerses(chapter.getId());
			List<Verse> verses = getDataSource().getVerses(chapter);
//			ArrayAdapter<Verse> adapter = new ArrayAdapter<Verse>(this,
//					android.R.layout.simple_list_item_1, verses);
//			versesView.setAdapter(adapter);
			/*verseAdapter = new ArrayAdapter<Verse>(this,
					android.R.layout.simple_list_item_1, verses);*/
			verseAdapter = new VerseAdapter<Verse>(this,
					android.R.layout.simple_list_item_1, verses);
			versesView.setAdapter(verseAdapter);
			// Set the verses to the view
		}

/*		Button backToBooksBtn = (Button) findViewById(R.id.backToBooksBtn);
		backToBooksBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// Intent intent = new Intent(Verses.this, MainActivity.class);
				Intent intent = new Intent(getApplicationContext(),
						MainActivity.class);
				// intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_SINGLE_TOP);
				// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);

				// finish();
			}
		});*/
		
		Button prevBtn = (Button) findViewById(R.id.prevBtn);
		prevBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Chapter tempChapter = getDataSource().getPreviousChapter(chapter);
				if (tempChapter != null) {
					chapter = tempChapter;
//					titleView.setText(chapter.toString());
					titleView.setText(book.toString() + " " + chapter.toString());
					List<Verse> verses = getDataSource().getVerses(chapter);
					verseAdapter.clear();
					verseAdapter.addAll(verses);
//					versesView.smoothScrollToPosition(0);
					versesView.setSelection(0);
				}
			}
		});
		
		Button nextBtn = (Button) findViewById(R.id.nextBtn);
		nextBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				if (chapter.getIndex() < chapter.getMaxChapterIndexInBook()) {
//					int nextIndex = chapter.getIndex() + 1;
//					chapter = getDataSource().getChapterByBookIdAndChapterIndex(chapter.getBookId(), nextIndex);
				Chapter tempChapter = getDataSource().getNextChapter(chapter, book);
				if (tempChapter != null) {
					chapter = tempChapter;
					titleView.setText(book.toString() + " " + chapter.toString());
					List<Verse> verses = getDataSource().getVerses(chapter);
					verseAdapter.clear();
					verseAdapter.addAll(verses);
//					versesView.smoothScrollToPosition(0);
					versesView.setSelection(0);
				}
			}
		});

/*		Button backToChaptersBtn = (Button) findViewById(R.id.backToChaptersBtn);
		backToChaptersBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chapter, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			Toast.makeText(this, "menu in chapter", Toast.LENGTH_SHORT)
					.show();
			break;
	    case R.id.action_chapters:
			finish();
	        break;
	    case R.id.action_books:
			Intent intent = new Intent(getApplicationContext(),
					MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
					| Intent.FLAG_ACTIVITY_SINGLE_TOP);
			startActivity(intent);
	        break;
        default:
        	break;
		}
		return true;
	}
}
