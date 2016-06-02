package com.congnhanvienthong.pttb;

import java.util.ArrayList;
import java.util.HashMap;

import org.ksoap2.serialization.SoapObject;

import com.congnhanvienthong.ActivityBaseToDisplay;
import com.congnhanvienthong.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import adapter.BaseListViewAdapter;
import adapter.BaseSpinnerAdapter;
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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import api.GetToaDoFromAddressTask;
import congnhanvienthong.entity.pttb.DoiTac;
import congnhanvienthong.entity.pttb.DuAn;
import congnhanvienthong.entity.pttb.QuanHuyen;
import congnhanvienthong.entity.pttb.ThongTinTimKiemToaNha;
import congnhanvienthong.entity.pttb.ThongTinToaNha;
import congnhanvienthong.entity.vitri.DoiTuong;
import congnhanvienthong.entity.vitri.TAI_NGUYEN_MANG;
import congnhanvienthong.entity.vitri.ThongTinToaDo;
import control.Util;
import webservice.WebProtocol;
import webservice.pttb.GetDoiTacTask;
import webservice.pttb.GetDuAnTask;
import webservice.pttb.GetQuanHuyenTask;
import webservice.pttb.GetToaNhaTask;
import webservice.vitri.CapNhatDoiTuongTaiNguyenMangTask;
import webservice.vitri.LayListThongTinToaDoTask;

