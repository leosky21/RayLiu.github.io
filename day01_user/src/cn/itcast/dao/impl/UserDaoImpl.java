package cn.itcast.dao.impl;

import java.text.SimpleDateFormat;

import org.dom4j.Document;
import org.dom4j.Element;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import cn.itcast.utils.XmlUtils;

public class UserDaoImpl implements UserDao {
	
	@Override
	public void add(User user) {
        try {
            Document document = XmlUtils.getDocument();
            Element root = document.getRootElement();
            root.addElement("user").addAttribute("id", user.getId())
                                   .addAttribute("username", user.getUsername())
                                   .addAttribute("password", user.getPassword())
                                   .addAttribute("email", user.getEmail())
                                   .addAttribute("birthday", user.getBirthday()==null?"":user.getBirthday().toLocaleString())
                                   .addAttribute("nickname", user.getNickname());

            XmlUtils.write2Xml(document);
            System.out.println("-----------"+document.getPath()+"----------------");
        } catch (Exception e) {
            // 最好应该转换为自定义异常，但不想把这个工程搞得太复杂
            throw new RuntimeException(e);
        }
    }

    @Override
	public User find(String username, String password) {
        try {
            Document document = XmlUtils.getDocument();
            Element e = (Element) document.selectSingleNode("//user[@username='"+username+"' and @password='"+password+"']");
            
            if(e == null) {
                return null;
            }
            System.out.println(e.attributeValue("password")+"::"+e.attributeValue("username")+"::"+e.attributeValue("nickname"));
            User user = new User();

            String date = e.attributeValue("birthday"); // "" "1980-09-09"
            if(date == null || date.equals("")) {
                user.setBirthday(null);
            } else {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                user.setBirthday(df.parse(date));
            }
            
            user.setEmail(e.attributeValue("email"));
            user.setId(e.attributeValue("id"));
            user.setNickname(e.attributeValue("nickname"));
            user.setPassword(e.attributeValue("password"));
            user.setUsername(e.attributeValue("username"));
           
            return user;
        } catch (Exception e) {
            // 最好应该转换为自定义异常，但不想把这个工程搞得太复杂
            throw new RuntimeException(e);
        	//e.printStackTrace();
        }
    }

    // 查找注册的用户是否在数据库中存在
    @Override
	public boolean find(String username) {
        try {
            Document document = XmlUtils.getDocument();
            Element e = (Element) document.selectSingleNode("//user[@username='"+username+"']");
            if(e == null) {
                return false;
            }
            System.out.print(e.attributeValue("username"));
            return true;
        } catch (Exception e) {
            // 最好应该转换为自定义异常，但不想把这个工程搞得太复杂
            throw new RuntimeException(e);
        }
    }
}
