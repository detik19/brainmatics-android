package com.latih.splashscreen;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class SplachAct extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach);
        
        Button btnKamera= (Button) findViewById(R.id.button1);
        
        btnKamera.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {

				Intent intent=new Intent(getApplicationContext(), CameraActivity.class);
				startActivity(intent);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_splach, menu);
        return true;
    }
}
