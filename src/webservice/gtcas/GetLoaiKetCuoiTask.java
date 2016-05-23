package webservice.gtcas;

import webservice.BaseTask;

public class GetLoaiKetCuoiTask extends BaseTask{

	public GetLoaiKetCuoiTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/GetObjectTypesByCableNetwork";
		METHOD_NAME = "GetObjectTypesByCableNetwork";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/wslink/wsgtcas.asmx?WSDL";
		para.add("ma_tinh_thanh");
		para.add("loaiMangCapID");
		User_WS = "gtcasUser";
		Pass_WS = "gtcasPassword";
		headerTitle = "AuthHeader";
		pUserLabel = "Username";
		pPassLabel = "Password";
	}
}
