package webservice.qltt;

import java.util.ArrayList;


import org.ksoap2.HeaderProperty;
import org.ksoap2.serialization.PropertyInfo;

import congnhanvienthong.entity.qltt.SoapInfo;
import com.congnhanvienthong.qltt.Constants;

public class PaymentsWebServices {

	private void setValueForSoap(SoapInfo soapInfo, String sFunctionName,
			String[] params, Object[] values) {

		final String SOAP_ACTION = Constants.PAYMENT_NAME_SPACE + "/"
				+ sFunctionName;
		//
		// Set property for SOAP
		//
		soapInfo.setwSDL(Constants.PAYMENT_WEBSERVICE_LINK);
		soapInfo.setMethodName(sFunctionName);
		soapInfo.setNameSpace(Constants.PAYMENT_NAME_SPACE);
		soapInfo.setSoapAction(SOAP_ACTION);
		HeaderProperty headerProperty = new HeaderProperty(
				Constants.PAYMENT_HEADER_KEY, Constants.PAYMENT_HEADER_VALUE);
		soapInfo.setHeaderProperty(headerProperty);

		ArrayList<PropertyInfo> propertyInfoList = new ArrayList<PropertyInfo>();
		soapInfo.setPropertyInfoList(propertyInfoList);
		//
		// Set value for parameters
		//
		for (int i = 0; i < params.length; i++) {

			soapInfo.addParameter(params[i], values[i]);
		}
	}

	public String getDebtInfomation(String billingCode, String userName, String authenticationString ) {

		String customerInfo = "";

		SoapInfo soapInfo = new SoapInfo();

		String[] params = new String[] { "sThongTinTim", "sLoaiDV", "sUserName", "sAuthenticationString" };

		Object[] values = new Object[] { billingCode, "CD", userName, authenticationString };

		setValueForSoap(soapInfo, Constants.METHOD_GET_DEBT_INFOMATION, params,
				values);
	
		CallWebService callService = new CallWebService();

		Object response = callService.callSoap(soapInfo);

		if (response != null) {

			customerInfo = response.toString();
		}

		return customerInfo;

	}

	public String login(String userName, String password) {

		String sResult = "";

		SoapInfo soapInfo = new SoapInfo();

		String[] params = new String[] { "strUserName", "strPassword" };

		Object[] values = new Object[] { userName, password };

		setValueForSoap(soapInfo, Constants.METHOD_LOGIN, params, values);

		CallWebService callService = new CallWebService();

		Object response = callService.callSoap(soapInfo);

		if (response != null) {

			sResult = response.toString();
		}

		return sResult;
	}

	public String payBills(String billingCode, String transactionDate,
			String amounts, String gateway, String transaction_Id,
			String userName, String paymentTypes, String note, String authenticationString) {

		String sResult = "";

		SoapInfo soapInfo = new SoapInfo();

		String[] params = new String[] { "sMaThanhToan", "sNgayGD",
				"sSoTien", "sGateWay", "sTransaction", "sUserName",
				"sLoaiGachNo", "sGhiChu", "sAuthenticationString" };

		Object[] values = new Object[] { billingCode, transactionDate, amounts,
				gateway, transaction_Id, userName, paymentTypes, note,authenticationString };

		setValueForSoap(soapInfo, Constants.METHOD_PAY_BILLS, params, values);

		CallWebService callService = new CallWebService();

		Object response = callService.callSoap(soapInfo);

		if (response != null) {

			sResult = response.toString();
		}

		return sResult;
	}

	public String getAuthenticationString(String userName, String password) {

		String sResult = "";

		String[] params = new String[] { "sUserName", "sPassword" };

		Object[] values = new Object[] { userName, password };

		SoapInfo soapInfo = new SoapInfo();

		setValueForSoap(soapInfo, Constants.METHOD_GET_AUTHENTICATION_STRING, params, values);

		CallWebService callService = new CallWebService();

		Object response = callService.callSoap(soapInfo);

		if (response != null) {

			sResult = response.toString();
		}

		return sResult;
	}
	
	public String checkUser(String userName){
		
		String sResult="";

		String[] params = new String[] { "sUserName"};

		Object[] values = new Object[] { userName};

		SoapInfo soapInfo = new SoapInfo();

		setValueForSoap(soapInfo, Constants.METHOD_CHECK_USERNAME, params, values);

		CallWebService callService = new CallWebService();

		Object response = callService.callSoap(soapInfo);

		if (response != null) {

			sResult = response.toString();
		}

		return sResult;
	}

}
