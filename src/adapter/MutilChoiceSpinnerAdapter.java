package adapter;

import java.lang.reflect.Field;
import java.util.ArrayList;

import com.congnhanvienthong.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class MutilChoiceSpinnerAdapter<T> extends ArrayAdapter<T> {
	private Context context;
	// Your custom values for the spinner (User)
	private ArrayList<T> values;
	public String name;
	public String id;
	private LayoutInflater inflater;
	ArrayList<T> lstSelected;

	public MutilChoiceSpinnerAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
		// TODO Auto-generated constructor stub
	}

	public MutilChoiceSpinnerAdapter(Context context, ArrayList<T> ld) {
		super(context, android.R.layout.simple_spinner_dropdown_item, ld);
		this.context = context;
		inflater = LayoutInflater.from(context);
		this.values = ld;
		lstSelected = new ArrayList<T>();
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
		label.setText("Chọn nhân viên");
		return label;
	}

	@SuppressWarnings({ "unused", "rawtypes" })
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		final Holder holder = new Holder();
		final int pos = position;

		if (convertView == null) {
			vi = inflater.inflate(R.layout.multi_choice_item_spinner, null);
		} else {
			vi = convertView;
		}
		holder.txtInfor = (TextView) vi.findViewById(R.id.txtMultiChoice);
		holder.check = (CheckBox) vi.findViewById(R.id.cbxMultichoice);
		final Object ob = values.get(position);
		Class claz = ob.getClass();
		Field fieldArr[];
		fieldArr = values.get(position).getClass().getDeclaredFields();
		for (Field field : fieldArr) {
			field.setAccessible(true);
			try {
				String a = field.getName();

				if (a.equals(name)) {
					String hienthi = field.get(values.get(position)).toString();
					holder.txtInfor.setText(hienthi);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		}
		holder.check.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				lstSelected.add((T) ob);

			}
		});

		return vi;
	}

	private class Holder {
		public TextView txtInfor;
		public CheckBox check;

	}

	public ArrayList<T> getMultiChoice() {
		return lstSelected;

	}

}
