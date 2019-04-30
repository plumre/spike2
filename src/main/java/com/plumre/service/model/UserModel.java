package com.plumre.service.model;

/*
 * Created by renhongjiang on 2019/3/25.
 */

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/3/25 12:31
 */
public class UserModel {

    private Integer id;
    @NotBlank(message = "用户名不能为空")
    private String name;
    @NotNull(message = "性别还没选择呢")
    private Byte gender;
    @NotNull(message = "你的年龄不是秘密")
    @Min(value = 0, message = "你的年龄貌似来自外太空")
    @Max(value = 150, message = "你是从长寿村来的吗")
    private Integer age;
    @NotNull(message = "你是让我猜你的手机号吗")
    private String mobile;
    private String registerMode;
    private String thirdPartyId;
    private Date gmtCreate;
    private Date gmtModified;
    @NotNull(message = "密码貌似还没有填写")
    private String encryptPassword;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRegisterMode() {
        return registerMode;
    }

    public void setRegisterMode(String registerMode) {
        this.registerMode = registerMode;
    }

    public String getThirdPartyId() {
        return thirdPartyId;
    }

    public void setThirdPartyId(String thirdPartyId) {
        this.thirdPartyId = thirdPartyId;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getEncryptPassword() {
        return encryptPassword;
    }

    public void setEncryptPassword(String encryptPassword) {
        this.encryptPassword = encryptPassword;
    }
}