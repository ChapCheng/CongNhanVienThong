package congnhanvienthong.entity.gtacs;

import java.io.Serializable;

public class ThongTinDonVi implements Serializable{
	long ID, COMPANY_ID, ID_TINHTHANH;
	String MANAGEMENT_CENTRE_CODE, MANAGEMENT_CENTRE_NAME,COMPANY_NAME;
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public long getCOMPANY_ID() {
		return COMPANY_ID;
	}
	public void setCOMPANY_ID(long cOMPANY_ID) {
		COMPANY_ID = cOMPANY_ID;
	}
	public long getID_TINHTHANH() {
		return ID_TINHTHANH;
	}
	public void setID_TINHTHANH(long iD_TINHTHANH) {
		ID_TINHTHANH = iD_TINHTHANH;
	}
	public String getMANAGEMENT_CENTRE_CODE() {
		return MANAGEMENT_CENTRE_CODE;
	}
	public void setMANAGEMENT_CENTRE_CODE(String mANAGEMENT_CENTRE_CODE) {
		MANAGEMENT_CENTRE_CODE = mANAGEMENT_CENTRE_CODE;
	}
	public String getMANAGEMENT_CENTRE_NAME() {
		return MANAGEMENT_CENTRE_NAME;
	}
	public void setMANAGEMENT_CENTRE_NAME(String mANAGEMENT_CENTRE_NAME) {
		MANAGEMENT_CENTRE_NAME = mANAGEMENT_CENTRE_NAME;
	}
	public String getCOMPANY_NAME() {
		return COMPANY_NAME;
	}
	public void setCOMPANY_NAME(String cOMPANY_NAME) {
		COMPANY_NAME = cOMPANY_NAME;
	}
	

}
