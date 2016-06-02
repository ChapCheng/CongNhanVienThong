package webservice.dhsc;

import org.ksoap2.serialization.SoapObject;

import webservice.BaseTask;

public class NhanPhieuTask extends BaseTask {
	public NhanPhieuTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/WsNhanPhieuHoTroTrucTiep";
		METHOD_NAME = "WsNhanPhieuHoTroTrucTiep";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/WebServiceDHSC/dhscttp.asmx?WSDL";

		User_WS = "wscskh";
		Pass_WS = "wscskh@456";
		headerTitle = "AuthHeaderDhscTtp";
	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		SoapObject reObjet = (SoapObject) result;
		erros = reObjet.getPropertyAsString("IsError");
		return reObjet.getPropertyAsString("Message");
	}

}