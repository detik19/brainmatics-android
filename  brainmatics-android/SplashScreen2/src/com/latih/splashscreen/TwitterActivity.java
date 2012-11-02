package com.latih.splashscreen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.latih.model.TwitterSearchModel;
import com.latih.model.twitter;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.view.Menu;
import android.widget.ListView;

public class TwitterActivity extends ListActivity {


	List<twitter> mlist=new ArrayList<twitter>();
	private String murl="http://search.twitter.com/search.json?q=";
	private String muser="@detik19";
	private String mresponse;
	private CustomAdapter adapter;
	
	public String getResponseFromURL(String url) throws ClientProtocolException, IOException{
		String response=null;
		HttpClient httpClient=new DefaultHttpClient();
		
		HttpGet httpGet=new HttpGet(url);
		
		ResponseHandler<String> responseHandler= new BasicResponseHandler();
		
		response=httpClient.execute(httpGet,responseHandler);
		
		return response;
		
		
	}
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter);
        
        
       new GetTwitterClient().execute(muser);
       
       ListView lv= (ListView) findViewById(R.id.listView1);
       adapter=new CustomAdapter(this, mlist);
       setListAdapter(adapter);
       
     
	
       
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_twitter, menu);
        return true;
    }
    
    
   /**
    * Class extends ASynTask untuk respon URL
    * @author Student 5
    *
    */
    class GetTwitterClient extends AsyncTask<String, Void, String>{

	


		@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		if(result==null){
			adapter.notifyDataSetChanged();
		}
		super.onPostExecute(result);
	}

		@Override
		protected String doInBackground(String... params) {
			
//				try {
//					mresponse=  getResponseFromURL("http://search.twitter.com/search.json?q="+params[0]);
//
//					JSONObject json = new JSONObject(mresponse);
//					JSONArray jArray=json.getJSONArray("results");
//					for (int i=0;i<jArray.length();i++){
//						json=jArray.getJSONObject(i);
//						twitter atwitter=new twitter();
//						atwitter.setFrom_user_name(json.getString("from_user_name"));
//						atwitter.setText(json.getString("text"));
//						mlist.add(atwitter);
//					}
//					
//
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (ClientProtocolException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			// ganti proses parsing dengan GSON
			try {
				mresponse=  getResponseFromURL("http://search.twitter.com/search.json?q="+params[0]);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Gson gson = new Gson();
			TwitterSearchModel twitterSearchModel = gson.fromJson(
					mresponse, TwitterSearchModel.class);
			mlist.addAll(twitterSearchModel.getResults());
				
			
			
			return null;
		}
    	
    }
}
