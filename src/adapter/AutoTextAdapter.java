package adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.location.Address;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class AutoTextAdapter extends ArrayAdapter<Address> implements Filterable {
	Context context;
	ArrayList<Address> lstAddress;
	private LayoutInflater mInflater;

	public AutoTextAdapter(Context context, int resource, ArrayList<Address> objects) {
		super(context, resource, objects);
		lstAddress = objects;
		this.context = context;
		mInflater = LayoutInflater.from(context);

		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
		TextView text = new TextView(context);
		text.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		if (v == null) {
			v = text;
		}
		Address add = lstAddress.get(position);
		if (add != null) {
			text.setText(add.getAddressLine(0));
		}
		return v;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView text = new TextView(context);
		text.setTextColor(Color.BLACK);
		text.setLayoutParams(new LayoutParams(35, 35));
		Address add = lstAddress.get(position);
		if (add != null) {
			text.setText("b");
		}
		return text;
	}

	@Override
	public Address getItem(int position) {
		// TODO Auto-generated method stub
		return lstAddress.get(position);
	}

	@Override
	public Filter getFilter() {
		// TODO Auto-generated method stub
		return super.getFilter();
	}

}
