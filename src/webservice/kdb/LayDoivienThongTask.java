package webservice.kdb;

import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import congnhanvienthong.entity.kdb.DoiVienThong;
import control.Util;
import webservice.BaseTask;

public class LayDoivienThongTask extends BaseTask {
	public LayDoivienThongTask() {
		// TODO Auto-generated constructor stub
		super();
		WSDL = "http://123.16.191.37/wsvnpt360/KhoanDiaBan_TraCuu360.asmx?WSDL";
		METHOD_NAME = "LayDanhSachDoiVt";
		SOAP_ACTION = "http://tempuri.org/LayDanhSachDoiVt";
		NAMESPACE = "http://tempuri.org/";
	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		SoapObject lstObject = (SoapObject) ((SoapObject) result).getProperty("Data");
		ArrayList<DoiVienThong> lst = Util.GetListData(lstObject, DoiVienThong.class, false);
		return lst;
	}

}
