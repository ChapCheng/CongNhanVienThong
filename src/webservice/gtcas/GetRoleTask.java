package webservice.gtcas;

import webservice.BaseTask;

public class GetRoleTask extends BaseTask {

	public GetRoleTask() {
		super();
		SOAP_ACTION = "http://tempuri.org/GetRole";
		METHOD_NAME = "GetRole";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/WSCSDLViTri/WSCSDLViTri/WSCDLViTri.asmx?WSDL";
		para.add("UserName");
	}

}
