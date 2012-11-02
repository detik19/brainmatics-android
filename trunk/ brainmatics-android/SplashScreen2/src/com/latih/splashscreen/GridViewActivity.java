package com.latih.splashscreen;

import java.security.spec.MGF1ParameterSpec;

import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class GridViewActivity extends Activity {
	private GridView mGridView;
	int[] mlistImages=new int[] {R.drawable.email,R.drawable.email,R.drawable.email,R.drawable.email,R.drawable.email,R.drawable.email,R.drawable.email
			,R.drawable.email,R.drawable.email,R.drawable.email,R.drawable.email
	
		
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        mGridView = (GridView) findViewById(R.id.gridView1);
        mGridView.setAdapter(new MyGridAdapter());
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_grid_view, menu);
        return true;
    }
    public class MyGridAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mlistImages.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return mlistImages[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			float scale=getResources().getDisplayMetrics().density;
			
			// TODO Auto-generated method stub
			ImageView view=new ImageView(getApplicationContext());
			view.setScaleType(ScaleType.FIT_CENTER);
			view.setImageResource(mlistImages[position]);
			view.setLayoutParams(new GridView.LayoutParams((int)(50*scale),(int) (50*scale)));
			
			return view;
		}
    	
    }
    
}
