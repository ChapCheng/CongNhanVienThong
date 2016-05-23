package congnhanvienthong.entity.dhsc;

//@AnnotationClass(id = "kieuDo", name = "tenHienThi")
public class LoaiDo  {
	private String kieuDo;
	private String tenHienThi;
	private String maKieuDo;
	
	
	
	public LoaiDo(String kieuDo, String tenHienThi, String maKieuDo) {
		super();
		this.kieuDo = kieuDo;
		this.tenHienThi = tenHienThi;
		this.maKieuDo = maKieuDo;
	}
	public String getMaKieuDo() {
		return maKieuDo;
	}
	public void setMaKieuDo(String maKieuDo) {
		this.maKieuDo = maKieuDo;
	}
	public String getTenHienThi() {
		return tenHienThi;
	}
	public void setTenHienThi(String tenHienThi) {
		this.tenHienThi = tenHienThi;
	}
	public String getKieuDo() {
		return kieuDo;
	}
	public void setKieuDo(String kieuDo) {
		this.kieuDo = kieuDo;
	}

	

}
