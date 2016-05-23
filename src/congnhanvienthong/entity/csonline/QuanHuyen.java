package congnhanvienthong.entity.csonline;

public class QuanHuyen {
	int ID;
	String Ten;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getTen() {
		return Ten;
	}

	public void setTen(String ten) {
		Ten = ten;
	}

	public QuanHuyen() {
		// TODO Auto-generated constructor stub
		setID(0);
		setTen("-- Chọn quận huyện --");
	}

}
