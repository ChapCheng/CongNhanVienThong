package webservice;

import java.util.Vector;

import org.ksoap2.serialization.SoapObject;

import com.google.android.gms.internal.el;
import com.google.android.gms.maps.model.LatLng;

import android.widget.Toast;
import congnhanvienthong.entity.ViTriThueBao;
import control.Util;

public class GetViTriTask extends BaseTask {
	public GetViTriTask() {
		super();
		SOAP_ACTION = "http://tempuri.org/LayThongTinVSDLVT_Mobile";
		METHOD_NAME = "LayThongTinVSDLVT_Mobile";
		NAMESPACE = "http://tempuri.org/";
		WSDL = "http://123.16.191.37/WSCSDLViTri/WSCSDLViTri/WSCDLViTri.asmx?WSDL";
		User_WS = "wsdhsc";
		Pass_WS = "wsdhsc@123";
		headerTitle = "AuthHeaderHTTT";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		Vector<Object> vec = (Vector<Object>) result;
		SoapObject soapObject = (SoapObject) vec.get(1);
		LatLng latLan = null;
		ViTriThueBao vitri = new ViTriThueBao();
		Util.GetObjectFromSoapObject(vitri, soapObject);
		if (vitri.getTONTAI() == 0)
			Toast.makeText(context, "Chưa có dữ liệu vị trí", Toast.LENGTH_SHORT).show();
		else {
			latLan = new LatLng(Double.valueOf(vitri.getLATITUDE()), Double.valueOf(vitri.getLONGITUDE()));
		}
		return latLan;
	}

}
