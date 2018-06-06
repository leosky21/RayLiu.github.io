package com.rayNotes.StringExes;


/**
 * 
 * testStringBuffer
 * @author ray
 *
 */
public class StringBufferExec {
	public static void main(String[] args) {
		StringBufferExec p = new StringBufferExec();
//		p.draw(10, 8);
		
//		int[] arr = {34, 12, 67, 43, 69};
//		p.numToString(arr);
		
		 StringBuffer buf1 = new StringBuffer("hello");
	     StringBuffer buf2 = new StringBuffer("java");
	     test(buf1, buf2);
	     System.out.println("main中的："+buf1 + "..." + buf2);
	}
	
	 public static void test(StringBuffer buf1, StringBuffer buf2) {
	        buf1.append(buf2);
	        buf1 = buf2;
	        System.out.println("test中的："+buf1 + "..." + buf2);
	    }
	
	/**
	 * TODO 将int数组的元素转成字符串，格式为：[34, 12, 67]。 
	 */
	public void numToString(int[] arr) {
		StringBuffer sb = new StringBuffer("[");
//		StringBuffer sb = new StringBuffer('[');// emm...它不行。。。
		for(int a: arr) {
			sb.append(a);
		}
		sb.append(']');
		System.out.println(sb);
	}
	
	
	/**
	 * TODO 通过缓冲区，将要打印的矩形组成元素*进行存储后，一次性返回，并输出。 
	 */
	public void draw(int row,int col) {
		
		StringBuffer sb = new StringBuffer();
		
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				sb.append('*');
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
