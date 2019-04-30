package com.plumre.controller.viewobject;

/*
 * Created by renhongjiang on 2019/3/25.
 */

import java.util.Date;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/3/25 12:31
 */
public class UserVO {

    private Integer id;
    private String name;
    private Byte gender;
    private Integer age;
    private String mobile;
    private Date gmtCreate;
    private Date gmtModified;


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

}