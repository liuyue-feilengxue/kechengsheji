package address_book;

//�������绰���ֻ����������䡢
//���ա���Ƭ��������λ ����ͥ��ַ���ʱࡢ�����顢��ע 
//�����鲻�ܳ���10000��

public class PeopleTable {
	private String name = new String();  //����
	private String telephone = new String();  //�绰
	private String phone= new String();  //�ֻ�
	private String email = new String();  //��������
	private String birthday = new String();  //���� 
	private String workplace = new String();  //������
	private String homeaddres = new String();  //��ͥ��ַ
	private String postcode = new String();  //�ʱ�
	private String[] group = new String[10000];  //�����飬����Ҫ��һ��
	private String note = new String();  //��ע
	private String photopath = new String();  //��Ƭ�ĵ�ַ
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
