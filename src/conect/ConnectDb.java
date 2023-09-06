/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import view.AdminSetting;
import view.CustomerView;

/**
 *
 * @author Егор
 */
public class ConnectDb {

    // + разделить класс на подключение и стартовый метод
    // + добавить запрос имени БД
    // + Все данные получать из конфигфайла
    private Connection Con = null;
    private Statement St = null, St1 = null;
    private ResultSet Rs = null, Rs1 = null;
    private String url = "";
    private String username = "";
    private String password = "";
    private boolean hasNext = false;

    public boolean firstConnect() throws SQLException {
        String Query = "SELECT * FROM OPDERSDB.USER where USERNAME='" + "admin" + "'";
        String QueryIn = "INSERT INTO OPDERSDB.USER VALUES('admin', 'admin@admin.ru', 'admin', '2023-08-20 15:55:46','3', 'admin')";
        try {
            Con = DriverManager.getConnection(url, username, password);
            St = Con.createStatement();
            Rs = St.executeQuery(Query);
            if (Rs.next()) {
                return true;
            } else {
                St.executeUpdate(QueryIn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public String validUser(String userName, String pass) {
        String Query = "SELECT * FROM OPDERSDB.USER where USERNAME='" + userName + "'" + "and PASSWORD = '" + pass + "'";

        try {
            Con = DriverManager.getConnection(url, username, password);
            St = Con.createStatement();
            Rs = St.executeQuery(Query);
            if (Rs.next()) {
                hasNext = true;
                String userType = Rs.getString("TYPE");
                return userType;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public Connection getCon() {
        return Con;
    }

    public Statement getSt() {
        return St;
    }

    public Statement getSt1() {
        return St1;
    }

    public ResultSet getRs() {
        return Rs;
    }

    public ResultSet getRs1() {
        return Rs1;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    

}
