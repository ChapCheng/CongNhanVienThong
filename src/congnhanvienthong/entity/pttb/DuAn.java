package congnhanvienthong.entity.pttb;

public class DuAn {
	int ID_DU_AN;

	public DuAn() {
		// TODO Auto-generated constructor stub
		setTEN_DU_AN("--Tất cả--");
		setID_DU_AN(0);
	}

	public int getID_DU_AN() {
		return ID_DU_AN;
	}

	public void setID_DU_AN(int iD_DU_AN) {
		ID_DU_AN = iD_DU_AN;
	}

	public String getMA_DU_AN() {
		return MA_DU_AN;
	}

	public void setMA_DU_AN(String mA_DU_AN) {
		MA_DU_AN = mA_DU_AN;
	}

	public String getTEN_DU_AN() {
		return TEN_DU_AN;
	}

	public void setTEN_DU_AN(String tEN_DU_AN) {
		TEN_DU_AN = tEN_DU_AN;
	}

	String MA_DU_AN;
	String TEN_DU_AN;

}
