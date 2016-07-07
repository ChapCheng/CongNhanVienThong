package com.congnhanvienthong.dhsc;

import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

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
import android.net.Uri;
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
import android.widget.ProgressBar;
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
import webservice.WebProtocol;
import webservice.dhsc.BaoTonTTP;
import webservice.dhsc.GetLoaiSuaChiTietTask;
import webservice.dhsc.KhoaPhieuTTP;
import webservice.dhsc.ListNhanVienTask;
import webservice.dhsc.NhanPhieuTask;
import webservice.dhsc.ThemMoiViTriDHSCTask;
import webservice.dhsc.TraCuuTTPTask;
import webservice.dhsc.TraPhieuWS;

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
	KhoaPhieuTTP khoaPhieuDHSCTask; // not ok
	ListNhanVienTask listNhanVienTask;
	Button bttTon, bttKhoaMain, bttNhan, bttTra;
	BaoTonTTP baoTonTask;

	GetLoaiSuaChiTietTask getLoaiSuaChiTietTask;
	NhanPhieuTask nhanPhieuTask; // not ok
	Button buttonExit, buttonOK, btnClose, btnBaoHong, btnCall;
	NhanVien nhanvien;
	private GoogleMap map;
	ProgressBar progressBar;

	public static int NHAN_TYPE = 0;
	public static int TON_TYPE = 1;
	public static int KHOA_TYPE = 2;
	LinearLayout lineuser, lineloaisua, lineloaisuachitiet, linenghiemthu, lineton, linendsuachitiet,
			viewThongTinDichVu;
	ScrollView scrollViewMain;
	ThemMoiViTriDHSCTask themMoiViTriDHSCTask = new ThemMoiViTriDHSCTask();
	LocationManager locationManager;
	// ---Ok----
	boolean isGPSEnabled;
	CheckBox cbxLocation;
	TraCuuTTPTask traCuuTask = new TraCuuTTPTask();
	Dialog dia;
	TextView txtKetQua;

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
						if (Util.ttp.getId_ttpho().equals("1")) {
							khoaPhieuDHSCTask.setDataInputHNI(thongTinBaoHong, loaiSua, loaiSuaChiTiet, loaiNghiemThu,
									ndSua, ndSuaChitiet, ndNghiemThu, ndGoc, nhanvien);
						} else
							khoaPhieuDHSCTask.setDataInput(thongTinBaoHong, loaiSua, loaiSuaChiTiet, loaiNghiemThu,
									ndSua, ndSuaChitiet, ndNghiemThu, ndGoc, nhanvien);
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
							themMoiViTriDHSCTask.addParam("user", Util.userName);
							themMoiViTriDHSCTask.addParam("ma_dichvu", thongTinBaoHong.getMa_DichVu());
							themMoiViTriDHSCTask.addParam("id_loaidichvuDHSC", thongTinBaoHong.getID_LoaiDichVu());
							themMoiViTriDHSCTask.addParam("diachi", thongTinBaoHong.getDChi_TB());
							themMoiViTriDHSCTask.addParam("tenthuebao", thongTinBaoHong.getTen_TB());
							themMoiViTriDHSCTask.addParam("mavt", thongTinBaoHong.getMa_VT());
							themMoiViTriDHSCTask.addParam("longtitude", loc.getLongitude());
							themMoiViTriDHSCTask.addParam("latitude", loc.getLatitude());
							themMoiViTriDHSCTask.addParam("anh", "anh");
							onExecuteToServer(true, "Xác nhận khóa phiếu", khoaPhieuDHSCTask, themMoiViTriDHSCTask);
						} else {
							onExecuteToServer(true, "Xác nhận khóa phiếu", khoaPhieuDHSCTask);
						}

					}
				});

			}
		});

		bttTon.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				creatDialog(TON_TYPE, "Báo Tồn Phiếu");
				buttonOK.setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						// TODO Auto-generated method stub
						baoTonTask = new BaoTonTTP();
						baoTonTask.addParam("trangThaiCuId", thongTinBaoHong.getID_TrangThai());
						baoTonTask.addParam("baoHongId", thongTinBaoHong.getID_BaoHong());
						baoTonTask.addParam("phieuSuaId", thongTinBaoHong.getID_PhieuSua());
						baoTonTask.addParam("phieuId", thongTinBaoHong.getID_LanXPNT());
						baoTonTask.addParam("veTinhId", thongTinBaoHong.getID_VT());
						baoTonTask.addParam("nguoiNhanId", nhanvien.getId());
						baoTonTask.addParam("userName", Util.userName);
						baoTonTask.addParam("xuatId", thongTinBaoHong.getID_Xuat());
						ldTonCt = lyDoTon.getTenLyDoTon();
						ldTon = ldTonCt + ldTon;
						baoTonTask.addParam("noiDungTon", ldTon);
						baoTonTask.addParam("noiDungTonChiTiet", ldTonCt);
						baoTonTask.addParam("tonId", lyDoTon.getIdLyDoTon());
						baoTonTask.addParam("maDichvu", thongTinBaoHong.getMa_DichVu());
						baoTonTask.addParam("loaiDichVuId", thongTinBaoHong.getID_LoaiDichVu());
						baoTonTask.addParam("tinhThanhPhoId", Util.ttp.getId_ttpho());
						if (Util.ttp.getId_ttpho().equals("1")) {
							ldTonCt = lyDoTon.getTenLyDoTon();
							ldTon = ldTonCt + ldTon;
							baoTonTask = new BaoTonTTP();
							baoTonTask.addParam("tonId", lyDoTon.getIdLyDoTon());
							baoTonTask.addParam("lyDoTon", ldTonCt);
							baoTonTask.addParam("trangThaiId", thongTinBaoHong.getID_TrangThai());
							baoTonTask.addParam("loaiDichVuId", thongTinBaoHong.getID_LoaiDichVu());
							baoTonTask.addParam("maDichVu", thongTinBaoHong.getMa_DichVu());
							baoTonTask.addParam("loaiDichVuChiTietId", thongTinBaoHong.getID_LoaiDichVuCT());
							baoTonTask.addParam("lanxpntId", thongTinBaoHong.getID_LanXPNT());
							baoTonTask.addParam("xuatId", thongTinBaoHong.getID_Xuat());
							baoTonTask.addParam("phieuId", thongTinBaoHong.getID_LanXPNT());
							baoTonTask.addParam("phieuSuaId", thongTinBaoHong.getID_PhieuSua());
							baoTonTask.addParam("nguoiTonId", nhanvien.getId());
							baoTonTask.addParam("userName", Util.userName);
							baoTonTask.addParam("soMayLienHe", "");
							baoTonTask.addParam("soDienThoaiToVienThong", "");
							baoTonTask.addParam("ngayHen", "");

						}

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
			Util.showAlert(context, baoTonTask.getResult().toString());

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
					finish();

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
			Util.showAlert(context, nhanPhieuTask.getResult().toString());
			if (nhanPhieuTask.erros.contains("false")) {
				thongTinBaoHong.setIsAllowKhoa(true);
				thongTinBaoHong.setIsAllowTon(true);
				thongTinBaoHong.setAllowNhan(false);
				refresh(thongTinBaoHong);
				dialog.dismiss();
				Intent i = new Intent(context, ActivityNhanKhoaPhieuTTP.class);
				startActivity(i);
				finish();

			}

		}
		if (task instanceof TraPhieuWS) {
			Util.showAlert(context, task.getResult().toString());

		}
		if (task.equals(traCuuTask)) {

			btnCall.setOnClickListener(this);
			btnClose.setOnClickListener(this);
			dia.setCancelable(true);
			progressBar.setVisibility(View.GONE);
			txtKetQua = (TextView) dia.findViewById(R.id.ketqua);
			Util.setTextFromObject(txtKetQua, traCuuTask.getResult());

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
		if (Util.ttp.getId_ttpho().equals("1")) {
			bttNhan.setVisibility(View.GONE);
			bttTra.setVisibility(View.VISIBLE);
		}

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
		viewThongTinDichVu = (LinearLayout) body.findViewById(R.id.viewThongTinDichVuChiTiet);
		setFootLayout(R.layout.foot_activity_chi_tiet_phieu);
		listNhanVienTask = new ListNhanVienTask();
		listNhanVienTask.addParam("userName", Util.userName);
		cbxLocation = (CheckBox) body.findViewById(R.id.cbxLocation);
		onExecuteToServer(false, null, listNhanVienTask);

		// Xong phần thông tin
		// nút tồn
		bttTon = (Button) foot.findViewById(R.id.phieuton);
		// ----- Nhấn nút khóa, hiện dialog với màn hình khóa
		bttKhoaMain = (Button) foot.findViewById(R.id.phieu_khoa);
		bttNhan = (Button) foot.findViewById(R.id.take_image);
		bttTra = (Button) foot.findViewById(R.id.bttTraPhieu);
		bttTra.setOnClickListener(this);
		bttNhan.setText("Nhận");
		refresh(thongTinBaoHong);
		viewThongTinDichVu.setOnClickListener(this);

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
				getLoaiSuaChiTietTask.addParam("loaiSuaId", loaiSua.getIdLoaiSua());
				getLoaiSuaChiTietTask.addParam("tinhThanhPhoId", Util.ttp.getId_ttpho());
				// task.execute(getLoaiSuaChiTietTask);
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
		// MutilChoiceSpinnerAdapter<NhanVien> adapterNhanVien = new
		// MutilChoiceSpinnerAdapter<NhanVien>(context,
		// lstNhanVien);
		BaseSpinnerAdapter<NhanVien> adapterNhanVien = new BaseSpinnerAdapter<NhanVien>(context, lstNhanVien);
		adapterNhanVien.name = "TenNhanVien";
		spnUser.setAdapter(adapterNhanVien);
		spnUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				nhanvien = (NhanVien) arg0.getAdapter().getItem(arg2);
				int id = Integer.valueOf(nhanvien.getId());
				nhanPhieuTask = new NhanPhieuTask();
				nhanPhieuTask.addParam("phieuId", thongTinBaoHong.getID_LanXPNT());
				nhanPhieuTask.addParam("userName", Util.userName);
				nhanPhieuTask.addParam("nguoiNhanSuaId", id);
				nhanPhieuTask.addParam("trangThaiId", thongTinBaoHong.getID_TrangThai());
				nhanPhieuTask.addParam("loaiDichVuId", thongTinBaoHong.getID_LoaiDichVu());
				nhanPhieuTask.addParam("loaiDichVuChiTietId", thongTinBaoHong.getID_LoaiDichVuCT());
				nhanPhieuTask.addParam("xuatId", thongTinBaoHong.getID_Xuat());
				nhanPhieuTask.addParam("tinhThanhPhoId", Util.ttp.getId_ttpho());

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

	@SuppressWarnings("unchecked")
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.bttTraPhieu:
			TraPhieuWS traPhieuWS = new TraPhieuWS();
			traPhieuWS.addParam("userName", Util.userName);
			traPhieuWS.addParam("lanxpntId", thongTinBaoHong.getID_LanXPNT());
			onExecuteToServer(true, null, traPhieuWS);

			break;
		case R.id.viewThongTinDichVuChiTiet:
			dia = new Dialog(context);
			dia.setContentView(R.layout.tracuuchung_detail);
			dia.setCancelable(true);
			dia.setTitle("Thông tin chi tiết");
			dia.setCancelable(false);
			dia.show();
			btnClose = (Button) dia.findViewById(R.id.btnClose);
			btnBaoHong = (Button) dia.findViewById(R.id.btnBaoHong);
			btnCall = (Button) dia.findViewById(R.id.btnCall);
			btnCall.setVisibility(View.VISIBLE);
			btnBaoHong.setVisibility(View.GONE);
			progressBar = (ProgressBar) dia.findViewById(R.id.prbLayThongTin);
			progressBar.setVisibility(View.VISIBLE);
			traCuuTask.addParam("maDichVu", thongTinBaoHong.getMa_DichVu().trim());
			traCuuTask.addParam("loaiDichVuId", thongTinBaoHong.getID_LoaiDichVu());

			traCuuTask.addParam("userName", Util.userName);
			traCuuTask.addParam("tinhThanhPhoId", Util.ttp.getId_ttpho());
			if (Util.ttp.getId_ttpho().equals("1")) {
				traCuuTask.removeParam("userName");
				traCuuTask.removeParam("tinhThanhPhoId");

			}
			onExecuteToServer(false, null, traCuuTask);

			break;
		case R.id.btnClose:
			dia.dismiss();
			break;
		case R.id.btnCall:
			if (thongTinBaoHong != null && thongTinBaoHong.getSoMay_LHe() != "") {
				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(Uri.parse("tel:" + thongTinBaoHong.getSoMay_LHe()));
				startActivity(callIntent);
			}
			break;

		default:
			break;
		}
	}

}
