package com.company;

import database.dbConn;

import java.sql.ResultSet;
import java.util.Scanner;

public class StuGrade {

    private String stuNumber, courceName, score, stuName = "";

    void getInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("学生成绩录入");
        System.out.print("请输入学号:");
        stuNumber = sc.next();
        System.out.print("\n请输入课程名:");
        courceName = sc.next();
        System.out.print("\n请输入成绩:");
        score = sc.next();
    }


    void EntryGrade() {
        dbConn con = new dbConn();
        getInput();
        // 课程信息录入
        if (stuNumber.length() == 0) {
            System.out.println("学生学号不能为空");
        } else if (courceName.length() == 0) {
            System.out.println("课程名称不能为空");
        } else if (score.length() == 0) {
            System.out.println("成绩不能为空");
        } else {
            try {
                if (0 < con.getUpdate("insert into tb_score (stuNumber,courceName,score) values ('"
                        + stuNumber + "','" + courceName + "','" + score + "')")) {
                    System.out.println("课程信息录入成功");
                } else {
                    System.err.printf("修改 tb_score 表中 stuNumber = %d 的记录失败\n", stuNumber);
                }
            } catch (Exception ce) {
                System.out.println(ce.getMessage());
            }
        }
    }


    int QueryGrade() {
        Scanner sc = new Scanner(System.in);
        int choose;
        System.out.println("学生成绩查询");
        System.out.println("1:按学号查询:");
        System.out.println("2:按课程查询:");
        choose = sc.nextInt();
        System.out.println("请输入查询条件:");
        String query = sc.next();
        switch (choose) {
            case 1:
                calcueateGrade("stuNumber", query);
                Query("stuNumber", query);
                break;
            case 2:
                calcueateGrade("courceName", query);
                Query("courceName", query);
                break;
        }
        return 0;
    }

    // 查询成绩信息
    void Query(String type, String query) {
        String sql;
        if (!query.trim().equals("")) {
            sql = "select * from tb_score where " + type + "='" + query.trim() + "'";
        } else {
            System.out.println("请输入正确的查询条件！");
            return;
        }
        QuerySQL(sql, false);

    }

    void QuerySQL(String SQL, boolean avg) {
        dbConn con = new dbConn();
        try {
            ResultSet rs = con.getRs(SQL);
            while (rs.next()) {
                if (avg) {
                    score = rs.getString("avgScore");
                    System.out.println("平均分:\t\t\t\t" + score + "\t\t\t\t");
                } else {
                    stuNumber = rs.getString("stuNumber");
                    courceName = rs.getString("courceName");
                    score = rs.getString("score");
                    System.out.println(stuNumber + "\t\t\t\t" + courceName + "\t\t\t\t" + score + "\t\t\t\t");
                }
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    int calcueateGrade(String type,String course) {
        String SQL = "select AVG(score) as avgScore from tb_score where " + type + "='"  + course.trim() + "'";
        QuerySQL(SQL, true);
        return 0;
    }

    int calcueateGrade() {
        String SQL = "select AVG(score) AS avgScore from tb_score";
        QuerySQL(SQL, true);
        return 0;


    }


    void Update() {
        dbConn con = new dbConn();
        getInput();
        try {
            if (0 < con.getUpdate("update tb_score set stuNumber = '" + stuNumber + "', courceName = '" + courceName
                    + "', score = '" + score
                    + "' where stuNumber = '" + stuNumber + "'")) {
                System.out.println("学生信息修改成功！");
            } else {
                System.err.printf("修改 tb_student 表中 stuNumber = %d 的记录失败\n", stuNumber);
            }
        } catch (Exception ce) {
            System.out.println(ce.getMessage());
        }
    }

    void InC() {
        // 异常判断
        dbConn con = new dbConn();
        getInput();
        if (stuNumber.length() == 0) {
            System.out.println("学生学号不能为空");
        } else if (courceName.length() == 0) {
            System.out.println("课程名称不能为空");
        } else if (score.length() == 0) {
            System.out.println("成绩不能为空");
        } else {
            try {
                boolean courceNameExzist = false;
                ResultSet rs = con.getRs("select stuNumber from tb_score where courceName='"
                        + courceName + "'");
                while (rs.next()) {
                    if (stuNumber.trim().equals(rs.getString("stuNumber").trim())) {
                        courceNameExzist = true;
                    }
                }
                if (courceNameExzist) {
                    System.out.println("课程名称已经存在！");
                } else {
                    con.getUpdate("insert into tb_score (stuNumber,score,courceName) values ('"
                            + stuNumber.trim() + "','" + score.trim() + "','"
                            + courceName.trim() + "')");
                    System.out.println("课程信息提交成功！");
                }
                rs.close();
            } catch (Exception ce) {
                System.out.println("--------" + ce);
            }
        }
    }


//    void calculateUnpass() {
//        dbConn con = new dbConn();
//
//        int totalNumber = 0, failureNumber = 0;
//        try {
//            // 获取总人数
//            ResultSet rs = con.getRs("select * from tb_score");
//            while (rs.next()) {
//                totalNumber++;
//            }
//
//            // 获取低于60分（不及格）人数
//            ResultSet rs_failure = con.getRs("select * from tb_score where score<'" + 60 + "'");
//            while (rs_failure.next()) {
//                failureNumber++;
//            }
//        } catch (SQLException e1) {
//            System.out.println(e1);
//        }
//
//        // 显示不及格率
//        System.out.println("不及格率  = " + (float) failureNumber / totalNumber * 100 + "%\n不及格人数有 "
//                + failureNumber + "人\n总人数有 " + totalNumber + "人");
//    }


}
