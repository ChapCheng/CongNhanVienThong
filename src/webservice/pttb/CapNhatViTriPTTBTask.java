package webservice.pttb;

import webservice.BaseTask;
import congnhanvienthong.entity.pttb.ChiTietThueBao;
import congnhanvienthong.entity.pttb.ThongTinThueBao;

public class CapNhatViTriPTTBTask extends BaseTask {
	public CapNhatViTriPTTBTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/ThemMoiThueBaoViTriPTTB_new";
		METHOD_NAME = "ThemMoiThueBaoViTriPTTB_new";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/WSCSDLViTri/WSCSDLViTri/WSCDLViTri.asmx?WSDL";
		// para.add("eidLoaiDoiTuong");
		// para.add("user");
		// para.add("oThongTinThueBao");
		// para.add("oChiTietThueBao");

		// -------------------------------------//
		paraObj.add("objThongTinThueBao");
		paraObjType.add(ThongTinThueBao.class);
		// -----------------------------------//
		paraObj.add("objChiTietThueBao");
		paraObjType.add(ChiTietThueBao.class);

	}

}
