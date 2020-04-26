package address_book;

public class GroupTable {
	private String group = new String();
	private String[] name = new String[10000];
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String[] getName() {
		return name;
	}
	public void setName(String[] name) {
		this.name = name;
	}
}
