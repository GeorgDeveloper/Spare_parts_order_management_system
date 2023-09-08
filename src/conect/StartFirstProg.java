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

/**
 *
 * @author Егор
 * Первый запуск программы
 */
public class StartFirstProg {

    private Connection Con = null;
    private Statement St = null;
    private ResultSet Rs = null;
    private boolean hasNext = false;

    ConnectDb connect = new ConnectDb();

    //Проверка существования главного пользователя
    public boolean firstConnect() throws SQLException {
        String Query = "SELECT * FROM OPDERSDB.USER where USERNAME='" + "admin" + "'";
        String QueryIn = "INSERT INTO OPDERSDB.USER VALUES('admin', 'admin@admin.ru', 'admin', '2023-08-20 15:55:46','3', 'admin')";
        try {
            Con = connect.getCon();
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

// Проверка пользователя 
    public String validUser(String userName, String pass) {
           String Query = "SELECT * FROM OPDERSDB.USER where USERNAME='" + userName + "'" + "and PASSWORD = '" + pass + "'";

        try {
            Con = connect.getCon();
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

}
