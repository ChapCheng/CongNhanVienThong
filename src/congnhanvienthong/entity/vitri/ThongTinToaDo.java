package congnhanvienthong.entity.vitri;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class ThongTinToaDo implements KvmSerializable {
	public String getLongtitude() {
		return Longtitude;
	}

	public void setLongtitude(String longtitude) {
		Longtitude = longtitude;
	}

	public String getLatitude() {
		return Latitude;
	}

	public void setLatitude(String latitude) {
		Latitude = latitude;
	}

	public String getGhichu() {
		return Ghichu;
	}

	public void setGhichu(String ghichu) {
		Ghichu = ghichu;
	}

	public long getDistance() {
		return Distancee;
	}

	public void setDistance(long distance) {
		Distancee = distance;
	}

	String Longtitude;
	String Latitude;
	String Ghichu;
	long Distancee;

	@Override
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		switch (arg0) {
		case 0:

			return Longtitude;
		case 1:
			return Latitude;
		case 2:
			return Ghichu;
		case 3:
			return Distancee;

		default:
			return null;
		}
	}

	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo info) {
		// TODO Auto-generated method stub
		switch (arg0) {
		case 0:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "Longtitude";
			break;
		case 1:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "Latitude";
			break;
		case 2:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "Ghichu";
			break;
		case 3:
			info.type = PropertyInfo.LONG_CLASS;
			info.name = "Distance";
			break;
		}

	}

	@Override
	public void setProperty(int arg0, Object value) {
		// TODO Auto-generated method stub
		switch (arg0) {
		case 0:
			Longtitude = value.toString();
			break;
		case 1:
			Latitude = value.toString();
			break;
		case 2:
			Ghichu = value.toString();
			break;
		case 3:
			Distancee = Long.parseLong(value.toString());
			break;
		}

	}

}
