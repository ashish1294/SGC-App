package com.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.TypedValue;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.TextView;

public class EventView extends Activity{
	SpannableString spans;
	Intent eventlinkintent;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Typeface Jim = Typeface.createFromAsset(getAssets(), "fonts/Jim.ttf");
		
		String event = getIntent().getStringExtra("eventname");
		
		if (event.equals(new String("Astro Talk")))
		{
			setContentView(R.layout.astrotalkdes);
			TextView astrotalklink1 = (TextView) findViewById(R.id.astrotalklink1);
			spans = new SpannableString(astrotalklink1.getText().toString());
	    	spans.setSpan(new UnderlineSpan(), 0, spans.length(), 0);
	    	astrotalklink1.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
	    	astrotalklink1.setText(spans);
	    	
	    	TextView astrotalklink2 = (TextView) findViewById(R.id.astrotalklink2);
			spans = new SpannableString(astrotalklink2.getText().toString());
	    	spans.setSpan(new UnderlineSpan(), 0, spans.length(), 0);
	    	astrotalklink2.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
	    	astrotalklink2.setText(spans);
	    	
	    	eventlinkintent = new Intent();
	    	eventlinkintent.setAction(Intent.ACTION_VIEW);
	        eventlinkintent.addCategory(Intent.CATEGORY_BROWSABLE);
	    	OnClickListener linklisten = new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					eventlinkintent.setData(Uri.parse(((TextView)v).getText().toString()));
					startActivity(eventlinkintent);
			        Display();
				}
	    	};
	    	astrotalklink1.setOnClickListener(linklisten);
	    	astrotalklink2.setOnClickListener(linklisten);
	    	
	    	TextView eventviewdes1 = (TextView) findViewById(R.id.eventviewdes1);
	    	eventviewdes1.setTypeface(Jim);
	    	
	    	TextView eventviewdes2 = (TextView) findViewById(R.id.eventviewdes2);
	    	eventviewdes2.setTypeface(Jim);
	    	
	    	TextView eventviewdes3 = (TextView) findViewById(R.id.eventviewdes3);
	    	eventviewdes3.setTypeface(Jim);
	    	
	    	TextView eventviewdes4 = (TextView) findViewById(R.id.eventviewdes4);
	    	eventviewdes4.setTypeface(Jim);
	    	
	    	TextView eventviewdes5 = (TextView) findViewById(R.id.eventviewdes5);
	    	eventviewdes5.setTypeface(Jim);
		}
		else if (event.equals(new String("Star Wars")))
		{
			setContentView(R.layout.starwarsdes);
			TextView eventviewdes1 = (TextView) findViewById(R.id.eventviewdes1);
	    	eventviewdes1.setTypeface(Jim);
		}
		else if (event.equals(new String("Beyond Earth")))
		{
			setContentView(R.layout.beyondearthdes);
			TextView eventviewdes1 = (TextView) findViewById(R.id.eventviewdes1);
	    	eventviewdes1.setTypeface(Jim);
	    	
	    	TextView eventviewdes2 = (TextView) findViewById(R.id.eventviewdes2);
	    	eventviewdes2.setTypeface(Jim);
		}
		else
		{
			setContentView(R.layout.exhibits);
			TextView eventviewdes1 = (TextView) findViewById(R.id.eventviewdes1);
	    	eventviewdes1.setTypeface(Jim);
	    	
	    	TextView eventviewdes2 = (TextView) findViewById(R.id.eventviewdes2);
	    	eventviewdes2.setTypeface(Jim);
	    	
	    	TextView eventviewdes3 = (TextView) findViewById(R.id.eventviewdes3);
	    	eventviewdes3.setTypeface(Jim);
	    	
	    	TextView eventviewdes4 = (TextView) findViewById(R.id.eventviewdes4);
	    	eventviewdes4.setTypeface(Jim);
	    	
	    	TextView eventviewdes5 = (TextView) findViewById(R.id.eventviewdes5);
	    	eventviewdes5.setTypeface(Jim);
	    	
	    	TextView eventviewdes6 = (TextView) findViewById(R.id.eventviewdes6);
	    	eventviewdes6.setTypeface(Jim);
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
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Display();
	}
}
