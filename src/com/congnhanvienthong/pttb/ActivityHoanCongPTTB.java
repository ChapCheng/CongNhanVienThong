package com.congnhanvienthong.pttb;

import java.util.Vector;

import org.ksoap2.serialization.SoapObject;

import com.congnhanvienthong.ActivityBaseToDisplay;
import com.congnhanvienthong.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import congnhanvienthong.entity.pttb.ChiTietThueBao;
import congnhanvienthong.entity.pttb.ThongTinThueBao;
import control.Util;
import view.YourMapFragment;
import webservice.GetViTriTask;
import webservice.WebProtocol;
import webservice.pttb.CapNhatHoanCongTask;
import webservice.pttb.CapNhatViTriPTTBTask;

public class ActivityHoanCongPTTB extends ActivityBaseToDisplay {
	ProgressDialog progressDialog;
	public ThongTinThueBao thongtin_thuebao;
	String accoung_khach_hang;
	Button bttHoanCong, bttKetThuc;
	public static EditText nguoicaidat;
	TextView MaTH, MaTB, TenTB, DiaChiTB, LoaiHinhTB, ngaycaidat, Hdtb_id;
	String sMaTH = "", sMaTB = "", sTenTB = "", sDiaChiTB = "", sLoaiHinhTB = "", sHdtb_ID, sNguoiCaiDat = "",
			sNgayCaiDat = "", ID_THUEBAO = "", MA_DICHVU = "", MA_QUANHUYEN = "", MA_PHUONGXA = "";
	int check = 0;
	String date;
	CapNhatHoanCongTask capnhat;
	public static String longtitude, lattitude;
	private Intent pictureActionIntent = null;
	private final int CAMERA_PICTURE = 1;
	Bitmap bitmap = null;
	boolean coNgayHoanCong = true;
	private GoogleMap map;
	GetViTriTask getViTriTask;
	CheckBox checkBox;
	boolean checkCapNhatViTri = false;
	// String maADSL;
	ScrollView scrollViewMain;
	ChiTietThueBao oChiTietThueBao;

	/*****************************************/

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setBodyLayout(R.layout.activity_hoan_cong_pttb_all);
		setFootLayout(R.layout.foot_pttb_khoaphieuhoancong);
		context = ActivityHoanCongPTTB.this;
		checkBox = (CheckBox) body.findViewById(R.id.check_co_toado);
		setHeader("Khóa phiếu hoàn công các dịch vụ");
		bttHoanCong = (Button) foot.findViewById(R.id.bttHoanCong);
		bttKetThuc = (Button) foot.findViewById(R.id.bttKetThuc);
		Intent i = getIntent();
		thongtin_thuebao = (ThongTinThueBao) i.getSerializableExtra("thongTinThueBao");
		Object obj = thongtin_thuebao;
		// maADSL = i.getStringExtra("maADSL");
		setData(thongtin_thuebao);
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

		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		map.getUiSettings().setZoomControlsEnabled(true);
		map.getUiSettings().setCompassEnabled(true);
		map.getUiSettings().setMyLocationButtonEnabled(true);
		map.getUiSettings().setAllGesturesEnabled(true);
		map.setTrafficEnabled(true);
		map.getUiSettings().setTiltGesturesEnabled(true);
		map.setMyLocationEnabled(true);
		map.setOnMarkerDragListener(new OnMarkerDragListener() {

			@Override
			public void onMarkerDragStart(Marker arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onMarkerDragEnd(Marker arg0) {
				// TODO Auto-generated method stub
				if (oChiTietThueBao != null) {
					oChiTietThueBao.latitude = "" + arg0.getPosition().latitude;
					oChiTietThueBao.longtitude = "" + arg0.getPosition().longitude;
				}
			}

			@Override
			public void onMarkerDrag(Marker arg0) {
				// TODO Auto-generated method stub

			}
		});
		DS_Click_Button();
		getViTriTask = new GetViTriTask();
		getViTriTask.addParam("ma_dichvu", thongtin_thuebao.getMA_THUE_BAO());
		getViTriTask.addParam("id_hethong", "2");
		Task taskGetTask = new Task();
		taskGetTask.execute(getViTriTask);

	}

