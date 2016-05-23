package control;

//import crowx.android.AndCellTrack.clfDB.CLFRecord;
import android.os.Parcel;
import android.os.Parcelable;

public class CellInfo implements Parcelable {
	public final static int CHANGED_RXL = 1;
	public final static int CHANGED_CID = 2;
	public final static int CHANGED_LAC = 4;
	public final static int CHANGED_RNC = 8;
	public final static int CHANGED_MCCMNC = 16;
	public final static int CHANGED_NET_NAME = 32;
	public final static int CHANGED_CBS = 64;
	
	public final static int CHANGED_CLF_RECORD = 128;
	
	private int changedFlag = 0;
	
	protected int rxlAsu = -1;	
	protected int cid = 0;	
	protected int lac = 0;
	protected int rnc = 0;
	protected int mccmnc = 0;
	protected String netName = "";	
	protected String cbs = "?";
	
	//protected CLFRecord clfRecord = null;
	
	public String netCountryIso = "";
	public int netState = android.telephony.ServiceState.STATE_OUT_OF_SERVICE;
	public int netType = android.telephony.TelephonyManager.NETWORK_TYPE_UNKNOWN;
	
	public CellInfo() {
    }
	
	@Override
	public final String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LCID=").append(rnc).append(" ").append(cid)
				.append(" LAC=").append(lac).append(" MCCMNC=").append(mccmnc);
		return builder.toString();
	}
	
	public static final Parcelable.Creator<CellInfo> CREATOR = new Parcelable.Creator<CellInfo>() {
        public CellInfo createFromParcel(Parcel in) {
            return new CellInfo(in);
        }

        public CellInfo[] newArray(int size) {
            return new CellInfo[size];
        }
    };
	
    private CellInfo(Parcel in) {
    	readFromParcel(in);
    }
    
	public final int describeContents() {
		return 0;
	}
	
	public final void writeToParcel(Parcel dest, int flags) {
		android.util.Log.w("AndCellTrack","CellInfo.writeToParcel");
		dest.writeInt(changedFlag);
		dest.writeInt(cid);
		dest.writeInt(rnc);
		dest.writeInt(lac);
		dest.writeInt(mccmnc);
		dest.writeString(netName);
		//dest.writeString(netCountryIso);
		dest.writeInt(netState);
		dest.writeInt(netType);
		dest.writeInt(rxlAsu);
		dest.writeString(cbs);
		//dest.writeParcelable(clfRecord, flags);		
	}
	
	public final void readFromParcel(Parcel in) {
		android.util.Log.w("AndCellTrack","CellInfo.readToParcel");
		changedFlag = in.readInt();
		cid = in.readInt();
		rnc = in.readInt();
		lac = in.readInt();
		mccmnc = in.readInt();
		netName = in.readString();
		//netCountryIso = in.readString();
		netState = in.readInt();
		netType = in.readInt();
		rxlAsu = in.readInt();
		cbs = in.readString();
		//clfRecord = in.readParcelable(null);		
	}

	public final int getChangedFlag() {
		return changedFlag;
	}
	
	public final void cleanChangedFlag() {
		changedFlag = 0;
	}
	
	public final int getCID() {
		return cid;
	}

	public final void setLCID(int lcid) {
		int cid = lcid;
		int rnc = cid >> 16;
		cid = cid & 0xffff;
		setCID(cid);
		setRNC(rnc);
	}
	
	public final void setCID(int cid) {
		if(this.cid != cid) changedFlag |= CHANGED_CID;
		this.cid = cid;
	}

	public final int getRNC() {
		return rnc;
	}

	public final void setRNC(int rnc) {
		if(this.rnc != rnc) changedFlag |= CHANGED_RNC;
		this.rnc = rnc;
	}

	public final int getLAC() {
		return lac;
	}

	public final void setLAC(int lac) {
		if(this.lac != lac) changedFlag |= CHANGED_LAC;
		this.lac = lac;
	}

	public final int getMCCMNC() {
		return mccmnc;
	}

	public final void setMCCMNC(int mccmnc) {
		if(this.mccmnc != mccmnc) changedFlag |= CHANGED_MCCMNC;
		this.mccmnc = mccmnc;
	}

	public final String getNetName() {
		return netName;
	}

	public final void setNetName(String netName) {
		if(!this.netName.equals(netName)) changedFlag |= CHANGED_NET_NAME;
		this.netName = netName;
	}

	public final int getRXL() {
		return -113 + 2*rxlAsu;
	}

	public final void setRXL(int rxl) {
		int rxlAsu = (rxl+113)/2;
		if(this.rxlAsu != rxlAsu) changedFlag |= CHANGED_RXL;
		this.rxlAsu = rxlAsu;
	}
	
	public final int getRXLAsu() {
		return rxlAsu;
	}
	
	public final void setRXLAsu(int rxlAsu) {
		if(this.rxlAsu != rxlAsu) changedFlag |= CHANGED_RXL;
		this.rxlAsu = rxlAsu;
	}
	
	public final String getCBS() {
		return cbs;
	}

	public final void setCBS(String cbs) {
		if(!this.cbs.equals(cbs)) changedFlag |= CHANGED_CBS;
		this.cbs = cbs;
	}

	/*public CLFRecord getClfRecord() {
		return clfRecord;
	}

	public void setClfRecord(CLFRecord clfRecord) {
		//if(this.clfRecord == null) changedFlag |= CHANGED_CLF_RECORD;
		//else if(!this.clfRecord.equals(clfRecord)) changedFlag |= CHANGED_CLF_RECORD;
		this.clfRecord = clfRecord;
	}*/	
}