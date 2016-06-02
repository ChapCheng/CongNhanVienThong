package com.congnhanvienthong;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.ksoap2.serialization.SoapObject;

import webservice.BaseTask;
import webservice.WebProtocol;
import webservice.dhsc.LayListDichVuTask;
import adapter.BaseSpinnerAdapter;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import congnhanvienthong.entity.dhsc.LoaiDichVu;
import congnhanvienthong.entity.dhsc.NghiepVu;
import congnhanvienthong.entity.dhsc.TinhThanhPho;
import control.Util;

public class ActivityChonTinhThanhPho extends ActivityBaseToDisplay {
	ArrayList<TinhThanhPho> lstTinhThanhPho;
	TinhThanhPho ttp;
	ArrayList<LoaiDichVu> lst = new ArrayList<LoaiDichVu>();
	List<NghiepVu> listNghiepVus = new ArrayList<NghiepVu>();
	Object loaidichvu;
	Boolean hasDialog = true;
	LayListDichVuTask layListDichVuTask;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chontinh);
		context = ActivityChonTinhThanhPho.this;

		Intent i = getIntent();
		// --lấy thông tin từ màn hình cũ
		lstTinhThanhPho = (ArrayList<TinhThanhPho>) i.getSerializableExtra("lstTTP");
		Spinner spinnerTTP = (Spinner) findViewById(R.id.spnTinhThanhPho);
		BaseSpinnerAdapter<TinhThanhPho> adapter = new BaseSpinnerAdapter<TinhThanhPho>(context,
				android.R.layout.simple_spinner_dropdown_item, lstTinhThanhPho);
		adapter.name = "Ten_ttpho";
		spinnerTTP.setAdapter(adapter);
		spinnerTTP.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				ttp = (TinhThanhPho) parent.getAdapter().getItem(position);
				Util.ttp = ttp;

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		// layListNghiepVuTask = new LayListNghiepVuTask();
		onExecuteToServer(true, null, layListDichVuTask);
		Button ok = (Button) findViewById(R.id.bttLoginTTP);
		ok.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				layListDichVuTask = new LayListDichVuTask();
				// layListDichVuTask.para.add("id_ttpho");
				// layListDichVuTask.input.add(Util.ttp.getId_ttpho());
				// layListDichVuTask.para.add("error");
				// layListDichVuTask.input.add("1");
				layListDichVuTask.addParam("id_ttpho", Util.ttp.getId_ttpho());
				layListDichVuTask.addParam("error", 1);
				Task task = new Task();
				task.execute(layListDichVuTask);

			}
		});

	}

	@SuppressWarnings("unchecked")
	@Override
	public void onsucces(WebProtocol name) {
		// TODO Auto-generated method stub
		super.onsucces(name);
		if (name.equals(layListDichVuTask)) {
			try {
				// loaidichvu = ketqua.get(0);
				// SoapObject result = (SoapObject) loaidichvu; // get
				// ECONNREFUSED
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
				Intent i = new Intent(ActivityChonTinhThanhPho.this, ActivityProfileTD.class);
				startActivity(i);
				finish();

			} catch (Exception e) {
				// TODO: handle exception
				AlertDialog.Builder alert = new AlertDialog.Builder(ActivityChonTinhThanhPho.this);
				alert.setTitle("Thông báo!");
				alert.setMessage("Kiểm tra lại đường truyền mạng ");
				alert.setPositiveButton("OK", new OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						System.exit(0);
					}
				});
				alert.show();
			}

		}
	}

}
