package com.congnhanvienthong.dhsc;

import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import com.congnhanvienthong.ActivityBaseToDisplay;
import com.congnhanvienthong.R;

import adapter.BaseSpinnerAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import congnhanvienthong.entity.dhsc.LoaiDichVu;
import congnhanvienthong.entity.dhsc.NoiDungHong;
import control.Util;
import webservice.WebProtocol;
import webservice.dhsc.GetNoiDungBaoHongTask;
import webservice.dhsc.NhanBaoHongTTPTask;

public class ActivityBaoHongTTP extends ActivityBaseToDisplay {
	Spinner spnLoaiDichVu, spnNoiDungHong;
	LoaiDichVu ldv;
	GetNoiDungBaoHongTask getNoiDungBaoHongTask;
	ArrayList<NoiDungHong> lstDungHongs = new ArrayList<NoiDungHong>();
	NhanBaoHongTTPTask nhanBaoHongTTPTask;
	Button bttOK;
	NoiDungHong ndBaoHong;
	EditText txtMaDichVu, txtAcc, txtNoiDungHong, txtLienHe, txtTenLienHe;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = ActivityBaoHongTTP.this;
		setBodyLayout(R.layout.activity_baohong_ttp);
		setFootLayout(R.layout.foot_tracuu);
		setHeader("Báo hỏng");
		txtMaDichVu = (EditText) body.findViewById(R.id.inputMa);
		txtAcc = (EditText) body.findViewById(R.id.inputAcc);
		txtNoiDungHong = (EditText) body.findViewById(R.id.inputnd_hong);
		txtLienHe = (EditText) body.findViewById(R.id.inputLienHe);
		txtTenLienHe = (EditText) body.findViewById(R.id.input_tenlienhe);
		if (Util.ttp.getId_ttpho().equals("1")) {
			txtAcc.setVisibility(View.GONE);
		}
		bttOK = (Button) foot.findViewById(R.id.bttOK);
		bttOK.setOnClickListener(this);
		spnLoaiDichVu = (Spinner) body.findViewById(R.id.spn_loaidv);
		spnNoiDungHong = (Spinner) body.findViewById(R.id.spn_baohong);
		ArrayList<LoaiDichVu> lstLoaiDichVU = (ArrayList<LoaiDichVu>) Util.listLoaiDichVu;
		BaseSpinnerAdapter<LoaiDichVu> adapterLoaiDichVu = new BaseSpinnerAdapter<LoaiDichVu>(context,
				android.R.layout.simple_spinner_dropdown_item, lstLoaiDichVU);
		adapterLoaiDichVu.name = "TenLoaiDichVu";
		spnLoaiDichVu.setAdapter(adapterLoaiDichVu);
		spnLoaiDichVu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				ldv = (LoaiDichVu) arg0.getAdapter().getItem(arg2);
				getNoiDungBaoHongTask = new GetNoiDungBaoHongTask();
				getNoiDungBaoHongTask.addParam("dichVuId", ldv.getIdLoaiDichvu());
				getNoiDungBaoHongTask.addParam("userName", Util.userName);
				onExecuteToServer(true, null, getNoiDungBaoHongTask);

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onsucces(WebProtocol task) {
		// TODO Auto-generated method stub
		super.onsucces(task);
		if (task.equals(getNoiDungBaoHongTask)) {
			lstDungHongs = (ArrayList<NoiDungHong>) getNoiDungBaoHongTask.getResult();
			Util.SetDataToSpinner(context, spnNoiDungHong, lstDungHongs, "TenNoiDungBaoHong");

		}
		if (task.equals(nhanBaoHongTTPTask)) {
			try {
				SoapObject temp = (SoapObject) nhanBaoHongTTPTask.result;
				String res = Util.GetData(temp, context, String.class, "IsError", "Message", "Result");
				Util.showAlert(context, res);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
	}

	public boolean Validate() {
		boolean flag = true;
		if (txtMaDichVu.getText().toString().length() == 0 && txtAcc.getText().toString().length() == 0) {
			txtMaDichVu.setError("Phải nhập ít nhất 1 trong 2 giá trị");
			txtAcc.setError("Phải nhập ít nhất 1 trong 2 giá trị");
			flag = false;
		}
		if (txtNoiDungHong.getText().toString().length() == 0) {
			txtNoiDungHong.setError("Chưa nhập nội dung báo hỏng");
			flag = false;
		}
		if (txtLienHe.getText().toString().length() == 0) {
			txtLienHe.setError("Chưa nhập số máy liên hệ");
			flag = false;
		}
		if (txtTenLienHe.getText().toString().length() == 0) {
			txtTenLienHe.setError("Chưa nhập tên người liên hệ");
			flag = false;
		}
		if (ndBaoHong == null) {
			Util.showAlert(context, "Chưa lấy đủ dữ liệu cần thiết!");
			flag = false;
		}
		return flag;

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.bttOK:
			ndBaoHong = (NoiDungHong) spnNoiDungHong.getSelectedItem();
			if (Validate()) {
				nhanBaoHongTTPTask = new NhanBaoHongTTPTask();
				nhanBaoHongTTPTask.addParam("loaiDichVuId", ldv.getIdLoaiDichvu());
				nhanBaoHongTTPTask.addParam("maDichVu", txtMaDichVu.getText().toString());
				nhanBaoHongTTPTask.addParam("tenAccount", txtAcc.getText().toString());
				nhanBaoHongTTPTask.addParam("khan", false);
				nhanBaoHongTTPTask.addParam("noiDungBaoHongId", ndBaoHong.getIdNoiDungBaoHong());
				nhanBaoHongTTPTask.addParam("noiDungBaoHong",
						ndBaoHong.getTenNoiDungBaoHong() + txtNoiDungHong.getText().toString());
				nhanBaoHongTTPTask.addParam("gioHen", "");
				nhanBaoHongTTPTask.addParam("dienThoaiLienHe", txtLienHe.getText().toString());
				nhanBaoHongTTPTask.addParam("diDongLienHe", txtLienHe.getText().toString());
				nhanBaoHongTTPTask.addParam("nguoiLienHe", txtTenLienHe.getText().toString());
				nhanBaoHongTTPTask.addParam("userName", Util.userName);
				nhanBaoHongTTPTask.addParam("tinhThanhPhoId", Util.ttp.getId_ttpho());
				if (Util.ttp.getId_ttpho().equals("1")) {
					txtAcc.setVisibility(View.GONE);
					nhanBaoHongTTPTask.removeParam("tinhThanhPhoId");
					nhanBaoHongTTPTask.removeParam("tenAccount");
					nhanBaoHongTTPTask.addParam("isBaoHongAo", true);
					nhanBaoHongTTPTask.addParam("loaiKhachHangId", "0");
				}
				onExecuteToServer(true, "Xác nhận báo hỏng!", nhanBaoHongTTPTask);
			}

			break;

		default:
			break;
		}
	}
}
