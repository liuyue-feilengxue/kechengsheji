package address_book;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinYin {
	 
    /**
     * ������ת��Ϊȫƴ
     * @param src
     * @return
     */
    public static String getPinYin(String src){
        char[] hz = null;
        hz = src.toCharArray();//�÷����������Ƿ���һ���ַ����飬���ַ������д���˵�ǰ�ַ����е������ַ�
        String[] py = new String[hz.length];//�����������洢
        //���ú���ƴ������ĸ�ʽ
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        
        String pys = ""; //���ƴ���ַ���
        int len = hz.length;
        
        try {
            for (int i = 0; i < len ; i++ ){
                //���ж��Ƿ�Ϊ�����ַ�
                if(Character.toString(hz[i]).matches("[\\u4E00-\\u9FA5]+")){
                    //�����ֵļ���ȫƴ���浽py������
                    py = PinyinHelper.toHanyuPinyinStringArray(hz[i],format);
                    //ȡ���ĺ���ȫƴ�ĵ�һ�ֶ���������ŵ��ַ���pys��
                    pys += py[0];
                }else{
                    //������Ǻ����ַ������ȡ���ַ������ӵ� pys ��
                    pys += Character.toString(hz[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e){
            e.printStackTrace();
        }
        return pys;
    }
 
    /**
     * ��ȡÿ�����ֵ�����ĸ
     * @param str
     * @return
     */
    public static String getPinYinHeadCharUpper(String str){
        String convert = "";
        for (int i = 0; i < str.length(); i++) {
             char word = str.charAt(i);
             //��ȡ���ֵ�����ĸ
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null){
                convert += pinyinArray[0].charAt(0);
            }else{
                convert += word;
            }
        }
        return convert.toUpperCase();//���д
    }
    public static String getPinYinHeadCharLower(String str){
        String convert = "";
        for (int i = 0; i < str.length(); i++) {
             char word = str.charAt(i);
             //��ȡ���ֵ�����ĸ
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null){
                convert += pinyinArray[0].charAt(0);
            }else{
                convert += word;
            }
        }
        return convert.toLowerCase();//��Сд
    }
}