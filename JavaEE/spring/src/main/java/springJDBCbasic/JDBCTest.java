package springJDBCbasic;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCTest {

    private ApplicationContext ctx = null;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    {
        ctx = new ClassPathXmlApplicationContext("spring-jdbc.xml");
        jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) ctx.getBean("namedParameterJdbcTemplate");
    }

//    @Test
//    public void testC3P0Connection() throws Exception{
//        DataSource dataSource = ctx.getBean(DataSource.class);
//        System.out.println(dataSource.getConnection());
//    }

    /**
     * 使用JdbcTemplate进行更新操作（Insert，Delete，Update）
     * 插入单条记录
     */
    @Test
    public void testUpdate() {
        String sql = "DELETE FROM student WHERE ID=?";
        jdbcTemplate.update(sql, "5537");
    }

    /**
     * 使用JdbcTemplate进行批量更新操作（Insert，Delete，Update）
     * 成批插入
     * 思路是：待插入的每一条记录作为List的一个条目，而List内的元素是一个Object的数组，这个数组就是带插入的字段
     */
    @Test
    public void testBatchUpdate() {
        String sql = "INSERT INTO student VALUES(?,?,?,?)";
        //list里面存放的是object类型的数组，即要插入的字段值
        List<Object[]> batchArgs = new ArrayList<>();
        batchArgs.add(new Object[]{"5536", "Jack", "男", 56});
        batchArgs.add(new Object[]{"5590", "Jim", "男", 19});
        batchArgs.add(new Object[]{"5078", "Lucy", "女", 36});

        jdbcTemplate.batchUpdate(sql, batchArgs);
    }

    /**
     * 使用JdbcTemplate进行查询操作，从数据库中获得一条记录，返回由各个字段组成的一个对象。
     * 多多观察query相关的API，看看传入的参数是什么，返回值又是什么
     * 在这里，因为返回的是要一个由结果集组成的对象，因此要用RowMapper先把要创建对象的类型告诉它
     */
    @Test
    public void testQueryForObject() {
        String sql = "SELECT id, name, sex, age FROM student WHERE id=?";
        RowMapper<Student> rowMapper = new BeanPropertyRowMapper<>(Student.class);//-->!!
        Student student = (Student) jdbcTemplate.queryForObject(sql, rowMapper, "5078");
        System.out.println(student);
    }

    /**
     * 使用JdbcTemplate进行查询操作，从数据库中获得多条记录，返回由各个记录组成的包含多个对象组成的对象集合。
     * 注意不是调用 queryForList
     * 另外，在查看API的时候，注意区分以下两者的不同：
     * --> Object[]： 是一个对象类型的数组
     * --> Object... args： 是一个不定参数
     */
    @Test
    public void testQueryForList() {
        String sql = "SELECT id, name, sex, age FROM student WHERE sex=?";
        RowMapper<Student> rowMapper = new BeanPropertyRowMapper<>(Student.class);
        List<Student> students = jdbcTemplate.query(sql, rowMapper, "男");
        System.out.println(students);
    }

    /**
     * 使用JdbcTemplate进行查询操作，获取单个列的值，或者是统计查询（如SUM/COUNT/MIN/MAX等）
     */
    @Test
    public void testQueryForSingleField() {
        String sql = "SELECT COUNT(*) FROM student WHERE sex=?";
        Integer manCount = jdbcTemplate.queryForObject(sql, Integer.class, "男");
        System.out.println("manCount: " + manCount);
    }

    /**
     * 使用具名参数模板NamedParameterJdbcTemplate，sql语句的占位符可以用更加明了的名称代替，格式是":属性名"
     * 优点：可读性更高，维护性更好（因为使用?占位符的话，传入的参数就必须一一对应，如果数据表中的字段发生了变化，
     * 则原有的代码都得改动）
     * 缺点：相对来说写起来更繁琐一点
     */
    @Test
    public void testUpdateWithNamedParameter() {
        String sql = "INSERT INTO student VALUES(:id,:name,:sex,:age)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", "5566");
        paramMap.put("name", "Wade");
        paramMap.put("sex", "男");
        paramMap.put("age", 37);
        namedParameterJdbcTemplate.update(sql, paramMap);
    }

    /**
     * namedParameterJdbcTemplate还可以利用数据表中一条记录对应的类的实体来向表中插入记录。
     * 有点类似于 queryForObject() 方法的逆过程， 需要借助于 SqlParameterSource
     * 需要特别注意的是：SQL 语句中的字段名和类的属性需一致!（注意类的数据域(field)和属性(property)的区别）
     * 在本例中，setID()表明其对应的属性是iD，而不是ID或id，所以在sql语句中对应的字段名就应该是 iD !
     */
    @Test
    public void testUpdateWithNamedParameter2() {
        String sql = "INSERT INTO student VALUES(:iD,:name,:sex,:age)";
        Student student = new Student();
        student.setID("5899");
        student.setName("KD");
        student.setSex("男");
        student.setAge(29);

        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(student);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

}
