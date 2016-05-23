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
		para.add("userName");
		para.add("passWord");
		para.add("accountSearch");

	}

	public void setData(String user) {
		input.add("ptpm2");
		input.add("ptpm2@123");
		input.add(user);
	}

}
