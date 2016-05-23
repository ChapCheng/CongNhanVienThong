package com.congnhanvienthong.gtacs;

import java.text.SimpleDateFormat;
import java.util.Locale;

import org.ksoap2.serialization.SoapObject;

import com.congnhanvienthong.ActivityBaseToDisplay;
import com.congnhanvienthong.R;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import congnhanvienthong.entity.gtacs.ThongTinLyLichMay;
import control.Util;
import webservice.BaseTask;
import webservice.WebProtocol;
import webservice.gtcas.LyLichMayTask;

public class ActivityLyLichMay extends ActivityBaseToDisplay {

	TextView txtResult, txtMaTraCuu;
	Button bttTraCuu;
	LyLichMayTask doDSLAMTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setBodyLayout(R.layout.activity_ly_lich_may);
		setFootLayout(R.layout.foot_tracuu);
		setHeader("Tra lý lịch máy");
		bttTraCuu = (Button) foot.findViewById(R.id.bttOK);
		bttTraCuu.setOnClickListener(this);
		txtResult = (TextView) body.findViewById(R.id.ketqua);
		txtMaTraCuu = (TextView) body.findViewById(R.id.inputData);

	}

	@Override
	public void onsucces(WebProtocol task) {
		// TODO Auto-generated method stub
		super.onsucces(task);
		try {
			SoapObject arrString = (SoapObject) doDSLAMTask.result;
			String result = arrString.getPrimitivePropertyAsString("isError");
			String mess = arrString.getPrimitivePropertyAsString("Message");
			if (result.toLowerCase().equals("true")) {
				Util.showAlert(context, mess);
				return;
			}
			SoapObject lylichmay = (SoapObject) arrString.getProperty("Result");

			ThongTinLyLichMay thongTinLyLichMay = new ThongTinLyLichMay();

			Util.GetObjectFromSoapObject(thongTinLyLichMay, lylichmay);
			System.out.println(thongTinLyLichMay);
			Util.setTextFromObject(txtResult, thongTinLyLichMay);

		} catch (Exception e) {
			// TODO: handle exception
			Util.processException(e, context);
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.bttOK:
			txtResult.setText("");
			doDSLAMTask = new LyLichMayTask();
			doDSLAMTask.input.add(txtMaTraCuu.getText().toString());
			doDSLAMTask.input.add(Util.ttp.getMa_Ttp());
			onExecuteToServer(true, null, doDSLAMTask);
			break;
		}
	}

}