public class ActivityTraCuuToaNha extends ActivityBaseToDisplay {
	GetDuAnTask duAnTask = new GetDuAnTask();
	GetDoiTacTask doiTacTask = new GetDoiTacTask();
	GetQuanHuyenTask quanHuyenTask = new GetQuanHuyenTask();
	ArrayList<DuAn> lstDuAnObj = new ArrayList<DuAn>();
	ArrayList<DoiTac> lstDoiTacObj = new ArrayList<DoiTac>();
	ArrayList<QuanHuyen> lstQuanHuyenObj = new ArrayList<QuanHuyen>();
	ArrayList<ThongTinToaNha> lstThongTinToaNhas = new ArrayList<ThongTinToaNha>();
	Spinner spnDoiTac, spnQuanHuyen, spnDuAn;
	Button bttOK;
	ThongTinTimKiemToaNha thongTinTimToaNha;
	EditText txtTen, txtDiaChi;
	GetToaNhaTask getToaNhaTask;
	Button btnMap, btnData, bttOk;
	RelativeLayout viewMap;
	LinearLayout viewData, resultView;
	Animation out, in;
	PullToRefreshListView lstData;
	TextView resInfor;
	int hei;
	GoogleMap map;
	Location myLocation;
	CapNhatDoiTuongTaiNguyenMangTask CapNhatDoiTuongTaiNguyenMangTask;
	LayListThongTinToaDoTask layListThongTinToaDo;
	int coToaDo = 0;
	ThongTinToaNha editedToaNha;
	Marker lastMaker;
	CapNhatDoiTuongTaiNguyenMangTask capNhatToaDoToaNhaTask;
	TAI_NGUYEN_MANG taiNguyenMang;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setBodyLayout(R.layout.activity_tracuutoanha);
		setFootLayout(R.layout.foot_tracuu);
		thongTinTimToaNha = new ThongTinTimKiemToaNha();
		spnDoiTac = (Spinner) body.findViewById(R.id.spn_doitac);
		spnDuAn = (Spinner) body.findViewById(R.id.spn_duan);
		spnQuanHuyen = (Spinner) body.findViewById(R.id.spn_quanhuyen);
		txtTen = (EditText) body.findViewById(R.id.txt_ten_toa_nha);
		txtDiaChi = (EditText) body.findViewById(R.id.txt_dc_toa_nha);
		bttOK = (Button) foot.findViewById(R.id.bttOK);
		bttOK.setOnClickListener(this);
		doiTacTask.addParam("sMA_TINHTP", Util.ttp.getMa_Ttp());
		quanHuyenTask.addParam("sMA_TINHTP", Util.ttp.getMa_Ttp());
		btnData = (Button) body.findViewById(R.id.btn_data);
		btnMap = (Button) body.findViewById(R.id.btn_map);
		btnData.setOnClickListener(this);
		btnMap.setOnClickListener(this);
		setHeader("Tra cứu tòa nhà");
		out = AnimationUtils.loadAnimation(context, android.R.anim.fade_out);
		in = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
		viewData = (LinearLayout) body.findViewById(R.id.data_view);
		viewMap = (RelativeLayout) body.findViewById(R.id.map_view);
		resultView = (LinearLayout) body.findViewById(R.id.map_dt);
		resInfor = (TextView) body.findViewById(R.id.infor);
		TextView emptyView = new TextView(getApplicationContext());
		lstData = (PullToRefreshListView) body.findViewById(R.id.lst_data);
		emptyView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		emptyView.setText("Không có dữ liệu");
		emptyView.setTextSize(20);
		emptyView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
		lstData.setAdapter(null);
		lstData.setEmptyView(emptyView);
		layListThongTinToaDo = new LayListThongTinToaDoTask();
		spnQuanHuyen.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				QuanHuyen quanhuyen = (QuanHuyen) parent.getAdapter().getItem(position);
				if (quanhuyen != null) {
					thongTinTimToaNha.setsQUANHUYEN_ID(quanhuyen.getID_QUAN_HUYEN());
					duAnTask = new GetDuAnTask();
					duAnTask.addParam("sMA_TINHTP", Util.ttp.getMa_Ttp());
					duAnTask.addParam("sQUANHUYEN_ID", quanhuyen.getID_QUAN_HUYEN());
					onExecuteToServer(true, null, duAnTask);
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
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
		map.setOnMarkerDragListener(new OnMarkerDragListener() {

			@Override
			public void onMarkerDragStart(Marker arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onMarkerDragEnd(final Marker arg0) {
				// TODO Auto-generated method stub
				taiNguyenMang = new TAI_NGUYEN_MANG();
				taiNguyenMang.setID_HETHONGGOC(2);
				taiNguyenMang.setDC_DOITUONG(editedToaNha.getTEN_TOA_NHA());
				taiNguyenMang.setID_DOITUONG(editedToaNha.getID_TOA_NHA());
				taiNguyenMang.setID_LOAIDOITUONG(550);
				final ThongTinToaDo thongTinToaDo = new ThongTinToaDo();
				thongTinToaDo.setLatitude("" + arg0.getPosition().latitude);
				thongTinToaDo.setLongtitude("" + arg0.getPosition().longitude);
				thongTinToaDo.setGhichu("Đẩy lên từ VNPT 360");
				Object temp = taiNguyenMang;
				AlertDialog.Builder alert = new AlertDialog.Builder(context);
				alert.setTitle("Thông báo!");
				alert.setMessage("Cập nhật tọa độ cho tòa nhà : " + arg0.getTitle());
				alert.setNegativeButton("Quay lại", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						arg0.remove();
						editedToaNha.creatMarker(map, false, false);
						viewMap.setVisibility(View.VISIBLE);
						viewData.setVisibility(View.GONE);

					}
				});
				alert.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						capNhatToaDoToaNhaTask = new CapNhatDoiTuongTaiNguyenMangTask();
						capNhatToaDoToaNhaTask.addParam("obj", taiNguyenMang);
						capNhatToaDoToaNhaTask.addParam("toaDo", thongTinToaDo);
						capNhatToaDoToaNhaTask.addParam("lstAnh", "");
						capNhatToaDoToaNhaTask.addParam("nguoiCapNhat", Util.userName);
						capNhatToaDoToaNhaTask.addParam("maTtp", Util.ttp.getMa_Ttp());
						onExecuteToServer(true, null, capNhatToaDoToaNhaTask);
					}
				});
				alert.show();

			}

			@Override
			public void onMarkerDrag(Marker arg0) {
				// TODO Auto-generated method stub

			}
		});
		spnDuAn.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				DuAn duan = (DuAn) parent.getAdapter().getItem(position);
				if (duan != null) {
					thongTinTimToaNha.setsDUAN_ID(duan.getID_DU_AN());
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		spnDoiTac.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				DoiTac doitac = (DoiTac) parent.getAdapter().getItem(position);
				if (doitac != null) {
					thongTinTimToaNha.setsDOITAC_ID(doitac.getID_DOI_TAC());

				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		lstData.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				editedToaNha = (ThongTinToaNha) parent.getAdapter().getItem(position);
				if (editedToaNha.getMaker() != null) {
					Marker mk = editedToaNha.getMaker();
					mk.setDraggable(true);
					mk.showInfoWindow();
					viewData.setVisibility(View.GONE);
					viewMap.setVisibility(View.VISIBLE);
					Toast.makeText(context, "Di nhẹ icon để cập nhật dữ liệu", Toast.LENGTH_SHORT).show();
					map.moveCamera(CameraUpdateFactory.newLatLngZoom(mk.getPosition(), 14));

				} else {
					Toast.makeText(context, "Không có dữ liệu tọa độ. Con trỏ sẽ được đưa về vị trí hợp lý nhất.",
							Toast.LENGTH_SHORT).show();
					GetToaDoFromAddressTask getToaDoTask = new GetToaDoFromAddressTask();
					getToaDoTask.setDiaChi(editedToaNha.getQUAN_HUYEN() + " " + Util.ttp.getTen_ttpho());
					getToaDoTask.SetConText(context);
					onExecuteToServer(true, null, getToaDoTask);
				}

			}
		});
		android.view.ViewGroup.LayoutParams param = resultView.getLayoutParams();
		hei = param.height;
		setListenerToRootView();
		onExecuteToServer(true, null, doiTacTask, quanHuyenTask);
		disableShortCut();

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

	@SuppressWarnings("unchecked")
	@Override
	public void onsucces(WebProtocol ws) {
		// TODO Auto-generated method stub

		super.onsucces(ws);
		if (ws.equals(doiTacTask)) {
			SoapObject lstDoitac = (SoapObject) ((SoapObject) doiTacTask.result).getProperty("Result");
			SoapObject lstQuanhuyen = (SoapObject) ((SoapObject) quanHuyenTask.result).getProperty("Result");
			int len = 0;
			len = lstDoitac.getPropertyCount();
			lstDoiTacObj.add(new DoiTac());
			for (int i = 0; i < len; i++) {
				SoapObject soapDuAn = (SoapObject) lstDoitac.getProperty(i);
				DoiTac doitac = new DoiTac();
				Util.GetObjectFromSoapObject(doitac, soapDuAn);
				lstDoiTacObj.add(doitac);

			}
			len = lstQuanhuyen.getPropertyCount();
			lstQuanHuyenObj.add(new QuanHuyen());
			for (int i = 0; i < len; i++) {
				SoapObject soapQuanHuyen = (SoapObject) lstQuanhuyen.getProperty(i);
				QuanHuyen quanhuyen = new QuanHuyen();
				Util.GetObjectFromSoapObject(quanhuyen, soapQuanHuyen);
				lstQuanHuyenObj.add(quanhuyen);

			}

			BaseSpinnerAdapter<QuanHuyen> QuanHuyenAdapter = new BaseSpinnerAdapter<QuanHuyen>(context,
					android.R.layout.simple_spinner_dropdown_item, lstQuanHuyenObj);
			BaseSpinnerAdapter<DoiTac> DoiTacAdapter = new BaseSpinnerAdapter<DoiTac>(context,
					android.R.layout.simple_spinner_dropdown_item, lstDoiTacObj);

			DoiTacAdapter.name = "TEN_DOI_TAC";
			QuanHuyenAdapter.name = "TEN_QUAN_HUYEN";
			spnDoiTac.setAdapter(DoiTacAdapter);
			spnQuanHuyen.setAdapter(QuanHuyenAdapter);

		}
		if (ws.equals(duAnTask)) {

			try {
				SoapObject lstDuAn = (SoapObject) ((SoapObject) duAnTask.result).getProperty("Result");
				int len = 0;
				len = lstDuAn.getPropertyCount();
				lstDuAnObj = new ArrayList<DuAn>();
				lstDuAnObj.add(new DuAn());
				for (int i = 0; i < len; i++) {
					SoapObject soapDuAn = (SoapObject) lstDuAn.getProperty(i);
					DuAn duAn = new DuAn();
					Util.GetObjectFromSoapObject(duAn, soapDuAn);
					lstDuAnObj.add(duAn);
					BaseSpinnerAdapter<DuAn> DuAnAdapter = new BaseSpinnerAdapter<DuAn>(context,
							android.R.layout.simple_spinner_dropdown_item, lstDuAnObj);

				}
				BaseSpinnerAdapter<DuAn> DuAnAdapter = new BaseSpinnerAdapter<DuAn>(context,
						android.R.layout.simple_spinner_dropdown_item, lstDuAnObj);
				DuAnAdapter.name = "TEN_DU_AN";
				spnDuAn.setAdapter(DuAnAdapter);
			} catch (Exception e) {
				// TODO: handle exception
				lstDuAnObj = new ArrayList<DuAn>();
				BaseSpinnerAdapter<DuAn> DuAnAdapter = new BaseSpinnerAdapter<DuAn>(context,
						android.R.layout.simple_spinner_dropdown_item, lstDuAnObj);
				DuAnAdapter.name = "TEN_DU_AN";
				spnDuAn.setAdapter(DuAnAdapter);
			}

		}
		if (ws.equals(getToaNhaTask)) {

			SoapObject arrString = (SoapObject) getToaNhaTask.result;
			String result = arrString.getPrimitivePropertyAsString("IsError");
			String mess = "Không tìm thấy dữ liệu phù hợp!";
			mess = arrString.getPrimitivePropertyAsString("Message");
			if (mess.length() == 0)
				mess = "Không tìm thấy dữ liệu phù hợp!";
			if (result.toLowerCase().equals("true")) {
				TextView emptyView = new TextView(getApplicationContext());
				emptyView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				emptyView.setText("Không có dữ liệu");
				emptyView.setTextSize(20);
				emptyView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
				lstData.setAdapter(null);
				lstData.setEmptyView(emptyView);
				Util.showAlert(context, mess);
				return;
			}
			SoapObject lstThongTinToaNha = (SoapObject) arrString.getProperty("Result");
			int len = lstThongTinToaNha.getPropertyCount();
			lstThongTinToaNhas = new ArrayList<ThongTinToaNha>();
			ArrayList<DoiTuong> lstDoiTuong = new ArrayList<DoiTuong>();
			for (int i = 0; i < len; i++) {
				SoapObject soapToaNha = (SoapObject) lstThongTinToaNha.getProperty(i);
				ThongTinToaNha toanha = new ThongTinToaNha();
				Util.GetObjectFromSoapObject(toanha, soapToaNha);
				lstThongTinToaNhas.add(toanha);
				DoiTuong doituong = new DoiTuong();
				doituong.setID_DOITUONG(toanha.getID_TOA_NHA());
				doituong.setID_HETHONGGOC(2);
				doituong.setID_LOAIDOITUONG(550);
				lstDoiTuong.add(doituong);
			}
			layListThongTinToaDo = new LayListThongTinToaDoTask();
			Gson gson = new Gson();
			String data = gson.toJson(lstDoiTuong);
			layListThongTinToaDo.addParam("lstObjJson", data);
			layListThongTinToaDo.addParam("isGetImages", false);
			layListThongTinToaDo.addParam("maTtp", Util.ttp.getMa_Ttp());
			onExecuteToServer(true, null, layListThongTinToaDo);

		}
		if (ws.equals(layListThongTinToaDo)) {
			HashMap<Integer, ThongTinToaDo> hashMap = (HashMap<Integer, ThongTinToaDo>) layListThongTinToaDo
					.getResult();
			coToaDo = 0;
			for (ThongTinToaNha toanha : lstThongTinToaNhas) {
				ThongTinToaDo toado = hashMap.get(toanha.getID_TOA_NHA());
				if (toado != null) {
					coToaDo++;
					toanha.setPOS_LAT(toado.getLatitude());
					toanha.setPOS_LONG(toado.getLongtitude());
					Marker mk = toanha.creatMarker(map, false, false);
					viewMap.setVisibility(View.VISIBLE);
					viewData.setVisibility(View.GONE);
					toanha.setMaker(mk);
				}

			}
			resInfor.setText("Có " + lstThongTinToaNhas.size() + " kết quả phù hợp. Trong đó có " + coToaDo
					+ " bản ghi có tọa độ");
			BaseListViewAdapter<ThongTinToaNha> toanhaAdapter = new BaseListViewAdapter<ThongTinToaNha>(context,
					lstThongTinToaNhas, null, false);
			lstData.setAdapter(toanhaAdapter);
		}
		if (ws instanceof GetToaDoFromAddressTask) {
			Address add = (Address) ws.getResult();
			myLocation = map.getMyLocation();
			if (add != null) {
				editedToaNha.setPOS_LAT("" + add.getLatitude());
				editedToaNha.setPOS_LONG("" + add.getLongitude());
			}
			if (add == null && myLocation == null) {
				Util.confirmTurnOnGPS(context);
			}
			if (myLocation != null && add == null) {
				editedToaNha.setPOS_LAT("" + myLocation.getLatitude());
				editedToaNha.setPOS_LONG("" + myLocation.getLongitude());
			}
			if (lastMaker != null)
				lastMaker.remove();
			lastMaker = editedToaNha.creatMarker(map, false, false);
			viewMap.setVisibility(View.VISIBLE);
			viewData.setVisibility(View.GONE);

			if (lastMaker != null) {
				lastMaker.setDraggable(true);
				lastMaker.showInfoWindow();
				map.moveCamera(CameraUpdateFactory.newLatLngZoom(lastMaker.getPosition(), 14));
				viewMap.setVisibility(View.VISIBLE);
				viewData.setVisibility(View.GONE);
			}

		}
		if (ws.equals(capNhatToaDoToaNhaTask)) {
			Util.showAlert(context, capNhatToaDoToaNhaTask.getResult().toString());
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.bttOK:
			map.clear();
			coToaDo = 0;
			resInfor.setText("");
			thongTinTimToaNha.setsMA_TINHTP(Util.ttp.getMa_Ttp());
			thongTinTimToaNha.setsDIACHITOANHA(txtDiaChi.getText().toString());
			thongTinTimToaNha.setsTENTOANHA(txtTen.getText().toString());
			thongTinTimToaNha.setsSoLuongHienThi(30);
			getToaNhaTask = new GetToaNhaTask();
			getToaNhaTask.addParam("sSoLuongHienThi", thongTinTimToaNha.getsSoLuongHienThi());
			getToaNhaTask.addParam("sMA_TINHTP", thongTinTimToaNha.getsMA_TINHTP());
			getToaNhaTask.addParam("sTENTOANHA", thongTinTimToaNha.getsTENTOANHA());
			getToaNhaTask.addParam("sDIACHITOANHA", thongTinTimToaNha.getsDIACHITOANHA());
			getToaNhaTask.addParam("sDOITAC_ID", thongTinTimToaNha.getsDOITAC_ID());
			getToaNhaTask.addParam("sQUANHUYEN_ID", thongTinTimToaNha.getsQUANHUYEN_ID());
			getToaNhaTask.addParam("sDUAN_ID", thongTinTimToaNha.getsDUAN_ID());
			onExecuteToServer(true, null, getToaNhaTask);

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

	// public Marker creatMaker(ThongTinToaNha thongtintoanha, boolean
	// setLonLat, boolean setDrag, boolean showInfor) {
	// double lat = 0;
	// double lon = 0;
	// Marker m = null;
	// try {
	// lat = Double.parseDouble(thongtintoanha.getPOS_LAT());
	// lon = Double.parseDouble(thongtintoanha.getPOS_LONG());
	// LatLng latLng = new LatLng(lat, lon);
	// MarkerOptions mko = new MarkerOptions();
	// mko.position(latLng);
	//
	// mko.anchor(0.5f, 1.0f);
	// BitmapDescriptor ketcuoiIcon;
	// ketcuoiIcon = BitmapDescriptorFactory.fromResource(R.drawable.het);
	// mko.icon(ketcuoiIcon);
	// mko.title(thongtintoanha.getTEN_TOA_NHA());
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
	// // Toast.makeText(context, "Chưa có dữ liệu vị trí",
	// // Toast.LENGTH_SHORT).show();
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
