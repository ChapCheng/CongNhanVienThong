package congnhanvienthong.entity.dhsc;

import java.io.Serializable;

import control.AnnotationField;

@SuppressWarnings("serial")
// @AnnotationClass(id = "ID_Xuat", name = "ID_NguoiNhanSua")
public class ThongTinBaoHong implements Serializable {
	/**
	 * 
	 */

	// các trường hiển thị

	// hiển thị = true : hiển thị sẵn, không phải ở phần ẩn hiện. Tên nhãn :
	// nhãn trước giá trị value cần hiện thị. Oreder : thứ tự hiển thị của
	// trường này
	private String ID_Ton;
	@AnnotationField(hienthi = true, tenNhan = "Mã V.Tinh", order = 0)
	private String Ma_VT;

	@AnnotationField(hienthi = true, tenNhan = "Tên tắt", order = 1)
	String Ten_Tat;

	@AnnotationField(hienthi = true, tenNhan = "Mã D.Vụ", order = 2)
	String Ma_DichVu;

	// các trường không hiển thị

	@AnnotationField(hienthi = false, tenNhan = "Ngày xuất", order = 2)
	String Ngay_Xuat;
	@AnnotationField(hienthi = false, tenNhan = "Tên T.bao", order = 2)
	String Ten_TB;
	@AnnotationField(hienthi = false, tenNhan = "Đ.Chỉ T.bao", order = 2)
	String DChi_TB;

	String ID_LanXPNT, ID_Xuat, Ten_VT, Ten_Account, DienThoai_LapDat, Nguoi_LHe, SoMay_LHe, Ngay_NhanSua, Nd_Ton,
			ID_VungSC, Dan_Tem, ID_LoaiDichVu, TGMLL, Hong_Lai, So_LanBao, So_LanNhacPhieu, Nguoi_NhanSua,
			ID_NguoiNhanSua, ID_NhomSua, Ton_Qua_3h, ID_LoaiDichVuCT, ID_BaoHong, ID_PhieuSua, ID_VT, ID_TrangThai;
	@AnnotationField(hienthi = true, tenNhan = "N.D xuất phiếu", order = 3)

	String ND_Xuat;
	private String ID_NhomTon;

	int pTotalRecord;
	private boolean isAllowNhan, isAllowTon, isAllowKhoa, isNghiemThu;

	public ThongTinBaoHong() {
		super();
	}

	public ThongTinBaoHong(String iD_TrangThai, String iD_LanXPNT, String ma_VT, String ten_VT, String ten_Tat,
			String ma_DichVu, String ten_Account, String dienThoai_LapDat, String ngay_Xuat, String nD_Xuat,
			String nguoi_LHe, String soMay_LHe, String ten_TB, String dChi_TB, String ngay_NhanSua, String nd_Ton,
			String iD_VungSC, String dan_Tem, String iD_LoaiDichVu, String tGMLL, String hong_Lai, String so_LanBao,
			String so_LanNhacPhieu, String Nguoi_NhanSua, String ID_NguoiNhanSua, String ID_NhomSua, String Ton_Qua_3h,
			boolean isAllowKhoa, String ID_Xuat, String ID_LoaiDichVuCT, String ID_BaoHong, String ID_PhieuSua,
			String ID_VT, boolean isAllowTon) {
		super();
		ID_TrangThai = iD_TrangThai;
		ID_LanXPNT = iD_LanXPNT;
		Ma_VT = ma_VT;
		Ten_VT = ten_VT;
		Ten_Tat = ten_Tat;
		Ma_DichVu = ma_DichVu;
		Ten_Account = ten_Account;
		DienThoai_LapDat = dienThoai_LapDat;
		Ngay_Xuat = ngay_Xuat;
		ND_Xuat = nD_Xuat;
		Nguoi_LHe = nguoi_LHe;
		SoMay_LHe = soMay_LHe;
		Ten_TB = ten_TB;
		DChi_TB = dChi_TB;
		Ngay_NhanSua = ngay_NhanSua;
		Nd_Ton = nd_Ton;
		ID_VungSC = iD_VungSC;
		Dan_Tem = dan_Tem;
		ID_LoaiDichVu = iD_LoaiDichVu;
		TGMLL = tGMLL;
		Hong_Lai = hong_Lai;
		So_LanBao = so_LanBao;
		So_LanNhacPhieu = so_LanNhacPhieu;
		this.Nguoi_NhanSua = Nguoi_NhanSua;
		this.ID_NguoiNhanSua = ID_NguoiNhanSua;
		this.ID_NhomSua = ID_NhomSua;
		this.Ton_Qua_3h = Ton_Qua_3h;
		this.isAllowKhoa = isAllowKhoa;
		this.ID_Xuat = ID_Xuat;
		this.ID_LoaiDichVuCT = ID_LoaiDichVuCT;
		this.ID_PhieuSua = ID_PhieuSua;
		this.ID_BaoHong = ID_BaoHong;
		this.ID_VT = ID_VT;
		this.isAllowTon = isAllowTon;
	}

