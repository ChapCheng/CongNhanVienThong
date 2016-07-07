package com.congnhanvienthong.dhsc;

import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import com.congnhanvienthong.ActivityBaseToDisplay;
import com.congnhanvienthong.R;
import com.congnhanvienthong.qltt.Utilities;

import adapter.AutoTextAdapter;
import adapter.BaseGroupListViewAdapter;
import adapter.BaseSpinnerAdapter;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import congnhanvienthong.entity.dhsc.LoaiDichVu;
import congnhanvienthong.entity.dhsc.ThongTinDichVu;
import congnhanvienthong.entity.dhsc.ThongTinVissaFiberVNN;
import congnhanvienthong.entity.dhsc.ThongTinVissaMyTV;
import congnhanvienthong.entity.dhsc.TienTrinhSuaTTP;
import congnhanvienthong.entity.gtacs.ThongTinLyLichMay;
import congnhanvienthong.entity.tracuuchung.ThongTinTraCuuChung;
import control.Util;
import webservice.WebProtocol;
import webservice.dhsc.LyLichSuaChuaTTPTask;
import webservice.dhsc.TraCuuTTPTask;
import webservice.dhsc.TraThongTinVissaTask;
import webservice.gtcas.LyLichMayTask;
import webservice.tracuuchung.TraCuuNhanhTask;

