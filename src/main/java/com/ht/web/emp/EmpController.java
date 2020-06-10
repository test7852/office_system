package com.ht.web.emp;
import com.ht.bean.emp.Empinfo;
import com.ht.bean.json.JsonData;
import com.ht.service.emp.EmpinfoService;
import com.ht.util.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 员工管理
 *
 * */
@Controller
@RequestMapping("emp")
public class EmpController {
    @Resource
    private EmpinfoService empinfoService;
    @Resource
    private JsonData jsonData;

    /**
     * @param empinfo
     * @return
     * 登录
     */
    @RequestMapping("login")
    public String login(Empinfo empinfo){
        empinfo.setPhone("1111111");
        empinfo.setPassword("123456");
        Empinfo user = empinfoService.login(empinfo);
        if (user == null){
            System.out.println("失败");
        }
        System.out.println("成功");
        return "managerui/login";
    }

    /**
     * @param
     * @return
     * 去主页
     */
    @RequestMapping("index")
    public String index(){
        return "managerui/index";
    }


    /**
     * @return
     * 去员工资料表
     */
    @RequestMapping("list")
    public String list(){
        return "emp/emplist";
    }

    /**
     * @param limit
     * @param page
     * @return
     * 获取员工资料
     */
    @RequestMapping("empdata")
    @ResponseBody
    public JsonData empdata(@Param("limit")int limit , @Param("page")int page){
        Pager pager = new Pager();
        pager.setPageSize(limit);
        pager.setCurrPage(page);
        jsonData.setCount(empinfoService.selprocount());
        jsonData.setData(empinfoService.allPageEmp(pager));
        return jsonData;
    }


    /**
     * @param empinfo
     * @return
     * 添加
     */
    @RequestMapping("add")
    public String add(Empinfo empinfo){
        empinfoService.insert(empinfo);
        return "";
    }

    /**
     * @param id
     * @return
     * 删除
     */
    @RequestMapping("del")
    public void del(@Param("id") Integer id){
        System.out.println("id = " + id);
        empinfoService.deleteByPrimaryKey(id);
    }

    @RequestMapping("emplistUi")
    public String emplistUi(){
        return "emp/emplist";
    }



    /**
     * @param id
     * @param model
     * @return
     * 去修改
     */
    @RequestMapping("toupdata")
    public String toupdata(Integer id,Model model){
        Empinfo empinfo = empinfoService.selectByPrimaryKey(id);
        return "";
    }

    /**
     * @param empinfo
     * @return
     * 修改
     */
    @RequestMapping("updata")
    public Integer updata(Empinfo empinfo){
        System.out.println("empinfo.toString() = " + empinfo.toString());
        int updatacurr = empinfoService.updateByPrimaryKeySelective(empinfo);
        return updatacurr;
    }
}
