package com.latih.splashscreen;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.webkit.WebView;


public class WebViewActivity extends Activity {
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode==KeyEvent.KEYCODE_BACK && mWebView.canGoBack()){
			mWebView.goBack();
		}
		
		return super.onKeyDown(keyCode, event);

	}

	WebView mWebView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        
        mWebView=(WebView) findViewById(R.id.webView1);
        
        mWebView.loadUrl("http://blognuklir.wordpress.com");
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_web_view, menu);
        return true;
    }
}
