package cn.hhit.test;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.junit.Test;

public class BeanUtilsTest {

	@Test
	public void test1() throws IllegalAccessException, InvocationTargetException {
		Person p = new Person();
		BeanUtils.setProperty(p, "name", "xcc");

		System.out.println(p.getName());
	}

	@Test
	public void test2() throws IllegalAccessException, InvocationTargetException {
		String name = "aaaa";
		String password = "123";
		String age = "34";
		String birthday = "1980-09-09";

		Person p = new Person();
		BeanUtils.setProperty(p, "name", name);
		BeanUtils.setProperty(p, "password", password);
		BeanUtils.setProperty(p, "age", age); // 对数据进行转换，转换时只支持8种基本数据类型
		BeanUtils.setProperty(p, "birthday", birthday); // 碰到复杂类型无法转换！！！否则必须给beanUtils注册日期转换器

		System.out.println(p.getName());
		System.out.println(p.getPassword());
		System.out.println(p.getAge());
		System.out.println(p.getBirthday());
	}

	@Test
	public void test3() throws IllegalAccessException, InvocationTargetException {
		String name = "aaaa";
		String password = "123";
		String age = "34";
		String birthday = "1980-09-09";

		ConvertUtils.convert(new DateLocaleConverter(), Date.class);// 原生的不够健壮
		Person p = new Person();
		BeanUtils.setProperty(p, "name", name);
		BeanUtils.setProperty(p, "password", password);
		BeanUtils.setProperty(p, "age", age); // 对数据进行转换，转换时只支持8种基本数据类型
		BeanUtils.setProperty(p, "birthday", birthday); // 碰到复杂类型无法转换！！！否则必须给beanUtils注册日期转换器

		System.out.println(p.getName());
		System.out.println(p.getPassword());
		System.out.println(p.getAge());
		System.out.println(p.getBirthday());
	}

	@Test
	public void test4() throws IllegalAccessException, InvocationTargetException {
		String name = "aaaa";
		String password = "123";
		String age = "34";
		String birthday = "1980-09-09";

		ConvertUtils.register(new Converter() {

			@Override
			public Object convert(Class type, Object value) {
				if (value == null) { // 先检查
					return null;
				}
				if (!(value instanceof String)) {
					throw new ConversionException("只支持String类型的转换！！"); // BeanUtils文档中建议抛此异常
				}
				String str = (String) value; // 非空且为String
				if (str.trim().equals("")) { // 再判断是否有值
					return null;
				}
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				try {
					return df.parse(str);
				} catch (ParseException e) {
					throw new RuntimeException(e); // 异常链不能断，必须把原来的异常信息封装进去，抛出异常给上一层，上一层就会知道到底出了什么问题
				}
			}
		}, Date.class);

		Person p = new Person();
		BeanUtils.setProperty(p, "name", name);
		BeanUtils.setProperty(p, "password", password);
		BeanUtils.setProperty(p, "age", age); // 对数据进行转换，转换时只支持8种基本数据类型
		BeanUtils.setProperty(p, "birthday", birthday); // 碰到复杂类型无法转换！！！否则必须给beanUtils注册日期转换器

		System.out.println(p.getName());
		System.out.println(p.getPassword());
		System.out.println(p.getAge());
		System.out.println(p.getBirthday());
	}

	@Test
	public void test5() throws IllegalAccessException, InvocationTargetException {
		Map map = new HashMap();
		map.put("name1", "aaa"); // 注意：map集合中的键必须和bean的属性名称一致
		map.put("password", "123");
		map.put("age", "23");
		map.put("birthday", "1980-09-09");

		ConvertUtils.register(new Converter() {

			@Override
			public Object convert(Class type, Object value) {
				if (value == null) { // 先检查
					return null;
				}
				if (!(value instanceof String)) {
					throw new ConversionException("只支持String类型的转换！！"); // BeanUtils文档中建议抛此异常
				}
				String str = (String) value; // 非空且为String
				if (str.trim().equals("")) { // 再判断是否有值
					return null;
				}
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				try {
					return df.parse(str);
				} catch (ParseException e) {
					throw new RuntimeException(e); // 异常链不能断，必须把原来的异常信息封装进去，抛出异常给上一层，上一层就会知道到底出了什么问题
				}
			}
		}, Date.class);

		Person bean = new Person();
		BeanUtils.populate(bean, map); // 用map集合中的值，填充bean的属性

		System.out.println(bean.getName());
		System.out.println(bean.getPassword());
		System.out.println(bean.getAge());
		 Date date = bean.getBirthday();
		System.out.println(date.toLocaleString());
		System.out.println(bean.getBirthday());
	}
	
	
}
