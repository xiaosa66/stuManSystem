package com.company;

import database.dbConn;

import java.sql.ResultSet;

public class Login {
    boolean login(String userName, String password) {
        dbConn con = new dbConn();
        boolean success = false;
        // 异常判断
        if (userName.length() == 0) {
            System.out.println("用户名不能为空");
        } else {
            try {
                ResultSet rs = con.getRs("select * from tb_user where userName='" + userName + "'");
                while (rs.next()) {
                    if (rs.getString("userPwd").trim().equals(password)) {
                        System.out.println("登录成功");
                        success = true;
                    } else {
                        System.out.println("用户名或密码错误");
                    }
                }
                rs.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        if (success) {
            return true;
        } else {
            return false;

        }
    }
}
