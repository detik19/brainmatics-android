package com.latih.splashscreen;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SplashScreen extends Activity {
	private Context ctx=this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        LinearLayout rootlayout= (LinearLayout)findViewById(R.id.LinearLayout1);

        TextView logo1=(TextView) findViewById(R.id.textView1);
        Animation fade1=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        rootlayout.startAnimation(fade1);
        logo1.startAnimation(fade1);
        
        Thread athread= new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        startActivity(new Intent(ctx,SplachAct.class ));

			}
		});
        athread.start();
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_splash_screen, menu);
        return true;
    }
}
