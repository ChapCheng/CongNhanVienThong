package webservice.dhsc;

import control.Util;
import webservice.BaseTask;

public class LyLichSuaChuaTTPTask extends BaseTask {
	public LyLichSuaChuaTTPTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/GetLsTienTrinhSua";
		METHOD_NAME = "GetLsTienTrinhSua";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/dhscnew/service/WSMobile.asmx?WSDL";
		para.add("ma_dv");
//		if(Util.ttp.getId_ttpho().equals("1")){
//			WSDL="http://123.16.191.37/dhscnew/service/WSMobile.asmx";
//			
//		}
		para.add("id_loaidv");
		para.add("userName");
		para.add("id_ttpho");
		para.add("error");
		
		
		
		
		User_WS = "wscskh";
		Pass_WS = "wscskh@456";
		headerTitle = "AuthHeader";
	}

}
