package api;

import congnhanvienthong.entity.nhatram.NhaTram;

public class GetDSNhaTramByListAPI extends ApiTask {
	public GetDSNhaTramByListAPI() {
		// TODO Auto-generated constructor stub
		super();
		this.url = "http://123.16.191.37/qlntapi/api/Mobi/GetDsNhaTramByListId";
		this.addParam("userName", "PM1");
		this.addParam("passWord", "@PM1_VNPT@");
		this.addParam("maTinhThanh", "HNI");
	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		return getList(NhaTram.class);
	}

}
