package com.latih.list.image;

import java.util.zip.Inflater;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListImageAdapter extends BaseAdapter {
	private Activity ma;
	private int[] mimages;
	private String[] mwords;
	private static LayoutInflater minflater=null;
	
	public ListImageAdapter(Activity a, int[] images, String[] words){
		ma=a;
		mimages=images;
		mwords=words;
		
		minflater=(LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}
	
	public int getCount() {
		// TODO Auto-generated method stub
		return mwords.length;
	}
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi=convertView;
		vi=minflater.inflate(R.layout.activity2, null);
		
		ImageView image = (ImageView) vi.findViewById(R.id.image);
		TextView word=(TextView) vi.findViewById(R.id.text);
		
		image.setImageResource(mimages[position]);
		word.setText(mwords[position]);
		
		return vi;
		
	}
	

}
