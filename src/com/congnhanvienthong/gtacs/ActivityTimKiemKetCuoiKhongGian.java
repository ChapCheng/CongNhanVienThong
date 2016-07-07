package com.congnhanvienthong.gtacs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import org.ksoap2.serialization.SoapObject;

import com.congnhanvienthong.ActivityBaseToDisplay;
import com.congnhanvienthong.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import adapter.BaseListViewAdapter;
import adapter.BaseSpinnerAdapter;
import adapter.ListAddressAdapter;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
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
import control.Util;
import webservice.WebProtocol;
import webservice.gtcas.GetRankTask;

public class ActivityTimKiemKetCuoiKhongGian extends ActivityBaseToDisplay {
	Spinner spnLoaiDoiTuong, spnKhoangCach;
	BaseSpinnerAdapter<LoaiDoiTuong> adapter;
	Button bttOk, bttMap, bttData, bttTimDiaChi;
	String maDoiTuong, khoangCach;
	GetRankTask getRankTask;
	GoogleMap map;
	ArrayList<KetCuoi> listKetCuoiObj;
	Location myLocation;
	BaseListViewAdapter<KetCuoi> adapterKetcuoi;
	AutoCompleteTextView danhDachKetCuoi;
	HashSet<LatLng> setLaLngs;
	ScrollView scrollViewMain;
	HashSet<Marker> cacDiemBTS;
	ArrayList<Device> arrayListDevices;
	LinearLayout viewMap, viewData, viewListAdd;
	Animation out, in;
	PullToRefreshListView lstData;
	TextView txtInfor;
	HashSet<MarkerOptions> setMarkers;
	Address add;
	AutoCompleteTextView txtAddress;
	MarkerOptions makerSearch;
	ListView lstAddres;
	Button btnTest;
	EditText txtName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setBodyLayout(R.layout.quet_ketcuoi_bankinh);
		setFootLayout(R.layout.foot_tracuu);
		setMarkers = new HashSet<MarkerOptions>();
		setHeader("Tìm kiếm vị trí các kết cuối");
		disableShortCut();
		bttOk = (Button) foot.findViewById(R.id.bttOK);
		bttMap = (Button) body.findViewById(R.id.btn_map);
		bttData = (Button) body.findViewById(R.id.btn_data);
		bttTimDiaChi = (Button) body.findViewById(R.id.btn_tim_diachi);
		txtAddress = (AutoCompleteTextView) body.findViewById(R.id.txt_address);
		txtName = (EditText) body.findViewById(R.id.txt_name_ketcuoi);
		viewData = (LinearLayout) body.findViewById(R.id.data_view);
		viewMap = (LinearLayout) body.findViewById(R.id.map_view);
		viewListAdd = (LinearLayout) body.findViewById(R.id.lst_add_view);
		out = AnimationUtils.loadAnimation(context, android.R.anim.fade_out);
		lstAddres = (ListView) body.findViewById(R.id.lst_address_view);
		in = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
		spnLoaiDoiTuong = (Spinner) body.findViewById(R.id.loai_doi_tuong);
		spnKhoangCach = (Spinner) body.findViewById(R.id.khoang_cach);
		danhDachKetCuoi = (AutoCompleteTextView) body.findViewById(R.id.edit);
		lstData = (PullToRefreshListView) body.findViewById(R.id.lst_data);
		txtInfor = (TextView) body.findViewById(R.id.infor);
		TextView emptyView = new TextView(getApplicationContext());
		emptyView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		emptyView.setText("Không có dữ liệu");
		emptyView.setTextSize(20);
		emptyView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
		lstData.setAdapter(null);
		lstData.setEmptyView(emptyView);

		ArrayList<LoaiDoiTuong> arrLoaiDoiTuongs = new ArrayList<LoaiDoiTuong>();
		arrLoaiDoiTuongs.add(new LoaiDoiTuong("17000", "Splitter", "SA"));
		arrLoaiDoiTuongs.add(new LoaiDoiTuong("10800", "Măng xông đồng", "M"));
		arrLoaiDoiTuongs.add(new LoaiDoiTuong("14100", "Tổng đài", "D"));
		arrLoaiDoiTuongs.add(new LoaiDoiTuong("10300", "Tủ/hộp/nhà cáp", "H"));
		arrLoaiDoiTuongs.add(new LoaiDoiTuong("10900", "Măng xông quang", "M"));
		arrLoaiDoiTuongs.add(new LoaiDoiTuong("15000", "ODF", "O"));
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