	public String getID_TrangThai() {
		return ID_TrangThai;
	}

	public void setID_TrangThai(String iD_TrangThai) {
		ID_TrangThai = iD_TrangThai;

	}

	public String getID_LanXPNT() {
		return ID_LanXPNT;
	}

	public void setID_LanXPNT(String iD_LanXPNT) {
		ID_LanXPNT = iD_LanXPNT;
	}

	public String getMa_VT() {
		return Ma_VT;
	}

	public void setMa_VT(String ma_VT) {
		Ma_VT = ma_VT;
	}

	public String getTen_VT() {
		return Ten_VT;
	}

	public void setTen_VT(String ten_VT) {
		Ten_VT = ten_VT;
	}

	public String getTen_Tat() {
		return Ten_Tat;
	}

	public void setTen_Tat(String ten_Tat) {
		Ten_Tat = ten_Tat;
	}

	public String getMa_DichVu() {
		return Ma_DichVu;
	}

	public void setMa_DichVu(String ma_DichVu) {
		Ma_DichVu = ma_DichVu;
	}

	public String getTen_Account() {
		return Ten_Account;
	}

	public void setTen_Account(String ten_Account) {
		Ten_Account = ten_Account;
	}

	public String getDienThoai_LapDat() {
		return DienThoai_LapDat;
	}

	public void setDienThoai_LapDat(String dienThoai_LapDat) {
		DienThoai_LapDat = dienThoai_LapDat;
	}

	public String getNgay_Xuat() {
		return Ngay_Xuat;
	}

	public void setNgay_Xuat(String ngay_Xuat) {
		Ngay_Xuat = ngay_Xuat;
	}

	public String getND_Xuat() {
		return ND_Xuat;
	}

	public void setND_Xuat(String nD_Xuat) {
		ND_Xuat = nD_Xuat;
	}

	public String getNguoi_LHe() {
		return Nguoi_LHe;
	}

	public void setNguoi_LHe(String nguoi_LHe) {
		Nguoi_LHe = nguoi_LHe;
	}

	public String getSoMay_LHe() {
		return SoMay_LHe;
	}

	public void setSoMay_LHe(String soMay_LHe) {
		SoMay_LHe = soMay_LHe;
	}

	public String getTen_TB() {
		return Ten_TB;
	}

	public void setTen_TB(String ten_TB) {
		Ten_TB = ten_TB;
	}

	public String getDChi_TB() {
		return DChi_TB;
	}

	public void setDChi_TB(String dChi_TB) {
		DChi_TB = dChi_TB;
	}

	public String getNgay_NhanSua() {
		return Ngay_NhanSua;
	}

	public void setNgay_NhanSua(String ngay_NhanSua) {
		Ngay_NhanSua = ngay_NhanSua;
	}

	public String getNd_Ton() {
		return Nd_Ton;
	}

	public void setNd_Ton(String nd_Ton) {
		Nd_Ton = nd_Ton;
	}

	public String getID_VungSC() {
		return ID_VungSC;
	}

	public void setID_VungSC(String iD_VungSC) {
		ID_VungSC = iD_VungSC;
	}

	public String getDan_Tem() {
		return Dan_Tem;
	}

	public void setDan_Tem(String dan_Tem) {
		Dan_Tem = dan_Tem;
	}

