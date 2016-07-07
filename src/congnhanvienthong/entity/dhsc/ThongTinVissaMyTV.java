package congnhanvienthong.entity.dhsc;

import control.AnnotationField;

public class ThongTinVissaMyTV {
	@AnnotationField(hienthi = true, order = 1, tenNhan = "Lần đăng nhập cuối")
	String Last_Login;
	@AnnotationField(hienthi = true, order = 2, tenNhan = "IP")
	String IP;
	@AnnotationField(hienthi = true, order = 3, tenNhan = "MAC")
	String MAC;
	@AnnotationField(hienthi = true, order = 4, tenNhan = "Họ và tên")
	String Name;
	@AnnotationField(hienthi = true, order = 5, tenNhan = "Địa chỉ")
	String FirstAdd;
	@AnnotationField(hienthi = true, order = 6, tenNhan = "Mã MyTV")
	String IPTVAccount;
	@AnnotationField(hienthi = true, order = 7, tenNhan = "Gói cước")
	String PackageName;
	@AnnotationField(hienthi = true, order = 8, tenNhan = "Gói cước VASC")
	String PackageID;
	@AnnotationField(hienthi = true, order = 9, tenNhan = "Setupbox ID")
	String STBID;
	@AnnotationField(hienthi = true, order = 10, tenNhan = "Tình trạng")
	String Status;
	@AnnotationField(hienthi = true, order = 11, tenNhan = "Đối tượng khách hàng")
	String CateName;

}
