package congnhanvienthong.entity;

import java.util.Locale;

import android.location.Address;

public class MyAddress extends Address{

	public MyAddress(Locale locale) {
		super(locale);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getAddressLine(0)+ ", "+this.getAddressLine(1)+", "+getAddressLine(2);
	}

}
