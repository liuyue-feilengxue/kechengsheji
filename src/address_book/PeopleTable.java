package address_book;

import java.awt.Image;

//姓名、电话、手机、电子邮箱、
//生日、相片、工作单位 、家庭地址、邮编、所属组、备注 
//最大限制10000个联系人

public class PeopleTable {
	private String[] name = new String[10000];  //姓名
	private String[] telephone = new String[10000];  //电话
	private String[] phone= new String[10000];  //手机
	private String[] email = new String[10000];  //电子邮箱
	private String[] birthday = new String[10000];  //生日
	private Image[] photo = new Image[10000];  //照片
	private String[] workplace = new String[10000];  //工作地
	private String[] homeaddres = new String[10000];  //家庭地址
	private String[] postcode = new String[10000];  //邮编
	private String[][] group = new String[10000][];  //所属组
	private String[] note = new String[10000];  //备注
	private int length=0;
	private String filename = new String();
	
	public PeopleTable() {
	}
	
	
	public void add(String[] name,String[] telephone,
			String[] phone,String[] email,String[] birthday,
			Image[] photo,String[] workplace,String[] homeaddres
			,String[] postcode,String[][] group,String[] note
			,String filename,int length) {
		
		for (int i = 0 ; i < length;i++) {
			this.name[i] = name[i];
			this.telephone[i] = telephone[i];
			this.phone[i] = phone[i];
			this.email[i]=email[i];
			this.birthday[i]=birthday[i];
			this.photo[i]=photo[i];
			this.workplace[i]=workplace[i];
			this.homeaddres[i]=homeaddres[i];
			this.postcode[i]=postcode[i];
			for (int j=0;j<group[i].length;j++) {
				this.group[i][j] = group[i][j];
			}
			this.note[i]=note[i];
		}
		this.length = length;
		this.filename = filename;
	}
		
	public void clear() {
		for (int i=0;i<this.length;i++) {
			this.name[i] = null;
			this.telephone[i] =  null;
			this.phone[i] =  null;
			this.email[i]= null;
			this.birthday[i]= null;
			this.photo[i]= null;
			this.workplace[i]= null;
			this.homeaddres[i]= null;
			this.postcode[i]= null;
			this.group[i]= null;
			this.note[i]= null;
		}
		this.length = 0;
		this.filename = null;
	}
	
	public int getlength() {
		return this.length;
	}
	
	public void setName(String name,int location) {
		this.name[location]=name;
	}
}
