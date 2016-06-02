package webservice.dhsc;

import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import congnhanvienthong.entity.dhsc.LoaiSua;
import congnhanvienthong.entity.dhsc.LyDoTon;
import control.Util;
import webservice.BaseTask;

public class GetLyDoTonTask extends BaseTask {
	public GetLyDoTonTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/WsGetLyDoTon";
		METHOD_NAME = "WsGetLyDoTon";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/WebServiceDHSC/dhscttp.asmx?WSDL";
		User_WS = "wscskh";
		Pass_WS = "wscskh@456";
		headerTitle = "AuthHeaderDhscTtp";
		// para.add("loaiDichVuId");
		// para.add("nhomTonId");
		// para.add("tinhThanhPhoId");
	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		SoapObject lstObject = (SoapObject) ((SoapObject) result).getProperty("Result");
		ArrayList<LyDoTon> lst = Util.GetListData(lstObject, LyDoTon.class, false);
		return lst;
	}

}
