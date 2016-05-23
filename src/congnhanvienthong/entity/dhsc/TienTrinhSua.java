package congnhanvienthong.entity.dhsc;

import control.AnnotationField;

public class TienTrinhSua {
	
	
	
	@AnnotationField(hienthi = true, order = 0, tenNhan = "Vệ Tinh")
	private String 	VeTinh;
	@AnnotationField(hienthi = true, order = 1, tenNhan = "Nghiệp vụ")
	private String NghiepVu; 
	
	// field ẩn
	@AnnotationField(hienthi = false, order = 1, tenNhan = "Thời gian")
	private String ThoiGian;
	@AnnotationField(hienthi = false, order = 2, tenNhan = "Phòng ban")
	private String MaPhongBan;
	@AnnotationField(hienthi = false, order = 0, tenNhan = "Người thực hiện")
	private String  NguoiThucHien;
	@AnnotationField(hienthi = false, order = 4, tenNhan = "Liên hệ")
	private String SoMayLH;
	@AnnotationField(hienthi = false, order = 3, tenNhan = "Nội dung")
	private String NoiDung;


	public String getMaPhongBan() {
		return MaPhongBan;
	}

	public void setMaPhongBan(String maPhongBan) {
		MaPhongBan = maPhongBan;
	}

	public String getNguoiThucHien() {
		return NguoiThucHien;
	}

	public void setNguoiThucHien(String nguoiThucHien) {
		NguoiThucHien = nguoiThucHien;
	}

	public String getSoMayLH() {
		return SoMayLH;
	}

	public void setSoMayLH(String soMayLH) {
		SoMayLH = soMayLH;
	}

	public String getNoiDung() {
		return NoiDung;
	}

	public void setNoiDung(String noiDung) {
		NoiDung = noiDung;
	}

	public String getNghiepVu() {
		return NghiepVu;
	}

	public void setNghiepVu(String nghiepVu) {
		NghiepVu = nghiepVu;
	}

	public String getVeTinh() {
		return VeTinh;
	}

	public void setVeTinh(String veTinh) {
		VeTinh = veTinh;
	}

	public TienTrinhSua(String maPhongBan, String nguoiThucHien,
			String soMayLH, String noiDung, String nghiepVu, String veTinh,
			String thoiGian) {
		super();
		MaPhongBan = maPhongBan;
		NguoiThucHien = nguoiThucHien;
		SoMayLH = soMayLH;
		NoiDung = noiDung;
		NghiepVu = nghiepVu;
		VeTinh = veTinh;
		ThoiGian = thoiGian;
	}

	public String getThoiGian() {
		return ThoiGian;
	}

	public void setThoiGian(String thoiGian) {
		ThoiGian = thoiGian;
	}

	public TienTrinhSua() {
		super();

	}

}
