package webservice.gtcas;

import webservice.BaseTask;

public class GetRankTask extends BaseTask {

	public GetRankTask() {
		super();
		SOAP_ACTION = "http://tempuri.org/GetVitriFromMaxrange";
		METHOD_NAME = "GetVitriFromMaxrange";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/WSCSDLViTri/WSCSDLViTri/WSCDLViTri.asmx?WSDL";
		para.add("p_Longitude");
		para.add("p_Latitude");
		para.add("p_MaxRange");
		para.add("p_Count");
		para.add("p_Type");
		para.add("p_ObjName");
		para.add("id_Tinhthanh");
	}

}
