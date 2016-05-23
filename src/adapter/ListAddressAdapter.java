package adapter;

import java.util.ArrayList;

import com.congnhanvienthong.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Address;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

@SuppressLint("ResourceAsColor")
@SuppressWarnings("hiding")
public class ListAddressAdapter extends BaseAdapter {

	Context context;
	ArrayList<Address> lstAddress;
	private LayoutInflater inflater;

	public ListAddressAdapter(Context context, ArrayList<Address> lstAddress) {
		super();
		this.context = context;
		this.lstAddress = lstAddress;
		inflater = LayoutInflater.from(context);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lstAddress.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return lstAddress.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
		if (convertView == null) {
			v = inflater.inflate(R.layout.address_item, null);
		}
		TextView text = (TextView) v.findViewById(R.id.txt_address);
		text.setMinimumHeight(35);

		Address add = lstAddress.get(position);
		int a = add.getMaxAddressLineIndex();
		String diachi = add.getAddressLine(0);
		for (int i = 1; i < a; i++) {
			diachi = diachi + ", " + add.getAddressLine(i);
		}
		if (add != null) {
			text.setText(add.getAdminArea() + "\n" + diachi);
		}
		return v;
	}

	

	

}
