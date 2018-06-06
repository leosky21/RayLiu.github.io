package com.rayNotes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.Test;

/**
 * TODO 当需求中出现映射关系时，应该最先想到Map集合。
 * 
 * 	- Map与Collection的不同
	* -	Map与Collection在集合框架中属并列存在
	* - 	Map存储的是键值对
	* - 	Map存储元素使用put方法，Collection使用add方法
	* - 	Map集合没有直接取出元素的方法，而是先转成Set集合，在通过迭代获取元素
	* - 	Map集合中键要保证唯一性
	*
	* - 	总结：Map是一个双列集合，一次存一对(键值对)，而且要保证键的唯一性。
 * @author ray
 * 
 * @see CollectionTest
 *
 */
public class MapTest {
	/**
	 * TODO 练习
	 * 		 map集合扩展知识——一对多关系
	 * 		一个Employee有一家公司，一家公司有多个Employee。
	 * 			（借用已经封装号的Employee类，在查询时能够实现通过年纪排序，其他时候可以其他方式排序）
	 * 
	 * 		思路:
	 * 			不能重复,可以实现排序：
	 * 		可以用Set、TreeSet
	 * 			ArrayList 查询快、删改慢
	 * 			LinkedList 增删快、查询快
	 * 		
	 */
	@Test
	public void oneToMany() {
//		一个国家，一个国家有若干个公司
		HashMap<String,TreeSet<Employee>> country = 
				new HashMap<String,TreeSet<Employee>>();
//		若干个公司
		TreeSet<Employee> company1 = new TreeSet<Employee>();
		TreeSet<Employee> company2 = new TreeSet<Employee>();
		country.put("company1", company1);
		country.put("company2", company2);
//		Employee 与公司建立关系
		company2.add(new Employee("zhangsan",22));
		company2.add(new Employee("zhaosi",20));
		company1.add(new Employee("liwu",20));
		company1.add(new Employee("zhaoliu",20));
		Set<String> keys = country.keySet();
		Iterator it = keys.iterator();
		String companyName;
		 TreeSet<Employee> temp;
		while(it.hasNext()) {
			 companyName = (String) it.next();
			 temp = country.get(companyName);
			 System.out.println(companyName);
			 getInfo(temp);
		}
	}
	private void getInfo(TreeSet<Employee> temp) {
		Iterator it = temp.iterator();
		Employee tempStu;
		while(it.hasNext()) {
			tempStu = (Employee) it.next();
			System.out.println(tempStu.toString());
		}
	}

