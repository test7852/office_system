package com.ht.web.student;

import com.ht.bean.emp.Empinfo;
import com.ht.bean.json.JsonData;
import com.ht.bean.student.*;
import com.ht.service.emp.EmpinfoService;
import com.ht.service.student.DeptService;
import com.ht.service.student.MajorService;
import com.ht.service.student.StudentclassService;
import com.ht.service.student.StudentfallService;
import com.ht.util.Contants;
import com.ht.util.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author 王钟华
 * @date 2020-06-10 10:53
 */
@Controller
@RequestMapping("studentClass")
public class StudentClassController {

    @Resource
    private JsonData jsonData;
    @Resource
    private StudentclassService studentclassService;//班级管理接口
    @Resource
    private EmpinfoService empinfoService;//员工管理接口
    @Resource
    private DeptService deptService;//系管理接口
    @Resource
    private MajorService majorService;//专业管理接口
    @Resource
    private StudentfallService studentfallService;//届别管理接口

    @RequestMapping("data")
    @ResponseBody
    public JsonData data(@Param("limit")int limit , @Param("page")int page){
        Pager pager = new Pager();
        pager.setPageSize(limit);
        pager.setCurrPage(page);
        jsonData.setCount(studentclassService.selCount());
        jsonData.setData(studentclassService.sybase(pager));
        return jsonData;
    }


    /**
     * @return
     * 去班级添加
     */
    @RequestMapping("/toadd")
    public String toadd(Map map) {
        List<Empinfo> lecturers = empinfoService.selByPostId(Contants.POST_CLASS);
        List<Empinfo> classTeachers = empinfoService.selByPostId(Contants.POST_TEACHER);
        List<Dept> depts = deptService.allDept();
        List<Studentfall> studentFalls = studentfallService.selList();
        map.put("studentFalls",studentFalls);
        map.put("lecturers",lecturers);
        map.put("classTeachers",classTeachers);
        map.put("depts",depts);
        return "student/studentClassAdd";
    }

    /**
     * @return
     * 去班级修改
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(Map map,Integer id) {
        Studentclass studentclass = studentclassService.selectByPrimaryKey(id);
        Empinfo teacher = empinfoService.selectByPrimaryKey(Integer.valueOf(studentclass.getTeacher()));
        Empinfo classTeacher = empinfoService.selectByPrimaryKey(Integer.valueOf(studentclass.getClassteacher()));
        Dept dept = deptService.selectByPrimaryKey(studentclass.getDeptid());
        Studentfall studentfall = studentfallService.selectByPrimaryKey(studentclass.getFalled());
        Major major = majorService.selectByPrimaryKey(studentclass.getMajorid());
        List<Empinfo> lecturers = empinfoService.selByPostId(Contants.POST_CLASS);
        List<Empinfo> classTeachers = empinfoService.selByPostId(Contants.POST_TEACHER);
        List<Dept> depts = deptService.allDept();
        List<Studentfall> studentFalls = studentfallService.selList();

        System.out.println(teacher+""+classTeacher);
        map.put("teacher",teacher);
        map.put("classTeacher",classTeacher);
        map.put("dept",dept);
        map.put("studentfall",studentfall);
        map.put("major",major);

        map.put("studentFalls",studentFalls);
        map.put("lecturers",lecturers);
        map.put("classTeachers",classTeachers);
        map.put("depts",depts);
        map.put("studentClass",studentclass);

        return "student/studentClassUpdate";
    }

    /**
     * @param studentclass
     * @return
     * 添加班级
     */
    @RequestMapping("add")
    @ResponseBody
    public Integer add(Studentclass studentclass){
        List<Studentclass> list = studentclassService.selList();
        for (Studentclass studentclass1:list) {
            if (studentclass.getClassname().equals(studentclass1.getClassname()) || studentclass1.getClassname()==studentclass.getClassname()
            || studentclass.getClassno().equals(studentclass1.getClassno()) || studentclass1.getClassno()==studentclass.getClassno()){
                return 0;
            }
        }
        Integer i = studentclassService.insertSelective(studentclass);
        return i;
    }

    /**
     * @return
     * 去班级页面
     */
    @RequestMapping("list")
    public String list(){
        return "student/studentClass";
    }



    /**
     * @return
     * 删除班级
     */
    @RequestMapping("del")
    public String del(@Param("id") Integer id){
        System.out.println("id = " + id);
       studentclassService.deleteByPrimaryKey(id);
        return "redirect:studentClass/list";
    }


    /**
     * @param id
     * @return 根据系id查找所在该系下的专业
     */
    @RequestMapping("byDeptId")
    @ResponseBody
    public List byDeptId(Integer id) {
        List<Major> l  = majorService.byDeptId(id);
        return l;
    }

    /**
     * @param studentclass
     * @return 修改班级
     */
    @RequestMapping("update")
    @ResponseBody
    public Integer update(Studentclass studentclass) {
        List<Studentclass> list = studentclassService.selList();
        for (Studentclass studentclass1 : list) {
            if (studentclass.getClassid() != null){
                if (studentclass.getClassid() == studentclass1.getClassid()){
                    continue;
                }
            }
            if (studentclass.getClassname().equals(studentclass1.getClassname()) || studentclass1.getClassname() == studentclass.getClassname()
                    || studentclass.getClassno().equals(studentclass1.getClassno()) || studentclass1.getClassno() == studentclass.getClassno()) {
                return 0;
            }
        }
        Integer i = studentclassService.updateByPrimaryKeySelective(studentclass);
        return i;
    }
}
