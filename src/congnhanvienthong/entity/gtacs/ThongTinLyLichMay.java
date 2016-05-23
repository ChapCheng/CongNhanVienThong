package congnhanvienthong.entity.gtacs;

import control.AnnotationField;

public class ThongTinLyLichMay {
	@AnnotationField(hienthi = true, order = 1, tenNhan = "Mã Dịch Vụ")
	String MaDichVu;
	@AnnotationField(hienthi = true, order = 2, tenNhan = "Account")
	String Account;
	@AnnotationField(hienthi = true, order = 3, tenNhan = "Tên Loại Thiết Bị")
	String TenLoaiThietBi;
	@AnnotationField(hienthi = true, order = 4, tenNhan = "NE")
	String NE;
	@AnnotationField(hienthi = true, order = 5, tenNhan = "Phiến TB")
	String TB;
	@AnnotationField(hienthi = true, order = 6, tenNhan = "Phiến TD")
	String TD;
	@AnnotationField(hienthi = true, order = 7, tenNhan = "Tên thuê bao")
	String MSubName;
	@AnnotationField(hienthi = true, order = 8, tenNhan = "Địa chỉ thuê bao")
	String MSubAddress;
	@AnnotationField(hienthi = true, order = 9, tenNhan = "Địa chỉ thực tế")
	String DcDvSd;
	@AnnotationField(hienthi = true, order = 10, tenNhan = "Điện thoại liên hệ")
	String DienthoaiLienhe;
	@AnnotationField(hienthi = true, order = 11, tenNhan = "Ngày hòa mạng")
	String NgayHoaMang;
	@AnnotationField(hienthi = true, order = 12, tenNhan = "Trạng thái")
	String TenTrangThai;
	@AnnotationField(hienthi = true, order = 13, tenNhan = "Cáp Ngọn (Dung lượng/Đôi,sợi số)")
	String CAP_NGON;
	@AnnotationField(hienthi = true, order = 14, tenNhan = "Cáp gốc (Dung lượng/Đôi,sợi số)")
	String CAP_GOC;
	@AnnotationField(hienthi = true, order = 15, tenNhan = "Kết cuối ngọn")
	String HOP_CUOI;
	@AnnotationField(hienthi = true, order = 16, tenNhan = "Địa chỉ kết cuối ngọn")
	String DIACHI_HOP_CUOI;
	@AnnotationField(hienthi = true, order = 17, tenNhan = "Spitter")
	String SPL_NAME;
	@AnnotationField(hienthi = true, order = 18, tenNhan = "Cổng spitter")
	String SPL_PORT;
	@AnnotationField(hienthi = false, order = 19, tenNhan = "Cổng PON")
	String PortInfoQLTNM;
	@AnnotationField(hienthi = true, order = 19, tenNhan = "Cổng(QLTNM)")
	String SPL_OLT;
	@AnnotationField(hienthi = true, order = 20, tenNhan = "Tốc độ")
	String Speed;
	@AnnotationField(hienthi = true, order = 21, tenNhan = "Hồ sơ đấu chuyển")
	HoSoThanhThai HoSoDauChuyenModel;

	/// ---------- không show----------------------------------------------
	@AnnotationField(hienthi = false, order = 23, tenNhan = "Mã Loại Dịch Vụ")
	String MServiceFnolong;

	@AnnotationField(hienthi = false, order = 24, tenNhan = "Ghi Chú")
	String Note;
	@AnnotationField(hienthi = false, order = 25, tenNhan = "Người sử dụng ")
	String NgDvSd;
	@AnnotationField(hienthi = false, order = 26, tenNhan = "Ngày cập nhật")
	String EditDate;

	@AnnotationField(hienthi = false, order = 27, tenNhan = "Dịch vụ đính kèm")
	String LISTSERVICE_ATT;

	@AnnotationField(hienthi = false, order = 28, tenNhan = "Tên cáp ngọn")
	String LINE_NAME_NGON;
	@AnnotationField(hienthi = false, order = 29, tenNhan = "Sợi số")
	String CABLE_PAIR_NGON;

}
