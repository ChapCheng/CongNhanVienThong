package congnhanvienthong.entity.pttb;

public class QuanHuyen {

	int ID_QUAN_HUYEN;
	String MA_QUAN_HUYEN;
	String TEN_QUAN_HUYEN;

	public QuanHuyen() {
		// TODO Auto-generated constructor stub
		setTEN_QUAN_HUYEN("--Tất cả--");
		setID_QUAN_HUYEN(0);
	}

	public int getID_QUAN_HUYEN() {
		return ID_QUAN_HUYEN;
	}

	public void setID_QUAN_HUYEN(int iD_QUAN_HUYEN) {
		ID_QUAN_HUYEN = iD_QUAN_HUYEN;
	}

	public String getMA_QUAN_HUYEN() {
		return MA_QUAN_HUYEN;
	}

	public void setMA_QUAN_HUYEN(String mA_QUAN_HUYEN) {
		MA_QUAN_HUYEN = mA_QUAN_HUYEN;
	}

	public String getTEN_QUAN_HUYEN() {
		return TEN_QUAN_HUYEN;
	}

	public void setTEN_QUAN_HUYEN(String tEN_QUAN_HUYEN) {
		TEN_QUAN_HUYEN = tEN_QUAN_HUYEN;
	}

}
