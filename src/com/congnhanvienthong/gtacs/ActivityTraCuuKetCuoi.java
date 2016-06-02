package com.congnhanvienthong.gtacs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import org.ksoap2.serialization.SoapObject;

import com.congnhanvienthong.ActivityBaseToDisplay;
import com.congnhanvienthong.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import adapter.BaseListViewAdapter;
import adapter.BaseSpinnerAdapter;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import congnhanvienthong.entity.dhsc.Device;
import congnhanvienthong.entity.gtacs.KetCuoi;
import congnhanvienthong.entity.gtacs.LoaiDoiTuong;
import congnhanvienthong.entity.gtacs.LoaiKetCuoi;
import congnhanvienthong.entity.gtacs.LoaiKetCuoiChiTiet;
import congnhanvienthong.entity.gtacs.ThongTinDonVi;
import congnhanvienthong.entity.gtacs.ThongTinKetCuoi;
import congnhanvienthong.entity.gtacs.ThongTinTimKiemKetCuoi;
import congnhanvienthong.entity.gtacs.VeTinh;
import control.Util;
import view.YourMapFragment;
import webservice.WebProtocol;
import webservice.gtcas.CapNhatToaDoGtcasTask;
import webservice.gtcas.GetLoaiKetCuoiChiTiet;
import webservice.gtcas.GetLoaiKetCuoiTask;
import webservice.gtcas.GetObjectGTCASInfor;
import webservice.gtcas.GetThongTinDonViTask;
import webservice.gtcas.GetVeTinhTheoDonViTask;

