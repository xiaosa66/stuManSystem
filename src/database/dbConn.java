package database;

import java.sql.*;


public class dbConn {
    public dbConn() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Statement conn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/stuManagerDB";
            String user = "root";
            String pwd = "xiaosa98";
            Connection con = null;
            con = DriverManager.getConnection(url, user, pwd);
            Statement stat = con.createStatement();
            return stat;
        } catch (ClassNotFoundException ex) {
            System.err.println("未找到相关类异常"+ex);
            return null;
        } catch (SQLException ex1) {
            System.err.println("数据库异常"+ex1);
            return null;
        }
    }

    public ResultSet getRs(String sql) {
        try {
            Statement stat = conn();
            ResultSet rs = stat.executeQuery(sql);
            System.out.println(rs);
            return rs;
        } catch (SQLException ex) {
            System.err.println("------------" + ex.getMessage());
            return null;
        }
    }


    public int getUpdate(String sql) {
        try {
            Statement stat = conn();
            int i = stat.executeUpdate(sql);
            return i;
        } catch (Exception ex) {
            System.out.println(">>>>>>>>" + ex.getMessage());
            return -1;
        }
    }

    private void jbInit() throws Exception {
        conn();
    }

}
