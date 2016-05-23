package congnhanvienthong.entity.qlkn;

import control.AnnotationField;

public class LichSuKhieuNai {

	//field hiện
	@AnnotationField(hienthi = true, order = 0, tenNhan = "Số C.văn")
	private String SoCV;
	@AnnotationField(hienthi = true, order = 1, tenNhan = "Ngày nhận")
	private String NgayNhan;
	@AnnotationField(hienthi = true, order = 2, tenNhan = "Người nhận")
	private String NguoiNhan;
	
	// filde ẩn
	@AnnotationField(hienthi = false, order = 0, tenNhan = "Nội dung")
	private String NoiDungKN;
	@AnnotationField(hienthi = false, order = 1, tenNhan = "Ngày G.Quyết")
	private String NgayNT;
	@AnnotationField(hienthi = false, order = 2, tenNhan = "Người G.Quyết")
	private String NguoiGQ;
	@AnnotationField(hienthi = false, order = 3, tenNhan = "N.dung Tr.Lời")
	private String KetQua;
	
	public LichSuKhieuNai() {
		// TODO Auto-generated constructor stub
	}

	public String getSoCV() {
		return SoCV;
	}

	public void setSoCV(String soCV) {
		SoCV = soCV;
	}

	public String getNgayNhan() {
		return NgayNhan;
	}

	public void setNgayNhan(String ngayNhan) {
		NgayNhan = ngayNhan;
	}

	public String getNguoiNhan() {
		return NguoiNhan;
	}

	public void setNguoiNhan(String nguoiNhan) {
		NguoiNhan = nguoiNhan;
	}

	public String getNoiDungKN() {
		return NoiDungKN;
	}

	public void setNoiDungKN(String noiDungKN) {
		NoiDungKN = noiDungKN;
	}

	public String getNgayGQ() {
		return NgayNT;
	}

	public void setNgayGQ(String ngayGQ) {
		NgayNT = ngayGQ;
	}

	public String getNguoiGQ() {
		return NguoiGQ;
	}

	public void setNguoiGQ(String nguoiGQ) {
		NguoiGQ = nguoiGQ;
	}

	public String getNoiDungTraLoi() {
		return KetQua;
	}

	public void setNoiDungTraLoi(String p_NoiDungTraLoi) {
		KetQua = p_NoiDungTraLoi;
	}

	public LichSuKhieuNai(String p_SoCV, String p_NgayNhan, String p_NguoiNhan,
			String p_NoiDungKN, String p_NgayGQ, String p_NguoiGQ,
			String p_NoiDungTraLoi) {
		super();
		SoCV = p_SoCV;
		NgayNhan = p_NgayNhan;
		NguoiNhan = p_NguoiNhan;
		NoiDungKN = p_NoiDungKN;
		NgayNT = p_NgayGQ;
		NguoiGQ = p_NguoiGQ;
		KetQua = p_NoiDungTraLoi;
	}

}
