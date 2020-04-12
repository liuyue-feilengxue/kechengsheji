package address_book;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.*;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;


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
//			String[][] data = new String[pt.size()][];
//			int i = 0;
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
}
