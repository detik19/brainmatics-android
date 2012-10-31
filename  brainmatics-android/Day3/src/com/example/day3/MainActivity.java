package com.example.day3;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Animation fade = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        
        LinearLayout layout = (LinearLayout) findViewById(R.id.LinearLayout1);
        TextView textView = (TextView) findViewById(R.id.textView1);
        
        layout.startAnimation(fade);
        
        Thread thread = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
				startActivity(intent);
				finish();
			}
		});
        
        thread.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
