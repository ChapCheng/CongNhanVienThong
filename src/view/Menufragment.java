package view;

import java.util.ArrayList;

import view.appmenu.Item;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.congnhanvienthong.R;

public class Menufragment extends SherlockFragment {
	ListView list;
	MenuClickInterFace mClick;
	ListView listview = null;
	ArrayList<Item> items = new ArrayList<Item>();

	public interface MenuClickInterFace {
		void onListitemClick(String item);

	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		mClick = (MenuClickInterFace) activity;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.menulist, container, false);
		return v;
	}

}
