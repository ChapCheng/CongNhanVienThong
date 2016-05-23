package view.appmenu;

public class EntryItem implements Item {

	public final String title;
	@SuppressWarnings("rawtypes")
	public final Class subtitle;
	public int icon;

	@SuppressWarnings("rawtypes")
	public EntryItem(String title, Class subtitle, int icon) {
		this.title = title;
		this.subtitle = subtitle;
		this.icon = icon;
	}

	@SuppressWarnings("rawtypes")
	public EntryItem(String title, Class subtitle) {
		this.title = title;
		this.subtitle = subtitle;
		this.icon = 0;
	}

	@Override
	public boolean isSection() {
		return false;
	}

}
