package address_book;

import java.io.File;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.*;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class AddressUtil {
	// 联系人数组
	List<PeopleTable> pt = new ArrayList<PeopleTable>();

	// 增加联系人
	public boolean addContactPerson(PeopleTable e) {
		return pt.add(e);
	}

	// 删除联系人
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

	// 修改联系人
	public boolean changeContactPerson(int index, PeopleTable e) {
		if (index <= pt.size() && index >= 0) {
			pt.set(index, e);
			return true;
		} else {
			return false;
		}
	}

	// 查询联系人（未完成）
	public boolean checkContactPerson() {
		return true;
	}

	// txt导出操作
	public void txtwrite() {
		JFileChooser jf = new JFileChooser();
		jf.setFileSelectionMode(JFileChooser.SAVE_DIALOG | JFileChooser.DIRECTORIES_ONLY);
		jf.showDialog(null, null);
		File fi = jf.getSelectedFile();
		String f = fi.getAbsolutePath() + "\\通讯录.txt";
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
				// 把它变成[数据,数据,数据]的string，再存入
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
			System.out.println("txt导出成功");
		} catch (Exception e) {
			System.out.println("txt导出失败");
		}
	}

	// txt导入操作（未完成）
	public void txtread() {
		JFileChooser fd = new JFileChooser();
		fd.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fd.setFileFilter(new FileFilter() {
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
		
	}

	// csv导出操作（未测试）
	public void csvwrite() {
		try {
			CsvFileFilterTest fileFilter = new CsvFileFilterTest(); // 创建过滤器对象
			JFileChooser fd = new JFileChooser();
			fd.setFileFilter(fileFilter);// 创建一个csv的文件过滤器
			fd.showOpenDialog(null);
			String filePath = fd.getSelectedFile().getAbsolutePath(); // 获得文件路径
			String filename = fd.getSelectedFile().getName(); // 获得文件名
			CsvWriter csvWriter = new CsvWriter(filePath, ',', Charset.forName("GBK"));
			// 姓名、电话、手机、电子邮箱、
			// 生日、相片、工作单位 、家庭地址、邮编、所属组、备注
			String[] headers = { "姓名", "电话", "手机", "电子邮箱", "生日", "相片", "工作单位", "家庭地址", "邮编", "所属组", "备注" };

			csvWriter.writeRecord(headers);
			// 写到csv里面

			String[][] data = new String[pt.size()][];
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
				// 把它变成[数据,数据,数据]的string，再存入
				String[] grouptemp = pttemp.getGroup();
				String group = grouptemp.toString();
				data[i][9] = group;
				data[i][10] = pttemp.getNote();
				i++;
			}

			for (int j = 0; i < data.length; i++) {
				csvWriter.writeRecord(data[j]);
			}
			csvWriter.close();

		} catch (Exception e) {
			System.out.println("导出失败");
		}
	}

	// csv导入
	public void csvread() {
		// 导入操作
		try {
			if (!pt.isEmpty()) {
				pt.clear();// 清空数据
			}
			JFileChooser fd = new JFileChooser();
			// 创建csv的文件选择器
			CsvFileFilterTest ff = new CsvFileFilterTest();
			fd.setFileFilter(ff);
			// 显示选择文件框
			fd.showOpenDialog(null);
			String filePath = fd.getSelectedFile().getAbsolutePath();

			// 创建CSV读对象,utf-8来读取
			CsvReader csvReader = new CsvReader(filePath, ',', Charset.forName("GBK"));
			// 读表头
			csvReader.readHeaders();
			int i = 0;
			String data[][] = new String[10000][];

			while (csvReader.readRecord() && i < 10000) {
				// 读一整行
				String str = csvReader.getRawRecord();
				data[i] = str.split("\\,");

				PeopleTable e = new PeopleTable();
				// 姓名、电话、手机、电子邮箱、
				// 生日、相片、工作单位 、家庭地址、邮编、所属组、备注
				e.setName(data[i][0]);
				e.setTelephone(data[i][1]);
				e.setPhone(data[i][2]);
				e.setEmail(data[i][3]);
				e.setBirthday(data[i][4]);
				e.setPhotopath(data[i][5]);
				e.setWorkplace(data[i][6]);
				e.setHomeaddres(data[i][7]);
				e.setPostcode(data[i][8]);
				// 一维数组变二维数组
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
				pt.add(e); // 把数据放入数组中
				i++;
			}
			System.out.println("导入成功");

//			for(int j=0;j<pt.size();j++) {
//				System.out.println(pt.get(0).getName());
//			}
		} catch (Exception e) {
			System.out.println("导入失败");
		}
	}
}
