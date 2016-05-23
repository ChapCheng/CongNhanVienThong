package control;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import view.appmenu.EntryItem;
import view.appmenu.Item;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.location.LocationManager;
import android.provider.Settings;
import android.text.Html;
import android.text.Spanned;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import adapter.BaseSpinnerAdapter;
import congnhanvienthong.entity.User;
import congnhanvienthong.entity.dhsc.Device;
import congnhanvienthong.entity.dhsc.LoaiDichVu;
import congnhanvienthong.entity.dhsc.LoaiDo;
import congnhanvienthong.entity.dhsc.NhanVien;
import congnhanvienthong.entity.dhsc.TinhThanhPho;

public class Util {
	// public static List<NghiepVu> listNghiepVu;
	public static List<LoaiDichVu> listLoaiDichVu;
	public static List<Integer> listQuyenUser;
	public static List<String> listHeThong;
	public static ArrayList<NhanVien> lstNhanVien;
	public static String userName, pass, userTel;
	public static User user;
	public static SoapObject userSoap;
	public static ArrayList<LoaiDo> arrayListLoaiDo;
	public static TinhThanhPho ttp;
	View view;
	ProgressDialog progressDialog;
	public static boolean flag = false;
	// public static boolean isUserQLTT= false;
	public static boolean isUserDHSC = false;
	public static boolean isUserGTCAS = false;

	public static ArrayList<Item> items;
	public static boolean isUserQLTT;

	// Show thông báo
	public static void showAlert(Context context, String messeage) {
		AlertDialog.Builder alert = new AlertDialog.Builder(context);
		alert.setTitle("Thông báo!");
		alert.setMessage(messeage);
		alert.setPositiveButton("Thoát", null);
		alert.show();

	}

