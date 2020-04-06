package address_book;

import java.io.File;

public class CsvFileFilterTest extends javax.swing.filechooser.FileFilter{

	@Override
	public boolean accept(File f) {
		if (f.isDirectory())return true;
	    return f.getName().endsWith(".csv");  //设置为选择以.csv为后缀的文件
	}

	@Override
	public String getDescription() {
		return ".csv";
	}

}
