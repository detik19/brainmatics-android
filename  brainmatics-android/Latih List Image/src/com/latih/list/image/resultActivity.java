package com.latih.list.image;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;

public class resultActivity extends ListActivity{
	private static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;
	String[] words;
	int[] gambar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        startVoiceRecognitionActivity();
        
        
	}

	/**
     * Fire an intent to start the speech recognition activity.
     */
    private void startVoiceRecognitionActivity() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Njajal Ngomongo");
        startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
    }

    /**
     * Handle the results from the recognition activity.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK) {
            // Fill the list view with the strings the recognizer thought it could have heard
            ArrayList<String> matches = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            
            words = new String[matches.size()];
            matches.toArray(words);
            gambar=new int[words.length];
            
            for(int i=0;i<words.length;i++){
            	gambar[i]=R.drawable.ic_launcher;
            }
            ListImageAdapter adapter = new ListImageAdapter(this, gambar, words);
            setListAdapter(adapter);
            
          
            //mTv.setText(matches.toString());
           // mList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
           //         matches));
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