	public static void confirmProcess(final Context context, String messeage, final Intent intent) {
		AlertDialog.Builder alert = new AlertDialog.Builder(context);
		alert.setTitle("Thông báo!");
		alert.setMessage(messeage);
		alert.setNegativeButton("Quay lại", null);
		alert.setPositiveButton("Xác nhận", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				context.startActivity(intent);

			}
		});
		alert.show();

	}

	// hàm này để khi bấm nút back, hỏi có thoát không.
	public static void exit(Context context) {
		AlertDialog.Builder alert = new AlertDialog.Builder(context);
		alert.setTitle("Thông báo!");
		alert.setMessage("Bạn có muốn thoát không ");
		alert.setNegativeButton("Quay lại", null);
		alert.setPositiveButton("Thoát", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				System.exit(0);

			}
		});
		alert.show();
	}

	// gọi webservice

	//
	public static void hideKeyBoard(Context context, View view) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}

	public static void enterEdittext(final EditText edit, final View second) {
		edit.setOnKeyListener(new View.OnKeyListener() {

			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					second.isFocusable();
					edit.requestFocus();
				}
				return false;
			}
		});

	}

	public static String BitMapToString(Bitmap bitmap) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
		byte[] b = baos.toByteArray();
		String temp = Base64.encodeToString(b, Base64.DEFAULT);
		return temp;
	}

	public static Bitmap StringToBitMap(String image) {
		try {
			byte[] encodeByte = Base64.decode(image, Base64.DEFAULT);
			Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
			return bitmap;
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	// Đặt listview trong scroller view mà không bị lỗi. Dùng khi bắt buộc
	public static void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			// pre-condition
			return;
		}

		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}

	// Khi WS trả về dưới dạng data{}- cái này do phía ws làm không chuẩn- thì
	// bắt để trả về
	public static String layStringChuanTuSoap(String data) {
		if (data.contains("{}"))
			return "";
		else
			return data;
	}

	public static String GetNameDevice(ArrayList<Device> lstDevice, LatLng temLatLng) {
		String name = "";
		for (Device device : lstDevice) {
			if (device.getToado().equals(temLatLng)) {
				name = name + device.getDeciveName() + " ;";
			}
		}
		return "Trạm : " + name;
	}

	public static String GetNameKetCuoi(ArrayList<Device> lstDevice, LatLng temLatLng) {
		String name = "";
		int count = 0;
		for (Device device : lstDevice) {
			if (device.getToado().equals(temLatLng)) {
				count = count + 1;
				name = name + device.getDeciveName() + "\n";
			}
		}
		return "Có  : " + count + "kết cuối tại vị trí này : \n" + name;
	}

	public static LatLng DoiLongLat(double lat, double lon) {

		LatLng temp = new LatLng(lat, lon);
		if (lon < lat) {
			temp = new LatLng(lon, lat);

		}

		return temp;

	}

	public static Marker GetCurentBTS(HashSet<Marker> setBTS, String maBTS) {
		Marker temp = null;
		for (Marker marker : setBTS) {
			if (marker.getTitle().contains(maBTS)) {
				temp = marker;
			}
		}
		return temp;

	}

	public static void GetObjectFromSoapObject(Object object, SoapObject soapObject) {
		try {
			@SuppressWarnings("rawtypes")

			Class clazz = object.getClass();

			String val = null;
			// Field[] filFields = clazz.getFields();
			Field[] filFields = clazz.getDeclaredFields();
			for (Field field : filFields) {
				field.setAccessible(true);
				String name = field.getName();
				Object value = null;
				Class<?> type = field.getType();
				String typeName = type.getName();
				if (soapObject.hasProperty(name)) {
					value = soapObject.getProperty(name);
					val = layStringChuanTuSoap(value.toString());
					if (typeName.contains("String")) {
						field.set(object, val);
						// break;
					}
					if (typeName.contains("int")) {
						field.setInt(object, Integer.valueOf(layStringChuanTuSoap(value.toString())));
						// break;
					}
					if (typeName.contains("float")) {
						field.setFloat(object, Float.valueOf(layStringChuanTuSoap(value.toString())));
						// break;
					}
					if (typeName.contains("boolean")) {
						field.setBoolean(object, Boolean.valueOf(layStringChuanTuSoap(value.toString())));
						// break;
					}
					if (typeName.contains("long")) {
						field.setLong(object, Long.valueOf(layStringChuanTuSoap(value.toString())));
						// break;
					} else if (typeName.contains("congnhanvienthong")) {

						Class<?> c = Class.forName(typeName);
						Object obj = c.newInstance();
						SoapObject soap = (SoapObject) soapObject.getProperty(name);
						GetObjectFromSoapObject(obj, soap);
						field.set(object, obj);
					}

				} else {
					field.set(object, null);
				}

			}

		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static EntryItem getEntryItem(String className, ArrayList<Item> ls) {
		EntryItem entryItemResult = null;
		try {

			for (Item item : ls) {
				if (item.isSection() == false) {
					EntryItem entryItem = (EntryItem) item;
					if (entryItem.subtitle != null && entryItem.subtitle.getName().equals(className)) {
						entryItemResult = entryItem;
					}
				}

			}
			return entryItemResult;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	public static void processException(Exception e, Context context) {

		if (e instanceof ClassCastException) {
			Util.showAlert(context, "Gặp lỗi về cấu trúc dữ liệu");
		}
		if (e instanceof NullPointerException) {
			Util.showAlert(context, "Có dữ liệu chưa được gán giá trị");
		}
		if (e instanceof TimeoutException) {
			Util.showAlert(context, "Thời gian truy vấn quá lâu. Mời thử lại hoặc liên hệ quản trị nếu cần");
		}
		if (e instanceof RuntimeException) {
			Util.showAlert(context, "Lỗi xung đột dữ liệu");
		}

	}

	public static String GetInformation(SoapObject obj, Context context, int res) {
		String result = "";
		int count = obj.getPropertyCount();
		String[] temp = context.getResources().getStringArray(res);
		HashMap<String, String> hashMap = new HashMap<String, String>();
		int length = temp.length;
		for (int i = 0; i < length; i++) {
			String item = temp[i];
			String k = (item.split("-"))[0];
			String v = (item.split("-"))[1];
			hashMap.put(k, v);

		}

		for (int i = 0; i < count; i++) {
			PropertyInfo propertyInfo = new PropertyInfo();
			obj.getPropertyInfo(i, propertyInfo);
			if (layStringChuanTuSoap(propertyInfo.getValue().toString()) != "") {
				if (hashMap.containsKey(propertyInfo.getName())) {
					result = result + hashMap.get(propertyInfo.getName()) + " : " + propertyInfo.getValue().toString()
							+ "\n";
				}
			}
		}

		return result;

	}

	public static boolean confirmTurnOnGPS(final Context context) {
		LocationManager locationManager = (LocationManager) context.getSystemService(Activity.LOCATION_SERVICE);
		boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

		// Setting Dialog Title
		alertDialog.setTitle("Bật toạ độ!");

		// Setting Dialog Message
		alertDialog.setMessage("Chưa bật GPS. Bạn có muốn bận GPS của thiết bị?");

		// On pressing Settings button
		alertDialog.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				context.startActivity(intent);
				dialog.cancel();
			}
		});

		// on pressing cancel button
		alertDialog.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();

			}
		});

		// Showing Alert Message
		if (!isGPSEnabled) {
			alertDialog.show();

		}
		return isGPSEnabled;
	}

	public static void GetListObject() {

	}

	public static Bitmap writeTextOnDrawable(Context context, int drawableId, String text) {

		Bitmap bm = BitmapFactory.decodeResource(context.getResources(), drawableId).copy(Bitmap.Config.ARGB_8888,
				true);

		Typeface tf = Typeface.create("Helvetica", Typeface.BOLD);

		Paint paint = new Paint();
		paint.setStyle(Style.FILL);
		paint.setColor(Color.BLACK);
		paint.setTypeface(tf);
		paint.setTextAlign(Align.LEFT);
		paint.setTextSize(convertToPixels(context, 8));

		Rect textRect = new Rect();
		paint.getTextBounds(text, 0, text.length(), textRect);

		Canvas canvas = new Canvas(bm);

		// If the text is bigger than the canvas , reduce the font size
		if (textRect.width() >= (canvas.getWidth() - 4)) // the padding on
															// either sides is
															// considered as 4,
															// so as to
															// appropriately fit
															// in the text
			paint.setTextSize(convertToPixels(context, 7)); // Scaling needs to
															// be used for
															// different dpi's

		// Calculate the positions
		int xPos = (canvas.getWidth() / 2) - 2; // -2 is for regulating the x
												// position offset

		// "- ((paint.descent() + paint.ascent()) / 2)" is the distance from the
		// baseline to the center.
		int yPos = (int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2));

		canvas.drawText(text, xPos, yPos, paint);

		return bm;
	}

	public static int convertToPixels(Context context, int nDP) {
		final float conversionScale = context.getResources().getDisplayMetrics().density;

		return (int) ((nDP * conversionScale) + 0.5f);

	}

	public static <T> void setTextFromObject(TextView textView, T obj) {
		Field fieldArr[] = obj.getClass().getDeclaredFields();
		String hienThi = "";
		int length = fieldArr.length;
		Field fielHienThi[] = new Field[length];
		for (Field field : fieldArr) {
			if (field != null) {
				field.setAccessible(true);
				try {
					AnnotationField annotationField = field.getAnnotation(AnnotationField.class);

					if (annotationField != null && field != null) {
						// if (annotationField.hienthi()) {
						fielHienThi[annotationField.order() - 1] = field;
						// }

					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}

		}
		for (Field field : fielHienThi) {
			if (field != null) {
				field.setAccessible(true);
				AnnotationField annotationField = field.getAnnotation(AnnotationField.class);
				try {
					if (annotationField.hienthi() && field.get(obj) != null) {
						if (field.get(obj).getClass().isArray()) {
							Object[] objects = (Object[]) field.get(obj);
							hienThi = hienThi + "<b>" + annotationField.tenNhan() + "</b>" + ": <br>";
							for (int i = 0; i < objects.length; i++) {

								hienThi = hienThi + objects[i].toString() + "<br>";
							}

						} else {
							hienThi = hienThi + "<b>" + annotationField.tenNhan() + "</b>" + ": ";
							hienThi = hienThi + field.get(obj).toString() + "<br>";
						}

					}

				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Spanned sp = Html.fromHtml(hienThi.toString());
			textView.setText(sp);
		}
	}

	public static String removeAccent(String s) {

		String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		temp = pattern.matcher(temp).replaceAll("");
		temp = temp.replaceAll("đ", "d");
		temp = temp.replaceAll("Đ", "D");
		return temp;
	}

	@SuppressWarnings("unchecked")
	public static <T> ArrayList<T> SetDataToSpinner(Context context, Spinner spn, SoapObject soap, Class<?> clazz,
			boolean defaultValue, String nameField) {
		int len = soap.getPropertyCount();
		ArrayList<T> lst = new ArrayList<T>();

		try {
			if (defaultValue)
				lst.add((T) clazz.newInstance());
			for (int i = 0; i < len; i++) {
				SoapObject soapObject = (SoapObject) soap.getProperty(i);
				Object temp = clazz.newInstance();
				GetObjectFromSoapObject(temp, soapObject);
				lst.add((T) temp);
			}
			BaseSpinnerAdapter<T> adapterLoaiDichVu = new BaseSpinnerAdapter<T>(context, lst);
			adapterLoaiDichVu.name = nameField;
			spn.setAdapter(adapterLoaiDichVu);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lst;

	}

	@SuppressWarnings("unchecked")
	public static <T> ArrayList<T> SetDataToSpinner(Context context, Spinner spn, ArrayList<T> lst, String nameField) {

		try {

			BaseSpinnerAdapter<T> adapter = new BaseSpinnerAdapter<T>(context, lst);
			adapter.name = nameField;
			spn.setAdapter(adapter);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lst;

	}

	public static <T> ArrayList<T> GetListData(SoapObject soap, Class<?> clazz, boolean defaultValue) {
		int len = soap.getPropertyCount();
		ArrayList<T> lst = new ArrayList<T>();

		try {
			if (defaultValue)
				lst.add((T) clazz.newInstance());
			for (int i = 0; i < len; i++) {
				SoapObject soapObject = (SoapObject) soap.getProperty(i);
				Object temp = clazz.newInstance();
				GetObjectFromSoapObject(temp, soapObject);
				lst.add((T) temp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lst;

	}

	public static <T> T GetData(SoapObject input, Context context, Class<T> clazz, String err, String mes, String res) {
		try {
			String erros = input.getPrimitivePropertyAsString(err);
			String mess = (String) input.getPrimitivePropertyAsString(mes);
			if (erros.toLowerCase().equals("true")) {
				Util.showAlert(context, mess);
				return null;
			} else {
				SoapObject temp = (SoapObject) input.getProperty(res);
				Object name = clazz.newInstance();
				GetObjectFromSoapObject(name, temp);
				return (T) name;

			}
		} catch (Exception e) {
			// TODO: handle exception

			return null;
		}

	}

}
