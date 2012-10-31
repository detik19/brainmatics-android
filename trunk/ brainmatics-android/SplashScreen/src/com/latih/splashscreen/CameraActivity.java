package com.latih.splashscreen;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CameraActivity extends Activity {

	final int TAKE_AVATAR_CAMERA_REQUEST=0;
	final int TAKE_AVATAR_GALLERY_REQUEST=1;
	ImageView iV;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        
        Button pickGalleryBtn= (Button) findViewById(R.id.button1);
        Button takePictBtn=(Button) findViewById(R.id.button2);
		iV= (ImageView)findViewById(R.id.imageView1);

        
        pickGalleryBtn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				ambilGallery();
			}
		});
        
        takePictBtn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				panggilCamera();
				
			}
		});
    }

    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
    	  if (requestCode == TAKE_AVATAR_CAMERA_REQUEST && resultCode == RESULT_OK) {
    			Bitmap cameraPic=(Bitmap) data.getExtras().get("data");
    			iV.setImageBitmap(cameraPic);

    	  }
    	  
    	  else if(requestCode== TAKE_AVATAR_GALLERY_REQUEST && resultCode== RESULT_OK){
    		  Uri photoUri = data.getData();
    		  iV.setImageURI(photoUri);
    		  
    	  }
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_camera, menu);
        return true;
    }
    
    private void panggilCamera()
    {
    	Intent picIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
    	startActivityForResult(picIntent, TAKE_AVATAR_CAMERA_REQUEST);
    	
    }
   
    private void ambilGallery(){
    	
    	Intent intent = new Intent(Intent.ACTION_PICK);
    	intent.setType("image/*");
    	
    	startActivityForResult(intent, TAKE_AVATAR_GALLERY_REQUEST);
    	
    }
    
}
