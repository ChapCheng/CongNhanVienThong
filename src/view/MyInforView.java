package view;

import java.util.HashSet;
import java.util.Set;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MyInforView implements InfoWindowAdapter {

	Context context;

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public HashSet<MarkerOptions> getSetMarkers() {
		return setMarkers;
	}

	public void setSetMarkers(HashSet<MarkerOptions> setMarkers) {
		this.setMarkers = setMarkers;
	}

	HashSet<MarkerOptions> setMarkers;

	@Override
	public View getInfoContents(Marker arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public View getInfoWindow(Marker marker) {
		// TODO Auto-generated method stub
		LinearLayout info = new LinearLayout(context);
		info.setOrientation(LinearLayout.VERTICAL);
		
		for (MarkerOptions tempMarker : setMarkers) {
			LatLng markerLatLng = marker.getPosition();
			LatLng setLatLng = tempMarker.getPosition();
			if (setLatLng.latitude == markerLatLng.latitude
					&& setLatLng.longitude == markerLatLng.longitude) {
				TextView title = new TextView(context);
				title.setTextColor(Color.BLACK);
				title.setGravity(Gravity.CENTER);
				title.setTypeface(null, Typeface.BOLD);
				title.setText(marker.getTitle());
				TextView snippet = new TextView(context);
				snippet.setTextColor(Color.GRAY);
				snippet.setText(marker.getSnippet());
				info.addView(title);
				info.addView(snippet);
			}
		}

		return info;
	}

}
