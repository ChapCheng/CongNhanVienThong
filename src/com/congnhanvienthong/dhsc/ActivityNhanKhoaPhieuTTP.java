package com.congnhanvienthong.dhsc;

import java.util.ArrayList;
import java.util.Vector;

import org.ksoap2.serialization.SoapObject;

import webservice.BaseTask;
import webservice.WebProtocol;
import webservice.dhsc.GetLoaiNghiemThuTask;
import webservice.dhsc.GetLoaiSuaTask;
import webservice.dhsc.GetLyDoTonTask;
import webservice.dhsc.LayListPhieuTTP;
import adapter.BaseListViewAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.congnhanvienthong.ActivityBaseToDisplay;
import com.congnhanvienthong.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import congnhanvienthong.entity.dhsc.LoaiDichVu;
import congnhanvienthong.entity.dhsc.LoaiNghiemThu;
import congnhanvienthong.entity.dhsc.LoaiSua;
import congnhanvienthong.entity.dhsc.LyDoTon;
import congnhanvienthong.entity.dhsc.ThongTinBaoHong;
import control.Util;

public class ActivityNhanKhoaPhieuTTP extends ActivityBaseToDisplay {
	LayListPhieuTTP layListPhieuTTP;
	TextView totalInfor;
	PullToRefreshListView listView;
	ArrayList<ThongTinBaoHong> lstTHThongTinBaoHongs = new ArrayList<ThongTinBaoHong>();
	ThongTinBaoHong thongTinBaoHong;
	ArrayList<LyDoTon> lstTon = new ArrayList<LyDoTon>();
	int index = 0;
	RadioGroup radiogroup;
	int type = 0;
	// GetDataPhieuDHSCTask getDataPhieuDHSCTask;
	ArrayList<LoaiSua> lstLoaiSua = new ArrayList<LoaiSua>();
	ArrayList<LoaiNghiemThu> lstLoaiNghiemThu = new ArrayList<LoaiNghiemThu>();
	public static int sophieulay = 10;
	GetLoaiSuaTask getLoaiSuaTask;
	GetLoaiNghiemThuTask getLoaiNghiemThuTask;
	GetLyDoTonTask getLyDoTonTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if (Util.ttp != null) {

			context = ActivityNhanKhoaPhieuTTP.this;
			setBodyLayout(R.layout.activity_nhan_khoa_phieu);
			setFootLayout(R.layout.foot_nhan_phieu);
			setHeader("Nhận khoá phiếu");
			radiogroup = (RadioGroup) foot.findViewById(R.id.RadioGroup01);

			radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

				public void onCheckedChanged(RadioGroup group, int checkedId) {
					// TODO Auto-generated method stub
					Task task = new Task();
					layListPhieuTTP = new LayListPhieuTTP();
					layListPhieuTTP.input.add(Util.userName);
					layListPhieuTTP.input.add(checkedId);
					type = checkedId;
					layListPhieuTTP.input.add("0");
					layListPhieuTTP.input.add(sophieulay);
					layListPhieuTTP.input.add(Util.ttp.getId_ttpho());
					while (lstTHThongTinBaoHongs.size() > 0) {
						lstTHThongTinBaoHongs.remove(0);

					}
					task.execute(layListPhieuTTP);

				}
			});
			DisplayRadioButton();
			layListPhieuTTP = new LayListPhieuTTP();

			layListPhieuTTP.input.add(Util.userName);
			layListPhieuTTP.input.add(type);
			layListPhieuTTP.input.add("0");
			layListPhieuTTP.input.add(sophieulay);
			layListPhieuTTP.input.add(Util.ttp.getId_ttpho());
			setHeader("Khoá phiếu");
			Task task = new Task();
			task.execute(layListPhieuTTP);

		} else {
			finish();
			System.exit(0);

		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public void onsucces(WebProtocol task) {
		// TODO Auto-generated method stub
		super.onsucces(task);
		if (task.equals(layListPhieuTTP)) {
			int total = 0;
			try {
				lstTHThongTinBaoHongs = (ArrayList<ThongTinBaoHong>) layListPhieuTTP.getResult();
				total = lstTHThongTinBaoHongs.get(0).getpTotalRecord();
			} catch (Exception e) {
				e.printStackTrace();
			}
			listView = (PullToRefreshListView) body.findViewById(R.id.list_nhan_khoa_phieu);
			totalInfor = (TextView) body.findViewById(R.id.so_phieu_hong);
			listView.setMode(Mode.BOTH);
			listView.onRefreshComplete();
			listView.setAdapter(new BaseListViewAdapter<ThongTinBaoHong>(context, lstTHThongTinBaoHongs, null, false));
			totalInfor.setText("Có tổng số " + total + " phiếu. ");

			listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {

				@Override
				public void onRefresh(PullToRefreshBase<ListView> refreshView) {
					// TODO Auto-generated method stub
					listView.setAdapter(null);
					if (listView.getCurrentMode().equals(PullToRefreshBase.Mode.PULL_FROM_START)
							&& lstTHThongTinBaoHongs.size() > sophieulay - 1)
						index = index + sophieulay;
					if (listView.getCurrentMode().equals(PullToRefreshBase.Mode.PULL_FROM_END)
							&& index > sophieulay - 1)
						index = index - sophieulay;
					// refreshView.setShowViewWhileRefreshing(false);
					layListPhieuTTP = new LayListPhieuTTP();
					layListPhieuTTP.input.add(Util.userName);
					layListPhieuTTP.input.add(type);
					layListPhieuTTP.input.add(index);
					layListPhieuTTP.input.add(sophieulay);
					layListPhieuTTP.input.add(Util.ttp.getId_ttpho());
					onExecuteToServer(false, null, layListPhieuTTP);

				}
			});
			listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					// TODO Auto-generated method stub
					thongTinBaoHong = (ThongTinBaoHong) arg0.getItemAtPosition(arg2);

					// bundle.put
					Task task = new Task();
					getLoaiSuaTask = new GetLoaiSuaTask();
					getLoaiSuaTask.input.add(thongTinBaoHong.getID_LoaiDichVu());
					getLoaiSuaTask.input.add(thongTinBaoHong.getID_NhomSua());
					getLoaiSuaTask.input.add(Util.ttp.getId_ttpho());

					getLyDoTonTask = new GetLyDoTonTask();
					getLyDoTonTask.input.add(thongTinBaoHong.getID_LoaiDichVu());
					getLyDoTonTask.input.add(thongTinBaoHong.getID_NhomTon());
					getLyDoTonTask.input.add(Util.ttp.getId_ttpho());

					getLoaiNghiemThuTask = new GetLoaiNghiemThuTask();
					getLoaiNghiemThuTask.input.add(thongTinBaoHong.getID_LoaiDichVu());
					getLoaiNghiemThuTask.input.add(Util.ttp.getId_ttpho());
					task.execute(getLoaiSuaTask, getLyDoTonTask, getLoaiNghiemThuTask);

				}
			});

		}
		if (task.equals(getLoaiSuaTask)) {
			while (lstLoaiSua.size() > 0) {
				lstLoaiSua.remove(0);
			}
			try {
				lstLoaiSua = (ArrayList<LoaiSua>) getLoaiSuaTask.getResult();
				while (lstLoaiNghiemThu.size() > 0) {
					lstLoaiNghiemThu.remove(0);
				}

				lstLoaiNghiemThu = (ArrayList<LoaiNghiemThu>) getLoaiNghiemThuTask.getResult();
				while (lstTon.size() > 0) {
					lstTon.remove(0);
				}

				lstTon = (ArrayList<LyDoTon>) getLyDoTonTask.getResult();

				Intent intent = new Intent(ActivityNhanKhoaPhieuTTP.this, ActivityChiTietPhieuTTP.class);
				intent.putExtra("phieu", thongTinBaoHong);
				intent.putExtra("loaisua", lstLoaiSua);
				intent.putExtra("loainghiemthu", lstLoaiNghiemThu);
				intent.putExtra("ton", lstTon);
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

			} catch (Exception exception) {
				exception.printStackTrace();
			}

		}
	}

	public void DisplayRadioButton() {
		try {
			RadioButton tatCa = new RadioButton(this);
			tatCa.setId(0);
			tatCa.setText("Tất cả");

			radiogroup.addView(tatCa);
			for (LoaiDichVu loaiDichVu : Util.listLoaiDichVu) {
				RadioButton rdbtn = new RadioButton(this);
				rdbtn.setId(loaiDichVu.getIdLoaiDichvu());
				rdbtn.setText(loaiDichVu.getTenLoaiDichVu());
				radiogroup.addView(rdbtn);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
	}
}
