package com.ht.service.student;

import com.ht.bean.student.Studentfloor;
    /**
 * @author 王金宝
 * @date 2020/6/8 20:42
 *  
 */
public interface StudentfloorService{


    int deleteByPrimaryKey(Integer floorid);

    int insert(Studentfloor record);

    int insertSelective(Studentfloor record);

    Studentfloor selectByPrimaryKey(Integer floorid);

    int updateByPrimaryKeySelective(Studentfloor record);

    int updateByPrimaryKey(Studentfloor record);

}