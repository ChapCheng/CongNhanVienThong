package webservice.dhsc;

import webservice.BaseTask;


public class LayToaDoThietBiTask extends BaseTask{
	public LayToaDoThietBiTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/GetListDevice";
		METHOD_NAME = "GetListDevice";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/wsgtcasmobile/devicelocationservice.asmx?WSDL";
		para.add("deviceType");
		para.add("latitude");
		para.add("longitude");
		para.add("maxRange");
		para.add("count");
		User_WS="thinp";
		Pass_WS="locon";
		headerTitle="AuthHeader";
		
	}
	

}
