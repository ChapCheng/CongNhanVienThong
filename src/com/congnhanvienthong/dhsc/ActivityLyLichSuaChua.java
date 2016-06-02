package com.congnhanvienthong.dhsc;

import java.util.ArrayList;

import com.congnhanvienthong.ActivityBaseToDisplay;
import com.congnhanvienthong.R;

import adapter.BaseGroupListViewAdapter;
import adapter.BaseSpinnerAdapter;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import congnhanvienthong.entity.dhsc.LoaiDichVu;
import congnhanvienthong.entity.dhsc.TienTrinhSuaTTP;
import control.Util;
import webservice.WebProtocol;
import webservice.dhsc.LyLichSuaChuaTTPTask;

public class ActivityLyLichSuaChua extends ActivityBaseToDisplay {
	Spinner spnLoaiDichVu;
	ArrayList<String> lstTenLoaiDichVu = new ArrayList<String>();
	String id_loaidichvu;
	String maLoaiDichVu, maDichVu;
	EditText txtLoaiDichVu;
	Button bttXem;
	ArrayList<TienTrinhSuaTTP> aTienTrinhSuas = new ArrayList<TienTrinhSuaTTP>();
	ExpandableListView listView;
	Dialog dialog;
	LyLichSuaChuaTTPTask lichSuaChuaTask;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.context = ActivityLyLichSuaChua.this;
		setBodyLayout(R.layout.activity_ly_lich_sua_chua);
		setFootLayout(R.layout.foot_layout_ly_lich_sua_chua);
		setHeader("Lý lịch sửa chữa");
		listView = (ExpandableListView) body.findViewById(R.id.tientrinhsua_listview);

		spnLoaiDichVu = (Spinner) body.findViewById(R.id.spnLoaiDichVu);
		for (LoaiDichVu loaiDichVu : Util.listLoaiDichVu) {
			lstTenLoaiDichVu.add(loaiDichVu.getTenLoaiDichVu());
		}
		BaseSpinnerAdapter<LoaiDichVu> arrayAdapter = new BaseSpinnerAdapter<LoaiDichVu>(context,
				android.R.layout.simple_spinner_dropdown_item, (ArrayList) Util.listLoaiDichVu);
		arrayAdapter.name = "TenLoaiDichVu";
		// arrayAdapter
		// .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnLoaiDichVu.setAdapter(arrayAdapter);
		spnLoaiDichVu.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				id_loaidichvu = String.valueOf(Util.listLoaiDichVu.get(arg2).getIdLoaiDichvu());

			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		txtLoaiDichVu = (EditText) body.findViewById(R.id.txt_ma_dich_vu);
		bttXem = (Button) foot.findViewById(R.id.tra_cuu_ly_lich_sua);
		bttXem.setOnClickListener(this);

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		// super.onBackPressed();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onsucces(WebProtocol task) {
		// TODO Auto-generated method stub
		super.onsucces(task);
		if (task.equals(lichSuaChuaTask)) {
			while (aTienTrinhSuas.size() > 0) {
				aTienTrinhSuas.remove(0);

			}
			aTienTrinhSuas = (ArrayList<TienTrinhSuaTTP>) lichSuaChuaTask.getResult();
			listView.setAdapter(new BaseGroupListViewAdapter<TienTrinhSuaTTP>(context, aTienTrinhSuas, "NgayNhap",
					"Lần báo hỏng "));
			if (aTienTrinhSuas.size() == 0) {
				Util.showAlert(context, "Không tìm thấy lí lịch sửa gần nhất");

			}

		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.tra_cuu_ly_lich_sua:

			listView.setAdapter(
					new BaseGroupListViewAdapter<TienTrinhSuaTTP>(context, new ArrayList<TienTrinhSuaTTP>(), "", ""));

			listView.setVisibility(View.VISIBLE);
			lichSuaChuaTask = new LyLichSuaChuaTTPTask();
			lichSuaChuaTask.context = ActivityLyLichSuaChua.this;
			maDichVu = txtLoaiDichVu.getText().toString();
			lichSuaChuaTask.addParam("maDichVu", maDichVu.trim());
			lichSuaChuaTask.addParam("loaiDichVuId", id_loaidichvu);
			lichSuaChuaTask.addParam("userName", Util.userName.trim());
			lichSuaChuaTask.addParam("tinhThanhPhoId", Util.ttp.getId_ttpho().trim());
			if (Util.ttp.getId_ttpho().equals("1")) {
				lichSuaChuaTask.addParam("pageIndex", 0);
				lichSuaChuaTask.addParam("pageSize", 20);
			}
			onExecuteToServer(true, "Tra cứu lý lịch sửa chữa", lichSuaChuaTask);

			break;

		default:
			break;
		}
	}
}
