package com.android;

import java.io.InputStream;
import java.util.Arrays;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class PeopleView extends Activity{
	
	TextView nametv,yeartv,deptv,abttv,urltv,emailtv,areatv;
	TableRow namerow,yearrow,deprow,abtrow,urlrow,emailrow,arearow;
	TableLayout table;
	ImageView img;
	Drawable drawimg;
	Person infoin;
	Thread imageload;
	ProgressDialog progress;
	InputStream isr = null;
	boolean image=true;
	Typeface Jim;
	
		public class Person{
			public String name,year,dep,abt,url,email,area,imgname,sex;
			Drawable d;
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
				sex = s[8];
				d = null;
			}
		}

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.peopleview);
        
        infoin =  new Person(getIntent().getStringArrayExtra("perinfo"));
        
        Jim = Typeface.createFromAsset(getAssets(), "fonts/Jim.ttf");
        
        table = (TableLayout) findViewById(R.id.peopleviewtable);
        img = (ImageView) findViewById(R.id.peopleimage);
        namerow = (TableRow) findViewById(R.id.namerow);
        yearrow = (TableRow) findViewById(R.id.yearrow);
        deprow = (TableRow) findViewById(R.id.deprow);
        abtrow = (TableRow) findViewById(R.id.abtrow);
        arearow = (TableRow) findViewById(R.id.arearow);
        emailrow = (TableRow) findViewById(R.id.emailrow);
        urlrow = (TableRow) findViewById(R.id.urlrow);
        
        nametv = (TextView) findViewById(R.id.nametv);
        yeartv = (TextView) findViewById(R.id.yeartv);
        deptv = (TextView) findViewById(R.id.deptv);
        abttv = (TextView) findViewById(R.id.abttv);
        areatv = (TextView) findViewById(R.id.areatv);
        emailtv = (TextView) findViewById(R.id.emailtv);
        urltv = (TextView) findViewById(R.id.urltv);
        progress = new ProgressDialog(this);
        progress.setTitle("Please Wait");
		progress.setCancelable(false);
		progress.setMessage("Loading Content");
		progress.setIcon(R.drawable.wait);
		
		imageload = new Thread(new Runnable() {
	        public void run() {
	        	try
	        	{
	        		if(Arrays.asList(getResources().getAssets().list("images")).contains(infoin.imgname))
	        			isr = getAssets().open("images/"+infoin.imgname);
	        		else if(infoin.sex.equals("M")||(infoin.sex.equals("m")))
	        		{	isr = getAssets().open("images/man.png"); image=false;}
	        		else
	        		{	isr = getAssets().open("images/woman.png"); image=false;}
	        		infoin.d = Drawable.createFromStream(isr, null);
	        		isr.close();
	        		runOnUiThread(new Runnable() {
	        		    public void run() {
	        		    	if(image==false)
	        		    		Toast.makeText(getApplicationContext(), "Image Unvailable !!", Toast.LENGTH_SHORT).show();
	        		    	img.setImageDrawable(infoin.d);
	        		    }
	        		});
	        	}
	        	catch (Exception e)
	        	{
	        	}
	        }
	    });
		
		imageload.start();
        
        if(infoin.name.equals("")||infoin.name.equals(null))
        {	
        	table.removeView(namerow);
        }
        else
        {
        	nametv.setText(infoin.name);
        	nametv.setTypeface(Jim);
        }
        if(infoin.year.equals("")||infoin.year.equals(null))
        {
        	table.removeView(yearrow);
        }
        else
        {
        	yeartv.setText(infoin.year);
        	yeartv.setTypeface(Jim);
        }
        if(infoin.dep.equals("")||infoin.dep.equals(null))
        {
        	table.removeView(deprow);
        }
        else
        {
        	deptv.setText(infoin.dep);
        	deptv.setTypeface(Jim);
        }
        if(infoin.abt.equals("")||infoin.abt.equals(null))
        {
        	table.removeView(abtrow);
        }
        else
        {
        	abttv.setText(infoin.abt);
        	abttv.setTypeface(Jim);
        }
        if(infoin.area.equals("")||infoin.area.equals(null))
        {
        	table.removeView(arearow);
        }
        else
        {
        	areatv.setText(infoin.area);
        	areatv.setTypeface(Jim);
        }
        if(infoin.email.equals("")||infoin.email.equals(null))
        {
        	table.removeView(emailrow);
        }
        else
        {
        	emailtv.setText(infoin.email);
        	emailtv.setTypeface(Jim);
        }
        if(infoin.url.equals("")||infoin.url.equals(null))
        {
        	table.removeView(urlrow);
        }
        else
        {
        	SpannableString spans = new SpannableString(infoin.url);
        	spans.setSpan(new UnderlineSpan(), 0, spans.length(), 0);
        	urltv.setText(spans);
        	urltv.setTypeface(Jim);
        	
        }
        
        urltv.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
		        intent.setAction(Intent.ACTION_VIEW);
		        intent.addCategory(Intent.CATEGORY_BROWSABLE);
		        intent.setData(Uri.parse(urltv.getText().toString()));
		        startActivity(intent);
		        Display();
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
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
}
