package com.example.catholicbible;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ChapterActivity extends CBActivity {
	ListView versesView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chapter);
		versesView = (ListView) findViewById(R.id.versesView);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			Chapter chapter = (Chapter) extras.getSerializable("CHAPTER");
			TextView view = (TextView) findViewById(R.id.titleView);
			view.setText(chapter.toString());
			List<Verse> verses = getDataSource().getVerses(chapter.getId());
			ArrayAdapter<Verse> adapter = new ArrayAdapter<Verse>(this,
					android.R.layout.simple_list_item_1, verses);
			versesView.setAdapter(adapter);
			// Set the verses to the view
		}

		Button backToBooksBtn = (Button) findViewById(R.id.backToBooksBtn);
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
		});

		Button backToChaptersBtn = (Button) findViewById(R.id.backToChaptersBtn);
		backToChaptersBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.verses, menu);
		return true;
	}

}
