package congnhanvienthong.entity.dhsc;

import control.AnnotationField;

public class ThongTinDichVu {
	@AnnotationField(hienthi = true, tenNhan = "Mã Dịch Vụ", order = 0)
	String Ma_DichVu;
	String Ma_TC;
	@AnnotationField(hienthi = true, tenNhan = "Account", order = 2)
	String Account;
	@AnnotationField(hienthi = true, tenNhan = "Tên Thuê Bao", order = 3)
	String Ten_TB;
	@AnnotationField(hienthi = true, tenNhan = "Địa chỉ", order = 4)
	String DiaChi;
	@AnnotationField(hienthi = true, tenNhan = "Số liên hệ", order = 5)
	String SoLienHe;
	String Id_LoaiDichVu;
	@AnnotationField(hienthi = true, tenNhan = "Loại dịch vụ", order = 6)
	String LoaiDichVu;
	@AnnotationField(hienthi = true, tenNhan = "Phường xã", order = 7)
	String TenPhuongXa;
	@AnnotationField(hienthi = true, tenNhan = "Quận huyện", order = 8)
	String TenQuanHuyen;
	@AnnotationField(hienthi = true, tenNhan = "Trạng thái thuê bao", order = 9)
	String TrangThaiTB;
	int LoaiKhachHangId;
	String MaLoaiKhachHang;
	@AnnotationField(hienthi = true, tenNhan = "Loại khách hàng", order = 10)
	String TenLoaiKhachHang;
	int UuTienId;
	int VungSuaChuaId;
	@AnnotationField(hienthi = true, tenNhan = "Trung tâm", order = 11)
	String TrungTam;
	String Doi;
	@AnnotationField(hienthi = true, tenNhan = "Vệ Tinh", order = 12)
	String VeTinh;
	String DVI_SDUNG;
	String DVI_DC1;
	String DAU_DC1;
	String CUOI_DC1;
	String CONG_A;
	String CONG_B;
	String DIEM_TRUNGGIAN;
	String TOC_DO;
}
