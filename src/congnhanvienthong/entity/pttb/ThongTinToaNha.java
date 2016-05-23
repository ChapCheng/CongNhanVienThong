package congnhanvienthong.entity.pttb;

import control.AnnotationField;

public class ThongTinToaNha {
	int ID_TOA_NHA;
	@AnnotationField(hienthi = true, tenNhan = "Mã tòa nhà", order = 0)
	private

	String MA_TOA_NHA;
	@AnnotationField(hienthi = true, tenNhan = "Tên Tòa Nhà", order = 1)
	private

	String TEN_TOA_NHA;
	private String POS_LONG;
	private String POS_LAT;
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
	public void setMA_TOA_NHA(String mA_TOA_NHA) {
		MA_TOA_NHA = mA_TOA_NHA;
	}
	public String getTEN_TOA_NHA() {
		return TEN_TOA_NHA;
	}
	public void setTEN_TOA_NHA(String tEN_TOA_NHA) {
		TEN_TOA_NHA = tEN_TOA_NHA;
	}

}
