package com.congnhanvienthong.pttb;

import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import com.congnhanvienthong.ActivityBaseToDisplay;
import com.congnhanvienthong.R;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import congnhanvienthong.entity.csonline.LoaiDichVu;
import congnhanvienthong.entity.csonline.PhuongXa;
import congnhanvienthong.entity.csonline.QuanHuyen;
import control.Util;
import webservice.BaseTask;
import webservice.WebProtocol;
import webservice.csonline.GetLoaiDichVuTask;
import webservice.csonline.GetLoaiYeuCauTask;
import webservice.csonline.GetPhuongXaTask;
import webservice.csonline.GetQuanHuyenTask;
import webservice.csonline.TiepnhanDVVTTask;

public class CSOActivity extends ActivityBaseToDisplay {
	Spinner spnLoaiDichVu, spnQuanHuyen, spnPhuongXa, spnLoaiYeuCau;
	ArrayList<LoaiDichVu> lstLoaiDichVu;
	ArrayList<QuanHuyen> lstQuanHuyen;
	GetLoaiDichVuTask csOnlineGetLoaiDichVuTask;
	GetPhuongXaTask phuongxaTask;
	GetLoaiYeuCauTask loaiYeuCauTask;
	GetQuanHuyenTask getQuanHuyenTask;
	TiepnhanDVVTTask tiepnhanDVVTTask;
	Button btnOK;
	EditText txtTenkh, txtDiachilapdat, txtTennguoilh, txtDiachilh, txtSodtlh, txtEmail, txtThoigianlh, txtNguoigt,
			txtFonegt, txtGhichu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setBodyLayout(R.layout.activity_csonline);
		setFootLayout(R.layout.foot_tracuu);
		txtTenkh = (EditText) body.findViewById(R.id.txtTenKhachHang);
		txtDiachilapdat = (EditText) body.findViewById(R.id.txtDiaChiLapDat);
		txtTennguoilh = (EditText) body.findViewById(R.id.txtTenNguoiLienHe);
		txtDiachilh = (EditText) body.findViewById(R.id.txtDiaChiLienHe);
		txtSodtlh = (EditText) body.findViewById(R.id.txtDienThoaiLienHe);
		txtEmail = (EditText) body.findViewById(R.id.txtEmail);
		txtThoigianlh = (EditText) body.findViewById(R.id.txtThoiGianLienHe);
		txtNguoigt = (EditText) body.findViewById(R.id.txtNguoiGioiThieu);
		txtGhichu = (EditText) body.findViewById(R.id.txtGhiChu);
		btnOK = (Button) foot.findViewById(R.id.bttOK);
		btnOK.setOnClickListener(this);
		spnLoaiDichVu = (Spinner) body.findViewById(R.id.spn_loaidv);
		spnQuanHuyen = (Spinner) body.findViewById(R.id.spn_quanhuyen);
		spnPhuongXa = (Spinner) body.findViewById(R.id.spn_phuongxaa);
		spnLoaiYeuCau = (Spinner) body.findViewById(R.id.spn_yeucau);
		csOnlineGetLoaiDichVuTask = new GetLoaiDichVuTask();
		csOnlineGetLoaiDichVuTask.input.add(Util.ttp.getMa_Ttp());
		GetQuanHuyenTask getQuanHuyenTask = new GetQuanHuyenTask();
		spnLoaiDichVu.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				LoaiDichVu ldv = (LoaiDichVu) parent.getAdapter().getItem(position);
				loaiYeuCauTask = new GetLoaiYeuCauTask();
				loaiYeuCauTask.input.add(Util.ttp.getMa_Ttp());
				loaiYeuCauTask.input.add(ldv.getID());
				onExecuteToServer(true, null, loaiYeuCauTask);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		spnQuanHuyen.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub

				QuanHuyen quanhuyen = (QuanHuyen) parent.getAdapter().getItem(position);
				phuongxaTask = new GetPhuongXaTask();
				phuongxaTask.input.add(quanhuyen.getID());
				onExecuteToServer(true, null, phuongxaTask);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		onExecuteToServer(true, null, csOnlineGetLoaiDichVuTask, getQuanHuyenTask);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.bttOK:
			tiepnhanDVVTTask = new TiepnhanDVVTTask();
			tiepnhanDVVTTask.input.add(((LoaiDichVu) spnLoaiDichVu.getSelectedItem()).getID());
			tiepnhanDVVTTask.input.add(txtTenkh.getText().toString());
			tiepnhanDVVTTask.input.add(txtDiachilapdat.getText().toString());
			tiepnhanDVVTTask.input.add(txtTenkh.getText().toString());
			tiepnhanDVVTTask.input.add(txtDiachilh.getText().toString());
			tiepnhanDVVTTask.input.add(((PhuongXa) spnPhuongXa.getSelectedItem()).getID());
			tiepnhanDVVTTask.input.add(txtSodtlh.getText().toString());
			tiepnhanDVVTTask.input.add(txtEmail.getText().toString());
			tiepnhanDVVTTask.input.add(txtThoigianlh.getText().toString());
			tiepnhanDVVTTask.input.add(txtNguoigt.getText().toString());
			tiepnhanDVVTTask.input.add("");
			tiepnhanDVVTTask.input.add(txtGhichu.getText().toString());
			tiepnhanDVVTTask.input.add(Util.userName);
			onExecuteToServer(true, null, tiepnhanDVVTTask);
			break;

		default:
			break;
		}
	}

	@Override
	public void onsucces(WebProtocol ws) {
		// TODO Auto-generated method stub
		super.onsucces(ws);
		if (ws.equals(csOnlineGetLoaiDichVuTask)) {
			SoapObject lstResult = (SoapObject) ((SoapObject) csOnlineGetLoaiDichVuTask.result).getProperty("Result");
			lstLoaiDichVu = Util.SetDataToSpinner(context, spnLoaiDichVu, lstResult, LoaiDichVu.class, false, "Ten");
			lstResult = (SoapObject) ((SoapObject) getQuanHuyenTask.result).getProperty("Result");
			lstQuanHuyen = Util.SetDataToSpinner(context, spnQuanHuyen, lstResult, QuanHuyen.class, false, "Ten");

		}
		if (ws.equals(phuongxaTask)) {
			SoapObject lstResult = (SoapObject) ((SoapObject) phuongxaTask.result).getProperty("Result");
			Util.SetDataToSpinner(context, spnPhuongXa, lstResult, PhuongXa.class, false, "Ten");

		}
		if (ws.equals(loaiYeuCauTask)) {
			SoapObject lstResult = (SoapObject) ((SoapObject) loaiYeuCauTask.result).getProperty("Result");
			Util.SetDataToSpinner(context, spnLoaiYeuCau, lstResult, PhuongXa.class, false, "Ten");

		}
		if (ws instanceof TiepnhanDVVTTask) {
			try {
				String mess = ((SoapObject) tiepnhanDVVTTask.result).getPropertyAsString("Message");

				Util.showAlert(context, mess);
			} catch (Exception e) {
				// TODO: handle exception
			}

		}

	}

}
