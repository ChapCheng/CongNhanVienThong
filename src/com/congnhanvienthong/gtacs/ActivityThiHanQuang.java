// package com.congnhanvienthong.gtacs;
//
// import java.util.ArrayList;
// import java.util.Locale;
// import java.util.Vector;
//
// import org.ksoap2.serialization.SoapObject;
//
// import webservice.BaseTask;
// import webservice.GetDichVuCungIDDayChungTask;
// import webservice.GetViTriTask;
// import webservice.dhsc.ThemMoiViTriDHSCTask;
// import adapter.BaseListViewAdapter;
// import android.location.Location;
// import android.os.Bundle;
// import android.view.Gravity;
// import android.view.View;
// import android.view.View.OnClickListener;
// import android.widget.AdapterView;
// import android.widget.Button;
// import android.widget.EditText;
// import android.widget.HeaderViewListAdapter;
// import android.widget.LinearLayout;
// import android.widget.Toast;
//
// import com.congnhanvienthong.ActivityBaseToDisplay;
// import com.congnhanvienthong.R;
// import com.google.android.gms.maps.CameraUpdateFactory;
// import com.google.android.gms.maps.GoogleMap;
// import com.google.android.gms.maps.SupportMapFragment;
// import com.google.android.gms.maps.model.LatLng;
// import com.google.android.gms.maps.model.MarkerOptions;
// import com.handmark.pulltorefresh.library.PullToRefreshListView;
//
// import congnhanvienthong.entity.gtacs.ThongTinDichVu;
// import control.Util;
//
// public class ActivityThiHanQuang extends ActivityBaseToDisplay {
// GetDichVuCungIDDayChungTask getDichVuCungIDDayChungTask;
// PullToRefreshListView listView;
// EditText editMaDichVu;
// Button bttTimKiem, bttOK, bttMap, bttDichVuCungDay;
// ThemMoiViTriDHSCTask[] arrCapNhatKetCuoiTasks;
// private GoogleMap map;
// LinearLayout showHideMap;
// Location location;
// GetViTriTask getViTriTask1;
//
// @Override
// protected void onCreate(Bundle savedInstanceState) {
//
// super.onCreate(savedInstanceState);
// setBodyLayout(R.layout.activity_gtcas_thi_han_quang);
// setHeader("Cập nhật vị trí thuê bao");
// setFootLayout(R.layout.foot_tracuu);
// context = ActivityThiHanQuang.this;
// bttOK = (Button) foot.findViewById(R.id.bttOK);
// bttOK.setText("C.N Vị Trí");
// bttMap = (Button) foot.findViewById(R.id.bttMap);
// bttMap.setVisibility(View.VISIBLE);
//
// listView = (PullToRefreshListView) body.findViewById(R.id.lst_thi_han_quang);
//
// editMaDichVu = (EditText) body.findViewById(R.id.ma_dich_vu);
// bttTimKiem = (Button) foot.findViewById(R.id.bttTimKiem);
// bttTimKiem.setVisibility(View.VISIBLE);
//
// map = ((SupportMapFragment)
// getSupportFragmentManager().findFragmentById(R.id.mapo)).getMap();
// map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(21.0293,
// 105.8522), 14));
// map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
// map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
// map.getUiSettings().setZoomControlsEnabled(true);
// map.getUiSettings().setCompassEnabled(true);
// map.getUiSettings().setMyLocationButtonEnabled(true);
// map.getUiSettings().setAllGesturesEnabled(true);
// map.setTrafficEnabled(true);
// map.getUiSettings().setTiltGesturesEnabled(true);
// map.setMyLocationEnabled(true);
// showHideMap = (LinearLayout) body.findViewById(R.id.sh_map);
// bttMap.setOnClickListener(new OnClickListener() {
//
// @Override
// public void onClick(View v) {
// // TODO Auto-generated method stub
// switch (showHideMap.getVisibility()) {
// case View.VISIBLE:
// showHideMap.setVisibility(View.GONE);
// break;
// case View.GONE:
// showHideMap.setVisibility(View.VISIBLE);
// break;
//
// }
//
// }
// });
//
// bttTimKiem.setOnClickListener(new OnClickListener() {
//
// @Override
// public void onClick(View v) {
// getDichVuCungIDDayChungTask = new GetDichVuCungIDDayChungTask();
// String maDichVu =
// editMaDichVu.getText().toString().trim().toUpperCase(Locale.getDefault());
//
// getDichVuCungIDDayChungTask.input.add(maDichVu);
// onCallWS(true, "Lấy các dịch vụ cùng dây chung với mã " + maDichVu,
// getDichVuCungIDDayChungTask);
// }
// });
//
// }
//
// @Override
// public void onBackPressed() {
// // TODO Auto-generated method stub
//
// }
//
// @SuppressWarnings("unchecked")
// @Override
// public void onsucces(BaseTask task) {
// super.onsucces(task);
// map.clear();
// if (task.equals(getDichVuCungIDDayChungTask)) {
// try {
//
// ArrayList<ThongTinDichVu> lstThongTinDichVu = new
// ArrayList<ThongTinDichVu>();
// SoapObject temp = (SoapObject) ketqua.get(0);
//
// int lenth = temp.getPropertyCount();
//
// for (int i = 0; i < lenth; i++) {
// SoapObject tempDichVu = (SoapObject) temp.getProperty(i);
// ThongTinDichVu tempThongTinDichVu = new ThongTinDichVu();
// Util.GetObjectFromSoapObject(tempThongTinDichVu, tempDichVu);
// lstThongTinDichVu.add(tempThongTinDichVu);
//
// }
// listView.setAdapter(new BaseListViewAdapter<ThongTinDichVu>(context,
// lstThongTinDichVu, null, true));
//
// bttOK.setOnClickListener(new OnClickListener() {
//
// @Override
// public void onClick(View v) {
// // TODO Auto-generated method stub
// HeaderViewListAdapter tempAdapter = (HeaderViewListAdapter)
// listView.getAdapter();
// BaseListViewAdapter<ThongTinDichVu> tempBaseListAdapter =
// (BaseListViewAdapter<ThongTinDichVu>) tempAdapter
// .getWrappedAdapter();
// ArrayList<ThongTinDichVu> lstSelect = tempBaseListAdapter.getChechItem();
// int count = lstSelect.size();
// if (count == 0) {
// Toast tbKetQua = Toast.makeText(context, "Phải chọn ít nhất 1 dịch vụ để cập
// nhật tọa độ",
// Toast.LENGTH_LONG);
// tbKetQua.setGravity(Gravity.CENTER, 0, 0);
// tbKetQua.show();
// return;
// }
// arrCapNhatKetCuoiTasks = new ThemMoiViTriDHSCTask[count];
// location = map.getMyLocation();
// for (int i = 0; i < count; i++) {
// arrCapNhatKetCuoiTasks[i] = new ThemMoiViTriDHSCTask();
// arrCapNhatKetCuoiTasks[i].input.add(Util.userName);
// arrCapNhatKetCuoiTasks[i].input.add(lstSelect.get(i).getMaDichVu());
// arrCapNhatKetCuoiTasks[i].input.add(lstSelect.get(i).getSerFno().trim());
// arrCapNhatKetCuoiTasks[i].input.add(lstSelect.get(i).getSubAddress().trim());
// arrCapNhatKetCuoiTasks[i].input.add(lstSelect.get(i).getSubName().trim());
//
// arrCapNhatKetCuoiTasks[i].input.add(lstSelect.get(i).getSwitchCentreClli());
// arrCapNhatKetCuoiTasks[i].input.add(location.getLongitude());
// arrCapNhatKetCuoiTasks[i].input.add(location.getLatitude());
// arrCapNhatKetCuoiTasks[i].input.add("xau lam");
// arrCapNhatKetCuoiTasks[i].input.add(Util.userTel +
// ";0913508458;0903465777;0914899286");
// }
// onCallWS(true, "Xác nhận cập nhật vị trí??", arrCapNhatKetCuoiTasks);
//
// }
// });
//
// listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
// @Override
// public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
// {
// // TODO Auto-generated method stub
// ThongTinDichVu tempObj = (ThongTinDichVu) arg0.getAdapter().getItem(arg2);
// getViTriTask1 = new GetViTriTask();
// getViTriTask1.input.add(tempObj.getMaDichVu());
// getViTriTask1.input.add("3");
// onCallWS(true, "Tìm vị trí mã " + tempObj.getMaDichVu(), getViTriTask1);
// }
// });
// } catch (Exception e) {
// // TODO: handle exception
// Toast.makeText(context, "Kiểm tra lại mã vừa nhập",
// Toast.LENGTH_SHORT).show();
// }
// }
//
// if (arrCapNhatKetCuoiTasks != null && task.equals(arrCapNhatKetCuoiTasks[0]))
// {
// int lenth = ketqua.size();
// ArrayList<String> arrThongBao = new ArrayList<String>();
// for (int i = 0; i < lenth; i++) {
// String ma = arrCapNhatKetCuoiTasks[i].input.get(1).toString();
// try {
// String loi = ((SoapObject) ketqua.get(i)).getPropertyAsString(0);
//
// if (loi.equals("0")) {
// arrThongBao.add("Mã " + ma + ": Cập nhật tọa độ thành công!");
// } else {
// arrThongBao.add("Mã " + ma + ": Cập nhật tọa độ không thành công!");
// }
// } catch (Exception e) {
// // TODO: handle exception
// arrThongBao.add("Lỗi khi cập nhật cho mã " + ma + " !");
// }
// }
// String mess = "";
// for (String string : arrThongBao) {
// mess = mess + string + "\n";
// }
// Toast tbKetQua = Toast.makeText(context, mess, Toast.LENGTH_LONG);
// tbKetQua.setGravity(Gravity.CENTER, 0, 0);
// tbKetQua.show();
//
// }
// if (task.equals(getViTriTask1)) {
//
// Vector<Object> thongBaoViTri = (Vector<Object>) ketqua.get(0);
// SoapObject thongTin = (SoapObject) thongBaoViTri.get(1);
//
// String tonTai = thongTin.getPropertyAsString("TONTAI");
//
// if (tonTai.equals("1")) {
// showHideMap.setVisibility(View.VISIBLE);
// float lon = Float.valueOf(thongTin.getPropertyAsString("LONGITUDE"));
// float lat = Float.valueOf(thongTin.getPropertyAsString("LATITUDE"));
// map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 14));
// // map.addMarker(new MarkerOptions())
// MarkerOptions oMarkerOptions = new
// MarkerOptions().draggable(true).anchor(0.5f, 1.0f)
// .title(getViTriTask1.input.get(0).toString());
// LatLng me = new LatLng(lat, lon);
// oMarkerOptions.position(me);
// map.addMarker(oMarkerOptions);
// map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
//
// } else {
// Util.showAlert(context, "Dịch vụ này chưa có trong CSDL vị trí");
// }
// }
//
// }
//
// }
