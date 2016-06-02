package webservice.dhsc;

import webservice.BaseTask;

public class LayListDichVuTask extends BaseTask {

	public LayListDichVuTask() {
		super();
		SOAP_ACTION = "http://tempuri.org/GetLoaiDichVu";
		METHOD_NAME = "GetLoaiDichVu";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/dhscnew/service/WSDanhMuc.asmx?WSDL";
		User_WS = "wscskh";
		Pass_WS = "wscskh@456";
		headerTitle = "AuthHeaderHTTT";
		// if (Util.ttp.getId_ttpho().equals("1")) {
		// WSDL = "http://123.16.191.37/dhsc/Service/WSDanhMuc.asmx";
		// User_WS = "wscskh";
		// Pass_WS="wscskh@456";
		// headerTitle="AuthHeaderHTTT";
		// }

	}

}
