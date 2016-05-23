package webservice;

public class GetImageTask extends BaseTask{
	public GetImageTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/GetAvatarImage";
		METHOD_NAME = "GetAvatarImage";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/wslinkduphong/xacthuc.asmx?WSDL";
		User_WS = "WSGWDVKH";
		Pass_WS = "Authentication";
		headerTitle = "AuthHeader";
		para.add("strUserName");
	}

}
