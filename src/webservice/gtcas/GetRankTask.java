package webservice.gtcas;

import java.util.ArrayList;
import java.util.HashSet;

import org.ksoap2.serialization.SoapObject;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import congnhanvienthong.entity.dhsc.Device;
import congnhanvienthong.entity.gtacs.KetCuoi;
import control.Util;
import webservice.BaseTask;

public class GetRankTask extends BaseTask {

	public GetRankTask() {
		super();
		SOAP_ACTION = "http://tempuri.org/GetVitriFromMaxrange";
		METHOD_NAME = "GetVitriFromMaxrange";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/WSCSDLViTri/WSCSDLViTri/WSCDLViTri.asmx?WSDL";

	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		SoapObject listKetCuoi = (SoapObject) result;
		int length = listKetCuoi.getPropertyCount();

		// khi load mới thì remove toàn bộ dữ liệu cũ đi
		ArrayList<KetCuoi> listKetCuoiObj = new ArrayList<KetCuoi>();
		// chạy vòng lặp, tạo dữ liệu và đổ vào list để sử dụng
		for (int i = 0; i < length; i++) {
			int con = 0;
			KetCuoi temp = null;
			try {
				SoapObject tienTrienSuaObj = (SoapObject) listKetCuoi.getProperty(i);
				temp = new KetCuoi();
				Util.GetObjectFromSoapObject(temp, tienTrienSuaObj);

				con = Integer.parseInt(temp.getO_TOTAL_SIZE()) - Integer.parseInt(temp.getIN_USED())
						- Integer.parseInt(temp.getFAULT_PORT());

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				temp = new KetCuoi();
			}
			temp.setThongTinCong(
					temp.getO_TOTAL_SIZE() + "/" + temp.getIN_USED() + "/" + temp.getFAULT_PORT() + "/" + con);
			listKetCuoiObj.add(temp);
		}
		return listKetCuoiObj;
	}

}
