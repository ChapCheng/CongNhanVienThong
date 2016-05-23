package congnhanvienthong.entity.dhsc;

import java.io.Serializable;

public class LoaiSua implements Serializable{
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private String IdLoaiSua;
	private String LoaiSuaText;
	public String getIdLoaiSua() {
		return IdLoaiSua;
	}
	public void setIdLoaiSua(String idLoaiSua) {
		IdLoaiSua = idLoaiSua;
	}
	public String getLoaiSuaText() {
		return LoaiSuaText;
	}
	public void setLoaiSuaText(String loaiSuaText) {
		LoaiSuaText = loaiSuaText;
	}
	public LoaiSua(String idLoaiSua, String loaiSuaText) {
		super();
		IdLoaiSua = idLoaiSua;
		LoaiSuaText = loaiSuaText;
	}
	public LoaiSua() {
		// TODO Auto-generated constructor stub
	}
	
	
}
