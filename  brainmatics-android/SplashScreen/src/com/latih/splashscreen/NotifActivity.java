package com.latih.splashscreen;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NotifActivity extends Activity {

	EditText mTicker,mTitle,mMsg;
    String ns = Context.NOTIFICATION_SERVICE;
	NotificationManager mNotificationManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif);
       
        mTicker=(EditText) findViewById(R.id.editTextTicker);
        mTitle=(EditText) findViewById(R.id.editTextTittle);
        mMsg=(EditText) findViewById(R.id.editTextmsg);
        Button btnNotif=(Button)findViewById(R.id.buttonnotif);
        mNotificationManager=(NotificationManager) getSystemService(ns);        
         btnNotif.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				shownotif();
			}
		});
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_notif, menu);
        return true;
    }
    
    private void shownotif(){
    	
    	
    	int icon=R.drawable.ic_launcher;
        long when = System.currentTimeMillis()+5000;

        CharSequence tickerText=mTicker.getText().toString();
        CharSequence titleText=mTitle.getText().toString();
        CharSequence msgText=mMsg.getText().toString();
        
        
        Intent intent = new Intent(this, SplachAct.class);
        PendingIntent contentInten= PendingIntent.getActivity(this, 0, intent, 0);
        
        Notification notif=new Notification(icon, tickerText, when);
        notif.setLatestEventInfo(this, titleText, msgText, contentInten);
       mNotificationManager.notify(1,notif);
       
        
        
        

    }
}
