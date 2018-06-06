package stream;
public class TestCommandLine{
	public static void main(String[] args) {
		for ( int i = 0; i < args.length; i++ ) {
			System.out.println("args[" + i + "] = " + args[i]);
		}
	}
}

//����ʱ��ʹ��java TestCommandLine lisa "bily" "Mr Brown"