package com.congnhanvienthong.qltt;

import com.congnhanvienthong.ActivityBaseToDisplay;
import com.congnhanvienthong.R;
import com.google.gson.Gson;
import com.google.zxing.client.android.CaptureActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import congnhanvienthong.entity.qltt.ThongTinTraCuoc;
import control.Util;
import webservice.WebProtocol;
import webservice.qltt.PaymentManagementSearchingTask;

@SuppressLint("NewApi")
public class ActivityTraCuuNo extends ActivityBaseToDisplay {
	EditText txtKyCuoc, txtInput;
	TextView txtKetQua;
	private DatePickerDialog date;
	Button bttOk, bttBarcode;
	PaymentManagementSearchingTask managementSearchingTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setBodyLayout(R.layout.activity_tracuu_no_qltt);
		txtKyCuoc = (EditText) body.findViewById(R.id.txtkycuoc);
		txtKyCuoc.setOnClickListener(this);
		txtInput = (EditText) body.findViewById(R.id.txtinput);
		txtKetQua = (TextView) body.findViewById(R.id.ketqua);
		setFootLayout(R.layout.foot_tracuu);
		bttOk = (Button) foot.findViewById(R.id.bttOK);
		bttBarcode = (Button) body.findViewById(R.id.btn_barcode);
		bttOk.setOnClickListener(this);
		bttBarcode.setOnClickListener(this);

	}

	@Override
	public void onsucces(WebProtocol ws) {
		// TODO Auto-generated method stub
		super.onsucces(ws);
		String temp = managementSearchingTask.result.toString();
		try {
			Gson gson = new Gson();
			ThongTinTraCuoc thongTinTraCuoc = gson.fromJson(temp, ThongTinTraCuoc.class);
			if (!thongTinTraCuoc.getErrorCode().trim().equals("0")) {
				Util.showAlert(context, thongTinTraCuoc.getErrorMessage());
				return;
			}
			// ThongTinNo[] lst = thongTinTraCuoc.getThongTinNo();
			// Collections.reverse(lst);
			// thongTinTraCuoc.set
			thongTinTraCuoc.setThongTinNoString();
			Util.setTextFromObject(txtKetQua, thongTinTraCuoc);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.txtkycuoc:
			// DatePickerDialog datePick = new DatePickerDialog
			Time today = new Time(Time.getCurrentTimezone());
			today.setToNow();
			txtKyCuoc.setText("");
			int month = today.month;
			int year = today.year;
			date = new DatePickerDialog(context, new OnDateSetListener() {

				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
					// TODO Auto-generated method stub
					txtKyCuoc.setText(monthOfYear + 1 + "/" + year);
				}
			}, year, month, 10);
			((ViewGroup) date.getDatePicker()).findViewById(Resources.getSystem().getIdentifier("day", "id", "android"))
					.setVisibility(View.GONE);

			if (!date.isShowing())
				date.show();
			break;
		case R.id.bttOK:
			String kycuoc = txtKyCuoc.getText().toString();
			String input = txtInput.getText().toString();
			txtKetQua.setText("");
			managementSearchingTask = new PaymentManagementSearchingTask();
			managementSearchingTask.addParam("value", input);
			managementSearchingTask.addParam("cycle", kycuoc);
			managementSearchingTask.addParam("province", Util.ttp.getMa_Ttp());
			onExecuteToServer(true, "Tra cứu cước", managementSearchingTask);
			break;
		case R.id.btn_barcode:
			Intent intent = new Intent(getApplicationContext(), CaptureActivity.class);
			intent.setAction("com.google.zxing.client.android.SCAN");
			intent.putExtra("SAVE_HISTORY", false);
			startActivityForResult(intent, 0);
			break;

		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, intent);
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				String res = intent.getStringExtra("SCAN_RESULT");
				txtInput.setText(res);
			}
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
	}

}
