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
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends CBActivity {
	GridView ntView, otView;
	TabHost tabHost;
	TabSpec otTab, ntTab;

	// private CBDataSource dataSource;

	/*
	 * static final String[] books = new String[] { "book1", "book2", "book3",
	 * "book4", "book5", "book6" };
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tabHost = (TabHost)findViewById(android.R.id.tabhost);
		tabHost.setup();
		/*TabSpec otTab = tabHost.newTabSpec("New Testament Tab");
		TabSpec ntTab = tabHost.newTabSpec("Old Testament Tab");*/
		otTab = tabHost.newTabSpec("New Testament Tab");
		ntTab = tabHost.newTabSpec("Old Testament Tab");
		
		otTab.setIndicator(getString(R.string.old_testament));
		ntTab.setIndicator(getString(R.string.new_testament));
		otTab.setContent(R.id.otView);
		ntTab.setContent(R.id.ntView);
		tabHost.addTab(otTab);
		tabHost.addTab(ntTab);

		ntView = (GridView) findViewById(R.id.ntGridView);

		// Get the datasource
		/*
		 * if (dataSource == null) { dataSource = getDataSource(); }
		 */
		/**
		 * Retrieve the books from the datasource. And put them in a collection.
		 */

		// List<Book> books = dataSource.getAllBooks();
		// List<Book> books = getDataSource().getAllBooks();
		List<Book> ntBooks = getDataSource().getNewTestament();

		/*ArrayAdapter<Book> adapter = new ArrayAdapter<Book>(this,
				android.R.layout.simple_list_item_1, ntBooks);

		ntView.setAdapter(adapter);*/
		
		ArrayAdapter<Book> ntAdapter = new ArrayAdapter<Book>(this,
				android.R.layout.simple_list_item_1, ntBooks);

		ntView.setAdapter(ntAdapter);

		ntView.setOnItemClickListener(new OnItemClickListener() {

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
		
		otView = (GridView) findViewById(R.id.otGridView);

		// Get the datasource
		/*
		 * if (dataSource == null) { dataSource = getDataSource(); }
		 */
		/**
		 * Retrieve the books from the datasource. And put them in a collection.
		 */

		// List<Book> books = dataSource.getAllBooks();
		List<Book> otBooks = getDataSource().getOldTestament();

		ArrayAdapter<Book> otAdapter = new ArrayAdapter<Book>(this,
				android.R.layout.simple_list_item_1, otBooks);

		otView.setAdapter(otAdapter);

		otView.setOnItemClickListener(new OnItemClickListener() {

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
		
//		ActionBar actionBar = getActionBar();
//		actionBar.setSubtitle("mytest");
//		actionBar.setTitle("vogella.com"); 
//		actionBar.hide();
//		// more stuff here...
//		actionBar.show(); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// my new line

}
