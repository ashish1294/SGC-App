package com.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainAbout extends Activity{

	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutmain);
        
        Button nitk= (Button) findViewById (R.id.aboutnitk);
        Button engi= (Button) findViewById (R.id.aboutengi);
        Button sgc= (Button) findViewById (R.id.aboutsgc);
        Button astro= (Button) findViewById (R.id.aboutastro);
        
        Typeface Lycanthrope = Typeface.createFromAsset(getAssets(), "fonts/Lycanthrope.ttf");
        nitk.setTypeface(Lycanthrope);
        astro.setTypeface(Lycanthrope);
        engi.setTypeface(Lycanthrope);
        sgc.setTypeface(Lycanthrope);
        
        nitk.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent aboutscr = new Intent(getApplicationContext(), About.class);
				aboutscr.putExtra("aboutof", "Nitk");
				Log.e("About Screen",".");
				startActivity(aboutscr);
				Button_Anim(arg0);
				Display();
				
			}
		});
        
        engi.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent aboutscr = new Intent(getApplicationContext(), About.class);
				aboutscr.putExtra("aboutof", "Engineer2013");
				Log.e("About Screen",".");
				startActivity(aboutscr);
				Button_Anim(arg0);
				Display();
			}
		});

        sgc.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent aboutscr = new Intent(getApplicationContext(), About.class);
				aboutscr.putExtra("aboutof", "SGC");
				Log.e("About Screen",".");
				startActivity(aboutscr);
				Button_Anim(arg0);
				Display();
			}
		});

        astro.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent aboutscr = new Intent(getApplicationContext(), About.class);
				aboutscr.putExtra("aboutof", "Astro");
				Log.e("About Screen",".");
				startActivity(aboutscr);
				Button_Anim(arg0);
				Display();
			}
		});
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
