package congnhanvienthong.entity.nhatram;

import com.congnhanvienthong.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import control.AnnotationField;

public class NhaTram {
	Marker maker;
	@AnnotationField(hienthi = true, tenNhan = "Phường xã", order = 7)
	public String TenPhuongXa;
	@AnnotationField(hienthi = true, tenNhan = "Quận huyện", order = 6)
	String TenQuanHuyen;
	@AnnotationField(hienthi = true, tenNhan = "Tên nhà trạm", order = 2)
	String TenNhaTram;

	public String getTenPhuongXa() {
		return TenPhuongXa;
	}

	public void setTenPhuongXa(String tenPhuongXa) {
		TenPhuongXa = tenPhuongXa;
	}

	public String getTenQuanHuyen() {
		return TenQuanHuyen;
	}

	public void setTenQuanHuyen(String tenQuanHuyen) {
		TenQuanHuyen = tenQuanHuyen;
	}

	public Marker getMaker() {
		return maker;
	}

	public void setMaker(Marker maker) {
		this.maker = maker;
	}

	public String getDiaChi() {
		return DiaChi;
	}

	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}

	public String getDonViQuanLyId() {
		return DonViQuanLyId;
	}

	public void setDonViQuanLyId(String donViQuanLyId) {
		DonViQuanLyId = donViQuanLyId;
	}

	public String getGhiChu() {
		return GhiChu;
	}

	public void setGhiChu(String ghiChu) {
		GhiChu = ghiChu;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getIsUpdate() {
		return IsUpdate;
	}

	public void setIsUpdate(String isUpdate) {
		IsUpdate = isUpdate;
	}

	public String getLatitude() {
		return Latitude1;
	}

	public void setLatitude(String latitude) {
		Latitude1 = latitude;
	}

	public String getLoaiNhaTramId() {
		return LoaiNhaTramId;
	}

	public void setLoaiNhaTramId(String loaiNhaTramId) {
		LoaiNhaTramId = loaiNhaTramId;
	}

	public String getLongitude() {
		return Longitude1;
	}

	public void setLongitude(String longitude) {
		Longitude1 = longitude;
	}

	public String getMa() {
		return Ma;
	}

	public void setMa(String ma) {
		Ma = ma;
	}

	public String getMaDonViQuanLy() {
		return MaDonViQuanLy;
	}

	public void setMaDonViQuanLy(String maDonViQuanLy) {
		MaDonViQuanLy = maDonViQuanLy;
	}

	public String getMaVeTinh() {
		return MaVeTinh;
	}

	public void setMaVeTinh(String maVeTinh) {
		MaVeTinh = maVeTinh;
	}

	public String getTenLoaiNhaTram() {
		return TenLoaiNhaTram;
	}

	public void setTenLoaiNhaTram(String tenLoaiNhaTram) {
		TenLoaiNhaTram = tenLoaiNhaTram;
	}

	public String getTrangThai() {
		return TrangThai;
	}

	public void setTrangThai(String trangThai) {
		TrangThai = trangThai;
	}

	@AnnotationField(hienthi = true, tenNhan = "Địa chỉ", order = 3)
	String DiaChi;
	String DonViQuanLyId;
	String GhiChu;
	int Id;
	String IsUpdate;
	String Latitude1;
	String LoaiNhaTramId;
	String Longitude1;
	@AnnotationField(hienthi = true, tenNhan = "Mã", order = 1)
	String Ma;
	@AnnotationField(hienthi = true, tenNhan = "Đơn vị quản lý", order = 5)
	String MaDonViQuanLy;
	String MaVeTinh;
	@AnnotationField(hienthi = true, tenNhan = "Loại nhà trạm", order = 4)
	String TenLoaiNhaTram;
	@AnnotationField(hienthi = true, tenNhan = "Trạng thái", order = 8)
	String TrangThai;

	public Marker creatMarker(GoogleMap map, boolean showInfor, boolean setLonLat) {
		double lat;
		double lon;
		Marker m = null;
		try {
			lat = Double.parseDouble(getLatitude());
			lon = Double.parseDouble(getLongitude());
			LatLng latLng = new LatLng(lat, lon);
			MarkerOptions mko = new MarkerOptions();
			mko.position(latLng);

			mko.snippet("Đơn vị quản lý" + getMaDonViQuanLy());
			mko.anchor(0.5f, 1.0f);
			BitmapDescriptor ketcuoiIcon;
			ketcuoiIcon = BitmapDescriptorFactory.fromResource(R.drawable.con_dl);
			mko.icon(ketcuoiIcon);
			mko.title(getTenLoaiNhaTram() + ":" + getMa());

			m = map.addMarker(mko);
			if (showInfor) {
				m.showInfoWindow();
				map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 14));
			}

		} catch (Exception e) {
			// TODO: handle exception

		}

		return m;

	}

}
