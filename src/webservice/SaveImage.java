package webservice;

public class SaveImage extends BaseTask{
	public SaveImage() {
		// TODO Auto-generated constructor stub
		SOAP_ACTION = "http://tempuri.org/WriteImageData";
		METHOD_NAME = "WriteImageData";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/WSLink/XacThuc.asmx?WSDL";
		User_WS = "WSGWDVKH";
		Pass_WS = "Authentication";
		headerTitle = "AuthHeader";
		para.add("strUserName");
		para.add("strPassword");
		para.add("strImageName");
		para.add("iImageSize");
		para.add("strImageFileType");
		para.add("arrImageByte");
		para.add("iImageObjectType");
		para.add("iImageObjectId");
		para.add("strLongitude");
		para.add("strLatitude");
		
		
	}

}
