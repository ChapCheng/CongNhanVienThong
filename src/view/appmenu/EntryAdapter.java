package view.appmenu;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.congnhanvienthong.R;

public class EntryAdapter extends ArrayAdapter<Item> {

	@SuppressWarnings("unused")
	private Context context;
	private ArrayList<Item> items;
	private LayoutInflater vi;
	public static Boolean isScrolling = true;

	public EntryAdapter(Context context, ArrayList<Item> items) {
		super(context, 0, items);
		this.context = context;
		this.items = items;
		vi = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		Item i = getItem(position);
		if (convertView == null || convertView.getTag() != getItem(position)) {

			if (i != null) {
				holder = new ViewHolder();
				if (i.isSection()) {
					convertView = vi.inflate(R.layout.list_item_section, null);
					convertView.setOnClickListener(null);
					convertView.setOnLongClickListener(null);
					convertView.setLongClickable(false);

					holder.text = (TextView) convertView
							.findViewById(R.id.list_item_section_text);

				}
				if (!i.isSection()) {
					convertView = vi.inflate(R.layout.list_item_entry, null);
					holder.text = (TextView) convertView
							.findViewById(R.id.list_item_entry_title);
					holder.icon = (ImageView) convertView
							.findViewById(R.id.list_item_entry_drawable);
				}

			}
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (i.isSection()) {
			SectionItem si = (SectionItem) getItem(position);
			holder.text.setText(si.getTitle());

		}
		if (!i.isSection()) {
			EntryItem ei = (EntryItem) getItem(position);

			if (holder.text != null) {
				holder.text.setText(ei.title);
				holder.text.setSelected(true);
			}

			if (holder.icon != null && ei.icon != 0) {
				holder.icon.setImageResource(ei.icon);
			}

		}

		return convertView;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Item getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	static class ViewHolder {
		TextView text;
		ImageView icon;
	}

}