	public String getID_LoaiDichVu() {
		return ID_LoaiDichVu;
	}

	public void setID_LoaiDichVu(String iD_LoaiDichVu) {
		ID_LoaiDichVu = iD_LoaiDichVu;
	}

	public String getTGMLL() {
		return TGMLL;
	}

	public void setTGMLL(String tGMLL) {
		TGMLL = tGMLL;
	}

	public String getHong_Lai() {
		return Hong_Lai;
	}

	public void setHong_Lai(String hong_Lai) {
		Hong_Lai = hong_Lai;
	}

	public String getSo_LanBao() {
		return So_LanBao;
	}

	public void setSo_LanBao(String so_LanBao) {
		So_LanBao = so_LanBao;
	}

	public String getSo_LanNhacPhieu() {
		return So_LanNhacPhieu;
	}

	public void setSo_LanNhacPhieu(String so_LanNhacPhieu) {
		So_LanNhacPhieu = so_LanNhacPhieu;
	}

	public String getTon_Qua_3h() {
		return Ton_Qua_3h;
	}

	public void setTon_Qua_3h(String ton_Qua_3h) {
		Ton_Qua_3h = ton_Qua_3h;
	}

	public String getID_NguoiNhanSua() {
		return ID_NguoiNhanSua;
	}

	public void setID_NguoiNhanSua(String iD_NguoiNhanSua) {
		ID_NguoiNhanSua = iD_NguoiNhanSua;
	}

	public String getNguoi_NhanSua() {
		return Nguoi_NhanSua;
	}

	public void setNguoi_NhanSua(String nguoi_NhanSua) {
		Nguoi_NhanSua = nguoi_NhanSua;
	}

	public String getID_NhomSua() {
		return ID_NhomSua;
	}

	public void setID_NhomSua(String iD_NhomSua) {
		ID_NhomSua = iD_NhomSua;
	}

	public boolean getIsAllowKhoa() {
		return isAllowKhoa;
	}

	public void setIsAllowKhoa(boolean isAllowKhoa) {
		this.isAllowKhoa = isAllowKhoa;
	}

	public String getID_Xuat() {
		return ID_Xuat;
	}

	public void setID_Xuat(String iD_Xuat) {
		ID_Xuat = iD_Xuat;
	}

	public String getID_LoaiDichVuCT() {
		return ID_LoaiDichVuCT;
	}

	public void setID_LoaiDichVuCT(String iD_LoaiDichVuCT) {
		ID_LoaiDichVuCT = iD_LoaiDichVuCT;
	}

	public String getID_BaoHong() {
		return ID_BaoHong;
	}

	public void setID_BaoHong(String iD_BaoHong) {
		ID_BaoHong = iD_BaoHong;
	}

	public String getID_PhieuSua() {
		return ID_PhieuSua;
	}

	public void setID_PhieuSua(String iD_PhieuSua) {
		ID_PhieuSua = iD_PhieuSua;
	}

	public String getID_VT() {
		return ID_VT;
	}

	public void setID_VT(String iD_VT) {
		ID_VT = iD_VT;
	}

	public boolean getIsAllowTon() {
		return isAllowTon;
	}

	public void setIsAllowTon(boolean isAllowTon) {
		this.isAllowTon = isAllowTon;
	}

	public int getpTotalRecord() {
		return pTotalRecord;
	}

	public void setpTotalRecord(int pTotalRecord) {
		this.pTotalRecord = pTotalRecord;
	}

	public boolean isAllowNhan() {
		return isAllowNhan;
	}

	public void setAllowNhan(boolean isAllowNhan) {
		this.isAllowNhan = isAllowNhan;
	}

	public boolean isNghiemThu() {
		return isNghiemThu;
	}

	public void setNghiemThu(boolean isNghiemThu) {
		this.isNghiemThu = isNghiemThu;
	}

	public String getID_NhomTon() {
		return ID_NhomTon;
	}

	public void setID_NhomTon(String iD_NhomTon) {
		ID_NhomTon = iD_NhomTon;
	}

	public String getID_Ton() {
		return ID_Ton;
	}

	public void setID_Ton(String iD_Ton) {
		ID_Ton = iD_Ton;
	}

}
