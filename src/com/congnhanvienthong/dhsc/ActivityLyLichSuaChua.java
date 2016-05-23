package com.congnhanvienthong.dhsc;

import java.util.ArrayList;
import java.util.Vector;

import org.ksoap2.serialization.SoapObject;

import webservice.BaseTask;
import webservice.WebProtocol;
import webservice.dhsc.LyLichSuaChuaTTPTask;
import adapter.BaseListViewAdapter;
import adapter.BaseSpinnerAdapter;
import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.congnhanvienthong.ActivityBaseToDisplay;
import com.congnhanvienthong.R;

import congnhanvienthong.entity.dhsc.LoaiDichVu;
import congnhanvienthong.entity.dhsc.TienTrinhSuaTTP;
import control.Util;

public class ActivityLyLichSuaChua extends ActivityBaseToDisplay {
	Spinner spnLoaiDichVu;
	ArrayList<String> lstTenLoaiDichVu = new ArrayList<String>();
	String id_loaidichvu;
	String maLoaiDichVu, maDichVu;
	EditText txtLoaiDichVu;
	Button bttXem;
	ArrayList<TienTrinhSuaTTP> aTienTrinhSuas = new ArrayList<TienTrinhSuaTTP>();
	ListView listView;
	Dialog dialog;
	LyLichSuaChuaTTPTask lichSuaChuaTask;
	private DisplayMetrics metrics;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		this.context = ActivityLyLichSuaChua.this;
		setBodyLayout(R.layout.activity_ly_lich_sua_chua);
		setFootLayout(R.layout.foot_layout_ly_lich_sua_chua);
		setHeader("Lý lịch sửa chữa");
		listView = (ListView) body.findViewById(R.id.tientrinhsua_listview);

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
				txtLoaiDichVu.setText("");
				listView.setAdapter(null);

			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		txtLoaiDichVu = (EditText) body.findViewById(R.id.txt_ma_dich_vu);
		bttXem = (Button) foot.findViewById(R.id.tra_cuu_ly_lich_sua);
		bttXem.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub\
				listView.setVisibility(View.VISIBLE);
				lichSuaChuaTask = new LyLichSuaChuaTTPTask();
				lichSuaChuaTask.context = ActivityLyLichSuaChua.this;
				maDichVu = txtLoaiDichVu.getText().toString();

				lichSuaChuaTask.input.add(maDichVu.trim());
				lichSuaChuaTask.input.add(id_loaidichvu);
				lichSuaChuaTask.input.add(Util.userName.trim());
				lichSuaChuaTask.input.add(Util.ttp.getId_ttpho().trim());
				// Task task = new Task();
				lichSuaChuaTask.input.add("1");
				// task.execute(lichSuaChuaTask);
				onExecuteToServer(true, "Tra cứu lý lịch sửa chữa", lichSuaChuaTask);

			}
		});

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
			Vector<Object> temp = (Vector<Object>) lichSuaChuaTask.result;
			SoapObject lstTienTrinhSua = (SoapObject) temp.get(0);
			int length = lstTienTrinhSua.getPropertyCount();
			// khi load mới thì remove toàn bộ dữ liệu cũ đi
			while (aTienTrinhSuas.size() > 0) {
				aTienTrinhSuas.remove(0);

			}
			// chạy vòng lặp, tạo dữ liệu và đổ vào list để sử dụng
			for (int i = 0; i < length; i++) {
				SoapObject tienTrienSuaObj = (SoapObject) lstTienTrinhSua.getProperty(i);

				TienTrinhSuaTTP tempTienTrinh = new TienTrinhSuaTTP();
				Util.GetObjectFromSoapObject(tempTienTrinh, tienTrienSuaObj);
				aTienTrinhSuas.add(tempTienTrinh);
			}
			listView.setAdapter(new BaseListViewAdapter<TienTrinhSuaTTP>(context, aTienTrinhSuas, metrics, false));

			// Util.setListViewHeightBasedOnChildren(listView);
			// ấn vào item để ẩn hiện thông tin

			if (aTienTrinhSuas.size() == 0) {
				Util.showAlert(context, "Không tìm thấy lí lịch sửa gần nhất");

			}

		}
	}

}
