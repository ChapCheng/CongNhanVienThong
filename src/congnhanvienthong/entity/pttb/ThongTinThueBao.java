package congnhanvienthong.entity.pttb;

import java.io.Serializable;
import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import control.AnnotationField;

@SuppressWarnings("serial")
public class ThongTinThueBao implements KvmSerializable, Serializable {
	/**
	 * 
	 */
	// private static final long serialVersionUID = 1L;
	@AnnotationField(hienthi = true, order = 0, tenNhan = "Mã T.Công")
	private String MA_TC;
	private String HDTB_ID;
	@AnnotationField(hienthi = true, order = 1, tenNhan = "Mã T.bao")
	private String MA_THUE_BAO;
	@AnnotationField(hienthi = true, order = 2, tenNhan = "Tên T.bao")
	private String TEN_THUE_BAO;
	@AnnotationField(hienthi = false, order = 2, tenNhan = "Đ.Chỉ T.bao")
	private String DIA_CHI_THUE_BAO;
	@AnnotationField(hienthi = false, order = 2, tenNhan = "Ngày C.Đặt")
	private String NGAY_CAI_DAT;
	@AnnotationField(hienthi = false, order = 2, tenNhan = "Người C.Đặt")
	private String NGUOI_CAI_DAT;
	private String LOAIHINH_THUE_BAO;
	private String ID_THUEBAO;
	private String MA_DICHVU;
	private String MA_QUANHUYEN;
	private String MA_PHUONGXA;
	private String VE_TINH;

	public String getMA_TC() {
		return MA_TC;
	}

	public void setMA_TC(String mA_TC) {
		MA_TC = mA_TC;
	}

	public String getHDTB_ID() {
		return HDTB_ID;
	}

	public void setHDTB_ID(String hDTB_ID) {
		HDTB_ID = hDTB_ID;
	}

	public String getMA_THUE_BAO() {
		return MA_THUE_BAO;
	}

	public void setMA_THUE_BAO(String mA_THUE_BAO) {
		MA_THUE_BAO = mA_THUE_BAO;
	}

	public String getTEN_THUE_BAO() {
		return TEN_THUE_BAO;
	}

	public void setTEN_THUE_BAO(String tEN_THUE_BAO) {
		TEN_THUE_BAO = tEN_THUE_BAO;
	}

	public String getDIA_CHI_THUE_BAO() {
		return DIA_CHI_THUE_BAO;
	}

	public void setDIA_CHI_THUE_BAO(String dIA_CHI_THUE_BAO) {
		DIA_CHI_THUE_BAO = dIA_CHI_THUE_BAO;
	}

	public String getNGAY_CAI_DAT() {
		return NGAY_CAI_DAT;
	}

	public void setNGAY_CAI_DAT(String nGAY_CAI_DAT) {
		NGAY_CAI_DAT = nGAY_CAI_DAT;
	}

	public String getNGUOI_CAI_DAT() {
		return NGUOI_CAI_DAT;
	}

	public void setNGUOI_CAI_DAT(String nGUOI_CAI_DAT) {
		NGUOI_CAI_DAT = nGUOI_CAI_DAT;
	}

	public String getLOAIHINH_THUE_BAO() {
		return LOAIHINH_THUE_BAO;
	}

	public void setLOAIHINH_THUE_BAO(String lOAIHINH_THUE_BAO) {
		LOAIHINH_THUE_BAO = lOAIHINH_THUE_BAO;
	}

	public String getID_THUEBAO() {
		return ID_THUEBAO;
	}

	public void setID_THUEBAO(String iD_THUEBAO) {
		ID_THUEBAO = iD_THUEBAO;
	}

	public String getMA_DICHVU() {
		return MA_DICHVU;
	}

	public void setMA_DICHVU(String mA_DICHVU) {
		MA_DICHVU = mA_DICHVU;
	}

	public String getMA_QUANHUYEN() {
		return MA_QUANHUYEN;
	}

	public void setMA_QUANHUYEN(String mA_QUANHUYEN) {
		MA_QUANHUYEN = mA_QUANHUYEN;
	}

	public String getMA_PHUONGXA() {
		return MA_PHUONGXA;
	}

	public void setMA_PHUONGXA(String mA_PHUONGXA) {
		MA_PHUONGXA = mA_PHUONGXA;
	}

	public String getVE_TINH() {
		return VE_TINH;
	}

	public void setVE_TINH(String vE_TINH) {
		VE_TINH = vE_TINH;
	}

	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		String retVal = "";
		switch (arg0) {
		case 0:
			retVal = MA_TC;
			break;

		case 1:
			retVal = HDTB_ID;
			break;
		case 2:
			retVal = MA_THUE_BAO;
			break;
		case 3:
			retVal = TEN_THUE_BAO;
			break;
		case 4:
			retVal = DIA_CHI_THUE_BAO;
			break;
		case 5:
			retVal = NGAY_CAI_DAT;
			break;
		case 6:
			retVal = NGUOI_CAI_DAT;
			break;
		case 7:
			retVal = LOAIHINH_THUE_BAO;
			break;
		case 8:
			retVal = ID_THUEBAO;
			break;
		case 9:
			retVal = MA_DICHVU;
			break;
		case 10:
			retVal = MA_QUANHUYEN;
			break;
		case 11:
			retVal = MA_PHUONGXA;
			break;
		case 12:
			retVal = VE_TINH;
			break;

		}

		return retVal;
	}

	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 13;
	}

	@SuppressWarnings("rawtypes")
	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo arg2) {
		// TODO Auto-generated method stub
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

	public void setProperty(int arg0, Object arg1) {
		// TODO Auto-generated method stub
		switch (arg0) {
		case 0:
			MA_TC = arg1.toString();
			break;
		case 1:
			HDTB_ID = arg1.toString();
			break;
		case 2:
			MA_THUE_BAO = arg1.toString();
			break;
		case 3:
			TEN_THUE_BAO = arg1.toString();
			break;
		case 4:
			DIA_CHI_THUE_BAO = arg1.toString();
			break;
		case 5:
			NGAY_CAI_DAT = arg1.toString();
			break;
		case 6:
			NGUOI_CAI_DAT = arg1.toString();
			break;
		case 7:
			LOAIHINH_THUE_BAO = arg1.toString();
			break;
		case 8:
			ID_THUEBAO = arg1.toString();
			break;
		case 9:
			MA_DICHVU = arg1.toString();
			break;
		case 10:
			MA_QUANHUYEN = arg1.toString();
			break;
		case 11:
			MA_PHUONGXA = arg1.toString();
			break;
		case 12:
			VE_TINH = arg1.toString();
			break;

		default:
			break;
		}
	}

}
