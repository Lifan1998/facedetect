package com.example.face.enmu;

/**
 * @author fan.li
 * @date 2020-03-31
 * @description
 */
public enum LoginType {

    MOBILENO(1, "手机号"),
    EMAIL(2, "email");

    private int value;
    private String desc;

    LoginType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static LoginType fromValue(int value){
        for (LoginType loginType : LoginType.values()) {
            if (loginType.getValue()==value){
                return loginType;
            }
        }
        return EMAIL;
    }

}

