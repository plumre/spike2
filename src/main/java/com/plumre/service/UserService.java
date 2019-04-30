package com.plumre.service;

/*
 * Created by renhongjiang on 2019/3/25.
 */

import com.plumre.common.BusinessException;
import com.plumre.service.model.UserModel;

import java.security.NoSuchAlgorithmException;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/3/25 12:27
 */
public interface UserService {

    UserModel getUserById(Integer id);

    void register(UserModel userModel) throws BusinessException;

    UserModel login(String mobile, String password) throws BusinessException, NoSuchAlgorithmException;
}