package cn.ray.listener;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 当一个Web应用创建的Session很多时， 为了避免Session占用太多的内存， 我们可以选择手动将这些内存中的session销毁，
 * 那么此时也可以借助监听器技术来实现。
 * 
 * @author ray
 *
 */
@WebListener
public class SessionScanner implements HttpSessionListener, ServletContextListener {
	// private List<HttpSession> list = new ArrayList<HttpSession>();
	/*---常增删--但是LinkedList线程不安全-*/

	/**
	 * 1. 可以把这句代码list.add(session);放在同步代码块里面。 2. 也可以把这个容器做成线程安全的。
	 */
	private List<HttpSession> list = Collections.synchronizedList(new LinkedList<HttpSession>());

	// 定义一个对象，让这个对象充当一把锁，用这把锁来保证往list集合添加新的session和遍历list集合中的session这两个操作达到同步
	private Object lock = new Object(); // 在不同位置的代码怎么做到同步，可以利用一个对象锁就可实现

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();

		synchronized (lock) {
			list.add(session);
		}

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		Timer timer = new Timer();
		timer.schedule(new MyTask1(list, this.lock), 0, 30 * 1000); // 定时器每隔30秒执行一个任务
	}
}

// 任务(每隔30秒干什么事情，即扫描list集合)
class MyTask1 extends TimerTask {

	// 存储HttpSession的list集合
	private List<HttpSession> list;
	private Object lock;

	public MyTask1(List<HttpSession> list, Object lock) {
		this.list = list;
		this.lock = lock;
	}

	// run方法指明了任务要做的事情
	@Override
	public void run() {

		// Iterator<HttpSession> it = list.iterator();
		/**
		 * 说明在集合迭代的过程中，删除集合的元素， 就会抛一个并发修改异常，所以在迭代集合时， 不可以通过集合对象的方法操作集合中的元素。
		 * 如果想要在集合迭代的过程中，增删改动集合的元素，那该怎么办呢？ 这时就需要使用其子接口
		 * listIterator——List集合特有的迭代器(listIterator)。
		 */
		synchronized (this.lock) {
			Iterator<HttpSession> it = list.listIterator();
			while (it.hasNext()) {
				HttpSession session = it.next();
				if (session != null) {// 只有一个访问,退掉就会变成null,Session already invalidated
					if ((System.currentTimeMillis() - session.getLastAccessedTime()) > 30 * 1000) {

						session.invalidate();
						// 为了不抛并发修改异常
						list.remove(session); // 从集合中移除摧毁的session。注意：在集合迭代的过程中，删除集合的元素，就会抛一个并发修改异常。
					}
				}
			}
		}
		/**
		 * 为了能够将这两段不相干的代码做成同步，只能定义一把锁(Object lock)， 然后给这两步操作加上同一把锁，
		 * 用这把锁来保证往list集合添加新的session和遍历list集合中的session这两个操作达到同步，
		 * 当在执行往list集合添加新的session操作时， 就必须等添加完成之后才能够对list集合进行迭代操作，
		 * 当在执行对list集合进行迭代操作时， 那么必须等到迭代操作结束之后才能够往list集合添加新的session。
		 */
	}

}