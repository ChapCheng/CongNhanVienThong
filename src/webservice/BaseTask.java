package webservice;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;

import android.content.Context;
import congnhanvienthong.entity.MarshalDouble;

public class BaseTask implements WebProtocol {
	public String WSDL;
	public String METHOD_NAME;
	public String SOAP_ACTION;
	public String NAMESPACE;
	public ArrayList<Object> input;
	public ArrayList<String> para;
	public String headerTitle;
	public String User_WS;
	public String Pass_WS;
	public Context context;
	public String pUserLabel;
	public String pPassLabel;
	public Boolean hidden = false;
	public ArrayList<String> paraObj;
	@SuppressWarnings("rawtypes")
	public ArrayList<Class> paraObjType;
	public boolean isFinish = false;
	public Object result;
	public String mess;
	public String erros;

	// public ArrayList<String> paraObj;

	@SuppressWarnings("rawtypes")
	public BaseTask() {
		// TODO Auto-generated constructor stub
		SOAP_ACTION = NAMESPACE + METHOD_NAME;
		NAMESPACE = "http://tempuri.org/";
		input = new ArrayList<Object>();
		para = new ArrayList<String>();
		paraObj = new ArrayList<String>();
		paraObjType = new ArrayList<Class>();
		// paraObj = new ArrayList<String>();
		headerTitle = "headerTitle";
		User_WS = "User_WS";
		Pass_WS = "Pass_WS";

		pUserLabel = "Username";
		pPassLabel = "Password";
	}

	public Object getResult() {
		return result;

	}

	public void execute() {
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		if (!hidden) {
			Element h = new Element().createElement(NAMESPACE, headerTitle);
			Element username = new Element().createElement(NAMESPACE, pUserLabel);
			username.addChild(Node.TEXT, User_WS);
			h.addChild(Node.ELEMENT, username);
			Element pass = new Element().createElement(NAMESPACE, pPassLabel);
			pass.addChild(Node.TEXT, Pass_WS);
			h.addChild(Node.ELEMENT, pass);

			envelope.headerOut = new Element[1];
			envelope.headerOut[0] = h;

		}
		List<HeaderProperty> arrayList = new ArrayList<HeaderProperty>();
		if (hidden) {
			HeaderProperty headerProperty = new HeaderProperty(User_WS, Pass_WS);
			arrayList.add(headerProperty);

		}

		envelope.implicitTypes = true;
		MarshalDouble marshaldDouble = new MarshalDouble();
		marshaldDouble.register(envelope);
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

		if (para.size() > 0) {

			for (int i = 0; i < para.size(); i++) {
				PropertyInfo pInfo = new PropertyInfo();
				pInfo.setName(para.get(i));
				pInfo.setValue(input.get(i));
				pInfo.setType(input.get(i).getClass());
				request.addProperty(pInfo);
			}
		}
		int len = paraObj.size();
		if (len > 0) {
			for (int w = 0; w < len; w++) {
				envelope.addMapping(NAMESPACE, paraObj.get(w), paraObjType.get(w));
			}

		}

		envelope.setOutputSoapObject(request);
		envelope.dotNet = true;
		HttpTransportSE ht = new HttpTransportSE(WSDL, 40000);
		envelope.bodyOut = request;
		ht.debug = true;

		try {
			if (hidden)
				ht.call(SOAP_ACTION, envelope, arrayList);
			else {
				ht.call(SOAP_ACTION, envelope);
			}

			Object object = envelope.getResponse();
			result = object;
		} catch (Exception e) {
			// TODO: handle exception
			// xử lý lỗi
			result = e;
		}

	}

}
