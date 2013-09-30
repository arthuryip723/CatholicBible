package com.example.catholicbible;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class MainActivity extends CBActivity {
	GridView gridView;

	// private CBDataSource dataSource;

	/*
	 * static final String[] books = new String[] { "book1", "book2", "book3",
	 * "book4", "book5", "book6" };
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		gridView = (GridView) findViewById(R.id.booksGridView);

		// Get the datasource
		/*
		 * if (dataSource == null) { dataSource = getDataSource(); }
		 */
		/**
		 * Retrieve the books from the datasource. And put them in a collection.
		 */

		// List<Book> books = dataSource.getAllBooks();
		List<Book> books = getDataSource().getAllBooks();

		ArrayAdapter<Book> adapter = new ArrayAdapter<Book>(this,
				android.R.layout.simple_list_item_1, books);

		gridView.setAdapter(adapter);

		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				// TODO Auto-generated method stub
				// Switch to another view
				// Intent intent = new Intent(MainActivity.this,
				// Chapters.class);
				Intent intent = new Intent(getApplicationContext(),
						BookActivity.class);
				// intent.putExtra("BOOK", "Mathew");
				// Object item = parent.getItemAtPosition(position);
				// intent.putExtra("BOOK", item.toString());
				Book book = (Book) parent.getItemAtPosition(position);
				intent.putExtra("BOOK", book);
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
