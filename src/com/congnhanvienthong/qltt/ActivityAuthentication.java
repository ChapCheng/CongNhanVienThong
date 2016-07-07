package com.congnhanvienthong.qltt;

import webservice.qltt.PaymentsWebServices;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.congnhanvienthong.ActivityBaseToDisplay;
import com.congnhanvienthong.ActivityLoginTD;
import com.congnhanvienthong.R;

import control.PreferenceConnector;
import control.Util;

@SuppressLint("NewApi")
public class ActivityAuthentication extends ActivityBaseToDisplay implements AsyncResponse {

	EditText edtAuthenticationString;
	BroadcastReceiver receiver = null;
	GetAuthenticationAsync authenticationAsync;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thanhtoan_authentication);
		initialize();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(receiver);
	}

	@Override
	public void processFinish(Object output) {

		String sResult = output.toString();

		try {

			if (!sResult.equals("0")) {

				Toast.makeText(getBaseContext(), "Lấy chuỗi xác thực không thành công", Toast.LENGTH_SHORT).show();
				finish();

			} else {

			}
		} catch (Exception exc) {

		}
	}

	private void initialize() {

		final IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");

		receiver = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {

				processReceive(context, intent);
			}

		};
		registerReceiver(receiver, filter);

		//
		// Hiển thị Dialog để nhận chuỗi xác thực
		//
		AlertDialog.Builder builder = new AlertDialog.Builder(ActivityAuthentication.this);

		builder.setTitle("Nhập chuỗi xác thực thanh toán");

		edtAuthenticationString = new EditText(ActivityAuthentication.this);

		builder.setView(edtAuthenticationString);

		builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				saveAuthenticationString();
				finish();
				Intent intent = new Intent(ActivityAuthentication.this, ActivityTimKiemThanhToan.class);
				startActivity(intent);

			}
		});

		builder.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				finish();
			}
		});

		builder.setCancelable(false);
		builder.show();

		String userName = Util.userName;
		String password = Util.pass;

		authenticationAsync = new GetAuthenticationAsync(this);
		authenticationAsync.execute(userName, password);

	}

	private void processReceive(Context context, Intent intent) {

		String sms_extras = "pdus";

		Bundle bundle = intent.getExtras();

		Object[] objArr = (Object[]) bundle.get(sms_extras);

		String sms = "";

		for (int i = 0; i < objArr.length; i++) {
			//
			SmsMessage smsMsg = SmsMessage.createFromPdu((byte[]) objArr[i]);

			String body = smsMsg.getMessageBody();
			String address = smsMsg.getOriginatingAddress();
			if (address.equals("VNPT")) {
				int length = body.length();

				String OTP = body.substring(length - 4, length);
				edtAuthenticationString.setText(OTP);
			}
		}
	}

	private void saveAuthenticationString() {

		String authenticationString = edtAuthenticationString.getText().toString();
		if (authenticationString.isEmpty()) {

			Toast.makeText(this, "Chưa nhập chuỗi xác thực", Toast.LENGTH_SHORT).show();
			return;
		}

		else {

			SharedPreferences sharedPref = getSharedPreferences(Constants.FILE_AUTHENTICATION, Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = sharedPref.edit();
			editor.clear();
			editor.putString(Constants.AUTHENTICATION_KEY, authenticationString);
			editor.commit();

		}

	}

}

class GetAuthenticationAsync extends AsyncTask<String, Void, String> {

	AsyncResponse delegate = null;

	private ProgressDialog progressDialog;
	Context context = null;

	public GetAuthenticationAsync(Context context) {

		this.context = context;
	}

	@Override
	protected void onPreExecute() {

		progressDialog = ProgressDialog.show(context, "", "Đang lấy chuỗi xác thực ...");

	}

	@Override
	protected String doInBackground(String... params) {

		String userName = params[0];
		String password = params[1];

		String sResult = "";

		try {
			PaymentsWebServices paymentsWS = new PaymentsWebServices();

			sResult = paymentsWS.getAuthenticationString(userName, password);

		} catch (Exception exc) {

			sResult = "";

		}
		return sResult;
	}

	@Override
	protected void onPostExecute(String result) {

		try {

			progressDialog.dismiss();
			delegate.processFinish(result);

		} catch (Exception exc) {

			Log.w("Error function onPostExecute", exc);
		}
	}
}
