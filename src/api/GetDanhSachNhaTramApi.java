package api;

import control.Util;

public class GetDanhSachNhaTramApi extends ApiTask {
	public GetDanhSachNhaTramApi() {
		// TODO Auto-generated constructor stub
		super();
		this.url = "http://10.10.20.106/qlntapi/api/Mobi/GetDsNhaTram";
		this.addParam("userName", "PM1");
		this.addParam("passWord", "@PM1_VNPT@");
		this.addParam("maTinhThanh", "HNI");
		this.addParam("userNamePhanQuyen", Util.userName);
		this.addParam("pageSize", "10");

	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		return result;
	}

}