public class ActivityTraCuuDV extends ActivityBaseToDisplay {
	Button bttTim, bttOK;
	AutoCompleteTextView edtThongTinDichVu;
	TraCuuNhanhTask tracuunhanhTask;
	WebProtocol runTask;
	LyLichMayTask llmTask;
	TraCuuTTPTask traCuuTtpTask;
	Spinner spnLoaiTraCuu;
	ThongTinTraCuuChung thongtin;
	Spinner spnLoaiDichVu;
	TraThongTinVissaTask TraThongTinVissaTask;
	TextView txtKetQua;
	ExpandableListView listView;
	LyLichSuaChuaTTPTask lichSuaChuaTask;
	ArrayList<TienTrinhSuaTTP> aTienTrinhSuas = new ArrayList<TienTrinhSuaTTP>();
	LinearLayout viewText ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setBodyLayout(R.layout.activity_trathongtindv);
		setHeader("Tra thông tin dịch vu");
		tracuunhanhTask = new TraCuuNhanhTask();
		txtKetQua = (TextView) body.findViewById(R.id.ketqua);
		listView = (ExpandableListView) body.findViewById(R.id.tientrinhsua_listview);
		viewText = (LinearLayout) body.findViewById(R.id.viewText);
		spnLoaiTraCuu = (Spinner) body.findViewById(R.id.spnNghiepVu);
		setFootLayout(R.layout.foot_tracuu);
		spnLoaiDichVu = (Spinner) body.findViewById(R.id.loaiDichVu);
		ArrayList<LoaiDichVu> lstLoaiDichVU = (ArrayList<LoaiDichVu>) Util.listLoaiDichVu;
		BaseSpinnerAdapter<LoaiDichVu> adapterLoaiDichVu = new BaseSpinnerAdapter<LoaiDichVu>(context,
				android.R.layout.simple_spinner_dropdown_item, lstLoaiDichVU);
		adapterLoaiDichVu.name = "TenLoaiDichVu";
		spnLoaiDichVu.setAdapter(adapterLoaiDichVu);
		bttOK = (Button) foot.findViewById(R.id.bttOK);
		bttOK.setOnClickListener(this);
		bttTim = (Button) body.findViewById(R.id.btn_tim);
		edtThongTinDichVu = (AutoCompleteTextView) body.findViewById(R.id.txt_search);
		bttTim.setOnClickListener(this);
		spnLoaiTraCuu.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				if (position == 3) {
					viewText.setVisibility(View.GONE);
					listView.setVisibility(View.VISIBLE);
				} else {
					viewText.setVisibility(View.VISIBLE);
					listView.setVisibility(View.GONE);
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		edtThongTinDichVu.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				ThongTinTraCuuChung thongtin = (ThongTinTraCuuChung) parent.getAdapter().getItem(position);
				if (spnLoaiTraCuu.getSelectedItemPosition() == 2)
					edtThongTinDichVu.setText(thongtin.getAccount().trim());
				else
					edtThongTinDichVu.setText(thongtin.getMaDichVu().trim());
				for (int i = 0; i < Util.listLoaiDichVu.size(); i++) {
					if (thongtin.getIdLoaiDichVuDHSC() == Util.listLoaiDichVu.get(i).getIdLoaiDichvu())
						spnLoaiDichVu.setSelection(i);
				}

			}
		});

	}

	@SuppressWarnings("unchecked")
	@Override
	public void onsucces(WebProtocol task) {
		// TODO Auto-generated method stub
		super.onsucces(task);
		if (task.equals(tracuunhanhTask)) {
			ArrayList<ThongTinTraCuuChung> lst = (ArrayList<ThongTinTraCuuChung>) tracuunhanhTask.getResult();

			AutoTextAdapter<ThongTinTraCuuChung> adapter = new AutoTextAdapter<ThongTinTraCuuChung>(context,
					android.R.layout.simple_list_item_1, lst);

			edtThongTinDichVu.setAdapter(adapter);
			edtThongTinDichVu.showDropDown();

		}
		if (task.equals(llmTask)) {
			ThongTinLyLichMay tt = (ThongTinLyLichMay) llmTask.getResult();
			System.out.println(tt);
			txtKetQua.setMovementMethod(new ScrollingMovementMethod());
			Util.setTextFromObject(txtKetQua, tt);

		}
		if (task.equals(traCuuTtpTask)) {
			ThongTinDichVu tt = (ThongTinDichVu) traCuuTtpTask.getResult();
			txtKetQua.setMovementMethod(new ScrollingMovementMethod());
			Util.setTextFromObject(txtKetQua, tt);
		}
		if (task.equals(TraThongTinVissaTask)) {
			Object soap = TraThongTinVissaTask.getResult();
			if (soap instanceof ThongTinVissaFiberVNN) {
				ThongTinVissaFiberVNN tt = (ThongTinVissaFiberVNN) soap;
				txtKetQua.setMovementMethod(new ScrollingMovementMethod());
				Util.setTextFromObject(txtKetQua, tt);
			}
			if (soap instanceof ThongTinVissaMyTV) {
				ThongTinVissaMyTV tt = (ThongTinVissaMyTV) soap;
				txtKetQua.setMovementMethod(new ScrollingMovementMethod());
				Util.setTextFromObject(txtKetQua, tt);
			}

		}
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
		case R.id.btn_tim:
			tracuunhanhTask.addParam("SearchText", edtThongTinDichVu.getText().toString().trim());
			tracuunhanhTask.addParam("MaTinhTp", Util.ttp.getMa_Ttp());
			onExecuteToServer(true, null, tracuunhanhTask);
			break;

		case R.id.bttOK:
			int pos = spnLoaiTraCuu.getSelectedItemPosition();
			LoaiDichVu ldv = (LoaiDichVu) spnLoaiDichVu.getSelectedItem();
			txtKetQua.setText("");
			listView.setAdapter(
					new BaseGroupListViewAdapter<TienTrinhSuaTTP>(context, new ArrayList<TienTrinhSuaTTP>(), "", ""));
			switch (pos) {
			case 0:
				llmTask = new LyLichMayTask();
				llmTask.addParam("ma_dich_vu", edtThongTinDichVu.getText().toString());
				llmTask.addParam("ma_tinh_thanh", Util.ttp.getMa_Ttp());
				onExecuteToServer(true, "Tra lý lịch máy", llmTask);
				break;
			case 1:

				traCuuTtpTask = new TraCuuTTPTask();
				traCuuTtpTask.addParam("maDichVu", edtThongTinDichVu.getText().toString().trim());
				traCuuTtpTask.addParam("loaiDichVuId", ldv.getIdLoaiDichvu());
				traCuuTtpTask.addParam("userName", Util.userName);
				traCuuTtpTask.addParam("tinhThanhPhoId", Util.ttp.getId_ttpho());
				if (Util.ttp.getId_ttpho().equals("1")) {
					traCuuTtpTask.removeParam("userName");
					traCuuTtpTask.removeParam("tinhThanhPhoId");
				}
				onExecuteToServer(true, null, traCuuTtpTask);
				break;
			case 2:
				TraThongTinVissaTask = new TraThongTinVissaTask(ldv.getIdLoaiDichvu());
				if (ldv.getIdLoaiDichvu() == 80) {
					TraThongTinVissaTask.addParam("AccountMyTV", edtThongTinDichVu.getText().toString().trim());
					TraThongTinVissaTask.addParam("ma_tinh", Util.ttp.getMa_Ttp());
				} else if (ldv.getIdLoaiDichvu() == 50 || ldv.getIdLoaiDichvu() == 20) {
					TraThongTinVissaTask.addParam("accountName", edtThongTinDichVu.getText().toString().trim());
					TraThongTinVissaTask.addParam("maTTP", Util.ttp.getMa_Ttp());
				} else {
					Toast.makeText(context, "Khong ho tro loai dv nay", Toast.LENGTH_SHORT).show();
				}
				onExecuteToServer(true, null, TraThongTinVissaTask);
				break;
			case 3:

				lichSuaChuaTask = new LyLichSuaChuaTTPTask();
				lichSuaChuaTask.context = ActivityTraCuuDV.this;
				lichSuaChuaTask.addParam("maDichVu", edtThongTinDichVu.getText().toString().trim());
				lichSuaChuaTask.addParam("loaiDichVuId", ldv.getIdLoaiDichvu());
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
			break;
		default:
			break;
		}
	}

}
