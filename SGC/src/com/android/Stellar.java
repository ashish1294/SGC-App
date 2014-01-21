package com.android;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Stellar extends Activity{

	Button stellarupdate,stellaread;
	LinearLayout stellarlinear2;
	TextView stellarabout;
	Context con = this;
	ProgressDialog progressDialog;
	NotificationManager magdown;
	NotificationCompat.Builder magbuild;
	GetXMLTask task;
	boolean taskrunning = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stellar);
		stellarlinear2 = (LinearLayout) findViewById(R.id.stellarrela);
		stellarabout= (TextView) findViewById(R.id.stellarabout);
		stellarupdate = (Button) findViewById(R.id.stellarupdate);
		stellaread = (Button) findViewById(R.id.stellaread);
		
		Typeface Lycanthrope = Typeface.createFromAsset(getAssets(), "fonts/Lycanthrope.ttf");
		final Typeface U = Typeface.createFromAsset(getAssets(), "fonts/U.ttf");
		
		stellarabout.setTypeface(U);
		stellaread.setTypeface(Lycanthrope);
		stellarupdate.setTypeface(Lycanthrope);
		
		stellarupdate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Button_Anim(v);
				ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo networkInfowifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI); 
				NetworkInfo networkInfomob = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
				if (networkInfowifi.isConnected())
				{
					if(isExternalStorageWritable())
						Download_Stella_Update();
					else
						Toast.makeText(getApplicationContext(), "Check SD Card. Cannot Download !!", Toast.LENGTH_LONG).show();
				}
				else if (networkInfomob.isConnected())
				{
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(con);
					alertDialogBuilder.setTitle("Warning !!!");
					alertDialogBuilder
							.setMessage("You are using Mobile Data!!\nDownload may be slow!")
							.setCancelable(false)
							.setIcon(R.drawable.data_war)
							.setPositiveButton("Continue",new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,int id) {
									if(isExternalStorageWritable())
										Download_Stella_Update();
									else
										Toast.makeText(getApplicationContext(), "Check SD Card. Cannot Download !!", Toast.LENGTH_LONG).show();
								}
							  })
							.setNegativeButton("Go Back",new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,int id) {
									dialog.cancel();
								}
							});
					AlertDialog alertDialog = alertDialogBuilder.create();
					alertDialog.show();
				}
				else
				{
					Toast.makeText(getApplicationContext(), "You are not Connected. Please Check!", Toast.LENGTH_LONG).show();
				}
				
			}
		});
		
		stellaread.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent stellareadscr = new Intent(getApplicationContext(), StellaRead.class);
				startActivity(stellareadscr);
				Button_Anim(v);
				Display();
			}
		});
	}
	
	private void Download_Stella_Update() {
		// TODO Auto-generated method stub
		Integer j =Integer.valueOf(1);
		Integer k =Integer.valueOf(0);
		String[] tobedown = new String[20];
		String name=null,Path=null;
		task = new GetXMLTask(this);
		while(j<20)
		{
			name = getResources().getString(R.string.magname) + j.toString() + getResources().getString(R.string.magex); 
			Path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath().toString();
			File stellar_file = new File(Path,name);
			if(stellar_file.exists()==false)
			{
				tobedown[k]=name;
				k=k+1;
			}
			j=j+1;
		}
		progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Download in progress...");
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setIndeterminate(false);
        progressDialog.setMax(100);
        progressDialog.setCancelable(false);
        progressDialog.setIcon(R.drawable.download);
        magdown = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		magbuild = new NotificationCompat.Builder(this);
		magbuild.setContentTitle("Stella_Specta")
	    .setContentText("Download in progress")
	    .setSmallIcon(R.drawable.download);
		try
		{
			if(isExternalStorageWritable())
			{
				task.execute(tobedown);
				Toast.makeText(getApplicationContext(), "Download Starting", Toast.LENGTH_LONG).show();
			}
			else
			{
				Toast.makeText(getApplicationContext(),"SD Card Unavailable for writing !! ", Toast.LENGTH_LONG).show();
			}
			
		}
		catch(Exception e)
		{
			Toast.makeText(getApplicationContext(), "Async Task Error!! Please report!!", Toast.LENGTH_LONG).show();
			Log.e("excute","error from execute");
		}
	}
	private class GetXMLTask extends AsyncTask<String, Integer, String> {
		
		protected void onPreExecute() {
	        super.onPreExecute();
	        progressDialog.show();
	        ob = getRequestedOrientation();
	        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
	    }
        @SuppressWarnings("unused")
		private Activity context;
        boolean download=false;
        int ob;
        private boolean running = true, ioerror=false;
        Integer noOfURLs;
        public GetXMLTask(Activity context) {
            this.context = context;
        }
 
        @Override
        protected String doInBackground(String...urls) {
            noOfURLs = urls.length;
            int i;
            for (i=0;i<noOfURLs;i++) 
            {
            	if(running)
            		download(urls[i]);
            }
            return null;
        }
        private void download(String urlString) {
 
            int count = 0;
            URL url;
            InputStream inputStream = null;
            FileOutputStream outputStream = null;
 
            try {
            	String n = getResources().getString(R.string.magurl) +  urlString;
            	//progressDialog.setMessage("Downloading "+ urlString);
                url = new URL(n);
                URLConnection connection = url.openConnection();
                connection.setConnectTimeout(20000);
                int lenghtOfFile = connection.getContentLength();
                inputStream = connection.getInputStream();
                Log.e("Above Pa", urlString);
    			String Pa = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath().toString();
    			File dir = new File(Pa);
    			if (dir.exists()==false)
    			{
    				dir.mkdirs();
    			}
    			File f = new File(dir,urlString);
                outputStream = new FileOutputStream(f);
                byte data[] = new byte[1024];
                long total = 0;
                while ((count = inputStream.read(data)) != -1)
                {
                    total += count;
                    publishProgress((int)((total*100)/lenghtOfFile));
                    outputStream.write(data, 0, count);
                }
                outputStream.flush();
                download = true;
            } 
            catch (MalformedURLException e)
            {
                e.printStackTrace();
                Log.e("Malformed URL", "From catch");
                running=false;
                cancel(true);
            }
            catch (SocketTimeoutException e)
            {
                ioerror = true;
                running = false;
                Log.e("time out error","Async Catch block Socket Timeout error");
                cancel(true);
            }
            catch (Exception e)
            {
            	running = false;
            	Log.e("Catch last Esception","Some other exception");
            	cancel(true);
            }
            finally
            {
                Fileutils.close(inputStream);
                Fileutils.close(outputStream);
            }
            return;
        }
        
        @Override
        protected void onCancelled() {
        	progressDialog.dismiss();
        	if (running)
        		Toast.makeText(getApplicationContext(), "Download Interrupted!!", Toast.LENGTH_SHORT).show();
        	else if (ioerror)
        		Toast.makeText(getApplicationContext(), "Download Incomplete!! Server Not Responding!!", Toast.LENGTH_LONG).show();
        	else if (download==false)
        		Toast.makeText(getApplicationContext(), "No New Editions Available!!", Toast.LENGTH_LONG).show();
        	else
        		Toast.makeText(getApplicationContext(), "Download Complete!!", Toast.LENGTH_SHORT).show();
        }
        protected void onProgressUpdate(Integer... progress)
        {
            progressDialog.setProgress(progress[0]);
        }
        @Override    
        protected void onPostExecute(String str)
        {
        	progressDialog.dismiss();
        	setRequestedOrientation(ob);
        }
    }
	
	public boolean isExternalStorageWritable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state)) {
	        return true;
	    }
	    return false;
	}
	
	public File getDownloadStorageDir() {
	    // Get the directory for the user's download directory. 
	    File file = new File(Environment.getExternalStoragePublicDirectory(
	            Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
	    if (!file.mkdirs()) {
	        Log.e("Stellar", "Directory not created");
	    }
	    return file;
	}
	
	@Override
	public void onBackPressed()
	{
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
		Display();
	}
	
	public void Display()
	{
		Display display = ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		if ((display.getRotation() == Surface.ROTATION_0) || (display.getRotation() == Surface.ROTATION_180))
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
		else if ((display.getRotation() == Surface.ROTATION_90) || (display.getRotation() == Surface.ROTATION_270))
			overridePendingTransition(R.anim.slide_up, R.anim.slide_down);
	}
	
	public void Button_Anim(View v)
	{
		Animation animation = null;
		animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.hyperspace_out);
		animation.setDuration(150);
		v.startAnimation(animation);
		animation = null;
	}
	
}

