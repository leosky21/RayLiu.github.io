package stream;
import java.io.*;

class ProcessAndStream
{
	//�������̣�����ȡ�����
	//����Ĺ����̡߳�������Swing����Ĺ��������Բο�:
	// http://www.cnblogs.com/freeliver54/archive/2011/11/04/2235911.html
	public static void main(String[] args) throws IOException
	{
		String [] cmd =  {"ping", "www.pku.edu.cn"};
		Process process = null;
		try {
			// ����������ָ��������½���
			process = Runtime.getRuntime().exec(cmd);
		}
		catch(IOException e) {
			System.err.println("��������ʱ����...\n" + e);
			System.exit(1);
		}
		// ����½�����д�����
		// Ҫʹ�� process.getInputStream() �� process.getErrorStream();
		BufferedReader br = new BufferedReader( 
			new InputStreamReader( 
				process.getInputStream(), "GB2312"));
		String line = "";
		while((line = br.readLine()) != null) {
			System.out.println(line);
		}

	}
}
