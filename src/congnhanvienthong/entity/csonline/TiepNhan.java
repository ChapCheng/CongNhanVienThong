package congnhanvienthong.entity.csonline;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class TiepNhan implements KvmSerializable {
	int idyckh;
	int idloaidv;
	String tenkh;
	String diachilapdat;
	String tennguoilh;
	String diachilh;
	int idphuong;
	String sodtlh;
	String email;
	String thoigianlh;
	String nguoigt;
	String fonegt;
	String ghichu;
	int idnhanvien;
	@Override
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 14;
	}
	@Override
	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo arg2) {
		switch (arg0) {
		case 0:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "MA_TC";
			break;
		case 1:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "HDTB_ID";
			break;
		case 2:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "MA_THUE_BAO";
			break;
		case 3:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "TEN_THUE_BAO";
			break;
		case 4:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "DIA_CHI_THUE_BAO";
			break;
		case 5:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "NGAY_CAI_DAT";
			break;
		case 6:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "NGUOI_CAI_DAT";
			break;
		case 7:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "LOAIHINH_THUE_BAO";
			break;
		case 8:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "ID_THUEBAO";
			break;
		case 9:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "MA_DICHVU";
			break;
		case 10:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "MA_QUANHUYEN";
			break;
		case 11:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "MA_PHUONGXA";
			break;
		case 12:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "VE_TINH";
			break;

		default:
			break;
		}
		
	}
	@Override
	public void setProperty(int arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
