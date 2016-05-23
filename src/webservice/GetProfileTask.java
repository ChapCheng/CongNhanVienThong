package webservice;

public class GetProfileTask extends BaseTask{
	public GetProfileTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/GetThongtinNguoiDungByUsernameAAA";
		METHOD_NAME = "GetThongtinNguoiDungByUsernameAAA";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/WSLink/XacThuc.asmx?WSDL";
		User_WS = "WSGWDVKH";
		Pass_WS = "Authentication";
		headerTitle = "AuthHeader";
		para.add("strUserName");

				
	}

}
