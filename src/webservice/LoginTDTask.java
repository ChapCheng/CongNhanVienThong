package webservice;

public class LoginTDTask extends BaseTask {
	public LoginTDTask() {
		super();
		SOAP_ACTION = "http://tempuri.org/AuthenByUserPassUsingOTP";
		METHOD_NAME = "AuthenByUserPassUsingOTP";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/LDAPWebService/WSLDAP.asmx?WSDL";
		User_WS = "WSGWDVKH1";
		Pass_WS = "Authentication1";
		headerTitle = "AuthHeader";
		para.add("prUsername");
		para.add("prPassword");
		para.add("prUsingOTP");
	}

}
