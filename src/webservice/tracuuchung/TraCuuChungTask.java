package webservice.tracuuchung;

import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import congnhanvienthong.entity.tracuuchung.ThongTinTraCuuChung;
import control.Util;
import webservice.BaseTask;

public class TraCuuChungTask extends BaseTask {
	public TraCuuChungTask() {
		// TODO Auto-generated constructor stub
		super();
		WSDL = "http://123.16.191.37/wsvnpt360/wscskh_tracuu360.asmx?WSDL";
		METHOD_NAME = "TraCuu";
		SOAP_ACTION = "http://tempuri.org/TraCuu";
		NAMESPACE = "http://tempuri.org/";
		headerTitle = "AuthHeader";
		User_WS = "cskh_360";
		Pass_WS = "cskh_360";
		pUserLabel = "Username";
		pPassLabel = "Password";
		// para.add("SearchText");
		// para.add("SearchOption");
		// para.add("SearchOrder");
		// para.add("TinhTP_ID");
	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		SoapObject lstThongTinTimKiem = (SoapObject) ((SoapObject) result).getProperty("Result");
		int len = 0;
		len = lstThongTinTimKiem.getPropertyCount();
		ArrayList<ThongTinTraCuuChung> lstThongTinTraCuuChungObj = new ArrayList<ThongTinTraCuuChung>();
		for (int i = 0; i < len; i++) {
			SoapObject ttTraCuu = (SoapObject) lstThongTinTimKiem.getProperty(i);
			ThongTinTraCuuChung thongtintracuuchung = new ThongTinTraCuuChung();
			Util.GetObjectFromSoapObject(thongtintracuuchung, ttTraCuu);
			lstThongTinTraCuuChungObj.add(thongtintracuuchung);

		}
		return lstThongTinTraCuuChungObj;
	}

}
