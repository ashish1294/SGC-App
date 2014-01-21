package com.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class About extends Activity{
	
	Button linkbut;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
			
        Intent idata =getIntent();
        String aboutof = new String(idata.getStringExtra("aboutof"));
        
        ImageView abtimg = (ImageView) findViewById(R.id.aboutimage);
        TextView abttext = (TextView) findViewById(R.id.abouttext);
        linkbut = (Button) findViewById(R.id.aboutlink);
        
        linkbut.setTextAppearance(getApplicationContext(), R.style.Main_Home_Button);
        Typeface Lycanthrope = Typeface.createFromAsset(getAssets(), "fonts/Lycanthrope.ttf");
        Typeface Satisfy = Typeface.createFromAsset(getAssets(), "fonts/Satisfy.ttf");
        
        linkbut.setTypeface(Lycanthrope);
        abttext.setTypeface(Satisfy);
        
        if (aboutof.equals("Engineer2013"))
        {
        	abtimg.setImageResource(R.drawable.engi_logo);
        	abttext.setText(R.string.about_engi);
        	linkbut.setText(R.string.Engineerlink);
        }
        else if (aboutof.equals("Nitk"))
        {
        	abtimg.setImageResource(R.drawable.nitk_logo);
        	abttext.setText(R.string.about_nitk);
        	linkbut.setText(R.string.nitklink);
        }
        else if (aboutof.equals("SGC"))
        {
        	abtimg.setImageResource(R.drawable.sgc_logo);
        	abttext.setText(R.string.about_sgc);
        	linkbut.setText(R.string.sgclink);
        }
        else if (aboutof.equals("Astro"))
        {
        	abtimg.setImageResource(R.drawable.engi_logo);
        	abttext.setText(R.string.about_Astro);
        	linkbut.setText(R.string.astrolink);
        }
        
        linkbut.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
		        intent.setAction(Intent.ACTION_VIEW);
		        intent.addCategory(Intent.CATEGORY_BROWSABLE);
		        intent.setData(Uri.parse(linkbut.getText().toString()));
		        startActivity(intent);
		        Button_Anim(arg0);
		        Display();
			}
		});
	}

	@Override
	public void onBackPressed() {
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
	
	public void Button_Anim(View v)
	{
		Animation animation = null;
		animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.hyperspace_out);
		animation.setDuration(150);
		v.startAnimation(animation);
		animation = null;
	}
}
