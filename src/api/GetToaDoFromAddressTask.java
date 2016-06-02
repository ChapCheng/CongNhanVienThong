package api;

import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import webservice.WebProtocol;

public class GetToaDoFromAddressTask implements WebProtocol {

	String diaChi;
	public Context context;

	public ArrayList<Address> lstAddress;

	public String getDiaChi() {
		return diaChi;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public ArrayList<Address> getLstAddress() {
		return lstAddress;
	}

	public void setLstAddress(ArrayList<Address> lstAddress) {
		this.lstAddress = lstAddress;
	}

	public Address getAdd() {
		return add;
	}

	public void setAdd(Address add) {
		this.add = add;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	Address add;

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		try {
			lstAddress = new ArrayList<Address>();

			try {
				lstAddress = (ArrayList<Address>) new Geocoder(this.context).getFromLocationName(diaChi, 15);
				if (lstAddress.size() > 0) {
					add = lstAddress.get(0);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public Void SetConText(Context context) {
		// TODO Auto-generated method stub
		this.context = context;
		return null;
	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		return add;
	}

}
