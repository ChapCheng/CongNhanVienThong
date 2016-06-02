package com.congnhanvienthong.pttb;

import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import webservice.BaseTask;
import webservice.WebProtocol;
import webservice.pttb.GetQuyenCapNhatTask;
import webservice.pttb.LayDanhSachMaThiCongTask;
import webservice.pttb.LayDichVuVienThong_PTTBTask;
import adapter.BaseListViewAdapter;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.congnhanvienthong.ActivityBaseToDisplay;
import com.congnhanvienthong.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import congnhanvienthong.entity.pttb.Role;
import congnhanvienthong.entity.pttb.ThongTinDichVu;
import congnhanvienthong.entity.pttb.ThongTinThueBao;
import control.Util;

public class ActivityLoginHoanCongPTTB extends ActivityBaseToDisplay {
	ProgressDialog progressDialog;
	public static String sLoai_dichvu_pttb, sMa_tc_pttb;
	Button bttTiep, bttKetThuc;
	Spinner spinner;
	TextView loaidichvu;
	EditText ma_tc;
	LayDichVuVienThong_PTTBTask layDichVuVienThong_PTTBTask;
	LayDanhSachMaThiCongTask layDanhSachMaThiCongTask;
	ArrayList<ThongTinDichVu> arrayListThongTinDichVu = new ArrayList<ThongTinDichVu>();
	ArrayList<ThongTinThueBao> arrayListThongTinThueBao = new ArrayList<ThongTinThueBao>();
	ThongTinThueBao thongTinThueBao;
	RadioGroup radiogroup;
	PullToRefreshListView listView;
	TextView thongbao;
	ArrayList<String> maADSL = new ArrayList<String>();
	boolean back = false;
	private DisplayMetrics metrics;
	GetQuyenCapNhatTask getQuyenCapNhatTask;
	Spinner spnSelectRole;
	Button btnOK;

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		// super.onBackPressed();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		setHeader("Hoàn công các dịch vụ");
		this.context = ActivityLoginHoanCongPTTB.this;
		getQuyenCapNhatTask = new GetQuyenCapNhatTask();
		getQuyenCapNhatTask.addParam("sMA_TINHTP", Util.ttp.getMa_Ttp());
		getQuyenCapNhatTask.addParam("sUSER_XTTT", Util.userName);
		onExecuteToServer(true, null, getQuyenCapNhatTask);
		layDichVuVienThong_PTTBTask = new LayDichVuVienThong_PTTBTask();

	}

	@Override
	public void onsucces(WebProtocol ws) {
		// TODO Auto-generated method stub
		super.onsucces(ws);
		if (ws.equals(getQuyenCapNhatTask)) {
			SoapObject soapObjet = (SoapObject) ((SoapObject) getQuyenCapNhatTask.result).getProperty("Result");
			setBodyLayout(R.layout.select_role_pttb);
			spnSelectRole = (Spinner) body.findViewById(R.id.spnQuyenThaoTac);
			Util.SetDataToSpinner(context, spnSelectRole, soapObjet, Role.class, false, "TEN_NGUOI_DUNG");
			setFootLayout(R.layout.foot_tracuu);
			btnOK = (Button) foot.findViewById(R.id.bttOK);
			btnOK.setOnClickListener(this);

		}
		if (ws.equals(layDanhSachMaThiCongTask)) {
			try {
				setBodyLayout(R.layout.activity_login_pttb_all);
				SoapObject ttThueBao = (SoapObject) ((SoapObject) layDanhSachMaThiCongTask.result)
						.getProperty("Result");
				int length = ttThueBao.getPropertyCount();
				thongbao = (TextView) body.findViewById(R.id.txt_thongbao);
				thongbao.setText("Bạn có " + length + "dịch vụ cần hoàn công. ");
				while (arrayListThongTinThueBao.size() > 0) {
					arrayListThongTinThueBao.remove(0);

				}
				if (length > 0) {

					for (int i = 0; i < length; i++) {
						SoapObject dichvu = (SoapObject) ttThueBao.getProperty(i);
						ThongTinThueBao tempThongTinDichVu = new ThongTinThueBao();
						Util.GetObjectFromSoapObject(tempThongTinDichVu, dichvu);
						arrayListThongTinThueBao.add(tempThongTinDichVu);
						maADSL.add(dichvu.getPrimitivePropertyAsString("SO_ADSL_MEN"));

					}
				}
				BaseListViewAdapter<ThongTinThueBao> adapter = new BaseListViewAdapter<ThongTinThueBao>(context,
						arrayListThongTinThueBao, metrics, false);
				listView = (PullToRefreshListView) body.findViewById(R.id.list_hoancong_pttb);
				listView.setAdapter(null);
				listView.setAdapter(adapter);
				listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {

					@Override
					public void onRefresh(PullToRefreshBase<ListView> refreshView) {
						// TODO Auto-generated method stub
						Task task = new Task();
						listView.setAdapter(null);
						// refreshView.setShowViewWhileRefreshing(false);
						layDanhSachMaThiCongTask = new LayDanhSachMaThiCongTask();
						Role role = (Role) spnSelectRole.getSelectedItem();
						layDanhSachMaThiCongTask.addParam("sMA_TINHTP", Util.ttp.getMa_Ttp());
						layDanhSachMaThiCongTask.addParam("sDICHVUVT_ID",
								String.valueOf(radiogroup.getCheckedRadioButtonId()));
						layDanhSachMaThiCongTask.addParam("sMA_QUYEN_CD", role.getMA_NGUOI_DUNG());
						showDialog = false;
						thongbao.setText("Loading............");
						task.execute(layDanhSachMaThiCongTask);

					}
				});
				listView.onRefreshComplete();
				listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
						// TODO Auto-generated method stub
						thongTinThueBao = (ThongTinThueBao) arg0.getItemAtPosition(arg2);
						Intent intent = new Intent(ActivityLoginHoanCongPTTB.this, ActivityHoanCongPTTB.class);
						intent.putExtra("thongTinThueBao", thongTinThueBao);
						// intent.putExtra("maADSL", maADSL.get(arg2-1));
						startActivity(intent);
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
						back = true;

					}
				});
			} catch (Exception e) {
				// TODO: handle exception
				// Util.showAlert(context, "Lỗi! Vui lòng thử lạihoặc liên hệ
				// người phát triển.");
			}
		}
		if (ws.equals(layDichVuVienThong_PTTBTask)) {
			try {
				SoapObject ds_dichvu = (SoapObject) layDichVuVienThong_PTTBTask.result;
				int length = ds_dichvu.getPropertyCount();
				for (int i = 0; i < length; i++) {
					SoapObject dichvu = (SoapObject) ds_dichvu.getProperty(i);
					ThongTinDichVu tempThongTinDichVu = new ThongTinDichVu();
					Util.GetObjectFromSoapObject(tempThongTinDichVu, dichvu);
					arrayListThongTinDichVu.add(tempThongTinDichVu);

				}
				// setBodyLayout(R.layout.activity_login_pttb_all);
				setFootLayout(R.layout.foot_nhan_phieu);
				radiogroup = (RadioGroup) foot.findViewById(R.id.RadioGroup01);
				DisplayRadioButton();
				radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					public void onCheckedChanged(RadioGroup group, int checkedId) {
						Task task = new Task();
						layDanhSachMaThiCongTask = new LayDanhSachMaThiCongTask();
						Role role = (Role) spnSelectRole.getSelectedItem();
						layDanhSachMaThiCongTask.addParam("sMA_TINHTP", Util.ttp.getMa_Ttp());
						layDanhSachMaThiCongTask.addParam("sDICHVUVT_ID", checkedId);
						layDanhSachMaThiCongTask.addParam("sMA_QUYEN_CD", role.getMA_NGUOI_DUNG());

						task.execute(layDanhSachMaThiCongTask);
					}
				});
			} catch (Exception e) {
				Util.showAlert(context, "Lỗi! vui lòng kiểm tra lại mạng hoặc liên hệ người phát triển.");
			}
		}
	}

	public void DisplayRadioButton() {

		for (ThongTinDichVu thongTinDichVu : arrayListThongTinDichVu) {
			RadioButton rdbtn = new RadioButton(this);
			rdbtn.setId(Integer.valueOf(thongTinDichVu.getID_DICHVU()));
			rdbtn.setText(thongTinDichVu.getTEN_DICHVU());
			radiogroup.addView(rdbtn);

		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.bttOK:
			Role role = (Role) spnSelectRole.getSelectedItem();
			layDanhSachMaThiCongTask = new LayDanhSachMaThiCongTask();
			layDanhSachMaThiCongTask.addParam("sMA_TINHTP", Util.ttp.getMa_Ttp());
			layDanhSachMaThiCongTask.addParam("sDICHVUVT_ID", 0);
			layDanhSachMaThiCongTask.addParam("sMA_QUYEN_CD", role.getMA_NGUOI_DUNG());
			onExecuteToServer(true, null, layDanhSachMaThiCongTask);
			onExecuteToServer(true, null, layDichVuVienThong_PTTBTask);

			break;

		default:
			break;
		}
	}

}
