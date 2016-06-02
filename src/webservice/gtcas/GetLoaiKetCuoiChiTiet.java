package webservice.gtcas;

import webservice.BaseTask;

public class GetLoaiKetCuoiChiTiet extends BaseTask{
	public GetLoaiKetCuoiChiTiet() {
		// TODO Auto-generated constructor stub
		super();
		SOAP_ACTION = "http://tempuri.org/GetObjectSubTypesByCableNetworkAndTypeID";
		METHOD_NAME = "GetObjectSubTypesByCableNetworkAndTypeID";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/wslink/wsgtcas.asmx?WSDL";
		// para.add("ma_tinh_thanh");
		// para.add("typeID");
		User_WS = "gtcasUser";
		Pass_WS = "gtcasPassword";
		headerTitle = "AuthHeader";
		pUserLabel = "Username";
		pPassLabel = "Password";
	}

}
