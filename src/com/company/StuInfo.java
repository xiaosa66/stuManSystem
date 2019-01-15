package com.company;

import database.dbConn;

import java.sql.ResultSet;
import java.util.Scanner;

public class StuInfo {
    private String number, name, sdepart, sspec, sclass;
    void getInput(){
        Scanner sc = new Scanner(System.in);
        System.out.println("学生信息录入");
        System.out.print("请输入学号:");
        number = sc.next();
        System.out.print("\n请输入姓名:");
        name = sc.next();
        System.out.print("\n请输入学院:");
        sdepart = sc.next();
        System.out.print("\n请输入专业:");
        sspec = sc.next();
        System.out.print("\n请输入班级:");
        sclass = sc.next();
    }


    int EntryInformation() {
        dbConn con = new dbConn();
        getInput();
        // 学生信息录入
        try {
            if (0 < con.getUpdate("insert into tb_student (stuNumber,stuName,stuDepart,stuSpec,stuClass) values ('"
                    + number + "','" +name + "','" + sdepart + "','" + sspec + "','" + sclass + "')")) {
                System.out.println("学生信息录入成功");
            } else {
                System.err.printf("修改 tb_student 表中 stuNumber = %d 的记录失败\n", number);
            }
        } catch (Exception ce) {
            System.out.println(ce.getMessage());
        }
        return 0;
    }

    int BrowseInfomation() {
        Scanner sc = new Scanner(System.in);
        int choose;
        System.out.println("学生信息查询");
        System.out.println("1:按学号查询:");
        System.out.println("2:按姓名查询:");
        System.out.println("3:按学院查询:");
        System.out.println("4:按专业查询:");
        System.out.println("5:按班级查询:");
//        System.out.println("6:查询全部:");
        choose = sc.nextInt();
        System.out.println("请输入查询条件:");
        String query = sc.next();
        switch (choose) {
            case 1:
                Query("stuNumber", query);
                break;
            case 2:
                Query("stuName", query);
                break;
            case 3:
                Query("stuDepart", query);
                break;
            case 4:
                Query("stuSpec", query);
                break;
            case 5:
                Query("stuClass", query);
                break;
//            case 6:
//                Query("*", query);
//                break;
        }
        return 0;
    }

    // 查询
    void Query(String type, String query) {
        String sql;
        if (!query.trim().equals("")) {
            sql = "select * from tb_student where " + type + "='" + query.trim() + "'";
        } else {
            System.out.println("请输入正确的查询条件！");
            return;
        }
        QuerySQL(sql);
    }

    void QuerySQL(String SQL) {
        dbConn con = new dbConn();
        try {
            ResultSet rs = con.getRs(SQL);
            while (rs.next()) {
                number = rs.getString("stuNumber");
                name = rs.getString("stuName");
                sdepart = rs.getString("stuDepart");
                sspec = rs.getString("stuSpec");
                sclass = rs.getString("stuClass");
                System.out.println(number + "\t\t\t\t" + name + "\t\t\t\t" + sdepart + "\t\t\t\t" + sspec + "\t\t\t\t" + sclass);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void Update() {
        dbConn con = new dbConn();
        System.out.println("请输入需要修改的学号:");
        Scanner sc = new Scanner(System.in);
        String stuNumber = sc.next();
        getInput();
        try {
            if (0 < con.getUpdate("update tb_student set stuNumber = '" + number + "', stuName = '" + name
                    + "', stuDepart = '" + sdepart + "', stuSpec = '" + sspec + "', stuClass = '" + sclass
                    + "' where stuNumber = '" + stuNumber + "'")) {
                System.out.println("学生信息修改成功！");
            } else {
                System.err.printf("修改 tb_student 表中 stuNumber = %d 的记录失败\n", stuNumber);
            }
        } catch (Exception ce) {
            System.out.println(ce.getMessage());
        }
    }


}
