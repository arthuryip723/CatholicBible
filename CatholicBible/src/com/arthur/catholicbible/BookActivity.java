package com.arthur.catholicbible;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class BookActivity extends CBActivity {
	GridView chaptersView;
	Book book;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book);
		chaptersView = (GridView) findViewById(R.id.chaptersView);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			/*
			 * String value = extras.getString("BOOK"); TextView view =
			 * (TextView) findViewById(R.id.chaptersView); view.setText(value);
			 * view.setText(((CBApplication)this.getApplication()).foo);
			 * 
			 * List<Chapter> chapters = getDataSource().getChapters(value);
			 */

//			Book book = (Book) extras.getSerializable("BOOK");
			book = (Book) extras.getSerializable("BOOK");
			TextView view = (TextView) findViewById(R.id.titleView);
			view.setText(book.toString());

			List<Chapter> chapters = getDataSource().getChapters(book.getId());
			// Set the chapters to the view
			ArrayAdapter<Chapter> adapter = new ArrayAdapter<Chapter>(this,
					android.R.layout.simple_list_item_1, chapters);
			chaptersView.setAdapter(adapter);
			// Set onItemClick
		}

		/*Button backToBooksBtn = (Button) findViewById(R.id.backToBooksBtn);
		backToBooksBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(BookActivity.this, MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);

				finish();
			}
		});*/

		/*
		 * gridView = (GridView) findViewById(R.id.chaptersGridView);
		 * 
		 * ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		 * android.R.layout.simple_list_item_1, chapters);
		 * 
		 * gridView.setAdapter(adapter);
		 */

		chaptersView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				// TODO Auto-generated method stub
				// Intent intent = new Intent(Chapters.this, Verses.class);
				Intent intent = new Intent(getApplicationContext(),
						ChapterActivity.class);
				/*
				 * Object item = parent.getItemAtPosition(position);
				 * intent.putExtra("BOOK", item.toString());
				 */
				Chapter chapter = (Chapter) parent.getItemAtPosition(position);
				intent.putExtra("CHAPTER", chapter);
				intent.putExtra("BOOK", book);
				startActivity(intent);
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.book, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			Toast.makeText(this, "menu in book", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.action_books:
			finish();
			break;
		default:
			break;
		}
		return true;
	}
}
