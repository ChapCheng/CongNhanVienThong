package congnhanvienthong.entity.dhsc;

public class VissaLogFiberAndMega {
	String Action;
	String Description;
	String ModifyDate;
	String UserModify;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String res = "<br>"+"<li><b>Thao tác: </b>" + Action;
		res = res + "<br><b>Mô tả: </b>" + Description;
		res = res + "<br><b>Thời gian  : </b>" + ModifyDate;
		res = res + "<br><b>Người thay đổi : </b>" + UserModify+"</li>";
		
		return res;
	}

	public String getAction() {
		return Action;
	}

	public void setAction(String action) {
		Action = action;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getModifyDate() {
		return ModifyDate;
	}

	public void setModifyDate(String modifyDate) {
		ModifyDate = modifyDate;
	}

	public String getUserModify() {
		return UserModify;
	}

	public void setUserModify(String userModify) {
		UserModify = userModify;
	}

}
