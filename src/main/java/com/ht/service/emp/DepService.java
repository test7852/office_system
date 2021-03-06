package com.ht.service.emp;

import com.ht.bean.emp.Dep;
import com.ht.util.Pager;

import java.util.List;

/**
 * @author 王金宝
 * @date 2020/6/8 20:41
 *  
 */
public interface DepService{


    int deleteByPrimaryKey(Integer depid);

    int insert(Dep record);

    int insertSelective(Dep record);

    Dep selectByPrimaryKey(Integer depid);

    int updateByPrimaryKeySelective(Dep record);

    int updateByPrimaryKey(Dep record);

    /**
     * @return
     * 查询总数
     */
    int getTotalRow();

    /**
     * @param pager
     * @return
     * 分页查询
     */
    List<Dep> allPageDep(Pager pager);
    /**
     * @param fid
     * @return
     * 根据父id查找父级部门
     */
    Dep getDepByFid(int fid);
    /**
     * @param id
     * @return
     * 根据id查找子级部门
     */
    List<Dep> getDepByid(int id);
    /**
     * @return
     * 获取所有一级部门
     */
    List<Dep> getAllFdep();
    /**
     * @param dep
     * @return
     * 根据名称找部门
     */
    Dep getDepByName(Dep dep);

    /**
     * @return
     * 获取所有部门
     */
    List<Dep> list();

}
