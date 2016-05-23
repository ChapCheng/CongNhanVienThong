package congnhanvienthong.entity.dhsc;

public class NhanVien {
	/**
	 * 
	 */
	String ID;
	String TenNhanVien;

	public String getId() {
		return ID;
	}

	public void setId(String id) {
		ID = id;
	}

	public String getTenNhanVien() {
		return TenNhanVien;
	}

	public void setTenNhanVien(String tenNhanVien) {
		TenNhanVien = tenNhanVien;
	}

	public NhanVien(String id, String tenNhanVien) {
		super();
		ID = id;
		TenNhanVien = tenNhanVien;
	}

	public NhanVien() {
		// TODO Auto-generated constructor stub
	}

}
