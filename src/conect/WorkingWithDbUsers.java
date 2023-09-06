/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import managerdatabase.ProcessingTables;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Егор
 */
public class WorkingWithDbUsers implements ProcessingTables {

    String userN;
    String mail;
    String pass;
    String id;
    String type;

    private Connection Con = null;
    private Statement St = null;
    private ResultSet Rs = null;

    ConnectDb connect = new ConnectDb();

    public WorkingWithDbUsers(String userN, String mail, String pass, String id, String type) {
        this.userN = userN;
        this.mail = mail;
        this.pass = pass;
        this.id = id;
        this.type = type;
    }

    public WorkingWithDbUsers() {
    }

    public ResultSet SelectTab(String table) throws SQLException {
        Con = DriverManager.getConnection(connect.getUrl(), connect.getUsername(), connect.getPassword());
        St = Con.createStatement();
        Rs = St.executeQuery("SELECT * FROM OPDERSDB." + table);
        return Rs;

    }

    /**
     *
     * @param userN
     * @param userN
     */
    public boolean validNewUser(String id, String name) {
//        Версия для проверки id
//        try {
//            String Query = "SELECT ID FROM OPDERSDB.USER WHERE ID=" + id;
//            Con = DriverManager.getConnection(connect.getUrl(), connect.getUsername(), connect.getPassword());
//            Statement statement = Con.createStatement();
//            ResultSet results = statement.executeQuery(Query);
//            if (results.next()) {
//                int aid = results.getInt("ID");
//                if (aid == Integer.parseInt(id)) {
//                    return false;
//                }
//            } else {
//                return true;
//            }
//        } catch (SQLException e) {
//            return false;
//        }
//        return false;
        try {
            String Query = "SELECT ID FROM OPDERSDB.USER WHERE ID=" + id + " OR " + "USERNAME='" + name + "'";
            Con = DriverManager.getConnection(connect.getUrl(), connect.getUsername(), connect.getPassword());
            Statement statement = Con.createStatement();
            ResultSet results = statement.executeQuery(Query);
            if (results.next()) {
                while (results.next()) {
                    int aid = results.getInt("ID");
                    String aname = results.getString("USERNAME");
                    System.out.print(aid);
                    System.out.print(aname);
                    if (aid == Integer.parseInt(id) || aname.equalsIgnoreCase(name)) {
                        return false;
                    }
                }
            } else {
                return true;
            }

        } catch (SQLException e) {
        }
        return false;
    }

    @Override
    public void delData(String id) {
        try {
            Con = DriverManager.getConnection(connect.getUrl(), connect.getUsername(), connect.getPassword());
            String Query = "Delete from OPDERSDB.USER where ID=" + id;
            Statement Add = Con.createStatement();
            Add.executeUpdate(Query);
            SelectTab("USER");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateData() {
        Date date = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
        String UpdateQuery = "UPDATE OPDERSDB.USER set USERNAME='"
                        + userN + "'" + ", EMAIL='" + mail + "'"
                        + ", PASSWORD='" + pass + "'" + ",CREATE_TIME='" + formatForDateNow.format(date) + "'"
                        + ", TYPE='" + type + "'" + "where ID='" + id +"'";
        try {
            Con = DriverManager.getConnection(connect.getUrl(), connect.getUsername(), connect.getPassword());
            Statement add = Con.createStatement();
            add.executeUpdate(UpdateQuery);
            //Con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addData() {
        Date date = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
        try {
            Con = DriverManager.getConnection(connect.getUrl(), connect.getUsername(), connect.getPassword());
            PreparedStatement add = Con.prepareStatement("insert into USER values(?,?,?,?,?,?)");
            add.setString(1, userN);
            add.setString(2, mail);
            add.setString(3, pass);
            add.setString(4, formatForDateNow.format(date));
            add.setInt(5, Integer.valueOf(id));
            add.setString(6, type);
            int row = add.executeUpdate();
            Con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
