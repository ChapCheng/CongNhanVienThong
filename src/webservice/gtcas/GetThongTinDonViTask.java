package webservice.gtcas;

import webservice.BaseTask;

public class GetThongTinDonViTask extends BaseTask{
	public GetThongTinDonViTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/GetDonViQuanLyByTinhThanh";
		METHOD_NAME = "GetDonViQuanLyByTinhThanh";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/wslink/wsgtcas.asmx?WSDL";
		para.add("ma_tinh_thanh");
		para.add("user_name");
		User_WS = "gtcasUser";
		Pass_WS = "gtcasPassword";
		headerTitle = "AuthHeader";
		pUserLabel = "Username";
		pPassLabel = "Password";
		
		
		
	}

}
