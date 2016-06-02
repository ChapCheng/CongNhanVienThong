package webservice.kdb;

import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import congnhanvienthong.entity.kdb.ChiTieuBSC;
import control.Util;
import webservice.BaseTask;

public class LayDuLieuBaoCaoBscTask extends BaseTask {
	public LayDuLieuBaoCaoBscTask() {
		// TODO Auto-generated constructor stub
		super();
		WSDL = "http://123.16.191.37/wsvnpt360/KhoanDiaBan_TraCuu360.asmx?WSDL";
		METHOD_NAME = "LayDuLieuBaoCaoBsc";
		SOAP_ACTION = "http://tempuri.org/LayDuLieuBaoCaoBsc";
		NAMESPACE = "http://tempuri.org/";
	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		SoapObject lstObject = (SoapObject) ((SoapObject) result).getProperty("Data");
		ArrayList<ChiTieuBSC> lst = Util.GetListData(lstObject, ChiTieuBSC.class, false);
		for (ChiTieuBSC chiTieuBSC : lst) {
			chiTieuBSC.setGiaTri();
		}
		return lst;
	}

}
