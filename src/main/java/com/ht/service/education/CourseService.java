package com.ht.service.education;

import com.ht.bean.education.Course;
import com.ht.bean.education.Coursetype;
import com.ht.util.Pager;

import java.util.List;
import java.util.Map;

/**
 * @author 王金宝
 * @date 2020/6/8 20:35
 *  
 */
public interface CourseService{


    int deleteByPrimaryKey(Integer courseid);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Integer courseid);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    int selCountcEdu();//查询总数据

    List<Map> selByPage(Pager pager);//分页查询

    List<Coursetype> selCtypes();//查询课程类别

    List<Course> selCourse();

    List<Map> mhcou(Pager pager, Course course);//模糊查询
}
