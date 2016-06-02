package adapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.congnhanvienthong.R;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import control.AnnotationField;

public class BaseGroupListViewAdapter<T> extends BaseExpandableListAdapter {

	ArrayList<T> lstPara;
	String groupName;
	String parent;
	private Context context;
	private LayoutInflater inflater;
	private List<String> listDataHeader; // header titles
	// child data in format of header title, child title
	private HashMap<String, ArrayList<T>> mapDataChild;
	String title;

	public BaseGroupListViewAdapter(Context context, ArrayList<T> lstData, String name, String title) {
		this.context = context;
		this.lstPara = lstData;
		this.groupName = name;
		this.title = title;
		inflater = LayoutInflater.from(context);
		mapDataChild = new HashMap<String, ArrayList<T>>();
		this.listDataHeader = new ArrayList<String>();
		String tempName = null;
		ArrayList<T> lstObjNew = new ArrayList<T>();
		;
		for (T t : lstData) {
			try {
				Field field = t.getClass().getField(name);
				field.setAccessible(true);
				tempName = field.get(t).toString();

				if (!listDataHeader.contains(tempName)) {

					listDataHeader.add(tempName);
					lstObjNew = new ArrayList<T>();
				}
				lstObjNew.add(t);
				mapDataChild.put(tempName, lstObjNew);

			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return this.listDataHeader.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return this.mapDataChild.get(this.listDataHeader.get(groupPosition)).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return this.listDataHeader.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return this.mapDataChild.get(this.listDataHeader.get(groupPosition)).get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stubf
		String headerTitle = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.list_group, null);
		}
		TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
		lblListHeader.setText(this.title + ": " + headerTitle);
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		// TODO Auto-generated method stub
		View vi = convertView;
		final Holder holder = new Holder();

		if (convertView == null) {
			vi = inflater.inflate(R.layout.item_nhan_khoa_phieu, null);
		} else {
			vi = convertView;
		}
		T t = (T) getChild(groupPosition, childPosition);
		String hienThi = "", khongHienThi = "";
		holder.thongTinChung = (TextView) vi.findViewById(R.id.nhankhoaphieu_thongtinchung);
		Field fieldArr[] = t.getClass().getDeclaredFields();
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
					if (annotationField.hienthi()) {

						hienThi = hienThi + annotationField.tenNhan() + ": ";
						hienThi = hienThi + field1.get(t).toString() + "<br>";

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
						khongHienThi = khongHienThi + field2.get(t).toString() + "<br>";

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
		if (khongHienThi.trim().equals("")) {
			holder.more.setVisibility(View.GONE);
			holder.hienThiThem.setVisibility(View.GONE);
		}
		vi.setTag(holder);

		return vi;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	private class Holder {
		public TextView thongTinChung, hienThiThem;
		LinearLayout content;
		RelativeLayout more;

	}

}
