package com.congnhanvienthong.gtacs;

import java.util.ArrayList;
import java.util.HashSet;

import org.ksoap2.serialization.SoapObject;

import webservice.BaseTask;
import webservice.WebProtocol;
import webservice.dhsc.GetCurrentBTSTask;
//import webservice.dhsc.GetCurrentBTSTask;
import webservice.dhsc.LayToaDoThietBiTask;
import android.app.AlertDialog;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.view.KeyEvent;
import android.widget.Toast;

import com.congnhanvienthong.ActivityBaseToDisplay;
import com.congnhanvienthong.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import congnhanvienthong.entity.dhsc.Device;
import control.CustomMenu;
import control.CustomMenu.OnMenuItemSelectedListener;
import control.CustomMenuItem;
import control.GPSTracker;
import control.Util;

public class ActivityMap extends ActivityBaseToDisplay implements OnMenuItemSelectedListener {
	static LatLng BTS, me;
	private GoogleMap map;
	Location location;
	Circle circle;
	boolean flag = true, isBlink;
	Marker pointMe, current_BTS;
	HashSet<LatLng> taphopToaDo;
	HashSet<Marker> cacDiemBTS;
	int cuongDo;
	String ma_BTS_current = "khong tim duoc", thongTinSong = "null";
	String cuongDoSong;
	GetCurrentBTSTask getCurrentBTSTask;
	LayToaDoThietBiTask layToaDoThietBi;
	// thinp
	ArrayList<Device> devices = new ArrayList<Device>();
	MyPhoneStateListener MyListener;
	private CustomMenu mMenu;
	public static final int MENU_ITEM_1 = 1;
	public static final int MENU_ITEM_2 = 2;
	public static final int MENU_ITEM_3 = 3;
	public static final int MENU_ITEM_4 = 4;
	TelephonyManager telephonyManager;

