package com.congnhanvienthong;

import java.util.ArrayList;
import java.util.Vector;
import org.ksoap2.serialization.SoapObject;

import webservice.BaseTask;
import webservice.GetProfileTDTask;
import webservice.WebProtocol;
import webservice.dhsc.LayListDichVuTask;
import android.os.Bundle;
import android.widget.TextView;
import congnhanvienthong.entity.dhsc.LoaiDichVu;
import control.Util;

public class ActivityProfileTD extends ActivityBaseToDisplay {
	GetProfileTDTask getProfileTDTask;
	TextView name, tel, mobile, office, birth;
	LayListDichVuTask layListDichVuTask;
	ArrayList<LoaiDichVu> lst = new ArrayList<LoaiDichVu>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getProfileTDTask = new GetProfileTDTask();
		getProfileTDTask.setData(Util.userName);
		setHeader("Thông tin tài khoản");
		if (Util.listLoaiDichVu == null && Util.isUserDHSC == true) {
			layListDichVuTask = new LayListDichVuTask();
			layListDichVuTask.addParam("id_ttpho", Util.ttp.getId_ttpho());
			layListDichVuTask.addParam("error", 1);
			Task task = new Task();
			task.execute(layListDichVuTask);
		} else {
			if (Util.userSoap == null) {
				onExecuteToServer(true, null, getProfileTDTask);
			} else {
				setData(Util.userSoap);
			}

		}

	}

	@Override
	public void onsucces(WebProtocol task) {
		// TODO Auto-generated method stub
		super.onsucces(task);
		if (task.equals(getProfileTDTask)) {
			SoapObject userSoap = (SoapObject) getProfileTDTask.result;
			setData(userSoap);
			Util.userSoap = userSoap;
		}
		if (task.equals(layListDichVuTask)) {
			Vector<Object> vec = (Vector<Object>) layListDichVuTask.result;
			SoapObject result = (SoapObject) vec.get(0);
			int length = result.getPropertyCount();
			for (int i = 0; i < length; i++) {
				SoapObject temp = (SoapObject) result.getProperty(i);
				LoaiDichVu loaiDichVu = new LoaiDichVu();
				Util.GetObjectFromSoapObject(loaiDichVu, temp);
				lst.add(loaiDichVu);
				//
			}
			Util.listLoaiDichVu = lst;
			if (Util.userSoap == null) {
				onExecuteToServer(true, null, getProfileTDTask);
			} else {
				setData(Util.userSoap);
			}
		}
	}

	public void setData(SoapObject user) {
		setBodyLayout(R.layout.activity_profile_td);
		name = (TextView) body.findViewById(R.id.profile_name);
		tel = (TextView) body.findViewById(R.id.profile_tel);
		mobile = (TextView) body.findViewById(R.id.profile_mobile);
		office = (TextView) body.findViewById(R.id.profile_donvi);
		birth = (TextView) body.findViewById(R.id.profile_birth);

		name.setText(user.getProperty(1).toString());
		tel.setText(user.getProperty(6).toString());
		mobile.setText(user.getProperty(7).toString());
		office.setText(user.getProperty(4).toString() + " - " + user.getProperty(5).toString());
		birth.setText(user.getProperty(2).toString());

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
	}
}
