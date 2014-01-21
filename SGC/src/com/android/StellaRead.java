package com.android;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TableLayout.LayoutParams;

public class StellaRead extends Activity{

	ListView stellarlist;
	LinearLayout streadli;
	ArrayList<String> listItems=new ArrayList<String>();
	Myadapter ad;
	Typeface U,Demons;
	TextView itemtext,err;
	String to_open;
	View contemp;
	MotionEvent motemp;
	AdapterView.AdapterContextMenuInfo info;
	ProgressDialog prodia;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.readstella);
		
		streadli = (LinearLayout) findViewById(R.id.main1);
		U = Typeface.createFromAsset(getAssets(), "fonts/U.ttf");
		Demons = Typeface.createFromAsset(getAssets(), "fonts/Demons.ttf");
		err = new TextView(this);
		LayoutParams errL = (new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		err.setLayoutParams(errL);
		err.setGravity(Gravity.CENTER_HORIZONTAL);
		err.setTextColor(Color.WHITE);
		err.setTypeface(U);
		err.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
		ad = new Myadapter(this, listItems);
		stellarlist = (ListView) findViewById(R.id.liststellar);
		stellarlist.setAdapter(ad);
		registerForContextMenu(stellarlist);
		prodia = new ProgressDialog(this);
		prodia.setTitle("Please Wait");
		prodia.setCancelable(false);
		prodia.setMessage("Loading Content");
		prodia.setIcon(R.drawable.wait);
		stellarlist.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View rview, int pos,
					long arg3) {
				// TODO Auto-generated method stub
				itemtext = (TextView) rview.findViewById(R.id.edition);
				itemtext.setBackgroundColor(Color.argb(150,147,253,240));
				String temp = itemtext.getText().toString().substring(8);
				String st = getResources().getString(R.string.magname) + temp + getResources().getString(R.string.magex);
				Open_Stella(st);
			}});
		if (isExternalStorageReadable()==false)
		{
			err.setText(R.string.stellarcarderr);
			Toast.makeText(getApplicationContext(), "Please Check Your External Storage", Toast.LENGTH_LONG).show();
			streadli.addView(err);
			stellarlist.setVisibility(View.GONE);
		}
		else
		{
			prodia.show();
			stellarlist.setVisibility(View.VISIBLE);
			Integer j = Integer.valueOf(1);
			String name= new String();
			boolean found = true;
			while(j<20)
			{
				String name1 = getResources().getString(R.string.magname);
				String name2 = j.toString();
				String name3 = getResources().getString(R.string.magex);
				name = name1+name2+name3;
				String Path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath().toString();
				File stellar_file = new File(Path,name);
				if(stellar_file.exists())
				{
					listItems.add(j.toString());
					ad.notifyDataSetChanged();
					found = false;
				}
				j++;
			}
			if (found)
			{
				stellarlist.setVisibility(View.GONE);
				err.setText("No Files Found. Please Retrieve New Edition !");
				streadli.addView(err);
			}
			prodia.dismiss();
		}
	}
	
	public void Open_Stella(String st_name)
	{
		Intent magread = new Intent(Intent.ACTION_VIEW);
		try
		{
			String Path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath().toString();
			File read_file = new File(Path,st_name);
			magread.setDataAndType(Uri.fromFile(read_file),getResources().getString(R.string.magmime));
			magread.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			PackageManager packageManager = getPackageManager();
			List<ResolveInfo> activities = packageManager.queryIntentActivities(magread, 0);
			if (activities.size()==0)
			{
				Toast.makeText(getApplicationContext(), "Please Install a PDF Reader", Toast.LENGTH_LONG).show();
			}
			else
			{
				String title= "Open With...";
				Intent chooser = Intent.createChooser(magread, title);
				startActivity(chooser);
			}
		}
		catch (Exception e)
		{
			Toast.makeText(getApplicationContext(), "Unable to Open File !!", Toast.LENGTH_LONG).show();
		}
	}
	public class Myadapter extends BaseAdapter{

		Context cont;
		ArrayList<String> a;
		 public Myadapter (Context con, ArrayList<String> abc) {
		            this.cont = con;
		            this.a = abc;
		            }
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return a.size();
		}

		@Override
		public String getItem(int position) {
			// TODO Auto-generated method stub
			return a.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return a.indexOf(getItem(position));
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			LayoutInflater layinf = (LayoutInflater) cont.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			TextView tv;
			if (convertView==null)
			{
				convertView=layinf.inflate(R.layout.stlistitem, parent,false);
				tv= (TextView) convertView.findViewById(R.id.edition);
				convertView.setTag(tv);
			}
			else
			{
				tv = (TextView) convertView.getTag();
			}
			tv.setText("Edition " + getItem(position)); 
			tv.setBackgroundColor(Color.argb(130,147,253,191));
			tv.setTypeface(Demons);
			tv.setOnTouchListener(new OnTouchListener(){

				@SuppressWarnings("deprecation")
				@Override
				public boolean onTouch(View arg0, MotionEvent arg1) {
					// TODO Auto-generated method stub
					contemp = arg0;
					motemp=arg1;
					if(arg1.getAction()==MotionEvent.ACTION_DOWN)
					{
						ColorDrawable[] color = {new ColorDrawable(Color.argb(130,147,253,240)), new ColorDrawable(Color.argb(230, 134,116,250))};
					    TransitionDrawable trans = new TransitionDrawable(color);
					    arg0.setBackgroundDrawable(trans);
					    trans.startTransition(900);
					    new CountDownTimer(1000, 500) {
					    	Boolean flag=true;
					        public void onTick(long millisUntilFinished)
					        {
					        	if(motemp.getAction()==MotionEvent.ACTION_UP&&flag)
					        	{
					        		itemtext = (TextView)contemp;
					        		String temp = itemtext.getText().toString().substring(8);
									String sg = getResources().getString(R.string.magname) + temp + getResources().getString(R.string.magex);
									flag=false;
									Open_Stella(sg);
									cancel();
									
					        	}
					        }
					        public void onFinish() {
					        	contemp.setBackgroundColor(Color.argb(130,147,253,191));
					        }
					     }.start();
					}
					else
					{
						arg0.setBackgroundColor(Color.argb(130,147,253,191));
					}
					return false;
				}});
			Animation animation = null;
			animation = AnimationUtils.loadAnimation(cont,R.anim.wave);
			animation.setDuration(400);
			convertView.startAnimation(animation);
			animation = null;
			return convertView;
		}
	}
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo)
	{  
		super.onCreateContextMenu(menu, v, menuInfo);  
		menu.setHeaderTitle("Choose Action");
		menu.setHeaderIcon(R.drawable.conmenu);
		menu.add(Menu.NONE,1, 0, "Open");
		menu.add(Menu.NONE,2, 0, "Delete");
	}
	public boolean onContextItemSelected(MenuItem item) 
	{	
		info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
		//TextView t = (TextView) info.targetView;
		//t.setBackgroundColor(Color.argb(130,147,253,191));
		Log.e("Item Selected","Opening" + listItems.get(info.position).toString());
		if(item.getItemId()==1)
		{
			Open_Stella(getResources().getString(R.string.magname) + listItems.get(info.position) + getResources().getString(R.string.magex));
			Log.e("Item Selected","Opening" + listItems.get(info.position).toString());
			return true;
		}    
		else if(item.getItemId()==2)
		{ 
			AlertDialog.Builder adb=new AlertDialog.Builder(this);
			DialogInterface.OnClickListener Posbutclick = new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					try
					{
						if(isExternalStorageWritable())
						{
							String P = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath().toString();
							File del_File = new File(P,getResources().getString(R.string.magname) + listItems.get(info.position) + getResources().getString(R.string.magex));
							Toast.makeText(getApplicationContext(), del_File.getAbsolutePath().toString(), Toast.LENGTH_LONG).show();
							del_File.delete();
							listItems.remove(info.position);
							ad.notifyDataSetChanged();
							/*Intent in = getIntent();
							finish();
							startActivity(in);*/
						}
						else
						{
							Toast.makeText(getApplicationContext(), "SD Card unavailable for delete operation!!", Toast.LENGTH_LONG).show();
						}
					}
					catch(Exception e)
					{
						Toast.makeText(getApplicationContext(), "Unable to Delete File!!", Toast.LENGTH_LONG).show();
					}
				}
			};
			DialogInterface.OnClickListener Negbutclick = new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				}
			};
			adb.setTitle("Delete Stella Specta")
			   .setMessage("Are you sure you want to delete " + getResources().getString(R.string.magname) + listItems.get(info.position) + getResources().getString(R.string.magex) + "?")
			   .setIcon(R.drawable.delete_icon)
		       .setPositiveButton("Confirm",Posbutclick)
			   .setNegativeButton("Cancel",Negbutclick)
			   .setCancelable(false)
			   .show();
			return true;
		}
		return false;  
	}  
	/* Checks if external storage is available for read and write */
	public boolean isExternalStorageWritable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state)) {
	        return true;
	    }
	    return false;
	}

	/* Checks if external storage is available to at least read */
	public boolean isExternalStorageReadable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state) ||
	        Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
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
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
		Display display = ((WindowManager) 
				getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
			if ((display.getRotation() == Surface.ROTATION_0) || (display.getRotation() == Surface.ROTATION_180)) {
				overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
			} else if ((display.getRotation() == Surface.ROTATION_90) || (display.getRotation() == Surface.ROTATION_270)) {
				overridePendingTransition(R.anim.slide_up, R.anim.slide_down);
			}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onResume();
		//Intent intent = getIntent();
	    finish();
	    //startActivity(intent);
	}
	
}
