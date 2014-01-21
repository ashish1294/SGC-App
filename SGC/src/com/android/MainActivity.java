package com.android;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Display;
import android.view.Menu;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button Engi2013= (Button) findViewById(R.id.engineer2013);
		//Button StarWars= (Button) findViewById(R.id.quiz);
		Button Weight= (Button) findViewById(R.id.weight);
		Button Stellar = (Button) findViewById(R.id.stellar);
		Button People= (Button) findViewById(R.id.people);
		Button About= (Button) findViewById(R.id.aboutus);
		Button Contact = (Button) findViewById(R.id.contactus);
		
		Typeface Lycanthrope = Typeface.createFromAsset(getAssets(), "fonts/Lycanthrope.ttf");
		Engi2013.setTypeface(Lycanthrope);
		//StarWars.setTypeface(Lycanthrope);
		Weight.setTypeface(Lycanthrope);
		People.setTypeface(Lycanthrope);
		About.setTypeface(Lycanthrope);
		Contact.setTypeface(Lycanthrope);
		Stellar.setTypeface(Lycanthrope);
		
		Engi2013.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent engi2013scr = new Intent(getApplicationContext(), Engi2013.class);
				startActivity(engi2013scr);
				Button_Anim(v);
				Display();
			}
		});
		
		/*StarWars.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent quizscr = new Intent(getApplicationContext(), Quiz.class);
				startActivity(quizscr);
				Button_Anim(arg0);
				Display();
			}
		});*/
		
		Weight.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent weightscr = new Intent(getApplicationContext(),weight.class);
				startActivity(weightscr);
				Button_Anim(v);
				Display();
			}
		});
		
		Stellar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent stellarscr = new Intent(getApplicationContext(),Stellar.class);
				startActivity(stellarscr);
				Button_Anim(v);
				Display();
			}
		});
		People.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent peoplescr = new Intent(getApplicationContext(),People.class);
				startActivity(peoplescr);
				Button_Anim(v);
				Display();
			}
		});

		About.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent mainabout = new Intent(getApplicationContext(), MainAbout.class);
				startActivity(mainabout);
				Button_Anim(v);
				Display();
			}
		});
		
		
		Contact.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent contactus = new Intent(getApplicationContext(), Contact.class);
				startActivity(contactus);
				Button_Anim(v);
				Display();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
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
