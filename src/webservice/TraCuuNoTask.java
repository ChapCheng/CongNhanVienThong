package webservice;

public class TraCuuNoTask extends BaseTask{
	public TraCuuNoTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/TraThangNoDuong";
		METHOD_NAME = "TraThangNoDuong";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/ThanhToanTrucTuyenTest/ThanhToanTrucTuyen.asmx?WSDL";
		User_WS = "WSGWDVKH";
		Pass_WS = "Authentication";
		headerTitle = "AuthHeader";
		para.add("prUsername");
		para.add("prPassword");
		para.add("prUsingOTP");
	}

}
