package congnhanvienthong.entity;

import android.graphics.Bitmap;

public class User {
	private String name;
	private String tel;
	private String soThe;
	private String donVi;
	private String CMT;
	private String email;
	private Bitmap avatar;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getSoThe() {
		return soThe;
	}
	public void setSoThe(String soThe) {
		this.soThe = soThe;
	}
	public String getDonVi() {
		return donVi;
	}
	public void setDonVi(String donVi) {
		this.donVi = donVi;
	}
	public String getCMT() {
		return CMT;
	}
	public void setCMT(String cMT) {
		CMT = cMT;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Bitmap getAvatar() {
		return avatar;
	}
	public void setAvatar(Bitmap avatar) {
		this.avatar = avatar;
	}
	
	

}
