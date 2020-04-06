package address_book;

//姓名、电话、手机、电子邮箱、
//生日、相片、工作单位 、家庭地址、邮编、所属组、备注 
//所属组不能超过10000个

public class PeopleTable {
	private String name = new String();  //姓名
	private String telephone = new String();  //电话
	private String phone= new String();  //手机
	private String email = new String();  //电子邮箱
	private String birthday = new String();  //生日 
	private String workplace = new String();  //工作地
	private String homeaddres = new String();  //家庭地址
	private String postcode = new String();  //邮编
	private String[] group = new String[10000];  //所属组，至少要有一个
	private String note = new String();  //备注
	private String photopath = new String();  //照片的地址
	private String filename = new String();
	
	public PeopleTable() {
	}
		
	public String getTelephone() {
		return telephone;
	}

	public String getPhotopath() {
		return photopath;
	}

	public void setPhotopath(String photopath) {
		this.photopath = photopath;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getWorkplace() {
		return workplace;
	}

	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}

	public String getHomeaddres() {
		return homeaddres;
	}

	public void setHomeaddres(String homeaddres) {
		this.homeaddres = homeaddres;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String[] getGroup() {
		return group;
	}

	public void setGroup(String[] group) {
		this.group = group;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
