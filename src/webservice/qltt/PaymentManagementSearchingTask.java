package webservice.qltt;

import control.Constant;
import webservice.BaseTask;

public class PaymentManagementSearchingTask extends BaseTask {
	public PaymentManagementSearchingTask() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/GetDebitDetails";
		METHOD_NAME = "GetDebitDetails";
		NAMESPACE = "http://tempuri.org/";
		User_WS = "TT_SC_STR";
		Pass_WS = Constant.PAYMENTSEARCH__HEADER_VALUE;
		headerTitle = "Header";
		WSDL = "http://123.16.191.37/PaymentManagementSearching/PaymentManagementSearching.asmx";
		// para.add("value");
		// para.add("cycle");
		// para.add("province");
		hidden = true ;
	}

}
