package congnhanvienthong.entity.gtacs;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import control.AnnotationField;

public class HoSoThanhThai {
	@AnnotationField(hienthi = true, order = 2, tenNhan = "Mã dịch vụ")
	String MA_DICH_VU;
	@AnnotationField(hienthi = true, order = 2, tenNhan = "Loại hồ sơ")
	String TEN_LOAI;
	@AnnotationField(hienthi = true, order = 2, tenNhan = "Hình thức")
	String TEN_HINH_THUC;
	@AnnotationField(hienthi = true, order = 2, tenNhan = "Trạng thái")
	String TEN_TRANG_THAI;
	@AnnotationField(hienthi = true, order = 2, tenNhan = "Tên")
	String TEN;
	String NGAY_BAT_DAU;

	@Override
	public String toString() {

		String res = "<br>&nbsp&nbsp&nbsp<b><i>Tên: </b>" + TEN + "</i>";
		res = res + "<br>&nbsp&nbsp&nbsp<b><i>Loại hồ sơ: </b>" + TEN_LOAI + "</i>";
		res = res + "<br>&nbsp&nbsp&nbsp<b><i>Hình thức: </b>" + TEN_HINH_THUC + "</i>";
		res = res + "<br>&nbsp&nbsp&nbsp<b><i>Trạng thái: </b>" + TEN_TRANG_THAI + "</i>";
		

		try {
			DateFormat stringtoDate = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
			Date date = stringtoDate.parse(NGAY_BAT_DAU);
			DateFormat datetoString = new SimpleDateFormat("EEEE,dd-MM-yyyy");
			String datechuan = datetoString.format(date);
			res = res + "<br>&nbsp&nbsp&nbsp<b><i>Ngày bắt đầu: </b>" + datechuan + "</i>";
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res = res + "<br>&nbsp&nbsp&nbsp<b><i>Trạng thái: </b>" + NGAY_BAT_DAU + "</i>";
		}
		return res;
	}
}
