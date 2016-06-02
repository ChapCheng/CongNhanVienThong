package webservice.dhsc;

import org.ksoap2.serialization.SoapObject;

import webservice.BaseTask;

public class GetLoaiSuaChiTietTask extends BaseTask {
	public GetLoaiSuaChiTietTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/WsGetLoaiSuaChiTiet";
		METHOD_NAME = "WsGetLoaiSuaChiTiet";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/WebServiceDHSC/dhscttp.asmx?WSDL";
		User_WS = "wscskh";
		Pass_WS = "wscskh@456";
		headerTitle = "AuthHeaderDhscTtp";
	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		try {
			return ((SoapObject) result).getProperty("Result");
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

}
