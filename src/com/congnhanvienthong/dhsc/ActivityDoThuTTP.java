package com.congnhanvienthong.dhsc;

import org.ksoap2.serialization.SoapObject;

import com.congnhanvienthong.ActivityBaseToDisplay;
import com.congnhanvienthong.R;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import congnhanvienthong.entity.dhsc.ThamSoDSLAM;
import congnhanvienthong.entity.dhsc.ThamSoGPon;
import congnhanvienthong.entity.dhsc.ThongSoBras;
import control.Util;
import webservice.WebProtocol;
import webservice.dhsc.DoThongSoBrasTask;
import webservice.dhsc.DoThongSoCongMenTask;
import webservice.dhsc.DoThongSoGponTask;
import webservice.dhsc.DoThuTask;

public class ActivityDoThuTTP extends ActivityBaseToDisplay {
	Button bttOk;
	EditText txtMaDV;
	Spinner spnLoaiDichVu;
	int loaiDV = 0;
	DoThuTask doThuTask;
	TextView txtResult;
	DoThongSoGponTask doGponTask;
	Spinner spnLoaiDo;
	LinearLayout viewLoaiDichVu;
	DoThongSoCongMenTask doThongSoCongMen;
	DoThongSoBrasTask doThongSoBras;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setBodyLayout(R.layout.activity_dothu_ttp);
		setFootLayout(R.layout.foot_tracuu);
		setHeader("Đo thử");
		viewLoaiDichVu = (LinearLayout) body.findViewById(R.id.viewLoaiDichVu);
		spnLoaiDo = (Spinner) body.findViewById(R.id.spnKieuDo);
		spnLoaiDo.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				if (position == 0)
					viewLoaiDichVu.setVisibility(View.VISIBLE);
				else {
					viewLoaiDichVu.setVisibility(View.GONE);
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		txtMaDV = (EditText) body.findViewById(R.id.txt_ma_dv);
		txtResult = (TextView) body.findViewById(R.id.ketqua);
		spnLoaiDichVu = (Spinner) body.findViewById(R.id.spn_loai_dichvu);
		bttOk = (Button) foot.findViewById(R.id.bttOK);
		context = ActivityDoThuTTP.this;
		doGponTask = new DoThongSoGponTask();
		spnLoaiDichVu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					loaiDV = 8;
					break;
				case 1:
					loaiDV = 6;
					break;
				case 2:
					loaiDV = 7;
					break;

				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		bttOk.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		txtResult.setText("");
		switch (v.getId()) {
		case R.id.bttOK:
			String maDV = txtMaDV.getText().toString();
			if (maDV.trim().length() == 0) {
				Util.showAlert(context, "Kiểm tra lại mã dịch vụ!");
				return;
			}
			switch (spnLoaiDo.getSelectedItemPosition()) {
			case 0:

				doThuTask = new DoThuTask();
				doThuTask.addParam("p_id_loaidichvu", loaiDV);
				doThuTask.addParam("p_ma_dichvu", maDV);
				doThuTask.addParam("id_ttpho", "1");
				onExecuteToServer(true, "Đo thông số DSLAM ?", doThuTask);

				break;
			case 1:
				doThongSoCongMen = new DoThongSoCongMenTask();
				doThongSoCongMen.addParam("ma_dichvu", maDV);
				doThongSoCongMen.addParam("ma_tinh", Util.ttp.getMa_Ttp());
				onExecuteToServer(true, "Đo thông số cổng MEN", doThongSoCongMen);
				break;
			case 2:
				doThongSoBras = new DoThongSoBrasTask();
				doThongSoBras.addParam("ma_dichvu", maDV);
				doThongSoBras.addParam("ma_tinh", Util.ttp.getMa_Ttp());
				onExecuteToServer(true, "Đo thông số Bras", doThongSoBras);
				break;
			case 3:
				doGponTask.addParam("ma_dichvu", maDV);
				doGponTask.addParam("ma_tinh", Util.ttp.getMa_Ttp());
				onExecuteToServer(true, "Đo thông số GPon", doGponTask);
				break;

			default:
				break;
			}

			break;

		default:
			break;
		}

	}

	@SuppressLint("DefaultLocale")
	@Override
	public void onsucces(WebProtocol task) {
		// TODO Auto-generated method stub
		super.onsucces(task);
		try {
			if (task.equals(doThuTask)) {
				SoapObject arrString = (SoapObject) doThuTask.result;
				String result = arrString.getPrimitivePropertyAsString("isError");
				String mess = arrString.getPrimitivePropertyAsString("Message");
				if (result.toLowerCase().equals("true")) {
					Util.showAlert(context, mess);
					return;
				}
				SoapObject dslam = (SoapObject) arrString.getProperty("Result");

				ThamSoDSLAM thamSoDSLAM = new ThamSoDSLAM();

				Util.GetObjectFromSoapObject(thamSoDSLAM, dslam);
				System.out.println(thamSoDSLAM);
				Util.setTextFromObject(txtResult, thamSoDSLAM);
			}
			if (task.equals(doGponTask)) {
				ThamSoGPon ketqua = (ThamSoGPon) doGponTask.getResult();
				Util.setTextFromObjectAllField(txtResult, ketqua);
			}
			if (task.equals(doThongSoCongMen)) {
				String ketqua = (String) doThongSoCongMen.getResult();
				txtResult.setText(ketqua);
			}
			if (task.equals(doThongSoBras)) {
				ThongSoBras ketqua = (ThongSoBras) doThongSoBras.getResult();
				Util.setTextFromObject(txtResult, ketqua);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
