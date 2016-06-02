// package com.congnhanvienthong;
//
// import org.ksoap2.serialization.SoapObject;
// import org.ksoap2.serialization.SoapPrimitive;
//
// import webservice.BaseTask;
// import webservice.LoginTask;
// import webservice.WebProtocol;
// import android.app.AlertDialog;
// import android.content.BroadcastReceiver;
// import android.content.Context;
// import android.content.DialogInterface;
// import android.content.Intent;
// import android.content.IntentFilter;
// import android.os.Bundle;
// import android.telephony.SmsMessage;
// import android.text.InputType;
// import android.text.method.PasswordTransformationMethod;
// import android.view.View;
// import android.widget.Button;
// import android.widget.CheckBox;
// import android.widget.CompoundButton;
// import android.widget.CompoundButton.OnCheckedChangeListener;
// import android.widget.EditText;
// import control.PreferenceConnector;
// import control.Util;
//
// public class ActivityLogin extends ActivityBaseToDisplay {
// EditText user;
// EditText pass;
// Button login;
// String userName, passWorrd;
// LoginTask loginTask;
// CheckBox checkPass;
// EditText input;
// BroadcastReceiver broadCast = new BroadcastReceiver() {
//
// @Override
// public void onReceive(Context context, Intent intent) {
// // TODO Auto-generated method stub
// Bundle extras = intent.getExtras();
// if (extras != null) {
// // Get received SMS array
// Object[] smsExtra = (Object[]) extras.get("pdus");
// for (int i = 0; i < smsExtra.length; ++i) {
// SmsMessage sms = SmsMessage.createFromPdu((byte[]) smsExtra[i]);
// String body = sms.getMessageBody().toString();
// String address = sms.getOriginatingAddress();
// if (address.equals("VNPT")) {
// int length = body.length();
// String OTP = body.substring(length - 4, length);
// input.setText(OTP);
// }
// }
// }
// }
// };
//
// @Override
// protected void onCreate(Bundle savedInstanceState) {
// // TODO Auto-generated method stub
// super.onCreate(savedInstanceState);
// setContentView(R.layout.activity_login);
// input = new EditText(ActivityLogin.this);
// context = ActivityLogin.this;
// user = (EditText) findViewById(R.id.user);
// checkPass = (CheckBox) findViewById(R.id.checkPass);
// registerReceiver(broadCast, new
// IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
// pass = (EditText) findViewById(R.id.pass);
// login = (Button) findViewById(R.id.bttLogin);
// pass.setTransformationMethod(new PasswordTransformationMethod());
//
// checkPass.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//
// public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
// // TODO Auto-generated method stub
// if (isChecked == false) {
// pass.setTransformationMethod(new PasswordTransformationMethod());
// } else {
// pass.setTransformationMethod(null);
// }
//
// }
// });
// try {
// user.setText(PreferenceConnector.readString(ActivityLogin.this,
// PreferenceConnector.USER, null));
// pass.setText(PreferenceConnector.readString(ActivityLogin.this,
// PreferenceConnector.PASS, null));
// } catch (Exception e) {
// // TODO: handle exceptions
//
// }
//
// login.setOnClickListener(new View.OnClickListener() {
//
// @Override
// public void onClick(View v) {
// // TODO Auto-generated method stub
// userName = user.getText().toString();
//
// passWorrd = pass.getText().toString();
//
// if (userName.length() == 0 || passWorrd.length() == 0) {
// Util.showAlert(context, "Vui lòng nhập đầy đủ thông tin đăng nhập");
// } else {
// PreferenceConnector.writeString(ActivityLogin.this, PreferenceConnector.USER,
// userName);
// PreferenceConnector.writeString(ActivityLogin.this, PreferenceConnector.PASS,
// passWorrd);
// loginTask = new LoginTask();
// // para.add("strUsername");
// // para.add("strPassword");
// // loginTask.input.add(userName);
// // loginTask.input.add(passWorrd);
// loginTask.addParam("strUsername", userName);
// loginTask.addParam("strPassword", passWorrd);
//
// onExecuteToServer(true, null, loginTask);
// }
//
// }
// });
// }
//
// @Override
// public void onsucces(WebProtocol task) {
// super.onsucces(task);
// // TODO Auto-generated method stub
// if (task.equals(loginTask)) {
// try {
// SoapObject soapObject = (SoapObject) loginTask.result;
//
// SoapPrimitive messeage = (SoapPrimitive) soapObject.getProperty("Message");
// SoapPrimitive code = (SoapPrimitive) soapObject.getProperty("Code");
//
// if (!code.toString().equals("0")) {
// Util.showAlert(context, messeage.toString());
// }
//
// if (code.toString().equals("0")) {
// Util.userName = userName;
// Util.pass = passWorrd;
// SoapObject kqObject = (SoapObject) soapObject.getProperty("Data");
// SoapObject ob3 = (SoapObject) kqObject.getProperty("diffgram");
// SoapObject ob4 = (SoapObject) ob3.getProperty("NewDataSet");
// SoapObject THE_OTP = (SoapObject) ob4.getProperty("THE_OTP");
// final String OTP = ((SoapPrimitive)
// THE_OTP.getProperty("THE_OTP")).toString();
// // Đọc tin nhắn để kiểm tra có mã xác thực gửi về hay không
//
// AlertDialog.Builder builder = new AlertDialog.Builder(ActivityLogin.this);
// builder.setTitle("Nhập mã OTP");
//
// input = new EditText(ActivityLogin.this);
// input.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
// builder.setView(input);
// builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
// public void onClick(DialogInterface dialog, int which) {
// String temp = input.getText().toString();
// if (temp.equals(OTP)) {
// Intent i = new Intent(ActivityLogin.this, ActivityProfile.class);
// unregisterReceiver(broadCast);
// startActivity(i);
// finish();
//
// } else {
// Util.showAlert(ActivityLogin.this, "Kiểm tra lại mã OTP");
// }
// }
// });
// builder.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
// public void onClick(DialogInterface dialog, int which) {
// dialog.cancel();
// }
// });
//
// builder.setCancelable(false);
// builder.show();
//
// }
// }
//
// catch (Exception e) {
// // TODO: handle exception
// e.printStackTrace();
// Util.showAlert(context,
// "Có lỗi xảy ra khi kết nối đến hệ thống! Vui lòng thử lại hoặc báo với quản
// trị.");
// }
//
// }
// }
//
// @Override
// public void onBackPressed() {
// // TODO Auto-generated method stub
// AlertDialog.Builder alert = new AlertDialog.Builder(context);
// alert.setTitle("Thông báo!");
// alert.setMessage("Bạn có muốn thoát không ");
//
// alert.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
//
// public void onClick(DialogInterface dialog, int which) {
// System.exit(0);
// }
// });
// alert.setNegativeButton("Quay lại", null);
// alert.show();
// }
// }
