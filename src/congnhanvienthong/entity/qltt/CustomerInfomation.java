package congnhanvienthong.entity.qltt;


import java.util.ArrayList;

public class CustomerInfomation {
	
	private String ErrorCode;
	private String ErrorMessage;
	private String TenThanhToan;
	private String DiaChi;
	public String getDiaChi() {
		return DiaChi;
	}
	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}
	private String MaThanhToan;
	private ArrayList<DebtInfomation> ThongTinNo;
	
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
	public ArrayList<DebtInfomation> getThongTinNo() {
		return ThongTinNo;
	}
	public void setThongTinNo(ArrayList<DebtInfomation> thongTinNo) {
		ThongTinNo = thongTinNo;
	}
	
	
	
}

