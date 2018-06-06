package com.rayNotes;

import org.junit.Test;

/**
 * - 字符串是一个特殊的对象。 - 字符串一旦初始化就不可以被改变。
 * 
 * @author ray
 * @since 2018.1
 * @see StringBufferTest
 */
public class StringTest {

	/**
	 * - str在内存中只有一个对象，str1在内存中有两个对象。 - test01()
	 * :String类复写了Object类中的equals()，该方法用于判断字符串的内容是否相同。
	 */
	public static void main(String[] args) {
		String str1 = "abcd"; // str是一个类类型变量，"abcd"是一个对象
		String str2 = new String("abcd");

//		System.out.println("----------test01---------start---------");
		StringTest st = new StringTest();
//		st.test01(str1, str2);// 比较
//
//		System.out.println("----------test02---------start---------");
		String str3 = "abacddd";
//		st.test02(str3);// 获取
//
//		System.out.println("----------test03---------start---------");
		st.test03(str3, str2);// 拼接
//
//		System.out.println("----------test04---------start---------");
//		st.test04();// 判断
//
//		System.out.println("----------test05---------start---------");
//		st.test05();// 转换
//
//		System.out.println("----------test06---------start---------");
//		st.test06();// 替换
//
//		System.out.println("----------test07---------start---------");
//		st.test07();// 切割
//
//		System.out.println("----------test08---------start---------");
//		st.test08();// 子串
//
//		System.out.println("----------test09---------start---------");
//		st.test09();//字符串内部转换、比较
	}

	/**
	 * TODO：字符串内部转换、比较
	 */
//	@Test
	public void test09() {
		String str = "a b c d e f g h ij k l m n";

		System.out.println("str.toUpperCase():" + str.toUpperCase());
		String str1 = " c d e f ";
		System.out.println("str1:" + str1.toString());
		System.out.println("str1.trim():" + str1.trim());
		System.out.println("CompareTo()--------");
		String s1 = "abc";
		String s2 = "abcd";
		String s3 = "abcdfg";
		String s4 = "1bcdfg";
		String s5 = "cdfg";
		System.out.println(s1.compareTo(s2)); // -1 (前面相等,s1长度小1)
		System.out.println(s1.compareTo(s3)); // -3 (前面相等,s1长度小3)
		System.out.println(s1.compareTo(s4)); // 48 ("a"的ASCII码是97,"1"的的ASCII码是49,所以返回48)
		System.out.println(s1.compareTo(s5)); // -2 ("a"的ASCII码是97,"c"的ASCII码是99,所以返回-2)
	}

	/**
	 * TODO : 子串
	 */
	// @Test
	public void test08() {
		String str = "a b c d e f g h ij k l m n";
		System.out.println("原来的字符串:" + str);
		System.out.println("str.substring(10,12):" + str.substring(10, 12));// 从11 开始，到12结束； 如果超出长度则： 越界异常.
		System.out.println("str.substring(0, str.length()):" + str.substring(0, str.length()));// 从头到尾
	}

	/**
	 * TODO:切割
	 */
	// @Test
	public void test07() {
		String str = "a b c d e f g h ij k l m n ";// 最后还有一个空格
		String[] strs = str.split(" ");
		for (String ss : strs) {
			System.out.print(ss + ",");
		}
		System.out.println();
		/**
		 * - 如果该限制 n 大于 0，则模式将被最多应用 n - 1 次，数组的长度将不会大于 n。 - 如果 n
		 * 为非正，那么模式将被应用尽可能多的次数，而且数组可以是任何长度。 - 如果 n 为
		 * 0，那么模式将被应用尽可能多的次数，数组可以是任何长度，并且结尾空字符串将被丢弃。
		 */
		String[] strs1 = str.split(" ", -1);
		for (String ss : strs1) {
			System.out.print(ss + ",");
		}
	}

	/**
	 * TODO:替换 String replace(oldchar, newchar);
	 */
	// @Test
	public void test06() {
		String s = "hello java";
		// String s1 = s.replace('q', 'n'); // 如果要替换的字符不存在，返回的还是原串
		String oldworld = "java";
		String newWorld = "world";
		String s1 = s.replace(oldworld, newWorld);
		System.out.println("s1.toString():" + s1.toString());
	}

