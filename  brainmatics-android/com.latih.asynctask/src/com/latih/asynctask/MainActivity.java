package com.latih.asynctask;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

class CustomHttpClient {
  private static HttpClient customHttpClient;

  public static synchronized HttpClient getHttpClient() {
    if (customHttpClient != null) {
      return customHttpClient;
    }
    HttpParams params = new BasicHttpParams();
    HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
    HttpProtocolParams.setContentCharset(params,
        HTTP.DEFAULT_CONTENT_CHARSET);
    HttpProtocolParams.setUseExpectContinue(params, true);

    ConnManagerParams.setTimeout(params, 1000);

    HttpConnectionParams.setConnectionTimeout(params, 5000);
    HttpConnectionParams.setSoTimeout(params, 10000);

    SchemeRegistry schReg = new SchemeRegistry();
    schReg.register(new Scheme("http", PlainSocketFactory
        .getSocketFactory(), 80));
    schReg.register(new Scheme("https",
        SSLSocketFactory.getSocketFactory(), 443));
    ClientConnectionManager conMgr = new ThreadSafeClientConnManager(
        params, schReg);

    customHttpClient = new DefaultHttpClient(conMgr, params);

    return customHttpClient;
  }
}

class DownloadImageTask extends AsyncTask<String, Integer, Bitmap> {
  Context mContext;
  int progress = -1;
  Bitmap downloadedImage = null;

  DownloadImageTask(Context context) {
    mContext = context;
  }

  protected void setContext(Context context) {
    mContext = context;
    if (progress >= 0) {
      publishProgress(this.progress);
    }
  }

  protected void onPreExecute() {
    progress = 0;
  }

  protected Bitmap doInBackground(String... urls) {
    Log.v("doInBackground", "doing download of image...");
    return downloadImage(urls);
  }

  protected void onProgressUpdate(Integer... progress) {
    Log.v("onProgressUpdate", "Progress so far: " + progress[0]);
  }

  protected void onPostExecute(Bitmap result) {
    if (result != null) {
      downloadedImage = result;
      setImageInView();
    } else {
      Log.v("onPostExecute",
          "Problem downloading image. Please try later.");
    }
  }

  public Bitmap downloadImage(String... urls) {
    HttpClient httpClient = CustomHttpClient.getHttpClient();
    try {
      HttpGet request = new HttpGet(urls[0]);
      HttpParams params = new BasicHttpParams();
      HttpConnectionParams.setSoTimeout(params, 60000); // 1 minute
      request.setParams(params);
      HttpResponse response = httpClient.execute(request);
      setProgress(50);
      byte[] image = EntityUtils.toByteArray(response.getEntity());
      setProgress(75);
      Bitmap mBitmap = BitmapFactory.decodeByteArray(image, 0,
          image.length);
      setProgress(100);
      return mBitmap;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private void setProgress(int progress) {
    this.progress = progress;
    publishProgress(this.progress);
  }

  protected void setImageInView() {
    if (downloadedImage != null) {
      ImageView mImage = (ImageView) ((Activity) mContext)
          .findViewById(R.id.imageView1);
      mImage.setImageBitmap(downloadedImage);
    }
  }

  private void sleepFor(long msecs) {
    try {
      Thread.sleep(msecs);
    } catch (InterruptedException e) {
      Log.v("sleep", "interrupted");
    }
  }
}

public class MainActivity extends Activity {
  private DownloadImageTask diTask;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    if ((diTask = (DownloadImageTask) getLastNonConfigurationInstance()) != null) {
      diTask.setContext(this);
      if (diTask.getStatus() == AsyncTask.Status.FINISHED)
        diTask.setImageInView();
    }
  }

  public void doClick(View view) {
    if (diTask != null) {
      AsyncTask.Status diStatus = diTask.getStatus();
      Log.v("doClick", "diTask status is " + diStatus);
      if (diStatus != AsyncTask.Status.FINISHED) {
        Log.v("doClick", "... no need to start a new task");
        return;
      }
    }
    diTask = new DownloadImageTask(this);
    diTask.execute("http://aBigFile");
  }

  @Override
  public Object onRetainNonConfigurationInstance() {
    return diTask;
  }
}