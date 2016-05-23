package com.congnhanvienthong;

import java.util.ArrayList;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.congnhanvienthong.dhsc.ACtivityTraCuuTTP;
import com.congnhanvienthong.dhsc.ActivityBaoHongTTP;
import com.congnhanvienthong.dhsc.ActivityDoThuTTP;
import com.congnhanvienthong.dhsc.ActivityLyLichSuaChua;
import com.congnhanvienthong.dhsc.ActivityNhanKhoaPhieuTTP;
import com.congnhanvienthong.gtacs.ActivityLyLichMay;
import com.congnhanvienthong.gtacs.ActivityTimKiemKetCuoiKhongGian;
import com.congnhanvienthong.gtacs.ActivityTraCuuKetCuoi;
import com.congnhanvienthong.pttb.ActivityCheckUserCsonline;
import com.congnhanvienthong.pttb.ActivityLoginHoanCongPTTB;
import com.congnhanvienthong.pttb.ActivityTraCuuChung;
import com.congnhanvienthong.pttb.ActivityTraCuuToaNha;
import com.congnhanvienthong.qlnt.ActivityTraCuuNhaTram;
import com.congnhanvienthong.qltt.ActivityAuthentication;
import com.congnhanvienthong.qltt.ActivityTimKiemThanhToan;
import com.congnhanvienthong.qltt.ActivityTraCuuNo;
import com.slidingmenu.lib.SlidingMenu;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.IntentCompat;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import congnhanvienthong.entity.ThongTinModule;
import control.DatabaseHandler;
import control.Util;
import view.Menufragment;
import view.appmenu.EntryAdapter;
import view.appmenu.EntryItem;
import view.appmenu.Item;
import view.appmenu.SectionItem;
import webservice.WebProtocol;

