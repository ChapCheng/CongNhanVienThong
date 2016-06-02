package com.congnhanvienthong.qlnt;

import java.util.ArrayList;

import com.congnhanvienthong.ActivityBaseToDisplay;
import com.google.gson.Gson;

import android.os.Bundle;
import congnhanvienthong.entity.vitri.DoiTuong;
import webservice.WebProtocol;
import webservice.vitri.LayListThongTinToaDoTask;

public class CapNhatToaDo extends ActivityBaseToDisplay {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		LayListThongTinToaDoTask task = new LayListThongTinToaDoTask();
		DoiTuong doiTuong = new DoiTuong();
		doiTuong.setID_DOITUONG(5016);
		doiTuong.setID_LOAIDOITUONG(560);
		doiTuong.setID_HETHONGGOC(6);
		ArrayList<DoiTuong> lstDoiTuong = new ArrayList<DoiTuong>();
		for (int i = 0; i < 1; i++) {
			lstDoiTuong.add(doiTuong);

		}
		Gson gs = new Gson();
		String obj = gs.toJson(lstDoiTuong);
		System.out.println(obj);

		task.addParam("lstObjJson", obj);
		task.addParam("maTtp", "HNI");
		task.addParam("isGetImages", false);
		onExecuteToServer(true, null, task);
	}

	@Override
	public void onsucces(WebProtocol task) {
		// TODO Auto-generated method stub
		super.onsucces(task);
		task.toString();
	}

}
