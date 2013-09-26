package com.example.catholicbible;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends CBActivity {
	GridView gridView;

	static final String[] books = new String[] { "book1", "book2", "book3",
			"book4", "book5", "book6" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		gridView = (GridView) findViewById(R.id.booksGridView);
		
		getDataSource();
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, books);
		
		gridView.setAdapter(adapter);
		
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				// Switch to another view
				//Intent intent = new Intent(MainActivity.this, Chapters.class);
				Intent intent = new Intent(getApplicationContext(), Chapters.class);
				//intent.putExtra("BOOK", "Mathew");
				Object item = parent.getItemAtPosition(position);
				intent.putExtra("BOOK", item.toString());
				startActivity(intent);
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// my new line

}
