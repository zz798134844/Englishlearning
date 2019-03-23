package com.example.english_learning;

import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database{
    private static final String TAG = "DataBase";

    private static Connection getconnection(String dbName) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver"); //加载驱动
            String ip = "39.108.140.174";
            conn = DriverManager.getConnection(
                    "jdbc:mysql://" + ip + ":3306/" + dbName+"?useSSL=false",
                    "luoluo", "luo123456");
        } catch (SQLException ex) {
            ex.printStackTrace();
            Log.d(TAG, " 数据操作异常123");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            Log.d(TAG, " 数据操作异常456 ");
        }
        return conn;
    }

    public static String[] queryypw(String sql){
        Connection conn = getconnection("User");
        String[] upw = new String[30];
        try {
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(sql);
            if (!res.next()) {
                return null;
            }else {
                int cnt = res.getMetaData().getColumnCount();
                // res.next();
                for (int i = 1; i <= cnt; ++i) {
                    String field = res.getMetaData().getColumnName(i);
                    upw[i-1] = res.getString(field);
                }
            }
            conn.close();
            st.close();
            res.close();
            return upw;
        }
        catch(Exception e) {
            e.printStackTrace();
            Log.d(TAG, " 数据操作异常1.0");
            return null;
        }
    }

    public static String[] getUserInfoByName(String sql) {
        String web[] = new String[1000];
        Connection conn = getconnection("User");
        try {
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(sql);
            if (!res.next()) {
                return null;
            } else {
                int cnt = res.getMetaData().getColumnCount();
                res.beforeFirst();
                for (int i = 1; i <= cnt; ++i) {
                    int j= 0;
                    while(res.next()){
                        String field = res.getMetaData().getColumnName(i);
                        web[j] = res.getString(field);
                        j++;
                    }
                }
                conn.close();
                st.close();
                res.close();
                int k=0;
                while(web[k]!=null){
                    k++;
                }
                String web1[] = new String[k];
                for(int o=0;o<k;o++){
                    web1[o] = web[o];
                }
                return web1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, " 数据操作异常");
            return null;
        }
    }

}
