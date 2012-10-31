package com.latih.splashscreen;

import java.util.ArrayList;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.OverlayItem;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.Drawable;
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
       //mMap.setStreetView(true);
      //aMapController.setZoom(16);
        aMapController.setCenter(new GeoPoint((int)(-7.816021* 1E6),(int) (112.011402*1E6)));

        
        final MyLocationOverlay overlay = new MyLocationOverlay(this, mMap);
        overlay.enableCompass();
        overlay.enableMyLocation();
        overlay.runOnFirstFix(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				aMapController.animateTo(overlay.getMyLocation());
			}
		});
        mMap.getOverlays().add(overlay);
        
        Drawable drawable = getResources().getDrawable(R.drawable.ic_launcher);
        OverlayItems overlay2= new OverlayItems(drawable);
        
        OverlayItem item=new OverlayItem(new GeoPoint((int)(-6.541671* 1E6),(int) (106.640832*1E6)), "point 0", "Deskripsi Point A");
        overlay2.addItem(item);
        mMap.getOverlays().add(overlay2);
        
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
	
	private ArrayList<OverlayItem> items= new ArrayList<OverlayItem>();
	
	private class OverlayItems extends ItemizedOverlay<OverlayItem>{

		public OverlayItems(Drawable defaultMarker) {
			super(boundCenterBottom(defaultMarker));
			// TODO Auto-generated constructor stub
		}

		@Override
		protected OverlayItem createItem(int i) {
			// TODO Auto-generated method stub
			return items.get(i);
		}

		@Override
		public int size() {
			// TODO Auto-generated method stub
			return items.size();
			
		}
		
		public void addItem(OverlayItem overlayItem)
		{
			items.add(overlayItem);
			populate();
		}
		
	}
	
}
