package com.ht.service.education;

import com.ht.bean.education.Classtype;
import com.ht.util.Pager;

import java.util.List;

/**
 * @author 王金宝
 * @date 2020/6/19 19:10
 * 
 */
public interface ClasstypeService{


    int deleteByPrimaryKey(Integer classtypeid);

    int insert(Classtype record);

    int insertSelective(Classtype record);

    Classtype selectByPrimaryKey(Integer classtypeid);

    int updateByPrimaryKeySelective(Classtype record);

    int updateByPrimaryKey(Classtype record);

    List<Classtype> classtpedata(Pager pager);//分页查询班级类别表数据

    List<Classtype> alldata();//查询班级类别表所有数据

    int getclasstypeRow();
}
