package congnhanvienthong.entity.qlkn;

import control.AnnotationField;

public class TienTrinhPhieuXacMinhKhieuNai {
	
	@AnnotationField(hienthi = true, order = 0, tenNhan = "Ngày t.hiện")
	private String NgayThucHien;
	@AnnotationField(hienthi = true, order = 1, tenNhan = "N.vụ")
	private String NghiepVu;
	@AnnotationField(hienthi = false, order = 0, tenNhan = "ND t.hiện")
	private String NoiDungThucHien;
	@AnnotationField(hienthi = false, order = 1, tenNhan = "Người t.hiện")
	private String NguoiThucHien;
	@AnnotationField(hienthi = false, order = 2, tenNhan = "D.v t.hiện")
	private String DonViThucHien;
		

	public String getNgayThucHien() {
		return NgayThucHien;
	}

	public void setNgayThucHien(String p_NgayThucHien) {
		NgayThucHien = p_NgayThucHien;
	}

	public String getNghiepVu() {
		return NghiepVu;
	}

	public void getNghiepVu(String p_NghiepVu) {
		NghiepVu = p_NghiepVu;
	}
	public String getNoiDungThucHien() {
		return NoiDungThucHien;
	}
	public void setNoiDungThucHien(String p_NoiDungThucHien) {
		NoiDungThucHien = p_NoiDungThucHien;
	}
	public String getNguoiThucHien() {
		return NguoiThucHien;
	}

	public void setNguoiThucHien(String p_NguoiThucHien) {
		NguoiThucHien = p_NguoiThucHien;
	}
	public String getDonViThucHien() {
		return DonViThucHien;
	}

	public void setDonViThucHien(String p_DonViThucHien) {
		DonViThucHien = p_DonViThucHien;
	}	
	public TienTrinhPhieuXacMinhKhieuNai(String p_NgayThucHien,String p_NghiepVu,
			String p_NoiDungThucHien,String p_NguoiThucHien,String p_DonViThucHien) {
		super();
		NgayThucHien = p_NgayThucHien;
		NghiepVu = p_NghiepVu;
		NoiDungThucHien = p_NoiDungThucHien;
		NguoiThucHien = p_NguoiThucHien;
		DonViThucHien = p_DonViThucHien;
	}
public TienTrinhPhieuXacMinhKhieuNai() {
	// TODO Auto-generated constructor stub
}
	
	

}
