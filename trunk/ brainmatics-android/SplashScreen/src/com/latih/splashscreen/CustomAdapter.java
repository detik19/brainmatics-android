package com.latih.splashscreen;

import java.util.List;

import com.latih.model.twitter;

import android.content.Context;
import android.text.AndroidCharacter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
	private List<twitter> atwitter;
	private LayoutInflater inflater;
	private Context ctx;
	TextView mtv1,mtv2;
	
	
	public CustomAdapter(Context ctx, List<twitter> list){
		super();
		this.ctx=ctx;
		this.atwitter=list;
		inflater=(LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		
	}
	
	public int getCount() {
		// TODO Auto-generated method stub
		return atwitter.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return atwitter.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position	;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		
		if(vi==null){
			vi=inflater.inflate(android.R.layout.simple_list_item_2, null);
			mtv1=(TextView) vi.findViewById(android.R.id.text1);
			mtv2=(TextView) vi.findViewById(android.R.id.text2);
			
			
			mtv1.setText(atwitter.get(position).getText());
			mtv2.setText(atwitter.get(position).getFrom_user_name());
			
		}
		
		
		
		
		
		return vi;
	}

}
