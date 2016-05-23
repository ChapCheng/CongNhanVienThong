package webservice.csonline;

import webservice.BaseTask;

public class TiepnhanDVVTTask extends BaseTask {
	public TiepnhanDVVTTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/TiepnhanDVVT";
		METHOD_NAME = "TiepnhanDVVT";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/wsvnpt360/CSOnline_TraCuu360.asmx?WSDL";
		para.add("idloaidv");
		para.add("tenkh");
		para.add("diachilapdat");
		para.add("tennguoilh");
		para.add("diachilh");
		para.add("idphuong");
		para.add("sodtlh");
		para.add("email");
		para.add("thoigianlh");
		para.add("nguoigt");
		para.add("fonegt");
		para.add("ghichu");
		para.add("userName");
	}

}
