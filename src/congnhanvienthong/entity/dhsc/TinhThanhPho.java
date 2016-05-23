package congnhanvienthong.entity.dhsc;

import java.io.Serializable;

public class TinhThanhPho implements Serializable {
	private String Id_ttpho;
	private String Ten_ttpho;
	private String Ma_Ttp;

	public String getId_ttpho() {
		return Id_ttpho;
	}

	public void setId_ttpho(String id_ttpho) {
		Id_ttpho = id_ttpho;
	}

	public String getTen_ttpho() {
		return Ten_ttpho;
	}

	public void setTen_ttpho(String ten_ttpho) {
		Ten_ttpho = ten_ttpho;
	}

	public String getMa_Ttp() {
		return Ma_Ttp;
	}

	public void setMa_Ttp(String ma_Ttp) {
		Ma_Ttp = ma_Ttp;
	}

}
