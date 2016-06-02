package webservice.gtcas;

import webservice.BaseTask;

public class CapNhatToaDoGtcasTask extends BaseTask {
	public CapNhatToaDoGtcasTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/UpdateViTri";
		METHOD_NAME = "UpdateViTri";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/wslink/wsgtcas.asmx?WSDL";
		User_WS = "gtcasUser";
		Pass_WS = "gtcasPassword";
		headerTitle = "AuthHeader";
		pUserLabel = "Username";
		pPassLabel = "Password";
		// para.add("MaTinhThanh");
		// para.add("ObjectId");
		// para.add("Long");
		// para.add("Lat");
		// para.add("UserName");

	}

}
