package congnhanvienthong.entity;

public class ApiResult<T> implements ApiResultApi {
	String IsError;
	String Message;
	T Data;

}
