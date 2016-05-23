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
		para.add("id_trangthaicu");// <id_trangthaicu>int</id_trangthaicu>
		para.add("noidung_sua");// <noidung_sua>string</noidung_sua>
		para.add("id_phieu");// <id_phieu>int</id_phieu>
		para.add("id_loaisua");// <id_loaisua>int</id_loaisua>
		para.add("id_loaisuact");// <id_loaisuact>int</id_loaisuact>
		para.add("id_nguoisua");// <id_nguoisua>int</id_nguoisua>
		para.add("id_baohong");// <id_baohong>int</id_baohong>
		para.add("id_phieusua");// <id_phieusua>int</id_phieusua>
		para.add("id_vt");// <id_vt>int</id_vt>
		para.add("ma_dichvu");// <ma_dichvu>string</ma_dichvu>
		para.add("nd_suact");// <nd_suact>string</nd_suact>
		para.add("id_xuat");// <id_xuat>int</id_xuat>
		para.add("userName");// <userName>string</userName>
		para.add("ton_qua3h");// <ton_qua3h>string</ton_qua3h>
		para.add("id_nghiemthu");// <id_nghiemthu>int</id_nghiemthu>
		para.add("nd_nt");// <nd_nt>string</nd_nt>
		para.add("nd_goc");// <nd_goc>string</nd_goc>
		para.add("id_loaidichvu");// <id_loaidichvu>int</id_loaidichvu>
		para.add("hong_lai");// <hong_lai>int</hong_lai>
		para.add("id_ttpho");// id_ttpho
		// "wsdhsc", "wsdhsc@123", "AuthHeaderHTTT ");
		User_WS = "wscskh";
		Pass_WS = "wscskh@456";
		headerTitle = "AuthHeader";
//		if(Util.ttp.getId_ttpho().equals("1")){
//			WSDL="http://123.16.191.37/dhsc/Service/HTTT.asmx";
//			User_WS = "wsdhsc";
//			Pass_WS = "wsdhsc@123";
//			headerTitle = "AuthHeaderHTTT";
//		}
	}

	public void setDataInput(ThongTinBaoHong ttbh, LoaiSua loaisua,
			LoaiSuaChiTiet lsChiTiet, LoaiNghiemThu nghiemthu, String ndSua,
			String ndSuaChitiet, String ndNghiemThu, String ndGoc,
			NhanVien nhanvien) {
		input.add(ttbh.getID_TrangThai());
		input.add(ndSua);
		input.add(ttbh.getID_LanXPNT());
		input.add(loaisua.getIdLoaiSua());
		input.add(lsChiTiet.getIdLoaiSuaChiTiet());
		input.add(nhanvien.getId());
		input.add(ttbh.getID_BaoHong());
		input.add(ttbh.getID_PhieuSua());
		input.add(ttbh.getID_VT());
		input.add(ttbh.getMa_DichVu());
		input.add(ndSuaChitiet);
		input.add(ttbh.getID_Xuat());
		input.add(Util.userName);
		input.add(ttbh.getTon_Qua_3h());
		input.add(nghiemthu.getIdNoiDung());
		input.add(ndNghiemThu);
		input.add(ndGoc);
		input.add(ttbh.getID_LoaiDichVu());
		input.add(ttbh.getHong_Lai());
		input.add(Util.ttp.getId_ttpho());

	}
}
