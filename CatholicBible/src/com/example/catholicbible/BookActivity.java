package com.example.catholicbible;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class BookActivity extends CBActivity {
	GridView gridView;

	static final String[] chapters = new String[] { "chapter1", "chapter2", "chapter3",
			"chapter4", "chapter5", "chapter6" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chapters);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			String value = extras.getString("BOOK");
			TextView view = (TextView) findViewById(R.id.chaptersView);
			view.setText(value);
			view.setText(((CBApplication)this.getApplication()).foo);
			
			List<Chapter> chapters = getDataSource().getChapters(value);
			// Set the chapters to the view;
		}

		Button backToBooksBtn = (Button) findViewById(R.id.backToBooksBtn);
		backToBooksBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*
				 * Intent intent = new Intent(Chapters.this,
				 * MainActivity.class);
				 * intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				 * startActivity(intent);
				 */
				finish();
			}
		});
		
		gridView = (GridView) findViewById(R.id.chaptersGridView);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, chapters);
		
		gridView.setAdapter(adapter);
		
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				//Intent intent = new Intent(Chapters.this, Verses.class);
				Intent intent = new Intent(getApplicationContext(), ChapterActivity.class);
				Object item = parent.getItemAtPosition(position);
				intent.putExtra("BOOK", item.toString());
				startActivity(intent);
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chapters, menu);
		return true;
	}

	// add a line here

}
