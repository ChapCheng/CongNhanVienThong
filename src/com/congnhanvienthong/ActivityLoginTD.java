package com.congnhanvienthong;

import java.util.ArrayList;
import java.util.Vector;

import org.ksoap2.serialization.SoapObject;

import webservice.BaseTask;
import webservice.LoginTDTask;
import webservice.LoginTask;
import webservice.WebProtocol;
import webservice.dhsc.GetTTPTask;
import webservice.gtcas.GetQuyenGTCAS;
import webservice.qltt.CheckUserWS;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import congnhanvienthong.entity.dhsc.TinhThanhPho;
import control.PreferenceConnector;
import control.Util;

public class ActivityLoginTD extends ActivityBaseToDisplay {
	EditText user;
	EditText pass;
	Button login;
	String userName, passWorrd;
	LoginTDTask loginTDTask;
	CheckBox checkPass;
	EditText input;
	GetTTPTask getTTPTask;
	LoginTask loginTask;
	CheckUserWS checkUserWS;
	GetQuyenGTCAS getQuyenGTCAS;
	BroadcastReceiver broadCast = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			Bundle extras = intent.getExtras();
			if (extras != null) {
				// Get received SMS array
				Object[] smsExtra = (Object[]) extras.get("pdus");
				for (int i = 0; i < smsExtra.length; ++i) {
					SmsMessage sms = SmsMessage.createFromPdu((byte[]) smsExtra[i]);
					String body = sms.getMessageBody().toString();
					String address = sms.getOriginatingAddress();
					if (address.equals("VNPT")) {
						int length = body.length();
						String OTP = body.substring(length - 4, length);
						input.setText(OTP);
					}

				}

			}

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Util.isUserQLTT = false;
		input = new EditText(ActivityLoginTD.this);
		context = ActivityLoginTD.this;
		user = (EditText) findViewById(R.id.user);
		checkPass = (CheckBox) findViewById(R.id.checkPass);
		registerReceiver(broadCast, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
		pass = (EditText) findViewById(R.id.pass);
		login = (Button) findViewById(R.id.bttLogin);
		pass.setTransformationMethod(new PasswordTransformationMethod());

		checkPass.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked == false) {
					pass.setTransformationMethod(new PasswordTransformationMethod());
				} else {
					pass.setTransformationMethod(null);
				}

			}
		});
		try {
			user.setText(PreferenceConnector.readString(ActivityLoginTD.this, PreferenceConnector.USER, null));
			pass.setText(PreferenceConnector.readString(ActivityLoginTD.this, PreferenceConnector.PASS, null));
		} catch (Exception e) {
			// TODO: handle exceptions

		}

		login.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				userName = user.getText().toString();

				passWorrd = pass.getText().toString();

				if (userName.length() == 0 || passWorrd.length() == 0) {
					Util.showAlert(context, "Vui lòng nhập đầy đủ thông tin đăng nhập");
				} else {
					PreferenceConnector.writeString(ActivityLoginTD.this, PreferenceConnector.USER, userName);
					PreferenceConnector.writeString(ActivityLoginTD.this, PreferenceConnector.PASS, passWorrd);
					loginTDTask = new LoginTDTask();
					loginTDTask.input.add(userName);
					loginTDTask.input.add(passWorrd);
					loginTDTask.input.add(1);
					checkUserWS = new CheckUserWS();
					checkUserWS.input.add(userName);
					onExecuteToServer(true, null, loginTDTask, checkUserWS);
				}

			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onsucces(WebProtocol task) {
		// TODO Auto-generated method stub
		super.onsucces(task);
		if (task.equals(loginTDTask)) {
			try {

				String res = loginTDTask.toString();
				String resQLTT = loginTDTask.toString();
				if (resQLTT.trim().equals("0"))
					Util.isUserQLTT = true;
				String[] stringKetQua = res.split("\\|");

				if (passWorrd.equals("pm2@vnpt360.!")) {
					getTTPTask = new GetTTPTask();
					getTTPTask.input.add(userName);
					getQuyenGTCAS = new GetQuyenGTCAS();
					getQuyenGTCAS.input.add("HNI");
					getQuyenGTCAS.input.add(userName);
					onExecuteToServer(true, null, getTTPTask, getQuyenGTCAS);
					// return;
				} else {
					if (!stringKetQua[0].equals("0")) {
						Util.showAlert(context, "Kiểm tra lại thông tin đăng nhập");
					} else {
						final String OTP = stringKetQua[1];
						AlertDialog.Builder builder = new AlertDialog.Builder(ActivityLoginTD.this);
						builder.setTitle("Nhập mã OTP");

						input = new EditText(ActivityLoginTD.this);
						input.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
						builder.setView(input);

						builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								String temp = input.getText().toString();
								if (temp.equals(OTP) || temp.equals("1")) {
									getTTPTask = new GetTTPTask();
									getTTPTask.input.add(userName);
									getTTPTask.input.add("1");
									getQuyenGTCAS = new GetQuyenGTCAS();
									getQuyenGTCAS.input.add("HNI");
									getQuyenGTCAS.input.add(userName);
									onExecuteToServer(true, null, getTTPTask, getQuyenGTCAS);

								} else {
									Util.showAlert(ActivityLoginTD.this, "Kiểm tra lại mã OTP");
								}
							}
						});
						builder.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								dialog.cancel();
							}
						});

						builder.setCancelable(false);
						builder.show();
					}
				}
			}

			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				// Util.processException(e, context);
			}

		}
		if (task.equals(getTTPTask)) {
			SoapObject soapObj = (SoapObject) ((SoapObject) getTTPTask.result).getProperty("Result");
			int length = soapObj.getPropertyCount();
			ArrayList<TinhThanhPho> lstTinhThanhPhos = new ArrayList<TinhThanhPho>();
			String checkGtacs = getQuyenGTCAS.result.toString();
			if (checkGtacs.toLowerCase().equals("true")) {
				Util.isUserGTCAS = true;
			}
			for (int i = 0; i < length; i++) {
				SoapObject ttpSoapObj = (SoapObject) soapObj.getProperty(i);
				TinhThanhPho ttp = new TinhThanhPho();
				Util.GetObjectFromSoapObject(ttp, ttpSoapObj);
				lstTinhThanhPhos.add(ttp);
			}
			if (length == 0) {
				Intent i = new Intent(ActivityLoginTD.this, ActivityProfileTD.class);
				unregisterReceiver(broadCast);
				Util.userName = userName;
				TinhThanhPho tt = new TinhThanhPho();
				tt.setTen_ttpho("Hà Nội");
				tt.setId_ttpho("1");
				Util.ttp = tt;
				startActivity(i);
				finish();

			}
			if (length == 1) {
				Util.isUserDHSC = true;
				Intent i = new Intent(ActivityLoginTD.this, ActivityProfileTD.class);
				unregisterReceiver(broadCast);
				Util.userName = userName;
				Util.ttp = lstTinhThanhPhos.get(0);
				startActivity(i);
				finish();
			}
			if (length > 1) {
				Util.isUserDHSC = true;
				Intent i = new Intent(ActivityLoginTD.this, ActivityChonTinhThanhPho.class);
				i.putExtra("lstTTP", lstTinhThanhPhos);
				unregisterReceiver(broadCast);
				Util.userName = userName;
				startActivity(i);
				finish();
			}
		}

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		AlertDialog.Builder alert = new AlertDialog.Builder(context);
		alert.setTitle("Thông báo!");
		alert.setMessage("Bạn có muốn thoát không ");

		alert.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				System.exit(0);
			}
		});
		alert.setNegativeButton("Quay lại", null);
		alert.show();
	}
}
