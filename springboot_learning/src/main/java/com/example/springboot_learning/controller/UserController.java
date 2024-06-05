package com.example.springboot_learning.controller;

import com.example.springboot_learning.model.responseInfo.ResponseInfo;
import com.example.springboot_learning.model.user.UserInfo;
import com.example.springboot_learning.model.user.UserParamInfo;
import com.example.springboot_learning.model.user.UserParamLoginInfo;
import com.example.springboot_learning.pojo.User;
import com.example.springboot_learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    /*
     *   总结：
     *   1. 请求参数是一个对象，所以就算只有一个参数也要放到一个对象中，这里就需要创建一个参数实体类了；
     *   2. 请求参数需要添加@RequestBody注解，否则接口接收不到参数传递过来的数据
     *   3. 最好使用RequestMapping来设置value和method，否则可能有问题
     *
     * */

    /**
     * 新增用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResponseInfo addUser(@RequestBody User user) {
        int result = userService.addUser(user);
        ResponseInfo responseInfo = ResponseInfo.responseInfoResult(result == 1, user);

        return responseInfo;
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
    public ResponseInfo updateUser(@RequestBody User user) {
        int result = userService.updateUser(user);
        ResponseInfo responseInfo = ResponseInfo.responseInfoResult(result == 1, null);

        return responseInfo;
    }

    @RequestMapping(value = "/disabledUser", method = RequestMethod.PUT)
    public ResponseInfo disabledUser(@RequestBody UserParamInfo userParamInfo) {
        int result = userService.disabledUser(userParamInfo.getUserId());
        ResponseInfo responseInfo = ResponseInfo.responseInfoResult(result == 1, null);

        return responseInfo;
    }

    @RequestMapping(value = "/selectAllUserList", method = RequestMethod.POST)
    public ResponseInfo selectAllUserList() {
        List<UserInfo> userInfoList = userService.selectAllUserList();
        ResponseInfo responseInfo = ResponseInfo.responseInfoSuccess(userInfoList);

        return responseInfo;
    }

    @RequestMapping(value = "/selectUserByUserId", method = RequestMethod.POST)
    public ResponseInfo selectUserByUserId(@RequestBody UserParamInfo userParamInfo) {
        UserInfo userInfo = userService.selectUserByUserId(userParamInfo.getUserId());
        ResponseInfo responseInfo = ResponseInfo.responseInfoSuccess(userInfo);

        return responseInfo;
    }

    @RequestMapping(value = "/selecUserByUserParamInfo", method = RequestMethod.POST)
    public ResponseInfo selecUserByUserParamInfo(@RequestBody UserParamInfo userParamInfo) {
        List<UserInfo> userInfoList = userService.selecUserByUserParamInfo(userParamInfo);
        ResponseInfo responseInfo = ResponseInfo.responseInfoSuccess(userInfoList);

        return responseInfo;
    }

    @RequestMapping(value = "/loginWithUserNamePassword", method = RequestMethod.POST)
    public ResponseInfo loginWithUserNamePassword(@RequestBody UserParamLoginInfo userParamLoginInfo) {
        UserInfo userInfo = userService.loginWithUserNamePassword(userParamLoginInfo);
        ResponseInfo responseInfo = ResponseInfo.responseInfoSuccess(userInfo);

        return responseInfo;
    }





}
