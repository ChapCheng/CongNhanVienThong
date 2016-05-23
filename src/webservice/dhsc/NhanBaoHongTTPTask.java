package webservice.dhsc;

import webservice.BaseTask;

public class NhanBaoHongTTPTask extends BaseTask {
	public NhanBaoHongTTPTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/WsNhanBaoHong";
		METHOD_NAME = "WsNhanBaoHong";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/WebServiceDHSC/dhscttp.asmx?WSDL";
		pUserLabel = "Username";
		pPassLabel = "Password";
		User_WS = "wscskh";
		Pass_WS = "wscskh@456";
		headerTitle = "AuthHeaderDhscTtp";
		para.add("loaiDichVuId");
		para.add("maDichVu");
		para.add("tenAccount");
		para.add("khan");
		para.add("noiDungBaoHongId");
		para.add("noiDungBaoHong");
		para.add("gioHen");
		para.add("dienThoaiLienHe");
		para.add("diDongLienHe");
		para.add("nguoiLienHe");
		para.add("userName");
		para.add("tinhThanhPhoId");
	}

}
