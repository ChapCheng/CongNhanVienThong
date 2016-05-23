package com.congnhanvienthong.pttb;

import java.util.ArrayList;
import java.util.Locale;

import com.congnhanvienthong.ActivityBaseToDisplay;
import com.congnhanvienthong.R;

import adapter.BaseListViewAdapter;
import adapter.BaseSpinnerAdapter;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import congnhanvienthong.entity.tracuuchung.KieuSapXep;
import congnhanvienthong.entity.tracuuchung.KieuTimKiem;
import congnhanvienthong.entity.tracuuchung.ThongTinTraCuuChung;
import control.Util;
import webservice.BaseTask;
import webservice.WebProtocol;
import webservice.tracuuchung.DanhMucCheDoSapXepTask;
import webservice.tracuuchung.DanhMucKieuTimKiemTask;
import webservice.tracuuchung.TraCuuChungTask;

public class ActivityTraCuuChung extends ActivityBaseToDisplay {
	Spinner spnKieuTimKiem, spnKieuSapXep;
	ArrayList<KieuSapXep> lstKieuSapXepObj;
	ArrayList<KieuTimKiem> lstKieuTimKiemObj;
	ArrayList<ThongTinTraCuuChung> lstThongTinTraCuuChungObj;
	Button btnOK;
	DanhMucCheDoSapXepTask danhMucKieuSapXepTask;
	DanhMucKieuTimKiemTask danhMucKieuTimKiemTask;
	TraCuuChungTask tracuuTask;
	ListView lstKetQua;
	BaseListViewAdapter<ThongTinTraCuuChung> adapter;
	EditText txtFillter, txtTimKiem;
	TextView txtLabel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setBodyLayout(R.layout.activity_tracuuchung);
		setFootLayout(R.layout.foot_tracuu);
		btnOK = (Button) foot.findViewById(R.id.bttOK);
		disableShortCut();
		btnOK.setOnClickListener(this);
		lstKetQua = (ListView) body.findViewById(R.id.lstData);

		txtFillter = (EditText) body.findViewById(R.id.txtFillter);
		txtTimKiem = (EditText) body.findViewById(R.id.inputData);
		txtFillter.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				String text = txtFillter.getText().toString().toLowerCase(Locale.getDefault());
				if (adapter != null)
					adapter.Fillter(text);
				// Util.setListViewHeightBasedOnChildren(lstKetQua);

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
			}
		});
		danhMucKieuSapXepTask = new DanhMucCheDoSapXepTask();
		danhMucKieuTimKiemTask = new DanhMucKieuTimKiemTask();
		spnKieuSapXep = (Spinner) body.findViewById(R.id.spnsapxep);
		spnKieuTimKiem = (Spinner) body.findViewById(R.id.spnkiemtimkiem);

		onExecuteToServer(true, null, danhMucKieuSapXepTask, danhMucKieuTimKiemTask);

	}

	@SuppressWarnings("unchecked")
	@Override
	public void onsucces(WebProtocol ws) {
		// TODO Auto-generated method stub
		super.onsucces(ws);
		if (ws.equals(danhMucKieuSapXepTask)) {
			lstKieuSapXepObj = (ArrayList<KieuSapXep>) danhMucKieuSapXepTask.getResult();
			lstKieuTimKiemObj = (ArrayList<KieuTimKiem>) danhMucKieuTimKiemTask.getResult();
			BaseSpinnerAdapter<KieuSapXep> SapXepAdapter = new BaseSpinnerAdapter<KieuSapXep>(context,
					android.R.layout.simple_spinner_dropdown_item, lstKieuSapXepObj);
			BaseSpinnerAdapter<KieuTimKiem> TimKiemAdapter = new BaseSpinnerAdapter<KieuTimKiem>(context,
					android.R.layout.simple_spinner_dropdown_item, lstKieuTimKiemObj);
			SapXepAdapter.name = "TenKieuSapXep";
			TimKiemAdapter.name = "TenKieuTimKiem";
			spnKieuSapXep.setAdapter(SapXepAdapter);
			spnKieuTimKiem.setAdapter(TimKiemAdapter);
		}
		if (ws.equals(tracuuTask)) {
			lstThongTinTraCuuChungObj = (ArrayList<ThongTinTraCuuChung>) tracuuTask.getResult();
			adapter = new BaseListViewAdapter<ThongTinTraCuuChung>(context, lstThongTinTraCuuChungObj, null, false);
			lstKetQua.setAdapter(adapter);

		}

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.bttOK:
			String timkiem = txtTimKiem.getText().toString();
			txtFillter.setText("");
			tracuuTask = new TraCuuChungTask();

			KieuSapXep sapxep = (KieuSapXep) spnKieuSapXep.getSelectedItem();
			KieuTimKiem kieuTimKiem = (KieuTimKiem) spnKieuTimKiem.getSelectedItem();
			tracuuTask.input.add(timkiem);
			tracuuTask.input.add(kieuTimKiem.getIdKieuTimKiem());
			tracuuTask.input.add(sapxep.getIdKieuSapXep());
			tracuuTask.input.add(Util.ttp.getMa_Ttp());
			if (timkiem.equals("")) {
				Util.showAlert(context, "Nhập thông tin tìm kiếm");
				txtTimKiem.setFocusable(true);
			} else {
				lstKetQua.setAdapter(null);
				onExecuteToServer(true, null, tracuuTask);

			}
			break;

		}

	}

}
