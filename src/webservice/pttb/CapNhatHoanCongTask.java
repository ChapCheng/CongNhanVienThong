package webservice.pttb;

import webservice.BaseTask;

public class CapNhatHoanCongTask extends BaseTask {
	public CapNhatHoanCongTask() {
		super();
		// TODO Auto-generated constructor stub
		WSDL = "http://123.16.191.37/wsvnpt360/AndroidPTTB_Tracuu360.asmx?WSDL";
		METHOD_NAME = "CAPNHAT_HOANCONG_ALL_NEW";
		SOAP_ACTION = "http://tempuri.org/CAPNHAT_HOANCONG_ALL_NEW";
		NAMESPACE = "http://tempuri.org/";

	}

}
