package congnhanvienthong.entity.vitri;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class TAI_NGUYEN_MANG implements KvmSerializable {
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

	public String getTEN_DOITUONG() {
		return TEN_DOITUONG;
	}

	public void setTEN_DOITUONG(String tEN_DOITUONG) {
		TEN_DOITUONG = tEN_DOITUONG;
	}

	public String getDC_DOITUONG() {
		return DC_DOITUONG;
	}

	public void setDC_DOITUONG(String dC_DOITUONG) {
		DC_DOITUONG = dC_DOITUONG;
	}

	public String getMA_PX() {
		return MA_PX;
	}

	public void setMA_PX(String mA_PX) {
		MA_PX = mA_PX;
	}

	public String getMA_QH() {
		return MA_QH;
	}

	public void setMA_QH(String mA_QH) {
		MA_QH = mA_QH;
	}

	public String getDUONG_PHO() {
		return DUONG_PHO;
	}

	public void setDUONG_PHO(String dUONG_PHO) {
		DUONG_PHO = dUONG_PHO;
	}

	public String getMA_VT() {
		return MA_VT;
	}

	public void setMA_VT(String mA_VT) {
		MA_VT = mA_VT;
	}

	public int getID_DOITUONG() {
		return ID_DOITUONG;
	}

	public void setID_DOITUONG(int iD_DOITUONG) {
		ID_DOITUONG = iD_DOITUONG;
	}

	int ID_LOAIDOITUONG;
	int ID_HETHONGGOC;
	String TEN_DOITUONG;
	String DC_DOITUONG;
	String MA_PX;
	String MA_QH;
	String DUONG_PHO;
	String MA_VT;
	int ID_DOITUONG;

	@Override
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		switch (arg0) {
		case 0:
			return ID_LOAIDOITUONG;
		case 1:
			return ID_HETHONGGOC;
		case 2:
			return TEN_DOITUONG;
		case 3:
			return DC_DOITUONG;
		case 4:
			return MA_PX;
		case 5:
			return MA_QH;
		case 6:
			return DUONG_PHO;
		case 7:
			return MA_VT;
		case 8:
			return ID_DOITUONG;

		default:
			return null;

		}
	}

	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 9;
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
			info.name = "TEN_DOITUONG";
			break;
		case 3:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "DC_DOITUONG";
			break;
		case 4:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "MA_PX";
			break;
		case 5:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "MA_QH";
			break;
		case 6:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "DUONG_PHO";
			break;
		case 7:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "MA_VT";
			break;
		case 8:
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
			TEN_DOITUONG = value.toString();
			break;
		case 3:
			DC_DOITUONG = value.toString();
			break;
		case 4:
			MA_PX = value.toString();
			break;
		case 5:
			MA_QH = value.toString();
			break;
		case 6:
			DUONG_PHO = value.toString();
			break;
		case 7:
			MA_VT = value.toString();
			break;
		case 8:
			ID_DOITUONG = Integer.parseInt(value.toString());
			break;
		}

	}

}
