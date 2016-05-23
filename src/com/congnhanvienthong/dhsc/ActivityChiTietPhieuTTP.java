package com.congnhanvienthong.dhsc;

import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import com.congnhanvienthong.ActivityBaseToDisplay;
import com.congnhanvienthong.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import adapter.BaseSpinnerAdapter;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import congnhanvienthong.entity.dhsc.LoaiNghiemThu;
import congnhanvienthong.entity.dhsc.LoaiSua;
import congnhanvienthong.entity.dhsc.LoaiSuaChiTiet;
import congnhanvienthong.entity.dhsc.LyDoTon;
import congnhanvienthong.entity.dhsc.NhanVien;
import congnhanvienthong.entity.dhsc.ThongTinBaoHong;
import control.Util;
import view.YourMapFragment;
import webservice.BaseTask;
import webservice.WebProtocol;
import webservice.dhsc.BaoTonTTP;
import webservice.dhsc.GetLoaiSuaChiTietTask;
import webservice.dhsc.KhoaPhieuTTP;
import webservice.dhsc.ListNhanVienTask;
import webservice.dhsc.NhanPhieuTask;
import webservice.dhsc.ThemMoiViTriDHSCTask;

public class ActivityChiTietPhieuTTP extends ActivityBaseToDisplay {
	// biến
	TextView viewPhieu, viewMaDichVu, viewNgayXuat, viewNoiDung, viewHonglai;
	ArrayList<LoaiSua> lstLoaiSua = new ArrayList<LoaiSua>();
	ArrayList<LoaiSuaChiTiet> lstLoaiSuaChiTiet = new ArrayList<LoaiSuaChiTiet>();
	ArrayList<LoaiNghiemThu> lstLoaiNghiemThu = new ArrayList<LoaiNghiemThu>();
	ArrayList<LyDoTon> lstTon = new ArrayList<LyDoTon>();
	ArrayList<NhanVien> lstNhanVien = new ArrayList<NhanVien>();
	Spinner spnLoaiSuaChiTiet, spnLoaiSua, spnLoaiNghiemThu, loaiTon, spnUser;
	ThongTinBaoHong thongTinBaoHong;
	LoaiSua loaiSua;
	LoaiSuaChiTiet loaiSuaChiTiet;
	LoaiNghiemThu loaiNghiemThu = new LoaiNghiemThu("", "");
	LyDoTon lyDoTon = new LyDoTon();
	EditText txtNoiDungSua, txtNoiDungNghiemThu, txtNDTon;
	String id_loaisua, idLoaiSuaChiTiet, idNghiemThu, ndGoc = "", ndSuaChitiet = "", ndSua = "", ldTonCt = "",
			ldTon = "", ndNghiemThu = "", id_ton = "", id_nguoinhan = "";
	Dialog dialog;
	// BaoTonTask baoTonTask;
	KhoaPhieuTTP khoaPhieuDHSCTask;
	ListNhanVienTask listNhanVienTask;
	Button bttTon, bttKhoaMain, bttNhan;
	BaoTonTTP baoTonTask;

	GetLoaiSuaChiTietTask getLoaiSuaChiTietTask;
	NhanPhieuTask nhanPhieuTask;
	Button buttonExit, buttonOK;
	NhanVien nhanvien;
	private GoogleMap map;

