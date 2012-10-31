package com.example.day3;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class ImageActivity extends Activity implements OnClickListener {
	Button button1;
	Button button2;
	
	final int TAKE_AVATAR_CAMERA_REQUEST = 0;
	final int TAKE_AVATAR_GALLERY_REQUEST = 1;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);

		button1 = (Button) findViewById(R.id.buttonImage1);
		button2 = (Button) findViewById(R.id.buttonImage2);
		
		button2.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_image, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		ImageView view = (ImageView) findViewById(R.id.imageView1);
		
		if(requestCode == TAKE_AVATAR_CAMERA_REQUEST && resultCode == RESULT_OK) {
			Bitmap img = (Bitmap) data.getExtras().get("data");
			
			view.setImageBitmap(img);
		}else if(requestCode == TAKE_AVATAR_GALLERY_REQUEST && resultCode == RESULT_OK) {
			Uri uri = data.getData();
			view.setImageURI(uri);
		}
	}

	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.buttonImage1:
			Intent intent = new Intent(Intent.ACTION_PICK);
			intent.setType("image/");
			startActivityForResult(intent, TAKE_AVATAR_GALLERY_REQUEST);
			break;
		case R.id.buttonImage2:
			Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(intent2, TAKE_AVATAR_CAMERA_REQUEST);
			break;
		default:
			break;
		}
	}
}
