//package cn.hhit.test;
//
//import java.sql.SQLException;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.dbutils.QueryRunner;
//import org.apache.commons.dbutils.handlers.ArrayHandler;
//import org.apache.commons.dbutils.handlers.ArrayListHandler;
//import org.apache.commons.dbutils.handlers.BeanHandler;
//import org.apache.commons.dbutils.handlers.BeanListHandler;
//import org.apache.commons.dbutils.handlers.ColumnListHandler;
//import org.apache.commons.dbutils.handlers.KeyedHandler;
//import org.apache.commons.dbutils.handlers.ScalarHandler;
//import org.junit.Test;
//
//import cn.hhit.pojo.User;
//import cn.hhit.utils.JdbcUtils;
//
//public class DaoTest {
//	/*插入数据*/
//	@Test
//    public void insert() throws SQLException {
//        QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
//        String sql = "insert into tb_users(id,name,password,email,birthday) values(?,?,?,?,?)";
//        Object[] params = {2, "bbb", "123", "bbb@163.com", new Date()};
//        runner.update(sql, params);
//    }
//	 /*更新数据*/
//    @Test
//    public void update() throws SQLException {
//        QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
//        String sql = "update tb_users set email=? where id=?";
//        Object[] params = {"yeyiyi@126.com", 1};
//        runner.update(sql, params);
//    }
//    /*删除数据*/
//    @Test
//    public void delete() throws SQLException {
//        QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
//        String sql = "delete from tb_users where id=?";
//        runner.update(sql, 2);
//    }
//    /*按指定方式查询,返回bean数据*/
//    @Test
//    public void find() throws SQLException {
//        String sql = "select * from tb_users where id=?";
////        User user = (User) runner.query(sql, 1, new BeanHandler(User.class));
//        QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
//        qr.query(sql, new BeanHandler(User.class),"idIndex");
//    }
//    
//    /*查询数据封装成指定bean,且加入list集合*/
//    @Test
//    public void getAll() throws SQLException {
//        QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
//        String sql = "select * from tb_users";
//        List list = (List) runner.query(sql, new BeanListHandler(User.class));
//       
//    }
//
//    /*插入多组数据*/
//    @Test
//    public void batch() throws SQLException {
//        QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
//        String sql = "insert into tb_users(id,name,password,email,birthday) values(?,?,?,?,?)";
//        Object[][] params = new Object[3][5];
//        for (int i = 0; i < params.length; i++) {
//            params[i] = new Object[]{i+1, "aa"+i, "123", i+"@sian.com", new Date()};
//        }
//        runner.batch(sql, params);
//    }
//    /*把结果集中的第一行数据转成对象数组*/
//    @Test
//    public void arrayHandler() throws SQLException {
//        QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
//        String sql = "select * from users";
//        Object[] result = runner.query(sql, new ArrayHandler());
//        System.out.println(result[0]);
//        System.out.println(result[1]);
//    }
//    /*把结果集中的每一行数据都转成一个数组，再存放到List中*/
//    @Test
//    public void arrayListHandler() throws SQLException {
//        QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
//        String sql = "select * from tb_users";
//        List list = runner.query(sql, new ArrayListHandler());
//        System.out.println(list);
//    }
//    /*将结果集中某一列的数据存放到List中。*/
//    @Test
//    public void ColumnListHandler() throws SQLException {
//        QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
//        String sql = "select * from tb_users";
//        List list = (List) runner.query(sql, new ColumnListHandler("name"));
//        System.out.println(list);
//    }
//    
//    @Test
//    public void keyedHandler() throws SQLException {
//        QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
//        String sql = "select * from tb_users";
//        Map<Integer, Map<String, Object>> map = (Map<Integer, Map<String, Object>>) 
//        										runner.query(sql, new KeyedHandler("id"));
//        for (Map.Entry<Integer, Map<String, Object>> me : map.entrySet()) {
//            int id = me.getKey();
//            for (Map.Entry<String, Object> entry : me.getValue().entrySet()) {
//                String name = entry.getKey();
//                Object value = entry.getValue();
//                System.out.println(name+"="+value);
//            }
//        }
//    }
//    
//    @Test
//    public void counts() throws SQLException {
//    	QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
//        String sql = "select count(*) from tb_users";
//        int totalrecord = ((Long)runner.query(sql, new ScalarHandler(1))).intValue();
//        System.out.println(totalrecord);
//    }
//}
