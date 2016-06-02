package webservice.qlkn;

import webservice.BaseTask;

public class GetTienTrinhPhieuXacMinhKhieuNaiWS extends BaseTask {
	public GetTienTrinhPhieuXacMinhKhieuNaiWS() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/GetTienTrinhPhieu";
		METHOD_NAME = "GetTienTrinhPhieu";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/thinp/qlkn.asmx?WSDL";
		User_WS = "thinp";
		Pass_WS = "locon";
		headerTitle = "AuthHeader";
		// para.add("p_sophieu");
	}

}
