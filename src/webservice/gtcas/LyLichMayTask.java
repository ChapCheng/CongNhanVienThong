package webservice.gtcas;

import webservice.BaseTask;

public class LyLichMayTask extends BaseTask {
	public LyLichMayTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/GetInfoOfService_Mobile";
		METHOD_NAME = "GetInfoOfService_Mobile";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/wslink/wsgtcas.asmx?WSDL";
		User_WS = "gtcasUser";
		Pass_WS = "gtcasPassword";
		headerTitle = "AuthHeader";
		pUserLabel = "Username";
		pPassLabel = "Password";
		// para.add("ma_dich_vu");
		// para.add("ma_tinh_thanh");
	}

}
