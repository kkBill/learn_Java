package demo5;

import org.junit.Test;

import java.util.List;

public class DAOTest {

    DAO dao = new DAO();

    @Test
    public void testUpdate() throws Exception {
        String sql = "INSERT INTO student VALUES(?,?,?,?)";
        dao.update(sql, "1818", "Hunag", "男", "38");
    }

    @Test
    public void testGet() throws Exception {
        //为什么要使用别名呢？？
        //因为数据库中的字段名字和类的属性名字不一定是一致的，因此，通过别名来保证与类的属性名字完全一致！
        String sql = "SELECT ID id, Name name, Sex sex, Age age FROM student WHERE ID=?";
        Student student = null;
        student = dao.get(Student.class, sql, "9999");
        System.out.println(student);
    }

    @Test
    public void testGetForList() throws Exception {
        String sql = "SELECT ID id, Name name, Sex sex, Age age FROM student WHERE Sex=?";
        List<Student> list = null;
        list = dao.getForList(Student.class, sql, "女");
        System.out.println(list);
        System.out.println(list.size());
    }

    @Test
    public void testGetForValue() throws Exception {
        String sql = "SELECT COUNT(*) FROM student WHERE Sex=?";
        Object res = dao.getForValue(sql, "男");
        System.out.println(res);
    }

}
