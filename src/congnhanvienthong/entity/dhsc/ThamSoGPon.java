package congnhanvienthong.entity.dhsc;

import control.AnnotationField;

public class ThamSoGPon {
	@AnnotationField(hienthi = true, order = 4, tenNhan = "Nhiệt độ")
	String nhietDo;
	@AnnotationField(hienthi = true, order = 1, tenNhan = "Dòng phân cực")
	String dongDienPhanCuc;
	@AnnotationField(hienthi = true, order = 2, tenNhan = "Điện áp")
	String dienAp;
	@AnnotationField(hienthi = true, order = 3, tenNhan = "Công suất thu")
	String congSuatThu;
	@AnnotationField(hienthi = false, order = 4, tenNhan = "Công suất phát")
	String congSuatPhat;
	@AnnotationField(hienthi = true, order = 5, tenNhan = "Chất lượng cáp")
	String chatLuongCap;

}
