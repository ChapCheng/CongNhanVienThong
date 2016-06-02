package congnhanvienthong.entity.vitri;

import java.io.Serializable;
import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class DoiTuong implements KvmSerializable, Serializable {
	int ID_LOAIDOITUONG;
	int ID_HETHONGGOC;
	int ID_DOITUONG;

	public int getID_LOAIDOITUONG() {
		return ID_LOAIDOITUONG;
	}

	public void setID_LOAIDOITUONG(int iD_LOAIDOITUONG) {
		ID_LOAIDOITUONG = iD_LOAIDOITUONG;
	}

	public int getID_HETHONGGOC() {
		return ID_HETHONGGOC;
	}

	public void setID_HETHONGGOC(int iD_HETHONGGOC) {
		ID_HETHONGGOC = iD_HETHONGGOC;
	}

	public int getID_DOITUONG() {
		return ID_DOITUONG;
	}

	public void setID_DOITUONG(int iD_DOITUONG) {
		ID_DOITUONG = iD_DOITUONG;
	}

	@Override
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		switch (arg0) {
		case 0:
			return ID_LOAIDOITUONG;
		case 1:
			return ID_HETHONGGOC;
		case 2:
			return ID_DOITUONG;

		default:
			return null;

		}
	}

	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo info) {
		// TODO Auto-generated method stub
		switch (arg0) {
		case 0:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "ID_LOAIDOITUONG";
			break;
		case 1:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "ID_HETHONGGOC";
			break;
		case 2:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "ID_DOITUONG";
			break;

		}

	}

	@Override
	public void setProperty(int arg0, Object value) {
		// TODO Auto-generated method stub
		switch (arg0) {
		case 0:
			ID_LOAIDOITUONG = Integer.parseInt(value.toString());
			break;
		case 1:
			ID_HETHONGGOC = Integer.parseInt(value.toString());
			break;
		case 2:
			ID_DOITUONG = Integer.parseInt(value.toString());
			break;
		}

	}

}
