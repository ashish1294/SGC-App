package com.android;

import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.Surface;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class WeightResult extends Activity{
	
	String Planets[]={"Mercury","Venus","Mars","Jupiter","Saturn","Uranus","Neptune","Pluto"};
	double con_fac[]={0.378,0.907,0.377,2.364,1.064,0.899,1.123,0.067};
	
	String Satellite[]= {"Moon","Io","Europa", "Ganymede", "Callisto"};
	double con_sat_fac[]={0.166,0.1264,0.13358 ,0.1448,0.18355};
	
	String Stars[]= {"Sun","White Dwarf","Neutron Star"};
	double con_star_fac[] = {27.072,1345000.0,143500000000.0};
	
	String g_plan_weight(Integer weight,int i)
	{
		double temp= con_fac[i]*weight;
		String r = String.format(Locale.getDefault(),"%.3f", temp);
		return r;
	}
	
	String g_sat_weight(Integer weight,int i)
	{
		double temp= con_sat_fac[i]*weight;
		String r = String.format(Locale.getDefault(),"%.3f", temp);
		return r;
	}
	
	String g_star_weight(Integer weight,int i)
	{
		double temp= con_star_fac[i]*weight;
		String r = String.format(Locale.getDefault(),"%.1f", temp);
		return r;
	}
	TableRow tr,head1,head2,head3;
	TextView tv,tv1,tvh1,tvh2,tvh3;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weightresult);
		
		//tv=new TextView(this);
		//tv1=new TextView(this);
		//tr = new TableRow(this);
		
		Typeface Jim = Typeface.createFromAsset(getAssets(), "fonts/Jim.ttf");
		Typeface Demon = Typeface.createFromAsset(getAssets(), "fonts/Demons.ttf");
		
		TableRow.LayoutParams headpr = new TableRow.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		TableLayout.LayoutParams lp = new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		
		TextView tvh1 =new TextView(this);
		TextView tvh2 =new TextView(this);
		TextView tvh3 =new TextView(this);
		
		tvh1.setText("Planets");
		tvh2.setText("Satellites");
		tvh3.setText("Stars");
		
		tvh1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
		tvh1.setLayoutParams(headpr);
		tvh1.setGravity(Gravity.CENTER_HORIZONTAL);
		tvh1.setTextColor(Color.parseColor("#FFA43C"));
		tvh1.setTypeface(Demon);
		
		tvh2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
		tvh2.setLayoutParams(headpr);
		tvh2.setGravity(Gravity.CENTER_HORIZONTAL);
		tvh2.setTextColor(Color.parseColor("#FFA43C"));
		tvh2.setTypeface(Demon);
		
		tvh3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
		tvh3.setLayoutParams(headpr);
		tvh3.setGravity(Gravity.CENTER_HORIZONTAL);
		tvh3.setTextColor(Color.parseColor("#FFA43C"));
		tvh3.setTypeface(Demon);
		
		TableRow head1=new TableRow(this);
		head1.addView(tvh1);
		TableRow head2=new TableRow(this);
		head2.addView(tvh2);
		TableRow head3=new TableRow(this);
		head3.addView(tvh3);
		
		Intent previous =getIntent();
        String weight_pre = new String(previous.getStringExtra("weight"));
        int i;
        Integer iweight = Integer.parseInt(weight_pre);
        TableLayout tl = (TableLayout) findViewById(R.id.weighttable);
 
        tl.addView(head1);
        for (i=0;i<Planets.length;i++)
        {
        	TableRow tr = new TableRow(this);
	        TextView tv = new TextView(this);
	        TextView tv1= new TextView(this);
	        tv.setText(Planets[i]);
	        tv1.setText(g_plan_weight(iweight,i));
	        //tv.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	        //tv1.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	        tv.setTextColor(Color.parseColor("#0000FF"));
	        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
	        tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
	        tv.setTypeface(Jim);
	        tv1.setTextColor(Color.YELLOW);
	        tr.addView(tv);
	        tr.addView(tv1);
	        tl.addView(tr,lp);
        }
        tl.addView(head2);
        for (i=0;i<Satellite.length;i++)
        {
        	tr = new TableRow(WeightResult.this);
	        tr.setLayoutParams(lp);
	        tv = new TextView(this);
	        tv1= new TextView(this);
	        tv.setText(Satellite[i]);
	        tv1.setText(g_sat_weight(iweight,i));
	        tv.setTextColor(Color.parseColor("#0000FF"));
	        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
	        tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
	        tv.setTypeface(Jim);
	        tv1.setTextColor(Color.YELLOW);
	        tl.addView(tr);
	        tr.addView(tv);
	        tr.addView(tv1);
        }
        tl.addView(head3);
        for (i=0;i<Stars.length;i++)
        {
        	tr = new TableRow(WeightResult.this);
	        tr.setLayoutParams(lp);
	        tv = new TextView(this);
	        tv1= new TextView(this);
	        tv.setText(Stars[i]);
	        tv1.setText(g_star_weight(iweight,i));
	        tv.setTextColor(Color.parseColor("#0000FF"));
	        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
	        tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
	        tv.setTypeface(Jim);
	        tv1.setTextColor(Color.YELLOW);
	        tl.addView(tr);
	        tr.addView(tv);
	        tr.addView(tv1);
        }
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
		Display display = ((WindowManager) 
				getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
			if ((display.getRotation() == Surface.ROTATION_0) || 
				(display.getRotation() == Surface.ROTATION_180)) {
				overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
			} else if ((display.getRotation() == Surface.ROTATION_90) ||
					   (display.getRotation() == Surface.ROTATION_270)) {
				overridePendingTransition(R.anim.slide_up, R.anim.slide_down);
			}
	}
}
