package congnhanvienthong.entity.pttb;

import java.io.Serializable;
import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

@SuppressWarnings("serial")
public class ChiTietThueBao implements KvmSerializable, Serializable {

	public String longtitude;
	public String latitude;
	public String image;
	public String dichvucc;

	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		String retVal = "";
		
		switch (arg0) {
		case 0:
			retVal = longtitude;
			break;

		case 1:
			retVal = latitude;
			break;
		case 2:
			retVal = image;
			break;
		case 3:
			retVal = dichvucc;
			break;
		}
		return retVal;
	}

	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@SuppressWarnings("rawtypes")
	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo arg2) {
		// TODO Auto-generated method stub
		switch (arg0) {
		case 0:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "longtitude";
			break;
		case 1:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "latitude";
			break;
		case 2:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "image";
			break;
		case 3:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "dichvucc";
			break;

		default:
			break;
		}

	}

	public void setProperty(int arg0, Object arg1) {
		// TODO Auto-generated method stub
		switch (arg0) {
		case 0:
			longtitude = arg1.toString();
			break;
		case 1:
			latitude = arg1.toString();
			break;
		case 2:
			image = arg1.toString();
			break;
		case 3:
			dichvucc = arg1.toString();
			break;
		}

	}

}
