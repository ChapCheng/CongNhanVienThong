package congnhanvienthong.entity.gtacs;

import control.AnnotationField;

public class ThongTinDichVu {

	@AnnotationField(hienthi = true, tenNhan = "Mã Thuê Bao", order = 0)
	private String MaDichVu;

	@AnnotationField(hienthi = false, tenNhan = "Tên Thuê Bao", order = 2)
	private String SubName;

	@AnnotationField(hienthi = false, tenNhan = "Địa Chỉ", order = 2)
	private String SubAddress;

	@AnnotationField(hienthi = true, tenNhan = "Vệ Tinh", order = 1)
	private String SwitchCentreClli;
	private String SerFno;

	public String getSerFno() {
		return SerFno;
	}

	public void setSerFno(String serFno) {
		SerFno = serFno;
	}

	public String getMaDichVu() {
		return MaDichVu;
	}

	public void setMaDichVu(String maDichVu) {
		MaDichVu = maDichVu;
	}

	public String getSubName() {
		return SubName;
	}

	public void setSubName(String subName) {
		SubName = subName;
	}

	public String getSubAddress() {
		return SubAddress;
	}

	public void setSubAddress(String subAddress) {
		SubAddress = subAddress;
	}

	public String getSwitchCentreClli() {
		return SwitchCentreClli;
	}

	public void setSwitchCentreClli(String switchCentreClli) {
		SwitchCentreClli = switchCentreClli;
	}

}
