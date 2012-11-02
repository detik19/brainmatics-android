package com.latih.sample;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.LoginFilter.UsernameFilterGeneric;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
    private static final int REQUEST_CODE = 1;
	public EditText usermame,mpass;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        usermame=(EditText) findViewById(R.id.editText2);
        mpass=(EditText) findViewById(R.id.editText1);
        
        
        Button loginButton = (Button) findViewById(R.id.button1);
        loginButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
				Intent intent =new Intent(getApplicationContext(),WelcomeActivity.class);
				intent.putExtra("username", usermame.getText().toString());
				//startActivity(intent);
				startActivityForResult(intent, REQUEST_CODE);
			}
		});
        
        
        
    }

 
    
    
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
    		usermame.setText("OK");
    	}
	}




	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