	/**
	 * TODO: 转换
	 */
	 @Test
	public void test05() {
		/**
		 * - 1. 将 字符数组 转换成 字符串
		 */
		char[] cr = { 'i', 'm', 'a', 'c', 'h', 'a', 'r' };
		String str = "imaString";
		byte[] bt = { 'i', 'm', 'a', 'b', 'y', 't', 'e' };
		/**
		 * - 1.1 构造方法：
		 */
		String schar = new String(cr);// 底层通过Arrays.copyof()实现
		System.out.println("schar.toString():" + schar.toString());

		final int offset = 2;
		final int count = 3;
		String partChar = new String(cr, offset, count);// cr数据，从offset开始，count个数字 结束
		System.out.println("partChar.toString():" + partChar.toString());
		/**
		 * - 1.2 静态方法：
		 */
		String schar1 = String.copyValueOf(cr, offset, count);
		System.out.println("schar1.toString():" + schar1.toString());
		String scharValue1 = String.valueOf(cr, offset, count);
		System.out.println("scharValue1.toString():" + scharValue1.toString());
		/**
		 * - 2. 将字符串转换成字符数组
		 */
		char[] strValue = str.toCharArray();
		System.out.println("strValue.length:" + strValue.length);
		/**
		 * - 3. 将字节数组转成字符串 - 再转回来
		 */
		String strbyte = new String(bt);
		System.out.println("strbyte.toString():" + strbyte.toString());
		System.out.println("str.getBytes():" + str.getBytes());
		/**
		 * - 4. 基本数据类型转成字符串 - 通过 valueOf() - 10 +"" == String.valueOf(10); int--->String
		 */
	}

	/**
	 * TODO:判断
	 * 
	 * @param str
	 * @param str2
	 */
	// @Test
	public void test04() {
		String str = "StringTest.java";

		System.out.println("str.isEmpty():" + str.isEmpty());// 原理就是判断长度是否为0,是否空
//		特殊之处：indexOf(str)：可以索引str第一次出现的位置，如果返回-1，表示该str不在字符串中存在，
//				所以，也可以用于对指定字符串判断是否包含，而且该方法既可以判断，又可以获取出现的位置。
		System.out.println("str.contains(\"Test\") :" + str.contains("Test"));// 是否包含该String

		int toffset = 0;
		String strPart = "String";
		System.out.println("str.startsWith(strPart, toffset): " + str.startsWith(strPart, toffset));// 从 toffset 开始，是否以
																									// strPart 开头

		String strEnd = ".java";
		System.out.println("str.endsWith(strEnd):" + str.endsWith(strEnd));// 字符串是否是以指定内容结尾

		String str2 = "stringtest.java";
		System.out.println("str.equals(str2):" + str.equals(str2));// 比较 值 是否 same
		System.out.println("str.equalsIgnoreCase(str2):" + str.equalsIgnoreCase(str2));// 同上，忽略大小写
	}

