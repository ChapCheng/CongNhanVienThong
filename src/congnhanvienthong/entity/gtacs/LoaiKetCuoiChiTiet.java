package congnhanvienthong.entity.gtacs;

import java.io.Serializable;

public class LoaiKetCuoiChiTiet implements Serializable{
	  long ID;
	  String NAME;
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}

}