	/***************** danh sach button click ************************/
	public void DS_Click_Button() {
		bttHoanCong.setOnClickListener(this);
		bttKetThuc.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.bttHoanCong:
			sNgayCaiDat = ngaycaidat.getText().toString();
			sNguoiCaiDat = nguoicaidat.getText().toString();
			sNgayCaiDat = sNgayCaiDat.trim();
			sNguoiCaiDat = sNguoiCaiDat.trim();
			sHdtb_ID = Hdtb_id.getText().toString();
			capnhat = new CapNhatHoanCongTask();
			capnhat.addParam("sHDTB_ID", sHdtb_ID);
			capnhat.addParam("sNGAY_HOAN_CONG", sNgayCaiDat);

			if (sNguoiCaiDat.equals("")) {
				capnhat.addParam("sNGUOI_HOAN_CONG", "toanpv");
			} else {
				capnhat.addParam("sNGUOI_HOAN_CONG", sNguoiCaiDat);
			}
			capnhat.addParam("sLOAI", "1");
			if (thongtin_thuebao.getNGAY_CAI_DAT() == null || thongtin_thuebao.getNGAY_CAI_DAT().equals("")) {
				Util.showAlert(context, "Chưa có ngày hoàn công!");
				coNgayHoanCong = false;
				break;

			}
			if (!coNgayHoanCong) {
				Util.showAlert(context, "Nhấp vào ngày tháng để set ngày hoàn công!");
				break;

			}

			Location vitriThueBao = map.getMyLocation();
			capnhat.addParam("sLONG", vitriThueBao.getLongitude());
			capnhat.addParam("sLAT", vitriThueBao.getLatitude());
			capnhat.addParam("sMA_TINHTP", Util.ttp.getMa_Ttp());
			if (thongtin_thuebao.getNGUOI_CAI_DAT() == null || thongtin_thuebao.getNGUOI_CAI_DAT().equals("")) {
				thongtin_thuebao.setNGUOI_CAI_DAT(Util.userName);
			}
			Task task = new Task();
			CapNhatViTriPTTBTask capNhatViTriPTTBTask = new CapNhatViTriPTTBTask();

			boolean checkCapNhatViTri = checkBox.isChecked();

			if (checkCapNhatViTri) {
				if (bitmap == null) {
					Util.showAlert(context, "Chưa chụp ảnh đối tượng!");
					break;

				}
				capNhatViTriPTTBTask.addParam("eidLoaiDoiTuong", "MegaVNN");
				capNhatViTriPTTBTask.addParam("user", Util.userName);
				capNhatViTriPTTBTask.addParam("oThongTinThueBao", thongtin_thuebao);
				oChiTietThueBao = new ChiTietThueBao();
				oChiTietThueBao.dichvucc = "2";
				oChiTietThueBao.image = Util.BitMapToString(bitmap);
				oChiTietThueBao.latitude = String.valueOf(vitriThueBao.getLatitude());
				oChiTietThueBao.longtitude = String.valueOf(vitriThueBao.getLongitude());
				capNhatViTriPTTBTask.addParam("oChiTietThueBao", oChiTietThueBao);
				task.execute(capnhat);
				task.execute(capNhatViTriPTTBTask);
			}
			if (!checkCapNhatViTri) {
				task.execute(capnhat);
			}
			break;
		case R.id.bttKetThuc:
			// startDialog();
			pictureActionIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(pictureActionIntent, CAMERA_PICTURE);
			break;
		}
	}

	public void setData(ThongTinThueBao tttb) {
		MaTH = (TextView) body.findViewById(R.id.tvMaTH);
		Hdtb_id = (TextView) body.findViewById(R.id.tvHDTB_ID);
		MaTB = (TextView) body.findViewById(R.id.tvMaTB);
		TenTB = (TextView) body.findViewById(R.id.tvTenTB);
		DiaChiTB = (TextView) body.findViewById(R.id.tvDiaChiTB);
		LoaiHinhTB = (TextView) body.findViewById(R.id.tvLoaihinhTB);
		ngaycaidat = (TextView) body.findViewById(R.id.edNgayCaiDat);
		nguoicaidat = (EditText) body.findViewById(R.id.edNguoiCaiDat);
		// ----------------
		MaTH.setText(tttb.getMA_TC());
		Hdtb_id.setText(tttb.getHDTB_ID());
		MaTB.setText(tttb.getMA_THUE_BAO());
		TenTB.setText(tttb.getTEN_THUE_BAO());
		DiaChiTB.setText(tttb.getDIA_CHI_THUE_BAO());
		LoaiHinhTB.setText(tttb.getLOAIHINH_THUE_BAO());
		ngaycaidat.setText(tttb.getNGAY_CAI_DAT());
		nguoicaidat.setText(tttb.getNGUOI_CAI_DAT());

		ActivityHoanCongPTTB.this.ngaycaidat.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				final Dialog dateDialog = new Dialog(context);
				dateDialog.setContentView(R.layout.dialog_date_time);
				dateDialog.setCancelable(true);
				dateDialog.show();

				Button getTime = (Button) dateDialog.findViewById(R.id.get_time_btt);
				Button thoat = (Button) dateDialog.findViewById(R.id.exit_time_btt);
				thoat.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dateDialog.dismiss();

					}
				});
				getTime.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						DatePicker datePicker = (DatePicker) dateDialog.findViewById(R.id.date_picker);
						TimePicker timePicker = (TimePicker) dateDialog.findViewById(R.id.time_picker);
						date = datePicker.getDayOfMonth() + "/" + (datePicker.getMonth() + 1) + "/"
								+ datePicker.getYear();
						date = date + " " + timePicker.getCurrentHour() + ":" + timePicker.getCurrentMinute();
						ngaycaidat.setText(date);
						thongtin_thuebao.setNGAY_CAI_DAT(date);
						coNgayHoanCong = true;
						dateDialog.dismiss();

					}
				});

			}
		});

	}

	@SuppressWarnings("unchecked")
	@Override
	public void onsucces(WebProtocol ws) {
		super.onsucces(ws);
		if (ws.equals(capnhat)) {
			Util.showAlert(context, capnhat.result.toString());
		}

		if (ws.equals(getViTriTask)) {
			Vector<Object> thongBaoViTri = (Vector<Object>) getViTriTask.result;
			SoapObject thongTin = (SoapObject) thongBaoViTri.get(1);

			String tonTai = thongTin.getPropertyAsString("TONTAI");

			if (tonTai.equals("1")) {
				float lon = Float.valueOf(thongTin.getPropertyAsString("LONGITUDE"));
				float lat = Float.valueOf(thongTin.getPropertyAsString("LATITUDE"));
				checkBox.setClickable(true);
				checkBox.setChecked(false);
				map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 14));
				// map.addMarker(new MarkerOptions())
				MarkerOptions oMarkerOptions = new MarkerOptions().draggable(true).anchor(0.5f, 1.0f).title("me");
				LatLng me = new LatLng(lat, lon);
				oMarkerOptions.position(me);
				Marker mk = map.addMarker(oMarkerOptions);
				map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
				Toast.makeText(context,
						"Mã dịch vụ này đã có dữ liệu về vị trí. Nếu muốn cập nhật lại vị trí, vui lòng bấm vào ô tích để cập nhật!",
						Toast.LENGTH_LONG).show();
				checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							Toast.makeText(context, "Cho phép cập nhật vị trí!", Toast.LENGTH_SHORT).show();
							checkCapNhatViTri = true;
							bttKetThuc.setEnabled(true);
						} else {
							Toast.makeText(context, "Không cho phép cập nhật vị trí!", Toast.LENGTH_SHORT).show();
							checkCapNhatViTri = false;
							bttKetThuc.setEnabled(false);
						}

					}
				});

			}
			if (tonTai.equals("0")) {
				checkBox.setChecked(true);
				checkCapNhatViTri = true;
				bttKetThuc.setEnabled(true);
				map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(21.0293, 105.8522), 14));
				map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

			}

		}
		if (ws instanceof CapNhatViTriPTTBTask) {
			Util.showAlert(context, ((CapNhatViTriPTTBTask) ws).result.toString());
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		try {
			super.onActivityResult(requestCode, resultCode, data);

			if (requestCode == CAMERA_PICTURE) {
				if (data.getExtras() != null) {
					bitmap = (Bitmap) data.getExtras().get("data");
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

}