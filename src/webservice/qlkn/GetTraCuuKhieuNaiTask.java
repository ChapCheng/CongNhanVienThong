package webservice.qlkn;

import webservice.BaseTask;

public class GetTraCuuKhieuNaiTask extends BaseTask{
	public GetTraCuuKhieuNaiTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/GetTraCuuKhieuNai";
		METHOD_NAME = "GetTraCuuKhieuNai";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/thinp/qlkn.asmx?WSDL";
		User_WS="thinp";
		Pass_WS="locon";
		headerTitle="AuthHeader";
		para.add("socv");
	}

}
