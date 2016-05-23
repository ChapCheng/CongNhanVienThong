package webservice.dhsc;

import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import congnhanvienthong.entity.dhsc.NoiDungHong;
import control.Util;
import webservice.BaseTask;

public class GetNoiDungBaoHongTask extends BaseTask {
	public GetNoiDungBaoHongTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/WsGetNoiDungBaoHong";
		METHOD_NAME = "WsGetNoiDungBaoHong";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/WebServiceDHSC/dhsc.asmx?WSDL";
		User_WS = "wscskh";
		Pass_WS = "wscskh@456";
		headerTitle = "AuthHeaderDhsc";
		para.add("dichVuId");
		para.add("userName");
	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		SoapObject lstObject = (SoapObject) ((SoapObject) result).getProperty("Result");
		ArrayList<NoiDungHong> lst = Util.GetListData(lstObject, NoiDungHong.class, false);
		return lst;
	}

}
