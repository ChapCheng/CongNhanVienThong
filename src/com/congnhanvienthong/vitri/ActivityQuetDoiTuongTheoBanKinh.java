package com.congnhanvienthong.vitri;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.congnhanvienthong.ActivityBaseToDisplay;
import com.congnhanvienthong.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import adapter.BaseListViewAdapter;
import adapter.BaseSpinnerAdapter;
import adapter.ListAddressAdapter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import api.GetDSNhaTramByListAPI;
import api.GetToaDoFromAddressTask;
import congnhanvienthong.entity.gtacs.KetCuoi;
import congnhanvienthong.entity.gtacs.LoaiDoiTuong;
import congnhanvienthong.entity.nhatram.NhaTram;
import congnhanvienthong.entity.pttb.ThongTinToaNha;
import congnhanvienthong.entity.vitri.ThongTinToaDo;
import control.Util;
import webservice.WebProtocol;
import webservice.gtcas.GetRankTask;
import webservice.pttb.QuetToaNhaTheoDanhSachWS;
import webservice.vitri.QuetBanKinhWS;

public class ActivityQuetDoiTuongTheoBanKinh extends ActivityBaseToDisplay {
	HashMap<Object, Marker> hashMapViTri;
	Location searchLocation;
	RelativeLayout mapView;
	LinearLayout dataView, searchLocationView, viewListAdd;
	GetToaDoFromAddressTask getToaDoFromAddressTask;
	QuetBanKinhWS quetBanKinhWS;
	Button bttSearchLocation, bttOk, bttMap, bttData;
	EditText edtAddress;
	GoogleMap map;
	ListView lstAddres;
	AutoCompleteTextView txtAddress;
	MarkerOptions makerSearch;
	Spinner spnDoiTuong, spnKhoangCach, spnLoaiDoiTuongGTCAS;
	QuetToaNhaTheoDanhSachWS quetToaNhaWS;
	ArrayList<ThongTinToaNha> lstThongTinToaNha;
	PullToRefreshListView lstData;
	Animation out, in;
	HashMap<Integer, ThongTinToaDo> idToaDoMap;
	GetDSNhaTramByListAPI getNhaTramApi;
	GetRankTask getRankTask;
	LinearLayout gtcasView;
	private LoaiDoiTuong loaiDoiTuong;
	int coToaDo = 0;
	int totalSize = 0;
	TextView txtInfor;
	HashSet<Marker> setMarkerObject;
	EditText edtName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBodyLayout(R.layout.quetdoituong_theobankinh_activity);
		setFootLayout(R.layout.foot_tracuu);
		searchLocationView = (LinearLayout) body.findViewById(R.id.viewSearchAddress);
		searchLocationView.setVisibility(View.VISIBLE);
		dataView = (LinearLayout) body.findViewById(R.id.data_view);
		mapView = (RelativeLayout) body.findViewById(R.id.map_view);
		bttMap = (Button) body.findViewById(R.id.btn_map);
		bttOk = (Button) foot.findViewById(R.id.bttOK);
		bttData = (Button) body.findViewById(R.id.btn_data);
		bttOk.setOnClickListener(this);
		viewListAdd = (LinearLayout) body.findViewById(R.id.lst_add_view);
		bttSearchLocation = (Button) body.findViewById(R.id.btn_tim_diachi);
		txtAddress = (AutoCompleteTextView) body.findViewById(R.id.txt_address);
		bttData.setOnClickListener(this);
		bttMap.setOnClickListener(this);
		setMarkerObject = new HashSet<Marker>();
		setHeader("Tìm đối tượng theo bán kinh");
		bttSearchLocation.setOnClickListener(this);
		lstAddres = (ListView) body.findViewById(R.id.lst_address_view);
		spnDoiTuong = (Spinner) body.findViewById(R.id.spnLoaiDoiTuong);
		spnKhoangCach = (Spinner) body.findViewById(R.id.spnKhoangCach);
		map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapo)).getMap();
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(21.0293, 105.8522), 14));
		map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		map.getUiSettings().setZoomControlsEnabled(true);
		spnLoaiDoiTuongGTCAS = (Spinner) body.findViewById(R.id.spnLoaiKetcuoi);
		map.getUiSettings().setCompassEnabled(true);
		map.getUiSettings().setMyLocationButtonEnabled(true);
		map.getUiSettings().setAllGesturesEnabled(true);
		map.setTrafficEnabled(true);
		map.getUiSettings().setTiltGesturesEnabled(true);
		map.setMyLocationEnabled(true);
		lstData = (PullToRefreshListView) body.findViewById(R.id.lst_data);
		lstData.setAdapter(null);
		out = AnimationUtils.loadAnimation(context, android.R.anim.fade_out);
		in = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
		edtName = (EditText) body.findViewById(R.id.edtName);
		makerSearch = new MarkerOptions().draggable(true);
		BitmapDescriptor ketcuoiIcon = BitmapDescriptorFactory.fromResource(R.drawable.my_location);
		makerSearch.icon(ketcuoiIcon);
		makerSearch.anchor(0.5f, 1.0f);
		txtInfor = (TextView) body.findViewById(R.id.infor);
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
		map.setOnMyLocationButtonClickListener(new OnMyLocationButtonClickListener() {

			@Override
			public boolean onMyLocationButtonClick() {
				// TODO Auto-generated method stub
				Util.confirmTurnOnGPS(context);
				Location myLocation = map.getMyLocation();
				if (myLocation != null) {
					LatLng latLon = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
					setLocationToSearch(latLon);
				} else {
					Toast.makeText(context, "Chưa lấy được vị trí người dùng", Toast.LENGTH_SHORT).show();
				}
				return false;
			}
		});
		ArrayList<LoaiDoiTuong> arrLoaiDoiTuongs = new ArrayList<LoaiDoiTuong>();
		arrLoaiDoiTuongs.add(new LoaiDoiTuong("17000", "Splitter", "SA"));
		arrLoaiDoiTuongs.add(new LoaiDoiTuong("10800", "Măng xông đồng", "M"));
		arrLoaiDoiTuongs.add(new LoaiDoiTuong("14100", "Tổng đài", "D"));
		arrLoaiDoiTuongs.add(new LoaiDoiTuong("10300", "Tủ/hộp/nhà cáp", "H"));
		arrLoaiDoiTuongs.add(new LoaiDoiTuong("10900", "Măng xông quang", "M"));
		arrLoaiDoiTuongs.add(new LoaiDoiTuong("15000", "ODF", "O"));
		BaseSpinnerAdapter<LoaiDoiTuong> adapter = new BaseSpinnerAdapter<LoaiDoiTuong>(context,
				android.R.layout.simple_dropdown_item_1line, arrLoaiDoiTuongs);
		adapter.name = "tenHienThi";
		spnLoaiDoiTuongGTCAS.setAdapter(adapter);
		gtcasView = (LinearLayout) body.findViewById(R.id.viewGtcas);
		spnDoiTuong.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				if (position == 2)
					gtcasView.setVisibility(View.VISIBLE);
				else
					gtcasView.setVisibility(View.GONE);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		map.setOnMarkerDragListener(new OnMarkerDragListener() {

			@Override
			public void onMarkerDragStart(Marker arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onMarkerDragEnd(Marker arg0) {
				// TODO Auto-generated method stub
				makerSearch = makerSearch.position(arg0.getPosition());

			}

			@Override
			public void onMarkerDrag(Marker arg0) {
				// TODO Auto-generated method stub

			}
		});
		lstData.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Object obj = parent.getAdapter().getItem(position);
				Marker marker = null;
				if (obj instanceof KetCuoi) {
					marker = ((KetCuoi) obj).getMarker();
				}
				if (obj instanceof NhaTram) {
					marker = ((NhaTram) obj).getMaker();
				}
				if (obj instanceof ThongTinToaNha) {
					marker = ((ThongTinToaNha) obj).getMaker();
				}
				if (marker != null) {
					mapView.setVisibility(View.VISIBLE);
					dataView.setVisibility(View.GONE);
					marker.showInfoWindow();
				}

			}
		});

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_tim_diachi:
			getToaDoFromAddressTask = new GetToaDoFromAddressTask();
			getToaDoFromAddressTask.SetConText(context);
			String add = txtAddress.getText().toString();
			if (add.length() == 0) {
				txtAddress.setError("Chưa nhập địa chỉ");
				break;
			} else {
				getToaDoFromAddressTask.setDiaChi(add + " " + Util.ttp.getTen_ttpho());
				onExecuteToServer(true, null, getToaDoFromAddressTask);
				bttOk.setEnabled(false);
			}

			break;
		case R.id.bttOK:
			if (makerSearch == null || makerSearch.getPosition() == null)
				Util.showAlert(context, "Chưa có vị trí để quét!");
			else {

				GetListObjectByRange(makerSearch);
			}
			break;
		case R.id.btn_map:
			if (mapView.getVisibility() == View.GONE) {
				mapView.startAnimation(in);
				dataView.startAnimation(out);
				mapView.setVisibility(View.VISIBLE);
				dataView.setVisibility(View.GONE);
			}

			break;
		case R.id.btn_data:
			if (dataView.getVisibility() == View.GONE) {
				mapView.startAnimation(out);
				dataView.startAnimation(in);
				mapView.setVisibility(View.GONE);
				dataView.setVisibility(View.VISIBLE);
			}
			break;
		default:
			break;
		}
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public void onsucces(WebProtocol task) {
		// TODO Auto-generated method stub
		super.onsucces(task);
		if (task instanceof GetToaDoFromAddressTask) {
			try {
				bttOk.setEnabled(true);
				ArrayList<Address> lstAddress = (ArrayList<Address>) getToaDoFromAddressTask.getLstAddress();
				if (lstAddress != null && lstAddress.size() > 0) {

					txtAddress.setText("");
					viewListAdd.setVisibility(View.VISIBLE);
					map.getUiSettings().setAllGesturesEnabled(false);
					ListAddressAdapter lstAdapter = new ListAddressAdapter(context, lstAddress);
					lstAddres.setAdapter(lstAdapter);

				} else {
					Toast.makeText(context, "Không tìm được vị trí.Mời thử lại!", Toast.LENGTH_SHORT).show();
				}
			} catch (Exception e) {
				// TODO: handle exception
				Util.showAlert(context, e.getMessage());
			}
		}
		if (task.equals(quetBanKinhWS)) {
			idToaDoMap = (HashMap<Integer, ThongTinToaDo>) quetBanKinhWS.getResult();
			String param = "";

			System.out.println(param);
			if (spnDoiTuong.getSelectedItemPosition() == 0) {
				param = quetBanKinhWS.lstID.replace(" ", ",");
				quetToaNhaWS = new QuetToaNhaTheoDanhSachWS();
				quetToaNhaWS.addParam("sMA_TINHTP", Util.ttp.getMa_Ttp());
				quetToaNhaWS.addParam("sSoLuongHienThi", 30);
				quetToaNhaWS.addParam("sDS_ID_TOANHA", param);
				onExecuteToServer(true, null, quetToaNhaWS);
			}
			if (spnDoiTuong.getSelectedItemPosition() == 1) {

				param = quetBanKinhWS.lstID.replace(" ", "-");
				getNhaTramApi = new GetDSNhaTramByListAPI();
				getNhaTramApi.addParam("listIdNhaTram", param);
				onExecuteToServer(true, null, getNhaTramApi);
			}

		}
		if (task.equals(quetToaNhaWS)) {
			coToaDo = 0;
			totalSize = 0;
			lstData.setAdapter(null);

			if (setMarkerObject.size() > 0)
				for (Marker marker : setMarkerObject) {
					marker.remove();
				}
			txtInfor.setText("");
			ArrayList<ThongTinToaNha> lst = (ArrayList<ThongTinToaNha>) quetToaNhaWS.getResult();
			for (ThongTinToaNha toanha : lst) {
				ThongTinToaDo thongTinToaDo = idToaDoMap.get(toanha.getID_TOA_NHA());
				if (thongTinToaDo != null) {
					toanha.setPOS_LAT(thongTinToaDo.getLatitude());
					toanha.setPOS_LONG(thongTinToaDo.getLongtitude());
					Marker mk = toanha.creatMarker(map, false, false);
					setMarkerObject.add(mk);
					toanha.setMaker(mk);

				}
			}

			totalSize = lst.size();
			txtInfor.setText("Có " + totalSize + " bản ghi phù hợp");
			BaseListViewAdapter<ThongTinToaNha> adapter = new BaseListViewAdapter<ThongTinToaNha>(context, lst);
			lstData.setAdapter(adapter);

		}
		if (task.equals(getNhaTramApi)) {
			coToaDo = 0;
			totalSize = 0;
			lstData.setAdapter(null);
			if (setMarkerObject.size() > 0)
				for (Marker marker : setMarkerObject) {
					marker.remove();
				}
			txtInfor.setText("");
			ArrayList<NhaTram> lstNhaTrams = (ArrayList<NhaTram>) getNhaTramApi.getResult();

			for (NhaTram nhaTram : lstNhaTrams) {
				ThongTinToaDo thongTinToaDo = idToaDoMap.get(nhaTram.getId());
				if (thongTinToaDo != null) {

					nhaTram.setLatitude(thongTinToaDo.getLatitude());
					nhaTram.setLongitude(thongTinToaDo.getLongtitude());
					Marker mk = nhaTram.creatMarker(map, false, false);
					setMarkerObject.add(mk);
					nhaTram.setMaker(mk);
				}
			}

			totalSize = lstNhaTrams.size();
			txtInfor.setText("Có " + totalSize + " bản ghi phù hợp");
			BaseListViewAdapter<NhaTram> adapter = new BaseListViewAdapter<NhaTram>(context, lstNhaTrams);
			lstData.setAdapter(adapter);

		}
		if (task.equals(getRankTask)) {
			coToaDo = 0;
			totalSize = 0;
			lstData.setAdapter(null);
			for (Marker marker : setMarkerObject) {
				marker.remove();
			}
			txtInfor.setText("");
			ArrayList<KetCuoi> lstKetCuoi = (ArrayList<KetCuoi>) getRankTask.getResult();

			for (KetCuoi ketCuoi : lstKetCuoi) {
				Marker mk = ketCuoi.creatMaker(map, false, false);
				setMarkerObject.add(mk);
				ketCuoi.setMarker(mk);
			}

			totalSize = lstKetCuoi.size();
			txtInfor.setText("Có " + totalSize + " bản ghi phù hợp");
			BaseListViewAdapter<KetCuoi> adapter = new BaseListViewAdapter<KetCuoi>(context, lstKetCuoi);
			lstData.setAdapter(adapter);

		}
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

	protected void GetListObjectByRange(MarkerOptions marker) {
		if (marker == null)
			Util.showAlert(context, "Không xác định được vị trí tìm kiếm");
		else {
			quetBanKinhWS = new QuetBanKinhWS();
			switch (spnDoiTuong.getSelectedItemPosition()) {
			case 0:
				quetBanKinhWS.addParam("idLoaiDoiTuong", 550);
				quetBanKinhWS.addParam("longitude", marker.getPosition().longitude);
				quetBanKinhWS.addParam("latitude", marker.getPosition().latitude);
				quetBanKinhWS.addParam("radius", spnKhoangCach.getSelectedItem().toString());
				quetBanKinhWS.addParam("numRecord", 30);
				quetBanKinhWS.addParam("maTtp", Util.ttp.getMa_Ttp());
				onExecuteToServer(true, "Tìm kiếm theo bán kính ? ", quetBanKinhWS);
				break;

			case 1:
				quetBanKinhWS.addParam("idLoaiDoiTuong", 560);
				quetBanKinhWS.addParam("longitude", marker.getPosition().longitude);
				quetBanKinhWS.addParam("latitude", marker.getPosition().latitude);
				quetBanKinhWS.addParam("radius", spnKhoangCach.getSelectedItem().toString());
				quetBanKinhWS.addParam("numRecord", 30);
				quetBanKinhWS.addParam("maTtp", Util.ttp.getMa_Ttp());
				onExecuteToServer(true, "Tìm kiếm theo bán kính ? ", quetBanKinhWS);
				break;

			case 2:
				getRankTask = new GetRankTask();
				getRankTask.addParam("p_Longitude", marker.getPosition().longitude);
				getRankTask.addParam("p_Latitude", marker.getPosition().latitude);
				getRankTask.addParam("p_MaxRange", spnKhoangCach.getSelectedItem().toString());
				getRankTask.addParam("p_Count", 30);
				loaiDoiTuong = (LoaiDoiTuong) spnLoaiDoiTuongGTCAS.getSelectedItem();
				getRankTask.addParam("p_Type", loaiDoiTuong.getIdLoaiDoiTuong());
				getRankTask.addParam("p_ObjName", edtName.getText().toString());
				getRankTask.addParam("id_Tinhthanh", Util.ttp.getId_ttpho());
				onExecuteToServer(true, "Tìm kiếm trên bản đổ ? ", getRankTask);
				break;
			}

		}

	}

}
