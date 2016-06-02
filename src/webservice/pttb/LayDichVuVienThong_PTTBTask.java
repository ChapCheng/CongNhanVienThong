package webservice.pttb;

import webservice.BaseTask;

public class LayDichVuVienThong_PTTBTask extends BaseTask {
	public LayDichVuVienThong_PTTBTask() {
		// TODO Auto-generated constructor stub
		super();
		WSDL = "http://123.16.191.37/wsvnpt360/AndroidPTTB_Tracuu360.asmx?WSDL";
		METHOD_NAME = "DS_DICHVU_VIENTHONG";
		SOAP_ACTION = "http://tempuri.org/DS_DICHVU_VIENTHONG";
		NAMESPACE = "http://tempuri.org/";
	}

}
