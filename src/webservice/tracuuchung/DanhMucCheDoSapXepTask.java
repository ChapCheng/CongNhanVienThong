package webservice.tracuuchung;

import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import congnhanvienthong.entity.tracuuchung.KieuSapXep;
import congnhanvienthong.entity.tracuuchung.KieuTimKiem;
import control.Util;
import webservice.BaseTask;

public class DanhMucCheDoSapXepTask extends BaseTask {
	public DanhMucCheDoSapXepTask() {
		// TODO Auto-generated constructor stub
		super();
		WSDL = "http://123.16.191.37/wsvnpt360/wscskh_tracuu360.asmx?WSDL";
		METHOD_NAME = "DanhMucCheDoSapXep";
		SOAP_ACTION = "http://tempuri.org/DanhMucCheDoSapXep";
		NAMESPACE = "http://tempuri.org/";
		headerTitle = "AuthHeader";
		User_WS = "cskh_360";
		Pass_WS = "cskh_360";
		pUserLabel = "Username";
		pPassLabel = "Password";

	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		SoapObject lstKieuSapXep = (SoapObject) ((SoapObject) result).getProperty("Result");
		ArrayList<KieuSapXep> lstKieuSapXepObj = new ArrayList<KieuSapXep>();
		int len = 0;
		len = lstKieuSapXep.getPropertyCount();
		for (int i = 0; i < len; i++) {
			SoapObject kieuSapXepSoap = (SoapObject) lstKieuSapXep.getProperty(i);
			KieuSapXep kieuSapXepObj = new KieuSapXep();
			Util.GetObjectFromSoapObject(kieuSapXepObj, kieuSapXepSoap);
			lstKieuSapXepObj.add(kieuSapXepObj);

		}
		return lstKieuSapXepObj;
	}

}
