package com.latih.alertdialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	final CharSequence[] items=  {"Red","Green", "Blue"};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Pick a Color");
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), items[which], Toast.LENGTH_SHORT).show();
				showCustomDialog();
			}
		});
        
        AlertDialog alert = builder.create();
        alert.show();
        
    }

   
    
    public void showCustomDialog()
    {
    	
    	Dialog aDialog = new Dialog(this);
    	aDialog.setContentView(R.layout.custom_dialog);
    	aDialog.setTitle("Custom Dialog");
    	 
    	TextView text=(TextView) aDialog.findViewById(R.id.textView1);
    	text.setText("Halo iki dialog sing di kastem");
    	ImageView image=(ImageView) aDialog.findViewById(R.id.imageView1);
    	image.setImageResource(R.drawable.ic_launcher);
    	aDialog.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
