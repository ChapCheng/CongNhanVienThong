package webservice.gtcas;

import webservice.BaseTask;

public class GetObjectGTCASInfor extends BaseTask {

	public GetObjectGTCASInfor() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/GetObjectInfoOfExch";
		METHOD_NAME = "GetObjectInfoOfExch";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/wslink/wsgtcas.asmx?WSDL";
		para.add("ma_tinh_thanh");
		para.add("user_name");
		para.add("ma_ve_tinh");
		para.add("cableNetwork");
		para.add("pM_OBJECT_FNO");
		para.add("pM_OBJECT_SUB_TYPE_ID");
		para.add("pM_OBJECT_NAME");
		para.add("pM_OBJECT_ADDR");
		para.add("pPageIndex");
		para.add("pPageSize");

		User_WS = "gtcasUser";
		Pass_WS = "gtcasPassword";
		headerTitle = "AuthHeader";
		pUserLabel = "Username";
		pPassLabel = "Password";
	}
}
