package webservice.pttb;

import webservice.BaseTask;

public class GetToaNhaTask extends BaseTask {
	public GetToaNhaTask() {

		// TODO Auto-generated constructor stub
		super();
		WSDL = "http://123.16.191.37/wsvnpt360/AndroidPTTB_Tracuu360.asmx?WSDL";
		METHOD_NAME = "LAY_DANHSACH_TOANHA";
		SOAP_ACTION = "http://tempuri.org/LAY_DANHSACH_TOANHA";
		NAMESPACE = "http://tempuri.org/";
		// para.add("sSoLuongHienThi");
		// para.add("sMA_TINHTP");
		// para.add("sTENTOANHA");
		// para.add("sDIACHITOANHA");
		// para.add("sDOITAC_ID");
		// para.add("sQUANHUYEN_ID");
		// para.add("sDUAN_ID");
	}

}
