package com.example.face.enmu;

/**
 * @author fan.li
 * @date 2020-04-03
 * @description
 */

public enum UserType {

    STUDENT(1, "学生"),
    TEACHER(2, "老师");

    private int value;
    private String desc;

    UserType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static UserType fromValue(int value){
        for (UserType userType : UserType.values()) {
            if (userType.getValue()==value){
                return userType;
            }
        }
        return TEACHER;
    }

}

