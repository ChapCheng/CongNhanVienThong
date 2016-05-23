package webservice.dhsc;

import webservice.BaseTask;

public class BaoTonTTP extends BaseTask {
	public BaoTonTTP() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/WsTonHoTroTrucTiep";
		METHOD_NAME = "WsTonHoTroTrucTiep";
		NAMESPACE = "http://tempuri.org/";
		User_WS = "wscskh";
		Pass_WS = "wscskh@456";
		headerTitle = "AuthHeaderDhscTtp";
		WSDL = "http://123.16.191.37/WebServiceDHSC/dhscttp.asmx?WSDL";
		para.add("trangThaiCuId");
		para.add("baoHongId");
		para.add("phieuSuaId");
		para.add("phieuId");
		para.add("veTinhId");
		para.add("nguoiNhanId");
		para.add("userName");
		para.add("xuatId");
		para.add("noiDungTon");
		para.add("noiDungTonChiTiet");
		para.add("tonId");
		para.add("maDichvu");
		para.add("loaiDichVuId");
		para.add("tinhThanhPhoId");

	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		return result;
	}
}
