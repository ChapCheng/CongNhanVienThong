package webservice.pttb;

import webservice.BaseTask;

public class GetDoiTacTask extends BaseTask {
	public GetDoiTacTask() {
		// TODO Auto-generated constructor stub
		super();
		WSDL = "http://123.16.191.37/WSCSDLViTri/AndroidWS_PTTB.asmx?WSDL";
		METHOD_NAME = "LAY_DANHMUC_DOITAC";
		SOAP_ACTION = "http://tempuri.org/LAY_DANHMUC_DOITAC";
		NAMESPACE = "http://tempuri.org/";
		para.add("sMA_TINHTP");
	}

}
