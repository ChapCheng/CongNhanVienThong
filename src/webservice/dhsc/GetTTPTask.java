package webservice.dhsc;

import webservice.BaseTask;

public class GetTTPTask extends BaseTask {
	public GetTTPTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/WsCheckQuyenTinhThanhPho";
		METHOD_NAME = "WsCheckQuyenTinhThanhPho";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/WebServiceDHSC/dhscttp.asmx?WSDL";
		User_WS = "wscskh";
		Pass_WS = "wscskh@456";
		headerTitle = "AuthHeaderDhscTtp";
	}

}
