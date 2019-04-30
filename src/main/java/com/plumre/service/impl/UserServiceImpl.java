package com.plumre.service.impl;

/*
 * Created by renhongjiang on 2019/3/25.
 */

import com.plumre.common.BusinessErrorEnum;
import com.plumre.common.BusinessException;
import com.plumre.dao.UserDOMapper;
import com.plumre.dao.UserPasswordDOMapper;
import com.plumre.dataobject.UserDO;
import com.plumre.dataobject.UserPasswordDO;
import com.plumre.service.UserService;
import com.plumre.service.model.UserModel;
import com.plumre.util.MD5Utils;
import com.plumre.validator.ValidationResult;
import com.plumre.validator.ValidatorImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/3/25 12:27
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;

    @Autowired
    private ValidatorImpl validator;

    @Override
    public UserModel getUserById(Integer id) {
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        if (userDO == null) {
            return null;
        }
        // get password
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
        return convertFromDataObject(userDO, userPasswordDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(UserModel userModel) throws BusinessException {
//        Map<String, String> propsMap = new HashMap<16>();
//        Properties properties = null;
//        try {
//            properties = PropertiesLoaderUtils.loadAllProperties("aa.xml");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        properties.forEach((key, value) -> propsMap.put(key.toString(), new String(value.toString().getBytes(), StandardCharsets.UTF_8)));
        if (userModel == null) {
            throw new BusinessException(BusinessErrorEnum.PARAMETER_INVALIDATION);
        }
//        if (userModel.getAge() == null
//                || userModel.getGender() == null
//                || StringUtils.isEmpty(userModel.getMobile())
//                || StringUtils.isEmpty(userModel.getName())) {
//            throw new BusinessException(BusinessErrorEnum.PARAMETER_INVALIDATION);
//        }
        ValidationResult result = validator.validate(userModel);
        if (result.isHasErrors()) {
            throw new BusinessException(BusinessErrorEnum.PARAMETER_INVALIDATION, result.getErrorMessage());
        }
        UserDO userDO = convertFromModel(userModel);
        try {
            userDOMapper.insertSelective(userDO);
        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessErrorEnum.PARAMETER_INVALIDATION, "该手机号已注册");
        }

        UserPasswordDO passwordDO = new UserPasswordDO();
        passwordDO.setUserId(userDO.getId());
        passwordDO.setEncryptPassword(userModel.getEncryptPassword());
        userPasswordDOMapper.insertSelective(passwordDO);
    }

    @Override
    public UserModel login(String mobile, String password) throws BusinessException, NoSuchAlgorithmException {
        UserDO userDO = userDOMapper.selectByMobile(mobile);
        if (userDO == null) {
            throw new BusinessException(BusinessErrorEnum.USER_LOGIN_FAIL);
        }
        UserPasswordDO passwordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
        UserModel userModel = convertFromDataObject(userDO, passwordDO);
        if (!com.alibaba.druid.util.StringUtils.equals(MD5Utils.encodeByMD5(password), userModel.getEncryptPassword())) {
            throw new BusinessException(BusinessErrorEnum.USER_LOGIN_FAIL);
        }
        return userModel;
    }

    private UserDO convertFromModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userModel, userDO);
        return userDO;
    }

    private UserModel convertFromDataObject(UserDO userDO, UserPasswordDO userPasswordDO) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO, userModel);
        if (userPasswordDO != null) {
            userModel.setEncryptPassword(userPasswordDO.getEncryptPassword());
        }
        return userModel;
    }
}