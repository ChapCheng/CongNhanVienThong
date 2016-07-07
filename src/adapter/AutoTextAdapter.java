package adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.location.Address;
import android.text.TextUtils.TruncateAt;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class AutoTextAdapter<T> extends ArrayAdapter<T> implements Filterable {
	Context context;
	ArrayList<T> lstObj;
	private LayoutInflater mInflater;

	public AutoTextAdapter(Context context, int resource, ArrayList<T> objects) {
		super(context, resource, objects);
		lstObj = objects;
		this.context = context;
		mInflater = LayoutInflater.from(context);

		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Holder hol = new Holder();
	
		TextView text = new TextView(context);
		text.setTextSize(10);
		final int pos = position;
		text.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		Object t = lstObj.get(pos);
		if (t != null) {
			text.setText(t.toString());
		}
		return text;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView text = new TextView(context);
		text.setTextColor(Color.BLACK);
		text.setLayoutParams(new LayoutParams(35, 35));
		final int pos = position;
		Object t = lstObj.get(pos);
		if (t != null) {
			text.setText(t.toString());
		}
		return text;
	}

	@Override
	public T getItem(int position) {
		// TODO Auto-generated method stub
		return lstObj.get(position);
	}

	@Override
	public Filter getFilter() {
		// TODO Auto-generated method stub
		return super.getFilter();
	}
	private class Holder {
		public TextView thongTin;

	}

}
