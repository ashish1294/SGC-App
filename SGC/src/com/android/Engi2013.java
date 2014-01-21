package com.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class Engi2013 extends Activity{

	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.engineer2013);
        
        Button AstroEvents= (Button) findViewById(R.id.AstroEvents);
        Button AboutAstroCom= (Button) findViewById(R.id.About_Astro_Com);
        Button AboutEngi= (Button) findViewById(R.id.About_Engi2013);
        //Button Schedule= (Button) findViewById(R.id.Schedule);
        
        Typeface Lycanthrope = Typeface.createFromAsset(getAssets(), "fonts/Lycanthrope.ttf");
        AstroEvents.setTypeface(Lycanthrope);
        AboutAstroCom.setTypeface(Lycanthrope);
        AboutEngi.setTypeface(Lycanthrope);
        //Schedule.setTypeface(Lycanthrope);
        View.OnClickListener lis = new View.OnClickListener() {
        	Intent nexts = new Intent();
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch(v.getId())
				{
				case R.id.AstroEvents:		nexts = new Intent (getApplicationContext(), AstroEvents.class);
											break;
				//case R.id.Schedule:			Toast.makeText(getApplicationContext(), "You Clicked Schedule", Toast.LENGTH_LONG).show();
					//						break;
				case R.id.About_Astro_Com:	nexts = new Intent(getApplicationContext(), About.class);
											nexts.putExtra("aboutof", "Astro");
											break;
				case R.id.About_Engi2013:	nexts = new Intent(getApplicationContext(), About.class);
											nexts.putExtra("aboutof", "Engineer2013");
											break;
				}
				Button_Anim(v);
				startActivity(nexts);
			}
		};
		AstroEvents.setOnClickListener(lis);
        AboutAstroCom.setOnClickListener(lis);
        AboutEngi.setOnClickListener(lis);
        //Schedule.setOnClickListener(lis);
	}
	@Override
	public void onBackPressed() {
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
