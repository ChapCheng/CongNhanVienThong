package congnhanvienthong.entity.dhsc;

import control.AnnotationField;

public class ThamSoDSLAM {
	@AnnotationField(hienthi = true, order = 1, tenNhan = "Thông số cổng")
	String ThongSoCong;
	@AnnotationField(hienthi = true, order = 2, tenNhan = "Chiều dài dây")
	String ChieuDaiDay;
	@AnnotationField(hienthi = true, order = 3, tenNhan = "Trạng thái")
	String TrangThai;
	@AnnotationField(hienthi = true, order = 4, tenNhan = "Tốc độ cổng")
	String TocDoCong;
	@AnnotationField(hienthi = true, order = 5, tenNhan = "Tốc độ thực tế down")
	String TocDoThucTeDown;
	@AnnotationField(hienthi = true, order = 6, tenNhan = "Tốc độ thực tế up")
	String TocDoThucTeUp;
	@AnnotationField(hienthi = true, order = 7, tenNhan = "Tốc độ tối đa down")
	String TocDoToiDaDown;
	@AnnotationField(hienthi = true, order = 8, tenNhan = "Tốc độ tối đa up")
	String TocDoToiDaUp;
	@AnnotationField(hienthi = true, order = 9, tenNhan = "suy hao down")
	String SuyHaoDown;
	@AnnotationField(hienthi = true, order = 10, tenNhan = "Suy hao up")
	String SuyHaoUp;
	@AnnotationField(hienthi = true, order = 11, tenNhan = "Tạp âm down")
	String TySoTHTapAmDown;
	@AnnotationField(hienthi = true, order = 12, tenNhan = "Tạp âm up")
	String TySoTHTapAmUp;
	@AnnotationField(hienthi = true, order = 13, tenNhan = "Lỗi")
	String Error;

}
