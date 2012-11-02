package com.brainmatics.notepad;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.brainmatics.notepad.db.DataHelper;

public class MyNotepadActivity extends ListActivity implements OnClickListener {
	DataHelper dh;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        
        findViewById(R.id.btn_add).setOnClickListener(this);
        
        dh = new DataHelper(this);
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	Cursor c = dh.getAll();
        String[]from = new String[]{"title"};
        int[]to= new int[]{android.R.id.text1};
        
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, 
        		android.R.layout.simple_list_item_1, 
        		c, from, to);
        setListAdapter(adapter);
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_add:
			startActivity(new Intent(this, FormActivity.class));
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent(this, FormActivity.class);
		intent.putExtra("_id", id);
		startActivity(intent);
	}
	
	
	
	
	
	
	
	
	
	
	
}