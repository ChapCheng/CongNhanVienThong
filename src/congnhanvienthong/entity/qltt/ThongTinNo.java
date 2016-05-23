package congnhanvienthong.entity.qltt;

public class ThongTinNo {

	private String Thang, Nam, TienNo, ConNo;

	public String getThang() {
		return Thang;
	}

	public void setThang(String thang) {
		Thang = thang;
	}

	public String getNam() {
		return Nam;
	}

	public void setNam(String nam) {
		Nam = nam;
	}

	public String getTienNo() {
		return TienNo;
	}

	public void setTienNo(String tienNo) {
		TienNo = tienNo;
	}

	public String getConNo() {
		if (ConNo == null || ConNo.trim().equals(""))
			return "0";
		else

			return ConNo;
	}

	public void setConNo(String conNo) {
		ConNo = conNo;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if (ConNo == null || ConNo.trim().equals(""))
			ConNo = "0";
		String res = "&nbsp<b>Kỳ cước: </b>";
		res = res + "<i>" + Thang + "-" + Nam + "</i>";
		res = res + "&nbsp<b>.Tổng tiền/còn nợ: </b>";
		res = res + TienNo + "/" + ConNo;

		return res;
	}

}
