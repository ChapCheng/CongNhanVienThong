package webservice.qlkn;
import webservice.BaseTask;
public class GetPhieuDuyetXacMinhKhieuNaiTask extends BaseTask{
	public GetPhieuDuyetXacMinhKhieuNaiTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/GetPhieuDuyetXMByUserName";
		METHOD_NAME = "GetPhieuDuyetXMByUserName";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/thinp/qlkn.asmx?WSDL";
		User_WS="thinp";
		Pass_WS="locon";
		headerTitle="AuthHeader";
		para.add("userName");
	}

}
