package com.latih.splashscreen;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GPSActivity extends Activity {

	 TextView mtextLatt;
	 TextView mtextLong;
	 LocationManager   mlocationManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        Button btnGPS= (Button) findViewById(R.id.button1);
        mtextLatt=(TextView) findViewById(R.id.textLattitude);
        mtextLong=(TextView) findViewById(R.id.textLong);
        mlocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        btnGPS.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				getGPS();
				
			}
		});
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_gps, menu);
        return true;
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	mlocationManager.removeUpdates(aLocationListener);
    };
    private void getGPS()
    {
    	
    	mlocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,aLocationListener);
    }
    
    LocationListener aLocationListener=new LocationListener() {
		
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
			
			mtextLatt.setText(location.getLatitude()+"");
			mtextLong.setText(location.getLongitude()+"");
			
		}
	};
}
