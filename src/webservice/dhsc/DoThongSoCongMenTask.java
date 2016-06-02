package webservice.dhsc;

import org.ksoap2.serialization.SoapObject;

import webservice.BaseTask;

public class DoThongSoCongMenTask extends BaseTask {
	public DoThongSoCongMenTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/DoTrangThaiCongMEN";
		METHOD_NAME = "DoTrangThaiCongMEN";
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
		String thamso = ((SoapObject) result).getPropertyAsString("Result");
		return thamso;
	}

}
