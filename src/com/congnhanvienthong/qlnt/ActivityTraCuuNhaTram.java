package com.congnhanvienthong.qlnt;

import java.util.ArrayList;
import java.util.HashMap;

import com.congnhanvienthong.ActivityBaseToDisplay;
import com.congnhanvienthong.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import adapter.BaseListViewAdapter;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import api.GetDanhSachNhaTramApi;
import api.GetDonViQuanLyApi;
import api.GetLoaiNhaTramApi;
import api.GetToaDoFromAddressTask;
import congnhanvienthong.entity.nhatram.DonViQuanLy;
import congnhanvienthong.entity.nhatram.LoaiNhaTram;
import congnhanvienthong.entity.nhatram.NhaTram;
import congnhanvienthong.entity.vitri.DoiTuong;
import congnhanvienthong.entity.vitri.TAI_NGUYEN_MANG;
import congnhanvienthong.entity.vitri.ThongTinToaDo;
import control.Util;
import webservice.WebProtocol;
import webservice.vitri.CapNhatDoiTuongTaiNguyenMangTask;
import webservice.vitri.LayListThongTinToaDoTask;

public class ActivityTraCuuNhaTram extends ActivityBaseToDisplay {
	GetDonViQuanLyApi getDonViApi;
	GetLoaiNhaTramApi getLoaiNhaTramApi;
	Spinner spnLoaiNhaTram, spnDonVi;
	ArrayList<LoaiNhaTram> lstLoaiNhaTram;
	ArrayList<DonViQuanLy> lstDonViQuanLy;
	Button btnOk;
	Button btnMap, btnData;
	GetDanhSachNhaTramApi getDSNhaTramApi;
	RelativeLayout viewMap;
	LinearLayout viewData, resultView;
	int hei;
	Animation out, in;
	PullToRefreshListView lstData;
	TextView emptyView;
	int pageIndex = 1;
	EditText txtMa, txtTen;
	ArrayList<NhaTram> lstNhaTram;
	GoogleMap map;
	TextView txtInfor;
	Location myLocation;
	NhaTram editedNhaTram;
	CapNhatDoiTuongTaiNguyenMangTask capNhatToaDoNhaTramTask;
	TAI_NGUYEN_MANG taiNguyenMang;
	LayListThongTinToaDoTask layListToaDoTask;
	int coToaDo = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setBodyLayout(R.layout.activity_tracuu_nhatram);
		setFootLayout(R.layout.foot_tracuu);
		txtMa = (EditText) body.findViewById(R.id.txtMaNhaTram);
		txtTen = (EditText) body.findViewById(R.id.txtTenNhaTram);
		btnOk = (Button) foot.findViewById(R.id.bttOK);
		txtInfor = (TextView) body.findViewById(R.id.infor);
		map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapo)).getMap();
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
		setHeader("Tra cứu thông tin nhà trạm");
		capNhatToaDoNhaTramTask = new CapNhatDoiTuongTaiNguyenMangTask();
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
		btnOk.setOnClickListener(this);
		viewData = (LinearLayout) body.findViewById(R.id.data_view);
		viewMap = (RelativeLayout) body.findViewById(R.id.map_view);
		out = AnimationUtils.loadAnimation(context, android.R.anim.fade_out);
		in = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
		spnDonVi = (Spinner) body.findViewById(R.id.spnDonViQuanLy);
		spnLoaiNhaTram = (Spinner) body.findViewById(R.id.spnLoaiNhaTram);
		getDonViApi = new GetDonViQuanLyApi();
		getLoaiNhaTramApi = new GetLoaiNhaTramApi();
		emptyView = new TextView(getApplicationContext());
		lstData = (PullToRefreshListView) body.findViewById(R.id.lst_data);
		emptyView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		emptyView.setText("Không có dữ liệu");
		emptyView.setTextSize(20);
		emptyView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
		lstData.setAdapter(null);
		lstData.setEmptyView(emptyView);
		btnData = (Button) body.findViewById(R.id.btn_data);
		btnMap = (Button) body.findViewById(R.id.btn_map);
		btnData.setOnClickListener(this);
		btnMap.setOnClickListener(this);
		resultView = (LinearLayout) body.findViewById(R.id.map_dt);
		android.view.ViewGroup.LayoutParams param = resultView.getLayoutParams();
		hei = param.height;
		setListenerToRootView();
		getDSNhaTramApi = new GetDanhSachNhaTramApi();
		map.setOnMyLocationButtonClickListener(new OnMyLocationButtonClickListener() {

			@Override
			public boolean onMyLocationButtonClick() {
				// TODO Auto-generated method stub
				Util.confirmTurnOnGPS(context);
				myLocation = map.getMyLocation();
				return false;
			}
		});
		lstData.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				editedNhaTram = (NhaTram) parent.getAdapter().getItem(position);
				Marker mk = null;
				if (editedNhaTram.getMaker() != null) {
					mk = editedNhaTram.getMaker();
					mk.showInfoWindow();
					viewData.setVisibility(View.GONE);
					viewMap.setVisibility(View.VISIBLE);
					mk.setDraggable(true);
					Toast.makeText(context, "Di nhẹ icon để cập nhật tọa độ!", Toast.LENGTH_LONG).show();
					map.moveCamera(CameraUpdateFactory.newLatLngZoom(mk.getPosition(), 14));
				} else {
					Toast.makeText(context, "Không có dữ liệu bản đồ", Toast.LENGTH_LONG).show();
					GetToaDoFromAddressTask getToaDoFromAddTask = new GetToaDoFromAddressTask();
					getToaDoFromAddTask.SetConText(context);
					getToaDoFromAddTask.setDiaChi(editedNhaTram.getTenPhuongXa() + " " + editedNhaTram.getTenQuanHuyen()
							+ " " + Util.ttp.getTen_ttpho());
					onExecuteToServer(true, null, getToaDoFromAddTask);
				}

			}
		});
		map.setOnMarkerDragListener(new OnMarkerDragListener() {

			@Override
			public void onMarkerDragStart(Marker arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onMarkerDragEnd(final Marker arg0) {
				// TODO Auto-generated method stub
				taiNguyenMang = new TAI_NGUYEN_MANG();
				taiNguyenMang.setID_HETHONGGOC(6);
				taiNguyenMang.setDC_DOITUONG(editedNhaTram.getDiaChi());
				taiNguyenMang.setID_DOITUONG(editedNhaTram.getId());
				taiNguyenMang.setID_LOAIDOITUONG(560);
				final ThongTinToaDo thongTinToaDo = new ThongTinToaDo();
				thongTinToaDo.setLatitude("" + arg0.getPosition().latitude);
				thongTinToaDo.setLongtitude("" + arg0.getPosition().longitude);
				thongTinToaDo.setGhichu("Đẩy lên từ VNPT 360");
				AlertDialog.Builder alert = new AlertDialog.Builder(context);
				alert.setTitle("Thông báo!");
				alert.setMessage(
						"Cập nhật tọa độ cho " + ((LoaiNhaTram) spnLoaiNhaTram.getSelectedItem()).getTenLoaiNhaTram()
								+ " : " + arg0.getTitle());
				alert.setNegativeButton("Quay lại", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						arg0.remove();
						Marker mk = editedNhaTram.creatMarker(map, false, false);
						editedNhaTram.setMaker(mk);
						viewMap.setVisibility(View.VISIBLE);
						viewData.setVisibility(View.GONE);

					}
				});
				alert.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						capNhatToaDoNhaTramTask = new CapNhatDoiTuongTaiNguyenMangTask();
						capNhatToaDoNhaTramTask.addParam("obj", taiNguyenMang);
						capNhatToaDoNhaTramTask.addParam("toaDo", thongTinToaDo);
						capNhatToaDoNhaTramTask.addParam("lstAnh", "");
						capNhatToaDoNhaTramTask.addParam("nguoiCapNhat", Util.userName);
						capNhatToaDoNhaTramTask.addParam("maTtp", Util.ttp.getMa_Ttp());
						onExecuteToServer(true, null, capNhatToaDoNhaTramTask);
					}
				});
				alert.show();

			}

			@Override
			public void onMarkerDrag(Marker arg0) {
				// TODO Auto-generated method stub

			}
		});
		lstData.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				pageIndex = pageIndex + 1;
				getDSNhaTramApi.addParam("pageIndex", "" + pageIndex);
				onExecuteToServer(false, null, getDSNhaTramApi);
			}
		});
		onExecuteToServer(true, null, getDonViApi, getLoaiNhaTramApi);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onsucces(WebProtocol task) {
		// TODO Auto-generated method stub
		super.onsucces(task);
		if (task.equals(getDonViApi)) {
			lstDonViQuanLy = new ArrayList<DonViQuanLy>();
			lstDonViQuanLy = (ArrayList<DonViQuanLy>) getDonViApi.getResult();
			Util.SetDataToSpinner(context, spnDonVi, lstDonViQuanLy, "TenDonVi");
			lstLoaiNhaTram = (ArrayList<LoaiNhaTram>) getLoaiNhaTramApi.getResult();
			Util.SetDataToSpinner(context, spnLoaiNhaTram, lstLoaiNhaTram, "TenLoaiNhaTram");

		}
		if (task.equals(getDSNhaTramApi)) {
			ArrayList<NhaTram> lst = (ArrayList<NhaTram>) getDSNhaTramApi.getResult();
			lstNhaTram.addAll(lst);
			ArrayList<DoiTuong> lstDoiTuong = new ArrayList<DoiTuong>();
			for (NhaTram nhaTram : lstNhaTram) {
				DoiTuong doituong = new DoiTuong();
				doituong.setID_DOITUONG(nhaTram.getId());
				doituong.setID_HETHONGGOC(6);
				doituong.setID_LOAIDOITUONG(560);
				lstDoiTuong.add(doituong);
			}
			Gson gson = new Gson();
			String obj = gson.toJson(lstDoiTuong);
			layListToaDoTask = new LayListThongTinToaDoTask();
			layListToaDoTask.addParam("lstObjJson", obj);
			layListToaDoTask.addParam("maTtp", "HNI");
			layListToaDoTask.addParam("isGetImages", false);
			onExecuteToServer(true, null, layListToaDoTask);

		}
		if (task.equals(capNhatToaDoNhaTramTask)) {
			Util.showAlert(context, capNhatToaDoNhaTramTask.getResult().toString());
		}
		if (task.equals(layListToaDoTask)) {
			double roundLong = 0;
			double roundLat = 0;
			coToaDo = 0;
			HashMap<Integer, ThongTinToaDo> hashmap = (HashMap<Integer, ThongTinToaDo>) layListToaDoTask.getResult();

			BaseListViewAdapter<NhaTram> adapterNhaTram = new BaseListViewAdapter<NhaTram>(context, lstNhaTram);
			lstData.setMode(Mode.PULL_FROM_START);
			lstData.onRefreshComplete();
			lstData.setAdapter(adapterNhaTram);

			for (NhaTram nhaTram : lstNhaTram) {
				ThongTinToaDo thongTinToaDo = hashmap.get(nhaTram.getId());
				if (thongTinToaDo != null) {
					coToaDo++;
					try {
						roundLong = roundLong + Double.parseDouble(thongTinToaDo.getLongtitude());
						roundLat = roundLat + Double.parseDouble(thongTinToaDo.getLatitude());
					} catch (Exception e) {
						// TODO: handle exception
					}
					nhaTram.setLatitude(thongTinToaDo.getLatitude());
					nhaTram.setLongitude(thongTinToaDo.getLongtitude());
					Marker maker = nhaTram.creatMarker(map, false, false);
					viewMap.setVisibility(View.VISIBLE);
					viewData.setVisibility(View.GONE);
					nhaTram.setMaker(maker);
				}

			}
			if (coToaDo != 0) {
				roundLat = roundLat / coToaDo;
				roundLong = roundLong / coToaDo;
			}
			if (roundLat != 0 && roundLong != 0) {
				map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(roundLat, roundLong), 14));
			}
			if (lstNhaTram.size() > 0) {
				txtInfor.setText("Có " + lstNhaTram.size() + "/" + getDSNhaTramApi.totalSize + " bản ghi. Có " + coToaDo
						+ " có tọa độ");
			} else {
				txtInfor.setText("");
			}
		}
		if (task instanceof GetToaDoFromAddressTask) {
			Address add = (Address) task.getResult();
			myLocation = map.getMyLocation();
			if (add != null) {
				editedNhaTram.setLatitude("" + add.getLatitude());
				editedNhaTram.setLongitude("" + add.getLongitude());
			}
			if (add == null && myLocation == null) {
				Util.confirmTurnOnGPS(context);
			}
			if (myLocation != null && add == null) {
				editedNhaTram.setLatitude("" + myLocation.getLatitude());
				editedNhaTram.setLongitude("" + myLocation.getLongitude());
			}
			Marker maker = editedNhaTram.creatMarker(map, false, false);
			viewMap.setVisibility(View.VISIBLE);
			viewData.setVisibility(View.GONE);

			if (maker != null) {
				maker.setDraggable(true);
				maker.showInfoWindow();
				map.moveCamera(CameraUpdateFactory.newLatLngZoom(maker.getPosition(), 14));
				viewMap.setVisibility(View.VISIBLE);
				viewData.setVisibility(View.GONE);
			}

		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.bttOK:
			map.clear();
			lstNhaTram = new ArrayList<NhaTram>();
			pageIndex = 1;
			coToaDo = 0;
			getDSNhaTramApi = new GetDanhSachNhaTramApi();
			LoaiNhaTram loainhatram = (LoaiNhaTram) spnLoaiNhaTram.getSelectedItem();
			DonViQuanLy donvi = (DonViQuanLy) spnDonVi.getSelectedItem();
			getDSNhaTramApi.addParam("loaiNhaTramId", "" + loainhatram.getLoaiNhaTramId());
			getDSNhaTramApi.addParam("donViQuanLyId", "" + donvi.getDonViId());
			getDSNhaTramApi.addParam("maNhaTram", txtMa.getText().toString());
			getDSNhaTramApi.addParam("tenNhaTram", txtTen.getText().toString());
			getDSNhaTramApi.addParam("pageIndex", "" + pageIndex);
			onExecuteToServer(true, "Tìm kiếm nhà trạm", getDSNhaTramApi);
			break;
		case R.id.btn_map:
			if (viewMap.getVisibility() == View.GONE) {
				viewMap.startAnimation(in);
				viewData.startAnimation(out);
				viewMap.setVisibility(View.VISIBLE);
				viewData.setVisibility(View.GONE);
			}

			break;
		case R.id.btn_data:
			if (viewData.getVisibility() == View.GONE) {
				viewMap.startAnimation(out);
				viewData.startAnimation(in);
				viewMap.setVisibility(View.GONE);
				viewData.setVisibility(View.VISIBLE);
			}
			break;

		default:
			break;
		}
	}

	public void setListenerToRootView() {
		final View activityRootView = getWindow().getDecorView().findViewById(android.R.id.content);
		activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {

				int heightDiff = activityRootView.getRootView().getHeight() - activityRootView.getHeight();
				android.view.ViewGroup.LayoutParams param = resultView.getLayoutParams();
				if (heightDiff > 100) {

					param.height = 100;
					resultView.setLayoutParams(param);

				} else {

					param.height = hei;
					resultView.setLayoutParams(param);
				}
			}
		});
	}

	// public Marker creatMaker(NhaTram nhatram, boolean setLonLat, boolean
	// setDrag, boolean showInfor) {
	// double lat;
	// double lon;
	// Marker m = null;
	// try {
	// lat = Double.parseDouble(nhatram.getLatitude());
	// lon = Double.parseDouble(nhatram.getLongitude());
	// LatLng latLng = new LatLng(lat, lon);
	// MarkerOptions mko = new MarkerOptions();
	// mko.position(latLng);
	//
	// mko.snippet(nhatram.getMaDonViQuanLy());
	// mko.anchor(0.5f, 1.0f);
	// BitmapDescriptor ketcuoiIcon;
	// ketcuoiIcon = BitmapDescriptorFactory.fromResource(R.drawable.con_dl);
	// mko.icon(ketcuoiIcon);
	// mko.title(nhatram.getDiaChi());
	//
	// mko.draggable(setDrag);
	// m = map.addMarker(mko);
	// if (showInfor) {
	// m.showInfoWindow();
	// map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon),
	// 14));
	// }
	// viewMap.setVisibility(View.VISIBLE);
	// viewData.setVisibility(View.GONE);
	//
	// } catch (Exception e) {
	// // TODO: handle exception
	// if (setLonLat)
	// Toast.makeText(context, "Chưa có dữ liệu vị trí",
	// Toast.LENGTH_SHORT).show();
	// if (myLocation != null && setLonLat) {
	// lat = myLocation.getLatitude();
	// lon = myLocation.getLongitude();
	// }
	// }
	//
	// return m;
	//
	// }

}
