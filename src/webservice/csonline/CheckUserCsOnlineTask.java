package webservice.csonline;

import webservice.BaseTask;

public class CheckUserCsOnlineTask extends BaseTask {
	public CheckUserCsOnlineTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/Tra_XTTT_TD";
		METHOD_NAME = "Tra_XTTT_TD";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/wsvnpt360/CSOnline_TraCuu360.asmx?WSDL";
		para.add("sUserTD");
	}

}
