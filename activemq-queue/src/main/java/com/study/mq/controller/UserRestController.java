package com.study.mq.controller;

import com.study.mq.ScheduledTask.AsyncTask;
import com.study.mq.exception.CustomException;
import com.study.mq.services.IUserService;
import com.study.mq.vo.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;

/**
 * 消息产生者
 */
@RestController
@RequestMapping("/restUser")
public class UserRestController {

    private final static Logger log = LoggerFactory.getLogger(UserRestController.class);

    //本地用户服务注入
    @Autowired
    private IUserService userService;

    //异步任务bean注入
    @Autowired
    private AsyncTask asyncTask;

    @ApiOperation(value="测试", notes="根据url的msg作为测试信息")
    @ApiImplicitParam(name = "msg", value = "msg", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value = "test2/{msg}",method = RequestMethod.GET)
    public String test2(@PathVariable(value = "msg") String msg){
        return msg;
    }

    /**
     * @name mvc跳转测试，方式@RequestMapping
     * @param msg
     * @return
     */
    @RequestMapping("/test3")
    public String test3(String msg){
        return "b";
    }

    /**
     * @name 传参测试 @PostMapping
     * @param user
     * @return
     * @des form-data 方式可以提交
     * @des2 Content-Type 为 application/json 也可以
     */
    @PostMapping(path = "/test4")
    public String test4(User user){
        String msg = "";
        System.out.println(user.toString());
        return msg;
    }

    /**
     * @name 上传get方式
     * @return
     * @des   注意：Content-Type为：multipart/form-data
     */
    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    /**
     * @name 上传测试post方式
     * @param file
     * @return
     * @des MultipartFile
     */
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        String fileName = file.getOriginalFilename();
        String filePath = "d:/";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);//上传方法
            log.info("上传成功");
            return "上传成功";
        } catch (IOException e) {
            log.error(e.toString(), e);
        }
        return "上传失败！";
    }

    /**
     * @name 测试异步任务方法
     * @param request request对象
     * @return
     */
    @GetMapping(path = "/test5")
    public String test5(HttpServletRequest request){
        String msg = request.getParameter("msg");
        List<User> list = userService.selectAll();
        log.error(list.toString());
        try {
            Future<Boolean> future = asyncTask.doTask11();
            log.info("任务是否完成："+future.isDone());
            Thread.sleep(5000);
            log.info("任务是否完成："+future.isDone());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return "b";
    }

    /**
     * @name 测试全局异常处理
     * @param num
     * @return
     */
    @GetMapping("/test6")
    public String test3(Integer num) {
        // TODO 演示需要，实际上参数是否为空通过 @RequestParam(required = true)  就可以控制
        if (num == null) {
            throw new CustomException(400, "num不能为空");
        }
        int i = 10 / num;
        return "result:" + i;
    }

    /**
     * @name freemarker 简单的跳转ftl页
     * @param modelMap 处理工具：ModelMap对象主要用于传递控制方法处理数据到结果页面
     * @return
     */
    @RequestMapping("/test7")
    public String testFreemarker(ModelMap modelMap){
        modelMap.addAttribute("msg", "Hello dalaoyang , this is freemarker");
        return "freemarker";
    }


}
