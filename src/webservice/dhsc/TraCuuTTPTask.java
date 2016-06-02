package webservice.dhsc;

import org.ksoap2.serialization.SoapObject;

import congnhanvienthong.entity.dhsc.ThongTinDichVu;
import control.Util;
import webservice.BaseTask;

public class TraCuuTTPTask extends BaseTask {
	public TraCuuTTPTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/WsGetThongTinDichVu";
		METHOD_NAME = "WsGetThongTinDichVu";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37//WebServiceDHSC/dhscttp.asmx?WSDL";
		headerTitle = "AuthHeaderDhscTtp";
		if (Util.ttp.getId_ttpho().equals("1")) {
			WSDL = "http://123.16.191.37/WebServiceDHSC/dhsc.asmx";
			headerTitle = "AuthHeaderDhsc";
		}
		User_WS = "wscskh";
		Pass_WS = "wscskh@456";

	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		SoapObject lstObject = (SoapObject) ((SoapObject) result).getProperty("Result");
		ThongTinDichVu thongTinDichVu = new ThongTinDichVu();
		Util.GetObjectFromSoapObject(thongTinDichVu, lstObject);
		return thongTinDichVu;
	}

}