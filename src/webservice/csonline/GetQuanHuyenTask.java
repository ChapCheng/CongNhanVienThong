package webservice.csonline;

import webservice.BaseTask;

public class GetQuanHuyenTask extends BaseTask {
	public GetQuanHuyenTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/Get_TT_QuanHuyen";
		METHOD_NAME = "Get_TT_QuanHuyen";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/wsvnpt360/CSOnline_TraCuu360.asmx?WSDL";
	}

}
