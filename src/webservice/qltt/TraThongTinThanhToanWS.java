package webservice.qltt;

import com.congnhanvienthong.qltt.Constants;

import webservice.BaseTask;

public class TraThongTinThanhToanWS extends BaseTask {
	public TraThongTinThanhToanWS() {
		// TODO Auto-generated constructor stub
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "ThanhToanTrucTuyen/LayThongTinKhachHang";
		METHOD_NAME = "LayThongTinKhachHang";
		NAMESPACE = "ThanhToanTrucTuyen";
		User_WS = "TT_SC_STR";
		Pass_WS = Constants.PAYMENT_HEADER_VALUE;
		headerTitle = "Header";
		WSDL = "http://123.16.191.37/VienThongTinh/ThanhToanTrucTuyen.asmx";
		hidden = true;
	}

}
