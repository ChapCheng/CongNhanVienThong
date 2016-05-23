package congnhanvienthong.entity.pttb;

public class DoiTac {
	int ID_DOI_TAC;
	String MA_DOI_TAC;

	public DoiTac() {
		// TODO Auto-generated constructor stub
		setID_DOI_TAC(0);
		setTEN_DOI_TAC("----Tất cả----");
	}

	public int getID_DOI_TAC() {
		return ID_DOI_TAC;
	}

	public void setID_DOI_TAC(int iD_DOI_TAC) {
		ID_DOI_TAC = iD_DOI_TAC;
	}

	public String getMA_DOI_TAC() {
		return MA_DOI_TAC;
	}

	public void setMA_DOI_TAC(String mA_DOI_TAC) {
		MA_DOI_TAC = mA_DOI_TAC;
	}

	public String getTEN_DOI_TAC() {
		return TEN_DOI_TAC;
	}

	public void setTEN_DOI_TAC(String tEN_DOI_TAC) {
		TEN_DOI_TAC = tEN_DOI_TAC;
	}

	String TEN_DOI_TAC;

}
