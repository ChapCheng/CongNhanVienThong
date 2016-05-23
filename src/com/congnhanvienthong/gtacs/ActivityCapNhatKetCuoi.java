package com.congnhanvienthong.gtacs;

import java.util.ArrayList;
import java.util.Vector;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import view.YourMapFragment;
import webservice.BaseTask;
import webservice.GetViTriTask;
import webservice.WebProtocol;
import webservice.gtcas.CapNhatKetCuoiTask;
import webservice.gtcas.GetRoleTask;
import webservice.gtcas.LayListVeTinhTask;
import adapter.BaseSpinnerAdapter;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.congnhanvienthong.ActivityBaseToDisplay;
import com.congnhanvienthong.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.slidingmenu.lib.SlidingMenu;

import congnhanvienthong.entity.gtacs.LoaiDoiTuong;
import control.Util;

public class ActivityCapNhatKetCuoi extends ActivityBaseToDisplay {
	LayListVeTinhTask layListVeTinhTask;
	private GoogleMap map;
	Spinner spinnerLoaiDoiTuong, spinnerLoaiTuchua;
	String loaiDoiTuong;
	Button bttImage, bttCapNhatKetCuoi;
	private Intent pictureActionIntent = null;
	private final int CAMERA_PICTURE = 1;
	Bitmap bitmap = null;
	EditText maDichVu, diaChi, tenThueBao, maVT, sttSpliptter, tuchuaSpliptter;
	AutoCompleteTextView veTinh;
	String maVeTinh = "", maLoaiDoiTuong = "", tienTo = "";
	String madoituong;
	CapNhatKetCuoiTask capNhatKetCuoiTask;
	GetViTriTask getViTriTask;
	GetRoleTask getRoleTask;
	boolean flag = false;
	LinearLayout spliptterView, diachiView;
	String loaiTuChua = "";
	Marker pointMe;
	String maDoiTuongTruoc = "";
	private BaseSpinnerAdapter<LoaiDoiTuong> adapter;
	ScrollView scrollViewMain;
	Location location;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.context = ActivityCapNhatKetCuoi.this;
		setHeader("Cập nhật kết cuối");
		Task task = new Task();
		layListVeTinhTask = new LayListVeTinhTask();
		getRoleTask = new GetRoleTask();
		getRoleTask.input.add(Util.userName);
		layListVeTinhTask.context = ActivityCapNhatKetCuoi.this;
		layListVeTinhTask.input.add(Util.userName);
		task.execute(layListVeTinhTask, getRoleTask);
		enableRefresh();

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		// try {
		super.onActivityResult(requestCode, resultCode, data);
		try {
			if (requestCode == CAMERA_PICTURE) {
				if (data.getExtras() != null) {
					// here is the image from camera
					bitmap = (Bitmap) data.getExtras().get("data");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		// super.onBackPressed();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onsucces(WebProtocol task) {
		// TODO Auto-generated method stub
		super.onsucces(task);
		if (task.equals(layListVeTinhTask)) {
			String listRole = "";
			try {
				SoapPrimitive role = (SoapPrimitive) getRoleTask.result;
				listRole = role.toString();

			} catch (Exception e) {
				// TODO: handle exception
				Toast t = Toast.makeText(context, "Bạn không có quyền cập nhật các kết cuối.", Toast.LENGTH_LONG);
				t.setGravity(Gravity.CENTER, 0, 0);
				t.show();
			}

			ArrayList<String> lstVeTinh = new ArrayList<String>();
			SoapObject listString = (SoapObject) layListVeTinhTask.result;
			int length = listString.getPropertyCount();
			if (length == 0) {
				Toast t = Toast.makeText(context, "Bạn không có quyền cập nhật ở các vệ tinh.", Toast.LENGTH_LONG);
				t.setGravity(Gravity.CENTER, 0, 0);
				t.show();
				return;
			}

			setBodyLayout(R.layout.activity_gtcas_capnhatketcuoi);
			setFootLayout(R.layout.foot_cap_nhat_ket_cuoi);

			spinnerLoaiDoiTuong = (Spinner) body.findViewById(R.id.loai_ket_cuoi);

			map = ((YourMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapo)).getMap();

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
					map.setMyLocationEnabled(true);

				}
			});

			scrollViewMain = (ScrollView) body.findViewById(R.id.scroll_main);
			((YourMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapo))
					.setListener(new YourMapFragment.OnTouchListener() {

						@Override
						public void onTouch() {
							// TODO Auto-generated method stub
							scrollViewMain.requestDisallowInterceptTouchEvent(true);

						}
					});
			spinnerLoaiTuchua = (Spinner) body.findViewById(R.id.loai_tu_chua);
			sttSpliptter = (EditText) body.findViewById(R.id.stt_splitter);
			spliptterView = (LinearLayout) body.findViewById(R.id.splitter_view);
			tuchuaSpliptter = (EditText) body.findViewById(R.id.tuchua_splitter);
			diachiView = (LinearLayout) findViewById(R.id.diachi);
			spinnerLoaiTuchua.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

				public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					// TODO Auto-generated method stub
					switch (arg2) {
					case 0:
						loaiTuChua = "M";
						if (maLoaiDoiTuong.equals("SA")) {
							tuchuaSpliptter.setText("/" + loaiTuChua + "-" + maVeTinh);
						}
						if (maLoaiDoiTuong.equals("SB")) {
							tuchuaSpliptter.setText("/" + loaiTuChua + "-" + maVeTinh + "/");
						}
						break;
					case 1:
						loaiTuChua = "O";
						if (maLoaiDoiTuong.equals("SA")) {
							tuchuaSpliptter.setText("/" + loaiTuChua + "-" + maVeTinh);
						}
						if (maLoaiDoiTuong.equals("SB")) {
							tuchuaSpliptter.setText("/" + loaiTuChua + "-" + maVeTinh + "/");
						}
						break;
					}

				}

				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub

				}
			});

			ArrayList<LoaiDoiTuong> arrLoaiDoiTuongs = new ArrayList<LoaiDoiTuong>();
			if (listRole.contains("update_spliter")) {
				arrLoaiDoiTuongs.add(new LoaiDoiTuong("17000", "Splitter cấp 1", "SA"));
				arrLoaiDoiTuongs.add(new LoaiDoiTuong("17000", "Splitter cấp 2", "SB"));
			}
			if (listRole.contains("update_ketcuoi_dong")) {
				arrLoaiDoiTuongs.add(new LoaiDoiTuong("10800", "Măng xông đồng", "M"));
				arrLoaiDoiTuongs.add(new LoaiDoiTuong("14100", "Tổng đài", "D"));
				arrLoaiDoiTuongs.add(new LoaiDoiTuong("10300", "Tủ/hộp/nhà cáp", "H"));
			}
			if (listRole.contains("update_ketcuoi_quang")) {
				arrLoaiDoiTuongs.add(new LoaiDoiTuong("10900", "Măng xông quang", "M"));
				arrLoaiDoiTuongs.add(new LoaiDoiTuong("15000", "ODF", "O"));
			}

			adapter = new BaseSpinnerAdapter<LoaiDoiTuong>(context, android.R.layout.simple_spinner_dropdown_item,
					arrLoaiDoiTuongs);
			adapter.name = "tenHienThi";

			spinnerLoaiDoiTuong.setAdapter(adapter);

			spinnerLoaiDoiTuong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

				public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					// TODO Auto-generated method stub
					LoaiDoiTuong doituong = (LoaiDoiTuong) arg0.getAdapter().getItem(arg2);
					String id = doituong.getIdLoaiDoiTuong();
					int check = Integer.parseInt(id);
					switch (check) {
					case 14100:
						loaiDoiTuong = "14100";
						maLoaiDoiTuong = "D";
						sttSpliptter.setVisibility(View.GONE);
						maDichVu.setVisibility(View.VISIBLE);

						break;
					case 10300:
						loaiDoiTuong = "10300";
						maLoaiDoiTuong = "H";
						sttSpliptter.setVisibility(View.GONE);
						maDichVu.setVisibility(View.VISIBLE);

						break;
					case 10800:
						loaiDoiTuong = "10800";
						maLoaiDoiTuong = "M";
						sttSpliptter.setVisibility(View.GONE);
						maDichVu.setVisibility(View.VISIBLE);

						break;
					case 10900:
						loaiDoiTuong = "10900";
						maLoaiDoiTuong = "M";
						sttSpliptter.setVisibility(View.GONE);
						maDichVu.setVisibility(View.VISIBLE);

						break;
					case 17000:
						if (doituong.getMaDoiTuong().equals("SA")) {
							loaiDoiTuong = "17000";
							maLoaiDoiTuong = "SA";
							sttSpliptter.setVisibility(View.VISIBLE);
							maDichVu.setVisibility(View.GONE);
							tuchuaSpliptter.setText("/" + loaiTuChua + "-" + maVeTinh);
						}
						if (doituong.getMaDoiTuong().equals("SB")) {
							loaiDoiTuong = "17000";
							maLoaiDoiTuong = "SB";
							sttSpliptter.setVisibility(View.VISIBLE);
							maDichVu.setVisibility(View.VISIBLE);
							tuchuaSpliptter.setText("/" + loaiTuChua + "-" + maVeTinh + "/");
						}
						break;
					case 15000:
						loaiDoiTuong = "15000";
						maLoaiDoiTuong = "O";
						sttSpliptter.setVisibility(View.GONE);
						maDichVu.setVisibility(View.VISIBLE);

						break;

					}
					if (sttSpliptter.getVisibility() == View.GONE) {
						tienTo = maLoaiDoiTuong + "-" + maVeTinh + "/";
						maVT.setText(tienTo);
						spliptterView.setVisibility(View.GONE);
						tuchuaSpliptter.setVisibility(View.GONE);
					}
					if (sttSpliptter.getVisibility() == View.VISIBLE) {
						// tienTo = maLoaiDoiTuong + "-" + maVeTinh;
						maVT.setText(maLoaiDoiTuong);
						spliptterView.setVisibility(View.VISIBLE);
						tuchuaSpliptter.setVisibility(View.VISIBLE);

					}

				}

				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
				}
			});
			diaChi = (EditText) body.findViewById(R.id.dia_chi);
			maDichVu = (EditText) body.findViewById(R.id.ma_dv_hauto);
			tenThueBao = (EditText) body.findViewById(R.id.ten_doi_tuong);
			maVT = (EditText) body.findViewById(R.id.ma_dv_tiento);
			bttImage = (Button) foot.findViewById(R.id.take_image);

			bttImage.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (flag) {

						pictureActionIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
						startActivityForResult(pictureActionIntent, CAMERA_PICTURE);
					} else {
						if (checkValidate(false)) {
							Task task1 = new Task();
							getViTriTask = new GetViTriTask();
							// String maDT = "";
							if (sttSpliptter.getVisibility() == View.GONE) {
								String temp = tienTo + maDichVu.getText().toString();
								maDoiTuongTruoc = temp;
								getViTriTask.input.add(temp);
								getViTriTask.input.add("3");
								task1.execute(getViTriTask);
								// maDoiTuongTruoc = maDT;

							}
							if (sttSpliptter.getVisibility() == View.VISIBLE) {
								// maDT = tienTo +
								// maDichVu.getText().toString();
								if (maLoaiDoiTuong.equals("SA")) {
									String temp = maLoaiDoiTuong + sttSpliptter.getText().toString()
											+ tuchuaSpliptter.getText().toString();
									maDoiTuongTruoc = temp;
									getViTriTask.input.add(temp);
									getViTriTask.input.add("3");
									task1.execute(getViTriTask);

								}
								if (maLoaiDoiTuong.equals("SB")) {
									String temp = maLoaiDoiTuong + sttSpliptter.getText().toString()
											+ tuchuaSpliptter.getText().toString() + maDichVu.getText().toString();
									maDoiTuongTruoc = temp;
									getViTriTask.input.add(temp);
									getViTriTask.input.add("3");
									task1.execute(getViTriTask);

								}
							}

						}

					}

				}
			});
			bttCapNhatKetCuoi = (Button) foot.findViewById(R.id.capnhat_btt);
			bttCapNhatKetCuoi.setEnabled(flag);
			bttCapNhatKetCuoi.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub

					if (!Util.confirmTurnOnGPS(context))
						return;

					if (checkValidate(true)) {
						if (sttSpliptter.getVisibility() == View.GONE) {

							madoituong = maLoaiDoiTuong + "-" + maVeTinh + "/" + maDichVu.getText().toString();
						}
						if (sttSpliptter.getVisibility() == View.VISIBLE) {
							// maDT = tienTo +
							// maDichVu.getText().toString();
							if (maLoaiDoiTuong.equals("SA")) {
								madoituong = maLoaiDoiTuong + sttSpliptter.getText().toString()
										+ tuchuaSpliptter.getText().toString();
							}
							if (maLoaiDoiTuong.equals("SB")) {
								madoituong = maLoaiDoiTuong + sttSpliptter.getText().toString()
										+ tuchuaSpliptter.getText().toString() + maDichVu.getText().toString();
							}
						}
						if (!madoituong.equals(maDoiTuongTruoc)) {
							Toast toast = Toast.makeText(context,
									"Có sự thay đổi về đối tượng cần cập nhật. Cần kiểm tra lại trước khi cập nhật!",
									Toast.LENGTH_LONG);
							toast.setGravity(Gravity.CENTER, 0, 0);
							toast.show();
							flag = false;
							diachiView.setVisibility(View.GONE);
							bttImage.setText("Kiểm tra");
							bttCapNhatKetCuoi.setEnabled(false);
						} else if (madoituong.equals(maDoiTuongTruoc)) {
							AlertDialog.Builder alert = new AlertDialog.Builder(context);
							alert.setTitle("Thông báo!");
							alert.setMessage("Bạn có muốn cập nhật đối tượng có mã " + madoituong);
							alert.setNegativeButton("Không", null);
							alert.setPositiveButton("Có", new OnClickListener() {
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									Task task = new Task();
									capNhatKetCuoiTask = new CapNhatKetCuoiTask();
									capNhatKetCuoiTask.input.add(Util.userName);
									String maDT = "";

									if (sttSpliptter.getVisibility() == View.GONE) {
										maDT = tienTo + maDichVu.getText().toString();
									}
									if (sttSpliptter.getVisibility() == View.VISIBLE) {
										// maDT = tienTo +
										// maDichVu.getText().toString();
										if (maLoaiDoiTuong.equals("SA")) {
											maDT = maLoaiDoiTuong + sttSpliptter.getText().toString()
													+ tuchuaSpliptter.getText().toString();
										}
										if (maLoaiDoiTuong.equals("SB")) {
											maDT = maLoaiDoiTuong + sttSpliptter.getText().toString()
													+ tuchuaSpliptter.getText().toString()
													+ maDichVu.getText().toString();
										}
									}
									capNhatKetCuoiTask.input.add(maDT);
									capNhatKetCuoiTask.input.add(loaiDoiTuong);
									location = map.getMyLocation();
									capNhatKetCuoiTask.input.add(diaChi.getText().toString().length() > 0
											? diaChi.getText().toString() : "nhap tu di dong");
									capNhatKetCuoiTask.input.add(tenThueBao.getText().toString());
									capNhatKetCuoiTask.input.add(maVeTinh);

									capNhatKetCuoiTask.input.add("" + location.getLongitude());
									capNhatKetCuoiTask.input.add("" + location.getLatitude());
									capNhatKetCuoiTask.input.add("");
									capNhatKetCuoiTask.input.add("0947468855");

									task.execute(capNhatKetCuoiTask);
								}
							});
							alert.show();
						}

					}

				}
			});
			for (int i = 0; i < length; i++) {
				lstVeTinh.add(listString.getPropertyAsString(i));
			}
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,
					lstVeTinh) {
			};
			veTinh = (AutoCompleteTextView) body.findViewById(R.id.edit);
			veTinh.setAdapter(adapter);
			veTinh.setThreshold(0);
			veTinh.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					veTinh.showDropDown();
					return false;
				}
			});
			menu.setOnOpenedListener(new SlidingMenu.OnOpenedListener() {

				@Override
				public void onOpened() {
					// TODO Auto-generated method stub
					veTinh.dismissDropDown();
					Util.hideKeyBoard(context, body);

				}
			});

			veTinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					Util.hideKeyBoard(context, body);
					// TODO Auto-generated method stub
					maVeTinh = veTinh.getText().toString().split("-")[0];
					if (sttSpliptter.getVisibility() == View.GONE) {
						tienTo = maLoaiDoiTuong + "-" + maVeTinh + "/";
						maVT.setText(tienTo);
					}
					if (sttSpliptter.getVisibility() == View.VISIBLE) {
						// tienTo = maLoaiDoiTuong + "-" + maVeTinh;
						maVT.setText(maLoaiDoiTuong);
						if (maLoaiDoiTuong.equals("SA")) {
							tuchuaSpliptter.setText("/" + loaiTuChua + "-" + maVeTinh);
						}
						if (maLoaiDoiTuong.equals("SB")) {
							tuchuaSpliptter.setText("/" + loaiTuChua + "-" + maVeTinh + "/");
						}
					}

				}
			});

		}
		if (task.equals(capNhatKetCuoiTask)) {
			try {
				Vector<SoapObject> result = (Vector<SoapObject>) capNhatKetCuoiTask.result;
				SoapObject thongBao = result.get(0);
				String maLoi = thongBao.getPrimitivePropertyAsString("MaLoi");
				if (maLoi.equals("0")) {
					Util.showAlert(context, "Cập nhật thành công");
				}
				if (maLoi.equals("4")) {
					Util.showAlert(context, "Mã không tồn tại");
				}
				if (maLoi.equals("5") || maLoi.equals("6")) {
					SoapObject list = result.get(1);
					int length = list.getPropertyCount();
					String danhSachMa = "";
					for (int i = 0; i < length; i++) {
						danhSachMa = danhSachMa + "\n" + list.getPropertyAsString(i);

					}
					Util.showAlert(context, "Có " + length + " mã dịch vụ sau gần giống mã bạn vừa gõ :" + danhSachMa);
				}

			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		if (task.equals(getViTriTask)) {

			Vector<Object> thongBaoViTri = (Vector<Object>) getViTriTask.result;
			SoapObject thongTin = (SoapObject) thongBaoViTri.get(1);

			String tonTai = thongTin.getPropertyAsString("TONTAI");

			if (tonTai.equals("1")) {
				float lon = Float.valueOf(thongTin.getPropertyAsString("LONGITUDE"));
				float lat = Float.valueOf(thongTin.getPropertyAsString("LATITUDE"));
				String nameTB = thongTin.getPropertyAsString("DC_THUEBAO");
				if (nameTB.length() > 0) {
					diachiView.setVisibility(View.VISIBLE);
					diaChi.setText(nameTB);
				}
				map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 14));
				MarkerOptions oMarkerOptions = new MarkerOptions().draggable(true).anchor(0.5f, 1.0f)
						.title(getViTriTask.input.get(0).toString());
				LatLng me = new LatLng(lat, lon);
				if (pointMe != null) {
					pointMe.remove();
				}
				oMarkerOptions.position(me);

				pointMe = map.addMarker(oMarkerOptions);
				map.animateCamera(CameraUpdateFactory.zoomTo(14), 2000, null);
				AlertDialog.Builder alert = new AlertDialog.Builder(context);
				alert.setTitle("Đối tượng đã có vị trí trong hệ thống!");
				alert.setMessage("Cập nhật cho đối tượng ?");
				alert.setNegativeButton("Không", null);
				alert.setPositiveButton("Có", new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						bttCapNhatKetCuoi.setEnabled(true);
						bttImage.setText("Chụp ảnh");
						flag = true;
					}
				});
				alert.show();

			}
			if (tonTai.equals("0")) {
				Toast toast = Toast.makeText(context,
						"Mã chưa có trong dữ liệu về vị trí. \nNgười dùng có thể thực hiện cập nhật ngay bây giờ",
						Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				bttImage.setEnabled(true);
				map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(21.0293, 105.8522), 14));
				map.animateCamera(CameraUpdateFactory.zoomTo(14), 2000, null);
				bttCapNhatKetCuoi.setEnabled(true);
				bttImage.setText("Chụp ảnh");
				flag = true;

			}

		}
	}

	public boolean checkValidate(boolean isCheckImage) {
		boolean ok = true;
		String validate = "Chưa nhập đủ thông tin :";
		if (veTinh.getText().length() == 0) {
			validate = validate + "\n" + "Chưa nhập vệ tinh";
			ok = false;
		}
		if (maDichVu.getText().length() == 0 && maDichVu.getVisibility() == View.VISIBLE) {
			validate = validate + "\n" + "Chưa nhập mã đối tượng";
			ok = false;

		}

		if (tenThueBao.getText().length() == 0) {
			validate = validate + "\n" + "Chưa nhập ghi chú";
			ok = false;

		}
		// if (bitmap == null && isCheckImage == true) {
		// validate = validate + "\n" + "Chưa chụp ảnh";
		// ok = false;
		//
		// }

		if (sttSpliptter.getText().toString().length() == 0 && sttSpliptter.getVisibility() == View.VISIBLE) {
			validate = validate + "\n" + "Chưa nhập thứ tự của splitter";
			ok = false;
		}

		if (!ok) {
			Toast toast = Toast.makeText(context, validate, Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		}
		return ok;

	}

}
