package api;

import congnhanvienthong.entity.nhatram.NhaTram;
import control.Util;

public class GetDanhSachNhaTramApi extends ApiTask {
	public GetDanhSachNhaTramApi() {
		// TODO Auto-generated constructor stub
		super();
		this.url = "http://123.16.191.37/qlntapi/api/Mobi/GetDsNhaTram";
		this.addParam("userName", "PM1");
		this.addParam("passWord", "@PM1_VNPT@");
		this.addParam("maTinhThanh", "HNI");
		this.addParam("userNamePhanQuyen", Util.userName);
		this.addParam("pageIndex", "0");
		this.addParam("pageSize", "20");

	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		// return getList(NhaTram.class);
		return getListTotalSize(NhaTram.class, "DsNhaTram");
	}

}
