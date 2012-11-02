package com.brainmatics.notepad;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;

import com.brainmatics.notepad.db.DataHelper;

public class FormActivity extends Activity  {
	EditText inpTitle;
	EditText inpContent;
	
	long id;
	
	DataHelper dh;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);
        
        inpTitle = (EditText) findViewById(R.id.inp_title);
        inpContent= (EditText) findViewById(R.id.inp_content);
        
        dh = new DataHelper(this);
        
        id = getIntent().getLongExtra("_id", 0);
        if(id!=0){//read id notepad
        	Cursor c = dh.getById((int)id);
        	startManagingCursor(c);
        	if(c.moveToFirst()){
        		do { //untuk contoh kalau data banyak
        			String title = c.getString(c.getColumnIndex("title")); 
        			String content = c.getString(c.getColumnIndex("content"));
        			inpTitle.setText(title);
        			inpContent.setText(content);
					//TODO masukkan ke view
				} while (c.moveToNext());
        	}
        }
    }
    
    @Override
    protected void onPause() {
    	//cek apakah insert atau update
    	if(id==0)//bukan edit-->new
    		id = dh.insert2(inpTitle.getText().toString(), inpContent.getText().toString());
    	else
    		dh.updateById((int)id, inpTitle.getText().toString(), inpContent.getText().toString());
    	super.onPause();
    }
}