public class ActivityTraCuuKetCuoi extends ActivityBaseToDisplay implements OnClickListener {
	Spinner spnLoaiDoiTuong, spnKhoangCach;
	BaseSpinnerAdapter<LoaiDoiTuong> adapter;
	String maDoiTuong, khoangCach;
	GoogleMap map;
	Location myLocation;
	BaseListViewAdapter<KetCuoi> adapterKetcuoi;
	ArrayList<String> listTenKetCuoi;
	AutoCompleteTextView danhDachKetCuoi;
	HashSet<LatLng> setLaLngs;
	ScrollView scrollViewMain;
	HashSet<Marker> cacDiemBTS;
	ArrayList<Device> arrayListDevices;
	Button btnMap, btnData, bttOk;
	LinearLayout viewMap, viewData;
	PullToRefreshListView lstData;
	ArrayList<ThongTinKetCuoi> lstThongTinKetCuoi;
	ArrayList<ThongTinKetCuoi> lstThongTinKetCuoiCon;
	ArrayList<ThongTinKetCuoi> lstThongTinKetCuoiHet;
	ArrayList<ThongTinDonVi> lstThongTinDonVis;
	ArrayList<VeTinh> lsVeTinhs;
	ArrayList<LoaiKetCuoi> loaiKetCuois;
	int pageIndex = 1;
	TextView txtInfor;
	Spinner spnDonVi, spnVeTinh, spnLoaiMang, spnLoaiKetCuoi, spnLoaiKetCuoiChiTiet;
	GetThongTinDonViTask donViTask;
	GetVeTinhTheoDonViTask getVeTinhTask;
	GetLoaiKetCuoiTask getLoaiKetCuoiTask;
	ThongTinTimKiemKetCuoi thongTinTimKiemKetCuoi;
	GetLoaiKetCuoiChiTiet getLoaiKetCuoiChiTiet;
	ArrayList<LoaiKetCuoiChiTiet> lstLoaiKetCuoiChiTiet;
	Button btnTimKiem;
	EditText txtName, txtAdd;
	GetObjectGTCASInfor getObjectGTCASInfor;
	// HashSet<LatLng> setToaDo;
	Animation out, in;
	long idCapNhat = 0;
	CapNhatToaDoGtcasTask capNhatToaDoGtcasTask;
	ThongTinKetCuoi thongtinCapNhat;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setBodyLayout(R.layout.activity_timkiem_ketcuoi);
		setHeader("Tìm kiếm vị trí các kết cuối");
		disableShortCut();
		context = ActivityTraCuuKetCuoi.this;
		btnData = (Button) body.findViewById(R.id.btn_data);
		btnMap = (Button) body.findViewById(R.id.btn_map);
		viewData = (LinearLayout) body.findViewById(R.id.data_view);
		viewMap = (LinearLayout) body.findViewById(R.id.map_view);
		danhDachKetCuoi = (AutoCompleteTextView) body.findViewById(R.id.edit);
		lstData = (PullToRefreshListView) body.findViewById(R.id.lst_data);
		spnDonVi = (Spinner) body.findViewById(R.id.spn_donvi);
		spnVeTinh = (Spinner) body.findViewById(R.id.spn_vetinh);
		spnLoaiMang = (Spinner) body.findViewById(R.id.spn_loai_mang);
		spnLoaiKetCuoi = (Spinner) body.findViewById(R.id.spn_loai_ketcuoi);
		spnLoaiKetCuoiChiTiet = (Spinner) body.findViewById(R.id.spn_loai_ketcuoi_ct);
		TextView emptyView = new TextView(getApplicationContext());
		btnTimKiem = (Button) body.findViewById(R.id.bttTimKiemKetCuoi);
		txtName = (EditText) body.findViewById(R.id.txt_name);
		txtAdd = (EditText) body.findViewById(R.id.txt_add);
		emptyView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		emptyView.setText("Không có dữ liệu");
		emptyView.setTextSize(20);
		emptyView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
		lstData.setAdapter(null);
		lstData.setEmptyView(emptyView);
		out = AnimationUtils.loadAnimation(context, android.R.anim.fade_out);
		in = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
		thongTinTimKiemKetCuoi = new ThongTinTimKiemKetCuoi();
		thongTinTimKiemKetCuoi.setMa_tinh_thanh(Util.ttp.getId_ttpho());// tinhthanh
		thongTinTimKiemKetCuoi.setpPageIndex("" + pageIndex);
		thongTinTimKiemKetCuoi.setpPageSize("5");
		btnData.setOnClickListener(this);
		btnMap.setOnClickListener(this);
		btnTimKiem.setOnClickListener(this);
		txtInfor = (TextView) body.findViewById(R.id.infor);
		lstData.setMode(Mode.PULL_FROM_START);
		lstData.onRefreshComplete();
		lstData.getRefreshableView().setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				ThongTinKetCuoi ttkc = (ThongTinKetCuoi) parent.getItemAtPosition(position);
				thongtinCapNhat = ttkc;
				if (ttkc.isALLOW_EDIT()) {
					map.clear();
					creatMaker(ttkc, true, true, true);
					idCapNhat = ttkc.getM_OBJECT_FID();
				} else {
					Toast.makeText(context, "Bạn không được quyền cập nhật tọa độ kết cuối " + ttkc.getM_OBJECT_NAME(),
							Toast.LENGTH_SHORT).show();
				}
				return true;
			}
		});
		lstData.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				ThongTinKetCuoi ttkc = (ThongTinKetCuoi) parent.getItemAtPosition(position);
				creatMaker(ttkc, false, false, true);

			}
		});
		spnLoaiMang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				getLoaiKetCuoiTask = new GetLoaiKetCuoiTask();
				getLoaiKetCuoiTask.addParam("ma_tinh_thanh", Util.ttp.getMa_Ttp());
				getLoaiKetCuoiTask.addParam("loaiMangCapID", position == 0 ? 7002 : 7000);
				thongTinTimKiemKetCuoi.setCableNetwork(position == 0 ? "7002" : "7000");// loaimang
				onExecuteToServer(true, null, getLoaiKetCuoiTask);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapo)).getMap();
		scrollViewMain = (ScrollView) body.findViewById(R.id.scroll_main);
		((YourMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapo))
				.setListener(new YourMapFragment.OnTouchListener() {

					@Override
					public void onTouch() {
						// TODO Auto-generated method stub
						scrollViewMain.requestDisallowInterceptTouchEvent(true);

					}
				});
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(21.0293, 105.8522), 14));
		map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		map.getUiSettings().setZoomControlsEnabled(true);
		map.getUiSettings().setCompassEnabled(true);
		map.getUiSettings().setMyLocationButtonEnabled(true);
		map.getUiSettings().setAllGesturesEnabled(true);
		map.setTrafficEnabled(true);
		map.getUiSettings().setTiltGesturesEnabled(true);
		map.setMyLocationEnabled(true);
		map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

			@Override
			public View getInfoWindow(Marker arg0) {
				return null;
			}

			@Override
			public View getInfoContents(Marker marker) {

				LinearLayout info = new LinearLayout(context);
				info.setOrientation(LinearLayout.VERTICAL);

				TextView title = new TextView(context);
				title.setTextColor(Color.BLACK);
				title.setGravity(Gravity.CENTER);
				title.setTypeface(null, Typeface.BOLD);
				title.setText(marker.getTitle());

				TextView snippet = new TextView(context);
				snippet.setTextColor(Color.GRAY);
				snippet.setText(marker.getSnippet());
				info.addView(title);
				info.addView(snippet);

				return info;
			}
		});
		map.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
			LatLng oldLatLon = null;

			@Override
			public void onMarkerDragStart(Marker arg0) {
				// TODO Auto-generated method stub
				oldLatLon = arg0.getPosition();

			}

			@Override
			public void onMarkerDragEnd(final Marker arg0) {
				// TODO Auto-generated method stub
				// Toast.makeText(context, "Có cập nhật mới không",
				// Toast.LENGTH_SHORT).show();

				AlertDialog.Builder alert = new AlertDialog.Builder(context);
				alert.setTitle("Thông báo!");
				alert.setMessage("Cập nhật tọa độ cho kết cuối : " + arg0.getTitle());
				alert.setNegativeButton("Quay lại", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						// arg0.setPosition(oldLatLon);
						map.clear();
						// CameraPosition cameraPosition = new
						// CameraPosition.Builder().target(oldLatLon).bearing(45)
						// .tilt(90).zoom(map.getCameraPosition().zoom).build();
						// map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),
						// 2000, null);
						creatMaker(thongtinCapNhat, false, true, true);

					}
				});
				alert.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						capNhatToaDoGtcasTask = new CapNhatToaDoGtcasTask();
						capNhatToaDoGtcasTask.addParam("MaTinhThanh", Util.ttp.getMa_Ttp());
						capNhatToaDoGtcasTask.addParam("ObjectId", idCapNhat);
						capNhatToaDoGtcasTask.addParam("Long", arg0.getPosition().longitude);
						capNhatToaDoGtcasTask.addParam("Lat", arg0.getPosition().latitude);
						capNhatToaDoGtcasTask.addParam("UserName", Util.userName);
						onExecuteToServer(true, null, capNhatToaDoGtcasTask);

					}
				});
				alert.show();
			}

			@Override
			public void onMarkerDrag(Marker arg0) {
				// TODO Auto-generated method stub

			}
		});
		getVeTinhTask = new GetVeTinhTheoDonViTask();

		donViTask = new GetThongTinDonViTask();
		donViTask.addParam("ma_tinh_thanh", Util.ttp.getMa_Ttp());
		donViTask.addParam("user_name", Util.userName);

		onExecuteToServer(true, null, donViTask);

		spnVeTinh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				VeTinh vt = (VeTinh) parent.getAdapter().getItem(position);
				if (vt != null) {
					thongTinTimKiemKetCuoi.setMa_ve_tinh(vt.getEXCH_CODE());// vetinh
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		spnLoaiKetCuoi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				LoaiKetCuoi lkc = (LoaiKetCuoi) parent.getAdapter().getItem(position);
				if (lkc != null) {
					thongTinTimKiemKetCuoi.setpM_OBJECT_FNO("" + lkc.getID());// ketcuoi
				}
				getLoaiKetCuoiChiTiet = new GetLoaiKetCuoiChiTiet();
				getLoaiKetCuoiChiTiet.addParam("ma_tinh_thanh", Util.ttp.getMa_Ttp());
				getLoaiKetCuoiChiTiet.addParam("typeID", lkc.getID());
				thongTinTimKiemKetCuoi.setpM_OBJECT_SUB_TYPE_ID("");
				onExecuteToServer(true, null, getLoaiKetCuoiChiTiet);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}

		});
		spnLoaiKetCuoiChiTiet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				LoaiKetCuoiChiTiet lkcct = (LoaiKetCuoiChiTiet) parent.getAdapter().getItem(position);
				if (lkcct != null) {
					thongTinTimKiemKetCuoi.setpM_OBJECT_SUB_TYPE_ID("" + lkcct.getID());// ketcuoichitiet
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		lstData.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				pageIndex = pageIndex + 1;
				getObjectGTCASInfor = new GetObjectGTCASInfor();
				getObjectGTCASInfor.addParam("ma_tinh_thanh", thongTinTimKiemKetCuoi.getMa_tinh_thanh());
				getObjectGTCASInfor.addParam("user_name", Util.userName);
				getObjectGTCASInfor.addParam("ma_ve_tinh", thongTinTimKiemKetCuoi.getMa_ve_tinh());
				getObjectGTCASInfor.addParam("cableNetwork", thongTinTimKiemKetCuoi.getCableNetwork());
				getObjectGTCASInfor.addParam("pM_OBJECT_FNO", thongTinTimKiemKetCuoi.getpM_OBJECT_FNO());
				getObjectGTCASInfor.addParam("pM_OBJECT_SUB_TYPE_ID",
						thongTinTimKiemKetCuoi.getpM_OBJECT_SUB_TYPE_ID() == null
								? thongTinTimKiemKetCuoi.getpM_OBJECT_SUB_TYPE_ID() : 0);

				getObjectGTCASInfor.addParam("pM_OBJECT_NAME", txtName.getText().toString());
				getObjectGTCASInfor.addParam("pM_OBJECT_ADDR", txtAdd.getText().toString());
				getObjectGTCASInfor.addParam("pPageIndex", pageIndex);
				getObjectGTCASInfor.addParam("pPageSize", "5");

				onExecuteToServer(true, null, getObjectGTCASInfor);
			}
		});
		spnDonVi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				ThongTinDonVi dv = (ThongTinDonVi) arg0.getAdapter().getItem(arg2);
				if (dv != null) {
					getVeTinhTask = new GetVeTinhTheoDonViTask();
					getVeTinhTask.addParam("ma_tinh_thanh", Util.ttp.getMa_Ttp());
					getVeTinhTask.addParam("user_name", Util.userName);
					getVeTinhTask.addParam("maDonViQL", dv.getID());
					onExecuteToServer(true, null, getVeTinhTask);
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	@Override
	public void onsucces(WebProtocol ws) {
		// TODO Auto-generated method stub

		super.onsucces(ws);

		// --------------lấy đơn vị----------------//
		if (ws.equals(donViTask)) {
			SoapObject arrString = (SoapObject) donViTask.result;
			SoapObject arrRes = (SoapObject) arrString.getProperty("Result");
			int lenth = arrRes.getPropertyCount();
			lstThongTinDonVis = new ArrayList<ThongTinDonVi>();
			for (int i = 0; i < lenth; i++) {
				SoapObject res = (SoapObject) arrRes.getProperty(i);
				ThongTinDonVi thongTinDonVi = new ThongTinDonVi();
				Util.GetObjectFromSoapObject(thongTinDonVi, res);
				lstThongTinDonVis.add(thongTinDonVi);
			}
			BaseSpinnerAdapter<ThongTinDonVi> adapterDonVi = new BaseSpinnerAdapter<ThongTinDonVi>(context,
					android.R.layout.simple_spinner_dropdown_item, lstThongTinDonVis);
			adapterDonVi.name = "MANAGEMENT_CENTRE_NAME";
			spnDonVi.setAdapter(adapterDonVi);

		}
		// ----lấy list vệ tinh---------------
		if (ws.equals(getVeTinhTask)) {

			SoapObject arrString = (SoapObject) getVeTinhTask.result;
			SoapObject arrRes = (SoapObject) arrString.getProperty("Result");
			int lenth = arrRes.getPropertyCount();
			lsVeTinhs = new ArrayList<VeTinh>();
			for (int i = 0; i < lenth; i++) {
				SoapObject res = (SoapObject) arrRes.getProperty(i);
				VeTinh veTinh = new VeTinh();
				Util.GetObjectFromSoapObject(veTinh, res);
				lsVeTinhs.add(veTinh);
			}
			BaseSpinnerAdapter<VeTinh> adapterVeTinh = new BaseSpinnerAdapter<VeTinh>(context,
					android.R.layout.simple_spinner_dropdown_item, lsVeTinhs);
			adapterVeTinh.name = "EXCH_NAME";
			spnVeTinh.setAdapter(adapterVeTinh);
			ArrayAdapter<String> loaimangAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.loai_mang));
			spnLoaiMang.setAdapter(loaimangAdapter);

		}
		// lấy loại kết cuối
		if (ws.equals(getLoaiKetCuoiTask)) {
			SoapObject arrString = (SoapObject) getLoaiKetCuoiTask.result;
			SoapObject arrRes = (SoapObject) arrString.getProperty("Result");
			int lenth = arrRes.getPropertyCount();
			loaiKetCuois = new ArrayList<LoaiKetCuoi>();
			for (int i = 0; i < lenth; i++) {
				SoapObject res = (SoapObject) arrRes.getProperty(i);
				LoaiKetCuoi loaiKetCuoi = new LoaiKetCuoi();
				Util.GetObjectFromSoapObject(loaiKetCuoi, res);
				loaiKetCuois.add(loaiKetCuoi);
			}
			Collections.reverse(loaiKetCuois);
			BaseSpinnerAdapter<LoaiKetCuoi> adapterLoaiKetCuoi = new BaseSpinnerAdapter<LoaiKetCuoi>(context,
					android.R.layout.simple_spinner_dropdown_item, loaiKetCuois);
			adapterLoaiKetCuoi.name = "NAME";
			spnLoaiKetCuoi.setAdapter(adapterLoaiKetCuoi);

		}
		// lấy loại kết cuối chi tiết
		if (ws.equals(getLoaiKetCuoiChiTiet)) {

			SoapObject arrString = (SoapObject) getLoaiKetCuoiChiTiet.result;
			SoapObject arrRes = (SoapObject) arrString.getProperty("Result");
			int lenth = arrRes.getPropertyCount();
			lstLoaiKetCuoiChiTiet = new ArrayList<LoaiKetCuoiChiTiet>();
			for (int i = 0; i < lenth; i++) {
				SoapObject res = (SoapObject) arrRes.getProperty(i);
				LoaiKetCuoiChiTiet loaiKetCuoiCT = new LoaiKetCuoiChiTiet();
				Util.GetObjectFromSoapObject(loaiKetCuoiCT, res);
				lstLoaiKetCuoiChiTiet.add(loaiKetCuoiCT);
			}
			// Collections.reverse(lstLoaiKetCuoiChiTiet);
			BaseSpinnerAdapter<LoaiKetCuoiChiTiet> adapterLoaiKetCuoiCT = new BaseSpinnerAdapter<LoaiKetCuoiChiTiet>(
					context, android.R.layout.simple_spinner_dropdown_item, lstLoaiKetCuoiChiTiet);
			adapterLoaiKetCuoiCT.name = "NAME";
			spnLoaiKetCuoiChiTiet.setAdapter(adapterLoaiKetCuoiCT);

		}
		// lay du lieu
		if (ws.equals(getObjectGTCASInfor)) {
			SoapObject arrString = (SoapObject) getObjectGTCASInfor.result;
			SoapObject arrRes = (SoapObject) arrString.getProperty("Result");
			String total = arrString.getPrimitivePropertyAsString("TotalRecord");
			int lenth = arrRes.getPropertyCount();
			double move_lat = 21.02932;
			double move_lon = 105.852;

			for (int i = 0; i < lenth; i++) {
				SoapObject res = (SoapObject) arrRes.getProperty(i);
				ThongTinKetCuoi thontinketcuoi = new ThongTinKetCuoi();
				Util.GetObjectFromSoapObject(thontinketcuoi, res);
				lstThongTinKetCuoi.add(thontinketcuoi);
				creatMaker(thontinketcuoi, false, false, false);
				try {
					move_lat = Float.parseFloat(thontinketcuoi.getLATITUDE());
					move_lon = Float.parseFloat(thontinketcuoi.getLONGITUDE());
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(move_lat, move_lon), 14));
			lstData.setMode(Mode.PULL_FROM_START);
			lstData.onRefreshComplete();
			lstData.setAdapter(new BaseListViewAdapter<ThongTinKetCuoi>(context, lstThongTinKetCuoi, null, false));
			txtInfor.setText("Hiển thị " + lstThongTinKetCuoi.size() + "/" + total + " bản ghi!");
		}
		if (ws.equals(capNhatToaDoGtcasTask)) {
			SoapObject arrString = (SoapObject) capNhatToaDoGtcasTask.result;
			String mes = arrString.getPropertyAsString("Message");
			Util.showAlert(context, mes);

		}

	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_map:
			viewMap.startAnimation(in);
			viewData.startAnimation(out);
			viewMap.setVisibility(View.VISIBLE);
			viewData.setVisibility(View.GONE);

			break;
		case R.id.btn_data:
			viewMap.startAnimation(out);
			viewData.startAnimation(in);
			viewMap.setVisibility(View.GONE);
			viewData.setVisibility(View.VISIBLE);
			break;

		case R.id.bttTimKiemKetCuoi:
			// thongTinTimKiemKetCuoi
			lstThongTinKetCuoi = new ArrayList<ThongTinKetCuoi>();
			lstThongTinKetCuoiCon = new ArrayList<ThongTinKetCuoi>();
			lstThongTinKetCuoiHet = new ArrayList<ThongTinKetCuoi>();
			map.clear();
			lstData.setAdapter(null);
			pageIndex = 1;
			getObjectGTCASInfor = new GetObjectGTCASInfor();
			getObjectGTCASInfor = new GetObjectGTCASInfor();
			getObjectGTCASInfor.addParam("ma_tinh_thanh", thongTinTimKiemKetCuoi.getMa_tinh_thanh());
			getObjectGTCASInfor.addParam("user_name", Util.userName);
			getObjectGTCASInfor.addParam("ma_ve_tinh", thongTinTimKiemKetCuoi.getMa_ve_tinh());
			getObjectGTCASInfor.addParam("cableNetwork", thongTinTimKiemKetCuoi.getCableNetwork());
			getObjectGTCASInfor.addParam("pM_OBJECT_FNO", thongTinTimKiemKetCuoi.getpM_OBJECT_FNO());
			getObjectGTCASInfor.addParam("pM_OBJECT_SUB_TYPE_ID",
					thongTinTimKiemKetCuoi.getpM_OBJECT_SUB_TYPE_ID() == null
							? thongTinTimKiemKetCuoi.getpM_OBJECT_SUB_TYPE_ID() : 0);

			getObjectGTCASInfor.addParam("pM_OBJECT_NAME", txtName.getText().toString());
			getObjectGTCASInfor.addParam("pM_OBJECT_ADDR", txtAdd.getText().toString());
			getObjectGTCASInfor.addParam("pPageIndex", pageIndex);
			getObjectGTCASInfor.addParam("pPageSize", "5");
			onExecuteToServer(true, "Tìm kiếm thông tin ?", getObjectGTCASInfor);
			break;
		}

	}

	public Marker creatMaker(ThongTinKetCuoi thongTinKetCuoi, boolean setLonLat, boolean setDrag, boolean showInfor) {
		double lat = 0;
		double lon = 0;
		try {
			lat = Double.parseDouble(thongTinKetCuoi.getLATITUDE());
			lon = Double.parseDouble(thongTinKetCuoi.getLONGITUDE());

		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(context, "Chưa có dữ liệu vị trí", Toast.LENGTH_SHORT).show();
			if (myLocation != null && setLonLat) {
				lat = myLocation.getLatitude();
				lon = myLocation.getLongitude();
			}
		}

		LatLng latLng = new LatLng(lat, lon);
		MarkerOptions mko = new MarkerOptions();
		mko.position(latLng);

		int con = 0;
		try {
			con = (int) (thongTinKetCuoi.getO_TOTAL_SIZE() - thongTinKetCuoi.getIN_USED());
		} catch (Exception e) {
			// TODO: handle exception
		}
		mko.snippet("Dung lượng(tổng/đã dùng/còn trống) :" + thongTinKetCuoi.getO_TOTAL_SIZE() + "/"
				+ thongTinKetCuoi.getIN_USED() + "/" + con);
		mko.anchor(0.5f, 1.0f);
		BitmapDescriptor ketcuoiIcon;
		if (con == 0)
			ketcuoiIcon = BitmapDescriptorFactory.fromResource(R.drawable.het);
		else {
			ketcuoiIcon = BitmapDescriptorFactory.fromResource(R.drawable.con_dl);
		}
		mko.icon(ketcuoiIcon);
		mko.title(thongTinKetCuoi.getM_OBJECT_NAME());

		mko.draggable(setDrag);
		Marker m = map.addMarker(mko);
		if (showInfor) {
			m.showInfoWindow();
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 14));
		}
		viewMap.setVisibility(View.VISIBLE);
		viewData.setVisibility(View.GONE);

		return null;

	}

}
