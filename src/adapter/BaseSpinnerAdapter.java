package adapter;

import java.lang.reflect.Field;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class BaseSpinnerAdapter<T> extends ArrayAdapter<T> {

	// Your sent context
	private Context context;
	// Your custom values for the spinner (User)
	private ArrayList<T> values;
	public String name;
	public String id;

	public BaseSpinnerAdapter(Context context, int textViewResourceId, ArrayList<T> ld) {
		super(context, android.R.layout.simple_spinner_dropdown_item, ld);
		this.context = context;
		this.values = ld;
	}

	public BaseSpinnerAdapter(Context context, ArrayList<T> ld) {
		super(context, android.R.layout.simple_spinner_dropdown_item, ld);
		this.context = context;
		this.values = ld;
	}

	public int getCount() {
		return values.size();
	}

	public T getItem(int position) {
		return values.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// I created a dynamic TextView here, but you can reference your own
		// custom layout for each spinner item
		TextView label = new TextView(context);
		label.setTextColor(Color.BLACK);
		label.setPadding(5, 5, 5, 5);
		Object ob = values.get(position);
		Class claz = ob.getClass();
		Field fieldArr[];
		fieldArr = values.get(position).getClass().getDeclaredFields();
		for (Field field : fieldArr) {
			field.setAccessible(true);
			try {
				String a = field.getName();

				if (a.equals(name)) {
					String hienthi = field.get(values.get(position)).toString();
					label.setText(hienthi);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		}

		return label;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		TextView label = new TextView(context);
		label.setTextColor(Color.BLACK);
		label.setPadding(25, 25, 25, 25);
		Field fieldArr[] = values.get(position).getClass().getDeclaredFields();
		for (Field field : fieldArr) {
			field.setAccessible(true);
			try {
				if (field.getName().equals(name)) {
					String hienthi = field.get(values.get(position)).toString();
					label.setText(hienthi);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

		}

		return label;
	}
}