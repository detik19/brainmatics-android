package com.latih.listview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	TextView selection;
	String[] items=new String[10];
	List<Map<String,String>> itemmap=new ArrayList<Map<String,String>>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    

        showListView1();		

    }


	private void showListView1() {
		for(int i=0;i< items.length;i++)
        {
        	items[i]="negara "+i;
        }
        
        ListView listView =(ListView) findViewById(R.id.listView1);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(getApplicationContext(), items[arg2], Toast.LENGTH_SHORT).show();
			}
		});
	}
    
    
    public void showListofMap()
    {
    	Map<String, String> map=new HashMap<String, String>();
    	
    	map.put("Country", "Thailand");
    	map.put("CAPITAL", "Bangkok");
    	itemmap.add(map);
    	
    	ListView lv= (ListView) findViewById(R.id.listView1);
    	String[] from=new String[]{"Country", "CAPITAL"};
    	int[] to = new int[]{android.R.id.text1,android.R.id.text2};
    	
    	SimpleAdapter adapter=new SimpleAdapter(this, itemmap, android.R.layout.simple_list_item_2, from, to);
    	lv.setAdapter(adapter); 
    	//lv.setOnItemClickListener()
    	registerForContextMenu(lv);
    	
    	
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        showListofMap();

        return true;
    }
}
