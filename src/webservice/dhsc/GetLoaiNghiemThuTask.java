package webservice.dhsc;

import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import congnhanvienthong.entity.dhsc.LoaiNghiemThu;
import congnhanvienthong.entity.dhsc.LyDoTon;
import control.Util;
import webservice.BaseTask;

public class GetLoaiNghiemThuTask extends BaseTask {
	public GetLoaiNghiemThuTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/WsGetNoiDungNghiemThu";
		METHOD_NAME = "WsGetNoiDungNghiemThu";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/WebServiceDHSC/dhscttp.asmx?WSDL";
		User_WS = "wscskh";
		Pass_WS = "wscskh@456";
		headerTitle = "AuthHeaderDhscTtp";
		para.add("loaiDichVuId");
		para.add("tinhThanhPhoId");
	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		SoapObject lstObject = (SoapObject) ((SoapObject) result).getProperty("Result");
		ArrayList<LoaiNghiemThu> lst = Util.GetListData(lstObject, LoaiNghiemThu.class, false);
		return lst;
	}

}
