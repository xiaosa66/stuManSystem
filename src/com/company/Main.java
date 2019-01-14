package com.company;

import java.util.Scanner;


class DisplayServers {
    boolean Welcome() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请登录");
        System.out.print("请输入用户名:");
        String userName = sc.next();
        System.out.print("\n请输入密码:");
        String password = sc.next();
        Login login = new Login();
        return (login.login(userName, password));
    }

    int Menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("学生管理系统:");
        System.out.println("1、信息录入:");
        System.out.println("2、信息浏览:");
        System.out.println("3、信息查询:");
        System.out.println("4、信息修改:");
//        System.out.println("5、成绩录入:");
//        System.out.println("6、成绩浏览:");
//        System.out.println("7、成绩查询:");
//        System.out.println("8、信息修改:");
        System.out.println("9、退出登录:");
        return sc.nextInt();
    }
}

public class Main {

    public static void main(String[] args) {
        System.out.println("欢迎来到北京联合大学学生信息管理系统");
        DisplayServers display = new DisplayServers();
        StuInfo stu = new StuInfo();
        boolean logined = false;
        while (true) {
            if (logined) {
                switch (display.Menu()) {
                    case 1:
                        stu.EntryInformation();
                        break;
                    case 2:

                    case 3:
                        stu.BrowseInfomation();
                        break;
                    case 4:
                        stu.Update();
                        break;
//                    case 5:
//                        break;
//                    case 6:
//                        break;
//                    case 7:
//                        break;
//                    case 8:
//                        break;
                    case 9:
                        logined = false;
                        break;
                }
            } else {
                logined = display.Welcome();
            }
        }

    }

}
