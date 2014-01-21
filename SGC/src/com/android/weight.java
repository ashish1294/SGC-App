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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class weight extends Activity {

	EditText weight;
	String enteredweight= new String();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weight);
		
		TextView weightinfo = (TextView) findViewById(R.id.weighttext);
		weight = (EditText) findViewById(R.id.editweight);
		
		Button weightsub = (Button) findViewById(R.id.weightsubmit);
		Typeface Satisfy = Typeface.createFromAsset(getAssets(), "fonts/Satisfy.ttf");
		Typeface Lycanthrope = Typeface.createFromAsset(getAssets(), "fonts/Lycanthrope.ttf");
		weightinfo.setTypeface(Satisfy);
		weightsub.setTypeface(Lycanthrope);
		weightsub.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Button_Anim(arg0);
				enteredweight = weight.getText().toString();
				if(enteredweight.equals("")||enteredweight.equals(null))
					Toast.makeText(getApplicationContext(), "Please Enter Your Weight", Toast.LENGTH_SHORT).show();
				else
				{
					Intent wr = new Intent(getApplicationContext(), WeightResult.class);
					wr.putExtra("weight", enteredweight);
					Log.e("Weight Result Screen",".");
					startActivity(wr);
					Display();
				}
			}
		});
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		weight = (EditText) findViewById(R.id.editweight);
		weight.setText(null);
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
		Display();
	}
	
}
