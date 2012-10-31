package com.example.day3;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GPSActivity extends Activity implements OnClickListener {

	LocationManager manager;
	LocationListener listener = new LocationListener() {

		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}

		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}

		public void onLocationChanged(Location location) {
			textView.setText(location.getLatitude() + "");
			textView2.setText(location.getLongitude() + "");
			Toast.makeText(getApplicationContext(), "Location changed", Toast.LENGTH_SHORT).show();
		}
	};
	Button button;
	TextView textView;
	TextView textView2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gps);

		manager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		button = (Button) findViewById(R.id.buttonGps1);
		button.setOnClickListener(this);
		
		textView = (TextView) findViewById(R.id.lat);
		textView2 = (TextView) findViewById(R.id.lon);
		
		manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
		manager.removeUpdates(listener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_gps, menu);
		return true;
	}
	
	@Override
	protected void onDestroy() {
		manager.removeUpdates(listener);
		super.onDestroy();
	}

	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.buttonGps1:
			manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
			manager.removeUpdates(listener);
			break;
		default:
			break;
		}
	}
}
