package congnhanvienthong.entity.dhsc;

import java.util.ArrayList;

import control.AnnotationField;

public class ThongTinVissaFiberVNN {
	long AccountID ;
	
	int TariffType ;
	@AnnotationField(hienthi = true, order = 0, tenNhan = "Tài khoản")
	String AccountName ;
	String  AdslCode ;
	String  PaymentForm ;
	String  ActiveDate ;
	String  Address ;
	String  InstallAddress ;
	String  Admin ;
	
	@AnnotationField(hienthi = true, order = 1, tenNhan = "BrasIP")
	String  BrasIP ;
	
	
	@AnnotationField(hienthi = true, order = 2, tenNhan = "BrasName")
	String  BrasName ;
	
	
	String  ChargeType ;
	String  City ;
	String  ContractNo ;
	int  CustomerType ;
	String  DomainName ;
	@AnnotationField(hienthi = true, order = 3, tenNhan = "DSLAM")
	String  DSLAM ;
	@AnnotationField(hienthi = true, order = 4, tenNhan = "DslamId ")
	int  DslamId ;
	String  EXTService ;
	
	String  FrameIPAddress ;
	@AnnotationField(hienthi = true, order = 5, tenNhan = "Mail")
	String  Mail ;
	@AnnotationField(hienthi = true, order = 6, tenNhan = "Tên khách hàng")
	String  Name ;
	String  NewName ;
	String  OldName ;
	int  OutputCode ;
	@AnnotationField(hienthi = true, order = 7, tenNhan = "Slot/Port/Svlan/Cvlan")
	//Slot/Port/Svlan/Cvlan
	String  Parameter ;
	String  Phone ;
	String  ContactPhone ;
	String  Quota ;
	@AnnotationField(hienthi = true, order = 8, tenNhan = "Dịch vụ đang sử dụng")
	String  Service ;
	String  ServiceType ;
	@AnnotationField(hienthi = true, order = 9, tenNhan = "Tình trạng")
	String  Status ;
	String  TimeCreate ;
	String  TimeModify ;
	String  TypeID ;
	
	String  SlotPort ;
	@AnnotationField(hienthi = true, order = 10, tenNhan = "NasportId")
	String  NasportId ;
	
	@AnnotationField(hienthi = true, order = 11, tenNhan = "Log tác động vissa")
	ArrayList<VissaLogFiberAndMega> VisaLogs;

	public ArrayList<VissaLogFiberAndMega> getVisaLogs() {
		return VisaLogs;
	}

	public void setVisaLogs(ArrayList<VissaLogFiberAndMega> visaLogs) {
		VisaLogs = visaLogs;
	} 

}
