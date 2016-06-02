package api;

import com.google.gson.Gson;

import congnhanvienthong.entity.ListApiResult;
import congnhanvienthong.entity.nhatram.LoaiNhaTram;

public class GetLoaiNhaTramApi extends ApiTask {
	ListApiResult<LoaiNhaTram> LoaiNhaTram;

	public GetLoaiNhaTramApi() {
		// TODO Auto-generated constructor stub
		super();
		this.url = "http://123.16.191.37/qlntapi/api/Mobi/GetLoaiNhaTramApi";
		this.addParam("userName", "PM1");
		this.addParam("passWord", "@PM1_VNPT@");
		this.addParam("maTinhThanh", "HNI");
	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		LoaiNhaTram = new ListApiResult<LoaiNhaTram>();
		Gson gson = new Gson();

		return getList(LoaiNhaTram.class);
	}

}
