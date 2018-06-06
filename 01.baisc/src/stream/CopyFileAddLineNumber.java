package stream;

import java.io.*;
public class CopyFileAddLineNumber {
	public static void main (String[] args) {
		String infname = "CopyFileAddLineNumber.java";
		String outfname = "CopyFileAddLineNumber.txt";
		if( args.length >= 1 ) infname = args[0];
		if( args.length >= 2 ) outfname = args[1];

		try {
			File fin = new File(infname);
			File fout = new File(outfname);

			BufferedReader in = new BufferedReader(new FileReader(fin));
			PrintWriter out  = new PrintWriter(new FileWriter(fout));

			int cnt = 0;	// �к�
			String s = in.readLine();
			while ( s != null ) {
				cnt ++; 
				s = deleteComments(s);						//ȥ����//��ʼ��ע��
				out.println(cnt + ": \t" + s );				//д��
				s = in.readLine();							//����
			}			
			in.close();				// �رջ�����������ļ�������������.
			out.close();
		} catch (FileNotFoundException e1) {
			System.err.println("File not found!" );
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}

	static String deleteComments( String s ) //ȥ����//��ʼ��ע��
	{
		if( s==null ) return s;
		int pos = s.indexOf( "//" );
		if( pos<0 ) return s;
		return s.substring( 0, pos );
	}
}
