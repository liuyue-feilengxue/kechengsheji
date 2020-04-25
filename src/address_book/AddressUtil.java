package address_book;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.*;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import ezvcard.io.text.VCardReader;
import ezvcard.property.Address;
import ezvcard.property.Birthday;
import ezvcard.property.Email;
import ezvcard.property.FormattedName;
import ezvcard.property.Telephone;
import net.sourceforge.cardme.io.CompatibilityMode;
import net.sourceforge.cardme.io.VCardWriter;
import net.sourceforge.cardme.util.Util;
import net.sourceforge.cardme.vcard.VCardImpl;
import net.sourceforge.cardme.vcard.arch.EncodingType;
import net.sourceforge.cardme.vcard.arch.ParameterTypeStyle;
import net.sourceforge.cardme.vcard.arch.VCardVersion;
import net.sourceforge.cardme.vcard.types.AdrType;
import net.sourceforge.cardme.vcard.types.BDayType;
import net.sourceforge.cardme.vcard.types.EmailType;
import net.sourceforge.cardme.vcard.types.ExtendedType;
import net.sourceforge.cardme.vcard.types.FNType;
import net.sourceforge.cardme.vcard.types.NoteType;
import net.sourceforge.cardme.vcard.types.PhotoType;
import net.sourceforge.cardme.vcard.types.TelType;
import net.sourceforge.cardme.vcard.types.media.ImageMediaType;
import net.sourceforge.cardme.vcard.types.params.AdrParamType;
import net.sourceforge.cardme.vcard.types.params.TelParamType;


public class AddressUtil {
	// ��ϵ������
	List<PeopleTable> pt = new ArrayList<PeopleTable>();

	// ������ϵ��
	public boolean addContactPerson(PeopleTable e) {
		return pt.add(e);
	}

	// ɾ����ϵ��
	public boolean removeContactPerson(PeopleTable e) {
		int length = pt.size();
		for (int i = length - 1; i >= 0; i--) {
			PeopleTable tem = pt.get(i);
			if (e.equals(tem)) {
				pt.remove(tem);
				return true;
			}
		}
		return false;
	}

	// �޸���ϵ��
	public boolean changeContactPerson(int index, PeopleTable e) {
		if (index <= pt.size() && index >= 0) {
			pt.set(index, e);
			return true;
		} else {
			return false;
		}
	}

	// ��ѯ��ϵ�ˣ�δ��ɣ�
	public boolean checkContactPerson() {
		return true;
	}

	// txt��������
	public void txtwrite() {
		JFileChooser jf = new JFileChooser();
		jf.setFileSelectionMode(JFileChooser.SAVE_DIALOG | JFileChooser.DIRECTORIES_ONLY);
		jf.showDialog(null, null);
		File fi = jf.getSelectedFile();
		String f = fi.getAbsolutePath() + "\\ͨѶ¼.txt";
		try {
			FileWriter out = new FileWriter(f);
			for (PeopleTable pttemp : pt) {
				out.write("name:" + pttemp.getName()+"\n");
				out.write("telephone:"+pttemp.getTelephone()+"\n");
				out.write("phone:"+pttemp.getPhone()+"\n");
				out.write("email:"+pttemp.getEmail()+"\n");
				out.write("birthday:"+pttemp.getBirthday()+"\n");
				out.write("photopath:"+pttemp.getPhotopath()+"\n");
				out.write("workplace:"+pttemp.getWorkplace()+"\n");
				out.write("homeaddress:"+pttemp.getHomeaddres()+"\n");
				out.write("postcode:"+pttemp.getPostcode()+"\n");
				// �������[����,����,����]��string���ٴ���
				String[] grouptemp = pttemp.getGroup();
				String group = new String();
				group = "[";
				for (int temp=0;temp<grouptemp.length;temp++) {
					if (temp ==0) {
						group = group + grouptemp[temp];
					}
					else {
						group = group + ","+grouptemp[temp];
					}
				}
				group = group +"]";
				out.write("group:"+group+"\n");
				out.write("note:"+pttemp.getNote()+"\n");
			}
			out.close();
			System.out.println("txt�����ɹ�");
		} catch (Exception e) {
			System.out.println("txt����ʧ��");
		}
	}

