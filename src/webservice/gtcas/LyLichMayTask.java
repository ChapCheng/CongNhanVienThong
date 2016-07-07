package webservice.gtcas;

import org.ksoap2.serialization.SoapObject;

import congnhanvienthong.entity.gtacs.ThongTinLyLichMay;
import control.Util;
import webservice.BaseTask;

public class LyLichMayTask extends BaseTask {
	public LyLichMayTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/GetInfoOfService_Mobile";
		METHOD_NAME = "GetInfoOfService_Mobile";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/wslink/wsgtcas.asmx?WSDL";
		User_WS = "gtcasUser";
		Pass_WS = "gtcasPassword";
		headerTitle = "AuthHeader";
		pUserLabel = "Username";
		pPassLabel = "Password";
	}
	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		ThongTinLyLichMay thongTinLyLichMay = new ThongTinLyLichMay();
		try {
			
			SoapObject arrString = (SoapObject) result;
			String result = arrString.getPrimitivePropertyAsString("isError");
			String mess = arrString.getPrimitivePropertyAsString("Message");
			if (result.toLowerCase().equals("true")) {
				Util.showAlert(context, mess);
				return thongTinLyLichMay;
			}
			SoapObject lylichmay = (SoapObject) arrString.getProperty("Result");

			

			Util.GetObjectFromSoapObject(thongTinLyLichMay, lylichmay);
			return thongTinLyLichMay;

		} catch (Exception e) {
			// TODO: handle exception
			Util.processException(e, context);
			return thongTinLyLichMay;
			
		}
	}

}
