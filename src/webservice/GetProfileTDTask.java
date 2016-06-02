package webservice;

public class GetProfileTDTask extends BaseTask {
	public GetProfileTDTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/TraThongTinVNPTPortal";
		METHOD_NAME = "TraThongTinVNPTPortal";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/LDAPWebService/WSLDAP.asmx?WSDL";
		User_WS = "WSGWDVKH";
		Pass_WS = "Authentication";
		headerTitle = "AuthHeader";

	}

	public void setData(String user) {
		addParam("userName", "ptpm2");
		addParam("passWord", "ptpm2@123");
		addParam("accountSearch", user);
	}

}
