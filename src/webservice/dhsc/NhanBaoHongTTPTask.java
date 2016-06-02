package webservice.dhsc;

import control.Util;
import webservice.BaseTask;

public class NhanBaoHongTTPTask extends BaseTask {
	public NhanBaoHongTTPTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/WsNhanBaoHong";
		METHOD_NAME = "WsNhanBaoHong";
		NAMESPACE = "http://tempuri.org/";
		pUserLabel = "Username";
		pPassLabel = "Password";
		User_WS = "wscskh";
		Pass_WS = "wscskh@456";
		if (Util.ttp.getId_ttpho().equals("1")) {

			headerTitle = "AuthHeaderDhsc";
			WSDL = "http://123.16.191.37/WebServiceDHSC/dhsc.asmx?WSDL";

		} else {
			headerTitle = "AuthHeaderDhscTtp";
			WSDL = "http://123.16.191.37/WebServiceDHSC/dhscttp.asmx?WSDL";
		}
	}

}
