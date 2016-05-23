package webservice.qltt;

import control.Constant;
import webservice.BaseTask;

public class CheckUserWS extends BaseTask{
	public CheckUserWS() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "ThanhToanTrucTuyen/CheckUserName";
		METHOD_NAME = "CheckUserName";
		NAMESPACE = "ThanhToanTrucTuyen";
		User_WS="TT_SC_STR";
		Pass_WS= Constant.PAYMENT_HEADER_VALUE;
		headerTitle="AuthHeader";
		WSDL = "http://123.16.191.37/VienThongTinh/ThanhToanTrucTuyen.asmx";
		para.add("sUserName");
	}

}
