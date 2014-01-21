package com.android;

import org.apache.http.protocol.HTTP;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Contact extends Activity{

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contactus);

		Typeface Jim = Typeface.createFromAsset(getAssets(), "fonts/Jim.ttf");
		Typeface Mar = Typeface.createFromAsset(getAssets(), "fonts/Mar.ttf");
		Typeface Satisfy = Typeface.createFromAsset(getAssets(), "fonts/Satisfy.ttf");
		
		TextView astrocontext = (TextView) findViewById(R.id.conastrotext);
		TextView bugcontext = (TextView) findViewById(R.id.conbugtext);
		TextView astronum = (TextView) findViewById(R.id.conastronum);
		TextView bugnum = (TextView) findViewById(R.id.conbugnum);
		TextView astroemail = (TextView) findViewById(R.id.conastroemail);
		TextView bugemail = (TextView) findViewById(R.id.conbugemail);
		
		astrocontext.setTypeface(Satisfy);
		bugcontext.setTypeface(Satisfy);
		astronum.setTypeface(Mar);
		bugnum.setTypeface(Mar);
		astroemail.setTypeface(Jim);
		bugemail.setTypeface(Jim);
		
		ImageButton astrocallbut = (ImageButton) findViewById(R.id.astrocall);
		ImageButton astrosmsbut = (ImageButton) findViewById(R.id.astrosms);
		ImageButton astroemailbut = (ImageButton) findViewById(R.id.astroemail);
		
		ImageButton bugcallbut = (ImageButton) findViewById(R.id.bugcall);
		ImageButton bugsmsbut = (ImageButton) findViewById(R.id.bugsms);
		ImageButton bugemailbut = (ImageButton) findViewById(R.id.bugemail);
		
		astrocallbut.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				astrocall(1,v);
			}});
		astrosmsbut.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				astrosms(1,v);
			}});
		astroemailbut.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				astroemail(1,v);
			}});
		bugsmsbut.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				astrosms(2,v);
			}});
		bugcallbut.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				astrocall(2,v);
			}});
		bugemailbut.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				astroemail(2,v);
			}});
	}
	public void astrosms(int i,View v)
	{
		try
		{
		Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
		smsIntent.setType("vnd.android-dir/mms-sms");
		smsIntent.addCategory(Intent.CATEGORY_DEFAULT);
		if (i==1)
			smsIntent.setData(Uri.parse("sms:" + getResources().getString(R.string.conastrocall)));
		else
			smsIntent.setData(Uri.parse("sms:" + getResources().getString(R.string.conbugcall)));
		startActivity(smsIntent);
		Button_Anim(v);
		Display();
		}
		catch (ActivityNotFoundException e)
		{
			Toast.makeText(getApplicationContext(), "Your Device do not support SMS", Toast.LENGTH_LONG).show();
			Log.e("Activity","Activity_not_found_for_sms_con");
		}
		catch (Exception c)
		{
			Toast.makeText(getApplicationContext(), "Unable to SMS !!", Toast.LENGTH_LONG).show();
		}
	}
	public void astrocall(int i,View v)
	{
		try
		{
		Intent callIntent = new Intent(Intent.ACTION_CALL);
		callIntent.addCategory(Intent.CATEGORY_DEFAULT);
		if (i==1)
			callIntent.setData(Uri.parse("tel:"+getResources().getString(R.string.conastrocall)));
		else
			callIntent.setData(Uri.parse("tel:"+getResources().getString(R.string.conbugcall)));
        startActivity(callIntent);
        
        Button_Anim(v);
		Display();
		}
		catch (ActivityNotFoundException e)
		{
			Toast.makeText(getApplicationContext(), "Your Device do not support Calling", Toast.LENGTH_LONG).show();
			Log.e("Activity","Activity_not_found_for_call_con");
		}
		catch (Exception c)
		{
			Toast.makeText(getApplicationContext(), "Unable to CALL !!", Toast.LENGTH_LONG).show();
		}
	}
	public void astroemail(int i,View v)
	{
		try
		{
			try
			{
				Intent emailIntent = new Intent(android.content.Intent.ACTION_SENDTO);
				emailIntent.addCategory(Intent.CATEGORY_DEFAULT);
				String uriText;
				if (i==1)
					uriText = "mailto:" + getResources().getString(R.string.conastroemail) + 
			              "?subject=Query Regarding SGC/Astro Events" + 
			              "&body=";
				else
					uriText = "mailto:" +  getResources().getString(R.string.conbugemail) +
				              "?subject=Error Reporting SGC Android App" + 
				              "&body=";
				uriText = uriText.replace(" ", "%20");
				Uri uri = Uri.parse(uriText);
				emailIntent.setData(uri);
				startActivity(Intent.createChooser(emailIntent, "Send e-mail via..."));
			}
			catch(Exception a)
			{
				Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
				emailIntent.addCategory(Intent.CATEGORY_DEFAULT);
				emailIntent.setType("text/html");
				emailIntent.setType(HTTP.PLAIN_TEXT_TYPE);
				emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"ashish1294@gmail.com"});
				emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Error Report for SGC Android App");
				startActivity(Intent.createChooser(emailIntent, "Send e-mail via..."));
			}
			Button_Anim(v);
			Display();
		}
		catch (ActivityNotFoundException e)
		{
			Toast.makeText(getApplicationContext(), "No Email Clients Found !!", Toast.LENGTH_LONG).show();
			Log.e("Activity","Activity_not_found_for_email_con");
		}
		catch (Exception c)
		{
			Toast.makeText(getApplicationContext(), "Unable to EMail !!", Toast.LENGTH_LONG).show();
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
		// TODO Auto-generated method stub
		super.onBackPressed();
		Display();
	}
	
	
}
