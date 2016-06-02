package webservice.dhsc;

import org.ksoap2.serialization.SoapObject;

import webservice.BaseTask;

public class TraPhieuWS extends BaseTask {
	public TraPhieuWS() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/WsTonPhieuHoTroTrucTiep";
		METHOD_NAME = "WsTonPhieuHoTroTrucTiep";
		NAMESPACE = "http://tempuri.org/";
		User_WS = "wscskh";
		Pass_WS = "wscskh@456";
		WSDL = "http://123.16.191.37/WebServiceDHSC/dhsc.asmx?WSDL";
		headerTitle = "AuthHeaderDhsc";
	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		SoapObject soapObject = (SoapObject) result;
		return soapObject.getPrimitivePropertySafelyAsString("Result");
	}

}
