package com.example.catholicbible;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class VerseAdapter<T> extends ArrayAdapter<T> {

	public VerseAdapter(Context context, int resource, List<T> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		TextView result = (TextView)super.getView(position, convertView, parent);
/*	    if (convertView == null) {
	    	result = null;
	    } else {
	        result = (View) convertView;
	    }*/
		TextView textView = (TextView) result.findViewById(android.R.id.text1);

		Verse verse = (Verse)getItem(position);
		if (verse.getIndex() == 0)
			textView.setTextColor(Color.RED);
		
		return result;
	}
	
}
