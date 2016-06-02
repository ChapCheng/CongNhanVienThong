package com.congnhanvienthong.pttb;

import java.util.ArrayList;

import com.congnhanvienthong.ActivityBaseToDisplay;
import com.congnhanvienthong.R;

import adapter.BaseListViewAdapter;
import adapter.BaseSpinnerAdapter;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import congnhanvienthong.entity.tracuuchung.KieuSapXep;
import congnhanvienthong.entity.tracuuchung.KieuTimKiem;
import congnhanvienthong.entity.tracuuchung.ThongTinTraCuuChung;
import control.Util;
import webservice.WebProtocol;
import webservice.tracuuchung.DanhMucCheDoSapXepTask;
import webservice.tracuuchung.DanhMucKieuTimKiemTask;
import webservice.tracuuchung.TraCuuChungTask;

public class ActivityTraCuuChung extends ActivityBaseToDisplay {
	Spinner spnKieuTimKiem, spnKieuSapXep;
	ArrayList<KieuSapXep> lstKieuSapXepObj;
	ArrayList<KieuTimKiem> lstKieuTimKiemObj;
	ArrayList<ThongTinTraCuuChung> lstThongTinTraCuuChungObj;
	Button btnOK, btnClose;
	DanhMucCheDoSapXepTask danhMucKieuSapXepTask;
	DanhMucKieuTimKiemTask danhMucKieuTimKiemTask;
	TraCuuChungTask tracuuTask;
	ListView lstKetQua;
	BaseListViewAdapter<ThongTinTraCuuChung> adapter;
	EditText txtTimKiem;
	TextView txtLabel;
	Dialog dialog;
	TextView txtDetail, txtInfor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setBodyLayout(R.layout.activity_tracuuchung);
		setFootLayout(R.layout.foot_tracuu);
		btnOK = (Button) foot.findViewById(R.id.bttOK);
		disableShortCut();
		btnOK.setOnClickListener(this);
		setHeader("Tra cứu chung");
		lstKetQua = (ListView) body.findViewById(R.id.lstData);
		// txtInfor = new TextView(this);
		// txtInfor.setLayoutParams(new
		// Gallery.LayoutParams(LayoutParams.WRAP_CONTENT,
		// LayoutParams.WRAP_CONTENT));
		txtTimKiem = (EditText) body.findViewById(R.id.inputData);
		danhMucKieuSapXepTask = new DanhMucCheDoSapXepTask();
		danhMucKieuTimKiemTask = new DanhMucKieuTimKiemTask();
		spnKieuSapXep = (Spinner) body.findViewById(R.id.spnsapxep);
		spnKieuTimKiem = (Spinner) body.findViewById(R.id.spnkiemtimkiem);
		onExecuteToServer(true, null, danhMucKieuSapXepTask, danhMucKieuTimKiemTask);
		dialog = new Dialog(context);
		dialog.setContentView(R.layout.tracuuchung_detail);
		dialog.setCancelable(true);
		dialog.setTitle("Thông tin chi tiết");
		txtDetail = (TextView) dialog.findViewById(R.id.ketqua);
		btnClose = (Button) dialog.findViewById(R.id.btnClose);
		btnClose.setOnClickListener(this);
		lstKetQua.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				ThongTinTraCuuChung thongtin = (ThongTinTraCuuChung) parent.getAdapter().getItem(position);
				Util.setTextFromObjectAllField(txtDetail, thongtin);
				dialog.show();

			}
		});

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

			try {
				lstThongTinTraCuuChungObj = (ArrayList<ThongTinTraCuuChung>) tracuuTask.getResult();
				adapter = new BaseListViewAdapter<ThongTinTraCuuChung>(context, lstThongTinTraCuuChungObj, true);
				lstKetQua.setAdapter(adapter);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			// txtInfor.setText("Có tổng số " + lstThongTinTraCuuChungObj.size()
			// + " phù hợp.");

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
			tracuuTask = new TraCuuChungTask();
			KieuSapXep sapxep = (KieuSapXep) spnKieuSapXep.getSelectedItem();
			KieuTimKiem kieuTimKiem = (KieuTimKiem) spnKieuTimKiem.getSelectedItem();
			tracuuTask.addParam("SearchText", timkiem);
			tracuuTask.addParam("SearchOption", kieuTimKiem.getIdKieuTimKiem());
			tracuuTask.addParam("SearchOrder", sapxep.getIdKieuSapXep());
			tracuuTask.addParam("TinhTP_ID", Util.ttp.getMa_Ttp());
			if (timkiem.equals("")) {
				Util.showAlert(context, "Nhập thông tin tìm kiếm");
				txtTimKiem.setFocusable(true);
			} else {
				lstKetQua.setAdapter(null);
				onExecuteToServer(true, "Tra cứu ?", tracuuTask);

			}
			break;
		case R.id.btnClose:
			dialog.dismiss();
			break;

		}

	}

}
