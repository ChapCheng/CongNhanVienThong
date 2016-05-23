package com.congnhanvienthong.qltt;

import java.math.BigDecimal;
import java.util.Date;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import webservice.qltt.PaymentsWebServices;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.congnhanvienthong.ActivityBaseToDisplay;
import com.congnhanvienthong.R;
import com.google.gson.Gson;

import congnhanvienthong.entity.qltt.CustomerInfomation;
import congnhanvienthong.entity.qltt.DebtInfomation;
import congnhanvienthong.entity.qltt.ResultPayment;
import control.Util;

public class ActivityThucHienThanhToan extends ActivityBaseToDisplay implements
		AsyncResponse {

	private Button btnThanhToan;
	private TextView tvTenThanhToan;
	private TextView tvDiaChi;
	private TextView tvMaThanhToan;
	private TextView tvBillingCode;
	private TextView tvTrangThai;

	private TableLayout tblThongTinNo;

	private PayBillsAsync payBillsAsync;

	private TextView tvTienThanhToan;
	private EditText edtTienThanhToan;

	CustomerInfomation customerInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHeader("Thanh toán");
		setBodyLayout(R.layout.activity_thanhtoan_gach_no);

		initialize();
	}

	private void initialize() {

		Bundle bundle = getIntent().getExtras();

		if (bundle != null) {

			String customerJson = bundle.getString("customer_info");

			customerInfo = new Gson().fromJson(customerJson,
					CustomerInfomation.class);
		}

		tblThongTinNo = (TableLayout) this.findViewById(R.id.tblThongTinNo);

		btnThanhToan = (Button) this.findViewById(R.id.btnGachNo);

		tvTenThanhToan = (TextView) this.findViewById(R.id.tvTenThanhToan);

		tvDiaChi = (TextView) this.findViewById(R.id.tvDiaChi);

		tvMaThanhToan = (TextView) this.findViewById(R.id.tvMaThanhToan);
		tvBillingCode = (TextView) this.findViewById(R.id.tvBillingCode);

		tvTrangThai = (TextView) this.findViewById(R.id.tvTrangThai);

		tvTienThanhToan = (TextView) this.findViewById(R.id.tvTienThanhToan);
		edtTienThanhToan = (EditText) this.findViewById(R.id.edtTienThanhToan);
		//
		// Set value from Customer Info
		//
		tvTenThanhToan.setText("Tên khách hàng : "
				+ customerInfo.getTenThanhToan());

		tvDiaChi.setText("Địa chỉ : " + customerInfo.getDiaChi());

		tvMaThanhToan.setText("Mã thanh toán : ");
		tvBillingCode.setText(customerInfo.getMaThanhToan());

		final ArrayList<DebtInfomation> listOfDebt = customerInfo
				.getThongTinNo();

		tvTrangThai
				.setText("Trạng thái : "
						+ (listOfDebt.size() > 0 ? "Chưa thanh toán"
								: "Đã thanh toán"));

		/*** Hiển thị thông tin nợ ***/

		//
		// Khai báo biến lấy tổng tiền
		BigDecimal totalBill = new BigDecimal(0);

		DecimalFormat formatter = (DecimalFormat) NumberFormat
				.getInstance(Locale.GERMAN);

		if (listOfDebt.size() > 0) {

			//
			// STT
			//
			TableRow tbrow0 = new TableRow(this);

			TextView tv0 = new TextView(this);
			tv0.setText("Stt");
			tv0.setWidth(80);
			tv0.setTextColor(Color.BLACK);
			tv0.setGravity(Gravity.CENTER_HORIZONTAL);
			//
			// Kỳ cước
			//
			tbrow0.addView(tv0);
			TextView tv1 = new TextView(this);
			tv1.setText("Kỳ cước");
			tv1.setWidth(200);
			tv1.setTextColor(Color.BLACK);
			tv1.setPadding(0, 5, 0, 5);
			tv1.setGravity(Gravity.CENTER_HORIZONTAL);
			// tv1.setBackgroundColor(Color.YELLOW);
			tbrow0.addView(tv1);
			//
			// Số tiền
			//
			TextView tv3 = new TextView(this);
			tv3.setText("Số tiền");
			tv3.setTextColor(Color.BLACK);
			tv3.setWidth(300);
			tv3.setPadding(0, 5, 0, 5);
			tv3.setGravity(Gravity.RIGHT);
			// tv3.setBackgroundColor(Color.CYAN);
			tbrow0.addView(tv3);

			tblThongTinNo.addView(tbrow0);

			//
			/* Hiển thị thông tin chi tiết */

			for (int i = 0; i < listOfDebt.size(); i++) {
				TableRow row = new TableRow(this);

				TextView tvSTT = new TextView(this);
				tvSTT.setText((i + 1) + "");
				tvSTT.setWidth(50);
				tvSTT.setTextColor(Color.BLACK);
				tvSTT.setGravity(Gravity.CENTER_HORIZONTAL);
				row.addView(tvSTT);

				TextView tvKyCuoc = new TextView(this);
				tvKyCuoc.setText(listOfDebt.get(i).getKyCuoc());
				tvKyCuoc.setWidth(200);
				tvKyCuoc.setPadding(0, 5, 0, 5);
				tvKyCuoc.setGravity(Gravity.CENTER_HORIZONTAL);
				tvKyCuoc.setTextColor(Color.BLACK);
				row.addView(tvKyCuoc);

				TextView tvSoTien = new TextView(this);

				BigDecimal amountOfMoney = new BigDecimal(listOfDebt.get(i)
						.getSoTien());

				tvSoTien.setText(formatter.format(amountOfMoney).toString());
				tvSoTien.setWidth(300);
				tvSoTien.setPadding(0, 5, 0, 5);
				tvSoTien.setTextColor(Color.BLACK);
				tvSoTien.setGravity(Gravity.RIGHT);
				row.addView(tvSoTien);

				tblThongTinNo.addView(row);
				//
				// Tính tổng tiền
				//
				totalBill = totalBill.add(amountOfMoney);

			}
			//
			// Thêm dòng tổng tiền
			//
			TableRow totalRow = new TableRow(this);
			//
			// Tổng cộng
			//
			TextView tvTong = new TextView(this);
			tvTong.setText("Tổng");
			tvTong.setWidth(80);
			;
			totalRow.addView(tvTong);
			//
			// Kỳ cước
			//
			TextView tvKyCuoc = new TextView(this);
			tvKyCuoc.setText("");
			tvKyCuoc.setWidth(150);
			totalRow.addView(tvKyCuoc);
			//
			// Tổngtiền
			//
			/*
			 * TextView tvTongTien = new TextView(activity);
			 * tvTongTien.setText(totalBill + ""); tvTongTien.setWidth(300);
			 * tvTongTien.setGravity(Gravity.RIGHT);
			 * totalRow.addView(tvTongTien); tblThongTinNo.addView(totalRow);
			 */
			//
			// Điền tổng tiền vào text tiền thanh toán
			//
		}

		tvTienThanhToan.setVisibility(View.VISIBLE);

		edtTienThanhToan.setVisibility(View.VISIBLE);
		// edtTienThanhToan.setText(totalBill + "");

		edtTienThanhToan.setText(formatter.format(totalBill.longValue()));

		btnThanhToan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				GachNo();
			}
		});
		edtTienThanhToan.addTextChangedListener(new NumberTextWatcher(
				edtTienThanhToan));
	}

	@Override
	public void processFinish(Object output) {

		try {

			String jSon = output.toString();

			ResultPayment resultPayment;

			if (jSon.length() > 0) {

				resultPayment = new Gson().fromJson(jSon, ResultPayment.class);

			} else {

				/*
				Toast.makeText(getBaseContext(),
						"Không kết nối được đến Web Service",
						Toast.LENGTH_SHORT).show();
						*/
				Util.showAlert(context, "Không kết nối được đến WS thanh toán");
				
				return;
			}

			if (resultPayment.getErrorCode().equals("0")) {

				AlertDialog.Builder builder = new AlertDialog.Builder(
						ActivityThucHienThanhToan.this);
				
				builder.setTitle("Thông báo");
				builder.setMessage("Thanh toán thành công");
				builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
							
						onBackPressed();
					}
				});
				
				builder.setCancelable(false);
				builder.show();

			} else {

				/*
				Toast.makeText(getBaseContext(),
						resultPayment.getErrorMessage(), Toast.LENGTH_SHORT)
						.show();
				*/
				Util.showAlert(context, resultPayment.getErrorMessage());

			}

		} catch (Exception exc) {
			Log.w("Loi roi", exc);
		}

	}

	private void GachNo() {

		try {

			String billingCode = tvBillingCode.getText().toString();

			if (billingCode.isEmpty()) {
				/*
				Toast.makeText(getBaseContext(),
						"Không tìm thấy mã thanh toán", Toast.LENGTH_SHORT)
						.show();
						*/
				Util.showAlert(context, "Không tìm thấy mã thanh toán");
				return;

			}

			String stringTotal = edtTienThanhToan.getText().toString();

			DecimalFormat formatter = (DecimalFormat) NumberFormat
					.getInstance(Locale.GERMAN);
			stringTotal = stringTotal.replace(String.valueOf(formatter
					.getDecimalFormatSymbols().getGroupingSeparator()), "");
			//
			// Kiểm tra tiền
			//
			BigDecimal amountOfMoney = new BigDecimal(stringTotal);

			if (amountOfMoney.compareTo(new BigDecimal("99999999999")) == 1) {
				/*
				Toast.makeText(getBaseContext(),
						"Tiền thanh toán không được lớn hơn 99999999999",
						Toast.LENGTH_SHORT).show();
						*/
				Util.showAlert(context, "Tiền thanh toán không được lớn hơn 99999999999");
				return;
			}

			payBillsAsync = new PayBillsAsync(this);
			payBillsAsync.delegate = this;
			//
			// Get Authentication String
			//
			SharedPreferences sharedPref = getSharedPreferences(
					Constants.FILE_AUTHENTICATION, Context.MODE_PRIVATE);

			String authenticationString = sharedPref.getString(
					Constants.AUTHENTICATION_KEY, "");

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

			Date date = new Date();

			String transactionDate = dateFormat.format(date).toString();

			String authenticationMd5 = Utilities.md5(billingCode
					+ transactionDate + authenticationString);

			payBillsAsync.execute(billingCode, transactionDate, stringTotal,
					Util.userName, authenticationMd5);

		} catch (Exception e) {
			Log.w("Loi roi ", e.toString());
		}
	}

	class PayBillsAsync extends AsyncTask<String, Void, Object> {

		AsyncResponse delegate = null;

		private ProgressDialog progressDialog;
		Context context = null;

		public PayBillsAsync(Context context) {

			this.context = context;
		}

		@Override
		protected void onPreExecute() {

			progressDialog = ProgressDialog.show(context, "",
					"Đang thực hiện thanh toán ...");

		}

		@Override
		protected Object doInBackground(String... params) {

			String customerInfo = "";

			try {

				String billingCode = params[0];
				String transactionDate = params[1];
				String amounts = params[2];
				String userName = params[3];
				String authenticationString = params[4];

				PaymentsWebServices paymentsWS = new PaymentsWebServices();

				customerInfo = paymentsWS.payBills(billingCode,
						transactionDate, amounts, Constants.GATE_WAY,
						Constants.TRANSACTION_ID, userName,
						Constants.PAYMENTS_TYPE, Constants.NOTE,
						authenticationString);
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

}
