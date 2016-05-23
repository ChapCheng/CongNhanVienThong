package congnhanvienthong.entity.tracuuchung;

import control.AnnotationField;

public class ThongTinTraCuuChung {
	@AnnotationField(hienthi = true, order = 0, tenNhan = "Tên Khách Hàng")
	String TenKhachHang;
	String MaKhachHang;
	@AnnotationField(hienthi = true, order = 1, tenNhan = "Địa chỉ khách  hàng")
	String DiaChiKhachHang;
	@AnnotationField(hienthi = true, order = 2, tenNhan = "Tên Thanh Toán")
	String TenThanhToan;
	String MaThanhToan;
	@AnnotationField(hienthi = true, order = 3, tenNhan = "Điạ chỉ thanh toán")
	String DiaChiThanhToan;
	String TenThueBao;
	String MaThueBao;
	String DiaChiThueBao;
	String MaSoThue;
	String SoGiayTo;
	@AnnotationField(hienthi = false, order = 4, tenNhan = "Điện thoại liên hệ")
	String DienThoaiLienHe;
	String MaTuyenThu;
	String SoTaiKhoan;
	String TenDichVuVienThong;
	int DichVuVienThongId;
	int KhachHangId;
	int ThanhToanId;
	int ThueBaoId;
	int TinhTpId;

	public String getTenKhachHang() {
		return TenKhachHang;
	}

	public void setTenKhachHang(String tenKhachHang) {
		TenKhachHang = tenKhachHang;
	}

	public String getMaKhachHang() {
		return MaKhachHang;
	}

	public void setMaKhachHang(String maKhachHang) {
		MaKhachHang = maKhachHang;
	}

	public String getDiaChiKhachHang() {
		return DiaChiKhachHang;
	}

	public void setDiaChiKhachHang(String diaChiKhachHang) {
		DiaChiKhachHang = diaChiKhachHang;
	}

	public String getTenThanhToan() {
		return TenThanhToan;
	}

	public void setTenThanhToan(String tenThanhToan) {
		TenThanhToan = tenThanhToan;
	}

	public String getMaThanhToan() {
		return MaThanhToan;
	}

	public void setMaThanhToan(String maThanhToan) {
		MaThanhToan = maThanhToan;
	}

	public String getDiaChiThanhToan() {
		return DiaChiThanhToan;
	}

	public void setDiaChiThanhToan(String diaChiThanhToan) {
		DiaChiThanhToan = diaChiThanhToan;
	}

	public String getTenThueBao() {
		return TenThueBao;
	}

	public void setTenThueBao(String tenThueBao) {
		TenThueBao = tenThueBao;
	}

	public String getMaThueBao() {
		return MaThueBao;
	}

	public void setMaThueBao(String maThueBao) {
		MaThueBao = maThueBao;
	}

	public String getDiaChiThueBao() {
		return DiaChiThueBao;
	}

	public void setDiaChiThueBao(String diaChiThueBao) {
		DiaChiThueBao = diaChiThueBao;
	}

	public String getMaSoThue() {
		return MaSoThue;
	}

	public void setMaSoThue(String maSoThue) {
		MaSoThue = maSoThue;
	}

	public String getSoGiayTo() {
		return SoGiayTo;
	}

	public void setSoGiayTo(String soGiayTo) {
		SoGiayTo = soGiayTo;
	}

	public String getDienThoaiLienHe() {
		return DienThoaiLienHe;
	}

	public void setDienThoaiLienHe(String dienThoaiLienHe) {
		DienThoaiLienHe = dienThoaiLienHe;
	}

	public String getMaTuyenThu() {
		return MaTuyenThu;
	}

	public void setMaTuyenThu(String maTuyenThu) {
		MaTuyenThu = maTuyenThu;
	}

	public String getSoTaiKhoan() {
		return SoTaiKhoan;
	}

	public void setSoTaiKhoan(String soTaiKhoan) {
		SoTaiKhoan = soTaiKhoan;
	}

	public String getTenDichVuVienThong() {
		return TenDichVuVienThong;
	}

	public void setTenDichVuVienThong(String tenDichVuVienThong) {
		TenDichVuVienThong = tenDichVuVienThong;
	}

	public int getDichVuVienThongId() {
		return DichVuVienThongId;
	}

	public void setDichVuVienThongId(int dichVuVienThongId) {
		DichVuVienThongId = dichVuVienThongId;
	}

	public int getKhachHangId() {
		return KhachHangId;
	}

	public void setKhachHangId(int khachHangId) {
		KhachHangId = khachHangId;
	}

	public int getThanhToanId() {
		return ThanhToanId;
	}

	public void setThanhToanId(int thanhToanId) {
		ThanhToanId = thanhToanId;
	}

	public int getThueBaoId() {
		return ThueBaoId;
	}

	public void setThueBaoId(int thueBaoId) {
		ThueBaoId = thueBaoId;
	}

	public int getTinhTpId() {
		return TinhTpId;
	}

	public void setTinhTpId(int tinhTpId) {
		TinhTpId = tinhTpId;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub

		return TenKhachHang + " " + DiaChiKhachHang + " " + TenThanhToan + " " + DiaChiThanhToan + " "
				+ DienThoaiLienHe;
	}

}
