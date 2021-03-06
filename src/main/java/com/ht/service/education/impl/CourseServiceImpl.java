package com.ht.service.education.impl;

import com.ht.bean.education.Coursetype;
import com.ht.util.Pager;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.ht.bean.education.Course;
import com.ht.dao.education.CourseMapper;
import com.ht.service.education.CourseService;

import java.util.List;
import java.util.Map;

/**
 * @author 王金宝
 * @date 2020/6/8 20:35
 *  
 */
@Service
public class CourseServiceImpl implements CourseService{

    @Resource
    private CourseMapper courseMapper;

    @Override
    public int deleteByPrimaryKey(Integer courseid) {
        return courseMapper.deleteByPrimaryKey(courseid);
    }

    @Override
    public int insert(Course record) {
        return courseMapper.insert(record);
    }

    @Override
    public int insertSelective(Course record) {
        return courseMapper.insertSelective(record);
    }

    @Override
    public Course selectByPrimaryKey(Integer courseid) {
        return courseMapper.selectByPrimaryKey(courseid);
    }

    @Override
    public int updateByPrimaryKeySelective(Course record) {
        return courseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Course record) {
        return courseMapper.updateByPrimaryKey(record);
    }

    @Override
    public int selCountcEdu() {
        return courseMapper.selCountcEdu();
    }

    @Override
    public List<Map> selByPage(Pager pager) {
        return courseMapper.selbypage((pager.currPage-1)*pager.pageSize,pager.pageSize);
    }

    @Override
    public List<Coursetype> selCtypes() {
        return courseMapper.selCtype();
    }

    @Override
    public List<Course> selCourse() {
        return courseMapper.selCourse();
    }

    @Override
    public List<Map> mhcou(Pager pager, Course course) {
        return courseMapper.mhcou(pager, course);
    }


}
