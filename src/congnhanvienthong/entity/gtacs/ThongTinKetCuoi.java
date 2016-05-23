package congnhanvienthong.entity.gtacs;

import java.io.Serializable;

import control.AnnotationField;

public class ThongTinKetCuoi implements Serializable{
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
	private
    long conTrong;
	boolean ALLOW_EDIT;
	public boolean isALLOW_EDIT() {
		return ALLOW_EDIT;
	}

	public void setALLOW_EDIT(boolean aLLOW_EDIT) {
		ALLOW_EDIT = aLLOW_EDIT;
	}

}
