package webservice.dhsc;

import control.Util;
import webservice.BaseTask;

public class TraCuuTTPTask extends BaseTask {
	public TraCuuTTPTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/GetThongTinDichVu";
		METHOD_NAME = "GetThongTinDichVu";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/dhscnew/service/WSMobile.asmx?WSDL";
//		if(Util.ttp.getId_ttpho().equals("1")){
//			WSDL="http://123.16.191.37/dhscnew/service/WSMobile.asmx";
//		}
		User_WS = "wscskh";
		Pass_WS = "wscskh@456";
		headerTitle = "AuthHeader";
		para.add("ma_dv");
		para.add("id_loaidichvu");
		para.add("userName");
		para.add("id_ttpho");
		para.add("error");
	}

}