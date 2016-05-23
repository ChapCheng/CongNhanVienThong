package congnhanvienthong.entity;

//dùng để lưu các module do người dùng chọn, đưa lên shortcut của màn hình chương trình
public class ThongTinModule {
	private String userName;
	private String moduleClassPath_1;
	private String moduleClassPath_2;
	private String moduleClassPath_3;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getModuleClassPath_1() {
		return moduleClassPath_1;
	}

	public void setModuleClassPath_1(String moduleClassPath) {
		this.moduleClassPath_1 = moduleClassPath;
	}

	



	public String getModuleClassPath_2() {
		return moduleClassPath_2;
	}

	public void setModuleClassPath_2(String moduleClassPath_2) {
		this.moduleClassPath_2 = moduleClassPath_2;
	}



	public String getModuleClassPath_3() {
		return moduleClassPath_3;
	}

	public void setModuleClassPath_3(String moduleClassPath_3) {
		this.moduleClassPath_3 = moduleClassPath_3;
	}





	public ThongTinModule(String userName, 
			String moduleClassPath_1, 
			String moduleClassPath_2,
			String moduleClassPath_3) {
		super();
		this.userName = userName;
		this.moduleClassPath_1 = moduleClassPath_1;
		this.moduleClassPath_2 = moduleClassPath_2;
		this.moduleClassPath_3 = moduleClassPath_3;
	}

	public ThongTinModule() {
		// TODO Auto-generated constructor stub
	}

	

}
