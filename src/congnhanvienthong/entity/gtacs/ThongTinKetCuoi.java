package congnhanvienthong.entity.gtacs;

import java.io.Serializable;

import com.congnhanvienthong.R;
import com.google.android.gms.internal.fl;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.R.bool;
import android.view.View;
import android.widget.Toast;
import control.AnnotationField;

public class ThongTinKetCuoi implements Serializable {
	private Marker marker;

	public long getM_OBJECT_FID() {
		return M_OBJECT_FID;
	}

	public void setM_OBJECT_FID(long m_OBJECT_FID) {
		M_OBJECT_FID = m_OBJECT_FID;
	}

	public String getM_OBJECT_NAME() {
		return M_OBJECT_NAME;
	}

	public void setM_OBJECT_NAME(String m_OBJECT_NAME) {
		M_OBJECT_NAME = m_OBJECT_NAME;
	}

	public String getM_OBJECT_ADDRESS() {
		return M_OBJECT_ADDRESS;
	}

	public void setM_OBJECT_ADDRESS(String m_OBJECT_ADDRESS) {
		M_OBJECT_ADDRESS = m_OBJECT_ADDRESS;
	}

	public long getO_TOTAL_SIZE() {
		return O_TOTAL_SIZE;
	}

	public void setO_TOTAL_SIZE(long o_TOTAL_SIZE) {
		O_TOTAL_SIZE = o_TOTAL_SIZE;
	}

	public long getIN_USED() {
		return IN_USED;
	}

	public void setIN_USED(long iN_USED) {
		IN_USED = iN_USED;
	}

	public String getM_OBJECT_SUB_TYPE_NAME() {
		return M_OBJECT_SUB_TYPE_NAME;
	}

	public void setM_OBJECT_SUB_TYPE_NAME(String m_OBJECT_SUB_TYPE_NAME) {
		M_OBJECT_SUB_TYPE_NAME = m_OBJECT_SUB_TYPE_NAME;
	}

	public String getOBJECT_TYPE_NAME() {
		return OBJECT_TYPE_NAME;
	}

	public void setOBJECT_TYPE_NAME(String oBJECT_TYPE_NAME) {
		OBJECT_TYPE_NAME = oBJECT_TYPE_NAME;
	}

	public String getLONGITUDE() {
		return LONGITUDE;
	}

	public void setLONGITUDE(String lONGITUDE) {
		LONGITUDE = lONGITUDE;
	}

	public String getLATITUDE() {
		return LATITUDE;
	}

	public void setLATITUDE(String lATITUDE) {
		LATITUDE = lATITUDE;
	}

	public String getEDIT_DATE() {
		return EDIT_DATE;
	}

	public void setEDIT_DATE(String eDIT_DATE) {
		EDIT_DATE = eDIT_DATE;
	}

	public String getEDITED_BY() {
		return EDITED_BY;
	}

	public void setEDITED_BY(String eDITED_BY) {
		EDITED_BY = eDITED_BY;
	}

	public long getConTrong() {
		return conTrong;
	}

	public void setConTrong(long conTrong) {
		this.conTrong = conTrong;
	}

	long M_OBJECT_FID;
	@AnnotationField(hienthi = true, tenNhan = "Tên Kết Cuối", order = 0)
	String M_OBJECT_NAME;
	@AnnotationField(hienthi = true, tenNhan = "Đ.c kết cuối", order = 1)
	String M_OBJECT_ADDRESS;
	@AnnotationField(hienthi = true, tenNhan = "Dung lượng thiết kế", order = 2)
	long O_TOTAL_SIZE;
	@AnnotationField(hienthi = true, tenNhan = "Đã sử dụng", order = 3)

	long IN_USED;
	String M_OBJECT_SUB_TYPE_NAME;
	String OBJECT_TYPE_NAME;
	String LONGITUDE;
	String LATITUDE;
	@AnnotationField(hienthi = false, tenNhan = "Ngày cập nhật", order = 2)
	String EDIT_DATE;
	@AnnotationField(hienthi = false, tenNhan = "Người cập nhật", order = 2)
	String EDITED_BY;
	@AnnotationField(hienthi = true, tenNhan = "Còn trống", order = 4)
	private long conTrong;
	boolean ALLOW_EDIT;

	public boolean isALLOW_EDIT() {
		return ALLOW_EDIT;
	}

	public void setALLOW_EDIT(boolean aLLOW_EDIT) {
		ALLOW_EDIT = aLLOW_EDIT;
	}

	public Marker creatMaker(GoogleMap map, boolean setLonLat, boolean setDrag, boolean showInfor) {
		double lat = 0;
		double lon = 0;
		boolean flag = true;
		try {
			lat = Double.parseDouble(getLATITUDE());
			lon = Double.parseDouble(getLONGITUDE());

		} catch (Exception e) {
			flag = false;
		}
		if (flag) {
			LatLng latLng = new LatLng(lat, lon);
			MarkerOptions mko = new MarkerOptions();
			mko.position(latLng);

			int con = 0;
			try {
				con = (int) (getO_TOTAL_SIZE() - getIN_USED());
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

			mko.draggable(setDrag);
			Marker m = map.addMarker(mko);
			if (showInfor) {
				m.showInfoWindow();
				map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 14));
			}
			setMarker(m);
			return m;
		} else {
			return null;
		}

	}

	public Marker getMarker() {
		return marker;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}

}
