package webservice.gtcas;

import webservice.BaseTask;

public class GetQuyenGTCAS extends BaseTask {
	public GetQuyenGTCAS() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/CheckUser";
		METHOD_NAME = "CheckUser";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/wslink/wsgtcas.asmx?WSDL";
		User_WS = "gtcasUser";
		Pass_WS = "gtcasPassword";
		headerTitle = "AuthHeader";
		pUserLabel = "Username";
		pPassLabel = "Password";
		para.add("ma_tinh_thanh");
		para.add("user_name");
	}

}
