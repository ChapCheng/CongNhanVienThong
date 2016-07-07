package webservice.dhsc;

import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import congnhanvienthong.entity.dhsc.ThongTinVissaFiberVNN;
import congnhanvienthong.entity.dhsc.ThongTinVissaMyTV;
import congnhanvienthong.entity.dhsc.VissaLogFiberAndMega;
import control.Util;
import webservice.BaseTask;

public class TraThongTinVissaTask extends BaseTask {
	public int loaiDv;

	public TraThongTinVissaTask(int loaiDv) {
		super();
		this.loaiDv = loaiDv;
		if (loaiDv == 20 || loaiDv == 50) {
			NAMESPACE = "http://tempuri.org/";
			WSDL = "http://123.16.191.37/webservicedhsc/wsvisa.asmx?WSDL";
			SOAP_ACTION = "http://tempuri.org/GetAccountInfoByMaTinh4Mobile";
			METHOD_NAME = "GetAccountInfoByMaTinh4Mobile";
		}
		if (loaiDv == 80) {
			NAMESPACE = "http://tempuri.org/";
			WSDL = "http://123.16.191.37/WebServiceDHSC/newmytvws.asmx?WSDL";
			SOAP_ACTION = "http://tempuri.org/GetInfoMyTV4Mobile";
			METHOD_NAME = "GetInfoMyTV4Mobile";

		}

	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub

		if (((SoapObject) result).getPrimitivePropertyAsString("isError").equals("true")) {
			Util.showAlert(context, ((SoapObject) result).getPrimitivePropertyAsString("Message"));
			return null;
		} else {
			SoapObject thongtinObject = (SoapObject) ((SoapObject) result).getProperty("Result");
			if (this.loaiDv != 80) {
				ThongTinVissaFiberVNN thongtin = new ThongTinVissaFiberVNN();
				Util.GetObjectFromSoapObject(thongtin, thongtinObject);
				SoapObject visaLogs = (SoapObject) thongtinObject.getProperty("VisaLogs");
				ArrayList<VissaLogFiberAndMega> lst = Util.GetListData(visaLogs, VissaLogFiberAndMega.class, false);
				thongtin.setVisaLogs(lst);

				return thongtin;
			} else {
				ThongTinVissaMyTV thongtin = new ThongTinVissaMyTV();
				Util.GetObjectFromSoapObject(thongtin, thongtinObject);
				return thongtin;
			}
		}
	}

}
