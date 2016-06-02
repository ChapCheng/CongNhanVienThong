package webservice;

import android.content.Context;

public interface WebProtocol {
	void execute();

	Context context = null;

	Void SetConText(Context context);

	Object getResult();

}