	// txt�������
	public void txtread() {
		JFileChooser fd = new JFileChooser();
		fd.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fd.setFileFilter(new FileFilter() {

			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return ".txt";
			}

			@Override
			public boolean accept(File f) {
				// TODO Auto-generated method stub
				if (f.getName().endsWith(".txt") || f.isDirectory()) {
					return true;
				} else {
					return false;
				}

			}
		});
		fd.showOpenDialog(null);
		File f = fd.getSelectedFile();
		try {
			FileReader reader = new FileReader(f);
			BufferedReader bf = new BufferedReader(reader);
			String str;
			while ((str = bf.readLine()) != null) {
				if (null != str || !str.trim().equals("")) {
					PeopleTable pttemp = new PeopleTable();
					// ����ֵ����-1������
					if (str.indexOf("name:") == 0) {
						String name = str.replace("name:", "");
						pttemp.setName(name);
						str = bf.readLine();
					}
					if (str.indexOf("telephone:")==0) {
						String telephone = str.replace("telephone:", "");
						pttemp.setTelephone(telephone);
						str =bf.readLine();
					}
					if (str.indexOf("phone:")==0) {
						String phone = str.replace("phone:", "");
						pttemp.setPhone(phone);
						str=bf.readLine();
					}
					if(str.indexOf("email:")==0) {
						String email = str.replace("email:", "");
						pttemp.setEmail(email);
						str = bf.readLine();
					}
					if(str.indexOf("birthday:")==0) {
						String birthday = str.replace("birthday:", "");
						pttemp.setBirthday(birthday);
						str = bf.readLine();
					}
					if(str.indexOf("photopath:")==0) {
						String photopath = str.replace("photopath:", "");
						pttemp.setPhotopath(photopath);
						str = bf.readLine();
					}
					if(str.indexOf("workplace:")==0) {
						String workplace = str.replace("workplace:", "");
						pttemp.setWorkplace(workplace);;
						str = bf.readLine();
					}
					if(str.indexOf("homeaddress:")==0) {
						String homeaddress = str.replace("homeaddress:", "");
						pttemp.setHomeaddres(homeaddress);
						str = bf.readLine();
					}
					if (str.indexOf("postcode:")==0) {
						String postcode = str.replace("postcode:", "");
						pttemp.setPostcode(postcode);
						str = bf.readLine();
					}
					if (str.indexOf("group:")==0) {
						String tempstr = str.replace("group:", "");
						String[] group = new String[100];
						if (tempstr != "") {
							tempstr = tempstr.substring(1, tempstr.length() - 1);
							group = tempstr.split(",");
						} else {
							group = null;
						}
						pttemp.setGroup(group);
						str = bf.readLine();
					}
					if (str.indexOf("note:")==0) {
						String note = str.replace("note:", "");
						pttemp.setNote(note);
					}
					pt.add(pttemp);
				}
			}
			// �ر���
			bf.close();
			reader.close();
			System.out.println("����ɹ�");
		} catch (Exception e) {
			System.out.println("����ʧ��");
		}
	}

	// csv��������
	public void csvwrite() {
		JFileChooser jf = new JFileChooser();
//		jf.setFileSelectionMode(JFileChooser.SAVE_DIALOG | JFileChooser.DIRECTORIES_ONLY);
		jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jf.showDialog(null, null);
		File fi = jf.getSelectedFile();
		String f = fi.getAbsolutePath() + "\\ͨѶ¼.csv";
		try {
			//E:\eclipse2019\eclipse2019_downcc.com\path\javafx
			System.out.println(f);
			CsvWriter csvWriter = new CsvWriter(f, ',', Charset.forName("GBK"));
			String[] headers = { "����", "�绰", "�ֻ�", "��������", "����", "��Ƭ", "������λ", "��ͥ��ַ", "�ʱ�", "������", "��ע" };
			csvWriter.writeRecord(headers);
			// д��csv����
			String[][] data = new String[pt.size()][11];
			int i = 0;
			for (PeopleTable pttemp : pt) {
				data[i][0] = pttemp.getName();
				data[i][1] = pttemp.getTelephone();
				data[i][2] = pttemp.getPhone();
				data[i][3] = pttemp.getEmail();
				data[i][4] = pttemp.getBirthday();
				data[i][5] = pttemp.getPhotopath();
				data[i][6] = pttemp.getWorkplace();
				data[i][7] = pttemp.getHomeaddres();
				data[i][8] = pttemp.getPostcode();
				// �������[����,����,����]��string���ٴ���
				
				String[] grouptemp = pttemp.getGroup();
				String group = new String();
				group = "[";
				for (int temp=0;temp<grouptemp.length;temp++) {
					if (temp ==0) {
						group = group + grouptemp[temp];
					}
					else {
						group = group + ","+grouptemp[temp];
					}
				}
				group = group +"]";
				data[i][9] = group;
				data[i][10] = pttemp.getNote();
				
				i++;
			}
			for (int j = 0; j < data.length; j++) {
				csvWriter.writeRecord(data[j]);
			}
			csvWriter.close();
			System.out.println("�����ɹ�");
		} catch (Exception e) {
			System.out.println("����ʧ��");
		}
	}

	// csv����
	public void csvread() {
		// �������
		try {
			if (!pt.isEmpty()) {
				pt.clear();// �������
			}
			JFileChooser fd = new JFileChooser();
			// ����csv���ļ�ѡ����
			CsvFileFilterTest ff = new CsvFileFilterTest();
			fd.setFileFilter(ff);
			// ��ʾѡ���ļ���
			fd.showOpenDialog(null);
			String filePath = fd.getSelectedFile().getAbsolutePath();

			// ����CSV������,utf-8����ȡ
			CsvReader csvReader = new CsvReader(filePath, ',', Charset.forName("GBK"));
			// ����ͷ
			csvReader.readHeaders();
			int i = 0;
			String data[][] = new String[10000][];

			while (csvReader.readRecord() && i < 10000) {
				// ��һ����
				String str = csvReader.getRawRecord();
				data[i] = str.split("\\,");

				PeopleTable e = new PeopleTable();
				// �������绰���ֻ����������䡢
				// ���ա���Ƭ��������λ ����ͥ��ַ���ʱࡢ�����顢��ע
				e.setName(data[i][0]);
				e.setTelephone(data[i][1]);
				e.setPhone(data[i][2]);
				e.setEmail(data[i][3]);
				e.setBirthday(data[i][4]);
				e.setPhotopath(data[i][5]);
				e.setWorkplace(data[i][6]);
				e.setHomeaddres(data[i][7]);
				e.setPostcode(data[i][8]);
				// һά������ά����
				String tempstr = data[i][9];
				String[] group = new String[100];
				if (tempstr != "") {
					tempstr = tempstr.substring(1, tempstr.length() - 1);
					group = tempstr.split(",");
				} else {
					group = null;
				}
				e.setGroup(group);
				e.setNote(data[i][10]);
				pt.add(e); // �����ݷ���������
				i++;
			}
			System.out.println("����ɹ�");

//			for(int j=0;j<pt.size();j++) {
//				System.out.println(pt.get(0).getName());
//			}
		} catch (Exception e) {
			System.out.println("����ʧ��");
		}
	}
	public void vcardread() {
		JFileChooser fd = new JFileChooser();
		fd.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fd.setFileFilter(new FileFilter() {

			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return ".vcf";
			}

			@Override
			public boolean accept(File f) {
				if (f.getName().endsWith(".vcf") || f.isDirectory()) {
					return true;
				} else {
					return false;
				}
			}
		});
		fd.showOpenDialog(null);
		File file = new File(fd.getSelectedFile().getAbsolutePath());
		try {
			VCardReader reader = new VCardReader(file);
			ezvcard.VCard vcard;
			while ((vcard = reader.readNext()) != null) {
				PeopleTable pttemp = new PeopleTable();
				// ����
				FormattedName fn = vcard.getFormattedName();
				pttemp.setName(fn.getValue());
				// ��ͥ�绰
				Telephone tel = vcard.getTelephoneNumbers().get(0);
				pttemp.setTelephone(tel.getText());
				// �ֻ�
				Telephone phone = vcard.getTelephoneNumbers().get(1);
				pttemp.setPhone(phone.getText());
				// ��������
				Email email = vcard.getEmails().get(0);
				pttemp.setEmail(email.getValue());
				// ����
				DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
				Birthday bday = vcard.getBirthday();
				Date date = bday.getDate();
				date.setDate(date.getDate() - 1);
				String birthday = df.format(date);
				pttemp.setBirthday(birthday);
				// ������ַ
				Address addr = vcard.getAddresses().get(0);
				pttemp.setWorkplace(addr.getStreetAddress());
				// ��ͥ��ַ
				Address homeaddr = vcard.getAddresses().get(1);
				pttemp.setHomeaddres(homeaddr.getStreetAddress());
				// �ʱ�
				pttemp.setPostcode(homeaddr.getPostalCode());
				// ������
				String tempstr = vcard.getExtendedProperties().get(1).getValue();
				String[] group = new String[100];
				if (tempstr != "") {
					tempstr = tempstr.substring(1, tempstr.length() - 1);
					group = tempstr.split(",");
				} else {
					group = null;
				}
				for (int i = 0; i < group.length - 1; i++) {
					group[i] = group[i].substring(0, group[i].length() - 1);
				}
				pttemp.setGroup(group);
				// ͼƬλ��
				pttemp.setPhotopath(vcard.getExtendedProperties().get(0).getValue());
				// ��ע
				pttemp.setNote(vcard.getNotes().get(0).getValue());
				pt.add(pttemp);
			}
			reader.close();
			System.out.println("vcf����ɹ�");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error");
		}
	}
	public void vcardwrite() {
		try {
			JFileChooser jf = new JFileChooser();
			jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			jf.showDialog(null, null);
			File fi = jf.getSelectedFile();
			String f = fi.getAbsolutePath() + "\\ͨѶ¼.vcf";
			VCardWriter writer = new VCardWriter(VCardVersion.V3_0, CompatibilityMode.RFC2426);// �û���VCardת��Ϊ�ַ�
			FileWriter fw = new FileWriter(new File(f));// ��VCard���ݣ��ַ���д���ļ�
			
			String[] str = new String[pt.size()+1];
			int i = 0;
			for (PeopleTable pttemp : pt) {
				VCardImpl vc = new VCardImpl();// ����һ����Ƭ
				// ����
				vc.setFN(new FNType(pttemp.getName()));
				// ��ͥ�绰
				TelType hometel = new TelType();
				hometel.setCharset("UTF-8");
				hometel.setTelephone(pttemp.getTelephone());
				hometel.addParam(TelParamType.HOME);
				hometel.setParameterTypeStyle(ParameterTypeStyle.PARAMETER_VALUE_LIST);
				vc.addTel(hometel);
				// �ֻ�
				TelType phone = new TelType();
				phone.setCharset("UTF-8");
				phone.setTelephone(pttemp.getPhone());
				phone.addParam(TelParamType.CELL);
				phone.setParameterTypeStyle(ParameterTypeStyle.PARAMETER_VALUE_LIST);
				vc.addTel(phone);
				// ��������
				vc.addEmail(new EmailType(pttemp.getEmail()));
				// ����
				Calendar birthday = Calendar.getInstance();
				birthday.clear();
				birthday.set(Calendar.YEAR, 2000);
				birthday.set(Calendar.MONTH, 10 - 1);
				birthday.set(Calendar.DATE, 1 + 1);
				vc.setBDay(new BDayType(birthday));
				// ��Ƭ
				ExtendedType xphotopath = new ExtendedType("X-Photopath", pttemp.getPhotopath());
				vc.addExtendedType(xphotopath);

				// ������λ
				AdrType address2 = new AdrType();
				address2.setCharset("UTF-8");
				address2.setExtendedAddress("");
				address2.setStreetAddress(pttemp.getWorkplace());
				address2.addParam(AdrParamType.WORK);
				vc.addAdr(address2);
				// ��ͥסַ
				AdrType address1 = new AdrType();
				address1.setCharset("UTF-8");
				address1.setExtendedAddress("");
				address1.setStreetAddress(pttemp.getHomeaddres());
				address1.setPostalCode("123456");// �ʱ�
				address1.addParam(AdrParamType.HOME);
				vc.addAdr(address1);

				// ������
				// �������[����,����,����]��string���ٴ���
				String[] grouptemp = pttemp.getGroup();
				String group = new String();
				group = "[";
				for (int temp = 0; temp < grouptemp.length; temp++) {
					if (temp == 0) {
						group = group + grouptemp[temp];
					} else {
						group = group + "," + grouptemp[temp];
					}
				}
				group = group + "]";
				ExtendedType xGroup = new ExtendedType("X-Group", group);
				vc.addExtendedType(xGroup);

				// ��ע
				NoteType note = new NoteType();
				note.setNote(pttemp.getNote());
				vc.addNote(note);
				writer.setVCard(vc);
				str[i] = writer.buildVCardString();// ����Ƭ����ת��Ϊ�ַ�
				i++;
			}
			String vcString = new String();
			for (int j = 0; j < str.length-1; j++) {
				vcString = vcString + str[j];
			}
			fw.append(vcString);// д���ļ�
			fw.flush();
			fw.close();
			System.out.println("vcf�����ɹ�");
		} catch (Exception e) {
			System.out.println("vcf����ʧ��");
		}
	}
}
