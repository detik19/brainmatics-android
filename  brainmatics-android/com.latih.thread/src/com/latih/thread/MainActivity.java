package com.latih.thread;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	TextView mtv;
	int i;
	ProgressDialog dialog;
	SharedPreferences pref;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mtv=(TextView) findViewById(R.id.textView1);
        mtv.setText("0");
        Button button=(Button) findViewById(R.id.button1);
        dialog= new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMax(5);
        dialog.setMessage("Terserah");
        dialog.show();
        
        new MyCounterTask().execute(0);
        
        final EditText nicknameText= (EditText) findViewById(R.id.editText1);
        pref=this.getSharedPreferences("Pref", Context.MODE_PRIVATE);
        if (pref.contains("NICKNAME")){
        	nicknameText.setText(pref.getString("NICKNAME", ""));
        	
        }
        
        button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pref=getSharedPreferences("Pref", Context.MODE_PRIVATE);
				EditText nicknameText= (EditText) findViewById(R.id.editText1);			
				Editor editor =pref.edit();
				editor.putString("NICKNAME", nicknameText.getText().toString());
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    class MyCounterTask extends AsyncTask<Integer, Integer, String>{

		@Override
		protected String doInBackground(Integer... params) {
		
			i=params[0];
			while(i<5)
			{
				try{
					Thread.sleep(1000);
					i++;
					publishProgress(i);
				}
				catch(InterruptedException e)
				{
					
				}
			}
			return "Wes bar dul";
		}

		@Override
		protected void onPreExecute() {
			Toast.makeText(MainActivity.this, "Mulai dul", Toast.LENGTH_LONG).show();

		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			//super.onProgressUpdate(values);
			dialog.setProgress(values[0]);
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			dialog.dismiss();
		}
    	
    	
    }
}
