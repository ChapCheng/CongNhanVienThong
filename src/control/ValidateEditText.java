package control;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;

public class ValidateEditText implements TextWatcher {

	int validateType;
	Context context;
	String name;

	
	
	public ValidateEditText(int validateType, Context context, String name) {
		super();
		this.validateType = validateType;
		this.context = context;
		this.name = name;
	}

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		if (s.length() == 0)
		  {
			Util.showAlert(context, "Chưa nhập " + name );
			return;
		  }

	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		if (s.length() == 0)
		  {
			Util.showAlert(context, "Chưa nhập " + name );
			return;
		  }
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		if (s.length() == 0)
		  {
			Util.showAlert(context, "Chưa nhập " + name );
			return;
		  }
	}

}
