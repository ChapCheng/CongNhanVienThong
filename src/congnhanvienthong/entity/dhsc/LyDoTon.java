package congnhanvienthong.entity.dhsc;

import java.io.Serializable;

public class LyDoTon implements Serializable{
	/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;
	private String IdLyDoTon;
	private String TenLyDoTon;
	
	public LyDoTon() {
		// TODO Auto-generated constructor stub
	}

	public String getIdLyDoTon() {
		return IdLyDoTon;
	}

	public void setIdLyDoTon(String idLyDoTon) {
		IdLyDoTon = idLyDoTon;
	}

	public String getTenLyDoTon() {
		return TenLyDoTon;
	}

	public void setTenLyDoTon(String tenLyDoTon) {
		TenLyDoTon = tenLyDoTon;
	}
	

}
