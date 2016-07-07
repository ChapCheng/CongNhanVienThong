package webservice.tracuuchung;

import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import congnhanvienthong.entity.tracuuchung.ThongTinTraCuuChung;
import control.Util;
import webservice.BaseTask;

public class TraCuuNhanhTask extends BaseTask {
	public TraCuuNhanhTask() {
		// TODO Auto-generated constructor stub
		WSDL = "http://10.10.40.21/cskh/ws/tracuu360.asmx?WSDL";
		METHOD_NAME = "TraCuuNhanh";
		SOAP_ACTION = "http://tempuri.org/TraCuuNhanh";
		NAMESPACE = "http://tempuri.org/";
		headerTitle = "WebserviceUser";
		User_WS = "cskh_360";
		Pass_WS = "cskh_360";
		pUserLabel = "UserName";
		pPassLabel = "Password";
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
