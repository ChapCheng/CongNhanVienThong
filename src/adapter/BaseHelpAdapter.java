package adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.congnhanvienthong.R;

public class BaseHelpAdapter extends BaseAdapter {
	private ArrayList<String> item;
	private LayoutInflater mInflater;

	public BaseHelpAdapter(ArrayList<String> result, Context context) {

		item = result;
		mInflater = LayoutInflater.from(context);
	}

	public Object getItems(int position) {
		return item.get(position);
	}

	public LayoutInflater getmInflater() {
		return mInflater;
	}

	public void setmInflater(LayoutInflater mInflater) {
		this.mInflater = mInflater;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return item.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.items, null);
			holder = new ViewHolder();
			holder.txtQues = (TextView) convertView
					.findViewById(R.id.textTitle);
			// holder.temp= convertView.findViewById(R.id.)

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.txtQues.setText(item.get(position));

		return convertView;
	}

	static class ViewHolder {
		TextView txtQues;
		ImageView temp;

	}

}
