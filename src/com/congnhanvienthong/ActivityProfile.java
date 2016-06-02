// package com.congnhanvienthong;
//
// import java.io.File;
// import java.io.FileInputStream;
// import java.io.FileNotFoundException;
//
// import org.ksoap2.serialization.SoapObject;
// import org.ksoap2.serialization.SoapPrimitive;
//
// import webservice.BaseTask;
// import webservice.GetImageTask;
// import webservice.GetProfileTask;
// import webservice.SaveImage;
// import webservice.WebProtocol;
// import android.app.AlertDialog;
// import android.app.ProgressDialog;
// import android.content.DialogInterface;
// import android.content.Intent;
// import android.database.Cursor;
// import android.graphics.Bitmap;
// import android.graphics.BitmapFactory;
// import android.net.Uri;
// import android.os.Bundle;
// import android.view.Gravity;
// import android.view.View;
// import android.view.View.OnClickListener;
// import android.widget.ImageView;
// import android.widget.TextView;
// import android.widget.Toast;
// import congnhanvienthong.entity.User;
// import control.Util;
//
//// import com.congnhanvienthong.ActivityBaseToDisplay.Task;
//
// public class ActivityProfile extends ActivityBaseToDisplay {
// ProgressDialog progressDialog;
// private static User user;
// private final int CAMERA_PICTURE = 1;
// private final int GALLERY_PICTURE = 2;
// private ImageView userPictureImageView;
// private Intent pictureActionIntent = null;
// Bitmap bitmap;
// GetProfileTask getProfileTask;
// GetImageTask getImageTask;
// static Bitmap temp;
// SaveImage saveImage;
//
// String path, id_nguoidung;
//
// @Override
// public void onCreate(Bundle savedInstanceState) {
// // TODO Auto-generated method stub
// super.onCreate(savedInstanceState);
// this.context = ActivityProfile.this;
// setHeader("Thông tin tài khoản");
// if (Util.user == null) {
// getProfileTask = new GetProfileTask();
// getProfileTask.input.add(Util.userName);
// getImageTask = new GetImageTask();
// getImageTask.input.add(Util.userName);
// onExecuteToServer(true, null, getProfileTask, getImageTask);
// } else {
// setBodyLayout(R.layout.activity_profile);
// setData(Util.user);
// }
// }
//
// @Override
// public void onBackPressed() {
// }
//
// @Override
// protected void onActivityResult(int requestCode, int resultCode, Intent data)
// {
// // TODO Auto-generated method stub
// try {
// super.onActivityResult(requestCode, resultCode, data);
// if (requestCode == GALLERY_PICTURE) {
// Uri uri = data.getData();
// if (uri != null) {
// // User had pick an image.
// Cursor cursor = getContentResolver().query(uri,
// new String[] { android.provider.MediaStore.Images.ImageColumns.DATA }, null,
// null, null);
// cursor.moveToFirst();
// // Link to the image
// final String imageFilePath = cursor.getString(0);
// path = imageFilePath;
// File photos = new File(imageFilePath);
// Bitmap b = decodeFile(photos);
// b = Bitmap.createScaledBitmap(b, 150, 150, true);
// temp = b;
// bitmap = b;
// userPictureImageView.setImageBitmap(b);
// cursor.close();
// } else {
// Toast toast = Toast.makeText(this, "No Image is selected.",
// Toast.LENGTH_LONG);
// toast.show();
// }
// } else if (requestCode == CAMERA_PICTURE) {
// if (data.getExtras() != null) {
// // here is the image from camera
// bitmap = (Bitmap) data.getExtras().get("data");
// temp = bitmap;
// userPictureImageView.setImageBitmap(bitmap);
// }
//
// }
// if (Util.user != null) {
// Util.user.setAvatar(temp);
// String avatarString = Util.BitMapToString(bitmap);
// saveImage = new SaveImage();
// saveImage.input.add(Util.userName);
// saveImage.input.add(Util.pass);
// saveImage.input.add(Util.userName + "_avatar");
// saveImage.input.add(String.valueOf(bitmap.getRowBytes()));
// saveImage.input.add("png");
// saveImage.input.add(avatarString);
// saveImage.input.add("1");
// saveImage.input.add(id_nguoidung);
// saveImage.input.add("105.85");
// saveImage.input.add("21.026");
// onExecuteToServer(true, "Cập nhật ảnh người dùng ??", saveImage);
// }
//
// } catch (Exception e) {
// e.printStackTrace();
// }
// }
//
// private void startDialog() {
// AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
// myAlertDialog.setTitle("Ảnh đại diện");
// myAlertDialog.setMessage("Sử dụng ảnh ?");
//
// myAlertDialog.setPositiveButton("Gallery", new
// DialogInterface.OnClickListener() {
// public void onClick(DialogInterface arg0, int arg1) {
// pictureActionIntent = new Intent(Intent.ACTION_GET_CONTENT, null);
// pictureActionIntent.setType("image/*");
// pictureActionIntent.putExtra("return-data", true);
// startActivityForResult(pictureActionIntent, GALLERY_PICTURE);
// }
// });
//
// myAlertDialog.setNegativeButton("Camera", new
// DialogInterface.OnClickListener() {
// public void onClick(DialogInterface arg0, int arg1) {
// pictureActionIntent = new
// Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
// startActivityForResult(pictureActionIntent, CAMERA_PICTURE);
// }
// });
// myAlertDialog.show();
// }
//
// private Bitmap decodeFile(File f) {
// try {
// // decode image size
// BitmapFactory.Options o = new BitmapFactory.Options();
// o.inJustDecodeBounds = true;
// BitmapFactory.decodeStream(new FileInputStream(f), null, o);
//
// // Find the correct scale value. It should be the power of 2.
// final int REQUIRED_SIZE = 70;
// int width_tmp = o.outWidth, height_tmp = o.outHeight;
// int scale = 1;
// while (true) {
// if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE)
// break;
// width_tmp /= 2;
// height_tmp /= 2;
// scale++;
// }
//
// // decode with inSampleSize
// BitmapFactory.Options o2 = new BitmapFactory.Options();
// o2.inSampleSize = scale;
// return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
// } catch (FileNotFoundException e) {
// }
// return null;
// }
//
// @Override
// public void onsucces(WebProtocol task) {
// // TODO Auto-generated method stub
// super.onsucces(task);
// // get thông tin của user
// if (task.equals(getProfileTask)) {
// String nameUser = "";
// String tel = "";
// String soThe = "";
// String donVi = "";
// String CMT = "";
// String mail = "";
// String baoLoi = "";
// try {
// user = new User();
// SoapObject soapObject = (SoapObject) getProfileTask.result;
// SoapPrimitive messeage = (SoapPrimitive) soapObject.getProperty("Message");
// baoLoi = messeage.toString();
// SoapPrimitive code = (SoapPrimitive) soapObject.getProperty("Code");
// if (!code.toString().equals("0")) {
// Util.showAlert(context, messeage.toString());
// }
// if (code.toString().equals("0")) {
// SoapObject kqObject = (SoapObject) soapObject.getProperty("Data");
// SoapObject ob3 = (SoapObject) kqObject.getProperty("diffgram");
// SoapObject ob4 = (SoapObject) ob3.getProperty("NewDataSet");
// SoapObject thongTinNguoiDung = (SoapObject) ob4.getProperty("NGUOIDUNG");
// nameUser = thongTinNguoiDung.getProperty("TENNHANVIEN").toString();
// tel = thongTinNguoiDung.getProperty("DIDONG").toString();
// Util.userTel = tel;
// soThe = thongTinNguoiDung.getProperty("SOTHE").toString();
// donVi = thongTinNguoiDung.getProperty("TENDONVI").toString();
// CMT = thongTinNguoiDung.getProperty("SO_CMT").toString();
// mail = thongTinNguoiDung.getProperty("EMAIL").toString();
// id_nguoidung = thongTinNguoiDung.getProperty("NHANVIEN_ID").toString();
// SoapObject anhNguoiDung = (SoapObject) getImageTask.result;
// SoapObject ob2 = (SoapObject) anhNguoiDung.getProperty("Data");
// ob3 = (SoapObject) ((SoapObject) ob2).getProperty("diffgram");
// ob4 = (SoapObject) ((SoapObject) ob3).getProperty("NewDataSet");
// SoapObject ob5 = (SoapObject) ((SoapObject) ob4).getProperty("hinhanh");
//
// String dataAvatar = ob5.getPropertyAsString("noidung");
// temp = Util.StringToBitMap(dataAvatar);
// user.setAvatar(temp);
// }
// } catch (Exception e) {
// // TODO: handle exception
// Util.showAlert(context, baoLoi);
// }
//
// finally {
// if (nameUser.contains("{}")) {
// nameUser = "Chưa cập nhập";
// }
// if (tel.contains("{}")) {
// tel = "Chưa cập nhập";
// }
// if (soThe.contains("{}")) {
// soThe = "Chưa cập nhập";
// }
// if (donVi.contains("{}")) {
// donVi = "Chưa cập nhập";
// }
// if (CMT.contains("{}")) {
// CMT = "Chưa cập nhập";
// }
// if (mail.contains("{}")) {
// mail = "Chưa cập nhập";
// }
//
// user.setName(nameUser);
// user.setCMT(CMT);
// user.setDonVi(donVi);
// user.setSoThe(soThe);
// user.setTel(tel);
// user.setEmail(mail);
// setBodyLayout(R.layout.activity_profile);
// setData(user);
// Util.user = user;
//
// }
//
// }
//
// // update ảnh cho user
// if (task.equals(saveImage)) {
// SoapObject object = (SoapObject) saveImage.result;
// String thongbao = object.getProperty("Message").toString();
// Toast toast = Toast.makeText(context, "Cập nhật ảnh : " + thongbao,
// Toast.LENGTH_SHORT);
// toast.setGravity(Gravity.CENTER, 0, 0);
// toast.show();
//
// }
// }
//
// public void setData(User userTemp) {
// TextView nameView = (TextView) body.findViewById(R.id.profile_name);
// TextView hoView = (TextView) body.findViewById(R.id.profile_ho);
// TextView telView = (TextView) body.findViewById(R.id.profile_tel);
// TextView emailView = (TextView) body.findViewById(R.id.profile_mail);
// TextView sotheView = (TextView) body.findViewById(R.id.profile_sothe);
// TextView CMTView = (TextView) body.findViewById(R.id.profile_cmt);
// TextView donviView = (TextView) body.findViewById(R.id.profile_donvi);
// userPictureImageView = (ImageView) body.findViewById(R.id.profile_image);
// String hoTen = userTemp.getName();
// String[] ho_ten = hoTen.split(" ");
// int length = ho_ten.length;
// String ho = "";
// String ten = ho_ten[ho_ten.length - 1];
// if (length > 1) {
// for (int i = 0; i < length - 1; i++) {
// ho = ho + " " + ho_ten[i];
// }
// }
// ho = ho.trim();
// hoView.setText(ho);
// nameView.setText(ten);
// telView.setText(userTemp.getTel());
// emailView.setText(userTemp.getEmail());
// sotheView.setText(userTemp.getSoThe());
// CMTView.setText(userTemp.getCMT());
// donviView.setText(userTemp.getDonVi());
// if (temp != null) {
//
// userPictureImageView.setImageBitmap(ActivityProfile.temp);
// }
//
// userPictureImageView.setOnClickListener(new OnClickListener() {
// public void onClick(View v) {
// startDialog();
// }
//
// });
//
// }
//
// }
