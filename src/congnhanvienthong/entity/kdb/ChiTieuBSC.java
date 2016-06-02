package congnhanvienthong.entity.kdb;

import control.AnnotationField;

public class ChiTieuBSC {
	public String TenChiTieuCha;
	public int ChiTieuBscChaId;

	public int getChiTieuBscChaId() {
		return ChiTieuBscChaId;
	}

	public void setChiTieuBscChaId(int chiTieuBscChaId) {
		ChiTieuBscChaId = chiTieuBscChaId;
	}

	@AnnotationField(hienthi = true, tenNhan = "Tên chỉ tiêu", order = 0)
	public String TenChiTieuBsc;
	public int ChiTieuBscId;
	public int ThuTuChiTieuBsc;
	// @AnnotationField(hienthi = true, tenNhan = "Đơn vị tính", order = 1)
	String TenDonViTinh;
	// @AnnotationField(hienthi = true, tenNhan = "Tỷ trọng", order = 2)
	public String TyTrong;
	// @AnnotationField(hienthi = true, tenNhan = "Đơn vị", order = 1)
	String TenDonVi;
	@AnnotationField(hienthi = true, tenNhan = "Tỷ trọng", order = 2)
	String GiaTri;

	public String getGiaTri() {
		return GiaTri;
	}

	public void setGiaTri() {
		GiaTri = this.TyTrong + " (" + this.TenDonViTinh + ")";
	}

}
