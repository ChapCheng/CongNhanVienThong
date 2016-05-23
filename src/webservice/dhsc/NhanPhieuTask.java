package webservice.dhsc;

import webservice.BaseTask;

public class NhanPhieuTask extends BaseTask {
	public NhanPhieuTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/WsNhanPhieuHoTroTrucTiep";
		METHOD_NAME = "WsNhanPhieuHoTroTrucTiep";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/WebServiceDHSC/dhscttp.asmx?WSDL";

		User_WS = "wscskh";
		Pass_WS = "wscskh@456";
		headerTitle = "AuthHeader";
		para.add("phieuId");
		para.add("userName");
		para.add("nguoiNhanSuaId");
		para.add("trangThaiId");
		para.add("loaiDichVuId");
		para.add("loaiDichVuChiTietId");
		para.add("xuatId");
		para.add("tinhThanhPhoId");
	}

}