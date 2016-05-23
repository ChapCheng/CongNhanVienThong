package congnhanvienthong.entity.dhsc;

import java.io.Serializable;

public class LoaiNghiemThu implements Serializable {
	/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;
	private String IdNoiDung;
	private String NoiDung;
	public String getIdNoiDung() {
		return IdNoiDung;
	}
	public void setIdNoiDung(String idNoiDung) {
		IdNoiDung = idNoiDung;
	}
	public String getNoiDung() {
		return NoiDung;
	}
	public void setNoiDung(String noiDung) {
		NoiDung = noiDung;
	}
	public LoaiNghiemThu(String idNoiDung, String noiDung) {
		super();
		IdNoiDung = idNoiDung;
		NoiDung = noiDung;
	}
	public LoaiNghiemThu() {
		// TODO Auto-generated constructor stub
	}
	

}
