package adapter;

import java.lang.reflect.Field;
import java.util.ArrayList;

import com.congnhanvienthong.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import control.AnnotationField;
import control.Util;

@SuppressLint({ "NewApi", "DefaultLocale" })
public class BaseListViewAdapter<T> extends BaseAdapter {
	ArrayList<T> lstPara;
	ArrayList<T> lstSelect;
	ArrayList<T> lstData;
	private LayoutInflater inflater;
	private DisplayMetrics metrics_;
	boolean hasCheck;
	Context con;
	static final int MIN_DISTANCE = 100;
	boolean isVisiable = false;

	public BaseListViewAdapter(Context context, ArrayList<T> lstPara, DisplayMetrics metrics, boolean flag) {

		// TODO Auto-generated constructor stub
		inflater = LayoutInflater.from(context);
		con = context;
		this.lstPara = lstPara;
		this.metrics_ = metrics;
		this.lstSelect = new ArrayList<T>();
		lstData = new ArrayList<T>();
		lstData.addAll(this.lstPara);
		this.hasCheck = flag;

	}

	public BaseListViewAdapter(Context context, ArrayList<T> lstPara) {

		// TODO Auto-generated constructor stub
		inflater = LayoutInflater.from(context);
		con = context;
		this.lstPara = lstPara;
		this.metrics_ = null;
		this.lstSelect = new ArrayList<T>();
		lstData = new ArrayList<T>();
		lstData.addAll(this.lstPara);
		this.hasCheck = false;

	}

	public BaseListViewAdapter(Context context, ArrayList<T> lstPara, boolean isVis) {

		// TODO Auto-generated constructor stub
		inflater = LayoutInflater.from(context);
		con = context;
		this.lstPara = lstPara;
		this.metrics_ = null;
		this.lstSelect = new ArrayList<T>();
		lstData = new ArrayList<T>();
		lstData.addAll(this.lstPara);
		this.hasCheck = false;
		this.isVisiable = isVis;

	}

	public int getCount() {
		// TODO Auto-generated method stub
		return lstPara.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return lstPara.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressLint("NewApi")
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View vi = convertView;
		final Holder holder = new Holder();
		final int pos = position;

		if (convertView == null) {
			vi = inflater.inflate(R.layout.item_nhan_khoa_phieu, null);
			// holder = (BaseListViewAdapter<T>.Holder) vi.getTag();
			// vi.setTag(holder);
		} else {
			vi = convertView;
		}

		String hienThi = "", khongHienThi = "";
		String groupName = "";
		holder.thongTinChung = (TextView) vi.findViewById(R.id.nhankhoaphieu_thongtinchung);
		holder.check = (CheckBox) vi.findViewById(R.id.check_box);
		if (hasCheck) {
			holder.check.setVisibility(View.VISIBLE);
		}
		holder.txtGroup = (TextView) vi.findViewById(R.id.txtGroupText);
		Field fieldArr[] = lstPara.get(position).getClass().getDeclaredFields();
		Field fieldCoHienThi[] = new Field[10];
		ArrayList<Field> fieldKhongHienThi = new ArrayList<Field>();
		for (Field field : fieldArr) {
			field.setAccessible(true);
			try {
				AnnotationField annotationField = field.getAnnotation(AnnotationField.class);
				if (annotationField != null) {
					if (annotationField.hienthi()) {
						fieldCoHienThi[annotationField.order()] = field;
					}
					if (!annotationField.hienthi()) {
						fieldKhongHienThi.add(field);
					}
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		}
		for (Field field1 : fieldCoHienThi) {
			if (field1 != null) {
				field1.setAccessible(true);
				try {
					AnnotationField annotationField = field1.getAnnotation(AnnotationField.class);
					if (annotationField.hienthi() && field1.get(lstPara.get(position)).toString().length() > 0
							&& field1.get(lstPara.get(position)) != null) {

						hienThi = hienThi + annotationField.tenNhan() + ": ";
						hienThi = hienThi + field1.get(lstPara.get(position)).toString() + "<br>";

					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}

		}
		for (Field field2 : fieldKhongHienThi) {
			if (field2 != null) {
				field2.setAccessible(true);
				try {
					AnnotationField annotationField = field2.getAnnotation(AnnotationField.class);
					if (!annotationField.hienthi()) {

						khongHienThi = khongHienThi + "<b>" + annotationField.tenNhan() + "</b>: ";
						khongHienThi = khongHienThi + field2.get(lstPara.get(position)).toString() + "<br>";

					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

		}
		holder.more = (RelativeLayout) vi.findViewById(R.id.nhankhoaphieu_more);
		holder.content = (LinearLayout) vi.findViewById(R.id.nhankhoaphieu_content);
		holder.more.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (holder.content.getVisibility()) {
				case View.GONE:
					holder.content.setVisibility(View.VISIBLE);
					break;
				case View.VISIBLE:
					holder.content.setVisibility(View.GONE);
					break;

				default:
					break;
				}

			}
		});
		holder.check.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					lstSelect.add((T) getItem(pos));
				} else {

					lstSelect.remove((T) getItem(pos));
				}
			}
		});

		try {
			khongHienThi = khongHienThi.substring(0, khongHienThi.length() - 1);
			hienThi = hienThi.substring(0, hienThi.length() - 1);
			if (hienThi.endsWith("<br>"))
				hienThi = hienThi.substring(0, hienThi.length() - 4);
		} catch (Exception e) {
			// TODO: handle exception
		}

		holder.hienThiThem = (TextView) vi.findViewById(R.id.hienthithem);

		Spanned sp = Html.fromHtml(hienThi.toString());
		Spanned sp1 = Html.fromHtml(khongHienThi.toString());
		holder.hienThiThem.setText(sp1);
		holder.thongTinChung.setText(sp);
		holder.txtGroup.setText(groupName);
		if (khongHienThi.trim().equals("") || isVisiable) {
			holder.more.setVisibility(View.GONE);
			holder.hienThiThem.setVisibility(View.GONE);
		}
		vi.setTag(holder);
		if (metrics_ != null) {
			Animation animation = null;
			animation = new TranslateAnimation(metrics_.heightPixels / 2, 0, 0, 0);
			animation.setDuration(700);
			vi.startAnimation(animation);
			animation = null;
		}

		return vi;
	}

	public ArrayList<T> getChechItem() {
		return lstSelect;
	}

	private class Holder {
		public TextView thongTinChung, hienThiThem;
		LinearLayout content;
		TextView txtGroup;
		RelativeLayout more;
		public CheckBox check;

	}

	@SuppressWarnings("unchecked")
	@SuppressLint("DefaultLocale")
	public void Fillter(String fillterText) {
		fillterText = Util.removeAccent(fillterText);
		lstPara.clear();
		if (fillterText.trim().equals(""))
			lstPara.addAll(lstData);
		else {
			for (Object t : lstData) {
				String needCompare = Util.removeAccent(t.toString());
				if (needCompare.toLowerCase().contains(fillterText.toLowerCase()))
					lstPara.add((T) t);
			}
		}
		notifyDataSetChanged();

	}

	@Override
	public boolean isEnabled(int position) {
		// TODO Auto-generated method stub
		return true;
	}

	

}
