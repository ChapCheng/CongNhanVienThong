package webservice.dhsc;

import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import congnhanvienthong.entity.dhsc.LoaiSua;
import congnhanvienthong.entity.dhsc.ThongTinBaoHong;
import control.Util;
import webservice.BaseTask;

public class GetLoaiSuaTask extends BaseTask {
	public GetLoaiSuaTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/WsGetLoaiSua";
		METHOD_NAME = "WsGetLoaiSua";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/WebServiceDHSC/dhscttp.asmx?WSDL";
		User_WS = "wscskh";
		Pass_WS = "wscskh@456";
		headerTitle = "AuthHeaderDhscTtp";
		para.add("loaiDichVuId");
		para.add("nhomSuaId");
		para.add("tinhThanhPhoId");
	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		SoapObject lstObject = (SoapObject) ((SoapObject) result).getProperty("Result");
		ArrayList<LoaiSua> lst = Util.GetListData(lstObject, LoaiSua.class, false);
		return lst;
	}

}
