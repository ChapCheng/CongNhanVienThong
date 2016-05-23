package webservice.pttb;

import webservice.BaseTask;

public class GetQuanHuyenTask extends BaseTask {
	public GetQuanHuyenTask() {
		// TODO Auto-generated constructor stub
		super();
		WSDL = "http://123.16.191.37/WSCSDLViTri/AndroidWS_PTTB.asmx?WSDL";
		METHOD_NAME = "LAY_DANHMUC_QUANHUYEN";
		SOAP_ACTION = "http://tempuri.org/LAY_DANHMUC_QUANHUYEN";
		NAMESPACE = "http://tempuri.org/";
		para.add("sMA_TINHTP");
	}

}
