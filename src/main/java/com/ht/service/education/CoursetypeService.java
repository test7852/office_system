package com.ht.service.education;

import com.ht.bean.education.Coursetype;
    /**
 * @author 王金宝
 * @date 2020/6/8 20:40
 *  
 */
public interface CoursetypeService{


    int deleteByPrimaryKey(Integer coursetypeid);

    int insert(Coursetype record);

    int insertSelective(Coursetype record);

    Coursetype selectByPrimaryKey(Integer coursetypeid);

    int updateByPrimaryKeySelective(Coursetype record);

    int updateByPrimaryKey(Coursetype record);

}