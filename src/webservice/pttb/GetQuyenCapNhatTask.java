package webservice.pttb;

import webservice.BaseTask;

public class GetQuyenCapNhatTask extends BaseTask {
	public GetQuyenCapNhatTask() {
		// TODO Auto-generated constructor stub
		super();
		WSDL = "http://123.16.191.37/WSCSDLViTri/AndroidWS_PTTB.asmx?WSDL";
		METHOD_NAME = "LAY_DS_QUYEN_CAIDAT";
		SOAP_ACTION = "http://tempuri.org/LAY_DS_QUYEN_CAIDAT";
		NAMESPACE = "http://tempuri.org/";
		para.add("sMA_TINHTP");
		para.add("sUSER_XTTT");
	}

}
