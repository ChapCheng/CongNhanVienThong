package webservice.dhsc;

import webservice.BaseTask;
import congnhanvienthong.entity.dhsc.LoaiNghiemThu;
import congnhanvienthong.entity.dhsc.LoaiSua;
import congnhanvienthong.entity.dhsc.LoaiSuaChiTiet;
import congnhanvienthong.entity.dhsc.NhanVien;
import congnhanvienthong.entity.dhsc.ThongTinBaoHong;
import control.Util;

public class KhoaPhieuTTP extends BaseTask {
	public KhoaPhieuTTP() {
		// TODO Auto-generated constructor stub
		super();
		WSDL = "http://123.16.191.37/dhscnew/service/WSMobile.asmx?WSDL";

		METHOD_NAME = "KhoaPhieuHTTT";
		SOAP_ACTION = "http://tempuri.org/KhoaPhieuHTTT";
		NAMESPACE = "http://tempuri.org/";
		User_WS = "wscskh";
		Pass_WS = "wscskh@456";
		headerTitle = "AuthHeader";
	}

	public void setDataInput(ThongTinBaoHong ttbh, LoaiSua loaisua, LoaiSuaChiTiet lsChiTiet, LoaiNghiemThu nghiemthu,
			String ndSua, String ndSuaChitiet, String ndNghiemThu, String ndGoc, NhanVien nhanvien) {
		addParam("id_trangthaicu", ttbh.getID_TrangThai());
		addParam("noidung_sua", ndSua);
		addParam("id_phieu", ttbh.getID_LanXPNT());
		addParam("id_loaisua", loaisua.getIdLoaiSua());
		addParam("id_loaisuact", lsChiTiet.getIdLoaiSuaChiTiet());
		addParam("id_nguoisua", nhanvien.getId());
		addParam("id_baohong", ttbh.getID_BaoHong());
		addParam("id_phieusua", ttbh.getID_PhieuSua());
		addParam("id_vt", ttbh.getID_VT());
		addParam("ma_dichvu", ttbh.getMa_DichVu());
		addParam("nd_suact", ndSuaChitiet);
		addParam("id_xuat", ttbh.getID_Xuat());
		addParam("userName", Util.userName);
		addParam("ton_qua3h", ttbh.getTon_Qua_3h());
		addParam("id_nghiemthu", nghiemthu.getIdNoiDung());
		addParam("nd_nt", ndNghiemThu);
		addParam("nd_goc", ndGoc);
		addParam("id_loaidichvu", ttbh.getID_LoaiDichVu());
		addParam("hong_lai", ttbh.getHong_Lai());
		addParam("id_ttpho", Util.ttp.getId_ttpho());

	}

	public void setDataInputHNI(ThongTinBaoHong ttbh, LoaiSua loaisua, LoaiSuaChiTiet lsChiTiet,
			LoaiNghiemThu nghiemthu, String ndSua, String ndSuaChitiet, String ndNghiemThu, String ndGoc,
			NhanVien nhanvien) {
		addParam("phieuId", ttbh.getID_LanXPNT());
		addParam("phieuSuaId", ttbh.getID_PhieuSua());
		addParam("loaiSuaId", loaisua.getIdLoaiSua());
		addParam("loaiSuaChiTietId", lsChiTiet.getIdLoaiSuaChiTiet());
		addParam("noiDungSua", ndSua);
		addParam("noiDungSuaChiTiet", ndSuaChitiet);
		addParam("hongLai", ttbh.getHong_Lai());
		addParam("baoHongId", ttbh.getID_BaoHong());
		// addParam("tonId", ttbh.get);
		addParam("lyDoTon", "");
		// addParam("lyDoTonChiTiet", value);
		addParam("tonQua3H", ttbh.getTon_Qua_3h());
		addParam("userName", Util.userName);
		addParam("nguoiNhanSuaId", nhanvien.getId());
		addParam("trangThaiId", ttbh.getID_TrangThai());
		addParam("maDichVu", ttbh.getMa_DichVu());
		addParam("loaiDichVuId", ttbh.getID_LoaiDichVu());
		addParam("loaiDichVuChiTietId", ttbh.getID_LoaiDichVuCT());
		addParam("veTinhId", ttbh.getID_VT());
		addParam("nghiemThuId", nghiemthu.getIdNoiDung());
		addParam("noiDungNghiemThu", ndNghiemThu);
		addParam("noiDungNghiemThuGoc", ndGoc);
		addParam("isSendSms", false);
		addParam("noiDungTinNhanKhoaPhieu", "");
		addParam("soDienThoaiNguoiNhanSua", "");

	}
}
