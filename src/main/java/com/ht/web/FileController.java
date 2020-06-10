package com.ht.web;

import com.ht.bean.Datadoc;
import com.ht.util.Contants;
import com.ht.util.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @author 王钟华
 * @date 2020-06-09 10:15
 */
@Controller
@RequestMapping("file")
public class FileController {
    @RequestMapping("add")
    public String add(MultipartFile file) throws IOException {
        Datadoc datadoc = Utils.fileUpload(file);
        System.out.println(datadoc);
        return "redirect:/file/toAdd";
    }
    @RequestMapping("toAdd")
    public String add(){
        return "file";
    }

    //实现Spring Boot 的文件下载功能，映射网址为/download
    @RequestMapping("/download")
    public String downloadFile(HttpServletRequest request,
                               HttpServletResponse response) throws UnsupportedEncodingException {

        // 获取指定目录下的第一个文件
        File scFileDir = new File(Contants.PRO_FILE_SAVE_PATH);
        File TrxFiles[] = scFileDir.listFiles();
        System.out.println(TrxFiles[0]);
        String fileName = TrxFiles[0].getName(); //下载的文件名

        // 如果文件名不为空，则进行下载
        if (fileName != null) {
            //设置文件路径
            String realPath = Contants.PRO_FILE_SAVE_PATH;
            File file = new File(realPath, fileName);

            // 如果文件名存在，则进行下载
            if (file.exists()) {
                System.out.println("11111111111111111111111111111111111111111");
                // 配置文件下载
                response.setHeader("content-type", "application/octet-stream");
                response.setContentType("application/octet-stream");
                // 下载文件能正常显示中文
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

                // 实现文件下载
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("下载成功!");
                }
                catch (Exception e) {
                    System.out.println("下载失败！");
                }
                finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }
}