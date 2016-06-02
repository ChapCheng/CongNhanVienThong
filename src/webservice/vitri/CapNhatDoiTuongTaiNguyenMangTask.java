package webservice.vitri;

import org.ksoap2.serialization.SoapObject;

import congnhanvienthong.entity.vitri.TAI_NGUYEN_MANG;
import congnhanvienthong.entity.vitri.ThongTinToaDo;
import webservice.BaseTask;

public class CapNhatDoiTuongTaiNguyenMangTask extends BaseTask {
	public CapNhatDoiTuongTaiNguyenMangTask() {
		// TODO Auto-generated constructor stub
		super();
		WSDL = "http://123.16.191.37/WSCSDLViTri/WSCDLViTri.asmx?WSDL";
		METHOD_NAME = "CapNhatDoiTuongTaiNguyenMang";
		SOAP_ACTION = "http://tempuri.org/CapNhatDoiTuongTaiNguyenMang";
		NAMESPACE = "http://tempuri.org/";
		User_WS = "ws";
		Pass_WS = "vitri@#$";
		headerTitle = "AuthHeader";
		pUserLabel = "UserName";
		pPassLabel = "Password";
		paraObj.add("TAI_NGUYEN_MANG");
		paraObjType.add(TAI_NGUYEN_MANG.class);
		// -----------------------------------//
		paraObj.add("objThongTinToaDo");
		paraObjType.add(ThongTinToaDo.class);
	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		SoapObject soapObject = (SoapObject) result;

		return soapObject.getPropertyAsString("Message");
	}

}
