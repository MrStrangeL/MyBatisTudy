package com.mybatisstudy;

import com.mybatisstudy.entity.Classes;
import com.mybatisstudy.entity.Course;
import com.mybatisstudy.entity.Student;
import com.mybatisstudy.entity.User;
import com.mybatisstudy.repository.StudentRepository;
import com.mybatisstudy.repository.UserRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String [] args){
        //加载MyBatisConfig.xml配置文件
        InputStream inputStream=Main.class.getClassLoader().getResourceAsStream("MyBatisConfig.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory=sqlSessionFactoryBuilder.build(inputStream);
        //获取SqlSession
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //调用 MyBatis 原生接口执行 SQL
        //statement 为 UserMapper.xml 的 namespace 值+"."+select 标签的 id
        String statement="com.mybatisstudy.mapper.UserMapper.get";
        User user=sqlSession.selectOne(statement,2);
        System.out.println(user);

        //Mapper实现自定义接口
        //获取实现接口的代理对象
        UserRepository userRepository=sqlSession.getMapper(UserRepository.class);

        //新增用户
        User newUser=new User(3,"wqm","789",26);
//        int addUserFlag=userRepository.addUser(newUser);
//        sqlSession.commit();
//        System.out.println("addUserFlag:"+addUserFlag);

        //修改用户
        newUser.setUsername("王清明");
        newUser.setPassword("11111111");
        newUser.setAge(88);
        int updateUserFlag=userRepository.updateUser(newUser);
        sqlSession.commit();
        System.out.println("updateUserFlag:"+updateUserFlag);

        //查询用户
        System.out.println(userRepository.selectUserById(3));

        //删除用户
//        int deleteUserFlag=userRepository.deleteUser(3);
//        sqlSession.commit();
//        System.out.println("deleteUserFlag:"+deleteUserFlag);

        //基本数据类型
        System.out.println(userRepository.findUserByIdInt(3));

        //包装类型
        System.out.println(userRepository.findUserByIdInteger(3));

        //String类型
        System.out.println(userRepository.findUserByName("王清明"));

        //多个参数
        System.out.println(userRepository.findUserByIdAndName(3,"王清明"));

        //POJO
        User userPOJO=new User(3,"王清明","",null);
        System.out.println(userRepository.findUserByUser(userPOJO));

        Map<String,Object> map=userRepository.findNameAndAgeById(3);
        Iterator<Map.Entry<String, Object>> iterator=map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,Object> entry=iterator.next();
            System.out.println(entry.getKey()+"---"+entry.getValue());
        }

        StudentRepository studentRepository=sqlSession.getMapper(StudentRepository.class);
        System.out.println(studentRepository.findStudentById(2));

        List<Student> studentsList=studentRepository.findStudentAll();
        for(Student temp:studentsList){
            System.out.println(temp);
        }

        System.out.println(studentRepository.findClassesStudentsById(1));

        List<Classes> classesList=studentRepository.findClassesStudentsAll();
        for(Classes temp:classesList){
            System.out.println(temp);
        }

        System.out.println(studentRepository.findStudentCoursesByStudentId(1));

        List<Student> studentCoursesList=studentRepository.findStudentCoursesAll();
        for(Student temp:studentCoursesList){
            System.out.println(temp);
        }

        System.out.println(studentRepository.findCourseStudentsByCourseId(1));

        List<Course> courseStudentsList=studentRepository.findCourseStudentsAll();
        for(Course temp:courseStudentsList){
            System.out.println(temp);
        }
    }
}
