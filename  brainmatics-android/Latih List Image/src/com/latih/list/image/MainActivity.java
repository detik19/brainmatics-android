package com.latih.list.image;

import java.util.List;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	Menu myMenu;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button speakButton = (Button) findViewById(R.id.button1);
        
        
        PackageManager pm = getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(
                new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        if (activities.size() != 0) {
            speakButton.setOnClickListener(this);
        } else {
            speakButton.setEnabled(false);
            speakButton.setText("Recognizer not present");
        }
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.activity_main, menu);
        super.onCreateOptionsMenu(menu);
        this.myMenu=menu;
        
        menu.add(1, 1, 1, "Tes").setIntent(new Intent(Intent.ACTION_DIAL));
        menu.add(1,2,2,"item 2");
    	return true;
    }

	public void onClick(View v) {
		if (v.getId() == R.id.button1) {
			Intent intent=new Intent(this, resultActivity.class);
			startActivity(intent);
        }		
	}
}
