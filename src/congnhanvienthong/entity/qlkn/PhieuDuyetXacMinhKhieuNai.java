package congnhanvienthong.entity.qlkn;

import control.AnnotationField;

public class PhieuDuyetXacMinhKhieuNai {
	@AnnotationField(hienthi = true, order = 0, tenNhan = "Số C.văn")
	private String SoCV;
	
	private String SoPhieu;
	@AnnotationField(hienthi = false, order = 0, tenNhan = "Nội dung")
	private String NoiDungYeuCau;
	@AnnotationField(hienthi = false, order = 0, tenNhan = "Ngày xuất")
	private String NgayXuat;
	@AnnotationField(hienthi = false, order = 0, tenNhan = "Đơn vị")
	private String DonViXuat;	
	

	public String getSoCV() {
		return SoCV;
	}

	public void setSoCV(String soCV) {
		SoCV = soCV;
	}

	public String getSoPhieu() {
		return SoPhieu;
	}

	public void setSoPhieu(String soPhieu) {
		SoPhieu = soPhieu;
	}
	public String getNoiDungYeuCau() {
		return NoiDungYeuCau;
	}

	public void setNoiDungYeuCau(String noiDungYeuCau) {
		NoiDungYeuCau = noiDungYeuCau;
	}
	public String getNgayXuat() {
		return NgayXuat;
	}

	public void setNgayXuat(String ngayXuat) {
		NgayXuat = ngayXuat;
	}
	public String getDonViXuat() {
		return DonViXuat;
	}

	public void setDonViXuat(String donViXuat) {
		DonViXuat = donViXuat;
	}	
	public PhieuDuyetXacMinhKhieuNai(String p_SoCV,String p_SoPhieu,
			String p_NoiDungYeuCau,String p_NgayXuat,String p_DonViXuat) {
		super();
		SoCV = p_SoCV;
		SoPhieu = p_SoPhieu;
		NoiDungYeuCau = p_NoiDungYeuCau;
		NgayXuat = p_NgayXuat;
		DonViXuat = p_DonViXuat;
	}
	public PhieuDuyetXacMinhKhieuNai() {
		// TODO Auto-generated constructor stub
	}
	

}
