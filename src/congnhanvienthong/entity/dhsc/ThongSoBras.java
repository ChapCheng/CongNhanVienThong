package congnhanvienthong.entity.dhsc;

import control.AnnotationField;

public class ThongSoBras {
	@AnnotationField(hienthi = true, order = 1, tenNhan = "Trạng thái")

	String trangThai;
	@AnnotationField(hienthi = true, order = 2, tenNhan = "IP")

	String iP;
	@AnnotationField(hienthi = true, order = 3, tenNhan = "Thời gian đăng nhập")

	String thoiGianDangNhap;

}
