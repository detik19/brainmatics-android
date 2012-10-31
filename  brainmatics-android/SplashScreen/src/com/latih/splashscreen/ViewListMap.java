package com.latih.splashscreen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.android.maps.MapActivity;

public class ViewListMap extends Activity implements OnItemClickListener {

	List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
	ListView view;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_list_map);

		int lat = (int) (-6.197*1E6);
		int lon = (int) (106.847*1E6);
		int[] point = new int[]{lat, lon};

		Map<String, Object> item = new HashMap<String, Object>();
		item.put("ACTIVITY", "DKI Jakarta");
		item.put("DESC", "Jakarta");
		item.put("ATTR", point);
		list.add(item);

		lat = (int) (-6.9116*1E6);
		lon = (int) (107.6099*1E6);
		int[] point1 = new int[]{lat, lon};

		Map<String, Object> item1 = new HashMap<String, Object>();
		item1.put("ACTIVITY", "Jawa Barat");
		item1.put("DESC", "Bandung");
		item1.put("ATTR", point1);
		list.add(item1);

		lat = (int) (-5.424*1E6);
		lon = (int) (105.243*1E6);
		int[] point2 = new int[]{lat, lon};

		Map<String, Object> item2 = new HashMap<String, Object>();
		item2.put("ACTIVITY", "Lampung");
		item2.put("DESC", "Bandar Lampung");
		item2.put("ATTR", point2);
		list.add(item2);

		lat = (int) (-7.7935*1E6);
		lon = (int) (110.3691*1E6);
		int[] point3 = new int[]{lat, lon};

		Map<String, Object> item3 = new HashMap<String, Object>();
		item3.put("ACTIVITY", "DI Yogyakarta");
		item3.put("DESC", "Yogyakarta");
		item3.put("ATTR", point3);
		list.add(item3);

		view = (ListView) findViewById(R.id.listView1);
		String[] from = new String[]{"ACTIVITY", "DESC"};
		int[] to = new int[]{android.R.id.text1, android.R.id.text2};

		SimpleAdapter adapter = new SimpleAdapter(this, list, android.R.layout.simple_list_item_1, from, to);
		view.setAdapter(adapter);
		view.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_view_list_map, menu);
		return true;
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent intent = new Intent(getApplicationContext(), MapActivity.class);
		intent.putExtra("POINT", (int[]) ((Map<String, Object>) arg0.getItemAtPosition(arg2)).get("ATTR")); 
		startActivity(intent);
	}

	
}