		lstAddres.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				try {
					Address add = (Address) parent.getItemAtPosition(position);
					LatLng latLng = new LatLng(add.getLatitude(), add.getLongitude());
					setLocationToSearch(latLng);
					viewListAdd.setVisibility(view.GONE);
					map.getUiSettings().setAllGesturesEnabled(true);
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});
		adapter = new BaseSpinnerAdapter<LoaiDoiTuong>(context, android.R.layout.simple_dropdown_item_1line,
				arrLoaiDoiTuongs);
		adapter.name = "tenHienThi";
		spnLoaiDoiTuong.setAdapter(adapter);
		spnLoaiDoiTuong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				bttOk.setVisibility(View.VISIBLE);
				// TODO Auto-generated method stub
				LoaiDoiTuong doituong = (LoaiDoiTuong) arg0.getAdapter().getItem(arg2);
				maDoiTuong = doituong.getIdLoaiDoiTuong();
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		spnKhoangCach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				bttOk.setVisibility(View.VISIBLE);
				// TODO Auto-generated method stub
				khoangCach = arg0.getAdapter().getItem(arg2).toString();
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});

		bttOk.setOnClickListener(this);
		bttMap.setOnClickListener(this);
		bttData.setOnClickListener(this);
		bttTimDiaChi.setOnClickListener(this);
		myLocation = map.getMyLocation();
		if (myLocation != null) {

			makerSearch = new MarkerOptions().draggable(true);
			LatLng lat = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
			makerSearch.position(lat);
			BitmapDescriptor ketcuoiIcon = BitmapDescriptorFactory.fromResource(R.drawable.my_location);
			makerSearch.icon(ketcuoiIcon);
			makerSearch.anchor(0.5f, 1.0f);
			map.addMarker(makerSearch);
		}
		lstData.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				KetCuoi ttkc = (KetCuoi) parent.getItemAtPosition(position);
				Marker mk = ttkc.getMarker();
				mk.remove();
				Marker newMK = ttkc.creatMaker(map, true, true);

				viewMap.startAnimation(in);
				viewData.startAnimation(out);
				viewMap.setVisibility(View.VISIBLE);
				viewData.setVisibility(View.GONE);

			}
		});
		map.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {

			@Override
			public void onMarkerDragStart(Marker arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onMarkerDragEnd(Marker arg0) {
				// TODO Auto-generated method stub
				makerSearch.position(arg0.getPosition());
			}

			@Override
			public void onMarkerDrag(Marker arg0) {
				// TODO Auto-generated method stub

			}
		});
		map.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {

			@Override
			public boolean onMyLocationButtonClick() {
				// TODO Auto-generated method stub
				myLocation = map.getMyLocation();
				map.clear();
				Util.confirmTurnOnGPS(context);
				if (myLocation != null) {

					makerSearch = new MarkerOptions().draggable(true);
					LatLng lat = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
					makerSearch.position(lat);
					BitmapDescriptor ketcuoiIcon = BitmapDescriptorFactory.fromResource(R.drawable.my_location);
					makerSearch.icon(ketcuoiIcon);
					makerSearch.anchor(0.5f, 1.0f);
					map.addMarker(makerSearch);
				}
				return false;
			}
		});
	}

	@Override
	public void onsucces(WebProtocol task) {
		// TODO Auto-generated method stub
		if (task.equals(getRankTask)) {
			map.clear();
			SoapObject listKetCuoi = (SoapObject) getRankTask.result;
			int length = listKetCuoi.getPropertyCount();

			// khi load mới thì remove toàn bộ dữ liệu cũ đi
			listKetCuoiObj = new ArrayList<KetCuoi>();
			cacDiemBTS = new HashSet<Marker>();
			setLaLngs = new HashSet<LatLng>();
			arrayListDevices = new ArrayList<Device>();
			// chạy vòng lặp, tạo dữ liệu và đổ vào list để sử dụng
			for (int i = 0; i < length; i++) {
				int con = 0;
				KetCuoi temp = null;
				try {
					SoapObject tienTrienSuaObj = (SoapObject) listKetCuoi.getProperty(i);
					temp = new KetCuoi();
					Util.GetObjectFromSoapObject(temp, tienTrienSuaObj);

					con = Integer.parseInt(temp.getO_TOTAL_SIZE()) - Integer.parseInt(temp.getIN_USED())
							- Integer.parseInt(temp.getFAULT_PORT());

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					temp = new KetCuoi();
				}
				temp.setThongTinCong(
						temp.getO_TOTAL_SIZE() + "/" + temp.getIN_USED() + "/" + temp.getFAULT_PORT() + "/" + con);

				temp.creatMaker(map, false, false);
				listKetCuoiObj.add(temp);
			}

			lstData.setMode(Mode.PULL_FROM_START);
			lstData.onRefreshComplete();
			lstData.setAdapter(new BaseListViewAdapter<KetCuoi>(context, listKetCuoiObj, null, false));
			txtInfor.setText("Hiển thị " + listKetCuoiObj.size() + "/" + length + " bản ghi!");
			map.addCircle(new CircleOptions().center(makerSearch.getPosition()).radius(Integer.valueOf(khoangCach))
					.strokeWidth(1));
			map.addMarker(makerSearch);

		}
	}

	public class GetToaDo extends AsyncTask<String, String, ArrayList<Address>> {
		ProgressDialog dialog;

		@Override
		protected ArrayList<Address> doInBackground(String... params) {
			// TODO Auto-generated method stub
			Address addr = null;
			ArrayList<Address> res = new ArrayList<Address>();

			try {
				res = (ArrayList<Address>) new Geocoder(context).getFromLocationName(params[0].toString(), 15);
				if (res.size() > 0) {
					addr = res.get(0);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return res;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog = new ProgressDialog(context);
			dialog.setMessage("Đang xử lý");
			dialog.setIndeterminate(false);
			dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			dialog.setCancelable(false);
			dialog.show();
		}

		@Override
		protected void onPostExecute(ArrayList<Address> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			try {
				dialog.dismiss();
				bttOk.setEnabled(true);

				if (result != null && result.size() > 0) {

					txtAddress.setText("");
					viewListAdd.setVisibility(View.VISIBLE);
					map.getUiSettings().setAllGesturesEnabled(false);
					makerSearch = null;
					ListAddressAdapter lstAdapter = new ListAddressAdapter(context, result);
					lstAddres.setAdapter(lstAdapter);

				} else {
					Toast.makeText(context, "Không tìm được vị trí.Mời thử lại!", Toast.LENGTH_SHORT).show();
				}
			} catch (Exception e) {
				// TODO: handle exception
				Util.showAlert(context, e.getMessage());
			}

		}
	};

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
	}

	protected void setLocationToSearch(LatLng latLng) {

		map.clear();
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));
		map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
		makerSearch = new MarkerOptions().draggable(true);
		makerSearch.position(latLng);
		BitmapDescriptor ketcuoiIcon = BitmapDescriptorFactory.fromResource(R.drawable.my_location);
		makerSearch.icon(ketcuoiIcon);
		makerSearch.anchor(0.5f, 1.0f);
		map.addMarker(makerSearch);

	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
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

		case R.id.bttOK:
			if (makerSearch != null) {
				getRankTask = new GetRankTask();
				getRankTask.addParam("p_Longitude", makerSearch.getPosition().longitude);
				getRankTask.addParam("p_Latitude", makerSearch.getPosition().latitude);
				getRankTask.addParam("p_MaxRange", khoangCach);
				getRankTask.addParam("p_Count", 30);
				getRankTask.addParam("p_Type", maDoiTuong);
				getRankTask.addParam("p_ObjName", txtName.getText().toString());
				getRankTask.addParam("id_Tinhthanh", Util.ttp.getId_ttpho());
				onExecuteToServer(true, "Tìm kiếm trên bản đổ ? ", getRankTask);
			} else {
				Toast.makeText(context, "Chưa xác định vị trí cần tìm kiếm", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.btn_tim_diachi:
			try {
				map.clear();
				lstData.setAdapter(null);
				txtInfor.setText("");
				bttOk.setEnabled(false);
				Util.hideKeyBoard(context, body);
				GetToaDo getToaDo = new GetToaDo();
				getToaDo.execute(txtAddress.getText().toString() + "" + Util.ttp.getTen_ttpho());
			} catch (Exception e) {
				// TODO: handle exception
				Util.showAlert(context, e.getMessage());
			}

			break;
		case R.id.btt_test_rightmenu:
			Toast.makeText(context, "ngon rồi", Toast.LENGTH_SHORT).show();
			break;

		}

	}

}
