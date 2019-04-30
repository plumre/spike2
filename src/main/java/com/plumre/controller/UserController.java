package com.plumre.controller;

/*
 * Created by renhongjiang on 2019/3/25.
 */

import com.alibaba.druid.util.StringUtils;
import com.plumre.common.BaseController;
import com.plumre.common.BusinessErrorEnum;
import com.plumre.common.BusinessException;
import com.plumre.common.CommonReturnType;
import com.plumre.controller.viewobject.UserVO;
import com.plumre.dao.UserDOMapper;
import com.plumre.dataobject.UserDO;
import com.plumre.service.UserService;
import com.plumre.service.model.UserModel;
import com.plumre.util.MD5Utils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/3/25 10:29
 */
@Controller("user")
@RequestMapping("/user")
@CrossOrigin(allowedHeaders = "{*}", allowCredentials = "true")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserDOMapper userDOMapper;

    @RequestMapping("/")
    public String home() {
        UserDO userDO = userDOMapper.selectByPrimaryKey(1);
        if (userDO == null) {
            return "用户对象不存在";
        } else {
            return userDO.toString();
        }
    }

    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam("id") Integer id) throws BusinessException {
        UserModel userModel = userService.getUserById(id);
        if (userModel == null) {
            /**
             * show unknown error
             */
            // userModel.getAge();
            /**
             * show business error
             */
            throw new BusinessException(BusinessErrorEnum.USER_NOT_EXISTS);
        }
        UserVO userVO = convertFromModel(userModel);
        return CommonReturnType.create(userVO);
    }

    private UserVO convertFromModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }

    @RequestMapping(value = "/getOtp")
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam("mobile") String mobile) {
        // generate opt
        Random random = new Random();
        int anInt = random.nextInt(999999);
        anInt += 100000;
        String otp = String.valueOf(anInt);
        // prefer to use redis
        // bind mobile and otp
        request.getSession().setAttribute(mobile, otp);
        // send user otp on the text message channel
        System.out.println(mobile + " : " + otp);
        return CommonReturnType.create(null);
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType register(@RequestParam("mobile") String mobile,
                                     @RequestParam("otp") String otp,
                                     @RequestParam("name") String name,
                                     @RequestParam("gender") Byte gender,
                                     @RequestParam("age") Integer age,
                                     @RequestParam("password") String password
    ) throws BusinessException, NoSuchAlgorithmException {
        String otpInSession = (String) this.request.getSession().getAttribute(mobile);
        if (!StringUtils.equals(otp, otpInSession)) {
            throw new BusinessException(BusinessErrorEnum.PARAMETER_INVALIDATION, "短信验证码不正确");
        }
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setMobile(mobile);
        userModel.setGender(gender);
        userModel.setAge(age);
        userModel.setRegisterMode("bymobile");
        userModel.setEncryptPassword(MD5Utils.encodeByMD5(password));
        userService.register(userModel);
        return CommonReturnType.create(null);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType login(@RequestParam("mobile") String mobile,
                                  @RequestParam("password") String password) throws BusinessException, NoSuchAlgorithmException {
        if (org.apache.commons.lang3.StringUtils.isEmpty(mobile) || org.apache.commons.lang3.StringUtils.isEmpty(password)) {
            throw new BusinessException(BusinessErrorEnum.PARAMETER_INVALIDATION);
        }
        UserModel userModel = userService.login(mobile,password);

        // 加入session
        this.request.getSession().setAttribute("IS_LOGIN", true);
        this.request.getSession().setAttribute("LOGIN_USER", userModel);
        return CommonReturnType.create(null);
    }

}