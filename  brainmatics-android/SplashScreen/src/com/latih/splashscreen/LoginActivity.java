package com.latih.splashscreen;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {

	private final String murl="http://toresto.com/restaurantlogin.php";
	private EditText musername;
	private EditText mpass;
	private TextView mtv;
	String result = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        Button btnlogin=(Button) findViewById(R.id.btnlogin);
        musername=(EditText) findViewById(R.id.editTextuser);
        mpass=(EditText)findViewById(R.id.editTextpass);
        mtv=(TextView) findViewById(R.id.textView1);
        
        btnlogin.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {

				new LoginTask().execute();
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_login, menu);
        return true;
    }
    
    private String sendData(String username, String password) throws ClientProtocolException, IOException{
    	String res="";
    	
    	HttpClient httpClient= new DefaultHttpClient();
    	HttpPost httpPost= new HttpPost(murl);
    	
    	ArrayList<NameValuePair> data =new ArrayList<NameValuePair>(2);
    	data.add(new BasicNameValuePair("username", username));
    	data.add(new BasicNameValuePair("password", password));
    	httpPost.setEntity(new UrlEncodedFormEntity(data));
    	HttpResponse response=httpClient.execute(httpPost);
    	
    	HttpEntity entity= response.getEntity();
    	res=EntityUtils.toString(entity);
    	
    	return res;
    	
    	
    }
    
    class LoginTask extends AsyncTask<String, String , String>{

		@Override
		protected String doInBackground(String... params) {
			try {
				 result=sendData(musername.getText().toString(), mpass.getText().toString());
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return result;
		}
    	
    }
}
