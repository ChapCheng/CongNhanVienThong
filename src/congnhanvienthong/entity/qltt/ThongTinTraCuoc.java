package congnhanvienthong.entity.qltt;

import java.util.ArrayList;

import control.AnnotationField;

public class ThongTinTraCuoc {
	@AnnotationField(hienthi = false, order = 1, tenNhan = "Mã Thanh Toán")
	private String ErrorCode;
	@AnnotationField(hienthi = false, order = 1, tenNhan = "Mã Thanh Toán")
	private String ErrorMessage;
	@AnnotationField(hienthi = true, order = 1, tenNhan = "Mã Thanh Toán")
	private String MaThanhToan;
	@AnnotationField(hienthi = true, order = 2, tenNhan = "Tên Thanh Toán")
	private String TenThanhToan;
	@AnnotationField(hienthi = true, order = 3, tenNhan = "Địa chỉ")
	private String DiaChi;

	private ArrayList<ThongTinNo> ThongTinNo;
	@AnnotationField(hienthi = true, order = 4, tenNhan = "Nợ")
	private String ThongTinNoString = "<br>";

	public String getErrorCode() {
		return ErrorCode;
	}

	public void setErrorCode(String errorCode) {
		ErrorCode = errorCode;
	}

	public String getErrorMessage() {
		return ErrorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}

	public String getMaThanhToan() {
		return MaThanhToan;
	}

	public void setMaThanhToan(String maThanhToan) {
		MaThanhToan = maThanhToan;
	}

	public String getTenThanhToan() {
		return TenThanhToan;
	}

	public void setTenThanhToan(String tenThanhToan) {
		TenThanhToan = tenThanhToan;
	}

	public String getDiaChi() {
		return DiaChi;
	}

	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}

	public ArrayList<congnhanvienthong.entity.qltt.ThongTinNo> getThongTinNo() {
		return ThongTinNo;
	}

	public void setThongTinNo(ArrayList<congnhanvienthong.entity.qltt.ThongTinNo> ThongTinNo) {
		this.ThongTinNo = ThongTinNo;
	}

	public String getThongTinNoString() {
		return ThongTinNoString;
	}

	public void setThongTinNoString() {
		for (int i = ThongTinNo.size(); i > 0; i--) {
			ThongTinNoString = ThongTinNoString + ThongTinNo.get(i - 1).toString() + "<br>";

		}
	}

}
