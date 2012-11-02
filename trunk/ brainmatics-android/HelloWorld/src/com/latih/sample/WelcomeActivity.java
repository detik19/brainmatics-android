package com.latih.sample;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeActivity extends Activity 
{
	
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Button backButton=(Button) findViewById(R.id.button1);
        TextView tv=(TextView) findViewById(R.id.textView1);
        String username=getIntent().getExtras().getString("username");
        tv.setText(username);
        
        backButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent intent= new Intent();
				//intent.putExtra("hasil", "Coba");
				setResult(RESULT_OK, intent);
				finish();
				
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_welcome, menu);
        return true;
    }
}