	public static int NHAN_TYPE = 0;
	public static int TON_TYPE = 1;
	public static int KHOA_TYPE = 2;
	LinearLayout lineuser, lineloaisua, lineloaisuachitiet, linenghiemthu, lineton, linendsuachitiet;
	ScrollView scrollViewMain;
	ThemMoiViTriDHSCTask themMoiViTriDHSCTask = new ThemMoiViTriDHSCTask();
	LocationManager locationManager;
	// ---Ok----
	boolean isGPSEnabled;
	CheckBox cbxLocation;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		init();
		locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);

		// getting GPS status

		isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
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

				// getting network status

			}
		});
		cbxLocation.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (cbxLocation.isChecked()) {
					isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
					if (!isGPSEnabled) {
						Util.confirmTurnOnGPS(context);
					}
				}
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
		bttNhan.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				creatDialog(NHAN_TYPE, "Nhận phiếu luôn");
				buttonOK.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Task task = new Task();
						task.execute(nhanPhieuTask);

					}
				});

			}
		});

		bttKhoaMain.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// Khởi tạo dialog và gán các component trên dialog
				creatDialog(KHOA_TYPE, "Khoá phiếu sửa chữa");

				buttonOK.setOnClickListener(new OnClickListener() {

					// @Override
					public void onClick(View v) {
						ndSua = ndSuaChitiet + txtNoiDungSua.getText().toString();
						if (checkNghiemThuTTP())
							spnLoaiNghiemThu.setSelection(2);
						ndNghiemThu = loaiNghiemThu.getNoiDung();

						ndGoc = ndNghiemThu + txtNoiDungNghiemThu.getText().toString();
						khoaPhieuDHSCTask = new KhoaPhieuTTP();

						khoaPhieuDHSCTask.setDataInput(thongTinBaoHong, loaiSua, loaiSuaChiTiet, loaiNghiemThu, ndSua,
								ndSuaChitiet, ndNghiemThu, ndGoc, nhanvien);
						Location loc = null;
						if (map != null) {
							loc = map.getMyLocation();
						}
						isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
						if (!isGPSEnabled) {

							Toast.makeText(context, "Chưa bật GPS. Bạn sẽ không thể cập nhật toạ độ nếu không bật gps",
									Toast.LENGTH_LONG).show();
						}
						if (loc != null && cbxLocation.isChecked()) {
							themMoiViTriDHSCTask.input.add(Util.userName);
							themMoiViTriDHSCTask.input.add(thongTinBaoHong.getMa_DichVu());
							themMoiViTriDHSCTask.input.add(thongTinBaoHong.getID_LoaiDichVu());
							themMoiViTriDHSCTask.input.add(thongTinBaoHong.getDChi_TB());
							themMoiViTriDHSCTask.input.add(thongTinBaoHong.getTen_TB());
							themMoiViTriDHSCTask.input.add(thongTinBaoHong.getMa_VT());
							themMoiViTriDHSCTask.input.add(loc.getLongitude());
							themMoiViTriDHSCTask.input.add(loc.getLatitude());
							themMoiViTriDHSCTask.input.add("11111");
							onExecuteToServer(true, "Xác nhận khóa phiếu", khoaPhieuDHSCTask, themMoiViTriDHSCTask);
						} else {
							onExecuteToServer(true, "Xác nhận khóa phiếu", khoaPhieuDHSCTask);
						}

					}
				});

			}
		});

		// Nút tồn-------------------------------------

		bttTon.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				creatDialog(TON_TYPE, "Báo Tồn Phiếu");
				buttonOK.setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						// TODO Auto-generated method stub

						baoTonTask = new BaoTonTTP();

						baoTonTask.input.add(thongTinBaoHong.getID_TrangThai());
						baoTonTask.input.add(thongTinBaoHong.getID_BaoHong());
						baoTonTask.input.add(thongTinBaoHong.getID_PhieuSua());
						baoTonTask.input.add(thongTinBaoHong.getID_LanXPNT());
						baoTonTask.input.add(thongTinBaoHong.getID_VT());
						baoTonTask.input.add(nhanvien.getId());
						baoTonTask.input.add(Util.userName);
						baoTonTask.input.add(thongTinBaoHong.getID_Xuat());
						ldTonCt = lyDoTon.getTenLyDoTon();
						ldTon = ldTonCt + ldTon;
						baoTonTask.input.add(ldTon);
						baoTonTask.input.add(ldTonCt);
						baoTonTask.input.add(lyDoTon.getIdLyDoTon());
						baoTonTask.input.add(thongTinBaoHong.getMa_DichVu());
						baoTonTask.input.add(thongTinBaoHong.getID_LoaiDichVu());
						baoTonTask.input.add(Util.ttp.getId_ttpho());
						Object temp = baoTonTask;
						System.out.println(temp);
						onExecuteToServer(true, "Báo tồn ??", baoTonTask);

					}
				});

				buttonExit.setOnClickListener(new OnClickListener() {

					// @Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});

			}
		});

	}

	public boolean checkNghiemThuTTP() {
		int idls = Integer.parseInt(loaiSua.getIdLoaiSua());
		boolean flag = false;
		if (thongTinBaoHong.isNghiemThu() && idls != 36 && idls != 1241 && idls != 2083 && idls != 1903)
			flag = true;
		return flag;
	}

	// tao data cho ham khoa(truyền input và para cho ws khóa phiếu)

	@SuppressWarnings("unchecked")
	@Override
	public void onsucces(WebProtocol task) {
		// TODO Auto-generated method stub
		super.onsucces(task);
		// Bao Ton
		if (task.equals(baoTonTask)) {

			dialog.dismiss();
			Util.showAlert(context, baoTonTask.result.toString());

		}
		// Khoa phieu
		if (task.equals(khoaPhieuDHSCTask)) {

			String thongBao = "";
			try {
				SoapObject ketquaKhoaPhieu = (SoapObject) khoaPhieuDHSCTask.result;
				String loi = ketquaKhoaPhieu.getProperty("IsError").toString();
				if (loi.contains("false")) {
					thongBao = thongBao + "Khóa phiếu thành công ! \n";
				} else {
					thongBao = thongBao + "Khóa phiếu không thành công ! \n"
							+ ketquaKhoaPhieu.getProperty("ErrorMsg").toString();

				}
				try {
					SoapObject ketquaVitri = (SoapObject) themMoiViTriDHSCTask.result;
					String vitri = ketquaVitri.getProperty("MoTaLoi").toString();
					thongBao = thongBao + vitri;
				} catch (Exception e) {
					// TODO: handle exception
				}

			} catch (Exception e) {
				// TODO: handle exception
				thongBao = thongBao + "Khóa phiếu không thành công ! \n" + e.getMessage();
			}
			dialog.dismiss();
			AlertDialog.Builder alert = new AlertDialog.Builder(context);
			alert.setTitle("Thông báo!");
			alert.setMessage(thongBao);
			alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub

					Intent i = new Intent(context, ActivityNhanKhoaPhieuTTP.class);
					startActivity(i);

				}
			});
			alert.show();
		}
		// Lay lish nhan vien
		if (task.equals(listNhanVienTask)) {
			lstNhanVien = (ArrayList<NhanVien>) listNhanVienTask.getResult();
		}
		// lấy list loại sửa
		if (task.equals(getLoaiSuaChiTietTask)) {
			lstLoaiSuaChiTiet = Util.SetDataToSpinner(context, spnLoaiSuaChiTiet,
					(SoapObject) getLoaiSuaChiTietTask.getResult(), LoaiSuaChiTiet.class, false, "LoaiSuaChiTietText");

		}
		if (task.equals(nhanPhieuTask)) {
			// Object temp = ketqua.get(0);
			// SoapObject tempObj = (SoapObject) temp;
			// if (tempObj.getProperty("IsError").toString().contains("false"))
			// {
			// thongTinBaoHong.setIsAllowKhoa(true);
			// thongTinBaoHong.setIsAllowTon(true);
			// thongTinBaoHong.setAllowNhan(false);
			// refresh(thongTinBaoHong);
			// dialog.dismiss();
			// Intent i = new Intent(context, ActivityNhanKhoaPhieuTTP.class);
			// startActivity(i);
			// finish();
			//
			// }

		}
	}

	public void refresh(ThongTinBaoHong ttbh) {
		viewPhieu.setText(ttbh.getID_LanXPNT() + "/" + ttbh.getMa_VT() + "/" + ttbh.getTen_VT());
		viewMaDichVu.setText(ttbh.getMa_DichVu());
		viewNgayXuat.setText(ttbh.getNgay_Xuat() + ". LH:" + ttbh.getSoMay_LHe());
		viewNoiDung.setText(ttbh.getND_Xuat());
		viewHonglai.setText(ttbh.getHong_Lai() + "/" + ttbh.getSo_LanBao() + "/" + ttbh.getSo_LanNhacPhieu());
		if (!ttbh.getIsAllowKhoa()) {
			bttKhoaMain.setEnabled(false);
		} else
			bttKhoaMain.setEnabled(true);
		if (!ttbh.getIsAllowTon()) {
			bttTon.setEnabled(false);
		} else
			bttTon.setEnabled(true);
		if (!ttbh.isAllowNhan()) {
			bttNhan.setEnabled(false);
		} else
			bttNhan.setEnabled(true);

	}

	@SuppressWarnings("unchecked")
	public void init() {
		setBodyLayout(R.layout.activity_chi_tiet_phieu);

		setHeader("Khóa, báo tồn");
		this.context = ActivityChiTietPhieuTTP.this;
		Intent i = getIntent();
		// --lấy thông tin từ màn hình cũ

		thongTinBaoHong = (ThongTinBaoHong) i.getSerializableExtra("phieu");
		lstLoaiSua = (ArrayList<LoaiSua>) i.getSerializableExtra("loaisua");
		lstLoaiNghiemThu = (ArrayList<LoaiNghiemThu>) i.getSerializableExtra("loainghiemthu");

		lstTon = (ArrayList<LyDoTon>) i.getSerializableExtra("ton");
		// màn hình thông tin chi tiết phiếu--
		viewPhieu = (TextView) body.findViewById(R.id.chitietphieu_maphieu_vetinh);
		viewMaDichVu = (TextView) body.findViewById(R.id.chitietphieu_madichvu);
		viewHonglai = (TextView) body.findViewById(R.id.chitietphieu_lannhac);
		viewNgayXuat = (TextView) body.findViewById(R.id.chitietphieu_lienhe);
		viewNoiDung = (TextView) body.findViewById(R.id.chitietphieu_noidungxuat);

		setFootLayout(R.layout.foot_activity_chi_tiet_phieu);
		listNhanVienTask = new ListNhanVienTask();
		listNhanVienTask.input.add(Util.userName);
		listNhanVienTask.input.add("");
		cbxLocation = (CheckBox) body.findViewById(R.id.cbxLocation);
		onExecuteToServer(false, null, listNhanVienTask);

		// Xong phần thông tin
		// nút tồn
		bttTon = (Button) foot.findViewById(R.id.phieuton);
		// ----- Nhấn nút khóa, hiện dialog với màn hình khóa
		bttKhoaMain = (Button) foot.findViewById(R.id.phieu_khoa);
		bttNhan = (Button) foot.findViewById(R.id.take_image);
		bttNhan.setText("Nhận");
		refresh(thongTinBaoHong);

	}

	public void creatDialog(int type, String title) {
		dialog = new Dialog(context);
		dialog.setContentView(R.layout.layout_khoa_phieu);
		dialog.setCancelable(true);
		dialog.setTitle(title);
		buttonExit = (Button) dialog.findViewById(R.id.exit_khoa);
		buttonOK = (Button) dialog.findViewById(R.id.bttKhoaNgay);
		spnLoaiSua = (Spinner) dialog.findViewById(R.id.lstloaisua);
		spnLoaiNghiemThu = (Spinner) dialog.findViewById(R.id.lst_loai_nghiem_thu);
		spnLoaiSuaChiTiet = (Spinner) dialog.findViewById(R.id.lst_loai_sua_chi_tiet);
		txtNDTon = (EditText) dialog.findViewById(R.id.txt_noi_dung_ton);
		txtNoiDungNghiemThu = (EditText) dialog.findViewById(R.id.txt_noi_dung_nghiem_thu);
		txtNoiDungSua = (EditText) dialog.findViewById(R.id.txt_noi_dung_sua);

		buttonExit.setOnClickListener(new OnClickListener() {
			// @Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		BaseSpinnerAdapter<LoaiSua> adapter = new BaseSpinnerAdapter<LoaiSua>(context,
				android.R.layout.simple_spinner_dropdown_item, lstLoaiSua);
		adapter.name = "LoaiSuaText";
		spnLoaiSua.setAdapter(adapter);
		BaseSpinnerAdapter<LoaiNghiemThu> adapterNghiemThu = new BaseSpinnerAdapter<LoaiNghiemThu>(context,
				android.R.layout.simple_spinner_dropdown_item, lstLoaiNghiemThu);
		adapterNghiemThu.name = "NoiDung";
		spnLoaiNghiemThu.setAdapter(adapterNghiemThu);
		spnLoaiSua.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				loaiSua = (LoaiSua) arg0.getAdapter().getItem(arg2);
				getLoaiSuaChiTietTask = new GetLoaiSuaChiTietTask();
				getLoaiSuaChiTietTask.input.add(loaiSua.getIdLoaiSua());
				getLoaiSuaChiTietTask.input.add(Util.ttp.getId_ttpho());

				getLoaiSuaChiTietTask.input.add(Util.userName);
				getLoaiSuaChiTietTask.input.add("s");
				// task.execute(getLoaiSuaChiTietTask);
				id_loaisua = loaiSua.getIdLoaiSua();
				onExecuteToServer(true, null, getLoaiSuaChiTietTask);

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		spnLoaiSuaChiTiet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				loaiSuaChiTiet = (LoaiSuaChiTiet) arg0.getAdapter().getItem(arg2);
				id_loaisua = loaiSuaChiTiet.getIdLoaiSuaChiTiet();
				ndSuaChitiet = loaiSuaChiTiet.getLoaiSuaChiTietText();
				if (!checkNghiemThuTTP()) {
					linenghiemthu.setVisibility(View.GONE);
				} else
					linenghiemthu.setVisibility(View.VISIBLE);

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		spnUser = (Spinner) dialog.findViewById(R.id.lst_nguoiton);
		BaseSpinnerAdapter<NhanVien> adapterNhanVien = new BaseSpinnerAdapter<NhanVien>(context,
				android.R.layout.simple_spinner_dropdown_item, lstNhanVien);
		adapterNhanVien.name = "TenNhanVien";
		spnUser.setAdapter(adapterNhanVien);
		spnUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				nhanvien = (NhanVien) arg0.getAdapter().getItem(arg2);
				int id = Integer.valueOf(nhanvien.getId());
				nhanPhieuTask = new NhanPhieuTask();

				nhanPhieuTask.input.add(thongTinBaoHong.getID_LanXPNT());
				nhanPhieuTask.input.add(Util.userName);
				nhanPhieuTask.input.add(id);
				nhanPhieuTask.input.add(thongTinBaoHong.getID_TrangThai());
				nhanPhieuTask.input.add(thongTinBaoHong.getID_LoaiDichVu());
				nhanPhieuTask.input.add(thongTinBaoHong.getID_LoaiDichVuCT());
				nhanPhieuTask.input.add(thongTinBaoHong.getID_Xuat());
				nhanPhieuTask.input.add(Util.ttp.getId_ttpho());

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		spnLoaiNghiemThu.setSelection(1);
		spnLoaiNghiemThu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				loaiNghiemThu = (LoaiNghiemThu) arg0.getAdapter().getItem(arg2);

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		lineuser = (LinearLayout) dialog.findViewById(R.id.layout_user);
		lineloaisua = (LinearLayout) dialog.findViewById(R.id.layout_loaisua);
		lineloaisuachitiet = (LinearLayout) dialog.findViewById(R.id.layout_loaisua_chitiet);
		linenghiemthu = (LinearLayout) dialog.findViewById(R.id.layout_nghiemthu);
		lineton = (LinearLayout) dialog.findViewById(R.id.layout_ton);
		linendsuachitiet = (LinearLayout) dialog.findViewById(R.id.layout_ndsua_chitiet);
		loaiTon = (Spinner) dialog.findViewById(R.id.lst_loai_ton);
		BaseSpinnerAdapter<LyDoTon> adapterTon = new BaseSpinnerAdapter<LyDoTon>(context,
				android.R.layout.simple_spinner_dropdown_item, lstTon);
		adapterTon.name = "TenLyDoTon";
		loaiTon.setAdapter(adapterTon);
		loaiTon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				lyDoTon = (LyDoTon) arg0.getAdapter().getItem(arg2);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		if (type == NHAN_TYPE) {
			lineloaisua.setVisibility(View.GONE);
			lineloaisuachitiet.setVisibility(View.GONE);
			linenghiemthu.setVisibility(View.GONE);
			lineton.setVisibility(View.GONE);
			lineuser.setVisibility(View.VISIBLE);
			linendsuachitiet.setVisibility(View.GONE);
		}
		if (type == KHOA_TYPE) {
			lineloaisua.setVisibility(View.VISIBLE);
			lineloaisuachitiet.setVisibility(View.VISIBLE);
			linenghiemthu.setVisibility(View.VISIBLE);
			lineton.setVisibility(View.GONE);
			lineuser.setVisibility(View.VISIBLE);
			linendsuachitiet.setVisibility(View.VISIBLE);
		}
		if (type == TON_TYPE) {
			lineloaisua.setVisibility(View.GONE);
			lineloaisuachitiet.setVisibility(View.GONE);
			linenghiemthu.setVisibility(View.GONE);
			lineton.setVisibility(View.VISIBLE);
			lineuser.setVisibility(View.VISIBLE);
			linendsuachitiet.setVisibility(View.GONE);
		}
		dialog.show();

	}
}
