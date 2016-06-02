package congnhanvienthong.entity.dhsc;

import control.AnnotationField;

public class TienTrinhSuaTTP {
	public String NgayNhap;

	public String getNgayNhap() {
		return NgayNhap;
	}

	public void setNgayNhap(String ngayNhap) {
		NgayNhap = ngayNhap;
	}

	private String ID;
	@AnnotationField(hienthi = false, order = 1, tenNhan = "Mã Dịch Vụ")
	private String Ma_Dv;
	private String Account;
	private String Ten_TB;
	private String DiaChi;
	private String SoLienHe_Kh;
	private String Ma_DonVi;
	@AnnotationField(hienthi = true, order = 1, tenNhan = "Thời gian")
	private String ThoiGian;

	@AnnotationField(hienthi = false, order = 0, tenNhan = "Vệ Tinh")
	private String VeTinh;

	private String NguoiNhap;

	@AnnotationField(hienthi = false, order = 0, tenNhan = "Người thực hiện")
	private String NguoiThucHien;
	@AnnotationField(hienthi = false, order = 1, tenNhan = "Số ĐT Người Thực Hiện")
	private String SoLienHe_NguoiThucHien;
	@AnnotationField(hienthi = true, order = 3, tenNhan = "Nội dung thực hiện")
	private String ndThucHien;

	@AnnotationField(hienthi = true, order = 2, tenNhan = "Nghiệp vụ")
	private String NghiepVu;
	@AnnotationField(hienthi = false, order = 3, tenNhan = "Nội dung xuất")
	private String ndXuat;

	private String isNghiemThu;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getMa_Dv() {
		return Ma_Dv;
	}

	public void setMa_Dv(String ma_Dv) {
		Ma_Dv = ma_Dv;
	}

	public String getAccount() {
		return Account;
	}

	public void setAccount(String account) {
		Account = account;
	}

	public String getTen_TB() {
		return Ten_TB;
	}

	public void setTen_TB(String ten_TB) {
		Ten_TB = ten_TB;
	}

	public String getDiaChi() {
		return DiaChi;
	}

	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}

	public String getSoLienHe_Kh() {
		return SoLienHe_Kh;
	}

	public void setSoLienHe_Kh(String soLienHe_Kh) {
		SoLienHe_Kh = soLienHe_Kh;
	}

	public String getMa_DonVi() {
		return Ma_DonVi;
	}

	public void setMa_DonVi(String ma_DonVi) {
		Ma_DonVi = ma_DonVi;
	}

	public String getNguoiNhap() {
		return NguoiNhap;
	}

	public void setNguoiNhap(String nguoiNhap) {
		NguoiNhap = nguoiNhap;
	}

	public String getNguoiThucHien() {
		return NguoiThucHien;
	}

	public void setNguoiThucHien(String nguoiThucHien) {
		NguoiThucHien = nguoiThucHien;
	}

	public String getSoLienHe_NguoiThucHien() {
		return SoLienHe_NguoiThucHien;
	}

	public void setSoLienHe_NguoiThucHien(String soLienHe_NguoiThucHien) {
		SoLienHe_NguoiThucHien = soLienHe_NguoiThucHien;
	}

	public String getThoiGian() {
		return ThoiGian;
	}

	public void setThoiGian(String thoiGian) {
		ThoiGian = thoiGian;
	}

	public String getVeTinh() {
		return VeTinh;
	}

	public void setVeTinh(String veTinh) {
		VeTinh = veTinh;
	}

	public String getNdThucHien() {
		return ndThucHien;
	}

	public void setNdThucHien(String ndThucHien) {
		this.ndThucHien = ndThucHien;
	}

	public String getNghiepVu() {
		return NghiepVu;
	}

	public void setNghiepVu(String nghiepVu) {
		NghiepVu = nghiepVu;
	}

	public String getIsNghiemThu() {
		return isNghiemThu;
	}

	public void setIsNghiemThu(String isNghiemThu) {
		this.isNghiemThu = isNghiemThu;
	}

	public String getNdXuat() {
		return ndXuat;
	}

	public void setNdXuat(String ndXuat) {
		this.ndXuat = ndXuat;
	}
}
