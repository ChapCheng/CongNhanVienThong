package api;

import congnhanvienthong.entity.ListApiResult;
import congnhanvienthong.entity.nhatram.DonViQuanLy;

public class GetDonViQuanLyApi extends ApiTask {

	ListApiResult<DonViQuanLy> lstDonViQuanLy;

	public GetDonViQuanLyApi() {
		// TODO Auto-generated constructor stub
		super();
		this.url = "http://10.10.20.106/qlntapi/api/Mobi/GetDsDonViQuanLy";
		this.addParam("userName", "PM1");
		this.addParam("passWord", "@PM1_VNPT@");
		this.addParam("maTinhThanh", "HNI");

	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		return getList(DonViQuanLy.class);

	}
}
