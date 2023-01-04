package moe.xmcn.catsero.utils;

import moe.xmcn.catsero.Configuration;
import moe.xmcn.catsero.utils.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WhiteListDatabase {

    /**
     * 初始化数据库
     */
    public static void initDatabase() {
        Connection c;
        Statement sm;
        try {
            Class.forName(Configuration.PLUGIN.JDBC.CLASS_NAME);
            c = DriverManager.getConnection(
                    "jdbc:sqlite:" +
                            Configuration.plugin.getDataFolder() +
                            "/whitelist.db"
            );
            c.setAutoCommit(false);
            sm = c.createStatement();

            String cmd = "create TABLE if not exists RECORDS " +
                    "(NAME TEXT not NULL)";
            sm.executeUpdate(cmd);

            sm.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            Logger.logCatch(e);
        }
    }

    /**
     * 获取白名单列表
     * @return  所有在白名单的玩家名
     */
    public List<String> getList() {
        Connection c;
        Statement sm;
        List<String> list = new ArrayList<>();
        try {
            Class.forName(Configuration.PLUGIN.JDBC.CLASS_NAME);
            c = DriverManager.getConnection(
                    "jdbc:sqlite:" +
                            Configuration.plugin.getDataFolder() +
                            "/whitelist.db"
            );
            c.setAutoCommit(false);
            sm = c.createStatement();

            ResultSet rs = sm.executeQuery( "select * from RECORDS;" );
            while (rs.next()) {
                list.add(rs.getString("name"));
            }

            sm.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            Logger.logCatch(e);
        }
        return list;
    }

    public boolean insertList(String name) {
        Connection c;
        Statement sm;
        try {
            Class.forName(Configuration.PLUGIN.JDBC.CLASS_NAME);
            c = DriverManager.getConnection(
                    "jdbc:sqlite:" +
                            Configuration.plugin.getDataFolder() +
                            "/whitelist.db"
            );
            c.setAutoCommit(false);
            sm = c.createStatement();

            String cmd = "insert into RECORDS" +
                    " values (\"" + name + "\")";
            sm.executeUpdate(cmd);

            sm.close();
            c.commit();
            c.close();
            return true;
        } catch (Exception e) {
            Logger.logCatch(e);
            return false;
        }
    }

    public boolean updateList(String old_name, String new_name) {
        Connection c;
        Statement sm;
        try {
            Class.forName(Configuration.PLUGIN.JDBC.CLASS_NAME);
            c = DriverManager.getConnection(
                    "jdbc:sqlite:" +
                            Configuration.plugin.getDataFolder() +
                            "/whitelist.db"
            );
            c.setAutoCommit(false);
            sm = c.createStatement();

            String cmd = "update RECORDS" +
                    " set NAME = \"" + new_name + "\"" +
                    " where NAME = \"" + old_name + "\"";
            sm.executeUpdate(cmd);

            sm.close();
            c.commit();
            c.close();
            return true;
        } catch (Exception e) {
            Logger.logCatch(e);
            return false;
        }
    }

    public boolean removeList(String name) {
        Connection c;
        Statement sm;
        try {
            Class.forName(Configuration.PLUGIN.JDBC.CLASS_NAME);
            c = DriverManager.getConnection(
                    "jdbc:sqlite:" +
                            Configuration.plugin.getDataFolder() +
                            "/whitelist.db"
            );
            c.setAutoCommit(false);
            sm = c.createStatement();

            String cmd = "delete from RECORDS" +
                    " where NAME = \"" + name + "\"";
            sm.executeUpdate(cmd);

            sm.close();
            c.commit();
            c.close();
            return true;
        } catch (Exception e) {
            Logger.logCatch(e);
            return false;
        }
    }

}