	/**
	 * TODO 练习:
	 * 		获取字符串(比如”bwaerbctyxbacecrtdcvr”)
	 * 		中每一个字母出现的次数，要求结果格式为：a(2)b(1)d(3)… 
	 * 		- 思路：
	 * 		1、获取字符串中每个字符存在的次数
	 * 		2、每个字符和出现的次数存在映射关系（必须是Map），需要排序	-> 通过‘红黑树’（TreeMap）和‘链表’（LinkedMap、LinkedHashMap）
	 */
	@Test
	public void countCharMap() {
		String str = "bwaerbctyxbacecrtdcvr";
		char[] chs = str.toCharArray();
//		System.out.println(new String(chs));
		Map<Character,Integer> map = new TreeMap<Character,Integer>();
		Integer count=1;
		for(int i=0;i<chs.length;i++) {
			//只关心字母
			if(!(chs[i] >= 'a' && chs[i] <= 'z' 
					|| chs[i] >= 'A' && chs[i] <= 'Z') ) {
				continue;
			}
			
			if(!map.containsKey(chs[i])) {
				map.put(chs[i], 1);
			}else 
				if(map.containsKey(chs[i])) {
				count = map.get(chs[i]);
				count++;
//				map.remove(chs[i]);
				map.put(chs[i], count);	
			}
		}
		Set<Character> keyset = map.keySet();
		Iterator<Character> it = keyset.iterator();
		StringBuilder sb = new StringBuilder();  
		while(it.hasNext()) {
			Character key = it.next();
			int value = map.get(key);
			sb.append(key+"("+value+")");
		}
		System.out.println(sb.toString());
	}
	
	
	/**
	 * TODO 练习：
	 * 		每一个雇员都有对应的归属地。雇员Employee，地址String。
	 * 		雇员属性：姓名，年龄。
	 * 		将雇员和归属地存储到HashMap集合中并取出，注意：姓名和年龄相同的视为同一个雇员，须保证雇员的唯一性。 
	 */
	@Test
	public void hashMapTest() {
		Map<Employee,String> map = new HashMap<Employee,String>();
		map.put(new Employee("xiaozhang", 24), "北京");
		map.put(new Employee("laoli", 34), "上海");
		map.put(new Employee("mingming", 26), "南京");
		map.put(new Employee("xili", 30), "广州");
		map.put(new Employee("laoli", 34), "铁岭");
		
		Set<Entry<Employee, String>> s = map.entrySet();
		Iterator<Entry<Employee, String>> it = s.iterator();
		Entry<Employee, String> t;
		while(it.hasNext()) {
			 t = it.next();
//			it.next（）执行完后，马上游标就下去一行了
			System.out.println(map.size()+":"+t.getKey()+"::"+t.getValue());
		}
	}
	
	
	@Test
	public void mapTest() {
		Map<String,String> map = new HashMap();
		map.put("firK", "firV");
		map.put("firK", "firV");
		map.put("secK", "secV");
		/**
		 * TODO 取map 
		 *		- 第一种： Map集合的取出原理：将map集合转成set集合，再通过迭代器取出。
		 */
		Set<String> k = map.keySet();
		for(Iterator<String> it = k.iterator();it.hasNext();) {	
			System.out.println( "第一种："+
					map.get(it.next()) );
		}
		/**
		 * 		- 第二种： 将map集合中的映射关系存入到了Set集合中，而这个关系的数据类型就是：Map.Entry。
		 * 				Map.Entry，其实Entry也是一个接口，它是Map接口中的一个内部接口。
		 */
		Set<Entry<String, String>> entrySet = map.entrySet();
		Iterator<Entry<String, String>> it = entrySet.iterator();
		 Entry<String, String> entry;
		while(it.hasNext()) {
			entry = it.next();
			String key = entry.getKey();
			String value = entry.getValue();
			System.out.println( "第二种："
							+key+"::"+value);
		}
	}
}

class Employee implements Comparable{
	private String name;
//	private String address;
	private int age;
	
	public Employee() {super();}
	
	public Employee(String name, int age) {super();this.name = name;
	this.age = age;}

	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
//	public String getAddress() {return address;}
//	public void setAddress(String address) {this.address = address;}
	public int getAge() {return age;}
	public void setAge(int age) {this.age = age;}

	   public int hashCode() {
	        final int NUMBER = 31;
	        return name.hashCode() + age * NUMBER; // 尽量减小哈希冲突
	    }
	   
	   public boolean equals(Object obj) {
	        if (this == obj) {return true;}
	        if(obj==null) {return false;}
	        if(getClass() != obj.getClass()) {return false;}
	        if (!(obj instanceof Employee)) {
	            throw new ClassCastException("类型错误");
	        }
	        Employee em = (Employee) obj;
	        if(this.age != em.age) {
	        		return false;
	        }else {
	        		//处理null值
	        		if(this.name==null) {
	        			if(em.name!=null) 
	        				return false;
	        		}else if(!em.name.equals(em.name)) {
	        				return false;	
	        		}
	        }
	        return true;
//	        return this.name.equals(em.name) && this.age == em.age; //&& this.address.equals(em.address);
	    }
	public String toString() {
		return "["+this.name+","+this.age+"]";
	}
	@Override
	public int compareTo(Object o) {
		Employee e = (Employee) o;
		return this.age-e.age==0 ? this.name.compareTo(e.name) : this.age-e.age;
		
	}
	
}

