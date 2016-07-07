package com.congnhanvienthong.qltt;

import com.congnhanvienthong.ActivityBaseToDisplay;
import com.congnhanvienthong.R;
import com.google.gson.Gson;
import com.google.zxing.client.android.CaptureActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import congnhanvienthong.entity.qltt.CustomerInfomation;
import control.PreferenceConnector;
import control.Util;
import webservice.qltt.PaymentsWebServices;

@SuppressLint("NewApi")
public class ActivityTimKiemThanhToan extends ActivityBaseToDisplay implements AsyncResponse {

	private Button btnSearch, bttBarcode;
	private EditText edtThongTinTim;

	private GetDebtInfomationAsync debtInfoAsync;
	boolean isReadCode = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHeader("Thanh toán");
		setBodyLayout(R.layout.activity_thanhtoan_tim_kiem);
		PreferenceConnector.writeString(ActivityTimKiemThanhToan.this, PreferenceConnector.PASS, "");
		bttBarcode = (Button) body.findViewById(R.id.btn_barcode);
		bttBarcode.setOnClickListener(this);
		edtThongTinTim = (EditText) body.findViewById(R.id.txtdata);

		btnSearch = (Button) this.findViewById(R.id.btnSearch);
		initialize();

	}

	@Override
	public void onResume() {

		super.onResume();
		if (!isReadCode)
			edtThongTinTim.setText("");

	}

	private void initialize() {

		btnSearch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				searchBillingCode();
			}
		});

		// //
		// Kiểm tra chuỗi xác thực xem đã có chưa
		//
		SharedPreferences sharedPref = getSharedPreferences(Constants.FILE_AUTHENTICATION, Context.MODE_PRIVATE);

		String authenticationString = sharedPref.getString(Constants.AUTHENTICATION_KEY, "");

		if (authenticationString.trim().length() == 0) {

			Intent intent = new Intent(ActivityTimKiemThanhToan.this, ActivityAuthentication.class);
			this.startActivity(intent);

		}

	}

	private void searchBillingCode() {
		try {

			final String infomationSearch = edtThongTinTim.getText().toString();

			debtInfoAsync = new GetDebtInfomationAsync(this);
			debtInfoAsync.delegate = this;

			if (infomationSearch.length() == 0) {

				/*
				 * Toast.makeText(getBaseContext(),
				 * "Chưa nhập thông tin tìm kiếm", Toast.LENGTH_SHORT) .show();
				 */
				// Util.showAlert(context, "Chưa nhập thông tin tìm kiếm");
				edtThongTinTim.setError("Chưa nhập thông tin tìm kiếm");

				return;
			} else if (infomationSearch.length() > 50) {
				/*
				 * Toast.makeText(getBaseContext(), "Mã thanh toán không hợp lệ"
				 * , Toast.LENGTH_SHORT).show();
				 */
				Util.showAlert(context, "Mã thanh toán không hợp lệ");
				edtThongTinTim.requestFocus();
				return;
			}
			SharedPreferences sharedPref = this.getSharedPreferences(Constants.FILE_AUTHENTICATION,
					Context.MODE_PRIVATE);

			String authenticationString = sharedPref.getString(Constants.AUTHENTICATION_KEY, "");

			if (!(authenticationString.trim().length() == 0)) {
				String authenticationMd5 = Utilities.md5(infomationSearch + authenticationString);

				String username = Util.userName;

				debtInfoAsync.execute(infomationSearch, username, authenticationMd5);
			} else {
				/*
				 * Toast.makeText(getBaseContext(),
				 * "Chưa có thông tin xác thực thanh toán",
				 * Toast.LENGTH_SHORT).show();
				 */
				Util.showAlert(context, "Chưa có thông tin xác thực thanh toán");
				return;
			}

		} catch (Exception exc) {

		}
	}

	public void processFinish(Object output) {

		try {

			String jSon = output.toString();

			CustomerInfomation[] customers = null;

			if (jSon.length() > 0) {

				customers = new Gson().fromJson(jSon, CustomerInfomation[].class);
			} else {
				/*
				 * Toast.makeText(this.getBaseContext(),
				 * "Không kết nối được đến Web Service",
				 * Toast.LENGTH_SHORT).show();
				 */
				Util.showAlert(context, "Không kết nối được đến WS thanh toán");
				return;
			}

			if (customers.length == 0) {

				/*
				 * Toast.makeText(this.getBaseContext(),
				 * "Không tìm thấy thông tin khách hàng",
				 * Toast.LENGTH_SHORT).show();
				 */
				Util.showAlert(context, "Không tìm thấy thông tin khách hàng");
				return;

			} else if (customers.length > 1) {

				/*
				 * Toast.makeText(this.getBaseContext(),
				 * "Tìm thấy nhiều hơn một mã thanh toán",
				 * Toast.LENGTH_SHORT).show();
				 */
				Util.showAlert(context, "Tìm thấy nhiều hơn một mã thanh toán");
				return;

			} else {

				CustomerInfomation customer = customers[0];

				if (!customer.getErrorCode().equals("0")) {
					/*
					 * Toast.makeText(this.getBaseContext(),
					 * customer.getErrorMessage(), Toast.LENGTH_SHORT) .show();
					 */
					Util.showAlert(context, customer.getErrorMessage());

					return;
				}

				else {

					/*
					 * GachNoFragment gachno = new GachNoFragment(customer);
					 * FragmentManager fragmentManager = getActivity()
					 * .getSupportFragmentManager();
					 * fragmentManager.beginTransaction()
					 * .replace(R.id.flContent, gachno)
					 * .addToBackStack("tag").commit();
					 */
					String sCustomer = new Gson().toJson(customer);

					Intent intent = new Intent(ActivityTimKiemThanhToan.this, ActivityThucHienThanhToan.class);

					Bundle bundle = new Bundle();

					bundle.putString("customer_info", sCustomer);

					intent.putExtras(bundle);
					isReadCode = false;

					startActivity(intent);

				}

			}
		} catch (Exception exc) {

			Log.w("Loi roi", exc);

		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_barcode:
			Intent intent = new Intent(getApplicationContext(), CaptureActivity.class);
			intent.setAction("com.google.zxing.client.android.SCAN");
			intent.putExtra("SAVE_HISTORY", false);
			startActivityForResult(intent, 0);
			break;

		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, intent);
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				String res = intent.getStringExtra("SCAN_RESULT");
				edtThongTinTim.setText(res);
				isReadCode = true;
			}
		}
	}

}

/*-----------Get Debt Infomation Async */

class GetDebtInfomationAsync extends AsyncTask<String, Void, Object> {

	AsyncResponse delegate = null;
	private ProgressDialog progressDialog;
	Context context = null;

	public GetDebtInfomationAsync(Context context) {

		this.context = context;
	}

	@Override
	protected void onPreExecute() {

		progressDialog = ProgressDialog.show(context, "", "Đang lấy thông tin khách hàng ...");

	}

	@Override
	protected Object doInBackground(String... params) {

		String customerInfo = "";
		try {
			String billingCode = params[0];
			String userName = params[1];
			String authenticationString = params[2];

			PaymentsWebServices paymentsWS = new PaymentsWebServices();

			customerInfo = paymentsWS.getDebtInfomation(billingCode, userName, authenticationString);
		} catch (Exception exc) {

			customerInfo = "";
		}

		return customerInfo;
	}

	@Override
	protected void onPostExecute(Object result) {

		try {

			progressDialog.dismiss();
			delegate.processFinish(result);

		} catch (Exception exc) {

			Log.w("Error function onPostExecute", exc);
		}
	}

}