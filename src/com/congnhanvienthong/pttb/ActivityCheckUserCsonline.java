package com.congnhanvienthong.pttb;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import com.congnhanvienthong.ActivityBaseToDisplay;
import com.congnhanvienthong.R;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import control.Util;
import webservice.BaseTask;
import webservice.LoginTask;
import webservice.WebProtocol;
import webservice.csonline.CheckUserCsOnlineTask;
import webservice.csonline.UpdateXTTTCsOnlineTask;

@SuppressLint("DefaultLocale")
public class ActivityCheckUserCsonline extends ActivityBaseToDisplay {
	EditText txtUserName, txtPass;
	String userHNI;
	LoginTask loginTask;
	CheckUserCsOnlineTask checkUserTask;
	UpdateXTTTCsOnlineTask updateUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		checkUserTask = new CheckUserCsOnlineTask();
		checkUserTask.input.add(Util.userName);
		onExecuteToServer(true, null, checkUserTask);
	}

	@Override
	public void onsucces(WebProtocol task) {
		// TODO Auto-generated method stub
		super.onsucces(task);
		if (task instanceof CheckUserCsOnlineTask) {
			String res = ((SoapObject) checkUserTask.result).getPropertyAsString("Result");
			if (res.toLowerCase().equals("true")) {
				Intent i = new Intent(context, CSOActivity.class);
				startActivity(i);
			} else {
				Util.showAlert(context, "Để sử dụng chương trình cần đăng nhập với user XTTT Hà Nội ở lần đầu tiên");
				setBodyLayout(R.layout.check_user);
				Button btnXacNhan = (Button) body.findViewById(R.id.bttLogin);
				txtPass = (EditText) body.findViewById(R.id.pass);
				txtUserName = (EditText) body.findViewById(R.id.user);
				btnXacNhan.setOnClickListener(this);
			}

		}
		if (task instanceof LoginTask) {
			SoapObject soapObject = (SoapObject) loginTask.result;

			SoapPrimitive messeage = (SoapPrimitive) soapObject.getProperty("Message");
			SoapPrimitive code = (SoapPrimitive) soapObject.getProperty("Code");

			if (!code.toString().equals("0")) {
				Util.showAlert(context, messeage.toString());
			}

			if (code.toString().equals("0")) {
				updateUser = new UpdateXTTTCsOnlineTask();
				updateUser.input.add(userHNI);
				updateUser.input.add(Util.userName);
				onExecuteToServer(true, null, updateUser);
			}
		}
		if (task instanceof UpdateXTTTCsOnlineTask) {

			SoapObject temp = (SoapObject) updateUser.result;
			if (temp.getPropertyAsString("Result").toLowerCase().equals("true")) {
				Intent i = new Intent(ActivityCheckUserCsonline.this, CSOActivity.class);
				startActivity(i);
			} else {
				Util.showAlert(context, "Thao tác không thành công! Vui lòng thử lại sau");
			}
			System.out.println(temp);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.bttLogin:
			loginTask = new LoginTask();
			loginTask.input.add(txtUserName.getText().toString());
			loginTask.input.add(txtPass.getText().toString());
			if (txtPass.getText().toString().equals("") || txtUserName.getText().toString().equals("")) {
				Util.showAlert(context, "Chưa nhập đủ user, mật khẩu");
			} else {
				userHNI = txtUserName.getText().toString();
				onExecuteToServer(true, null, loginTask);
			}
			break;

		default:
			break;
		}
	}

}
