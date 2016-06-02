package webservice.pttb;

import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import congnhanvienthong.entity.pttb.ThongTinToaNha;
import control.Util;
import webservice.BaseTask;

public class QuetToaNhaTheoDanhSachWS extends BaseTask {
	public QuetToaNhaTheoDanhSachWS() {
		// TODO Auto-generated constructor stub
		WSDL = "http://123.16.191.37/wsvnpt360/AndroidPTTB_Tracuu360.asmx?WSDL";
		METHOD_NAME = "LAY_DANHSACH_TOANHA_THEO_DS";
		SOAP_ACTION = "http://tempuri.org/LAY_DANHSACH_TOANHA_THEO_DS";
		NAMESPACE = "http://tempuri.org/";

	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		ArrayList<ThongTinToaNha> thongTinToaNhas = new ArrayList<ThongTinToaNha>();
		SoapObject soapObject = (SoapObject) result;
		soapObject = (SoapObject) soapObject.getProperty("Result");
		thongTinToaNhas = Util.GetListData(soapObject, ThongTinToaNha.class, false);
		return thongTinToaNhas;

	}

}
