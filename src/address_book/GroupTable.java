package address_book;

import java.util.ArrayList;
import java.util.List;

public class GroupTable {
	private String group = new String();
	private List<String> name = new ArrayList<String>();
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public void setName(List<String> name) {
		this.name = name;
	}
	public List<String> getName() {
		return name;
	}
}
