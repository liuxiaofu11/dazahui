package com.tengda.dazahui.system.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.Vector;

/**
 * @Author teswell
 * @Date 2020/11/16 14:51
 * @function
 */
public class DuoXianCheng {
    public static void main(String[] args) throws Exception {

        Dao d1 = new Dao("jdbc:mysql://127.0.0.1:3306/ceshi", "select * from medical");
        Thread t1 = new Thread(d1);
        t1.start();
        Dao d2 = new Dao("jdbc:mysql://127.0.0.1:3306/ceshi", "select * from medical");
        Thread t2 = new Thread(d2);
        t2.start();
        t1.join();
        t2.join();
        for (Hashtable<String, Object> hso : d1.vhso) {
            System.out.println(hso.get("userid").toString() + hso.get("username") + hso.get("password"));
        }
        for (Hashtable<String, Object> hso : d2.vhso) {
            System.out.println(hso.get("userid").toString() + hso.get("username") + hso.get("password"));
        }
    }
}

class Dao implements Runnable {
    private Connection c = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    Vector<Hashtable<String, Object>> vhso = new Vector<>();
    private String connstr = null;
    private String sql = null;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Dao(String connstr, String sql) {
        this.connstr = connstr;
        this.sql = sql;
    }

    @Override
    public void run() {
        try {
            c = DriverManager.getConnection(connstr, "root", "");
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Hashtable<String, Object> hso = new Hashtable<>();
                hso.put("userid", rs.getInt(1));
                hso.put("username", rs.getString(2));
                hso.put("password", rs.getString(3));
                vhso.add(hso);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
