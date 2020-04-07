package com.mybatisstudy.repository;

import com.mybatisstudy.entity.Classes;
import com.mybatisstudy.entity.Course;
import com.mybatisstudy.entity.Student;

import java.util.List;

public interface StudentRepository {
    Student findStudentById(Integer id);
    List<Student> findStudentAll();

    Classes findClassesStudentsById(Integer id);
    List<Classes> findClassesStudentsAll();

    //查询某个学生学习那些课程
    Student findStudentCoursesByStudentId(Integer id);
    //查询所有学生学习的课程
    List<Student> findStudentCoursesAll();

    //查询某个课程有那些学生学习
    Course findCourseStudentsByCourseId(Integer id);
    //查询所有课程都有那些学生在学习
    List<Course> findCourseStudentsAll();

}
