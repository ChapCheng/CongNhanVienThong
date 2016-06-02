package webservice.vitri;

import java.util.HashMap;

import org.ksoap2.serialization.SoapObject;

import android.annotation.SuppressLint;
import congnhanvienthong.entity.vitri.DoiTuong;
import congnhanvienthong.entity.vitri.TAI_NGUYEN_MANG;
import congnhanvienthong.entity.vitri.ThongTinToaDo;
import control.Util;
import webservice.BaseTask;

@SuppressLint("UseSparseArrays")
public class LayListThongTinToaDoTask extends BaseTask {
	public LayListThongTinToaDoTask() {
		// TODO Auto-generated constructor stub
		WSDL = "http://123.16.191.37/WSCSDLViTri/WSCDLViTri.asmx?WSDL";
		METHOD_NAME = "LayToaDoVaAnhDSDoiTuongJson";
		SOAP_ACTION = "http://tempuri.org/LayToaDoVaAnhDSDoiTuongJson";
		NAMESPACE = "http://tempuri.org/";
		User_WS = "ws";
		Pass_WS = "vitri@#$";
		headerTitle = "AuthHeader";
		pUserLabel = "UserName";
		pPassLabel = "Password";
		// -----------------------------------//
	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		Object dmqd = this.lstParameter;
		SoapObject soapObject = (SoapObject) result;
		SoapObject soapThongTin = (SoapObject) soapObject.getProperty(0);
		int len = soapThongTin.getPropertyCount();
		HashMap<Integer, ThongTinToaDo> map = new HashMap<Integer, ThongTinToaDo>();

		for (int i = 0; i < len; i++) {
			try {
				SoapObject temp = (SoapObject) soapThongTin.getProperty(i);
				TAI_NGUYEN_MANG tnm = new TAI_NGUYEN_MANG();
				SoapObject doituongSoap = (SoapObject) temp.getProperty("DoiTuong");
				SoapObject toaDoSoap = (SoapObject) temp.getProperty("ToaDo");
				DoiTuong doiTuong = new DoiTuong();
				ThongTinToaDo toado = new ThongTinToaDo();
				Util.GetObjectFromSoapObject(doiTuong, doituongSoap);
				Util.GetObjectFromSoapObject(toado, toaDoSoap);
				tnm.setID_DOITUONG(doiTuong.getID_DOITUONG());
				map.put(doiTuong.getID_DOITUONG(), toado);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return map;
	}

}
