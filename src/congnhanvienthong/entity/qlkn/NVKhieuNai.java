package congnhanvienthong.entity.qlkn;


//@AnnotationClass(id = "ID", name = "Name")
public class NVKhieuNai {
	private String ID;
	private String Name;
	
	
	
	public NVKhieuNai(String p_ID, String p_Name) {
		super();
		this.ID = p_ID;
		this.Name = p_Name;
	}
	public String getID() {
		return ID;
	}
	public void setID(String p_ID) {
		this.ID = p_ID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String p_Name) {
		this.Name = p_Name;
	}
}