	/**
	 * TODO: 拼接
	 * 
	 * @param str,str2
	 */
	// @Test
	public void test03(String str, String str2) {
		/**
		 * - 1、+ - 2、concat - 当两个量都为String类型且值不为null时，可以用concat方式。 - 3、append -
		 * 当需要拼接至少三个量的时候，StringBuffer#append() 以避免临时字符串的产生
		 * 
		 * - StringBuffer大多数情况(包括append操作)线程安全。 -
		 * 若不会出现多线程同时对一实例并发进行append操作，建议使用非线程安全的StringBuilder以获得更好性能 StringBuffer
		 * buf=new StringBuffer() buf.append("a"); if(someCondition){ buf.append("b"); }
		 * buf.append("c"); String d=buf.toString();
		 */
		//
//		System.out.println(str + str2);

//		System.out.println(str.concat(str2));

		StringBuffer strb = new StringBuffer();
//		System.out.println(strb.append(str2).append(str).toString());

		StringBuilder strd = new StringBuilder();
//		System.out.println(strd.append(str2).append(str).toString());

		/**
		 * 引用 <https://www.cnblogs.com/Wfei/p/3248922.html>
		 * StringBuffer.append()所改变的是源引用的值，不会依赖于方法返回值，
		 * 而String.concat()在进行字符串拼接的时候，会产生很多的临时对象来保存
		 *
		 * - 最后在拼接结束后，需要把这个结果临时对象进行返回给接收值进行再指向， 需要依赖于方法的返回值，执行的效率也会随着字符数的增加而降低，不是真正的引用源
		 * 总结： - 在所使用的字符串需要经常变更的情况下，使用StringBuffer效率更高，
		 * 可以使用StringBuffer进行字符串的变更操作，操作完成后再还给String， - 操作方法：String -> StringBuffer ->
		 * 更改字符串 -> String
		 */
		forTest03Change(str, strb);
		System.out.println(str);
		System.out.println(strb);

		/**
		 * 拓展： 字符串拼接共五种方法： 1. 加号 “+”
		 * 
		 * 2. String contact() 方法
		 * 
		 * 3. StringUtils.join() 方法
		 * 
		 * 4. StringBuffer append() 方法
		 * 
		 * 5. StringBuilder append() 方法
		 *
		 *
		 * 1. 方法1 加号 “+” 拼接 和 方法2 String contact() 方法 适用于小数据量的操作，代码简洁方便，加号“+”
		 * 更符合我们的编码和阅读习惯；
		 * 
		 * 2. 方法3 StringUtils.join() 方法
		 * 适用于将ArrayList转换成字符串，就算90万条数据也只需68ms，可以省掉循环读取ArrayList的代码；
		 * 
		 * 3. 方法4 StringBuffer append() 方法 和 方法5 StringBuilder append() 方法
		 * 其实他们的本质是一样的，都是继承自AbstractStringBuilder，效率最高，大批量的数据处理最好选择这两种方法。
		 * 
		 * 4. 方法1 加号 “+” 拼接 和 方法2 String contact() 方法 的时间和空间成本都很高（分析在本文末尾），不能用来做批量数据的处理。
		 *
		 *
		 * 1、其实每次调用contact()方法就是一次数组的拷贝，虽然在内存中是处理都是原子性操作，速度非常快， -
		 * 但是，最后的return语句会创建一个新String对象，限制了concat方法的速度。
		 * 
		 * 2、StringBuffer 和 StringBuilder 的append方法都继承自AbstractStringBuilder，
		 * 整个逻辑都只做字符数组的加长，拷贝，到最后也不会创建新的String对象，所以速度很快，
		 * 完成拼接处理后在程序中用strBuffer.toString()来得到最终的字符串。
		 * 
		 * 
		 * 3、 字符串的加号“+” 方法， 虽然编译器对其做了优化，使用StringBuilder的append方法进行追加，
		 * 但是每循环一次都会创建一个StringBuilder对象，且都会调用toString方法转换成字符串，所以开销很大。 - 注：执行一次字符串“+”,相当于
		 * str = new StringBuilder(str).append("a").toString(); - 循环使用则 直接使用builder
		 * ，其余情况 +
		 * 
		 * 4、 本文开头的地方统计了时间开销，根据上述分析再想想空间的开销。常说拿空间换时间，反过来是不是拿时间换到了空间呢，
		 * 但是在这里，其实时间是消耗在了重复的不必要的工作上（生成新的对象，toString方法）， 所以对大批量数据做处理时，加号“+” 和 contact
		 * 方法绝对不能用，时间和空间成本都很高。
		 *
		 * 
		 * String：适用于少量的字符串操作的情况
		 * 
		 * StringBuilder：适用于单线程下在字符缓冲区进行大量操作的情况
		 * 
		 * StringBuffer：适用多线程下在字符缓冲区进行大量操作的情况（99.99999%情况下不用）
		 */
	}

	public void forTest03Change(String str, StringBuffer str2) {
		str.concat("contact");
		str2.append("append");
	}

	/**
	 * TODO: 获取
	 * 
	 * @param str
	 */
//	 @Test
	public void test02(String str) {
//		 abacddd
		int localAt = 2;
		int fromIndex = 1;
		String strLocalAt = "a";
		System.out.println(str.charAt(localAt));// 根据位置获取位置上某个字符 ,从0 开始计数

		// 索引，即使越界也没事，找不到返回 -1(也可借此来直接用来判断)，不存在该位置 则略过
		// System.out.println(str.contains("a"));//字符串中是否包含某一个子串:boolean

		System.out.println(str.indexOf(strLocalAt, fromIndex));// 返回的是 从fromIndex指定位置开始 ,strLocalAt/localAt
																// 在字符串中第一次出现的位置
		System.out.println(str.lastIndexOf(strLocalAt, fromIndex));// 反向
	}

	// @Test
	public void test01(String str1, String str2) {
		System.out.println(str1 == str2);// false，比较的对象
		System.out.println(str1.equals(str2));// true，比较的值
	}
}
