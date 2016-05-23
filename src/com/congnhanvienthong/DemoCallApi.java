package com.congnhanvienthong;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import api.ApiTask;
import api.GetLoaiNhaTramApi;
import webservice.WebProtocol;

public class DemoCallApi extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		LayDuLieu layDuLieu = new LayDuLieu();
		GetLoaiNhaTramApi getLoaiNhaTramApi = new GetLoaiNhaTramApi();
		layDuLieu.execute(getLoaiNhaTramApi);
	}

	class LayDuLieu extends AsyncTask<WebProtocol, Void, String> {
		@Override
		protected String doInBackground(WebProtocol... arg0) {
			// TODO Auto-generated method stub
			try {
				arg0[0].execute();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ((ApiTask) arg0[0]).result;

		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}

	}

}
