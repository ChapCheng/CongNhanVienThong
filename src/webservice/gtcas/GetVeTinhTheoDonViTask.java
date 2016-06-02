package webservice.gtcas;

import webservice.BaseTask;

public class GetVeTinhTheoDonViTask extends BaseTask {
	public GetVeTinhTheoDonViTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/GetVeTinhByDonViQuanLy";
		METHOD_NAME = "GetVeTinhByDonViQuanLy";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/wslink/wsgtcas.asmx?WSDL";
		// para.add("ma_tinh_thanh");
		// para.add("user_name");
		// para.add("maDonViQL");
		User_WS = "gtcasUser";
		Pass_WS = "gtcasPassword";
		headerTitle = "AuthHeader";
		pUserLabel = "Username";
		pPassLabel = "Password";
	}

}
