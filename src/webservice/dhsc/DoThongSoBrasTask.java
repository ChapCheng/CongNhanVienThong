package webservice.dhsc;

import org.ksoap2.serialization.SoapObject;

import congnhanvienthong.entity.dhsc.ThongSoBras;
import control.Util;
import webservice.BaseTask;

public class DoThongSoBrasTask extends BaseTask {
	public DoThongSoBrasTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/KiemTraTrangThaiBras";
		METHOD_NAME = "KiemTraTrangThaiBras";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/WebServiceDHSC/dothuws.asmx?WSDL";
		User_WS = "wsdhsc";
		Pass_WS = "wsdhsc@123";
		headerTitle = "AuthHeader";
		pUserLabel = "Username";
		pPassLabel = "Password";
	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		SoapObject soapObject = (SoapObject) ((SoapObject) result).getProperty("Result");
		ThongSoBras thamso = new ThongSoBras();
		Util.GetObjectFromSoapObject(thamso, soapObject);
		return thamso;
	}

}
