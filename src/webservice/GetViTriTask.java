package webservice;

public class GetViTriTask extends BaseTask {
	public GetViTriTask() {
		super();
		SOAP_ACTION = "http://tempuri.org/LayThongTinVSDLVT_Mobile";
		METHOD_NAME = "LayThongTinVSDLVT_Mobile";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/WSCSDLViTri/WSCSDLViTri/WSCDLViTri.asmx?WSDL";
		User_WS = "wsdhsc";
		Pass_WS = "wsdhsc@123";
		headerTitle = "AuthHeaderHTTT";
		para.add("ma_dichvu");
		para.add("id_hethong");
		//para.add("0947468855");

	}

}