	// Timer markertimer = new Timer();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setBodyLayout(R.layout.activity_map);
		setHeader("Bản đồ");
		context = ActivityMap.this;
		MyListener = new MyPhoneStateListener();
		telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		telephonyManager.listen(MyListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
		map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
		map.setMyLocationEnabled(true);
		mMenu = new CustomMenu(this, this, getLayoutInflater());
		mMenu.setHideOnSelect(true);
		mMenu.setItemsPerLineInPortraitOrientation(4);
		mMenu.setItemsPerLineInLandscapeOrientation(8);
		loadMenuItems();
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(21.0293, 105.8522), 14));
		map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		map.getUiSettings().setZoomControlsEnabled(true);
		map.getUiSettings().setCompassEnabled(true);
		map.getUiSettings().setMyLocationButtonEnabled(true);
		map.getUiSettings().setAllGesturesEnabled(true);
		map.setTrafficEnabled(true);
		map.getUiSettings().setTiltGesturesEnabled(true);
		taphopToaDo = new HashSet<LatLng>();
		cacDiemBTS = new HashSet<Marker>();
		// nút lấy tọa độ của người dùng

		// Bắt sự kiện di chuyển vị trí của người dùng
		map.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
			public void onMarkerDragStart(Marker marker) {
				// TODO Auto-generated method stub
				// Khi bắt đầu di chuyển, báo cho người dùng biết. Xóa các maker
				// như trạm BTS, vòng tròn bán kính 500m vẽ trước đó
				Toast.makeText(ActivityMap.this, "Bắt đầu", Toast.LENGTH_SHORT).show();
				if (circle != null) {
					circle.remove();
				}
				if (!cacDiemBTS.isEmpty()) {
					for (Marker temp : cacDiemBTS) {
						temp.remove();

					}
				}

			}

			public void onMarkerDragEnd(Marker marker) {
				// Vẽ lại vòng tròn bán kính 500m quanh vị trí người dùng
				me = marker.getPosition();
				circle = map.addCircle(new CircleOptions().center(me).radius(500).strokeWidth(1));
			}

			public void onMarkerDrag(Marker marker) {

			}
		});
		map.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {

			public boolean onMyLocationButtonClick() {
				// TODO Auto-generated method stub
				try {
					location = map.getMyLocation();
					float lat_me = (float) location.getLatitude();
					float lon_me = (float) location.getLongitude();

					me = new LatLng(lat_me, lon_me);
					if (circle != null) {
						circle.remove();
					}
					if (pointMe != null) {
						pointMe.remove();
					}
					if (!cacDiemBTS.isEmpty()) {
						for (Marker marker : cacDiemBTS) {
							marker.remove();

						}
					}
					// Tọa maker trên bản đổ, vẽ một vòng tròn bán kính 500m
					// quanh địa điểm chỉ định. Cho phép di chuyển vị trí
					// đánh dấu
					MarkerOptions oMarkerOptions = new MarkerOptions().draggable(true).anchor(0.5f, 1.0f).title("me");
					oMarkerOptions.position(me);
					pointMe = map.addMarker(oMarkerOptions);
					circle = map.addCircle(new CircleOptions().center(me).radius(500).strokeWidth(1));
					map.moveCamera(CameraUpdateFactory.newLatLngZoom(me, 15));

					GsmCellLocation cellLocation = (GsmCellLocation) telephonyManager.getCellLocation();
					int lcid = cellLocation.getCid();
					int cid = lcid & 0xffff;
					int lac = cellLocation.getLac();
					String input = lac + "-" + cid;

					thongTinSong = "\n Cid            : " + cid + "\n Lac            : " + lac;
					getCurrentBTSTask = new GetCurrentBTSTask();
					getCurrentBTSTask.input.add(input);
					Task task = new Task();
					task.execute(getCurrentBTSTask);
				} catch (Exception e) {
					// TODO: handle exception
					ma_BTS_current = "Không lưu trạm BTS này trong CSDL";
				}

				return false;
			}
		});

		// Phần này để bắt sự kiện khi tìm kiếm trạm BTS theo mã từ module tra
		// cứu
		try {
			float lon = Float.valueOf(getIntent().getStringExtra("lon"));
			float lat = Float.valueOf(getIntent().getStringExtra("lat"));
			BTS = Util.DoiLongLat(lat, lon);
			map.addMarker(new MarkerOptions().draggable(true).anchor(0.5f, 1.0f).position(BTS).title("BTS"));
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(BTS, 15));

		} catch (NullPointerException ex) {
			// khi có sự kiện này có nghĩa là không phải vào từ menu tra cứu,
			// set flag để sử lý sự kiện back press
			flag = false;
		} catch (Exception e) {
			// TODO: handle exception
			Util.showAlert(ActivityMap.this,
					"Cập nhật bản mới nhất các tiện ích google trên máy để dùng chức năng bản đồ");

		}

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (flag) {
			super.onBackPressed();
		}

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			doMenu();
			return true; // always eat it!
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * Load up our menu.
	 */
	private void loadMenuItems() {
		// This is kind of a tedious way to load up the menu items.
		// Am sure there is room for improvement.
		ArrayList<CustomMenuItem> menuItems = new ArrayList<CustomMenuItem>();
		CustomMenuItem cmi = new CustomMenuItem();
		cmi.setCaption("Vị trí");
		cmi.setImageResourceId(R.drawable.icon1);
		cmi.setId(MENU_ITEM_1);
		menuItems.add(cmi);
		cmi = new CustomMenuItem();
		cmi.setCaption("Quét BTS");
		cmi.setImageResourceId(R.drawable.icon2);
		cmi.setId(MENU_ITEM_2);
		menuItems.add(cmi);
		cmi = new CustomMenuItem();
		cmi.setCaption("BTS hiện tại");
		cmi.setImageResourceId(R.drawable.icon6);
		cmi.setId(MENU_ITEM_3);
		menuItems.add(cmi);
		cmi = new CustomMenuItem();
		cmi.setCaption("Kiểu B.Đồ");
		cmi.setImageResourceId(R.drawable.chance_map);
		cmi.setId(MENU_ITEM_4);
		menuItems.add(cmi);
		if (!mMenu.isShowing())
			try {
				mMenu.setMenuItems(menuItems);
			} catch (Exception e) {
				AlertDialog.Builder alert = new AlertDialog.Builder(this);
				alert.setTitle("Có lỗi !");
				alert.setMessage(e.getMessage());
				alert.show();
			}
	}

	/**
	 * Toggle our menu on user pressing the menu key.
	 */
	private void doMenu() {
		if (mMenu.isShowing()) {
			mMenu.hide();
		} else {
			mMenu.show(findViewById(R.id.body));
		}
	}

	/**
	 * For the demo just toast the item selected.
	 */
	public void MenuItemSelectedEvent(CustomMenuItem selection) {
		int choice = selection.getId();
		switch (choice) {
		// Tìm vị trí người dùng
		case 1:
			GPSTracker gps = new GPSTracker(ActivityMap.this);
			if (gps != null) {
				try {
					location = gps.getLocation();
					float lat_me = (float) location.getLatitude();
					float lon_me = (float) location.getLongitude();
					me = new LatLng(lat_me, lon_me);
					if (circle != null) {
						circle.remove();
					}
					if (pointMe != null) {
						pointMe.remove();
					}
					if (!cacDiemBTS.isEmpty()) {
						for (Marker marker : cacDiemBTS) {
							marker.remove();

						}
					}
					// Tọa maker trên bản đổ, vẽ một vòng tròn bán kính 500m
					// quanh địa điểm chỉ định. Cho phép di chuyển vị trí
					// đánh dấu
					MarkerOptions oMarkerOptions = new MarkerOptions().draggable(true).anchor(0.5f, 1.0f).title("me");
					oMarkerOptions.position(me);
					pointMe = map.addMarker(oMarkerOptions);
					circle = map.addCircle(new CircleOptions().center(me).radius(500).strokeWidth(1));
					map.moveCamera(CameraUpdateFactory.newLatLngZoom(me, 15));

					GsmCellLocation cellLocation = (GsmCellLocation) telephonyManager.getCellLocation();
					int lcid = cellLocation.getCid();
					int cid = lcid & 0xffff;
					int lac = cellLocation.getLac();
					String input = lac + "-" + cid;

					thongTinSong = "\n Cid            : " + cid + "\n Lac            : " + lac;
					getCurrentBTSTask = new GetCurrentBTSTask();
					getCurrentBTSTask.input.add(input);
					Task task = new Task();
					task.execute(getCurrentBTSTask);

				} catch (Exception e) {
					// TODO: handle exception
				}

			}

			break;
		case 2:
			if (me != null) {
				if (!cacDiemBTS.isEmpty()) {
					for (Marker marker : cacDiemBTS) {
						marker.remove();

					}
				}
				// Gọi ws, lấy 5 trạm BTS trong bán kính 500m
				layToaDoThietBi = new LayToaDoThietBiTask();
				layToaDoThietBi.context = ActivityMap.this;
				layToaDoThietBi.input.add("BTS");
				layToaDoThietBi.input.add(String.valueOf(me.latitude));
				layToaDoThietBi.input.add(String.valueOf(me.longitude));
				layToaDoThietBi.input.add("500");
				layToaDoThietBi.input.add("15");
				Task task = new Task();
				task.execute(layToaDoThietBi);

			}

			else {

			}
			break;
		case 3:
			if (!ma_BTS_current.equals("khong tim duoc")) {
				MyListener = new MyPhoneStateListener();
				telephonyManager.listen(MyListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
				Toast.makeText(context, "Thông tin sóng :  \nMã trạm BTS: " + ma_BTS_current + thongTinSong
						+ "\n Cường độ sóng : " + cuongDo + " dBm", Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(context, "Chọn vị trí trước khi kiểm tra sóng!", Toast.LENGTH_LONG).show();
			}

			break;
		case 4:

			if (map.getMapType() == GoogleMap.MAP_TYPE_NORMAL)
				map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
			else if (map.getMapType() == GoogleMap.MAP_TYPE_SATELLITE)
				map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

			break;

		default:
			break;
		}

	}

	private class MyPhoneStateListener extends PhoneStateListener {
		/*
		 * Get the Signal strength from the provider, each tiome there is an
		 * update
		 */
		@Override
		public void onSignalStrengthsChanged(SignalStrength signalStrength) {
			// if ( signalStrength == TelephonyManager.NETWORK_TYPE_LTE){
			//
			// dbm = Integer.parseInt(parts[8])*2-113;
			//
			// }

			if (signalStrength.isGsm()) {
				int a = signalStrength.getGsmSignalStrength(); // asunumber is
																// an int

				cuongDo = -113 + (2 * a); // conversion to dbm (int)
				// Toast.makeText(context, cuongDo, Toast.LENGTH_LONG).show();
			}
		}

	};

	@Override
	public void onsucces(WebProtocol task) {
		// TODO Auto-generated method stub
		super.onsucces(task);
		if (task.equals(getCurrentBTSTask)) {
			SoapObject BTS_temp = (SoapObject) getCurrentBTSTask.result;
			ma_BTS_current = BTS_temp.getPropertyAsString(0);

		}
		if (task.equals(layToaDoThietBi)) {
			SoapObject listDevice = (SoapObject) getCurrentBTSTask.result;
			try {
				int length = listDevice.getPropertyCount();
				while (taphopToaDo.size() > 0) {
					taphopToaDo.clear();
					while (!devices.isEmpty()) {
						devices.remove(0);
					}

				}
				for (int i = 0; i < length; i++) {
					// Lấy dữ liệu từng trạm
					SoapObject devive = (SoapObject) listDevice.getProperty(i);
					LatLng tempLatLng = Util.DoiLongLat(Double.valueOf(devive.getPropertyAsString("Latitude")),
							Double.valueOf(devive.getPropertyAsString("Longitude")));
					// add tọa độ vào 1 tập hợp- để loại bỏ các tọa độ
					// trùng nhau
					taphopToaDo.add(tempLatLng);
					Device temp = new Device(devive.getPropertyAsString("DeviceName"), tempLatLng,
							devive.getPropertyAsString("Range"));
					// add các trạm vào trong list kết quả
					devices.add(temp);

				}
				for (LatLng toado : taphopToaDo) {
					// Các trạm cùng tọa độ được add trùng nhau, trên
					// hiển thị là tên của các trạm, cách nhau dấu ;
					MarkerOptions mko = new MarkerOptions();
					mko.position(toado);

					mko.anchor(0.5f, 1.0f);
					BitmapDescriptor iconOther = BitmapDescriptorFactory.fromResource(R.drawable.bts_icon_grey);
					BitmapDescriptor iconVNP = BitmapDescriptorFactory.fromResource(R.drawable.bts_icon_green);
					mko.title(Util.GetNameDevice(devices, toado));
					BitmapDescriptor currentBTS = BitmapDescriptorFactory.fromResource(R.drawable.bts_icon_red);

					if (mko.getTitle().contains(ma_BTS_current)) {
						mko.icon(currentBTS);
					} else {
						if (mko.getTitle().contains("VNP")) {

							mko.icon(iconVNP);
						} else {
							mko.icon(iconOther);
						}
					}

					Marker temp = map.addMarker(mko);

					cacDiemBTS.add(temp);

				}
				current_BTS = Util.GetCurentBTS(cacDiemBTS, ma_BTS_current);
				if (current_BTS == null) {
					Util.showAlert(context, "Trạm BTS đang kết nối chưa cập nhật trong CSDL");
					ma_BTS_current = "Chưa cập nhật";
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				Toast.makeText(ActivityMap.this, "Không có kết quả tìm kiếm", Toast.LENGTH_SHORT).show();
				Util.showAlert(context, e.toString());
			}

		}

	}

}
