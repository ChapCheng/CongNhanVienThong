package com.congnhanvienthong.kdb;

import java.util.ArrayList;

import com.congnhanvienthong.ActivityBaseToDisplay;
import com.congnhanvienthong.R;

import adapter.BaseGroupListViewAdapter;
import adapter.BaseSpinnerAdapter;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Gallery;
import android.widget.Spinner;
import android.widget.TextView;
import congnhanvienthong.entity.kdb.ChiTieuBSC;
import congnhanvienthong.entity.kdb.DiaBan;
import congnhanvienthong.entity.kdb.DoiVienThong;
import congnhanvienthong.entity.kdb.TrungTamVienThong;
import control.Util;
import webservice.WebProtocol;
import webservice.kdb.LayDiaBanTask;
import webservice.kdb.LayDoivienThongTask;
import webservice.kdb.LayDuLieuBaoCaoBscTask;
import webservice.kdb.LayTrungTamVienThongTask;

public class ActivityKhoanDiaBan extends ActivityBaseToDisplay {
	ExpandableListView lstChiTieuBSC;
	LayTrungTamVienThongTask layTrungTamTask;
	LayDoivienThongTask layDoiVienThongTask;
	LayDuLieuBaoCaoBscTask baoCaoBscTask;
	LayDiaBanTask layDiaBanTask;
	Spinner spnTrungTam, spnDoiVienThong, spnDiaBan;
	EditText txtNam, txtTuThang, txtDenThang;
	Button bttOK;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setBodyLayout(R.layout.kdb_activity);
		setFootLayout(R.layout.foot_tracuu);
		setHeader("Khoán địa bàn");
		spnTrungTam = (Spinner) body.findViewById(R.id.spnTrungTamVienThong);
		spnDiaBan = (Spinner) body.findViewById(R.id.spnDiaBan);
		lstChiTieuBSC = (ExpandableListView) body.findViewById(R.id.lstChiTieuBSC);
		spnDoiVienThong = (Spinner) body.findViewById(R.id.spnDoiVienThong);
		layDiaBanTask = new LayDiaBanTask();
		layDiaBanTask.addParam("maTinhTp", Util.ttp.getMa_Ttp());
		txtDenThang = (EditText) body.findViewById(R.id.txtDenThang);
		txtTuThang = (EditText) body.findViewById(R.id.txtTuThang);
		txtNam = (EditText) body.findViewById(R.id.txtNam);
		bttOK = (Button) foot.findViewById(R.id.bttOK);
		bttOK.setOnClickListener(this);
		baoCaoBscTask = new LayDuLieuBaoCaoBscTask();
		layTrungTamTask = new LayTrungTamVienThongTask();
		layDoiVienThongTask = new LayDoivienThongTask();
		layDoiVienThongTask.addParam("maTinhTp", Util.ttp.getMa_Ttp());
		spnTrungTam.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				TrungTamVienThong trungTamVienThong = (TrungTamVienThong) parent.getAdapter().getItem(position);
				layDoiVienThongTask.addParam("maTrungTamVt", trungTamVienThong.getMaDonVi());
				onExecuteToServer(true, null, layDoiVienThongTask);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		spnDoiVienThong.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				DoiVienThong doivt = (DoiVienThong) parent.getAdapter().getItem(position);
				layDiaBanTask.addParam("maDoiVt", doivt.getMaDonVi());
				TrungTamVienThong trungtam = (TrungTamVienThong) spnTrungTam.getSelectedItem();
				layDiaBanTask.addParam("maTrungTamVt", trungtam.getMaDonVi());
				onExecuteToServer(true, null, layDiaBanTask);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		spnDiaBan.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				lstChiTieuBSC.setAdapter(
						new BaseGroupListViewAdapter<ChiTieuBSC>(context, new ArrayList<ChiTieuBSC>(), "", ""));

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		layTrungTamTask.addParam("maTinhTp", Util.ttp.getMa_Ttp());
		onExecuteToServer(true, null, layTrungTamTask);
	};

	@SuppressWarnings("unchecked")
	@Override
	public void onsucces(WebProtocol task) {
		// TODO Auto-generated method stub
		super.onsucces(task);
		if (task.equals(baoCaoBscTask)) {
			ArrayList<ChiTieuBSC> lstChiTieu = (ArrayList<ChiTieuBSC>) task.getResult();
			BaseGroupListViewAdapter<ChiTieuBSC> adapter = new BaseGroupListViewAdapter<ChiTieuBSC>(context, lstChiTieu,
					"TenChiTieuCha", "KPO");
			lstChiTieuBSC.setAdapter(adapter);
			for (int i = 0; i < adapter.getGroupCount(); i++)
				lstChiTieuBSC.expandGroup(i);

		}

		if (task.equals(layTrungTamTask)) {
			ArrayList<TrungTamVienThong> lstTrungTam = (ArrayList<TrungTamVienThong>) layTrungTamTask.getResult();
			BaseSpinnerAdapter<TrungTamVienThong> adapter = new BaseSpinnerAdapter<TrungTamVienThong>(context,
					lstTrungTam);
			adapter.name = "TenDonVi";
			spnTrungTam.setAdapter(adapter);
		}
		if (task.equals(layDoiVienThongTask)) {
			ArrayList<DoiVienThong> lstDoiVienThong = (ArrayList<DoiVienThong>) layDoiVienThongTask.getResult();
			BaseSpinnerAdapter<DoiVienThong> adapter = new BaseSpinnerAdapter<DoiVienThong>(context, lstDoiVienThong);
			adapter.name = "TenDonVi";
			spnDoiVienThong.setAdapter(adapter);
		}
		if (task.equals(layDiaBanTask)) {
			ArrayList<DiaBan> lstDiaBan = (ArrayList<DiaBan>) layDiaBanTask.getResult();
			BaseSpinnerAdapter<DiaBan> adapter = new BaseSpinnerAdapter<DiaBan>(context, lstDiaBan);
			adapter.name = "TenDonVi";
			spnDiaBan.setAdapter(adapter);

		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.bttOK:
			DoiVienThong doi = (DoiVienThong) spnDoiVienThong.getSelectedItem();
			TrungTamVienThong trungTamVienThong = (TrungTamVienThong) spnTrungTam.getSelectedItem();
			DiaBan diaban = (DiaBan) spnDiaBan.getSelectedItem();
			lstChiTieuBSC
					.setAdapter(new BaseGroupListViewAdapter<ChiTieuBSC>(context, new ArrayList<ChiTieuBSC>(), "", ""));
			if (Validate()) {
				baoCaoBscTask.addParam("chiTieuChaId", 0);
				baoCaoBscTask.addParam("nam", txtNam.getText().toString());
				baoCaoBscTask.addParam("tuThang", txtTuThang.getText().toString());
				baoCaoBscTask.addParam("denThang", txtDenThang.getText().toString());
				baoCaoBscTask.addParam("tinhTpId", trungTamVienThong.getTinhTpId());
				baoCaoBscTask.addParam("ttvtId", trungTamVienThong.getDonViId());
				baoCaoBscTask.addParam("doiVtId", doi.getDonViId());
				baoCaoBscTask.addParam("diaBanId", diaban.getDonViId());
				onExecuteToServer(true, null, baoCaoBscTask);
			}
			break;

		default:
			break;
		}
	}

	public boolean Validate() {
		boolean flag = true;

		if (txtDenThang.getText().toString().length() == 0) {
			txtDenThang.setError("Nhập tháng");
			flag = false;
		}
		if (txtTuThang.getText().toString().length() == 0) {
			txtTuThang.setError("Nhập tháng");
			flag = false;
		}
		if (txtNam.getText().toString().length() == 0) {
			txtNam.setError("Nhập năm");
			flag = false;
		}
		return flag;
	}

}
