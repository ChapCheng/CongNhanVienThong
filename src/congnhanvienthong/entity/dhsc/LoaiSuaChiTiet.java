package congnhanvienthong.entity.dhsc;

public class LoaiSuaChiTiet {

	private String IdLoaiSuaChiTiet;
	private String LoaiSuaChiTietText;

	public String getIdLoaiSuaChiTiet() {
		return IdLoaiSuaChiTiet;
	}

	public void setIdLoaiSuaChiTiet(String idLoaiSuaChiTiet) {
		IdLoaiSuaChiTiet = idLoaiSuaChiTiet;
	}

	public String getLoaiSuaChiTietText() {
		return LoaiSuaChiTietText;
	}

	public void setLoaiSuaChiTietText(String loaiSuaChiTietText) {
		LoaiSuaChiTietText = loaiSuaChiTietText;
	}

	public LoaiSuaChiTiet(String idLoaiSuaChiTiet, String loaiSuaChiTietText) {
		super();
		IdLoaiSuaChiTiet = idLoaiSuaChiTiet;
		LoaiSuaChiTietText = loaiSuaChiTietText;
	}

	public LoaiSuaChiTiet() {
		// TODO Auto-generated constructor stub
	}

}
