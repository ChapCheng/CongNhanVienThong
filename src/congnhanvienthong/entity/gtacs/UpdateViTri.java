package congnhanvienthong.entity.gtacs;

import java.io.Serializable;
import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class UpdateViTri implements KvmSerializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3537506877285369158L;
	public String MaTinhThanh;
	public String Long;
	public String Lat;
	public String UserName;
	public long ObjectId;

	public String getMaTinhThanh() {
		return MaTinhThanh;
	}

	public void setMaTinhThanh(String maTinhThanh) {
		MaTinhThanh = maTinhThanh;
	}

	public String getLong() {
		return Long;
	}

	public void setLong(String l) {
		Long = l;
	}

	public String getLat() {
		return Lat;
	}

	public void setLat(String lat) {
		Lat = lat;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public long getObjectId() {
		return ObjectId;
	}

	public void setObjectId(long objectId) {
		ObjectId = objectId;
	}

	@Override
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		switch (arg0) {
		case 0:
			return MaTinhThanh;
		case 1:
			return Long;
		case 2:
			return Lat;
		case 3:
			return UserName;
		case 4:
			return ObjectId;
		}

		return null;
	}

	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public void getPropertyInfo(int index, Hashtable arg1, PropertyInfo info) {
		// TODO Auto-generated method stub
		switch (index) {
		case 0:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "MaTinhThanh";
			break;
		case 1:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "Long";
			break;
		case 2:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "Lat";
			break;
		case 3:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "UserName";
			break;
		case 4:
			info.type = PropertyInfo.LONG_CLASS;
			info.name = "ObjectId";
			break;
		default:
			break;
		}

	}

	@Override
	public void setProperty(int index, Object value) {
		// TODO Auto-generated method stub
		switch (index) {
		case 0:
			MaTinhThanh = value.toString();
			break;
		case 1:
			Long = value.toString();
			break;
		case 2:
			Lat = value.toString();
			break;
		case 3:
			UserName = value.toString();
			break;
		case 4:
			ObjectId = (long) Float.parseFloat(value.toString());
			break;
		default:
			break;
		}
	}

}
