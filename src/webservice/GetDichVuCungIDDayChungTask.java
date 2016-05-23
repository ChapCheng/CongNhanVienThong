package webservice;

public class GetDichVuCungIDDayChungTask extends BaseTask {
	public GetDichVuCungIDDayChungTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/GetAllServiceAttached";
		METHOD_NAME = "GetAllServiceAttached";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/ThiNPWebserviceGetserviceAttach/WebserviceLayDichVuDinhKemGTCAS.asmx?WSDL";
		User_WS = "wsdhsc";
		Pass_WS = "wsdhsc@123";
		headerTitle = "AuthHeaderHTTT";
		para.add("maDichVu");
	}
}
