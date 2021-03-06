package com.ht.web.student;

import com.ht.bean.json.JsonData;
import com.ht.bean.student.Studentclass;
import com.ht.bean.student.Studentfloor;
import com.ht.bean.student.Studenthuor;
import com.ht.service.emp.EmpinfoService;
import com.ht.service.student.StudentfallService;
import com.ht.service.student.StudentfloorService;
import com.ht.service.student.StudenthuorService;
import com.ht.util.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author xmf
 * @date 2020/6/9 10:18
 */
@Controller
@RequestMapping("studentHuor")
public class studenthuorController {
    @Resource
    private JsonData jsonData;
    @Resource
    private StudenthuorService studenthuorService;//宿舍管理接口
    @Resource
    private StudentfloorService studentfloorService;//楼栋管理接口

    //表格数据渲染所要的分页数据
    @RequestMapping("data")
    @ResponseBody
    public JsonData data(@Param("limit")int limit , @Param("page")int page){
        Pager pager = new Pager();
        pager.setPageSize(limit);
        pager.setCurrPage(page);
        jsonData.setCount(studenthuorService.selCount());
        jsonData.setData(studenthuorService.sybase(pager));
        return jsonData;
    }

    //去宿舍页面
    @RequestMapping("list")
    public String list(){
        return "student/studentHuor";
    }



    //去添加宿舍页面
    @RequestMapping("/toAdd")
    public String toAdd(Map map) {
        List<Studentfloor> studentFloorList = studentfloorService.selList();
        map.put("studentFloorList",studentFloorList);
        return "student/studentHuorAdd";
    }


    //去修改宿舍页面
    @RequestMapping("/toUpdate")
    public String toUpdate(Map map,int id) {

        List<Studentfloor> studentFloorList = studentfloorService.selList();
        map.put("studentFloorList",studentFloorList);
        Studenthuor studenthuor = studenthuorService.selectByPrimaryKey(id);
        map.put("studenthuor",studenthuor);
        Studentfloor studentfloor = studentfloorService.selectByPrimaryKey(studenthuor.getFloorid());
        map.put("studentfloor",studentfloor);
        System.out.println(studentfloor+"2222222           "+studenthuor.getFloorid());
        return "student/studentHuorUpdate";
    }

    //添加宿舍
    @RequestMapping("/add")
    @ResponseBody
    public Integer add(Studenthuor studenthuor) {
        Studentfloor studentfloor = studentfloorService.selectByPrimaryKey(studenthuor.getFloorid());
        studenthuor.setHuoeiddsc(studentfloor.getFloorname()+""+studenthuor.getHuorname());
        List<Studenthuor> list = studenthuorService.selList();
        for (Studenthuor studenthuor1:list) {
            if (studenthuor.getHuorname().equals(studenthuor1.getHuorname()) || studenthuor1.getHuorname()==studenthuor.getHuorname()
                   ){
                return 0;
            }
        }
        Integer i = studenthuorService.insert(studenthuor);
        return i;
    }

    //修改宿舍
    @RequestMapping("/update")
    @ResponseBody
    public Integer upd(Studenthuor studenthuor){
        Studentfloor studentfloor = studentfloorService.selectByPrimaryKey(studenthuor.getFloorid());
        studenthuor.setHuoeiddsc(studentfloor.getFloorname()+""+studenthuor.getHuorname());
        int i = studenthuorService.updateByPrimaryKeySelective(studenthuor);
        return i;
    }


    //删除宿舍
    @RequestMapping("/del")
    public String del(Integer id) {
        System.out.println(id);
        studenthuorService.deleteByPrimaryKey(id);
        return "redirect:studentHuor/list";
    }

}
