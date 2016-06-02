package webservice.kdb;

import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import congnhanvienthong.entity.kdb.TrungTamVienThong;
import control.Util;
import webservice.BaseTask;

public class LayTrungTamVienThongTask extends BaseTask {
	public LayTrungTamVienThongTask() {
		// TODO Auto-generated constructor stub
		super();
		WSDL = "http://123.16.191.37/wsvnpt360/KhoanDiaBan_TraCuu360.asmx?WSDL";
		METHOD_NAME = "LayDanhSachTtvt";
		SOAP_ACTION = "http://tempuri.org/LayDanhSachTtvt";
		NAMESPACE = "http://tempuri.org/";
	}

	@Override
	public Object getResult() {
		SoapObject lstObject = (SoapObject) ((SoapObject) result).getProperty("Data");
		ArrayList<TrungTamVienThong> lst = Util.GetListData(lstObject, TrungTamVienThong.class, false);
		return lst;
	}

}
