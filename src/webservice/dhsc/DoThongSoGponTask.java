package webservice.dhsc;

import org.ksoap2.serialization.SoapObject;

import congnhanvienthong.entity.dhsc.ThamSoGPon;
import control.Util;
import webservice.BaseTask;

public class DoThongSoGponTask extends BaseTask {
	public DoThongSoGponTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/DoThongSoPon";
		METHOD_NAME = "DoThongSoPon";
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
		ThamSoGPon thamso = new ThamSoGPon();
		Util.GetObjectFromSoapObject(thamso, soapObject);
		return thamso;

	}

}
