package com.congnhanvienthong.pttb;

import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import com.congnhanvienthong.ActivityBaseToDisplay;
import com.congnhanvienthong.R;
import com.google.android.gms.internal.fs;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import adapter.BaseListViewAdapter;
import adapter.BaseSpinnerAdapter;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import congnhanvienthong.entity.gtacs.ThongTinKetCuoi;
import congnhanvienthong.entity.pttb.DoiTac;
import congnhanvienthong.entity.pttb.DuAn;
import congnhanvienthong.entity.pttb.QuanHuyen;
import congnhanvienthong.entity.pttb.ThongTinTimKiemToaNha;
import congnhanvienthong.entity.pttb.ThongTinToaNha;
import control.Util;
import view.YourMapFragment;
import webservice.BaseTask;
import webservice.WebProtocol;
import webservice.pttb.GetDoiTacTask;
import webservice.pttb.GetDuAnTask;
import webservice.pttb.GetQuanHuyenTask;
import webservice.pttb.GetToaNhaTask;

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
	LinearLayout viewMap;
	LinearLayout viewData, resultView;
	Animation out, in;
	PullToRefreshListView lstData;
	TextView resInfor;
	int hei;
	GoogleMap map;
	Location myLocation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// setBodyLayout(R.layout.activity_tracuutoanha);

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
		doiTacTask.input.add(Util.ttp.getMa_Ttp());
		quanHuyenTask.input.add(Util.ttp.getMa_Ttp());
		btnData = (Button) body.findViewById(R.id.btn_data);
		btnMap = (Button) body.findViewById(R.id.btn_map);
		btnData.setOnClickListener(this);
		btnMap.setOnClickListener(this);
		out = AnimationUtils.loadAnimation(context, android.R.anim.fade_out);
		in = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
		viewData = (LinearLayout) body.findViewById(R.id.data_view);
		viewMap = (LinearLayout) body.findViewById(R.id.map_view);
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
		spnQuanHuyen.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				QuanHuyen quanhuyen = (QuanHuyen) parent.getAdapter().getItem(position);
				if (quanhuyen != null) {
					thongTinTimToaNha.setsQUANHUYEN_ID(quanhuyen.getID_QUAN_HUYEN());
					duAnTask = new GetDuAnTask();
					duAnTask.input.add(Util.ttp.getMa_Ttp());
					duAnTask.input.add(quanhuyen.getID_QUAN_HUYEN());
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
			for (int i = 0; i < len; i++) {
				SoapObject soapToaNha = (SoapObject) lstThongTinToaNha.getProperty(i);
				ThongTinToaNha toanha = new ThongTinToaNha();
				Util.GetObjectFromSoapObject(toanha, soapToaNha);
				lstThongTinToaNhas.add(toanha);
				creatMaker(toanha, false, false, false);
			}
			resInfor.setText("Có " + lstThongTinToaNhas.size() + " kết quả phù hợp");
			BaseListViewAdapter<ThongTinToaNha> toanhaAdapter = new BaseListViewAdapter<ThongTinToaNha>(context,
					lstThongTinToaNhas, null, false);
			lstData.setAdapter(toanhaAdapter);

		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.bttOK:
			thongTinTimToaNha.setsMA_TINHTP(Util.ttp.getMa_Ttp());
			thongTinTimToaNha.setsDIACHITOANHA(txtDiaChi.getText().toString());
			thongTinTimToaNha.setsTENTOANHA(txtTen.getText().toString());
			thongTinTimToaNha.setsSoLuongHienThi(30);
			getToaNhaTask = new GetToaNhaTask();
			getToaNhaTask.input.add(thongTinTimToaNha.getsSoLuongHienThi());
			getToaNhaTask.input.add(thongTinTimToaNha.getsMA_TINHTP());
			getToaNhaTask.input.add(thongTinTimToaNha.getsTENTOANHA());
			getToaNhaTask.input.add(thongTinTimToaNha.getsDIACHITOANHA());
			getToaNhaTask.input.add(thongTinTimToaNha.getsDOITAC_ID());
			getToaNhaTask.input.add(thongTinTimToaNha.getsQUANHUYEN_ID());
			getToaNhaTask.input.add(thongTinTimToaNha.getsDUAN_ID());
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

	public Marker creatMaker(ThongTinToaNha thongtintoanha, boolean setLonLat, boolean setDrag, boolean showInfor) {
		double lat = 0;
		double lon = 0;
		try {
			lat = Double.parseDouble(thongtintoanha.getPOS_LAT());
			lon = Double.parseDouble(thongtintoanha.getPOS_LONG());

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

		mko.anchor(0.5f, 1.0f);
		BitmapDescriptor ketcuoiIcon;
		ketcuoiIcon = BitmapDescriptorFactory.fromResource(R.drawable.het);
		mko.icon(ketcuoiIcon);
		mko.title(thongtintoanha.getTEN_TOA_NHA());

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
