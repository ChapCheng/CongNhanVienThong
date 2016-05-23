package webservice.pttb;

import webservice.BaseTask;

public class CapNhatHoanCongTask extends BaseTask {
	public CapNhatHoanCongTask() {
		super();
		// TODO Auto-generated constructor stub
		WSDL = "http://123.16.191.37/WSCSDLViTri/AndroidWS_PTTB.asmx?WSDL";
		METHOD_NAME = "CAPNHAT_HOANCONG_ALL_NEW";
		SOAP_ACTION = "http://tempuri.org/CAPNHAT_HOANCONG_ALL_NEW";
		NAMESPACE = "http://tempuri.org/";
		para.add("sHDTB_ID");
		para.add("sNGAY_HOAN_CONG");
		para.add("sNGUOI_HOAN_CONG");
		para.add("sLOAI");
		para.add("sLONG");
		para.add("sLAT");
		para.add("sMA_TINHTP");

	}

}
