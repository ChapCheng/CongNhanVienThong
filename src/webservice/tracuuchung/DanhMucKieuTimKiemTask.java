package webservice.tracuuchung;

import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import congnhanvienthong.entity.tracuuchung.KieuSapXep;
import congnhanvienthong.entity.tracuuchung.KieuTimKiem;
import control.Util;
import webservice.BaseTask;

public class DanhMucKieuTimKiemTask extends BaseTask {
	public DanhMucKieuTimKiemTask() {
		// TODO Auto-generated constructor stub
		super();
		WSDL = "http://123.16.191.37/wsvnpt360/wscskh_tracuu360.asmx?WSDL";
		METHOD_NAME = "DanhMucKieuTimKiem";
		SOAP_ACTION = "http://tempuri.org/DanhMucKieuTimKiem";
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
		SoapObject lst = (SoapObject) ((SoapObject) result).getProperty("Result");
		ArrayList<KieuTimKiem> lstKieuSapXepObj = new ArrayList<KieuTimKiem>();
		int len = 0;
		len = lst.getPropertyCount();
		for (int i = 0; i < len; i++) {
			SoapObject kieuSapXepSoap = (SoapObject) lst.getProperty(i);
			KieuTimKiem kieuSapXepObj = new KieuTimKiem();
			Util.GetObjectFromSoapObject(kieuSapXepObj, kieuSapXepSoap);
			lstKieuSapXepObj.add(kieuSapXepObj);

		}
		return lstKieuSapXepObj;

	}

}
