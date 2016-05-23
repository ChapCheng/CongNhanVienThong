package congnhanvienthong.entity.qltt;

import java.util.ArrayList;

import org.ksoap2.HeaderProperty;
import org.ksoap2.serialization.PropertyInfo;

public class SoapInfo {
	private String WSDL;
	private String methodName;
	private String soapAction;
	private String nameSpace;
	private ArrayList<PropertyInfo> propertyInfoList;
	private HeaderProperty headerProperty;

	public String getWSDL() {
		return WSDL;
	}

	public void setwSDL(String wSDL) {
		this.WSDL = wSDL;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getSoapAction() {
		return soapAction;
	}

	public void setSoapAction(String soapAction) {
		this.soapAction = soapAction;
	}

	public String getNameSpace() {
		return nameSpace;
	}

	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}

	public ArrayList<PropertyInfo> getPropertyInfoList() {
		return propertyInfoList;
	}

	public void setPropertyInfoList(ArrayList<PropertyInfo> propertyInfoList) {
		this.propertyInfoList = propertyInfoList;
	}

	public HeaderProperty getHeaderProperty() {
		return headerProperty;
	}

	public void setHeaderProperty(HeaderProperty headerProperty) {
		this.headerProperty = headerProperty;
	}

	public void addParameter(String name, Object value) {
		PropertyInfo propertyInfo = new PropertyInfo();
		propertyInfo.setName(name);
		propertyInfo.setValue(value);
		// propertyInfo.setType(value.getClass());
		propertyInfoList.add(propertyInfo);
	}
}
