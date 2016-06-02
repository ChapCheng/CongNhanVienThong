package webservice.vitri;

import java.util.HashMap;
import java.util.HashSet;

import org.ksoap2.serialization.SoapObject;
import android.annotation.SuppressLint;
import congnhanvienthong.entity.vitri.DoiTuong;
import congnhanvienthong.entity.vitri.ThongTinToaDo;
import control.Util;
import webservice.BaseTask;

public class QuetBanKinhWS extends BaseTask {
	HashSet<Integer> setID;
	public String lstID = "";

	public QuetBanKinhWS() {
		super();
		WSDL = "http://123.16.191.37/WSCSDLViTri/WSCDLViTri.asmx?WSDL";
		METHOD_NAME = "TimKiemDoiTuongTheoKhoangCach";
		SOAP_ACTION = "http://tempuri.org/TimKiemDoiTuongTheoKhoangCach";
		NAMESPACE = "http://tempuri.org/";
		User_WS = "ws";
		Pass_WS = "vitri@#$";
		headerTitle = "AuthHeader";
		pUserLabel = "UserName";
		pPassLabel = "Password";
		setID = new HashSet<Integer>();
	}

	@SuppressLint("UseSparseArrays")
	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		HashMap<Integer, ThongTinToaDo> hashMap = new HashMap<Integer, ThongTinToaDo>();
		try {
			SoapObject soapObject = (SoapObject) result;
			soapObject = (SoapObject) soapObject.getProperty("Result");
			int lenth = soapObject.getPropertyCount();

			for (int i = 0; i < lenth; i++) {
				SoapObject thongtin = (SoapObject) soapObject.getProperty(i);
				System.out.println(thongtin);
				SoapObject soapDoiTuong = (SoapObject) thongtin.getProperty("DoiTuong");
				SoapObject soapToaDo = (SoapObject) thongtin.getProperty("ToaDo");
				DoiTuong doituong = new DoiTuong();
				ThongTinToaDo toado = new ThongTinToaDo();
				Util.GetObjectFromSoapObject(toado, soapToaDo);
				Util.GetObjectFromSoapObject(doituong, soapDoiTuong);
				hashMap.put(doituong.getID_DOITUONG(), toado);
				setID.add(doituong.getID_DOITUONG());

			}
			for (Integer i : setID) {
				lstID = lstID + " " + i;
			}
			lstID = lstID.trim();

		} catch (Exception e) {
			// TODO: handle exceptionẽ
			e.printStackTrace();
		}
		return hashMap;
	}
}
