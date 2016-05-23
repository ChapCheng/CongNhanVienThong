package congnhanvienthong.entity.gtacs;

import control.AnnotationField;

public class KetCuoi {
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
		//ThongTinCong = this.O_TOTAL_SIZE+"/"+this.IN_USED+"/"+this.FAULT_PORT;
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
	
	
	//LONGITUDE=105.854199; LATITUDE=21.027003; 
	//M_OBJECT_NAME=SB01-DTH/0101/0101; M_OBJECT_ADDRESS=pkt t8 58 quan su; 
	//IN_USED=1; O_TOTAL_SIZE=32; FAULT_PORT=0; KC=90.22204170

}
