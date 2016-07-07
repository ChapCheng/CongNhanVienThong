package congnhanvienthong.entity.gtacs;

import com.congnhanvienthong.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import control.AnnotationField;

public class KetCuoi {
	Marker marker;

	public Marker getMarker() {
		return this.marker;
	}

	public void setMarker(Marker mar) {
		this.marker = mar;
	}

	private String LONGITUDE;
	private String LATITUDE;
	@AnnotationField(hienthi = true, order = 0, tenNhan = "Tên đối tượng")
	private String M_OBJECT_NAME;
	@AnnotationField(hienthi = true, order = 3, tenNhan = "Khoảng cách")
	private String KC;
	@AnnotationField(hienthi = true, order = 1, tenNhan = "Địa chỉ  kết cuối")
	private String M_OBJECT_ADDRESS;
	private String IN_USED, O_TOTAL_SIZE, FAULT_PORT;
	@AnnotationField(hienthi = true, order = 2, tenNhan = "Tổng số cổng/đã dùng/hỏng/còn")
	private String ThongTinCong;

	public String getThongTinCong() {
		return ThongTinCong;
	}

	public void setThongTinCong(String thongTinCong) {
		// ThongTinCong =
		// this.O_TOTAL_SIZE+"/"+this.IN_USED+"/"+this.FAULT_PORT;
		this.ThongTinCong = thongTinCong;
	}

	public String getLongitude() {
		return LONGITUDE;
	}

	public void setLongitude(String longitude) {
		LONGITUDE = longitude;
	}

	public String getLatitude() {
		return LATITUDE;
	}

	public void setLatitude(String latitude) {
		LATITUDE = latitude;
	}

	public String getM_OBJECT_NAME() {
		return M_OBJECT_NAME;
	}

	public void setM_OBJECT_NAME(String M_OBJECT_NAME) {
		this.M_OBJECT_NAME = M_OBJECT_NAME;
	}

	public String getRange() {
		return KC;
	}

	public void setRange(String range) {
		KC = range;
	}

	public String getM_OBJECT_ADDRESS() {
		return M_OBJECT_ADDRESS;
	}

	public void setM_OBJECT_ADDRESS(String m_OBJECT_ADDRESS) {
		M_OBJECT_ADDRESS = m_OBJECT_ADDRESS;
	}

	public String getO_TOTAL_SIZE() {
		return O_TOTAL_SIZE;
	}

	public void setO_TOTAL_SIZE(String o_TOTAL_SIZE) {
		O_TOTAL_SIZE = o_TOTAL_SIZE;
	}

	public String getIN_USED() {
		return IN_USED;
	}

	public void setIN_USED(String iN_USED) {
		IN_USED = iN_USED;
	}

	public String getFAULT_PORT() {
		return FAULT_PORT;
	}

	public void setFAULT_PORT(String fAULT_PORT) {
		FAULT_PORT = fAULT_PORT;
	}

	public Marker creatMaker(GoogleMap map, boolean setDrag, boolean showInfor) {
		double lat = 0;
		double lon = 0;
		Marker marker = null;
		try {
			lat = Double.parseDouble(getLatitude());
			lon = Double.parseDouble(getLongitude());
			LatLng latLng = new LatLng(lat, lon);
			MarkerOptions mko = new MarkerOptions();
			mko.position(latLng);

			int con = 0;
			try {
				con = Integer.parseInt(getO_TOTAL_SIZE()) - Integer.parseInt(getIN_USED());
			} catch (Exception e) {
				// TODO: handle exception
			}
			mko.snippet("Dung lượng(tổng/đã dùng/còn trống) :" + getO_TOTAL_SIZE() + "/" + getIN_USED() + "/" + con);
			mko.anchor(0.5f, 1.0f);
			BitmapDescriptor ketcuoiIcon;
			if (con == 0)
				ketcuoiIcon = BitmapDescriptorFactory.fromResource(R.drawable.het);
			else {
				ketcuoiIcon = BitmapDescriptorFactory.fromResource(R.drawable.con_dl);
			}
			mko.icon(ketcuoiIcon);
			mko.title(getM_OBJECT_NAME());
			mko.draggable(true);
			if (showInfor) {
				marker = map.addMarker(mko);
				marker.showInfoWindow();
			} else {
				marker = map.addMarker(mko);
			}
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 14));

		} catch (Exception e) {
		}
		setMarker(marker);
		return marker;

	}
	

}
