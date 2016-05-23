package webservice.qltt;

import java.io.IOException;
import java.util.ArrayList;

import org.ksoap2.HeaderProperty;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import congnhanvienthong.entity.qltt.SoapInfo;
import android.util.Log;

public class CallWebService {

	public Object callSoap(SoapInfo soapInfo) {

		Object response = null;

		SoapObject request = new SoapObject(soapInfo.getNameSpace(),
				soapInfo.getMethodName());
		for (PropertyInfo propertyInfo : soapInfo.getPropertyInfoList()) {
			request.addProperty(propertyInfo);
		}
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapSerializationEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		envelope.bodyOut = request;
		HttpTransportSE androidHttpTransport = new HttpTransportSE(
				soapInfo.getWSDL());
		androidHttpTransport.debug = true;
		ArrayList<HeaderProperty> lstHeader = new ArrayList<HeaderProperty>();
		lstHeader.add(soapInfo.getHeaderProperty());
		try {
			androidHttpTransport.call(soapInfo.getSoapAction(), envelope,
					lstHeader);
			response = envelope.getResponse();

		}

		catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (Exception e) {
			Log.w("Loi roi", e.toString());
		}

		return response;

	}
}