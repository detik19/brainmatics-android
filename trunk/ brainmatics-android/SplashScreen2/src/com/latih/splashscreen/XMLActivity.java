package com.latih.splashscreen;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class XMLActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_xml, menu);
        return true;
    }
}
