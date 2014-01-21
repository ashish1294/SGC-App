package com.android;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class AstroEvents extends Activity{
	
	ListView eventlist;
	ArrayList<String> arrlist= new ArrayList<String>();
	Myadapter eventlistad;
	Typeface U;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.astroevents);
		
		eventlist = (ListView) findViewById(R.id.listevents);
		arrlist.add(new String("Astro Talk"));
		arrlist.add(new String("Star Wars"));
		arrlist.add(new String("Beyond Earth"));
		arrlist.add(new String("Exhibits"));
		eventlistad = new Myadapter(this,arrlist);
		eventlist.setAdapter(eventlistad);
		U = Typeface.createFromAsset(getAssets(), "fonts/U.ttf");
		
		eventlist.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent it = new Intent(getApplicationContext(),EventView.class);
				it.putExtra("eventname", arrlist.get(arg2));
				startActivity(it);
				Display();
				Button_Anim(arg1);
			}});
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
				convertView=layinf.inflate(R.layout.eventlistitem, parent,false);
				tv= (TextView) convertView.findViewById(R.id.eventlistitem);
				convertView.setTag(tv);
			}
			else
			{
				tv = (TextView) convertView.getTag();
			}
			tv.setText(getItem(position)); 
			tv.setBackgroundColor(Color.argb(70,235,24,225));
			tv.setTypeface(U);
			
			Animation animation = null;
			animation = AnimationUtils.loadAnimation(cont,R.anim.fade_in);
			animation.setDuration(400);
			convertView.startAnimation(animation);
			animation = null;
			return convertView;
		}
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
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
		Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
			if ((display.getRotation() == Surface.ROTATION_0) || (display.getRotation() == Surface.ROTATION_180)) {
				overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
			} else if ((display.getRotation() == Surface.ROTATION_90) || (display.getRotation() == Surface.ROTATION_270)) {
				overridePendingTransition(R.anim.slide_up, R.anim.slide_down);
			}
	}
}
