package congnhanvienthong.entity.gtacs;



public class LoaiDoiTuong {
	private String idLoaiDoiTuong;
	private String tenHienThi;
	private String maDoiTuong;

	public LoaiDoiTuong(String idLoaiDoiTuong, String tenHienThi, String maDoiTuong) {
		super();
		this.idLoaiDoiTuong = idLoaiDoiTuong;
		this.tenHienThi = tenHienThi;
		this.maDoiTuong = maDoiTuong;
	}

	public String getMaDoiTuong() {
		return maDoiTuong;
	}

	public void setMaDoiTuong(String maDoiTuong) {
		this.maDoiTuong = maDoiTuong;
	}

	public String getTenHienThi() {
		return tenHienThi;
	}

	public void setTenHienThi(String tenHienThi) {
		this.tenHienThi = tenHienThi;
	}

	public String getIdLoaiDoiTuong() {
		return idLoaiDoiTuong;
	}

	public void setIdLoaiDoiTuong(String idLoaiDoiTuong) {
		this.idLoaiDoiTuong = idLoaiDoiTuong;
	}

}
