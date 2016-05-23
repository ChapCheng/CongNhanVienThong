package com.congnhanvienthong.qlnt;

import java.util.ArrayList;

import com.congnhanvienthong.ActivityBaseToDisplay;
import com.congnhanvienthong.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import api.GetDanhSachNhaTramApi;
import api.GetDonViQuanLyApi;
import api.GetLoaiNhaTramApi;
import congnhanvienthong.entity.nhatram.DonViQuanLy;
import congnhanvienthong.entity.nhatram.LoaiNhaTram;
import control.Util;
import webservice.WebProtocol;

public class ActivityTraCuuNhaTram extends ActivityBaseToDisplay {
	GetDonViQuanLyApi getDonViApi;
	GetLoaiNhaTramApi getLoaiNhaTramApi;
	Spinner spnLoaiNhaTram, spnDonVi;
	ArrayList<LoaiNhaTram> lstLoaiNhaTram;
	ArrayList<DonViQuanLy> lstDonViQuanLy;
	Button btnOk;
	GetDanhSachNhaTramApi getDSNhaTramApi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setBodyLayout(R.layout.activity_tracuu_nhatram);
		setFootLayout(R.layout.foot_tracuu);
		btnOk = (Button) foot.findViewById(R.id.bttOK);
		btnOk.setOnClickListener(this);
		spnDonVi = (Spinner) body.findViewById(R.id.spnDonViQuanLy);
		spnLoaiNhaTram = (Spinner) body.findViewById(R.id.spnLoaiNhaTram);
		getDonViApi = new GetDonViQuanLyApi();
		getLoaiNhaTramApi = new GetLoaiNhaTramApi();
		onExecuteToServer(true, null, getDonViApi, getLoaiNhaTramApi);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onsucces(WebProtocol task) {
		// TODO Auto-generated method stub
		super.onsucces(task);
		if (task.equals(getDonViApi)) {
			lstDonViQuanLy = new ArrayList<DonViQuanLy>();
			lstDonViQuanLy = (ArrayList<DonViQuanLy>) getDonViApi.getResult();
			Util.SetDataToSpinner(context, spnDonVi, lstDonViQuanLy, "TenDonVi");
			lstLoaiNhaTram = (ArrayList<LoaiNhaTram>) getLoaiNhaTramApi.getResult();
			Util.SetDataToSpinner(context, spnLoaiNhaTram, lstLoaiNhaTram, "TenLoaiNhaTram");

		}
		if (task.equals(getDSNhaTramApi)) {
			String kq = getDSNhaTramApi.result;
			System.out.println(kq);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.bttOK:
			getDSNhaTramApi = new GetDanhSachNhaTramApi();
			LoaiNhaTram loainhatram = (LoaiNhaTram) spnLoaiNhaTram.getSelectedItem();
			DonViQuanLy donvi = (DonViQuanLy) spnDonVi.getSelectedItem();
			getDSNhaTramApi.addParam("loaiNhaTramId", "" + loainhatram.getLoaiNhaTramId());
			getDSNhaTramApi.addParam("donViQuanLyId", "" + donvi.getDonViId());
			getDSNhaTramApi.addParam("maNhaTram", null);
			getDSNhaTramApi.addParam("tenNhaTram", null);
			getDSNhaTramApi.addParam("pageIndex", "0");
			onExecuteToServer(true, "Tìm kiếm nhà trạm", getDSNhaTramApi);
			break;

		default:
			break;
		}
	}

}
