package com.congnhanvienthong.dhsc;

import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

import com.congnhanvienthong.ActivityBaseToDisplay;
import com.congnhanvienthong.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import adapter.BaseSpinnerAdapter;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import congnhanvienthong.entity.dhsc.LoaiDichVu;
import control.Util;
import view.YourMapFragment;
import webservice.GetViTriTask;
import webservice.WebProtocol;
import webservice.dhsc.TraCuuTTPTask;

public class ACtivityTraCuuTTP extends ActivityBaseToDisplay {
	Spinner spnLoaiDichVu, spnNoiDungHong;
	LoaiDichVu loaiDVu;
	TraCuuTTPTask traCuuTTPTask;
	GetViTriTask getvitriTask;
	Button bttOK;
	TextView ketquaText;
	EditText txtInput;
	private GoogleMap map;
	ScrollView mScrollView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setBodyLayout(R.layout.activity_tracuu_ttp);
		setHeader("Tra Cứu");
		final YourMapFragment fragment = (YourMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapo);
		mScrollView = (ScrollView) body.findViewById(R.id.scroll_main);
		map = fragment.getMap();

		map.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {

			@Override
			public void onMapLoaded() {
				// TODO Auto-generated method stub
				map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(21.0293, 105.8522), 14));
				map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
				map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
				map.getUiSettings().setZoomControlsEnabled(true);
				map.getUiSettings().setCompassEnabled(true);
				map.getUiSettings().setMyLocationButtonEnabled(true);
				map.getUiSettings().setAllGesturesEnabled(true);
				map.setTrafficEnabled(true);
				map.getUiSettings().setTiltGesturesEnabled(true);
				// Util.setListViewHeightBasedOnChildren(listView);

				map.setMyLocationEnabled(true);
				try {
					WifiManager wiff;
					wiff = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
					WifiInfo wifiInfo = wiff.getConnectionInfo();
					int speedMbps = wifiInfo.getLinkSpeed();
					System.out.println(speedMbps);
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});
		// map.moveCamera(CameraUpdateFactory.);
		fragment.setListener(new YourMapFragment.OnTouchListener() {
			@Override
			public void onTouch() {
				mScrollView.requestDisallowInterceptTouchEvent(true);
			}
		});
		context = ACtivityTraCuuTTP.this;
		spnLoaiDichVu = (Spinner) body.findViewById(R.id.loaiDichVu);
		setFootLayout(R.layout.foot_tracuu);
		ArrayList<LoaiDichVu> lstLoaiDichVU = (ArrayList<LoaiDichVu>) Util.listLoaiDichVu;
		BaseSpinnerAdapter<LoaiDichVu> adapterLoaiDichVu = new BaseSpinnerAdapter<LoaiDichVu>(context,
				android.R.layout.simple_spinner_dropdown_item, lstLoaiDichVU);
		adapterLoaiDichVu.name = "TenLoaiDichVu";
		spnLoaiDichVu.setAdapter(adapterLoaiDichVu);
		txtInput = (EditText) body.findViewById(R.id.inputData);
		spnLoaiDichVu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				loaiDVu = (LoaiDichVu) arg0.getAdapter().getItem(arg2);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		bttOK = (Button) foot.findViewById(R.id.bttOK);
		ketquaText = (TextView) body.findViewById(R.id.ketqua);
		bttOK.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				traCuuTTPTask = new TraCuuTTPTask();
				traCuuTTPTask.addParam("maDichVu", txtInput.getText().toString());
				traCuuTTPTask.addParam("loaiDichVuId", loaiDVu.getIdLoaiDichvu());
				traCuuTTPTask.addParam("userName", Util.userName);
				traCuuTTPTask.addParam("tinhThanhPhoId", Util.ttp.getId_ttpho());
				if (Util.ttp.getId_ttpho().equals("1")) {
					traCuuTTPTask.removeParam("userName");
					traCuuTTPTask.removeParam("tinhThanhPhoId");
				}
				getvitriTask = new GetViTriTask();
				getvitriTask.addParam("ma_dichvu", txtInput.getText().toString());
				getvitriTask.addParam("id_hethong", 2);
				onExecuteToServer(true, "Tra cứu thông tin ?", traCuuTTPTask, getvitriTask);
			}
		});

	}

	@Override
	public void onsucces(WebProtocol task) {
		// TODO Auto-generated method stub
		super.onsucces(task);
		if (task.equals(traCuuTTPTask)) {
			ketquaText.setText("");
			try {
				Util.setTextFromObject(ketquaText, traCuuTTPTask.getResult());
				MarkerOptions makerSearch;
				makerSearch = new MarkerOptions().draggable(true);
				LatLng lat = (LatLng) getvitriTask.getResult();
				if (lat != null) {
					makerSearch.position(lat);
					BitmapDescriptor ketcuoiIcon = BitmapDescriptorFactory.fromResource(R.drawable.my_location);
					makerSearch.icon(ketcuoiIcon);
					makerSearch.anchor(0.5f, 1.0f);
					map.addMarker(makerSearch);
				}
			} catch (Exception e) {
				// TODO: handle exception
				if (e instanceof TimeoutException) {
					Util.showAlert(context, "Thời gian truy vấn quá lâu. Vui lòng kiểm tra lại mạng của bạn!");
				} else

					Util.showAlert(context, e.getMessage());
			}

		}

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
	}
}
