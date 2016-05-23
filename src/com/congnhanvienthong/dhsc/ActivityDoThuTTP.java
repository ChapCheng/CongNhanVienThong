package com.congnhanvienthong.dhsc;

import org.ksoap2.serialization.SoapObject;

import webservice.BaseTask;
import webservice.WebProtocol;
import webservice.dhsc.DoThuTask;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.congnhanvienthong.ActivityBaseToDisplay;
import com.congnhanvienthong.R;

import congnhanvienthong.entity.dhsc.ThamSoDSLAM;
import congnhanvienthong.entity.gtacs.ThongTinLyLichMay;
import control.Util;

public class ActivityDoThuTTP extends ActivityBaseToDisplay {
	Button bttOk;
	EditText txtMaDV;
	Spinner spnLoaiDichVu;
	int loaiDV = 0;
	DoThuTask doThuTask;
	TextView txtResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setBodyLayout(R.layout.activity_dothu_ttp);
		setFootLayout(R.layout.foot_tracuu);
		txtMaDV = (EditText) body.findViewById(R.id.txt_ma_dv);
		txtResult = (TextView) body.findViewById(R.id.ketqua);
		spnLoaiDichVu = (Spinner) body.findViewById(R.id.spn_loai_dichvu);
		bttOk = (Button) foot.findViewById(R.id.bttOK);
		context = ActivityDoThuTTP.this;
		spnLoaiDichVu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					loaiDV = 8;
					break;
				case 1:
					loaiDV = 6;
					break;
				case 2:
					loaiDV = 7;
					break;

				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		bttOk.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		String maDV = txtMaDV.getText().toString();
		if (maDV.trim().length() == 0) {
			Util.showAlert(context, "Kiểm tra lại mã dịch vụ!");
			return;
		} else {
			doThuTask = new DoThuTask();
			doThuTask.input.add(loaiDV);
			doThuTask.input.add(maDV);
			doThuTask.input.add("1");
			onExecuteToServer(true, "Đo thông số DSLAM ?", doThuTask);
		}

	}

	@Override
	public void onsucces(WebProtocol task) {
		// TODO Auto-generated method stub
		super.onsucces(task);
		try {
			SoapObject arrString = (SoapObject) doThuTask.result;
			String result = arrString.getPrimitivePropertyAsString("isError");
			String mess = arrString.getPrimitivePropertyAsString("Message");
			if (result.toLowerCase().equals("true")) {
				Util.showAlert(context, mess);
				return;
			}
			SoapObject dslam = (SoapObject) arrString.getProperty("Result");

			ThamSoDSLAM thamSoDSLAM = new ThamSoDSLAM();

			Util.GetObjectFromSoapObject(thamSoDSLAM, dslam);
			System.out.println(thamSoDSLAM);
			Util.setTextFromObject(txtResult, thamSoDSLAM);

		} catch (Exception e) {
			// TODO: handle exception
			// Util.processException(e, context);
		}
	}
}
