package webservice.dhsc;

import control.Util;
import webservice.BaseTask;

public class BaoTonTTP extends BaseTask {
	public BaoTonTTP() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/WsTonPhieuHoTroTrucTiep";
		METHOD_NAME = "WsTonPhieuHoTroTrucTiep";
		NAMESPACE = "http://tempuri.org/";
		User_WS = "wscskh";
		Pass_WS = "wscskh@456";
		headerTitle = "AuthHeaderDhscTtp";
		WSDL = "http://123.16.191.37/WebServiceDHSC/dhscttp.asmx?WSDL";
		if (Util.ttp.getId_ttpho().equals("1")) {
			WSDL = "http://123.16.191.37/WebServiceDHSC/dhsc.asmx?WSDL";
			headerTitle = "AuthHeaderDhsc";
		}

	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		return result;
	}
}
