package ReferenceText;

//import java.lang.ref.WeakReference;
/**
 *  弱引用与软引用的区别在于：只具有弱引用的对象拥有更短暂的生命周期。
 *  在垃圾回收器线程扫描它所管辖的内存区域的过程中，一旦发现了只具有弱引用的对象，
 *  不管当前内存空间足够与否，都会回收它的内存。不过，由于垃圾回收器是一个优先级很低的线程，因此不一定会很快发现那些只具有弱引用的对象。

    弱引用可以和一个引用队列（ReferenceQueue）联合使用，如果弱引用所引用的对象被垃圾回收，Java虚拟机就会把这个弱引用加入到与之关联的引用队列中。
 * @author liujun
 *
 */
public class WeakReference {
	
	public static void main(String[] args) 
			throws Exception{
		String str = new String("leo  junner");
		// 将弱引用 也引用 该对象
//		WeakReference<String> aaa = new WeakReference<String>(str);
		str = null;
	}
}
