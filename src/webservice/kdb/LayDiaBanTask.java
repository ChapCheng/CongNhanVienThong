package webservice.kdb;

import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import congnhanvienthong.entity.kdb.DiaBan;
import control.Util;
import webservice.BaseTask;

public class LayDiaBanTask extends BaseTask {
	public LayDiaBanTask() {
		// TODO Auto-generated constructor stub
		super();
		WSDL = "http://123.16.191.37/wsvnpt360/KhoanDiaBan_TraCuu360.asmx?WSDL";
		METHOD_NAME = "LayDanhSachDiaBan";
		SOAP_ACTION = "http://tempuri.org/LayDanhSachDiaBan";
		NAMESPACE = "http://tempuri.org/";
	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		SoapObject lstObject = (SoapObject) ((SoapObject) result).getProperty("Data");
		ArrayList<DiaBan> lst = Util.GetListData(lstObject, DiaBan.class, false);
		return lst;
	}

}
