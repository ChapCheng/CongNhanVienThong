package webservice.pttb;

import webservice.BaseTask;

public class LayDanhSachMaThiCongTask extends BaseTask {
	public LayDanhSachMaThiCongTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/LAY_MATHICONG_THUOCTOVT_NEW";
		METHOD_NAME = "LAY_MATHICONG_THUOCTOVT_NEW";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/WSCSDLViTri/AndroidWS_PTTB.asmx?WSDL";
		headerTitle = "AuthHeader";
		para.add("sMA_TINHTP");
		para.add("sDICHVUVT_ID");
		para.add("sMA_QUYEN_CD");
	}

}
