package webservice;

public class LoginTask extends BaseTask {
	public LoginTask() {
		super();
		SOAP_ACTION = "http://tempuri.org/AuthenByUserPassAAA";
		METHOD_NAME = "AuthenByUserPassAAA";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/WSLink/XacThuc.asmx?WSDL";
		User_WS = "WSGWDVKH";
		Pass_WS = "Authentication";
		headerTitle = "AuthHeader";

	}

}
