package congnhanvienthong.entity;

import java.util.ArrayList;

public class ListApiResult<T> implements ApiResultApi {
	String IsError;
	String Message;
	ArrayList<T> Data;

	public ListApiResult() {
		// TODO Auto-generated constructor stub
		this.IsError = "";
		this.Message = "";
		this.Data = new ArrayList<T>();
	}

	public String getIsError() {
		return IsError;
	}

	public void setIsError(String isError) {
		IsError = isError;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public ArrayList<T> getData() {
		return Data;
	}

	public void setData(ArrayList<T> data) {
		Data = data;
	}

}
