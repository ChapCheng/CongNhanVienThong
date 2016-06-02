package webservice.dhsc;

import webservice.BaseTask;

public class DoThuTask extends BaseTask {
	public DoThuTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/DoThuDSLAM";
		METHOD_NAME = "DoThuDSLAM";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/dhsc/service/WSMobile.asmx?WSDL";
		User_WS = "wsdhsc";
		Pass_WS = "wsdhsc@123";
		headerTitle = "AuthHeader";
		pUserLabel = "Username";
		pPassLabel = "Password";
		// para.add("p_id_loaidichvu"); //
		// <p_id_loaidichvu>int</p_id_loaidichvu>
		// para.add("p_ma_dichvu"); // <p_ma_dichvu>string</p_ma_dichvu>
		// para.add("id_ttpho"); // <id_ttpho>int</id_ttpho>

	}

}
