package com.ht.service.emp;

import com.ht.bean.emp.Dep;
import com.ht.bean.emp.Workdiary;
import com.ht.util.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 王金宝
 * @date 2020/6/12 20:30
 * 
 */
public interface WorkdiaryService{

    int selworkday(Integer empid);//查询每个用户的周报

    int selworkcount();//查询总行数

    List<Workdiary> allPageWorkdiary(Pager pager);

    List<Workdiary> myworkdiary(Integer empid);

    List<Workdiary> searchWorkdiary(Integer id);

    int deleteByPrimaryKey(Integer worklogid);

    int insert(Workdiary record);

    int insertSelective(Workdiary record);

    Workdiary selectByPrimaryKey(Integer worklogid);

    int updateByPrimaryKeySelective(Workdiary record);

    int updateByPrimaryKey(Workdiary record);

}
