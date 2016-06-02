package congnhanvienthong.entity.pttb;

import com.congnhanvienthong.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import control.AnnotationField;

public class ThongTinToaNha {
	Marker maker;
	@AnnotationField(hienthi = true, tenNhan = "Phạm vi mạng", order = 4)
	String PHAMVI_MANG;
	@AnnotationField(hienthi = true, tenNhan = "Sẵn sàng PTTB", order = 5)
	String SANSANG_PTTB_TN;
	int ID_TOA_NHA;
	@AnnotationField(hienthi = true, tenNhan = "Mã tòa nhà", order = 0)
	String MA_TOA_NHA;
	@AnnotationField(hienthi = true, tenNhan = "Tên tòa nhà", order = 1)
	String TEN_TOA_NHA;
	@AnnotationField(hienthi = true, tenNhan = "Đ.C tòa nhà", order = 2)
	String DIACHI_TOA_NHA;
	String DONVI_TIEP_THI;
	String DONVI_QUAN_LY;
	@AnnotationField(hienthi = false, tenNhan = "Số tầng", order = 3)
	int SO_TANG;
	@AnnotationField(hienthi = false, tenNhan = "Số căn hộ", order = 4)
	int SO_CAN_HO;
	@AnnotationField(hienthi = false, tenNhan = "Số văn phòng", order = 5)
	int SO_VAN_PHONG;
	@AnnotationField(hienthi = false, tenNhan = "Diện tích", order = 6)
	String DIEN_TICH;
	@AnnotationField(hienthi = true, tenNhan = "Phạm vi đầu tư", order = 3)
	String PHAMVI_DAUTU_MANG;

	public String getDIACHI_TOA_NHA() {
		return DIACHI_TOA_NHA;
	}

	public void setDIACHI_TOA_NHA(String dIACHI_TOA_NHA) {
		DIACHI_TOA_NHA = dIACHI_TOA_NHA;
	}

	public String getDONVI_TIEP_THI() {
		return DONVI_TIEP_THI;
	}

	public void setDONVI_TIEP_THI(String dONVI_TIEP_THI) {
		DONVI_TIEP_THI = dONVI_TIEP_THI;
	}

	public String getDONVI_QUAN_LY() {
		return DONVI_QUAN_LY;
	}

	public void setDONVI_QUAN_LY(String dONVI_QUAN_LY) {
		DONVI_QUAN_LY = dONVI_QUAN_LY;
	}

	public int getSO_TANG() {
		return SO_TANG;
	}

	public void setSO_TANG(int sO_TANG) {
		SO_TANG = sO_TANG;
	}

	public int getSO_CAN_HO() {
		return SO_CAN_HO;
	}

	public void setSO_CAN_HO(int sO_CAN_HO) {
		SO_CAN_HO = sO_CAN_HO;
	}

	public int getSO_VAN_PHONG() {
		return SO_VAN_PHONG;
	}

	public void setSO_VAN_PHONG(int sO_VAN_PHONG) {
		SO_VAN_PHONG = sO_VAN_PHONG;
	}

	public String getDIEN_TICH() {
		return DIEN_TICH;
	}

	public void setDIEN_TICH(String dIEN_TICH) {
		DIEN_TICH = dIEN_TICH;
	}

	public String getPHAMVI_DAUTU_MANG() {
		return PHAMVI_DAUTU_MANG;
	}

	public void setPHAMVI_DAUTU_MANG(String pHAMVI_DAUTU_MANG) {
		PHAMVI_DAUTU_MANG = pHAMVI_DAUTU_MANG;
	}

	public String getTYLE_PHANCHIA_DOANHTHU() {
		return TYLE_PHANCHIA_DOANHTHU;
	}

	public void setTYLE_PHANCHIA_DOANHTHU(String tYLE_PHANCHIA_DOANHTHU) {
		TYLE_PHANCHIA_DOANHTHU = tYLE_PHANCHIA_DOANHTHU;
	}

	public String getQUAN_HUYEN() {
		return QUAN_HUYEN;
	}

	public void setQUAN_HUYEN(String qUAN_HUYEN) {
		QUAN_HUYEN = qUAN_HUYEN;
	}

	public String getPHUONG_XA() {
		return PHUONG_XA;
	}

	public String getPHAMVI_MANG() {
		return PHAMVI_MANG;
	}

	public void setPHAMVI_MANG(String pHAMVI_MANG) {
		PHAMVI_MANG = pHAMVI_MANG;
	}

	public String getSANSANG_PTTB_TN() {
		return SANSANG_PTTB_TN;
	}

	public void setSANSANG_PTTB_TN(String sANSANG_PTTB_TN) {
		SANSANG_PTTB_TN = sANSANG_PTTB_TN;
	}

	public void setPHUONG_XA(String pHUONG_XA) {
		PHUONG_XA = pHUONG_XA;
	}

	public String getDOI_TAC() {
		return DOI_TAC;
	}

	public void setDOI_TAC(String dOI_TAC) {
		DOI_TAC = dOI_TAC;
	}

	String TYLE_PHANCHIA_DOANHTHU;
	String QUAN_HUYEN;
	String PHUONG_XA;
	String DOI_TAC;
	String POS_LONG;
	String POS_LAT;

	public Marker getMaker() {
		return maker;
	}

	public void setMaker(Marker maker) {
		this.maker = maker;
	}

	public String getPOS_LONG() {
		return POS_LONG;
	}

	public void setPOS_LONG(String pOS_LONG) {
		POS_LONG = pOS_LONG;
	}

	public String getPOS_LAT() {
		return POS_LAT;
	}

	public void setPOS_LAT(String pOS_LAT) {
		POS_LAT = pOS_LAT;
	}

	public String getMA_TOA_NHA() {
		return MA_TOA_NHA;
	}

	public int getID_TOA_NHA() {
		return ID_TOA_NHA;
	}

	public void setID_TOA_NHA(int iD_TOA_NHA) {
		ID_TOA_NHA = iD_TOA_NHA;
	}

	public void setMA_TOA_NHA(String mA_TOA_NHA) {
		MA_TOA_NHA = mA_TOA_NHA;
	}

	public String getTEN_TOA_NHA() {
		return TEN_TOA_NHA;
	}

	public void setTEN_TOA_NHA(String tEN_TOA_NHA) {
		TEN_TOA_NHA = tEN_TOA_NHA;
	}

	public Marker creatMarker(GoogleMap map, boolean setDrag, boolean showInfor) {
		double lat = 0;
		double lon = 0;
		Marker m = null;
		try {
			lat = Double.parseDouble(getPOS_LAT());
			lon = Double.parseDouble(getPOS_LONG());
			LatLng latLng = new LatLng(lat, lon);
			MarkerOptions mko = new MarkerOptions();
			mko.position(latLng);

			mko.anchor(0.5f, 1.0f);
			BitmapDescriptor ketcuoiIcon;
			ketcuoiIcon = BitmapDescriptorFactory.fromResource(R.drawable.het);
			mko.icon(ketcuoiIcon);
			mko.title(getTEN_TOA_NHA() + "\n Tỷ lệ doanh thu :" + getTYLE_PHANCHIA_DOANHTHU());
			mko.snippet("Sẵn sàng PTTB : " + getSANSANG_PTTB_TN() + "\n Phạm vi mạng : " + getPHAMVI_MANG());

			mko.draggable(setDrag);
			m = map.addMarker(mko);
			if (showInfor) {
				m.showInfoWindow();
				map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 14));
			}

		} catch (Exception e) {
		}

		return m;

	}

}
