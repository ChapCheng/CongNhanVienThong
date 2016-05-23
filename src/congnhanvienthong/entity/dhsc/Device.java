package congnhanvienthong.entity.dhsc;
//thinp
import com.google.android.gms.maps.model.LatLng;

public class Device {
	private String deciveName;
	private LatLng toado;
	private String deciveRange;

	public String getDeciveName() {
		return deciveName;
	}

	public void setDeciveName(String deciveName) {
		this.deciveName = deciveName;
	}

	public String getDeciveRange() {
		return deciveRange;
	}

	public void setDeciveRange(String deciveRange) {
		this.deciveRange = deciveRange;
	}

	public LatLng getToado() {
		return toado;
	}

	public void setToado(LatLng toado) {
		this.toado = toado;
	}

	public Device(String deciveName, LatLng toado, String deciveRange) {
		super();
		this.deciveName = deciveName;
		this.toado = toado;
		this.deciveRange = deciveRange;
	}

	public Device() {
	}

}
