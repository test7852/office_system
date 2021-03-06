package com.ht.service.student;

import com.ht.bean.student.Studentfloor;
import com.ht.bean.student.Studenthuor;
import com.ht.util.Pager;

import java.util.List;

/**
 * @author 王金宝
 * @date 2020/6/8 20:43
 *  
 */
public interface StudenthuorService{
    List<Studenthuor> selList();//查询所有

    List<Studenthuor> sybase(Pager pager);//分页查询

    List<Studenthuor> selByFid(Integer floorId);//根据楼栋id查找所有宿舍

    int selCount();//查询总数

    int deleteByPrimaryKey(Integer hourid);

    int insert(Studenthuor record);

    int insertSelective(Studenthuor record);

    Studenthuor selectByPrimaryKey(Integer hourid);

    int updateByPrimaryKeySelective(Studenthuor record);

    int updateByPrimaryKey(Studenthuor record);

}
