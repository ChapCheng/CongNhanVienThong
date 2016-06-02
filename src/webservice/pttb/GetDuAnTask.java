package webservice.pttb;

import webservice.BaseTask;

public class GetDuAnTask extends BaseTask {
	public GetDuAnTask() {
		// TODO Auto-generated constructor stub
		super();
		WSDL = "http://123.16.191.37/wsvnpt360/AndroidPTTB_Tracuu360.asmx?WSDL";
		METHOD_NAME = "LAY_DANHMUC_DUAN";
		SOAP_ACTION = "http://tempuri.org/LAY_DANHMUC_DUAN";
		NAMESPACE = "http://tempuri.org/";
		// para.add("sMA_TINHTP");
		// para.add("sQUANHUYEN_ID");

	}

}
