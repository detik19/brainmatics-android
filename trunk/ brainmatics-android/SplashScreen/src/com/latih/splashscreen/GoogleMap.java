package com.latih.splashscreen;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class GoogleMap extends MapActivity {

	MapView mMap;
	MapController aMapController;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);
        mMap=(MapView) findViewById(R.id.map);
        
        aMapController=mMap.getController();
        mMap.setClickable(true);
        mMap.setEnabled(true);
        mMap.setBuiltInZoomControls(true);
        mMap.setStreetView(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_google_map, menu);
        return true;
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