public class ActivityBaseToDisplay extends SherlockFragmentActivity
		implements Menufragment.MenuClickInterFace, View.OnClickListener {
	protected SlidingMenu menu;
	ListView list;
	ListView listview = null;
	ArrayList<Item> items = new ArrayList<Item>();
	protected ActionBar ab;
	protected Context context;
	ProgressDialog mProgressDialog;
	protected View body, foot;
	TextView headerText;
	ImageView buttonMenu;
	Button shortcut1, shortcut2, shortcut3;
	ThongTinModule thongTinModule;
	DatabaseHandler databaseHandler;
	protected boolean showDialog = true;
	ImageView bttRefresh;
	RelativeLayout headerBar;
	LinearLayout shortcutView;
	public View rightMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base_to_display);

		context = ActivityBaseToDisplay.this;
		headerText = (TextView) findViewById(R.id.text_header);
		bttRefresh = (ImageView) findViewById(R.id.btt_refresh);
		buttonMenu = (ImageView) findViewById(R.id.menuViewButton);
		menu = new SlidingMenu(this);
		menu.setMode(SlidingMenu.LEFT);
		mProgressDialog = new ProgressDialog(context);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		menu.setMenu(R.layout.menu);
		menu.setSlidingEnabled(true);
		menu.setOnOpenListener(new SlidingMenu.OnOpenListener() {

			@Override
			public void onOpen() {
				// TODO Auto-generated method stub
				menu.setSlidingEnabled(true);
				Util.hideKeyBoard(context, body);
			}
		});
		Display display = getWindowManager().getDefaultDisplay();
		int screenWidth = display.getWidth();
		int wiMenu = (int) screenWidth * 3 / 4;
		menu.setBehindWidth(wiMenu);
		body = findViewById(R.id.body);
		foot = findViewById(R.id.footer);
		shortcutView = (LinearLayout) findViewById(R.id.test);

		list = (ListView) findViewById(R.id.listView_main);
		onCreatRightMenu();

		EntryAdapter adapter = new EntryAdapter(getApplicationContext(), items);
		list.setAdapter(adapter);
		list.setCacheColorHint(Color.TRANSPARENT);
		shortcut1 = (Button) findViewById(R.id.shortcut1);
		shortcut2 = (Button) findViewById(R.id.shortcut2);
		shortcut3 = (Button) findViewById(R.id.shortcut3);
		shortcut1.setSelected(true);
		shortcut2.setSelected(true);
		shortcut3.setSelected(true);
		shortcut1.setOnClickListener(this);
		shortcut2.setOnClickListener(this);
		shortcut3.setOnClickListener(this);
		buttonMenu.setOnClickListener(this);
		disableShortCut();
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				try {
					@SuppressWarnings("rawtypes")
					Class temp = ((EntryItem) arg0.getItemAtPosition(arg2)).subtitle;
					String title = ((EntryItem) arg0.getItemAtPosition(arg2)).title;
					if (temp == null) {
						if (title.equals("Thoát")) {
							AlertDialog.Builder alert = new AlertDialog.Builder(context);
							alert.setTitle("Thông báo!");
							alert.setMessage("Bạn có muốn thoát không ");

							alert.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog, int which) {
									Intent intent = new Intent(Intent.ACTION_MAIN);
									intent.addCategory(Intent.CATEGORY_HOME);
									intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
									// intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
									intent.addFlags(
											Intent.FLAG_ACTIVITY_CLEAR_TOP | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
									intent.putExtra("EXIT", true);
									finish();
									System.exit(0);
									startActivity(intent);

								}
							});
							alert.setNegativeButton("Quay lại", null);
							alert.show();
						} else {
							Intent intent = context.getPackageManager().getLaunchIntentForPackage("vthn.bts.swipetab");
							if (intent != null) {
								// We found the activity now start the activity
								intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								context.startActivity(intent);
							} else {
								// Bring user to the market or let them choose
								// an app?
								intent = new Intent(Intent.ACTION_VIEW);
								intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								intent.setData(Uri.parse("market://details?id=" + "vthn.bts.swipetab"));
								Util.confirmProcess(context, "Tải ứng dụng cường độ sóng?", intent);
							}

						}
					} else {
						Intent intent = null;
						intent = new Intent(getApplicationContext(), temp);
						startActivity(intent);
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
						finish();
					}
				} catch (Exception e) {
					// TODO: handle exception
					Util.processException(e, context);
				}

			}
		});
	}

	protected void setHeader(String header) {
		headerText.setText(header);
		headerText.setSelected(true);

	}

	public void bothMenu(boolean flag) {
		if (!flag)
			menu.setMode(SlidingMenu.LEFT);
		else {
			menu.setMode(SlidingMenu.LEFT_RIGHT);
		}

	}

	public void setRightMenu(int viewID) {
		rightMenu = LayoutInflater.from(context).inflate(viewID, null);
		menu.setSecondaryMenu(viewID);

	}

	protected void enableRefresh() {
		bttRefresh.setVisibility(View.VISIBLE);
		bttRefresh.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
				startActivity(getIntent());

			}
		});

	}

	// Đổ dữ liệu vào menu
	protected void onCreatRightMenu() {
		if (Util.isUserDHSC == true) {
			// Menu của điều hành sửa chữa
			items.add(new SectionItem("Điều hành sửa chữa"));
			items.add(new EntryItem("Tra cứu", ACtivityTraCuuTTP.class, R.drawable.tracuu_dothu));
			items.add(new EntryItem("Nhận Báo Hỏng", ActivityBaoHongTTP.class, R.drawable.lock));
			items.add(new EntryItem("Đo Thử", ActivityDoThuTTP.class, R.drawable.do_thu));

			items.add(new EntryItem("Khóa phiếu sửa chữa", ActivityNhanKhoaPhieuTTP.class, R.drawable.lock));
			items.add(new EntryItem("Lý lịch sửa chữa", ActivityLyLichSuaChua.class, R.drawable.tracuu_dothu));

		}
		// Menu PTTB
		if (true) {
			items.add(new SectionItem("Phát triển thuê bao"));
			items.add(new EntryItem("Hoàn công dịch vụ", ActivityLoginHoanCongPTTB.class, R.drawable.cap_nhat));
			items.add(new EntryItem("Tìm kiếm tòa nhà", ActivityTraCuuToaNha.class, R.drawable.cap_nhat));
			items.add(new EntryItem("Tra cứu chung", ActivityTraCuuChung.class, R.drawable.cap_nhat));
			items.add(new EntryItem("CSOnline", ActivityCheckUserCsonline.class, R.drawable.cap_nhat));
		}
		// Menu Quản lý mạng cáp
		if (Util.isUserGTCAS) {
			items.add(new SectionItem("Quản lý mạng cáp"));
			items.add(new EntryItem("Tìm kiếm kết cuối", ActivityTraCuuKetCuoi.class, R.drawable.cap_nhat));
			items.add(new EntryItem("Tìm kết cuối theo không gian", ActivityTimKiemKetCuoiKhongGian.class,
					R.drawable.cap_nhat));
			items.add(new EntryItem("Lý lịch máy", ActivityLyLichMay.class, R.drawable.cap_nhat));
		}
		if (true) {
			items.add(new SectionItem("Quản lý nhà trạm"));
			items.add(new EntryItem("Tra cứu nhà trạm", ActivityTraCuuNhaTram.class, R.drawable.cap_nhat));
		}
		//
		// Menu Thanh toán
		//

		items.add(new SectionItem("Thanh toán"));
		items.add(new EntryItem("Tra cứu nợ", ActivityTraCuuNo.class, R.drawable.cap_nhat));
		if (Util.isUserQLTT) {
			items.add(new EntryItem("Thanh toán", ActivityTimKiemThanhToan.class, R.drawable.payment));

			items.add(new EntryItem("Xác thực thanh toán", ActivityAuthentication.class, R.drawable.security_string));
		}

		items.add(new SectionItem("Tài khoản"));
		items.add(new EntryItem("Trợ giúp", ActivityHelpMenu.class, R.drawable.help));
		items.add(new EntryItem("Tài khoản", ActivityProfileTD.class, R.drawable.account));
		items.add(new EntryItem("Cường độ sóng", null, R.drawable.bts_icon_red));
		items.add(new EntryItem("Thoát", null, R.drawable.exit));

		Util.items = items;

	}

	public void setBodyLayout(int id) {
		try {
			((RelativeLayout) body).removeAllViews();
			((RelativeLayout) body).addView(getLayoutInflater().inflate(id, null));
		} catch (Exception ex) {
		}
	}

	public void disableShortCut() {
		try {
			shortcutView.setVisibility(View.GONE);
		} catch (Exception ex) {
		}
	}

	public void setFootLayout(int id) {
		((RelativeLayout) foot).removeAllViews();
		((RelativeLayout) foot).addView(getLayoutInflater().inflate(id, null));
	}

	// -------setup phím tắt------------

	// ---------------- Phần xử lý để các actitity con gọi các ws
	// -----------------
	// ----------------- Tạo ra 1 asytask để thực hiện triệu gọi các ws--------
	// ----------------Đầu vào là một object con, kế thừa từ class
	// BaseWebservice
	public class Task extends AsyncTask<WebProtocol, String, Void> {
		WebProtocol runTask;

		@Override
		protected Void doInBackground(WebProtocol... params) {
			// TODO Auto-generated method stub

			try {

				int length = params.length;
				for (int j = 0; j < length; j++) {
					runTask = params[0];
					params[j].execute();
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				// Util.processException(e, context);

			}
			return null;

		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			showDialog = true;
			try {
				if (mProgressDialog != null) {
					mProgressDialog.dismiss();
				}
				onsucces(this.runTask);
			} catch (Exception e) {
				// TODO: handle exception
				if (mProgressDialog != null) {
					mProgressDialog.dismiss();

				}
				e.printStackTrace();
			}

		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			if (showDialog && !mProgressDialog.isShowing()) {

				mProgressDialog.setMessage("Đang xử lý");
				mProgressDialog.setIndeterminate(false);
				mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				mProgressDialog.setCancelable(false);
				mProgressDialog.show();
			}

		}

	}

	public void onsucces(WebProtocol task) {
		Util.hideKeyBoard(context, body);
	}

	@SuppressLint("NewApi")
	public void onExecuteToServer(boolean dialog, String mes, WebProtocol... params) {

		Util.hideKeyBoard(context, body);
		showDialog = dialog;
		try {
			final WebProtocol[] temp = params;
			if (mes != null) {
				AlertDialog.Builder alert = new AlertDialog.Builder(context);
				alert.setTitle("Thông báo!");
				alert.setMessage(mes);

				alert.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						Task task = new Task();
						task.execute(temp);
					}
				});
				alert.setNegativeButton("Không", null);
				alert.show();
			} else {
				Task task = new Task();
				task.execute(temp);
			}
		} catch (Exception e) {
			Util.processException(e, context);
		}

	}

	@Override
	public void onListitemClick(String item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.menuViewButton:
			if (menu.isShown()) {
				menu.toggle();
			} else {
				menu.showMenu(true);
			}

			break;

		default:
			break;
		}

	}

}
