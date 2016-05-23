package webservice.dhsc;

import webservice.BaseTask;

public class ThemMoiViTriDHSCTask extends BaseTask {
	public ThemMoiViTriDHSCTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/ThemMoiThueBaoDHSC";
		METHOD_NAME = "ThemMoiThueBaoDHSC";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/WSCSDLViTri/WSCSDLViTri/WSCDLViTri.asmx?WSDL";
		para.add("user");
		para.add("ma_dichvu");
		para.add("id_loaidichvuDHSC");
		para.add("diachi");
		para.add("tenthuebao");
		para.add("mavt");
		para.add("longtitude");
		para.add("latitude");
		para.add("anh");
		

	}

}
