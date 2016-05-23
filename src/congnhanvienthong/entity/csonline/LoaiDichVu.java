package congnhanvienthong.entity.csonline;

public class LoaiDichVu {
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

	public LoaiDichVu() {
		// TODO Auto-generated constructor stub
		setID(0);
		setTen("-- Loai dịch vụ --");
	}

}
