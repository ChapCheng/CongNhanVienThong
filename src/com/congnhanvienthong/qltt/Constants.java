package com.congnhanvienthong.qltt;

public class Constants {
	
	public static final String PAYMENT_WEBSERVICE_LINK = "http://123.16.191.37/VienThongTinh/ThanhToanTrucTuyen.asmx";

	public static final String PAYMENT_NAME_SPACE = "ThanhToanTrucTuyen";
	public static final String PAYMENT_HEADER_KEY = "TT_SC_STR";
	public static final String PAYMENT_HEADER_VALUE = "5af6hTWtpGd7vUfp2QfW17dWpwrXccwTz06XB8SJAH4cmxFIpCwYB3RDNeyIkE9ppi6v5BZos+usOtQopcXk5A=VI$=23+y=xPjjf4/75rnGAuC/GJ==";
	//
	// Methods
	//
	public static final String METHOD_GET_DEBT_INFOMATION = "LayThongTinKhachHang";
	public static final String METHOD_LOGIN = "Login";
	public static final String METHOD_PAY_BILLS = "GachNoTheoUser";
	public static final String METHOD_GET_AUTHENTICATION_STRING = "GetAuthenticationString";
	public static final String METHOD_CHECK_USERNAME = "CheckUserName";
	//
	// Parameters
	//
	public static final String GATE_WAY = "0011";
	public static final String TRANSACTION_ID = "1111";
	public static final String PAYMENTS_TYPE = "002";
	public static final String NOTE = "";
	//
	// Files
	//
	public static final String FILE_AUTHENTICATION = "authentication_string";
	public static final String AUTHENTICATION_KEY="authentication_string";
}
