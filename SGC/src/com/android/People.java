package com.android;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import au.com.bytecode.opencsv.CSVReader;

public class People extends Activity{
	
	ListView people_list;
	Myad pplad;
	ArrayList<Person> ppllistitem;
	Typeface Mar;
	loadppl loadppltask;
	Intent pplscr;
	int ob;

	public class Person{
		public String name,year,dep,abt,url,email,area,imgname,sex;
		Drawable img;
		
		public Person(String[] s)
		{
			name= s[0];
			year= s[1];
			dep= s[2];
			abt= s[3];
			url= s[4];
			email=s[5];
			area=s[6];
			imgname=s[7];
			sex=s[8];
			img=null;
		}

		public String[] get_strs()
		{
			return (new String[]{this.name,this.year,this.dep,this.abt,this.url,this.email,this.area,this.imgname,this.sex});
		}
	}
	
	ProgressDialog progd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.people);
		people_list = (ListView) findViewById(R.id.listpeople);
		
		Mar = Typeface.createFromAsset(getAssets(), "fonts/U.ttf");
		progd = new ProgressDialog(this);
		ppllistitem = new ArrayList<Person>();
		progd.setTitle("Please Wait");
		progd.setCancelable(false);
		progd.setMessage("Loading Content");
		progd.setIcon(R.drawable.wait);
		pplad = new Myad(this, ppllistitem);
		people_list.setAdapter(pplad);
		try
		{
			ob=getRequestedOrientation();
			loadppltask=new loadppl(this);
			loadppltask.execute("");
		}
		catch(Exception e)
		{
			Toast.makeText(getApplicationContext(), "Loading of People's Information failed!!", Toast.LENGTH_LONG).show();
			Log.e("Exception","Error calling Asynctask");
		}
	}
	
	public class loadppl extends AsyncTask<String,Void,String>{
		InputStream insr;

		Context context;
		public loadppl(Activity context) {
            this.context = context;
        }
		protected void onPreExecute() {
	        super.onPreExecute();
	        ob=getRequestedOrientation();
	        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
	        progd.show();
	    }
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			try
			{
				CSVReader cr = new CSVReader(new InputStreamReader(getAssets().open("csv/People Info.csv")),',','"',1);
				String[] line;
				Person p;
				while ((line = cr.readNext()) !=null )
				{
					p = new Person(line);
					if(Arrays.asList(getResources().getAssets().list("images")).contains(p.imgname))
					{
						insr = getAssets().open("images/"+p.imgname);
						p.img = Drawable.createFromStream(insr, null);
						insr.close();
						
					}
					else
					{
						if(p.sex.equals("M")||(p.sex.equals("m")))
							insr = getAssets().open("images/man.png");
						else
							insr = getAssets().open("images/woman.png");
						p.img = Drawable.createFromStream(insr, null);
						insr.close();
					}
					ppllistitem.add(p);
				}
				cr.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				Log.e("Problem","Thread exception");
			}
			return null;
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pplad.notifyDataSetChanged();
			progd.dismiss();
		}
		@Override
		protected void onProgressUpdate(Void... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}
		
		
		
	}
	public class Myad extends BaseAdapter{

		Context cont;
		ArrayList<Person> a;
		 public Myad (Context con, ArrayList<Person> abc) {
		            this.cont = con;
		            this.a = abc;
		            }
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return a.size();
		}

		@Override
		public Person getItem(int position) {
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
			class vh{
				ImageView iv;
				TextView tv;
			}
			vh holder;
			if (convertView==null)
			{
				convertView=layinf.inflate(R.layout.peoplelistitem, parent,false);
				holder = new vh();
				holder.tv = (TextView) convertView.findViewById(R.id.pplname);
				holder.iv = (ImageView)convertView.findViewById(R.id.pplimg);
				convertView.setTag(holder);
			}
			else
			{
				holder = (vh) convertView.getTag();
			}
			holder.tv.setText(getItem(position).name);
			holder.tv.setTypeface(Mar);
			holder.iv.setImageDrawable(getItem(position).img);
			convertView.setBackgroundColor(Color.argb(70,253,151,243));
			Animation animation = null;
			animation = AnimationUtils.loadAnimation(cont,R.anim.push_left_in);
			animation.setDuration(400);
			convertView.startAnimation(animation);
			animation = null;
			//if (convertView.onTouchEvent(motemp1)==false)
			convertView.setOnTouchListener(new OnTouchListener(){

				@SuppressWarnings("deprecation")
				@Override
				public boolean onTouch(View arg0, MotionEvent arg1) {
					// TODO Auto-generated method stub
					if(arg1.getAction() == MotionEvent.ACTION_DOWN)
					{
						ColorDrawable[] color = {new ColorDrawable(Color.argb(70,253,151,243)), new ColorDrawable(Color.argb(230, 134,116,250))};
					    TransitionDrawable trans = new TransitionDrawable(color);
					    arg0.setBackgroundDrawable(trans);
					    trans.startTransition(100);
					}
					else if (arg1.getAction() == MotionEvent.ACTION_UP)
					{
						arg0.setBackgroundColor(Color.argb(70,253,151,243));
						pplscr = new Intent(getApplicationContext(),PeopleView.class);
						pplscr.putExtra("perinfo",getItem(people_list.getPositionForView(arg0)).get_strs());
						startActivity(pplscr);
						Button_Anim(arg0);
						Display();
					}
					else
					{
						arg0.setBackgroundColor(Color.argb(70,253,151,243));
					}
					return true;
				}});
			
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
}

