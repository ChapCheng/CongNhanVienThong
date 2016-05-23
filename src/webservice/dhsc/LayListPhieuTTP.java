package webservice.dhsc;

import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import congnhanvienthong.entity.dhsc.ThongTinBaoHong;
import control.Util;
import webservice.BaseTask;

public class LayListPhieuTTP extends BaseTask {
	public LayListPhieuTTP() {
		// TODO Auto-generated constructor stub
		super();
		METHOD_NAME = "WsGetListSuaChuaHoTroTrucTiep";
		SOAP_ACTION = "http://tempuri.org/WsGetListSuaChuaHoTroTrucTiep";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/WebServiceDHSC/dhscttp.asmx?WSDL";
		// if(Util.ttp.getId_ttpho().equals("1")){
		// WSDL="http://123.16.191.37/dhsc/Service/HTTT.asmx";
		// METHOD_NAME = "LayDanhSachPhieuTon";
		// SOAP_ACTION = "http://tempuri.org/LayDanhSachPhieuTon";
		// ?op=
		// }
		pPassLabel = "Password";
		pUserLabel = "Username";
		User_WS = "wscskh";
		Pass_WS = "wscskh@456";
		headerTitle = "AuthHeaderDhscTtp";
		para.add("userName");
		para.add("loaiDichVuId");
		para.add("pStartRowIndex");
		para.add("pPageSize");
		para.add("tinhThanhPhoId");

	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		SoapObject lstObject = (SoapObject) ((SoapObject) result).getProperty("Result");
		ArrayList<ThongTinBaoHong> lst = Util.GetListData(lstObject, ThongTinBaoHong.class, false);
		return lst;
	}

}