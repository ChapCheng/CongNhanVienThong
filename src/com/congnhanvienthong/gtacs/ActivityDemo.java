package com.congnhanvienthong.gtacs;

import java.io.IOException;
import java.util.List;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.congnhanvienthong.ActivityBaseToDisplay;
import com.congnhanvienthong.R;

public class ActivityDemo extends ActivityBaseToDisplay{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setBodyLayout(R.layout.quet_ketcuoi_bankinh);
		try {
			List<Address> result = new 
					Geocoder(context).getFromLocationName("ngọc hà", 5);
			System.out.println(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
