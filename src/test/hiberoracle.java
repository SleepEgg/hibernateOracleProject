import com.big3.pojo.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class hiberoracle {
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    @Before
    public void before(){
        Configuration configuration = new Configuration().configure();//创建配置文件
        sessionFactory = configuration.buildSessionFactory();//创建会话工厂
        session = sessionFactory.openSession();//开启会话
        transaction = session.beginTransaction();//开启事物
    }

    @After
    public void after(){
        transaction.commit();//提交
        session.close();//关闭会话
        sessionFactory.close();//关闭会话工厂
    }

    @org.junit.Test
    public void testUpdate(){
        Query query = session.createQuery("from Student ");
        List<Student> studentList = query.list();
        Student student = studentList.get(6);
        student.setName("Mona");
       }

    @Test
    public void testAdd(){
        Query query = session.createQuery("from Student ");
        Student studentnew = new Student();
        studentnew.setName("Mona");
        studentnew.setId(8l);
        studentnew.setAge(1l);
        session.save(studentnew);
    }

    @Test
    public void testDelete(){
        Query query = session.createQuery("from Student ");
        Student student = new Student();
        student.setId(8);
        session.delete(student);
    }

    @Test
    public void testSelect(){
        Query query = session.createQuery("select id,name,age from Student ");
        List<Object[]> list = query.list();
        for(Object[] stu :list){
            System.out.println("id:"+stu[0]);
            System.out.println("name:"+stu[1]);
            System.out.println("age:"+stu[2]);
        }
    }
}

